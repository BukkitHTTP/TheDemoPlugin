package cf.huzpsb.sample.session;

import nano.http.d2.consts.Mime;
import nano.http.d2.consts.Status;
import nano.http.d2.core.Response;
import nano.http.d2.serve.ServeProvider;
import nano.http.d2.session.Captcha;
import nano.http.d2.session.Session;
import nano.http.d2.session.SessionManager;

import java.util.Properties;

public class SessionMain implements ServeProvider {

    // 致前后端不分离的全栈工程师：
    // 您是否认为这里的相对路径有点奇怪？其实我同意您的观点。
    // 只不过，这里为了演示尽可能多的功能，所以我无法将这个demo作为站点根目录。
    // 不过您可以修改主类，使得serve方法永远返回null，然后将所有业务代码放在fallback方法中。
    // 这样，您就可以使用绝对路径了。
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        Session session = SessionManager.getSession(header);
        switch (uri) {
            case "/login":
                switch (method) {
                    case "GET":
                        String reason;
                        if (parms.containsKey("code")) {
                            switch (parms.getProperty("code")) {
                                case "1":
                                    reason = "用户名或密码不正确!";
                                    break;
                                case "2":
                                    reason = "登出成功!";
                                    break;
                                case "3":
                                    reason = "请启用Cookie!";
                                    break;
                                case "4":
                                    reason = "请先登录!";
                                    break;
                                case "5":
                                    reason = "验证码不正确!";
                                    break;
                                default:
                                    reason = "未知错误!";
                                    break;
                            }
                        } else {
                            reason = "欢迎!";
                        }
                        return new Response(Status.HTTP_OK, Mime.MIME_HTML, SessionHTML.getLogin(reason));
                    case "POST":
                        Response response = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "ERROR");
                        response.addHeader("Location", "./login?code=3");
                        response = SessionManager.validateOrDie(session, null, response);
                        // null表示不需要验证权限，只验证是否建立了session
                        if (response != null) {
                            // 如果返回不是null，说明验证失败。
                            return response;
                        }

                        // 先看看是不是要登出
                        if (parms.containsKey("logout")) {
                            session.reset();
                            // 重置session，相当于登出
                            response = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "SUCCESS");
                            response.addHeader("Location", "./login?code=2");
                            return response;
                        }

                        // 不是登出，那就是在登录了
                        // 校验验证码。这个是最简单的，因为校验方法接受null，所以不需要验证是否存在。
                        if (!Captcha.validateCaptcha(session, parms.getProperty("captcha"))) {
                            response = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "Captcha Wrong");
                            response.addHeader("Location", "./login?code=5");
                            return response;
                        }
                        String username = parms.getProperty("username");
                        String password = parms.getProperty("password");
                        if (username == null || password == null) {
                            return new Response(Status.HTTP_BADREQUEST, Mime.MIME_PLAINTEXT, "400 Bad Request - Hello, world!");
                            // 也不是登录？搞什么鬼（
                        } else if (username.equals("admin") && password.equals("P@ssw0rd")) {
                            session.grantPermissions("user");
                            session.grantPermissions("admin");
                            session.setAttribute("username", username);
                            // Attribute和Permission不是Cookie，不会离开服务端。因此，这里可以存放一些敏感信息，不用担心被抓包。
                            // Attribute也可以存放String以外的自定义对象。
                            // 不过，需要注意的是，session在服务端重启后会被清空，所以并不能代替数据库。（不会真有人这么大胆吧？）
                            // 客户端也可以通过手动清空Cookie来破坏session，所以也不要用它做风控。
                            // session可以正确区分单设备上的多用户。
                            Response r = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "SUCCESS");
                            r.addHeader("Location", "./content");
                            return r;
                        } else if (username.startsWith("user") && password.equals("123456")) {
                            session.grantPermissions("user");
                            // 普通用户只有user权限
                            session.setAttribute("username", username);
                            Response r = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "SUCCESS");
                            r.addHeader("Location", "./content");
                            return r;
                        } else {
                            Response r = new Response(Status.HTTP_REDIRECT, Mime.MIME_HTML, SessionHTML.getLogin("Wrong username or password!"));
                            r.addHeader("Location", "./login?code=1");
                            return r;
                        }
                    default:
                        return new Response(Status.HTTP_NOTFOUND, Mime.MIME_PLAINTEXT, "404 Not found - Hello, world!");
                }
            case "/content":
                Response response = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "ERROR");
                response.addHeader("Location", "./login?code=4");
                response = SessionManager.validateOrDie(session, "user", response);
                // 这里验证了是否有user权限，如果没有，就说明没有登录，跳转到登录页面
                if (response != null) {
                    // 如果返回不是null，说明验证失败。
                    return response;
                }
                return new Response(Status.HTTP_OK, Mime.MIME_HTML, SessionHTML.getUser((String) session.getAttribute("username"), session.hasPermissions("admin") ? "管理员" : "普通用户"));
            case "/":
                Response r = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "SUCCESS");
                r.addHeader("Location", "./login");
                // 本来可以是首页什么的，但是这是个demo，所以直接跳转到登录页面
                return r;
            default:
                return new Response(Status.HTTP_NOTFOUND, Mime.MIME_PLAINTEXT, "404 Not found - Hello, world!");
        }
    }
}

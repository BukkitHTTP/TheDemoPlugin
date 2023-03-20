package cf.huzpsb.sample.database;

import cf.huzpsb.sample.Main;
import nano.http.d2.consts.Mime;
import nano.http.d2.consts.Status;
import nano.http.d2.core.Response;
import nano.http.d2.database.NanoDb;
import nano.http.d2.json.NanoJSON;
import nano.http.d2.serve.ServeProvider;

import java.util.Properties;

public class DbMain implements ServeProvider {
    private static final NanoDb<String, DbObject> db;

    static {
        try {
            db = new NanoDb<>("sample.db", Main.class.getClassLoader());
            // 这里是演示数据库的用法。NanoDb的构造器的第一个参数是数据库的文件名称，第二个参数是ClassLoader。
            // ClassLoader可以由模块的任意类获取，这里的Main当然可以被替换为DbMain，并没有什么深意。
        } catch (Exception e) {
            throw new RuntimeException(e);
            // 异常处理就不写了，反正是演示。
            // 但是需要注意的是，在生产环境中，这是不可取的，因为数据库的确可能保存失败了（会产生备份文件）。请通知用户。
        }
    }

    public static void onShutdown() {
        db.save();
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        switch (uri) {
            case "/create":
                if (!parms.containsKey("name") || !parms.containsKey("age") || !parms.containsKey("id")) {
                    return new Response(Status.HTTP_BADREQUEST, Mime.MIME_PLAINTEXT, "Bad Request");
                }
                String id = parms.getProperty("id");
                // NanoDb不需要任何的SQL注入检查。您可以拿'or'='当key试试。您会发现它就像其他的项一样，被正常处理了。
                if (db.contains(id)) {
                    return new Response(Status.HTTP_FORBIDDEN, Mime.MIME_PLAINTEXT, "Already Exists");
                }
                db.set(id, new DbObject(parms.getProperty("name"), parms.getProperty("age")));
                return new Response(Status.HTTP_OK, Mime.MIME_PLAINTEXT, "OK");
            case "/delete":
                if (!parms.containsKey("id")) {
                    return new Response(Status.HTTP_BADREQUEST, Mime.MIME_PLAINTEXT, "Bad Request");
                }
                db.remove(parms.getProperty("id"));
                return new Response(Status.HTTP_OK, Mime.MIME_PLAINTEXT, "OK");
            case "/query":
                if (!parms.containsKey("id")) {
                    return new Response(Status.HTTP_BADREQUEST, Mime.MIME_PLAINTEXT, "Bad Request");
                }
                String id2 = parms.getProperty("id");
                if (!db.contains(id2)) {
                    return new Response(Status.HTTP_NOTFOUND, Mime.MIME_PLAINTEXT, "Not Found");
                }
                return new Response(Status.HTTP_OK, Mime.MIME_JSON, NanoJSON.asJSON(db.query(id2)));
            // 严格来说这里违反了线程安全：尽管上一步已经判断了id2是否存在，但是在这一步取值之前，数据库可能已经被修改了。
            // 不过无所谓，因为就算抛了NPE，也不会影响到数据库的正常使用，大不了InternalError掉一个请求。
            // 如果有控制台洁癖，可以给个空的try-catch。注明一下，怕有人误会。
            case "/download":
                Response r = new Response(Status.HTTP_OK, Mime.MIME_DEFAULT_BINARY, db.toCSV(DbObject.class, "姓名", DbLocalizer.getLocalizer()));
                r.addHeader("Content-Disposition", "attachment; filename=\"sample.csv\"");
                return r;
            default:
                return new Response(Status.HTTP_OK, Mime.MIME_HTML, DbHTML.HTML);
        }
    }
}

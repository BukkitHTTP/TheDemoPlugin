package cf.huzpsb.sample;

import cf.huzpsb.sample.commands.CommandManager;
import cf.huzpsb.sample.database.DbMain;
import cf.huzpsb.sample.files.DemoFileServer;
import cf.huzpsb.sample.session.SessionMain;
import nano.http.bukkit.api.BukkitServerProvider;
import nano.http.d2.console.Logger;
import nano.http.d2.consts.Mime;
import nano.http.d2.consts.Status;
import nano.http.d2.core.Response;
import nano.http.d2.utils.Router;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends BukkitServerProvider {
    public static String preUri;
    public static File dir;
    private static String name;
    private static Router router = null;

    @Override
    public void onEnable(String name, File dir, String uri) {
        Main.name = name;
        Main.dir = dir;
        Main.preUri = uri;
        if (router == null) {
            // 这里使用懒加载。主要是避免实例化过多的Router，减轻GC压力。毕竟这些类是单例的。
            router = new Router(new DefaultRouter());
            // 演示Router（路由）的用法。构造器的参数是FallBack，如果没有匹配到路由，就会调用FallBack的serve方法。
            // 也提供了取资源文件的写法示范。
            router.add("/session", new SessionMain());
            // 演示session（会话）的用法
            router.add("/db", new DbMain());
            // 演示db（数据库）的用法和Nano的用法
            router.add("/files", new DemoFileServer());
        }

        File config = new File(Main.dir, "config.properties");
        if (!config.exists()) {
            throw new RuntimeException("Config file not found.");
        }
        Properties pr = new Properties();
        try {
            pr.load(new FileReader(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Logger.info("DemoPlugin: " + pr.getProperty("message"));
        // 这里是读取配置文件的演示。

        Logger.info("DemoPlugin: Module has been loaded");
        // 节约性能，Logger不会判断调用方是哪个模块，所以不加模块名可能会导致混乱

        CommandManager.registerCommands();
        // 演示command（命令）的用法

        // 请不要忘了查看misc文件夹下的内容！
        // Misc文件夹演示了Nano系列工具类的用法，例如Base64，数据库版本迁移和出站HTTP请求。
    }

    @Override
    public void onDisable() {
        Logger.info("DemoPlugin: Module has been unloaded");
        DbMain.onShutdown();
        // 关闭数据库！不要忘记了！忘记了数据就没了！
        CommandManager.unregisterCommands();
        // 注销命令！也不要忘记了！忘记了不但GC不干净，下一次加载的时候还会报错（命令冲突）！
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        return router.serve(uri, method, header, parms, files);
    }

    @Override
    public Response fallback(String uri, String method, Properties header, Properties parms, Properties files) {
        Response r = new Response(Status.HTTP_REDIRECT, Mime.MIME_PLAINTEXT, "QWQ");
        r.addHeader("Location", preUri + "/#");
        return r;
        // 这里是对FallBack的演示。这里的意思是，如果没有匹配到路由，就跳转到这个模块的主页
    }
}

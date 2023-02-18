package nano.bukkit.example;

import nano.http.bukkit.api.BukkitServerProvider;
import nano.http.d2.console.Logger;
import nano.http.d2.core.Response;
import nano.http.d2.serve.FileServer;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends BukkitServerProvider {
    private static String name;
    private static File dir;
    private static String preUri;

    @Override
    public void onEnable(String name, File dir, String uri) {
        Main.name = name;
        Main.dir = dir;
        Main.preUri = uri;
        File config = new File(dir, "config.properties");
        if (!config.exists()) {
            throw new RuntimeException("Config file not found.");
        }
        Properties pr = new Properties();
        try {
            pr.load(new FileReader(config));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Config.doFallback = Boolean.parseBoolean(pr.getProperty("doFallback", "true"));
        Config.allowDirListing = Boolean.parseBoolean(pr.getProperty("allowDirListing", "true"));
        Logger.info("Hello from : " + name);
    }

    @Override
    public void onDisable() {
        Logger.info("Goodbye from : " + name);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        return FileServer.serveFile(uri, preUri, header, new File(dir, "webroot"), Config.allowDirListing);
    }

    @Override
    public Response fallback(String uri, String method, Properties header, Properties parms, Properties files) {
        if (Config.doFallback) {
            return FileServer.serveFile(uri, header, new File(dir, "fallback"), Config.allowDirListing);
        } else {
            return null;
        }
    }
}

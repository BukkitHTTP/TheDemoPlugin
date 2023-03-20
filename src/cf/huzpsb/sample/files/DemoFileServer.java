package cf.huzpsb.sample.files;

import cf.huzpsb.sample.Main;
import nano.http.d2.core.Response;
import nano.http.d2.serve.FileServer;
import nano.http.d2.serve.ServeProvider;

import java.util.Properties;

public class DemoFileServer implements ServeProvider {

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        return FileServer.serveFile(uri, Main.preUri + "/files", header, Main.dir, true);
    }
}

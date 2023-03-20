package cf.huzpsb.sample;

import nano.http.d2.consts.Mime;
import nano.http.d2.consts.Status;
import nano.http.d2.core.Response;
import nano.http.d2.serve.ServeProvider;

import java.util.Properties;

public class DefaultRouter implements ServeProvider {
    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        return new Response(Status.HTTP_OK, Mime.MIME_HTML, Main.class.getClassLoader().getResourceAsStream("index.html"));
        // getResourceAsStream被Bukkit重写了，所以可以直接读取jar包里的文件，而不用担心取到其他模块的内容。
    }
}

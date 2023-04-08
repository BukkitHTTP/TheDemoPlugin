package cf.huzpsb.sample;

import nano.http.bukkit.api.debug.DebugMain;

// 您可以直接运行此类来调试您的插件，而无需启动服务器。这样可以节省您的时间，也可以使用IDE的调试器。
public class DevMain {
    public static void main(String[] args) throws Exception {
        DebugMain.debug(Main.class, "/test");
    }
}

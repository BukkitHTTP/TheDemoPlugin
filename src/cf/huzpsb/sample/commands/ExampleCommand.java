package cf.huzpsb.sample.commands;

import nano.http.d2.console.Console;
import nano.http.d2.console.Logger;

public class ExampleCommand implements Runnable {
    @Override
    public void run() {
        // 命令会在一个新的线程中被执行。
        Logger.info("Please input your name:");
        String name = Console.await();
        // Console.await()会阻塞当前线程，直到用户输入了一行文本。
        Logger.info("Hello, " + name + "!");
    }
}

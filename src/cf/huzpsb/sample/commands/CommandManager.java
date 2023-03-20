package cf.huzpsb.sample.commands;

import nano.http.d2.console.Console;

public class CommandManager {
    public static void registerCommands() {
        Console.register("sample", new ExampleCommand());
    }

    public static void unregisterCommands() {
        Console.unregister("sample");
    }
}

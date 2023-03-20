package cf.huzpsb.sample.database;

import nano.http.d2.database.csv.Localizer;

public class DbLocalizer {
    private static Localizer localizer = null;

    public static Localizer getLocalizer() {
        if (localizer == null) {
            localizer = new Localizer();
            localizer.add("name", "学号");
            localizer.add("age", "手机号");
            // 这里演示的是如何通过Localizer来实现表头替换。
            // 我们故意把翻译的内容写错了，这样就可以更好的演示替换原理。
        }
        return localizer;
    }
}

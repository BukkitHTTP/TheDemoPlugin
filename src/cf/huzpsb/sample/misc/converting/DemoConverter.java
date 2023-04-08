package cf.huzpsb.sample.misc.converting;

import cf.huzpsb.sample.Main;
import nano.http.d2.database.NanoDb;
import nano.http.d2.json.NanoJSON;

public class DemoConverter {
    public static void main(String[] args) throws Exception {
        NanoDb<String, DemoDbObj2> db = new NanoDb<>("sample.db", DemoDbObj2.class, Main.class.getClassLoader());
        // 请注意！请注意！请注意！
        // 1 转换不可逆！转换会覆盖原有数据库！
        // 在转换之前，控制台也会要求你确认操作（yes/no）。
        // 2 转换过程需要旧的数据库obj！不要认为新版上线了，就可以把旧版的数据库obj删了！
        // IDE会跟你说旧的obj是unused，但是不要相信它！它还会说主类是unused嘞，你信吗？
        // 留着所有的旧的obj，以便在需要时进行转换。

        db.save();
        // 保存或者等待自动保存。都行。
        // 这里手动保存一下是为了演示这个构造器在数据库类型正确时，不会有额外提示。
        // 如果发布新版本有不同的数据库obj，在本地验证过转换正常后，可以直接把发行版的构造器改写成这样。
        // 既不影响正常使用，也可以实现向下兼容。

        DemoDbObj2 obj = db.query("小明");
        System.out.println(new NanoJSON(obj));
    }
}

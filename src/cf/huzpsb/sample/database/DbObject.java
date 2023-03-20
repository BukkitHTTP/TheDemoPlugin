package cf.huzpsb.sample.database;

import java.io.Serializable;

public class DbObject implements Serializable {
    // 这里的Serializable是必须的！这里的Serializable是必须的！这里的Serializable是必须的！
    // 这是NanoDb的要求，NanoDb会将所有的数据序列化为二进制文件，所以必须实现Serializable接口。

    // 一旦数据库文件被建立，就请不要再修改这个类的结构了！
    // 如果有修改需要，您可以手动删除数据库文件，然后再启动服务器，NanoDb会自动重建数据库文件。
    // 如果有继承数据需要，您可以查看Misc包中的DemoConverter。请放心，这并不复杂。

    private static final long serialVersionUID = 1145142333L;
    // 随手标serialVersionUID是好文明！

    public String name;
    // 这里风格不一致是为了演示NanoDb与NanoJSON兼容的特性。这个是public风格的。
    private String age;

    public DbObject(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

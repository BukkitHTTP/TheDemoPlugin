package cf.huzpsb.sample.misc.converting;

import java.io.Serializable;

@SuppressWarnings("unused")
public class DemoDbObj2 implements Serializable {
    private static final long serialVersionUID = 1145142334L;
    // 随手标serialVersionUID是好文明！不过不要标重复了，不然会炸的。

    // 数据库迁移规则：
    // 按照名称匹配，无视访问修饰符。

    // 名称相同的：
    // 如果类型相同，那就直接复制。
    // 如果类型不同，会尝试强制转换（即子类可以被转为父类）。（最好别整这花活。我的建议是，修改能小就小；故意改字段类型是搞什么）
    // 转换失败？那就炸了。

    // 名称不同的：
    // 旧类有新类没有的字段，会被删除。
    // 新类有旧类没有的字段，会被赋初值。
    // 没有初值？那也炸了。

    // 新类必须要有无参构造器。（最好显示声明一个无参构造器）
    public int newField = 5;
    // 数据宝贵，安全第一！
    private String name;
    // name的可见性被从public改为了private，以演示访问修饰符并不影响。
    // name字段会被复制。

    // private String age;
    // age字段被删除了。

    public DemoDbObj2() {
        // 无参构造器
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // newField字段被添加了。
    // 赋初值最好按照这样的写法，而不是由构造器赋。人类永远也搞不清楚该死的jvm有什么奇怪的构造器调用顺序。
    // 虽说一般来说，不会出事，但是万一呢？
    // 如果有复杂的初值需要赋予，那就请用static块。珍爱生命，远离构造器！
}

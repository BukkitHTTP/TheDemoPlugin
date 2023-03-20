package cf.huzpsb.sample.misc.encoding;

import nano.http.d2.utils.Encoding;

public class DemoEncoding {
    public static void main(String[] args) throws Exception {
        System.out.println("HelloWorld的Base64编码是：" + Encoding.enBase64("HelloWorld"));
        System.out.println("SGVsbG9Xb3JsZA==的Base64解码是：" + Encoding.deBase64("SGVsbG9Xb3JsZA=="));
        System.out.println("HelloWorld的URL编码是：" + Encoding.enURL("HelloWorld"));
        System.out.println("Hello%3DWorld的URL解码是：" + Encoding.deURL("Hello%3DWorld"));
    }
}

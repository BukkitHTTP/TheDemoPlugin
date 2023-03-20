package cf.huzpsb.sample.misc.requesting;

import nano.http.d2.utils.Request;

public class OutwardRequest {
    public static void main(String[] args) {
        System.out.println(Request.get("https://www.baidu.com", null));
    }
}

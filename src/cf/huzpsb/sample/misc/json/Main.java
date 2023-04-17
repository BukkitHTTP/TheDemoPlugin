package cf.huzpsb.sample.misc.json;

import nano.http.d2.json.NanoJSON;

public class Main {
    public static void main(String[] args) {
        String json = "{\"name\":\"huzpsb\",\"age\":18}";
        NanoJSON nanoJSON = new NanoJSON(json);
        System.out.println(nanoJSON.get("name"));
        nanoJSON.put("name", "huzpsb2");
        System.out.println(nanoJSON);
    }
}

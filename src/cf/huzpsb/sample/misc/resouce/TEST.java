package cf.huzpsb.sample.misc.resouce;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("ALL")
public class TEST {
    public static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        return baos.toByteArray();
    }

    public static void test() {
        try {
            byte[] rel = readAllBytes(TEST.class.getResourceAsStream("REL.TXT"));
            byte[] abs = readAllBytes(TEST.class.getResourceAsStream("/cf/huzpsb/sample/misc/resouce/REL.TXT"));
            System.out.println(new String(rel));
            System.out.println(new String(abs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package DataMining;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by oscar on 2016/3/1.
 */
public class Zip {

    public static String uncompressToString(InputStream in, String charset) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

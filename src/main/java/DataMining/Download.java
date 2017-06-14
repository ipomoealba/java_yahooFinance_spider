package DataMining;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by oscar on 2/29/16.
 */
public class Download {
    String HomeDirectory = null;

    public void downloadCsv(int fileNum, String csvUrl) {
        javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView();
        OSValidator os = new OSValidator();

        if (os.isWindows()) {
            HomeDirectory = fsv.getHomeDirectory() + "\\";
        } else if (os.isMac() || os.isUnix()) {
            HomeDirectory = fsv.getHomeDirectory() + "/";
        }
        HomeDirectory += "Fund_Data";
        File file = new File(HomeDirectory, fileNum + ".csv");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        try {

            URL url = new URL(csvUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedInputStream is = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(file);
            conn.setConnectTimeout(5000);
            byte[] b = new byte[8192];
            int l = 0;
            while ((l = is.read(b)) != -1) {
                fos.write(b, 0, l);
            }

            //釋放資源
            fos.close();
            is.close();
            conn.disconnect();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

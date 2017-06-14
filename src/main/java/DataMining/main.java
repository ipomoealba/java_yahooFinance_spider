package DataMining;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.Document;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oscar on 2/24/16.
 */
public class main {

    public static void main(String[] args) throws IOException {
        String HomeDirectory = null;
        System.setOut(new PrintStream(System.out, true, "utf-8"));
        HashMap<Integer, String> comparatorTable = new HashMap<Integer, String>();
//        System.out.println(c.getResults());
        fuckConnect ccLemon = new fuckConnect();
        ArrayList<String> firstLevelWeb = ccLemon.getResults();
        int i = 1;
        for (String s : firstLevelWeb) {
            Connect c = new Connect(s);
            HashMap<String, String> page = null;
            page = c.getResults();

            Download d = new Download();
//                   int t = 1;
            for (Map.Entry<String, String> entry2 : page.entrySet()) {
//					int count = 0;
                String title = entry2.getKey();
                String urlString = entry2.getValue();
                System.out.println("Downloading " + title);

                d.downloadCsv(i, entry2.getValue());
                comparatorTable.put(i, entry2.getKey());
                i++;
//                t++;
//                if(t > 10){
//                    break;
//                }
                System.out.println(title + " is downloaded.");
            }


        }


        ComareTable ct = new ComareTable(comparatorTable);
        javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView();
        OSValidator os = new OSValidator();
        String slash = null;
        if (os.isWindows()) {
            slash = "\\";
        } else if (os.isMac() || os.isUnix()) {
            slash = "/";
        }
        HomeDirectory = String.valueOf(fsv.getHomeDirectory()) + slash;
        HomeDirectory += "Fund_Data";
        System.out.println(HomeDirectory);
        File file = new File(HomeDirectory, "ComareTable" + ".txt");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            fw.write(ct.output());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(ct.output());

    }

}

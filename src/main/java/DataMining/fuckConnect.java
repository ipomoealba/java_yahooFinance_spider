package DataMining;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by oscar on 2016/3/1.
 */
public class fuckConnect {
    public String fetchCountent(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        System.out.println("Search URL:" + url + "\n");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("user-agent", "Mozilla/5.0 Chrome/48.0.2564.116");
        conn.setRequestProperty("accept-language",
                "zh-TW,zh;q=0.8,en-US;q=0.6,en;q=0.4,zh-CN;q=0.2");
        conn.setRequestProperty("accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        InputStream in = conn.getInputStream();
        String x = Zip.uncompressToString(in, "UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String retVal = "";

        String line = null;
        while ((line = br.readLine()) != null) {
            retVal += (line + "\n");

        }
        return x;

    }

    public ArrayList<String> getResults() throws IOException {
        String content = null;
        ArrayList<String> retVal = new ArrayList<>();
        if (content == null) {
            content = fetchCountent("https://tw.money.yahoo.com/fund/offshore");

        }
        Document doc = Jsoup.parse(content);
        Elements Divs = doc.select(".Ta-start");

        System.out.println("Search Result:");
        System.out.println(Divs);
        for (Element Div : Divs) {
            try {
                Element aTag = Div.select("a[href]").get(0);
                String url = aTag.attr("href");
                String title = Div.text();
                if (url.toLowerCase().startsWith("/fund/cpflist/")) {
                    String downloadUrl = url.substring(url.indexOf("cpflist/"));
                    retVal.add("https://tw.money.yahoo.com/fund/" + downloadUrl);
                }
            } catch (IndexOutOfBoundsException ex) {

            }


        }
        return retVal;

    }

}

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
 * Created by oscar on 2/29/16.
 */
public class Connect {
    private String strUrl;
    public Connect(String strUrl){
        this.strUrl = strUrl;
    }
    private  String fetchCountent(String strUrl) throws IOException {
        URL url = new URL(strUrl);
        System.out.println("Search URL:" + url + "\n");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("user-agent", "Mozilla/5.0 Chrome/48.0.2564.116");
        conn.setRequestProperty("accept-language",
                "zh-TW,zh;q=0.8,en-US;q=0.6,en;q=0.4,zh-CN;q=0.2");
        conn.setRequestProperty("accept",
                "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        InputStream in = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String retVal = "";

        String line = null;
        while ((line = br.readLine()) != null) {
            retVal += (line + "\n");

        }
        return retVal;

    }

    public HashMap<String, String> getResults() throws IOException {
        String content = null;
        HashMap<String, String> retVal = new HashMap<>();


        if (content == null) {
            content = fetchCountent(strUrl);

        }
        Document doc = Jsoup.parse(content);
        Elements Tds = doc.select(".Ta-start").select(".txt-left").select(".id");

        System.out.println("Search Result:");
        System.out.println(Tds);
        for (Element Td : Tds) {
            try {
                Element aTag = Td.select("a[href]").get(0);
                String url = aTag.attr("href");
                String title = Td.text();
                if (url.toLowerCase().startsWith("/fund/summary/")) {
                    String downloadUrl = url.replace("summary", "download");
                    String date = "?startDate=1900-01-01&endDate=2016-02-26";
                    retVal.put(title, "https://tw.money.yahoo.com" + downloadUrl + date);
                }
            } catch (IndexOutOfBoundsException ex) {

            }


        }
        return retVal;

    }


}

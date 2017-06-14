package DataMining;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by oscar on 2/29/16.
 */
public class ComareTable {


    private HashMap<Integer, String> map = new HashMap<Integer, String>();

    ComareTable(HashMap<Integer, String> map) {
        this.map = map;
    }


    public String output() {


        //實例化gson

        Gson gson = new Gson();

        //將map轉成json

        String str = gson.toJson(map);

        //設置json格式的header並輸出json內容


        return str;
    }

}

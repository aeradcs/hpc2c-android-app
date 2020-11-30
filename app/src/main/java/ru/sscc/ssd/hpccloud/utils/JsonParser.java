package ru.sscc.ssd.hpccloud.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class JsonParser {


    public String getToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("tokens");
        JSONObject jsonTokenObject = jsonArray.getJSONObject(0);
        return jsonTokenObject.getString("token");
    }
    public void parseUserInfo(String response, ArrayList<String> keys,ArrayList<String> values) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arrayFromObject = object.getJSONArray("users");
        JSONObject objectFromArray = arrayFromObject.getJSONObject(0);
        LinkedHashMap<String , String > userInfo = new LinkedHashMap<>();

        int length = objectFromArray.length();
        Iterator<String> itKeys = objectFromArray.keys();
        String key;
        for(int i = 0; i < length; i++){
            key = itKeys.next();
            keys.add(key);
            values.add(objectFromArray.getString(key));
        }
    }
}

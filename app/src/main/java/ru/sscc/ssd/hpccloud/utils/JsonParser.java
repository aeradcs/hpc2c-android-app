package ru.sscc.ssd.hpccloud.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public LinkedHashMap<String, String> parseUserInfo(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        JSONArray arrayFromObject = object.getJSONArray("users");
        JSONObject objectFromArray = arrayFromObject.getJSONObject(0);
        LinkedHashMap<String , String > userInfo = new LinkedHashMap<>();

        int length = objectFromArray.length();
        Iterator<String> keys = objectFromArray.keys();
        String key, value;

        for(int i = 0; i < length; i++){
            key = keys.next();
            value = objectFromArray.getString(key);
            userInfo.put(key, value);
        }
        return userInfo;
    }
}

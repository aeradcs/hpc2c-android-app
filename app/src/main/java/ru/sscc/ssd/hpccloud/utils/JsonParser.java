package ru.sscc.ssd.hpccloud.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {


    public String getToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("tokens");
        JSONObject jsonTokenObject = jsonArray.getJSONObject(0);
        return jsonTokenObject.getString("token");
    }
}

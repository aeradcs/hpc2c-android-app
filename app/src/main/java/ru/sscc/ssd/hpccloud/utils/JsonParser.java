package ru.sscc.ssd.hpccloud.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class JsonParser {
    //private static String requestType;

    public String getToken(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("tokens");
        JSONObject jsonTokenObject = jsonArray.getJSONObject(0);
        return jsonTokenObject.getString("token");
    }
    public void parseUserInfo(String response, ArrayList<String> keys, ArrayList<String> values) throws JSONException {//user info
        JSONObject object = new JSONObject(response);
        Iterator<String> iterator = object.keys();
        //requestType = object.keys().next();
        JSONArray arrayFromObject = object.getJSONArray(iterator.next());
        JSONObject objectFromArrayFromObject = arrayFromObject.getJSONObject(0);

        int length = objectFromArrayFromObject.length();
        Iterator<String> itKeys = objectFromArrayFromObject.keys();
        String key;
        for(int i = 0; i < length; i++){
            key = itKeys.next();
            keys.add(key);
            values.add(objectFromArrayFromObject.getString(key));
        }
    }
    public void parseApplications(String response, ArrayList<String> keys, ArrayList<String> values) throws JSONException {
        JSONObject object = new JSONObject(response);
        Iterator<String> iterator = object.keys();
        //requestType = object.keys().next();
        JSONArray arrayFromObject = object.getJSONArray(iterator.next());
        JSONObject objectFromArrayFromObject;
        int arrLength = arrayFromObject.length();
        int length;
        String key;
        for(int i = 0; i < arrLength; i++) {
            objectFromArrayFromObject = arrayFromObject.getJSONObject(i);
            length = objectFromArrayFromObject.length();
            Iterator<String> itKeys = objectFromArrayFromObject.keys();

            for(int j = 0; j < length; j++){
                key = itKeys.next();
                if(i==0) {
                    keys.add(key);
                }
                values.add(objectFromArrayFromObject.getString(key));
            }
        }
    }
    public void parseJobs(String response, ArrayList<String> keys, ArrayList<String> values) throws JSONException {
        JSONObject object = new JSONObject(response);
        Iterator<String> iterator = object.keys();
        //requestType = object.keys().next();
        JSONArray arrayFromObject = object.getJSONArray(iterator.next());
        JSONObject objectFromArrayFromObject;
        int arrLength = arrayFromObject.length();
        int length;
        String key;
        for(int i = 0; i < arrLength; i++) {
            objectFromArrayFromObject = arrayFromObject.getJSONObject(i);
            length = objectFromArrayFromObject.length();
            Iterator<String> itKeys = objectFromArrayFromObject.keys();

            for(int j = 0; j < length; j++){
                key = itKeys.next();
                if(i==0) {
                    keys.add(key);
                }
                values.add(objectFromArrayFromObject.getString(key));
            }
        }
    }
   /* public String getRequestType() {
        return requestType;
    }*/
}

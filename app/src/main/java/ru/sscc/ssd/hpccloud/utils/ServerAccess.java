package ru.sscc.ssd.hpccloud.utils;

import android.content.ContentValues;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.SplittableRandom;

import ru.sscc.ssd.hpccloud.ApplicationsPageActivity;

import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;

public class ServerAccess {
    private static final String HTTP = "http://";
    private static final String HPCCLOUD = "hpccloud.ssd.sscc.ru:4000/";
    private static final String API_NUMBER = "api/1.0/";
    private static final String TOKENS = "tokens";
    private static final String ACCESS_TOKEN = "access_token=";

    public static URL generateRegistrationURL(){
        Uri uri = Uri.parse(HTTP + HPCCLOUD + API_NUMBER + "users").buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static URL generateAuthorizationURL() {
        Uri uri = Uri.parse(HTTP + HPCCLOUD + API_NUMBER + TOKENS).buildUpon().build();    //   http://hpccloud.ssd.sscc.ru:4000/api/1.0/tokens
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL generateURL(String token, String typeOfRequest){
        Uri uri = Uri.parse(HTTP + HPCCLOUD + API_NUMBER + typeOfRequest + ACCESS_TOKEN + token).buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static int getRegistrationResponse(URL url, ArrayList<String > params, String auth) throws IOException, JSONException {

        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        BufferedWriter bufferedWriter;


        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept","application/json");
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        String authHeaderValue = "Basic " + new String(auth);
        httpURLConnection.setRequestProperty("Authorization", authHeaderValue);

        String json = new JSONObject()
                .put("lvl", "0")
                .put("firstname", params.get(0))
                .put("lastname", params.get(1))
                .put("e_mail", params.get(2))
                .put("access_code", params.get(3))
                .toString();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        writer.write(json);
        writer.flush();

        httpURLConnection.connect();

        int code = httpURLConnection.getResponseCode();

        writer.close();
        outputStream.close();

        return code;






        /*if (code == 201) {


            InputStream input = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            return code;


        }
        else if(code == 409)
            return code;
        else return -1;*/
    }
    public static String getAuthorizationResponseFromServer(URL url, String auth) throws IOException {

       HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

       String authHeaderValue = "Basic " + new String(auth);
       urlConnection.setRequestProperty("Authorization", authHeaderValue);

       int code = urlConnection.getResponseCode();

       try {
           InputStream inputStreamFromServer = new BufferedInputStream(urlConnection.getInputStream());
           Scanner scanner = new Scanner(inputStreamFromServer);
           scanner.useDelimiter("\\A");

           if (scanner.hasNext())
               return scanner.next();
           else
               return null;
       } finally{
           urlConnection.disconnect();
       }
    }
    public static String getResponse(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        try {
            InputStream inputStreamFromServer = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(inputStreamFromServer);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext())
                return scanner.next();
            else
                return null;
        } finally{
            urlConnection.disconnect();
        }
    }


}
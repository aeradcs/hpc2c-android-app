package ru.sscc.ssd.hpccloud.utils;

import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class ServerAccess {
    private static final String HTTP = "http://";
    private static final String HPCCLOUD = "hpccloud.ssd.sscc.ru:4000/";
    private static final String API_NUMBER = "api/1.0/";
    private static final String TOKENS = "tokens";

    public static URL generateURL(String UserLogin, String UserPassword) {
        Uri uri = Uri.parse(HTTP + UserLogin + ":" + UserPassword + "@" + HPCCLOUD + API_NUMBER + TOKENS).buildUpon().build();    //http://jeje:jejepass@hpccloud.ssd.sscc.ru:4000/api/1.0/tokens
        URL url = null;
        try {
             url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromServer(URL url) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");

        int s = urlConnection.getResponseCode();

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

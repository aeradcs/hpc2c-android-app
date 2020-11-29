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
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import ru.sscc.ssd.hpccloud.ApplicationsPageActivity;

public class ServerAccess {
    private static final String HTTP = "http://";
    private static final String HPCCLOUD = "hpccloud.ssd.sscc.ru:4000/";
    private static final String API_NUMBER = "api/1.0/";
    private static final String TOKENS = "tokens";
    private static final String POJECTS = "projects?";
    private static final String ACCESS_TOKEN = "access_token=";
    public static URL generateURL() {
        Uri uri = Uri.parse(HTTP + HPCCLOUD + API_NUMBER + TOKENS).buildUpon().build();    //   http://hpccloud.ssd.sscc.ru:4000/api/1.0/tokens
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static URL generateURLForApplicationsRequest(String token){
        Uri uri = Uri.parse(HTTP + HPCCLOUD + API_NUMBER + POJECTS + ACCESS_TOKEN + token).buildUpon().build();
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getApplicationsListFromServer(URL url, String token) throws IOException {
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
    public static String getResponseFromServer(URL url, String auth) throws IOException {

        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        String authHeaderValue = "Basic " + new String(auth);
        urlConnection.setRequestProperty("Authorization", authHeaderValue);

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
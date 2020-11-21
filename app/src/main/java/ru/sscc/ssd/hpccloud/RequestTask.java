package ru.sscc.ssd.hpccloud;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.getResponseFromServer;

//public class RequestTask extends AsyncTask<URL, Void, String> {
//
//    @Override
//    protected String doInBackground(URL... urls) {
//        String responseFromServer = null;
//        try{
//            responseFromServer = getResponseFromServer(urls[0]);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return responseFromServer;
//    }
//    @Override
//    protected void onPostExecute(String response) {
//        .setText(response);
//    }
//}

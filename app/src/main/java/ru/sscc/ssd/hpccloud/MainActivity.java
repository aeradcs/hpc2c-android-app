package ru.sscc.ssd.hpccloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.getResponseFromServer;


public class MainActivity extends AppCompatActivity {
    private EditText userLogin;
    private EditText userPassword;
    private TextView res;


    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try{
                responseFromServer = getResponseFromServer(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(String response) {
            res.setText(response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLogin = findViewById(R.id.UserLogin);
        userPassword = findViewById(R.id.UserPassword);
        res = findViewById(R.id.res);


        Button buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = generateURL(userLogin.getText().toString(), userPassword.getText().toString());
                new RequestTask().execute(generatedUrl);
            }


        });

    }
}
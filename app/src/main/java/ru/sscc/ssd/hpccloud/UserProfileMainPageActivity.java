package ru.sscc.ssd.hpccloud;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

import ru.sscc.ssd.hpccloud.utils.ServerAccess;


public class UserProfileMainPageActivity extends AppCompatActivity {
    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;

            try {
                responseFromServer = ServerAccess.getResponse(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseFromServer;

        }
        @Override
        protected void onPostExecute(String response) {
            //Intent intentApplications = new Intent(UserProfileMainPageActivity.this, ApplicationsPageActivity.class);
            //startActivity(intentApplications);
            TextView textView = findViewById(R.id.textView);
            textView.setText(response);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_main_page);


        Button buttonApplications = (Button) findViewById(R.id.buttonProjects);
        buttonApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "projects?");
                new RequestTask().execute(generatedUrl);

            }
        });
        Button buttonJobs = (Button)findViewById(R.id.buttonJobs);
        buttonJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "jobs?");
                new RequestTask().execute(generatedUrl);
            }
        });
        Button buttonDocuments = (Button)findViewById(R.id.buttonDocuments);
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "fs/?");
                new RequestTask().execute(generatedUrl);
            }
        });

        Button buttonUserInfo = (Button)findViewById(R.id.buttonUserInfo);
        buttonUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "users?");
                new RequestTask().execute(generatedUrl);
            }
        });
    }




}
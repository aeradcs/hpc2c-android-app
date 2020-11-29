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

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import ru.sscc.ssd.hpccloud.utils.ServerAccess;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURLForApplicationsRequest;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURLForDocumentsRequest;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURLForJobsRequest;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.getResponse;

public class UserProfileMainPageActivity extends AppCompatActivity {
    private String typeOfRequest;
    public class RequestTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;

            try {
                responseFromServer = ServerAccess.getResponse(urls[0], LoginPageActivity.getTokenUserId());
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
                typeOfRequest = "applications";
                URL generatedUrl = ServerAccess.generateURLForApplicationsRequest(LoginPageActivity.getTokenUserId());
                new RequestTask().execute(generatedUrl);

            }
        });
        Button buttonJobs = (Button)findViewById(R.id.buttonJobs);
        buttonJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeOfRequest = "jobs";
                URL generatedUrl = ServerAccess.generateURLForJobsRequest(LoginPageActivity.getTokenUserId());
                new RequestTask().execute(generatedUrl);
            }
        });
        Button buttonDocuments = (Button)findViewById(R.id.buttonDocuments);
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeOfRequest = "documents";
                URL generatedUrl = ServerAccess.generateURLForDocumentsRequest(LoginPageActivity.getTokenUserId());
                new RequestTask().execute(generatedUrl);
            }
        });

        Button buttonUserInfo = (Button)findViewById(R.id.buttonUserInfo);
        buttonUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeOfRequest = "userInfo";
                URL generatedUrl = ServerAccess.generateURLForUserInfoRequest(LoginPageActivity.getTokenUserId());
                new RequestTask().execute(generatedUrl);
            }
        });
    }




}
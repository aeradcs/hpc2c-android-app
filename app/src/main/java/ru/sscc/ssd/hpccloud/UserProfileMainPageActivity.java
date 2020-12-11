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

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.ServerAccess;


public class UserProfileMainPageActivity extends AppCompatActivity {
    private  static  int num;
    private static String responseFromServer;
    private String requestType;
    //private JsonParser jsonParser = new JsonParser();

    public static String getResponseFromServer(){
        return responseFromServer;
    }

    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                responseFromServer = ServerAccess.getResponse(urls[0]);
                //requestType = jsonParser.getRequestType();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseFromServer;

        }
        @Override
        protected void onPostExecute(String response) {
            //не знаю как сделать это по-другому
            if (requestType.equals("directory"))
            {
                Intent intentDirectory = new Intent(UserProfileMainPageActivity.this, DocumentsPageActivity.class);
                startActivity(intentDirectory);
            }
            else if (requestType.equals("jobs"))
            {
                Intent intentJobs = new Intent(UserProfileMainPageActivity.this, JobsPageActivity.class);
                startActivity(intentJobs);
            }
            else if(requestType.equals("projects"))
            {
                Intent intentApplications = new Intent(UserProfileMainPageActivity.this, ApplicationsPageActivity.class);
                startActivity(intentApplications);
            }
            else if(requestType.equals("users"))
            {
                Intent intentUserInfo = new Intent(UserProfileMainPageActivity.this, UserInfoPageActivity.class);
                startActivity(intentUserInfo);
            }
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
                requestType = "projects";
            }
        });
        Button buttonJobs = (Button)findViewById(R.id.buttonJobs);
        buttonJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "jobs?");
                new RequestTask().execute(generatedUrl);
                requestType = "jobs";

            }
        });
        Button buttonDocuments = (Button)findViewById(R.id.buttonDocuments);
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "fs/?");
                new RequestTask().execute(generatedUrl);
                requestType = "directory";

            }
        });

        Button buttonUserInfo = (Button)findViewById(R.id.buttonUserInfo);
        buttonUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(LoginPageActivity.getTokenUserId(), "users?");
                new RequestTask().execute(generatedUrl);
                requestType = "users";

            }
        });
    }




}
package ru.sscc.ssd.hpccloud;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private String responseFromServer;
    private String tokenUserId;
    private String requestType;
    private SharedPreferences sharedPreferences;

    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            try {
                responseFromServer = ServerAccess.getResponse(urls[0]);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseFromServer;

        }
        @Override
        protected void onPostExecute(String response) {

            if (requestType.equals("directory"))
            {
                saveDirsResponse(responseFromServer);
                Intent intentDirectory = new Intent(UserProfileMainPageActivity.this, DocumentsPageActivity.class);
                startActivity(intentDirectory);
            }
            else if (requestType.equals("jobs"))
            {
                saveJobsResponse(responseFromServer);
                Intent intentJobs = new Intent(UserProfileMainPageActivity.this, JobsPageActivity.class);
                startActivity(intentJobs);
            }
            else if(requestType.equals("projects"))
            {
                saveAppsResponse(responseFromServer);
                Intent intentApplications = new Intent(UserProfileMainPageActivity.this, ApplicationsPageActivity.class);
                startActivity(intentApplications);
            }
            else if(requestType.equals("users"))
            {
                saveUserInfoResponse(responseFromServer);
                Intent intentUserInfo = new Intent(UserProfileMainPageActivity.this, UserInfoPageActivity.class);
                startActivity(intentUserInfo);
            }
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_main_page);

        tokenUserId = loadToken();

        Button buttonApplications = (Button) findViewById(R.id.buttonProjects);
        buttonApplications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(tokenUserId, "projects?");
                new RequestTask().execute(generatedUrl);
                requestType = "projects";
            }
        });
        Button buttonJobs = (Button)findViewById(R.id.buttonJobs);
        buttonJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(tokenUserId, "jobs?");
                new RequestTask().execute(generatedUrl);
                requestType = "jobs";

            }
        });
        Button buttonDocuments = (Button)findViewById(R.id.buttonDocuments);
        buttonDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(tokenUserId, "fs/?");
                new RequestTask().execute(generatedUrl);
                requestType = "directory";

            }
        });

        Button buttonUserInfo = (Button)findViewById(R.id.buttonUserInfo);
        buttonUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = ServerAccess.generateURL(tokenUserId, "users?");
                new RequestTask().execute(generatedUrl);
                requestType = "users";

            }
        });
        Button buttonSignOut = (Button)findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToken(null);
                saveAppsResponse(null);
                saveJobsResponse(null);
                saveDirsResponse(null);
                saveUserInfoResponse(null);
                UserInfoPageActivity.setKeys(null);
                UserInfoPageActivity.setValues(null);
                JobsPageActivity.setKeys(null);
                JobsPageActivity.setValues(null);
                ApplicationsPageActivity.setKeys(null);
                ApplicationsPageActivity.setValues(null);
                Intent loginIntent = new Intent(UserProfileMainPageActivity.this, LoginPageActivity.class);
                startActivity(loginIntent);
            }
        });
    }
    public void saveToken(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tokenUserId", value);
        editor.commit();
    }

    public String loadToken() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("tokenUserId", null);
    }
    public void saveAppsResponse(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("appsResponse", value);
        editor.commit();


    }

    public String loadAppsResponse() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("appsResponse", null);
    }


    public void saveJobsResponse(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jobsResponse", value);
        editor.commit();


    }
    public String loadJobsResponse() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("jobsResponse", null);
    }

    public void saveDirsResponse(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dirsResponse", value);
        editor.commit();


    }
    public String loadDirsResponse() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("dirsResponse", null);
    }
    public void saveUserInfoResponse(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userInfoResponse", value);
        editor.commit();


    }

    public String loadUserInfoResponse() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("userInfoResponse", null);
    }

}
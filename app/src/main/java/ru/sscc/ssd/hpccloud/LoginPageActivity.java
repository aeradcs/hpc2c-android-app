package ru.sscc.ssd.hpccloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.getResponseFromServer;


public class LoginPageActivity extends AppCompatActivity {
    private EditText userLogin;
    private EditText userPassword;
    private String tokenUserId = null;
    private String auth;
    SharedPreferences sharedPreferences;
    //    private static String SHARED_REFS = "sharedRefs";
    Intent intentUserProfileMainPage;

    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try{
                responseFromServer = getResponseFromServer(urls[0], auth);
                tokenUserId = responseFromServer;
                //saveToken(responseFromServer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(String response) {
            intentUserProfileMainPage = new Intent(LoginPageActivity.this, UserProfileMainPageActivity.class);
            startActivity(intentUserProfileMainPage);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        userLogin = findViewById(R.id.editTextUserLogin);
        userPassword = findViewById(R.id.editTextUserPassword);

        Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL generatedUrl = generateURL();
                auth = Base64.getEncoder().encodeToString((userLogin.getText().toString() + ":" + userPassword.getText().toString()).getBytes(StandardCharsets.UTF_8));
                new RequestTask().execute(generatedUrl);
            }

        });

    }

    public void registerPreferences()
    {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginPageActivity.this);

    }
    public void saveToken(String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tokenUserId", value);
        editor.commit();

    }
    public String loadToken(){
        return sharedPreferences.getString("tokenUserId", null);
    }

    public boolean isValid(String token)
    {
        if(token == null)
            return false;
        else return true;
    }
}

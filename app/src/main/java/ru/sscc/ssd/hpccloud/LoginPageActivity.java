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
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import ru.sscc.ssd.hpccloud.utils.JsonParser;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;
import static ru.sscc.ssd.hpccloud.utils.ServerAccess.getResponseFromServer;


public class LoginPageActivity extends AppCompatActivity {
    private EditText userLogin;
    private EditText userPassword;
    private static String tokenUserId = null;
    private String auth;
    //SharedPreferences sharedPreferences;
    Intent intentUserProfileMainPage;


    JsonParser jsonParser;
    public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try{
                responseFromServer = getResponseFromServer(urls[0], auth);
                tokenUserId = jsonParser.getToken(responseFromServer);
                //saveToken(responseFromServer);//
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(String response) {
            //TextView textView = findViewById(R.id.textView5);
            //textView.setText(tokenUserId);
            intentUserProfileMainPage = new Intent(LoginPageActivity.this, UserProfileMainPageActivity.class);
            startActivity(intentUserProfileMainPage);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //loadToken();
        //if(tokenUserId == null) {//страница авторизации

            userLogin = findViewById(R.id.editTextUserLogin);
            userPassword = findViewById(R.id.editTextUserPassword);

            Button buttonSignIn = (Button) findViewById(R.id.buttonSignIn);

            jsonParser = new JsonParser();

            buttonSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    URL generatedUrl = generateURL();
                    auth = Base64.getEncoder().encodeToString((userLogin.getText().toString() + ":" + userPassword.getText().toString()).getBytes(StandardCharsets.UTF_8));
                    new RequestTask().execute(generatedUrl);

                }

            });
        //}
        /*else{//страница профиля пользователя
            intentUserProfileMainPage = new Intent(LoginPageActivity.this, UserProfileMainPageActivity.class);
            startActivity(intentUserProfileMainPage);
        }*/

    }

    public static String getTokenUserId()
    {
        return tokenUserId;
    }
    /*public void saveToken(String value){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(tokenUserId, value);
        editor.commit();


    }
    public String loadToken(){
        sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getString("tokenUserId", null);
    }*/





}

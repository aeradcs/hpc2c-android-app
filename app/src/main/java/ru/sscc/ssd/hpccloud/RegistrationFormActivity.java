package ru.sscc.ssd.hpccloud;

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

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.ServerAccess;

public class RegistrationFormActivity extends AppCompatActivity {
    String tokenUserId;
    JsonParser jsonParser;

    /*public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try{
                responseFromServer = ServerAccess.getAuthorizationResponseFromServer(urls[0], auth);
                tokenUserId = jsonParser.getToken(responseFromServer);
                //saveToken(responseFromServer);//
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return responseFromServer;
        }
        @Override
        protected void onPostExecute(String response) {
            Intent intentUserProfileMainPage = new Intent(RegistrationFormActivity.this, UserProfileMainPageActivity.class);
            startActivity(intentUserProfileMainPage);
            TextView textView = findViewById(R.id.res);
            textView.setText("успех");
        }
    }*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_registration_form);

        Button buttonRegister = (Button) findViewById(R.id.buttonRgister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //запрос
                /*URL generatedUrl = ServerAccess.generateAuthorizationURL();
                auth = Base64.getEncoder().encodeToString((userLogin.getText().toString() + ":" + userPassword.getText().toString()).getBytes(StandardCharsets.UTF_8));
                new LoginPageActivity.RequestTask().execute(generatedUrl);*/

            }
        });
    }
}

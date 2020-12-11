package ru.sscc.ssd.hpccloud;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.ServerAccess;

public class RegistrationFormActivity extends AppCompatActivity {
    String tokenUserId;
    JsonParser jsonParser;
    Map<String, String> params;
    String auth;
    /*public class RequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try{
                responseFromServer = ServerAccess.getARResponseFromServer(urls[0], auth);
                //tokenUserId = jsonParser.getToken(responseFromServer);
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

        EditText login = findViewById(R.id.editTextLogin);
        EditText password = findViewById(R.id.editTextPassword);
        EditText retypedPassword = findViewById(R.id.editTextRetypePassword);
        EditText firstName = findViewById(R.id.editTextFirstName);
        EditText lastName = findViewById(R.id.editTextLastName);
        EditText email = findViewById(R.id.editTextEmail);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText accessCode = findViewById(R.id.editTextAccessCode);


        Button buttonRegister = (Button) findViewById(R.id.buttonRgister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*params.add("lvl", 0);
                params.add(firstName.toString());
                params.add(lastName.toString());
                params.add(email.toString());
                params.add(phone.toString());
                params.add(accessCode.toString());

                URL generatedUrl = ServerAccess.generateRegistrationURL(params);
                //auth = Base64.getEncoder().encodeToString((userLogin.getText().toString() + ":" + userPassword.getText().toString()).getBytes(StandardCharsets.UTF_8));
                new RegistrationFormActivity().RequestTask().execute(generatedUrl);*/

            }
        });
    }
}

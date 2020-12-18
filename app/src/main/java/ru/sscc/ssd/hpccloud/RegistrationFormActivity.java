package ru.sscc.ssd.hpccloud;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.ServerAccess;

public class RegistrationFormActivity extends AppCompatActivity {
    private String tokenUserId;
    private JsonParser jsonParser;
    private ArrayList<String> params;
    private String auth;
    private SharedPreferences sharedPreferences;
    private Intent intentUserProfileMainPage;

    private boolean isAlreadyRegistred = false;

    public class AuthorizationRequestTask extends AsyncTask<URL, Void, String>
    {

        @Override
        protected String doInBackground(URL... urls) {
            String responseFromServer = null;
            try {
                responseFromServer = ServerAccess.getAuthorizationResponseFromServer(urls[0], auth);
                System.out.println("\n------------------------AUTH RESPONSE--------------------\n" + responseFromServer);

                saveToken(jsonParser.getToken(responseFromServer));
                saveResponse(responseFromServer);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return responseFromServer;
        }

        @Override
        protected void onPostExecute(String response) {
            tokenUserId = getSharedPreferences("systemPrefs", MODE_PRIVATE).getString("tokenUserId", null);
            if(tokenUserId != null) {
                intentUserProfileMainPage = new Intent(RegistrationFormActivity.this, UserProfileMainPageActivity.class);
                startActivity(intentUserProfileMainPage);
            }
        }
    }
    public class RegistrationRequestTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            int registrationResponseCode = 0;
            try {
                registrationResponseCode = ServerAccess.getRegistrationResponse(urls[0], params, auth);

                System.out.println("\n------------------------REG RESPONSE--------------------\n" + registrationResponseCode);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            if(registrationResponseCode == 409){
                isAlreadyRegistred = true;
                return "already registred";
            }
            else if(registrationResponseCode == 201)
            {
                return "user was created";
            }
            else return "some error";
        }
        @Override
        protected void onPostExecute(String response) {
            if(isAlreadyRegistred == true)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "The login name is already taken", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 150);
                toast.show();
                params = null;
            }
            else {
                URL authURL = ServerAccess.generateAuthorizationURL();
                new AuthorizationRequestTask().execute(authURL);
            }
        }
    }
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





        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(retypedPassword.getText().toString()))
                {
                    URL generatedUrl = ServerAccess.generateRegistrationURL();
                    params = new ArrayList<>();
                    //params.add("0");
                    params.add(firstName.getText().toString());
                    params.add(lastName.getText().toString());
                    params.add(email.getText().toString());
                    //params.add(phone.toString());
                    params.add(accessCode.getText().toString());

                    auth = Base64.getEncoder().encodeToString((login.getText().toString() + ":" + password.getText().toString()).getBytes(StandardCharsets.UTF_8));

                    new RegistrationRequestTask().execute(generatedUrl);

                }
                else
                {
                    //error
                }



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

    public void saveResponse(String value) {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("authorizationResponse", value);
        editor.commit();


    }

    public String loadResponse() {
        sharedPreferences = getSharedPreferences("systemPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("authorizationResponse", null);
    }
}

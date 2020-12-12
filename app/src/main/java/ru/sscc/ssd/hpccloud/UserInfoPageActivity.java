package ru.sscc.ssd.hpccloud;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.UserInfoAdapter;

public class UserInfoPageActivity extends AppCompatActivity {
    private String response;
    private SharedPreferences sharedPreferences;
    JsonParser jsonParser = new JsonParser();
    RecyclerView numberList;
    private static ArrayList<String> keys;
    private static ArrayList<String> values;

    UserInfoAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_page);
        keys = new ArrayList<>();
        values = new ArrayList<>();
        response = loadUserInfoResponse();
        try {
            jsonParser.parseUserInfo(response, keys, values);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        /*System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");//___________________________
        System.out.println(keys.toString() + "\n" + values.toString());*/



        numberList = findViewById(R.id.recycleViewUserInfo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(linearLayoutManager);
        numberList.setHasFixedSize(true);
        adapter = new UserInfoAdapter(keys.size());
        numberList.setAdapter(adapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        keys = null;
        values = null;

    }

    public static ArrayList<String> getKeys()
    {
        return keys;
    }
    public static ArrayList<String> getValues()
    {
        return values;
    }

    public static void setKeys(ArrayList<String> value) {
        UserInfoPageActivity.keys = value;
    }

    public static void setValues(ArrayList<String> value) {
        UserInfoPageActivity.values = value;
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

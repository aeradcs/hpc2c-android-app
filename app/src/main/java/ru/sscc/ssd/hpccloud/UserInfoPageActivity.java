package ru.sscc.ssd.hpccloud;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.UserInfoAdapter;

public class UserInfoPageActivity extends AppCompatActivity {
    private static String response = UserProfileMainPageActivity.getResponseFromServer();
    JsonParser jsonParser = new JsonParser();
    RecyclerView numberList;
    private static LinkedHashMap<String, String> userInfo = new LinkedHashMap<>();

    UserInfoAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_page);
        try {
            userInfo = jsonParser.parseUserInfo(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");//___________________________
        userInfo.forEach((key, value)->{
            System.out.println("KEY   " + key + "   VALUE  " + value + "\n");
        });

        numberList = findViewById(R.id.recycleViewUserInfo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(linearLayoutManager);
        numberList.setHasFixedSize(true);
        adapter = new UserInfoAdapter(userInfo.size());
        numberList.setAdapter(adapter);


    }

    public static LinkedHashMap<String , String> getUserInfo()
    {
        return userInfo;
    }
}

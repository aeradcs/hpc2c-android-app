package ru.sscc.ssd.hpccloud;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import ru.sscc.ssd.hpccloud.utils.ApplicationsAdapter;
import ru.sscc.ssd.hpccloud.utils.JsonParser;


public class ApplicationsPageActivity extends AppCompatActivity {
    private String response;
    private SharedPreferences sharedPreferences;
    private JsonParser jsonParser = new JsonParser();
    private RecyclerView numberList;
    private static ArrayList<String> keys;
    private static ArrayList<String> values;
    private ApplicationsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications_page);
        keys = new ArrayList<>();
        values = new ArrayList<>();

        response = loadAppsResponse();
        try {
            jsonParser.parseApplications(response, keys, values);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        numberList = findViewById(R.id.recycleViewApplications);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);
        numberList.setHasFixedSize(true);
        int size;
        if(keys.size() != 0) {
             size = values.size() / keys.size();
        }
        else size = 0;
        adapter = new ApplicationsAdapter(size);
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
        ApplicationsPageActivity.keys = value;
    }

    public static void setValues(ArrayList<String> value) {
        ApplicationsPageActivity.values = value;
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



}

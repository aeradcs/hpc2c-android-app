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
import ru.sscc.ssd.hpccloud.utils.JobsAdapter;
import ru.sscc.ssd.hpccloud.utils.JsonParser;

public class JobsPageActivity extends AppCompatActivity {
    private String response;
    private SharedPreferences sharedPreferences;
    JsonParser jsonParser = new JsonParser();
    RecyclerView numberList;
    private static ArrayList<String> keys = new ArrayList<>();
    private static ArrayList<String> values = new ArrayList<>();
    //private int size;

    JobsAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_page);
        response = loadJobsResponse();
        try {
            jsonParser.parseJobs(response, keys, values);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        numberList = findViewById(R.id.recycleViewJobs);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);
        numberList.setHasFixedSize(true);
        int size = values.size()/keys.size();
        adapter = new JobsAdapter(size);
        numberList.setAdapter(adapter);

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
        JobsPageActivity.keys = value;
    }

    public static void setValues(ArrayList<String> value) {
        JobsPageActivity.values = value;
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


}

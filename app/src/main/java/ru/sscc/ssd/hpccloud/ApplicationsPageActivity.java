package ru.sscc.ssd.hpccloud;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import ru.sscc.ssd.hpccloud.utils.ApplicationsAdapter;
import ru.sscc.ssd.hpccloud.utils.JsonParser;
import ru.sscc.ssd.hpccloud.utils.UserInfoAdapter;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;

public class ApplicationsPageActivity extends AppCompatActivity {
    private static String response = UserProfileMainPageActivity.getResponseFromServer();
    JsonParser jsonParser = new JsonParser();
    RecyclerView numberList;
    private static ArrayList<String> keys = new ArrayList<>();
    private static ArrayList<String> values = new ArrayList<>();
    //private int size;

    ApplicationsAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications_page);

        try {
            jsonParser.parseApplications(response, keys, values/*, size*/);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        numberList = findViewById(R.id.recycleViewApplications);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);
        numberList.setHasFixedSize(true);
        int size = values.size()/keys.size();
        adapter = new ApplicationsAdapter(size);
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

}

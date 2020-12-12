package ru.sscc.ssd.hpccloud;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.util.ArrayList;

import ru.sscc.ssd.hpccloud.utils.ApplicationsAdapter;
import ru.sscc.ssd.hpccloud.utils.DirectoryAdapter;
import ru.sscc.ssd.hpccloud.utils.JsonParser;

public class DocumentsPageActivity extends AppCompatActivity {
    private static String response;
    JsonParser jsonParser = new JsonParser();
    RecyclerView numberList;
    private static ArrayList<String> keys = new ArrayList<>();
    private static ArrayList<String> values = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_page);
        ExpandableListView dirs = (ExpandableListView)findViewById(R.id.expandableListDirectory);
        dirs.setAdapter(new DirectoryAdapter(this));

        /*dirs.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(DocumentsPageActivity.this, "успех", Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        /*try {
            jsonParser.parseApplications(response, keys, values*//*, size*//*);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        numberList = findViewById(R.id.recycleViewApplications);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        numberList.setLayoutManager(layoutManager);
        numberList.setHasFixedSize(true);
        int size = values.size()/keys.size();
        adapter = new ApplicationsAdapter(size);
        numberList.setAdapter(adapter);*/
    }
}

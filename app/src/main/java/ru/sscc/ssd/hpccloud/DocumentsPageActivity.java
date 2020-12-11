package ru.sscc.ssd.hpccloud;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.sscc.ssd.hpccloud.utils.DirectoryAdapter;

public class DocumentsPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_page);
        ExpandableListView dirs = (ExpandableListView)findViewById(R.id.expandableListDirectory);
        dirs.setAdapter(new DirectoryAdapter(this));

        dirs.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(DocumentsPageActivity.this, "успех", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}

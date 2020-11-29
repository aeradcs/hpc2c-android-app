package ru.sscc.ssd.hpccloud;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static ru.sscc.ssd.hpccloud.utils.ServerAccess.generateURL;

public class ApplicationsPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applications_page);


        TextView textView = findViewById(R.id.textView1);
        textView.setText("успех");
    }
}

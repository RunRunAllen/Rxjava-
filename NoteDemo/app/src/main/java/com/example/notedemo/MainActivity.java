package com.example.notedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv)
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View tv = findViewById(R.id.tv);
        InjectUtil.injectView(this);
        tv.setText("haha");


    }
}

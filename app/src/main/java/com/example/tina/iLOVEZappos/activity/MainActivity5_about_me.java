package com.example.tina.iLOVEZappos.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.tina.iLOVEZappos.R;


public class MainActivity5_about_me extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setTitle("About Me");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}

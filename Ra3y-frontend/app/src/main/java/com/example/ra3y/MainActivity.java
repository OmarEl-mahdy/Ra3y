package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
private static int Splash_time=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // """ HERE """
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nextpage=new Intent(MainActivity.this,petsitterorowner.class);
                startActivity(nextpage);
                finish();
            }
        },Splash_time);
    }
}
package com.example.ra3y;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class services extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        ActionBar actionBar = getActionBar();
        Button request_button = (Button)findViewById(R.id.request);
        Button vaccination_button = (Button)findViewById(R.id.vaccination);

        request_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, MainActivity2.class));
                //String temp =buttons[i].toString();

                Intent intent =new Intent(services.this, request.class);
                //intent.putExtra("next_activity", temp);
                startActivity(intent);
            }
        });

        vaccination_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, MainActivity2.class));
                //String temp =buttons[i].toString();

                Intent intent =new Intent(services.this, vaccine.class);
                //intent.putExtra("next_activity", temp);
                startActivity(intent);
            }
        });

    }}
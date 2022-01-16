package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class petsitterorowner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepetter);

        ImageButton petowner = (ImageButton) findViewById(R.id.imageButton2);

        ImageButton petsitter = (ImageButton) findViewById(R.id.imageButton4);

        petsitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(petsitterorowner.this,login.class);
                startActivity(nextpage);
                finish();            }
        });
        petowner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(petsitterorowner.this,login.class);
                startActivity(nextpage);
                finish();            }
        });



    }}
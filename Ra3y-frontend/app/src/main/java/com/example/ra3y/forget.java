package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class forget extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

//        Button loginButton = (Button) findViewById(R.id.);
//        loginButton.setTextColor(Color.WHITE);
//        loginButton.setBackgroundColor(Color.rgb(59,89,153));
//
        Button forgotbutton = (Button) findViewById(R.id.button2);
        forgotbutton.setTextColor(Color.WHITE);
        forgotbutton.setBackgroundColor(Color.rgb(59,89,153));
        forgotbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(forget.this,verificode.class);
                startActivity(nextpage);
                finish();            }
        });
//
//        newAccountButton.setTextColor(Color.WHITE);
//
//        EditText pass = (EditText) findViewById(R.id.pass);
//        EditText email = (EditText) findViewById(R.id.email);
//
//        pass.setHintTextColor(Color.rgb(150,150,150));
//        email.setHintTextColor(Color.rgb(150,150,150));
////
//        pass.setTextColor(Color.rgb(60,91,155));
//        email.setTextColor(Color.rgb(60,91,155));

    }}
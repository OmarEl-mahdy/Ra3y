package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setBackgroundColor(Color.rgb(59,89,153));

        Button newAccountButton = (Button) findViewById(R.id.newAccountButton);
        newAccountButton.setTextColor(Color.WHITE);
        newAccountButton.setBackgroundColor(Color.rgb(59,89,153));

        newAccountButton.setTextColor(Color.WHITE);

        EditText pass = (EditText) findViewById(R.id.pass);
        EditText email = (EditText) findViewById(R.id.email);

        pass.setHintTextColor(Color.rgb(150,150,150));
        email.setHintTextColor(Color.rgb(150,150,150));
//
        pass.setTextColor(Color.rgb(60,91,155));
        email.setTextColor(Color.rgb(60,91,155));

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(login.this,register.class);
                startActivity(nextpage);
                finish();            }
        });

    }}
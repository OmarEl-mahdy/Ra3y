package com.example.ra3y;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class petregister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpet);
        Spinner dropdown = findViewById(R.id.spinner1);
        Button btn =(Button)findViewById(R.id.submitpetReg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(petregister.this, yourpets.class);
                startActivity(intent);
            }

        });
        String[] items = new String[]{"Dog", "Cat", "Hamster","Turtle","Bird","Rabbit","Fish"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(petregister.this, services.class);
//        intent.putExtra("gh","jkjh");
        startActivity(intent);
    }
}
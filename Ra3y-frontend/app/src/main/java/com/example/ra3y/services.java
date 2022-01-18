package com.example.ra3y;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    }



    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logout:
                logout();
                return true;
            case R.id.aboutus:
                startActivity(new Intent(getApplicationContext(),aboutus.class));
            case R.id.backButton:
                Toast.makeText(this, "Back Button Pressed",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        Toast.makeText(this, "You have logged out",Toast.LENGTH_SHORT).show();
    }

}
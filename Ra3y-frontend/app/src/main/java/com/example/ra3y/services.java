package com.example.ra3y;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class services extends AppCompatActivity {
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services);
        ActionBar actionBar = getActionBar();
        Button request_button = (Button)findViewById(R.id.request);


        Bundle bundle = getIntent().getExtras();
        String data;
        data = bundle.getString("Owner Data");
        Log.d("Data", data.toString());
        try {
            jsonObject = new JSONObject(data);
            Log.d("name", jsonObject.getString("fname"));
            Toast.makeText(this, "Welcome Back "+ jsonObject.getString("fname")+"!",Toast.LENGTH_SHORT).show();

        }catch (JSONException e){
            //TODO
            Log.d("Error", e.toString());
            e.printStackTrace();
        }


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


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbar);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.vacc:
                        Intent intent1 =new Intent(services.this, vaccine.class);
                        startActivity(intent1);
                        break;
                    case R.id.serv:
                        Toast.makeText(getApplicationContext(), "Services page coming soon!",Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }

                return false;
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
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,login.class));
        Toast.makeText(this, "You have logged out",Toast.LENGTH_SHORT).show();
    }

}
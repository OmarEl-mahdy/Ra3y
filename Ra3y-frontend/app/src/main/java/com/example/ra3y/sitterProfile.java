package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class sitterProfile extends AppCompatActivity {
    Button launch_btn;
    Button notifi;
    JSONObject jsonObject;
    TextView name;
    TextView email;
    TextView phonenumber;
    TextView price;
    TextView yoe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_profile);
        Bundle bundle = getIntent().getExtras();
        String data;
        data = bundle.getString("Sitter Data");
        Log.d("Data", data.toString());
        try {
            jsonObject = new JSONObject(data);
            Log.d("name", jsonObject.getString("fname"));
            Toast.makeText(this, "Welcome Back "+ jsonObject.getString("fname")+"!",Toast.LENGTH_SHORT).show();
            name = (TextView) findViewById(R.id.textView8);
            email = (TextView) findViewById(R.id.textView4);
            phonenumber = (TextView) findViewById(R.id.textView2);
            price = (TextView)findViewById(R.id.price);
            yoe = (TextView)findViewById(R.id.textView6);
            name.setText(jsonObject.getString("fname"));
            email.setText(jsonObject.getString("email"));
            phonenumber.setText(jsonObject.getString("phonenumber"));
            yoe.setText(jsonObject.getString("yearsOfExperience"));
            price.setText(jsonObject.getString("priceperhour"));

        }catch (JSONException e){
            //TODO
            Log.d("Error", e.toString());
            e.printStackTrace();
        }


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbar);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.notification:
                        Intent intent1 = new Intent(sitterProfile.this, sitterRequest.class);
                        startActivity(intent1);
                        break;
                    case R.id.location:
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:47.4925,19.0513"));
                        Intent chooser =Intent.createChooser(intent, "Launch Maps");
                        startActivity(chooser);
                        break;
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
package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class ownerprofile extends AppCompatActivity {
    JSONObject jsonObject;
    String data;
    TextView name;
    TextView email;
    TextView phonenumber;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerprofile);

        Bundle bundle = getIntent().getExtras();

        data = bundle.getString("Owner Data");
        Log.d("Data", data.toString());
        try {
            jsonObject = new JSONObject(data);
            name = (TextView) findViewById(R.id.textView8);
            email = (TextView) findViewById(R.id.textView4);
            phonenumber = (TextView) findViewById(R.id.textView2);
            name.setText(jsonObject.getString("fname"));
            email.setText(jsonObject.getString("email"));
            phonenumber.setText(jsonObject.getString("phonenumber"));

        }catch (JSONException e){
            //TODO
            Log.d("Error", e.toString());
            e.printStackTrace();
        }

//
//        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbarop);
//
//        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.vacc:
//                        Intent intent1 =new Intent(getApplicationContext(), vaccine.class);
//                        intent1.putExtra("Owner Data",data);
//                        startActivity(intent1);
//                        break;
//                    case R.id.serv:
//                        Intent intent2 =new Intent(getApplicationContext(), shopsServices.class);
//                        intent2.putExtra("Owner Data",data);
//                        startActivity(intent2);
//                    default:
//                        break;
//                }
//                return false;
//            }
//        });
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
                startActivity(new Intent(getApplicationContext(),services.class));
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
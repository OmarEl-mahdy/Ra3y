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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.Locale;

public class shopsServices extends AppCompatActivity {
    Button launch_btn;
    JSONObject jsonObject;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_services);
        Bundle bundle = getIntent().getExtras();
        data = bundle.getString("Owner Data");
        Log.d("Data", data.toString());
        launch_btn=(Button) findViewById(R.id.launch);
        launch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("geo:30.116880922372093, 31.246166286669357"));
//                Intent chooser =Intent.createChooser(intent, "Launch Maps");
//                startActivity(chooser);
                String uri = String.format(Locale.ENGLISH, "https://goo.gl/maps/mbbB1VrampBbf8Z88", 30.10826825973349, 31.27054220339578);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

//        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbar);
//        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.profile:
//                        Intent intent =new Intent(getApplicationContext(), ownerprofile.class);
//                        intent.putExtra("Owner Data",data);
//                        startActivity(intent);
//                        break;
//                    case R.id.vacc:
//                        Intent intent1 =new Intent(getApplicationContext(), vaccine.class);
//                        intent1.putExtra("Owner Data",data);
//                        startActivity(intent1);
//                        break;
//                    default:
//                        break;
//                }
//
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
               // Toast.makeText(this, "Back Button Pressed",Toast.LENGTH_SHORT).show();
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
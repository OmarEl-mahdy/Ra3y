package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class sitterRequest extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_request);
        com.google.android.material.floatingactionbutton.FloatingActionButton FAB1;
        Button Accept1=(Button) findViewById(R.id.Accept1);
        Accept1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(sitterRequest.this,acceptor.class);
                startActivity(intent);
            }

        });
        FAB1 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB1);
        FAB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Mohamed Ashraf is requesting a sitter for his dog", Toast.LENGTH_LONG).show();
                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
            }
        });

        com.google.android.material.floatingactionbutton.FloatingActionButton FAB2;
        FAB2 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB2);
        FAB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Mahdy is requesting a sitter for his cat ", Toast.LENGTH_SHORT).show();
                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
            }
        });

        com.google.android.material.floatingactionbutton.FloatingActionButton FAB3;
        FAB3 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB3);
        FAB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Elewa is requesting a sitter for his dog ", Toast.LENGTH_SHORT).show();
                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
            }
        });


        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbarreq);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.sprofile:
                        onBackPressed();
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
                startActivity(new Intent(getApplicationContext(),sitterProfile.class));
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
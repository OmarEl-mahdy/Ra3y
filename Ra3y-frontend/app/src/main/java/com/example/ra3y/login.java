package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class login extends AppCompatActivity {

    FirebaseAuth fAuth;
    ProgressBar pg;
    Bundle bundle;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView forget = (TextView) findViewById(R.id.forget);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setBackgroundColor(Color.rgb(59, 89, 153));

        Button newAccountButton = (Button) findViewById(R.id.registerButton);
        newAccountButton.setTextColor(Color.WHITE);
        newAccountButton.setBackgroundColor(Color.rgb(59, 89, 153));

        newAccountButton.setTextColor(Color.WHITE);

        EditText passView = (EditText) findViewById(R.id.pass);
        EditText emailView = (EditText) findViewById(R.id.email);

        passView.setHintTextColor(Color.rgb(150, 150, 150));
        emailView.setHintTextColor(Color.rgb(150, 150, 150));
//
        passView.setTextColor(Color.rgb(60, 91, 155));
        emailView.setTextColor(Color.rgb(60, 91, 155));


        pg = (ProgressBar) findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        bundle = getIntent().getExtras();
        data = bundle.getString("User Type");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = emailView.getText().toString().trim();
                String pass = passView.getText().toString().trim();

                if (TextUtils.isEmpty(Email)) {
                    emailView.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    passView.setError("Password is required");
                    return;
                }
                pg.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Welcome Back!", Toast.LENGTH_SHORT).show();
                            Log.d("Back from register", data);

                            if (data.equals("petowner")) {
                                startActivity(new Intent(getApplicationContext(), services.class));
                                finish();
                            }
                            else{
                                startActivity(new Intent(getApplicationContext(), sitterProfile.class));
                                finish();
                            }

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pg.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent nextpage=new Intent(login.this,register.class);
                Log.d("Sending to register",data);
                nextpage.putExtra("User Type", data);
                startActivity(nextpage);
                finish();            }
        });




        forget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(login.this,forget.class);
                startActivity(nextpage);
                finish();            }
        });

    };

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.starters_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
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

    @Override
    protected void onResume() {
        super.onResume();
        pg.setVisibility(View.GONE);
    }
}
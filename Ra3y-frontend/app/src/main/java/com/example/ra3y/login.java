package com.example.ra3y;

import static com.android.volley.Request.Method.HEAD;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//<<<<<<< HEAD
import android.widget.TextView;
//=======
import android.widget.ProgressBar;
import android.widget.Toast;
//>>>>>>> 2fb3da20f1559b1cfb6bfcbeea04f262fbd30b23

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class login extends AppCompatActivity {

    FirebaseAuth fAuth;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        TextView forget= findViewById(R.id.forget);
        Button loginButton = findViewById(R.id.loginButton);
=======
        TextView forget = (TextView) findViewById(R.id.forget);
        Button loginButton = (Button) findViewById(R.id.loginButton);
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
        loginButton.setTextColor(Color.WHITE);
        loginButton.setBackgroundColor(Color.rgb(59, 89, 153));

        Button newAccountButton = findViewById(R.id.registerButton);
        newAccountButton.setTextColor(Color.WHITE);
        newAccountButton.setBackgroundColor(Color.rgb(59, 89, 153));

        newAccountButton.setTextColor(Color.WHITE);

<<<<<<< HEAD
        EditText pass = findViewById(R.id.pass);
        EditText email = findViewById(R.id.email);
=======
        EditText passView = (EditText) findViewById(R.id.pass);
        EditText emailView = (EditText) findViewById(R.id.email);
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5

        passView.setHintTextColor(Color.rgb(150, 150, 150));
        emailView.setHintTextColor(Color.rgb(150, 150, 150));
//
        passView.setTextColor(Color.rgb(60, 91, 155));
        emailView.setTextColor(Color.rgb(60, 91, 155));


        pg = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

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

<<<<<<< HEAD
                pg.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
=======
                fAuth.signInWithEmailAndPassword(Email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Welcome Back!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),services.class));
                            finish();

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
                startActivity(nextpage);
                finish();            }
        });


<<<<<<< HEAD
//<<<<<<< HEAD
=======


>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5
        forget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(login.this,forget.class);
                startActivity(nextpage);
                finish();            }
        });

<<<<<<< HEAD
//=======
//>>>>>>> 2fb3da20f1559b1cfb6bfcbeea04f262fbd30b23
    }}
=======
    };
    @Override
    protected void onResume() {
        super.onResume();
        pg.setVisibility(View.GONE);
    }
}





>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5

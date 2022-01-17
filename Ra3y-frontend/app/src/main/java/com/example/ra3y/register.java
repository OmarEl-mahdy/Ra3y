package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class register extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar pg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Button submitbutton = (Button) findViewById(R.id.button6);
//        submitbutton.setTextColor(Color.WHITE);
//        submitbutton.setBackgroundColor(Color.rgb(59,89,153));

<<<<<<< HEAD
        Button newAccountButton = (Button) findViewById(R.id.button6);
        newAccountButton.setTextColor(Color.WHITE);
        newAccountButton.setBackgroundColor(Color.rgb(59,89,153));
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(register.this,login.class);
                startActivity(nextpage);
                finish();            }
        });
//
//        newAccountButton.setTextColor(Color.WHITE);
//
//        EditText pass = (EditText) findViewById(R.id.pass);
//        EditText email = (EditText) findViewById(R.id.email);
//
//        pass.setHintTextColor(Color.rgb(150,150,150));
//        email.setHintTextColor(Color.rgb(150,150,150));
////
//        pass.setTextColor(Color.rgb(60,91,155));
//        email.setTextColor(Color.rgb(60,91,155));
=======
        EditText FirstName = (EditText) findViewById(R.id.FirstName);
        EditText LastName = (EditText) findViewById(R.id.LastName);
        EditText Email = (EditText) findViewById(R.id.email);
        EditText phoneNumber = (EditText) findViewById(R.id.phonenumber);
        EditText password = (EditText) findViewById(R.id.pass);
        EditText Confirmpassword = (EditText) findViewById(R.id.confirmpass);
>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5


        pg = (ProgressBar) findViewById(R.id.progressBar2);
        Button signupbutton = (Button) findViewById(R.id.submitReg);
        signupbutton.setTextColor(Color.WHITE);
        signupbutton.setBackgroundColor(Color.rgb(59,89,153));

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        
        signupbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String fname = FirstName.getText().toString().trim() ;
                String Lname = LastName.getText().toString().trim();
                String E = Email.getText().toString().trim();
                String PNumber = phoneNumber.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String cmPass = Confirmpassword.getText().toString().trim();
                // check if no email or password are provided
                if(TextUtils.isEmpty(E)){
                    Email.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("Password is Required");
                    return;
                }

                if (pass.length() < 8){
                    password.setError("Password Must be at least 8 characters long");
                    return;
                }

                if( cmPass.length() <  pass.length() || !cmPass.equals(pass) || TextUtils.isEmpty(cmPass)){
                    Confirmpassword.setError("Passwords Do not match!");
                    return;
                }

                pg.setVisibility(View.VISIBLE);

                // create user

                auth.createUserWithEmailAndPassword(E,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),login.class));
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




    }
    @Override
    protected void onResume() {
        super.onResume();
        pg.setVisibility(View.GONE);
    }
}
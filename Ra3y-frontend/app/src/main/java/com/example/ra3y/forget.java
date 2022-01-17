package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget extends AppCompatActivity {

    private FirebaseAuth auth;
    EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgetpass);

        emailInput = (EditText) findViewById(R.id.editTextTextEmailAddress2);

        auth = FirebaseAuth.getInstance();

        Button forgotbutton = (Button) findViewById(R.id.button2);
        forgotbutton.setTextColor(Color.WHITE);
        forgotbutton.setBackgroundColor(Color.rgb(59, 89, 153));
        forgotbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String email = emailInput.getText().toString().trim();
                Log.d("Debug", email);
                if (TextUtils.isEmpty(email)) {
                    emailInput.setError("Enter your registered email id");
                    return;
                }

                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forget.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), login.class));
                            finish();
                        } else {
                            Toast.makeText(forget.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
//
//
    }

}
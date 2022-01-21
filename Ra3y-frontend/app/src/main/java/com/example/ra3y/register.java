package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class register extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar pg;
    final private String myIP = "192.168.1.10";

    final private String EmulatorIP = "10.0.2.2";
    final private String DeviceIP = EmulatorIP;

    final private  String portNo = "3000";
    EditText FirstName;
    EditText LastName ;
    EditText Email ;
    EditText phoneNumber;
    EditText password ;
    String fname, Lname,E,PNumber,pass;
    API_handler api_handler;
    Bundle bundle;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Button submitbutton = (Button) findViewById(R.id.button6);
//        submitbutton.setTextColor(Color.WHITE);
//        submitbutton.setBackgroundColor(Color.rgb(59,89,153));

//<<<<<<< HEAD
        Button newAccountButton = (Button) findViewById(R.id.submitReg);
        newAccountButton.setTextColor(Color.WHITE);
        newAccountButton.setBackgroundColor(Color.rgb(59,89,153));
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(register.this,login.class);
                startActivity(nextpage);
                finish();


            }
        });

        EditText FirstName = (EditText) findViewById(R.id.FirstName);
        EditText LastName = (EditText) findViewById(R.id.LastName);
        EditText Email = (EditText) findViewById(R.id.email);
        EditText phoneNumber = (EditText) findViewById(R.id.phonenumber);
        EditText password = (EditText) findViewById(R.id.pass);
        EditText Confirmpassword = (EditText) findViewById(R.id.confirmpass);
//>>>>>>> ad924db1dcbb072bf8689c32df664fe4e8f190f5


        pg = (ProgressBar) findViewById(R.id.progressBar2);
        Button signupbutton = (Button) findViewById(R.id.submitReg);
        signupbutton.setTextColor(Color.WHITE);
        signupbutton.setBackgroundColor(Color.rgb(59,89,153));

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        signupbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                fname = FirstName.getText().toString().trim() ;
                Lname = LastName.getText().toString().trim();
                E = Email.getText().toString().trim();
                PNumber = phoneNumber.getText().toString().trim();
                pass = password.getText().toString().trim();
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
                            bundle = getIntent().getExtras();
                             data = bundle.getString("User Type");
                            api_handler = new API_handler();
                            api_handler.execute(data);



                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                });



            }

        });
    }


    //####################################################
    private class API_handler extends AsyncTask<String,String,String>{
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"User Created", Toast.LENGTH_SHORT).show();
            pg.setVisibility(View.GONE);
            Intent backToLogin = new Intent(getApplicationContext(),login.class);
            backToLogin.putExtra("User Type",data);
            FirebaseAuth.getInstance().signOut();

            startActivity(backToLogin);

            finish();
        }
        //###############################

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        //#####################################

        @Override
        protected String doInBackground(String... params) {

                Log.d("Inside Background ELSE", params[0]);
                registerOwner();

            return null;

        }
        //####################################################
        private  void registerOwner(){
            //"""This method is responsible for setting adding user of type owner to the database"""
            Log.d("HEREregister owner", "HEREEEE");
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/registerOwner");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.connect();

                // JSONObject to send
                JSONObject dataTosend = new JSONObject();
                Log.d("fname",fname);
                Log.d("lname",Lname);
                Log.d("email",E);
                Log.d("pass",pass);
                Log.d("phonenumber",PNumber);

                dataTosend.put("uid",FirebaseAuth.getInstance().getCurrentUser().getUid());
                dataTosend.put("fname",fname);
                dataTosend.put("lname",Lname);
                dataTosend.put("email",E);
                dataTosend.put("pass",pass);
                dataTosend.put("phonenumber",PNumber);

                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(dataTosend.toString());
                os.flush();
                os.close();
                Log.d("Debug data to send", dataTosend.toString());
                Log.d("Status", String.valueOf(connection.getResponseCode()));
                Log.d("ResponseBody", connection.getResponseMessage());


                connection.disconnect();

            } catch (MalformedURLException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            }catch (org.json.JSONException e){

            }
            finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    //##################################################
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.starters_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    //###################################################
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutus:
                startActivity(new Intent(getApplicationContext(),aboutus.class));
            case R.id.backButton:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //####################################################
    @Override
    protected void onResume() {
        super.onResume();
        pg.setVisibility(View.GONE);
    }



}
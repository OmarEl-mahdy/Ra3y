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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonObject;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity {
    FirebaseAuth fAuth;
    ProgressBar pg;
    Bundle bundle;
    String data;
    API_handler api_handler;
    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";
    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    String Email,pass;
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

        try {
            bundle = getIntent().getExtras();
            data = bundle.getString("User Type");
        }catch (java.lang.RuntimeException e){
            Log.e("Error",e.toString());
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Email = emailView.getText().toString().trim();
                 pass = passView.getText().toString().trim();

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
                            api_handler = new API_handler();
                            api_handler.execute(Email, FirebaseAuth.getInstance().getCurrentUser().getUid());

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
                Intent nextpage=new Intent(login.this,petsitterorowner.class);
                startActivity(nextpage);
                finish();
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(login.this,forget.class);
                startActivity(nextpage);
                finish();
            }
        });

    };
    //    ###############################################
   private class API_handler extends AsyncTask<String,String,String>{
        private JSONObject returnedDataSitter = new JSONObject();
        private JSONObject returnedDataOwner = new JSONObject();
        Boolean isSitter = true;
        Boolean isOwner = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
//########################################
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            Log.d("After Execution",isSitter.toString());
            Log.d("After Execution",isOwner.toString());

            if(isOwner == true){
                Log.d("Type", "Owner");
                Intent OwnerIntet = new Intent(getApplicationContext(), services.class);
                OwnerIntet.putExtra("Owner Data", returnedDataOwner.toString());
                startActivity(OwnerIntet);
                finish();
                pg.setVisibility(View.GONE);
            }else{
                Log.d("Type", "Sitter");
                Intent sitterIntet = new Intent(getApplicationContext(), sitterProfile.class);
                sitterIntet.putExtra("Sitter Data", returnedDataSitter.toString());
                startActivity(sitterIntet);
                finish();
                pg.setVisibility(View.GONE);
            }
        }
//##################################
        @Override
        protected String doInBackground(String... params) {

            getSitterInfo(params[1]);
            getOwnerInfo(params[1]);

            return null;
        }
//###################################
        private void getSitterInfo(String uid){
            //  """ This method is responsible for retrieving data for Sitter Info """
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Boolean NonEmptySMS = false;

            //   while(!NonEmptySMS) {
            try {
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/getSitterInfo");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.connect();

                //add Data to request body
                JSONObject dataTosend = new JSONObject();
                dataTosend.put("uid",uid);
                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(dataTosend.toString());
                os.flush();

                // get Data

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    Log.d("Full JSON file", line);
                }

                JSONArray JArray = new JSONArray(buffer.toString());
                returnedDataSitter = JArray.getJSONObject(0);
                Log.d("Status", returnedDataSitter.toString());

                // did not execute catch block then array not empty then the user is a sitter and not owner
                isOwner = false;
                os.close();
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            }
            catch (JSONException e) {
                Log.d("JSONException",e.toString());
                isSitter = false;

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

        //#######################################
        private void getOwnerInfo(String uid){
            //  """ This method is responsible for retrieving data for Owner Info """
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Boolean NonEmptySMS = false;

            //   while(!NonEmptySMS) {
            try {
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/getOwnerInfo");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.connect();

                //add Data to request body
                JSONObject dataTosend = new JSONObject();
                dataTosend.put("uid",uid);
                DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(dataTosend.toString());
                os.flush();

                // get Data

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    Log.d("Full JSON file", line);
                }

                JSONArray JArray = new JSONArray(buffer.toString());
                returnedDataOwner = JArray.getJSONObject(0);
                Log.d("Status", returnedDataOwner.toString());
                os.close();
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            }
            catch (JSONException e) {
                Log.d("JSONException",e.toString());
                isOwner = false;
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

    @Override
    public void onBackPressed() {

    }
}
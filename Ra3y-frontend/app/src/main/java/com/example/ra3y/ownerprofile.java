package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ownerprofile extends AppCompatActivity {
    JSONObject jsonObject;
    String data;
    TextView name;
    TextView email;
    TextView phonenumber;


    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerprofile);
//
//        Bundle bundle = getIntent().getExtras();
        name = (TextView) findViewById(R.id.textView8);
        email = (TextView) findViewById(R.id.textView4);
        phonenumber = (TextView) findViewById(R.id.textView2);
        API_Handler api_handler = new API_Handler();
        api_handler.execute(FirebaseAuth.getInstance().getCurrentUser().getUid());

//        try {
//            data = bundle.getString("Owner Data");
//        }catch (NullPointerException e){
//            //TODO
//        }
    }
    private class API_Handler extends AsyncTask<String,String,String>{
        private JSONObject returnedDataOwner = new JSONObject();
        @Override
        protected String doInBackground(String... strings) {
            getOwnerInfo(strings[0]);
            return null;
        }
        //###############################
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                name.setText(returnedDataOwner.getString("fname"));
                email.setText(returnedDataOwner.getString("email"));
                phonenumber.setText(returnedDataOwner.getString("phonenumber"));

            }catch (JSONException e){
                //TODO
                Log.d("Error", e.toString());
                e.printStackTrace();
            }
        }

        //###############################
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
        //#################################
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
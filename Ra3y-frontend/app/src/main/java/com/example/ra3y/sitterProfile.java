package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
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

public class sitterProfile extends AppCompatActivity {
    Button launch_btn;
    Button notifi;
    JSONObject jsonObject;
    TextView name;
    TextView email;
    TextView phonenumber;
    TextView price;
    String LONG ="NULL",LAT="NULL",address="NULL";
    TextView yoe,add;
    String data;
    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = myIP;
    final private  String portNo = "3000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_profile);
        Bundle bundle = getIntent().getExtras();


        name = (TextView) findViewById(R.id.textView8);
        email = (TextView) findViewById(R.id.textView4);
        phonenumber = (TextView) findViewById(R.id.textView2);
        price = (TextView) findViewById(R.id.price);
        yoe = (TextView) findViewById(R.id.textView6);
        add = (TextView)findViewById(R.id.textView7);

        try {

        }catch (NullPointerException e){
            //TODO
        }

//        try{
//            LONG = bundle.getString("long");
//            LAT = bundle.getString("lat");
//            address = bundle.getString("addr");
//
//            Log.d("LONG", LONG.toString());
//            Log.d("LAT", LAT.toString());
//            Toast.makeText(getApplicationContext(), "Location Added Sucessfully", Toast.LENGTH_SHORT).show();
//
//        }catch (NullPointerException e){
//            // TODO
//        }
        API_handler api_handler = new API_handler();
        api_handler.execute(FirebaseAuth.getInstance().getCurrentUser().getUid());

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbar);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.notification:
                        Intent intent1 = new Intent(sitterProfile.this, sitterRequest.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.location:
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setData(Uri.parse("geo:47.4925,19.0513"));
//                        Intent chooser =Intent.createChooser(intent, "Launch Maps");
//                        startActivity(chooser);
                        Intent nextpage=new Intent(sitterProfile.this,Mapss.class);
                        nextpage.putExtra("activity", "sitterProfile");
                        startActivity(nextpage);
                        finish();
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

    }
    private class API_handler extends AsyncTask<String,String,String>{
        private JSONObject returnedDataSitter = new JSONObject();
        @Override
        protected String doInBackground(String... strings) {
            getSitterInfo(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                try{
                    data  = getIntent().getExtras().getString("fname");
                    Toast.makeText(getApplicationContext(), "Welcome Back " + returnedDataSitter.getString("fname") + "!", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    //TODO
                }
                name.setText(returnedDataSitter.getString("fname"));
                email.setText(returnedDataSitter.getString("email"));
                phonenumber.setText(returnedDataSitter.getString("phonenumber"));
                yoe.setText(returnedDataSitter.getString("yearsOfExperience"));
                String priceperH = returnedDataSitter.getString("priceperhour");
                add.setText(returnedDataSitter.getString("address"));
                if (priceperH.equals("0")) {
                    price.setText("FREE");
                } else {
                    price.setText(priceperH);
                }

            } catch (JSONException e) {
                //TODO
                Log.d("Error", e.toString());
                e.printStackTrace();
            }
        }

        //###############################################
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
        //################################

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
                Toast.makeText(this, "Back Button Pressed",Toast.LENGTH_SHORT).show();
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
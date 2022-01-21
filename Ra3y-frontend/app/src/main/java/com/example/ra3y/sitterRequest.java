package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
import java.util.ArrayList;

public class  sitterRequest extends AppCompatActivity {

    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    JSONObject returnedData;
    JSONObject returnedDataOwner;
    String ownerID;
    String additionalInformation;
    API_handler api_handler ;
    TextView n,e,p,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_request);

        n = findViewById(R.id.rName);
        e = findViewById(R.id.remail);
        p = findViewById(R.id.rphone);
        i = findViewById(R.id.rinfo);
        api_handler = new API_handler();
        api_handler.execute(FirebaseAuth.getInstance().getCurrentUser().getUid());
//        com.google.android.material.floatingactionbutton.FloatingActionButton FAB1;
//        Button Accept1=(Button) findViewById(R.id.Accept1);
//        Accept1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(sitterRequest.this,acceptor.class);
//                startActivity(intent);
//            }
//
//        });
//        FAB1 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB1);
//        FAB1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Mohamed Ashraf is requesting a sitter for his dog", Toast.LENGTH_LONG).show();
//                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
//            }
//        });
//
//        com.google.android.material.floatingactionbutton.FloatingActionButton FAB2;
//        FAB2 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB2);
//        FAB2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Mahdy is requesting a sitter for his cat ", Toast.LENGTH_SHORT).show();
//                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
//            }
//        });
//
//        com.google.android.material.floatingactionbutton.FloatingActionButton FAB3;
//        FAB3 = (com.google.android.material.floatingactionbutton.FloatingActionButton) findViewById(R.id.FAB3);
//        FAB3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Elewa is requesting a sitter for his dog ", Toast.LENGTH_SHORT).show();
//                Intent fIntent = new Intent(view.getContext(), FloatingActionButton.class);
//            }
//        });


    }

    private class API_handler extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {


//                Log.d("J", returnedDataOwner.getString("fname").toString());
//                Log.d("J", returnedDataOwner.getString("email").toString());
//                Log.d("J", returnedDataOwner.getString("phonenumber").toString());
//                Log.d("J", additionalInformation);
                n.setText("Name: " +returnedDataOwner.getString("fname").toString());
                e.setText("Email: "+returnedDataOwner.getString("email").toString());
                p.setText("Phone Number:" + returnedDataOwner.getString("phonenumber").toString());
                i.setText("Additional Info" +additionalInformation);
            }catch (JSONException e){}





        }
        //#########################
        @Override
        protected String doInBackground(String... strings) {
            getMakeRequest(strings[0]);
            getOwnerInfo(ownerID);
            return null;
        }
        //###################################
        private void getMakeRequest(String uid){
            //  """ This method is responsible for retrieving data for Sitter Info """
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Boolean NonEmptySMS = false;

            //   while(!NonEmptySMS) {
            try {
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/getmakeRequest");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.connect();

                //add Data to request body
                JSONObject dataTosend = new JSONObject();
                dataTosend.put("sitteruid",uid);
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
                JSONObject JReader = new JSONObject(buffer.toString());
                JSONArray JArray = JReader.getJSONArray("result");
                returnedData =JArray.getJSONObject(0);
                ownerID = returnedData.getString("owneruid");
                additionalInformation = returnedData.getString("info");



                os.close();
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            }
            catch (JSONException e) {
                Log.d("JException",e.toString());

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

                JSONArray jsonArray = new JSONArray(buffer.toString());

                Log.d("JARRAY", jsonArray.toString());
                returnedDataOwner = jsonArray.getJSONObject(0);

                os.close();
                reader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
            }
            catch (JSONException e) {
                Log.d("JException",e.toString());
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

        //############################

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
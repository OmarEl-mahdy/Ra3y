package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

public class MainActivity extends AppCompatActivity {

    API_handler api_handler;
    FirebaseUser user;
    FirebaseAuth fAuth;

    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
private static int Splash_time=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // """ HERE """
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                fAuth = FirebaseAuth.getInstance();
                user = fAuth.getCurrentUser();
                if (user !=null) {
                    //user is still signed in ? redirect to the correct services
                    api_handler = new API_handler();
                    api_handler.execute(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
                else {
                    Intent nextpage=new Intent(MainActivity.this,login.class);
                    startActivity(nextpage);

                    finish();
                }
            }
        },Splash_time);
    }


    private class API_handler extends AsyncTask<String,String,String>{

        private JSONObject returnedDataSitter = new JSONObject();
        private JSONObject returnedDataOwner = new JSONObject();
        Boolean isSitter = true;
        Boolean isOwner = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(isOwner == true){
                Log.d("Type", "Owner");
                Intent OwnerIntet = new Intent(getApplicationContext(), services.class);
                OwnerIntet.putExtra("Owner Data", returnedDataOwner.toString());
                startActivity(OwnerIntet);

            }else{
                Log.d("Type", "Sitter");
                Intent sitterIntet = new Intent(getApplicationContext(), sitterProfile.class);
                sitterIntet.putExtra("Sitter Data", returnedDataSitter.toString());
                startActivity(sitterIntet);
            }
            finish();


        }
        //#########################
        @Override
        protected String doInBackground(String... strings) {

            getSitterInfo(strings[0]);
            getOwnerInfo(strings[0]);

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

        //############################
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
        //#############################
    }
}
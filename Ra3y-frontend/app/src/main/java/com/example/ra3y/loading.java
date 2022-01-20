package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class loading extends AppCompatActivity {
    //API_handler api_handler;
    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    String latitude, longitude,addr;
    Bundle bundle;
    ProgressBar pg;
    API_handler api_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        bundle = getIntent().getExtras();
        latitude = bundle.getString("latitude");
        longitude = bundle.getString("longitude");
        addr = bundle.getString("address");
        Log.d("lat",latitude);
        Log.d("long",longitude);

        Log.d("addr",addr);
<<<<<<< Updated upstream
        pg = (ProgressBar)findViewById(R.id.progressBar3);
=======
         pg = (ProgressBar)findViewById(R.id.progressBar3);
>>>>>>> Stashed changes
        pg.setVisibility(View.VISIBLE);
        api_handler = new API_handler();
        api_handler.execute(FirebaseAuth.getInstance().getCurrentUser().getUid());


    }
    private class API_handler extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            addLocation(strings[0]);
            addAddr(strings[0]);

            return null;
        }
        //#########################3
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"All set!", Toast.LENGTH_SHORT).show();
            pg.setVisibility(View.GONE);

            startActivity(new Intent(getApplicationContext(), sitterProfile.class));

        }

        //###########################3
        private void addLocation(String UID){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                Log.d("HERE","HERE INSIDE ADD Location");

                URL url = new URL("http://"+DeviceIP+":"+portNo+"/addSitterLocation");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                // data to send
                String dataTosend = " {\"uid\": \""+UID+"\",\"latitudes\":  "+Double.valueOf(latitude)+", \"longitudes\": "+Double.valueOf(longitude)+"}";

                OutputStream os = connection.getOutputStream();
                os.write(dataTosend.getBytes("UTF-8"));
                os.close();
                Log.d("Data to send", dataTosend);
                Log.d("Data to send", connection.getResponseMessage());
                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("HERE", " Inside Exception Malformed ");
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception ");
                e.printStackTrace();
                Log.d("HERE",e.toString());
            }
//            catch (org.json.JSONException e){
//
//            }
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


        };
        //################################
        private  void addAddr(String UID){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String ID;

            try {

                Log.d("HERE","HERE INSIDE ADD ADD");
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/addaddress");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                String dataTosend = " {\"uid\": \""+UID+"\",\"addr\":  \""+addr+"\"}";


                OutputStream os = connection.getOutputStream();
                os.write(dataTosend.getBytes("UTF-8"));
                os.close();
                Log.d("YARABBB",dataTosend);
                Log.d("Data to send", connection.getResponseMessage());
                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Log.d("HERE", "doInBackground: Inside Exception "+e);
                e.printStackTrace();
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

        };
    }
}
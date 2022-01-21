package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class sittersList extends ListActivity {

    private int debug = 0; // set 1 to activate
    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";

    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    API_handler api_handler ;
    private JSONArray returnedDataSitter = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle= getIntent().getExtras();
        String data = bundle.getString("Sitters");
        String Info = bundle.getString("Info");

        ArrayList<String> uid = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> email = new ArrayList<String>();
        ArrayList<String> priceperhour = new ArrayList<String>();
        ArrayList<String> yearsOfExperience = new ArrayList<String>();

        try{
            JSONArray jsonArray = new JSONArray(data);
            for(int i =0 ; i < jsonArray.length();++i){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                uid.add(jsonObject.getString("UID"));
                names.add(jsonObject.getString("fname"));
                email.add(jsonObject.getString("email"));
                priceperhour.add(jsonObject.getString("priceperhour"));
                yearsOfExperience.add(jsonObject.getString("yearsOfExperience"));

            }
        }catch (org.json.JSONException e){
            Log.e("String", e.toString());

        }


        setContentView(R.layout.activity_sitters_list);
        // getting the genre list view
        ListView sittersList = (ListView) findViewById(android.R.id.list);
        sittersList.setClickable(false);
        //  add data to rows
        CustomSittersList gl = new CustomSittersList(this, names, email, priceperhour,yearsOfExperience);
        sittersList.setAdapter(gl);

        sittersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                String unique_id = uid.get(position);
                Log.i("UID", unique_id);
                api_handler = new API_handler();
                api_handler.execute(unique_id,Info);

            }


        });
    }
    private class API_handler extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            makeRequest(strings[0],strings[1]);
            return null;
        }
        //#########################3
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),"Request Sent!", Toast.LENGTH_SHORT).show();
        }

        //###########################3
        private void makeRequest(String sitterUID, String info){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            String ownerUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            try {
                Log.d("HERE","HERE INSIDE make Request ");

                URL url = new URL("http://"+DeviceIP+":"+portNo+"/makeRequest");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("Accept","application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                // data to send
                String dataTosend = " {\"sitteruid\": \""+sitterUID+"\",\"owneruid\":  \""+ownerUID+"\", \"info\": \""+info+"\"}";

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
    }
}

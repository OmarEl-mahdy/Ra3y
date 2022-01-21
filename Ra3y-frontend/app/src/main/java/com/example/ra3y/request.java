package com.example.ra3y;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
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
import java.util.Calendar;

public class request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int FROM = 0;
    private static final int TO = 1;
    private JSONArray returnedDataSitter = new JSONArray();
    final private String EmulatorIP = "10.0.2.2";
    final private String myIP = "192.168.1.10";
    final private String DeviceIP = EmulatorIP;
    final private  String portNo = "3000";
    String Location,
            Duration,
            text;
    Button search,
            duration_from,
            duration_to,btn_launch;
    EditText location,
            duration,
            info;
    TextView starting_date,
            ending_date;
    private DatePicker datePicker;
    private int year_from,
            month_from,
            day_from;
    private int year_to,
            month_to,
            day_to;

    Spinner spinner_city;
    Spinner spinner_zone;
    int mode = FROM;
    ArrayAdapter<CharSequence> city_spinner_adapter;
    ArrayAdapter<CharSequence> zone_spinner_adapter;
    String[] zones;
    Bundle bundle;
    String data;
    String longitude, latitude;
API_handler api_handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        BottomNavigationView bnv = (BottomNavigationView) findViewById(R.id.navbar);

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:
                        Intent intent =new Intent(request.this, ownerprofile.class);
                        startActivity(intent);
                        break;
                    case R.id.vacc:
                        Intent intent1 =new Intent(request.this, vaccine.class);
                        startActivity(intent1);
                        break;
                    case R.id.serv:
                        startActivity(new Intent(getApplicationContext(),shopsServices.class));
                    default:
                        break;
                }

                return false;
            }
        });

        try{

            bundle = getIntent().getExtras();
            longitude = bundle.getString("longitude");
            latitude = bundle.getString("latitude");
            Log.d("long",longitude);
            Log.d("lat",latitude);

        }catch (RuntimeException e){

        }

        //location = (EditText) findViewById(R.id.location);
        //duration= (EditText) findViewById(R.id.tv_duration);
        info = (EditText) findViewById(R.id.info);
        search = (Button) findViewById(R.id.button);
        btn_launch = findViewById(R.id.launch);

        duration_from = (Button) findViewById(R.id.btn_from);
        duration_to = (Button) findViewById(R.id.btn_to);
        duration_from.setTextColor(Color.WHITE);
        duration_from.setBackgroundColor(Color.rgb(59,89,153));
        duration_to.setTextColor(Color.WHITE);
        duration_to.setBackgroundColor(Color.rgb(59,89,153));
        btn_launch.setTextColor(Color.WHITE);
        btn_launch.setBackgroundColor(Color.rgb(59,89,153));

        search.setTextColor(Color.WHITE);
        search.setBackgroundColor(Color.rgb(59,89,153));
        starting_date = (TextView) findViewById(R.id.tv_from);
        ending_date = (TextView) findViewById(R.id.tv_to);
        btn_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextpage=new Intent(request.this,Mapss.class);
                nextpage.putExtra("activity", "request");
                startActivity(nextpage);

                finish();
//                intent.setData(Uri.parse("geo:47.4925,19.0513"));
//                Intent chooser = Intent.createChooser(intent, "Launch Maps");
//                startActivity(chooser);
            }
        });
        spinner_city = (Spinner) findViewById(R.id.cities_spinner);
        city_spinner_adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array,
                android.R.layout.simple_spinner_item);

        city_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(city_spinner_adapter);

        spinner_zone = (Spinner) findViewById(R.id.zones_spinner);

        Calendar calendar = Calendar.getInstance();
        year_from = calendar.get(Calendar.YEAR);
        month_from = calendar.get(Calendar.MONTH);
        day_from = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year_from, month_from +1, day_from);

        spinner_city.setOnItemSelectedListener(this);
        duration_from.setOnClickListener(view -> {
            mode = FROM;
            setDate(view);
            //Log.v("Hey", "hey");
        });
        duration_to.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mode = TO;
                setDate(view);
            }
        });
//        search.setOnClickListener(view -> {
//            Location=location.getText().toString();
//            Duration=duration.getText().toString();
//            text=info.getText().toString();
//            showToast(Location);
//            showToast(Duration);
//            showToast(text);
//        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Location=location.getText().toString();
//                Duration=duration.getText().toString();
//                text=info.getText().toString();
                //  showToast(Location);
                //showToast(Duration);
                //showToast(text);
                api_handler = new API_handler();
                api_handler.execute();

            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        switch(mode){
            case FROM:
                Toast.makeText(getApplicationContext(),
                        R.string.pick_starting_date,
                        Toast.LENGTH_LONG).show();
            case TO:
                Toast.makeText(getApplicationContext(),
                        R.string.pick_ending_date,
                        Toast.LENGTH_LONG).show();
        }

    }

    //##########################################################
    private class API_handler extends AsyncTask<String,String,String> {

        Boolean isSitter = true;
        Boolean isOwner = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent nextpage = new Intent(getApplicationContext(), sittersList.class);
            nextpage.putExtra("Sitters", returnedDataSitter.toString());
            nextpage.putExtra("Info", info.getText().toString().trim());
            startActivity(nextpage);
            finish();



        }
        //#########################
        @Override
        protected String doInBackground(String... strings) {
            getSitterInfo();
            return null;
        }
        //###################################
        private void getSitterInfo(){
            //  """ This method is responsible for retrieving data for Sitter Info """
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            Boolean NonEmptySMS = false;

            //   while(!NonEmptySMS) {
            try {
                Log.d("HERE","inside get ALL");
                URL url = new URL("http://"+DeviceIP+":"+portNo+"/getallsitterusers");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Full JSON file", line);
                }
                JSONObject JReader = new JSONObject(buffer.toString());
                JSONArray JArray = JReader.getJSONArray("result");
                returnedDataSitter = JArray;
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

        //############################

    }


    //############################################################

    @Override
    protected Dialog onCreateDialog(int id) {

        // TODO Auto-generated method stub
        if (id == 999) {
            switch (mode) {
                case FROM:
                    return new DatePickerDialog(this,
                            myDateListener,
                            year_from,
                            month_from,
                            day_from
                    );
                case TO:
                    return new DatePickerDialog(this,
                            myDateListener,
                            year_to,
                            month_to,
                            day_to
                    );
            }
        }
        return null;
    }

    private final DatePickerDialog.OnDateSetListener myDateListener = (arg0, arg1, arg2, arg3) -> {
        // TODO Auto-generated method stub
        // arg1 = year
        // arg2 = month
        // arg3 = day
        showDate(arg1, arg2+1, arg3);
    };


    private void showToast (String text){
        Toast.makeText(request.this,text, Toast.LENGTH_SHORT).show();
    }

    private void showDate(int year, int month, int day) {

        switch (mode){
            case FROM:
                starting_date.setText(new StringBuilder()
                        .append(day)
                        .append("/")
                        .append(month)
                        .append("/")
                        .append(year)
                );
            case TO:
                ending_date.setText(new StringBuilder()
                        .append(day)
                        .append("/")
                        .append(month)
                        .append("/")
                        .append(year)
                );
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getApplicationContext(),
                Integer.toString(position),
                Toast.LENGTH_LONG)
                .show();

        switch(position){
            case 0:
                zones = getResources().getStringArray(R.array.cairo_districts);
                zone_spinner_adapter = ArrayAdapter.createFromResource(this,
                        R.array.cairo_districts,
                        android.R.layout.simple_spinner_item);
                spinner_zone.setAdapter(zone_spinner_adapter);
                break;
            default:
                zones = getResources().getStringArray(R.array.cities_array);
                zone_spinner_adapter = ArrayAdapter.createFromResource(this,
                        R.array.cities_array,
                        android.R.layout.simple_spinner_item);
                spinner_zone.setAdapter(zone_spinner_adapter);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
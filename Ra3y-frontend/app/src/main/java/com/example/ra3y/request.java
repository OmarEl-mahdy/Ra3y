package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int FROM = 0;
    private static final int TO = 1;

    String Location, Duration, text;
    Button btn_search, btn_duration_from, btn_duration_to, btn_launch;
    EditText et_location, et_duration, et_info;
    TextView starting_date, ending_date;
    private DatePicker datePicker;
    private int year_from, month_from, day_from;
    private int year_to, month_to, day_to;

    Spinner spinner_city;
    Spinner spinner_zone;
    int mode = FROM;
    ArrayAdapter<CharSequence> city_spinner_adapter;
    ArrayAdapter<CharSequence> zone_spinner_adapter;
    String[] zones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        et_info = findViewById(R.id.info);

        btn_search = findViewById(R.id.button);
        btn_duration_from = findViewById(R.id.btn_from);
        btn_duration_to = findViewById(R.id.btn_to);
        btn_launch = findViewById(R.id.launch);

        starting_date = findViewById(R.id.tv_from);
        ending_date = findViewById(R.id.tv_to);

        spinner_city = findViewById(R.id.cities_spinner);
        spinner_city.setAdapter(city_spinner_adapter);

        btn_search.setTextColor(Color.WHITE);
        btn_search.setBackgroundColor(Color.rgb(59,89,153));

        btn_duration_from.setTextColor(Color.WHITE);
        btn_duration_from.setBackgroundColor(Color.rgb(59,89,153));

        btn_duration_to.setTextColor(Color.WHITE);
        btn_duration_to.setBackgroundColor(Color.rgb(59,89,153));

        btn_launch.setTextColor(Color.WHITE);
        btn_launch.setBackgroundColor(Color.rgb(59,89,153));

        btn_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:47.4925,19.0513"));
                Intent chooser = Intent.createChooser(intent, "Launch Maps");
                startActivity(chooser);
            }
        });

        city_spinner_adapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array,
                android.R.layout.simple_spinner_item);

        city_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_zone = findViewById(R.id.zones_spinner);

        Calendar calendar = Calendar.getInstance();
        year_from = calendar.get(Calendar.YEAR);
        month_from = calendar.get(Calendar.MONTH);
        day_from = calendar.get(Calendar.DAY_OF_MONTH);

        showDate(year_from, month_from +1, day_from);

        spinner_city.setOnItemSelectedListener(this);

        btn_duration_from.setOnClickListener(view -> {
            mode = FROM;
            setDate(view);
            //Log.v("Hey", "hey");
        });

        btn_duration_to.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mode = TO;
                setDate(view);
            }
        });

        btn_search.setOnClickListener(view -> {
            //Location = et_location.getText().toString();
            Duration = et_duration.getText().toString();
            text = et_info.getText().toString();
            showToast(Location);
            showToast(Duration);
            showToast(text);
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
    public void onItemSelected(AdapterView<?> adapterView,
                               View view, int position, long l) {
        Toast.makeText(getApplicationContext(),
                Integer.toString(position),
                Toast.LENGTH_LONG)
                .show();

//        switch(position){
//            case 0:
//                zones = getResources().getStringArray(R.array.cairo_districts);
//                zone_spinner_adapter = ArrayAdapter.createFromResource(this,
//                        R.array.cairo_districts,
//                        android.R.layout.simple_spinner_item);
//                spinner_zone.setAdapter(zone_spinner_adapter);
//                break;
//            default:
//                zones = getResources().getStringArray(R.array.cities_array);
//                zone_spinner_adapter = ArrayAdapter.createFromResource(this,
//                        R.array.cities_array,
//                        android.R.layout.simple_spinner_item);
//                spinner_zone.setAdapter(zone_spinner_adapter);
//                break;
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {}
}
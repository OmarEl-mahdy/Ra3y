package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sittersList extends ListActivity {

    private int debug = 0; // set 1 to activate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] Names = getResources().getStringArray(R.array.sittersNames);
        String[] locations = getResources().getStringArray(R.array.sittersLocations);
        String[] pricePerHour = getResources().getStringArray(R.array.pricePerHour);
        setContentView(R.layout.activity_sitters_list);

        // getting the genre list view
        ListView sittersList = (ListView) findViewById(android.R.id.list);
        sittersList.setClickable(false);

        //  add data to rows
        CustomSittersList gl = new CustomSittersList(this, Names, pricePerHour, locations);
        sittersList.setAdapter(gl);


    }
}

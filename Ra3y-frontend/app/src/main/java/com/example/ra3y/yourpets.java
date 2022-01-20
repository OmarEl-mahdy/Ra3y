package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.TypedArray;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class yourpets extends AppCompatActivity  {
    String [] pet_name;
    TypedArray images;
    List<model> ml;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yourpets);




        ml=new ArrayList<model>();
        pet_name=getResources().getStringArray(R.array.pet_list);
        images=getResources().obtainTypedArray(R.array.pic_list);
        Button yourpetS = (Button) findViewById(R.id.petaddeing);
        yourpetS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(yourpets.this, petregister.class);
                startActivity(intent);
                finish();

            }

        });
        yourpetS.setTextColor(Color.WHITE);
        yourpetS.setBackgroundColor(Color.rgb(59, 89, 153));
        for (int i=0; i<pet_name.length; i++){
            model item= new model (pet_name[i],images.getResourceId(i,-1));
            ml.add(item);
        }
        listView= (ListView)  findViewById(R.id.list);
        CustomAdapter adapter=new CustomAdapter (this, ml);
        listView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(yourpets.this, services.class);
        startActivity(intent);
        finish();
    }
}
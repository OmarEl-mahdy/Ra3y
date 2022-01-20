package com.example.ra3y;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomSittersList extends ArrayAdapter {
    private ArrayList<String> names;
    private ArrayList<String>email  ;
    private ArrayList<String> priceperhour;
    private ArrayList<String> yearsOfExperience;
    private  Activity context;
    public CustomSittersList(Activity context, ArrayList<String> names, ArrayList<String>email, ArrayList<String> priceperhour,ArrayList<String>yearsOfExperience){
        super(context,R.layout.sitters_row_item, names);
        this.context = context;
        this.names = names;
        this.email = email;
        this.priceperhour = priceperhour;
        this.yearsOfExperience = yearsOfExperience;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.sitters_row_item, null, true);
        TextView sitterName = (TextView) row.findViewById(R.id.SitterName);
        TextView SitterEmail = (TextView) row.findViewById(R.id.SitterEmail);
        TextView sitterPrice = (TextView) row.findViewById(R.id.SitterPricePerHour);
        TextView sitterYearsOfExperience = (TextView)row.findViewById(R.id.Sitteryearsofexperience);
        sitterName.setText("Name: " + names.get(position));
        SitterEmail.setText("Email:" +email.get(position));
        if(priceperhour.get(position).equals("0")){
            sitterPrice.setText("Price Per Hour: FREE");
        }
        else{
            sitterPrice.setText("Price Per Hour: " +priceperhour.get(position));
        }
        sitterYearsOfExperience.setText("Years of Experience"+ yearsOfExperience.get(position));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1);
        params.setMargins(100,100,100,100);
        row.setLayoutParams(params);
        return  row;
    }
}
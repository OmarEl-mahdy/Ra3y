package com.example.ra3y;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomSittersList extends ArrayAdapter {
    private String [] Names;
    private String [] pricePerHour;
    private String [] location;
    private  Activity context;
    public CustomSittersList(Activity context, String [] Names, String [] pricePerHour, String [] location){
        super(context,R.layout.sitters_row_item, Names);
        this.context = context;
        this.Names = Names;
        this.pricePerHour = pricePerHour;
        this.location =location;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.sitters_row_item, null, true);
        TextView sitterName = (TextView) row.findViewById(R.id.SitterName);
        TextView sitterLocation = (TextView) row.findViewById(R.id.SitterLocation);
        TextView sitterPrice = (TextView) row.findViewById(R.id.SitterPricePerHour);

        sitterName.setText(Names[position]);
        sitterLocation.setText(location[position]);
        sitterPrice.setText(pricePerHour[position]);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1);
        params.setMargins(100,100,100,100);
        row.setLayoutParams(params);
        return  row;
    }
}
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

public class CustomRequestersList extends ArrayAdapter {
    private ArrayList<String> names;
    private ArrayList<String>email  ;
    private ArrayList<String> info;
    private ArrayList<String> phonenumber;
    private  Activity context;
    public CustomRequestersList(Activity context, ArrayList<String> names, ArrayList<String>email, ArrayList<String> info,ArrayList<String>phonenumber){
        super(context,R.layout.sitters_row_item, names);
        this.context = context;
        this.names = names;
        this.email = email;
        this.info = info;
        this.phonenumber = phonenumber;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.sitters_row_item, null, true);
        TextView reqName = (TextView) row.findViewById(R.id.rName);
        TextView reqEmail = (TextView) row.findViewById(R.id.remail);
        TextView reqInfo = (TextView) row.findViewById(R.id.rinfo);
        TextView reqPhone = (TextView)row.findViewById(R.id.rphone);
        reqName.setText("Name: " + names.get(position));
        reqEmail.setText("Email:" +email.get(position));

        reqInfo.setText("Info"+ info.get(position));
        reqPhone.setText("Phone Number:"+ phonenumber.get(position));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT,1);
        params.setMargins(100,100,100,100);
        row.setLayoutParams(params);
        return  row;
    }
}
package com.example.ra3y;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends BaseAdapter{
    Context context;
    List <model> ml;
    CustomAdapter(Context context, List<model> ml){
        this.context=context;
        this.ml=ml;
    }
    @Override
    public int getCount(){
        return ml.size();
    }

    @Override
    public Object getItem(int i) {
        return ml.get(i);
    }

    @Override
    public long getItemId(int i) {
        return ml.indexOf(getItem(i));
    }
    private class ViewHolder{
        ImageView imageView;
        TextView name_text;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view==null){
            view=inflater.inflate(R.layout.list,null);
            holder= new ViewHolder();
            holder.name_text=(TextView) view.findViewById(R.id.name);
            holder.imageView=(ImageView) view.findViewById(R.id.image);

            model row =ml.get(i);
            holder.imageView.setImageResource(row.getPics());
            holder.name_text.setText(row.getName());
            view.setTag(holder);

        }else{
            holder=(ViewHolder) view.getTag();
        }
        return view;
    }
}
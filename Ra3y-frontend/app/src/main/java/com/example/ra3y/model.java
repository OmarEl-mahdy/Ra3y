package com.example.ra3y;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class model {
    private String name;
    private int pics;

    public model (String name, int pics){
        this.name=name;
        this.pics=pics;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getPics(){
        return pics;
    }

    public void setPics(int pics){
        this.pics=pics;
    }
}
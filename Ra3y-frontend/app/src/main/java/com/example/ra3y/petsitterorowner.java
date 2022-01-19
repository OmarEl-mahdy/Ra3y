package com.example.ra3y;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class petsitterorowner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepetter);

        ImageButton petowner = (ImageButton) findViewById(R.id.imageButton4);

        ImageButton petsitter = (ImageButton) findViewById(R.id.imageButton2);

        TextView psitter = (TextView) findViewById(R.id.psitter);
        TextView powner = (TextView) findViewById(R.id.powner);

        psitter.setTextColor(Color.rgb(59, 89, 153));
        powner.setTextColor(Color.rgb(59, 89, 153));
        petsitter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(petsitterorowner.this,registerSitter.class);
                nextpage.putExtra("User Type", "petsitter");
                startActivity(nextpage);
                finish();            }
        });
        petowner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextpage=new Intent(petsitterorowner.this,register.class);
                nextpage.putExtra("User Type", "petowner");
                startActivity(nextpage);
                finish();            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.starters_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aboutus:
                startActivity(new Intent(getApplicationContext(),aboutus.class));
            case R.id.backButton:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
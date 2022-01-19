package com.example.ra3y;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Element adsElement = new Element();
        Element VersionElement = new Element();
        VersionElement.setTitle("Version 1.2");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("Ra3y is an application " +
                        "that allows users to search for keepers for their pets during a trip or a situation where the user cannot be accompanied by his/her pet.\n\n" +
                        "Developers Info\n\n" +
                        "Salma Elsayed\n" +
                        "Contact: Salma_elsayed@aucegypt.edu\n\n" +
                        "Mohamed Ashraf Taha\n" +
                        "Contact: mohammedashraf@aucegypt.edu\n\n" +
                        "Omar Elewa\n" +
                        "Contact: elewa97@aucegypt.edu\n\n" +
                        "Omar Mahdy\n" +
                        "Contact: omarmahdy122@aucegypt.edu\n\n")
                .setImage(R.drawable.ra3yyy)
                .addGroup("Connect With us")
                .addEmail("ra3y@gmail.com")
                .addFacebook("sample")
                .addTwitter("sample")
                .addGitHub("sample")
                .addInstagram("Sample")
                .addPlayStore("sample")
                .create();
        setContentView(aboutPage);

    }
}
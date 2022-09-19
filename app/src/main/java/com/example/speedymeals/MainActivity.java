package com.example.speedymeals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.speedymeals.database.DBFiller;
import com.example.speedymeals.database.DBHelper;
import com.example.speedymeals.database.DBManager;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dbManager.open();

    }
}
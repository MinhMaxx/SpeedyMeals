package com.example.speedymeals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.speedymeals.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(MainActivity.this);
    }
}
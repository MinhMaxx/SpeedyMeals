package com.example.speedymeals;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.speedymeals.views.fragment_cart;
import com.example.speedymeals.views.fragment_home;
import com.example.speedymeals.views.fragment_profile;
import com.example.speedymeals.views.fragment_restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.speedymeals.database.DBFiller;
import com.example.speedymeals.database.DBHelper;
import com.example.speedymeals.database.DBManager;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        dbManager.open();
        actionBar = getSupportActionBar();
        FragmentManager fm = getSupportFragmentManager();
        fragment_cart cFragment = new fragment_cart();
        fragment_home hFragment = new fragment_home();
        fragment_profile pFragment = new fragment_profile();
        fragment_restaurant rFragment = new fragment_restaurant();


        fm.beginTransaction().replace(R.id.mainMenuView, hFragment).commit();
        actionBar.setTitle("Home");
        actionBar.setSubtitle("Today's Specials");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        fm.beginTransaction().replace(R.id.mainMenuView,hFragment).commit();
                        actionBar.setTitle("Home");
                        actionBar.setSubtitle("Today's Specials");
                            return true;
                    case R.id.rest:
                        fm.beginTransaction().replace(R.id.mainMenuView,rFragment).commit();
                        actionBar.setTitle("Restaurants");
                        actionBar.setSubtitle("");
                            return true;
                    case R.id.cart:
                        fm.beginTransaction().replace(R.id.mainMenuView,cFragment).commit();
                        actionBar.setTitle("Cart");
                        actionBar.setSubtitle("");
                            return true;
                    case R.id.acct:
                        actionBar.setTitle("Profile");
                        actionBar.setSubtitle("");
                        fm.beginTransaction().replace(R.id.mainMenuView,pFragment).commit();
                            return true;
                }

                return false;
            }
        });

    }
}
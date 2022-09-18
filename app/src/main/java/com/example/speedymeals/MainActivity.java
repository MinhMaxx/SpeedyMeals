package com.example.speedymeals;

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

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        fragment_cart cFragment = new fragment_cart();
        fragment_home hFragment = new fragment_home();
        fragment_profile pFragment = new fragment_profile();
        fragment_restaurant rFragment = new fragment_restaurant();


        fm.beginTransaction().replace(R.id.mainMenuView, hFragment).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        fm.beginTransaction().replace(R.id.mainMenuView,hFragment).commit();
                            return true;
                    case R.id.rest:
                        fm.beginTransaction().replace(R.id.mainMenuView,rFragment).commit();
                            return true;
                    case R.id.cart:
                        fm.beginTransaction().replace(R.id.mainMenuView,cFragment).commit();
                            return true;
                    case R.id.acct:
                        fm.beginTransaction().replace(R.id.mainMenuView,pFragment).commit();
                            return true;
                }

                return false;
            }
        });

    }




}
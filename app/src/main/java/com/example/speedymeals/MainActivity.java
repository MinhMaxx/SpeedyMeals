package com.example.speedymeals;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;


import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.FoodList;
import com.example.speedymeals.model.RestaurantList;
import com.example.speedymeals.views.fragment_cart;
import com.example.speedymeals.views.fragment_home;
import com.example.speedymeals.views.fragment_profile;
import com.example.speedymeals.views.fragment_restaurant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.speedymeals.database.DBFiller;
import com.example.speedymeals.database.DBHelper;
import com.example.speedymeals.database.DBManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private RestaurantList restaurants;
    private FoodList foodList;
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gets Database information
            dbManager = new DBManager(this);
            dbManager.open();
            restaurants = new RestaurantList();
            restaurants.load(dbManager.readRestaurant());
            foodList = new FoodList();
            foodList.load(dbManager.readFood());
            ArrayList<Food> FODList = foodList.getFoodsOfTheDay(10);

        actionBar = getSupportActionBar();

        //set up fragments
        FragmentManager fm = getSupportFragmentManager();
        fragment_cart cFragment = new fragment_cart();
        fragment_home hFragment = new fragment_home();
        fragment_profile pFragment = new fragment_profile();
        fragment_restaurant rFragment = new fragment_restaurant();

        //create new bundle for passing into fragment
        Bundle Bundle = new Bundle();
        Bundle.putParcelableArrayList("fodList", FODList);
        Bundle.putParcelable("restList", restaurants);
        hFragment.setArguments(Bundle);



        actionBar.setTitle("Home");
        actionBar.setSubtitle("Today's Specials");
        fm.beginTransaction().replace(R.id.mainMenuView, hFragment).commit();


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
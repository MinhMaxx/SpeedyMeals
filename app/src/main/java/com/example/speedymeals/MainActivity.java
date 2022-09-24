package com.example.speedymeals;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.speedymeals.views.fragment_login;

import com.example.speedymeals.model.Food;
import com.example.speedymeals.model.FoodList;
import com.example.speedymeals.model.RestaurantList;
import com.example.speedymeals.model.User;
import com.example.speedymeals.model.CommonUser;
import com.example.speedymeals.views.fragment_cart;
import com.example.speedymeals.views.fragment_home;
import com.example.speedymeals.views.fragment_profile;
import com.example.speedymeals.views.fragment_restaurant;
import com.example.speedymeals.views.fragment_login;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import com.example.speedymeals.database.DBManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private RestaurantList restaurants;
    private FoodList foodList;
    private User user;
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;
    private CommonUser data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Gets Database information
            dbManager = DBManager.getInstance(this);
            dbManager.open();
            restaurants = new RestaurantList();
            restaurants.load(dbManager.readRestaurant());
            foodList = new FoodList();
            foodList.load(dbManager.readFood());
            ArrayList<Food> FODList = foodList.getFoodsOfTheDay(10);

        actionBar = getSupportActionBar();

        data = new ViewModelProvider(this).get(CommonUser.class);

        //set up fragments
        FragmentManager fm = getSupportFragmentManager();
        fragment_cart cFragment = new fragment_cart();
        fragment_home hFragment = new fragment_home();
        fragment_restaurant rFragment = new fragment_restaurant();
        fragment_login lFragment = new fragment_login();
        fragment_profile pFragment = new fragment_profile();

        //create new bundle for passing into fragment
        Bundle Bundle = new Bundle();
        Bundle.putParcelableArrayList("fodList", FODList); //list was used instead so a new list wasnt generated everytime it was called
        Bundle.putParcelable("restList", restaurants);//adds restaurants into the bundle
        hFragment.setArguments(Bundle);
        rFragment.setArguments(Bundle);



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
                        if(data.getUser()==null){
                            actionBar.setTitle("Profile");
                            actionBar.setSubtitle("");
                            fm.beginTransaction().replace(R.id.mainMenuView,lFragment).commit();
                            return true;
                        }
                        else{
                            actionBar.setTitle("Profile");
                            actionBar.setSubtitle("");
                            fm.beginTransaction().replace(R.id.mainMenuView,pFragment).commit();
                            return true;
                        }

                }

                return false;
            }
        });

    }
}
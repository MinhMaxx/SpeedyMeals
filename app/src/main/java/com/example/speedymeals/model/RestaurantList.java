package com.example.speedymeals.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList implements Parcelable
{
    private List<Restaurant> restaurants;

    public RestaurantList() {restaurants = new ArrayList<>();}

    protected RestaurantList(Parcel in) {
        restaurants = in.createTypedArrayList(Restaurant.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(restaurants);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RestaurantList> CREATOR = new Creator<RestaurantList>() {
        @Override
        public RestaurantList createFromParcel(Parcel in) {
            return new RestaurantList(in);
        }

        @Override
        public RestaurantList[] newArray(int size) {
            return new RestaurantList[size];
        }
    };

    public void load(List<Restaurant> restaurants)
    {
        this.restaurants = restaurants;
    }

    public int size()
    {
        return restaurants.size();
    }

    //Send Food.restaurantID to this to get back the restaurant
    //Since we add it manually -> restaurantID == their position in the list
    public Restaurant get(int i)
    {
        return restaurants.get(i);
    }

    public List<Restaurant> getRestaurants(){ return restaurants; }

    public int add(Restaurant newRestaurant)
    {
        restaurants.add(newRestaurant);
        return restaurants.size() - 1;
    }
}

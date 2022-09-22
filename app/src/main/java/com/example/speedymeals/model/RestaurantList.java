package com.example.speedymeals.model;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList
{
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantList() {}

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

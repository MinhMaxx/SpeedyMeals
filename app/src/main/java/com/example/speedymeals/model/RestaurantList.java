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

    public Restaurant get(int i)
    {
        return restaurants.get(i);
    }

    public int add(Restaurant newRestaurant)
    {
        restaurants.add(newRestaurant);
        return restaurants.size() - 1;
    }
}

package com.example.speedymeals.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodList
{
    private List<Food> foods = new ArrayList<>();

    public FoodList() {}

    public void load(List<Food> foods)
    {
        this.foods = foods;
    }

    public int size()
    {
        return foods.size();
    }

    public Food get(int i)
    {
        return foods.get(i);
    }

    public List<Food> getFoods() { return foods; }

    //Return random List of Food with numFood amount of Foods w/ no duplicate
    public List<Food> getFoodsOfTheDay(int numFood){
        Random rand = new Random();
        List<Food> foodList = new ArrayList<>();

        do{
            Food randFood = foods.get(rand.nextInt(foods.size()));
            if(!foodList.contains(randFood)){
                foodList.add(randFood);
            }
        }while (foodList.size()<numFood);

        return foodList;
    }

    public int add(Food newFood)
    {
        foods.add(newFood);
        return foods.size() - 1;
    }
}

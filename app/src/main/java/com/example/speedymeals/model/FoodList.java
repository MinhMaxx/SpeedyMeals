package com.example.speedymeals.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoodList implements Parcelable
{
    private List<Food> foods;

    public FoodList() {
        foods = new ArrayList<>();
    }

    protected FoodList(Parcel in) {
        foods = in.readArrayList(null);
    }

    public static final Creator<FoodList> CREATOR = new Creator<FoodList>() {
        @Override
        public FoodList createFromParcel(Parcel in) {
            return new FoodList(in);
        }

        @Override
        public FoodList[] newArray(int size) {
            return new FoodList[size];
        }
    };

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
    public ArrayList<Food> getFoodsOfTheDay(int numFood){
        Random rand = new Random();
        ArrayList<Food> foodList = new ArrayList<>();

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(foods);
    }
}

package com.example.speedymeals.model;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CommonCart extends ViewModel {

    private MutableLiveData<ArrayList<Food>> food = new MutableLiveData<ArrayList<Food>>();
    public CommonCart()
    {

    }


    public void addtoList(Food inFood)
    {
        ArrayList<Food> foods = new ArrayList<>();
        if(food.getValue()!=null)
            foods = food.getValue();
        foods.add(inFood);
        food.setValue(foods);
    }

    public ArrayList<Food> getFoods()
    {
        return food.getValue();
    }
}

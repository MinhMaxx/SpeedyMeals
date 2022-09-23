package com.example.speedymeals.model;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CommonCart extends ViewModel {

    private MutableLiveData<ArrayList<Food>> cart = new MutableLiveData<ArrayList<Food>>();
    private MutableLiveData<ArrayList<Integer>> noAmount = new MutableLiveData<ArrayList<Integer>>();
    public CommonCart()
    {

    }


    public void addtoList(Food inFood, int infoodAmount)
    {
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Integer> foodAmount = new ArrayList<>();
        if(noAmount.getValue()!=null) //Add to list the same time so index will correspond with food
            foodAmount = noAmount.getValue();
        if(cart.getValue()!=null)
            foods = cart.getValue();
        foods.add(inFood);
        foodAmount.add(infoodAmount);
        cart.setValue(foods);

    }

    public Food getFoods(int i)
    {
        return cart.getValue().get(i);
    }

    public Integer getAmount(int i)
    {
        return noAmount.getValue().get(i);
    }
    public void updateOrder(ArrayList<Food> inCart, ArrayList<Integer> inNoItem)
    {
        cart.setValue(inCart);
        noAmount.setValue(inNoItem);
    }

    public void setNoAmount(int i, Integer noOfItem)
    {
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Integer> foodAmount = new ArrayList<>();
        if(noAmount.getValue()!=null)
            foodAmount = noAmount.getValue();
        if(cart.getValue()!=null)
            foods = cart.getValue();

        if(foods.size()!=0 && foodAmount.size()!=0) {
            if (noOfItem == 0) {
                foodAmount.remove(i);
                foods.remove(i);
                cart.setValue(foods);
            }
            else
            {
                foodAmount.set(i, noOfItem);
            }

            noAmount.setValue(foodAmount);
        }
        }

}

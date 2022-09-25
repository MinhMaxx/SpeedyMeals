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


    public void addtoList(Food inFood, int inFoodAmount)
    {
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Integer> foodAmount = new ArrayList<>();
        if(cart.getValue()!=null ) {
            foods = cart.getValue();
        }
        if(noAmount.getValue()!=null){
            foodAmount = noAmount.getValue();
        }
        //If the food is already in the cart
        if(foods.contains(inFood)){
            int foodPosition = foods.indexOf(inFood);
            int newFoodAmount = foodAmount.get(foodPosition) + inFoodAmount;
            foodAmount.set(foodPosition,newFoodAmount);
        }
        //If the food is not in the cart
        else{
            foods.add(inFood);
            foodAmount.add(inFoodAmount);
        }
        cart.setValue(foods);
        noAmount.setValue(foodAmount);
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

    public int cartSize()
    {
        if(cart.getValue()!=null)
            return cart.getValue().size();

        return 0;
    }

    //Processing Logic()

    public double totalPrice()
    {
        double totalPrice = 0;
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Integer> foodAmount = new ArrayList<>();
        if(noAmount.getValue()!=null)
            foodAmount = noAmount.getValue();
        if(cart.getValue()!=null)
            foods = cart.getValue();

        int ii = 0;

        for (Food nfood:
             foods) {
            totalPrice += nfood.getPrice() * foodAmount.get(ii);
            ii++;
        }

        return totalPrice;
    }

    public void clearCart()
    {
        noAmount.setValue(null);
        cart.setValue(null);
    }

    public ArrayList<Food> getFoodList()
    {
        return cart.getValue();

    }

    public ArrayList<Integer> getNoItems()
    {
        return noAmount.getValue();
    }


}

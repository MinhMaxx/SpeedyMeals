package com.example.speedymeals.model;

import android.content.res.Resources;

import com.example.speedymeals.R;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private int id;
    private String name;
    private int profilePictureID;
    private List<Food> menus;

    public Restaurant(int id,String name,int profilePictureID){
        this.id = id;
        this.name = name;
        this.profilePictureID = profilePictureID;
        this.menus = new ArrayList<>();
    }

    public void loadMenus(List<Food> menus){this.menus=menus;}

    public boolean removeFood(Food food){return menus.remove(food);}

    public Food getFood(int position){return menus.get(position);}

    public String getName(){return name;}

    public int getProfilePictureID(){return profilePictureID;}

    public int getID(){return id;}

    public int size()
    {
        return menus.size();
    }

    public int addFood(Food newFood){
        menus.add(newFood);
        return menus.size()-1;
    }

}

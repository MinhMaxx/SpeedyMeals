package com.example.speedymeals.model;

import android.content.res.Resources;

import com.example.speedymeals.R;

import java.util.List;

public class Restaurant {
    private String name;
    private int profilePictureID;
    private List<Food> menus;

    public Restaurant(String name){
        this.name = name;
        this.menus = null;

        //Add drawable id to object base on name
        //If can't find a drawable -> use default one
        //I haven't tested it yet though
        int id = Resources.getSystem().getIdentifier(name,"drawable","com.example.speedymeals");
        if(id == 0){
            profilePictureID = R.drawable.default_image;
        }
        else{
            profilePictureID = id;
        }
    }

    public void addFood(Food food){menus.add(food);}

    public boolean removeFood(Food food){return menus.remove(food);}

    public Food getFood(int position){return menus.get(position);}

    public String getName(){return name;}

    public int getProfilePictureID(){return profilePictureID;}
}

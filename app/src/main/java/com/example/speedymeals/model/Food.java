package com.example.speedymeals.model;
import android.content.res.Resources;

import com.example.speedymeals.R;

public class Food {
    private String name;
    private int profilePictureID;
    private double price;
    private String description;

    public Food(String name, double price, String description){
        this.name = name;
        this.description = description;
        this.price = price;

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

    public String getName(){return name;}

    public int getProfilePictureID(){return profilePictureID;}

    public double getPrice(){return price;}

    public String getDescription(){return description;}

}

package com.example.speedymeals.model;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.speedymeals.R;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Parcelable {
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

    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        profilePictureID = in.readInt();
        menus = in.createTypedArrayList(Food.CREATOR);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(profilePictureID);
        parcel.writeTypedList(menus);
    }
}

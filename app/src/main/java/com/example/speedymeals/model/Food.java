package com.example.speedymeals.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable
{
    private int id;
    private String name;
    private int profilePictureID;
    private double price;
    private String description;
    private int restaurantID;

    public Food(int id, String name, String description, double price, int profilePictureID,int restaurantID){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.profilePictureID = profilePictureID;
        this.restaurantID = restaurantID;
    }

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        profilePictureID = in.readInt();
        price = in.readDouble();
        description = in.readString();
        restaurantID = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public int getId() {return id;}

    public String getName(){return name;}

    public int getProfilePictureID(){return profilePictureID;}

    public double getPrice(){return price;}

    public String getDescription(){return description;}

    public int getRestaurantID(){return restaurantID;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(profilePictureID);
        parcel.writeDouble(price);
        parcel.writeString(description);
        parcel.writeInt(restaurantID);
    }
}

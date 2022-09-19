package com.example.speedymeals.model;

public class Food {
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

    public int getId() {return id;}

    public String getName(){return name;}

    public int getProfilePictureID(){return profilePictureID;}

    public double getPrice(){return price;}

    public String getDescription(){return description;}

    public int getRestaurantID(){return restaurantID;}

}

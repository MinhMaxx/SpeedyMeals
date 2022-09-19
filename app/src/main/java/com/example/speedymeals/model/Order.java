package com.example.speedymeals.model;

import java.util.Date;

public class Order {
    private int id;
    private int userID;
    private String restaurantName;
    private String[] foodName;
    private String[] foodNumber;
    private String[] foodPrice;
    private Date date;
    private double totalCost;

    public Order(int id, int userID, String restaurantName, String[] foodName,
                 String[] foodNumber, String[] foodPrice, Date date, double totalCost){
        this.id = id;
        this.userID = userID;
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodNumber = foodNumber;
        this.foodPrice = foodPrice;
        this.date = date;
        this.totalCost = totalCost;
    }

    public int getId() {return id;}

    public int getUserID(){return userID;}

    public String getRestaurantName(){return restaurantName;}

    public String[] getFoodName(){return foodName;}

    public String[] getFoodNumber(){return foodNumber;}

    public String[] getFoodPrice(){return foodPrice;}

    public Date getDate(){return date;}

    public double getTotalCost(){return totalCost;}

}

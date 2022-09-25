package com.example.speedymeals.model;

import java.util.Date;

public class Order {
    private int id;
    private int userID;
    private String address;
    private String[] restaurantName;
    private String[] foodName;
    private String[] foodNumber;
    private String[] foodPrice;
    private String date;
    private String totalCost;

    public Order(int id, int userID, String address,String[] restaurantName, String[] foodName,
                 String[] foodNumber, String[] foodPrice, String date, String totalCost){
        this.id = id;
        this.userID = userID;
        this.address = address;
        this.restaurantName = restaurantName;
        this.foodName = foodName;
        this.foodNumber = foodNumber;
        this.foodPrice = foodPrice;
        this.date = date;
        this.totalCost = totalCost;
    }

    public int getId() {return id;}

    public int getUserID(){return userID;}

    public String getAddress(){return address;}

    public String getRestaurantName(){
        String str = "";
        for (int i = 0;i<restaurantName.length; i++) {
            str = str+restaurantName[i];
            // Do not append comma at the end of last element
            if(i<restaurantName.length-1){
                str = str+"\n";
            }
        }
        return str;
    }

    public String getFoodName(){
        String str = "";
        for (int i = 0;i<foodName.length; i++) {
            str = str+foodName[i];
            // Do not append comma at the end of last element
            if(i<foodName.length-1){
                str = str+"\n";
            }
        }
        return str;
    }

    public String getFoodNumber(){
        String str = "";
        for (int i = 0;i<foodNumber.length; i++) {
            str = str+"x"+foodNumber[i];
            // Do not append comma at the end of last element
            if(i<foodNumber.length-1){
                str = str+"\n";
            }
        }
        return str;
    }

    public String getFoodPrice(){
        String str = "";
        for (int i = 0;i<foodPrice.length; i++) {
            Double priceFood = Double.parseDouble(foodPrice[i])*Integer.parseInt(foodNumber[i]);
            str = str+"$"+priceFood.toString();
            // Do not append comma at the end of last element
            if(i<foodPrice.length-1){
                str = str+"\n";
            }
        }
        return str;
    }

    public String getDate(){return date;}

    public String getTotalCost(){return "$"+totalCost;}

}

package com.example.speedymeals.model;
import android.content.Context;
import android.content.res.Resources;
import com.example.speedymeals.R;
import com.example.speedymeals.database.DBManager;

public class User {
    private int id;
    private String username;
    private String name;
    private String address;


    //To use when import user from database
    public User(int id,String username, String name, String address){
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
    }

    public int getId() {return id;}

    public String getUsername(){return username;}

    public String getName(){return name;}

    public String getAddress(){return address;}
}

package com.example.speedymeals.model;
import android.content.res.Resources;
import com.example.speedymeals.R;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {
    private int id;
    private String username;
    private String address;

    //To use when import user from database
    public User(int id, String username, String address){
        this.id = id;
        this.username = username;
        this.address = address;
    }

    public int getId() {return id;}

    public String getUsername(){return username;}

    public String getAddress(){return address;}
}

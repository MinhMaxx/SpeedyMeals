package com.example.speedymeals.model;
import android.content.res.Resources;
import com.example.speedymeals.R;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class User {
    private int id;
    private String username;

    //To use when import user from database
    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId() {return id;}

    public String getUsername(){return username;}

    //Checking password with BCrypt
//    public boolean checkPassword(String password){
//        return BCrypt.verifyer().verify(this.password.toCharArray(),password).verified;
//    }
}

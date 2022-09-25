package com.example.speedymeals.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.speedymeals.model.User;

import java.util.ArrayList;

public class CommonUser extends ViewModel {
    private MutableLiveData<User> user;

    public CommonUser(){
        user = new MutableLiveData<User>();
        user.setValue(null);
    }

    public User getUser(){return user.getValue();}

    public void setUser(User user){this.user.setValue(user);}

    public void clearUser()
    {
        user.setValue(null);
    }
}

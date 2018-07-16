package com.example.arielo.momaentregable.controller;

import android.content.Context;

import com.example.arielo.momaentregable.model.database.DatabaseUser;
import com.example.arielo.momaentregable.model.pojo.User;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class UserControllerRoom {
    private Context context;

    public UserControllerRoom(Context context) {
        this.context = context;
    }

    public List<User> getUser(){
        DatabaseUser databaseUser = new DatabaseUser(context);
        return databaseUser.getAllUser();
    }

    public void addUser(User user){
        DatabaseUser databaseUser = new DatabaseUser(context);
        databaseUser.insertAll(user);
    }

    public void removeUser(User user){
        DatabaseUser databaseUser = new DatabaseUser(context);
        databaseUser.delete(user);
    }
}

package com.example.arielo.momaentregable.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.arielo.momaentregable.model.pojo.User;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class DatabaseUser {
    private RoomAppDatabase db;

    public DatabaseUser(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    //DAO Artist
    public List<User> getAllUser(){
        return db.userDao().getAllUser();
    }

    public void insertAll(User... users){
        db.userDao().insertAll(users);
    }

    public void delete(User user){
        db.userDao().delete(user);
    }

}

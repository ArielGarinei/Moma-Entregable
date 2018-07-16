package com.example.arielo.momaentregable.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.arielo.momaentregable.model.pojo.User;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

@Dao
public interface DAOUser {

    @Query("SELECT * FROM User")
    List<User> getAllUser();

    @Query("SELECT * FROM User WHERE email Like :email")
    User getUserWhithEmail(String email);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
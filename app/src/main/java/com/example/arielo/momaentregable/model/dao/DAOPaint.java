package com.example.arielo.momaentregable.model.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

/**
 * Created by Arielo on 15/7/2018.
 */

@Dao
public interface DAOPaint {
    @Query("SELECT * FROM Paint")
    List<Paint> getAllPinturas();

    @Query("SELECT * FROM Paint WHERE name Like :name")
    Paint getPaitWithName(String name);

    @Insert
    void insertAll(Paint... paint);

    @Delete
    void delete(Paint paint);
}
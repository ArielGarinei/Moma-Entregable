package com.example.arielo.momaentregable.model.dao;

/**
 * Created by Arielo on 15/7/2018.
 */
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

@Dao
public interface DAOPintura {
    @Query("SELECT * FROM Paint")
    List<Paint> getAllPinturas();

    @Query("SELECT * FROM Paint WHERE name Like :id")
    Paint getPinturaNombre(String id);

    @Insert
    void insertAll(Paint... paint);

    @Delete
    void delete(Paint paint);
}
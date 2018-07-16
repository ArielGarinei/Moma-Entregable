package com.example.arielo.momaentregable.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class DatabasePaints {
    private RoomAppDatabase db;

    public DatabasePaints(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    public List<Paint> getAllPaints(){
        return db.pinturaDao().getAllPinturas();
    }

    public void insertAll(Paint... paints){
        db.pinturaDao().insertAll(paints);
    }

    public void delete(Paint paints){
        db.pinturaDao().delete(paints);
    }
}

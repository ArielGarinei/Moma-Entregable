package com.example.arielo.momaentregable.model.database;

/**
 * Created by Arielo on 15/7/2018.
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.arielo.momaentregable.model.dao.DAOArtista;
import com.example.arielo.momaentregable.model.dao.DAOPintura;
import com.example.arielo.momaentregable.model.pojo.Artist;
import com.example.arielo.momaentregable.model.pojo.Paint;

@Database(entities = {Paint.class, Artist.class}, version = 1)
public abstract class RoomAppDatabase extends RoomDatabase {
    public abstract DAOPintura pinturaDao();
    public abstract DAOArtista artistaDao();
}

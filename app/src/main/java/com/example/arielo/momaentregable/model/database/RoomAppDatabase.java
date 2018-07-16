package com.example.arielo.momaentregable.model.database;

/**
 * Created by Arielo on 15/7/2018.
 */
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.arielo.momaentregable.model.dao.DAOArtist;
import com.example.arielo.momaentregable.model.dao.DAOPaint;
import com.example.arielo.momaentregable.model.dao.DAOUser;
import com.example.arielo.momaentregable.model.pojo.Artist;
import com.example.arielo.momaentregable.model.pojo.Paint;
import com.example.arielo.momaentregable.model.pojo.User;

@Database(entities = {Paint.class, Artist.class, User.class}, version = 1)
public abstract class RoomAppDatabase extends RoomDatabase {
    public abstract DAOPaint paintDao();
    public abstract DAOArtist artistDao();
    public abstract DAOUser userDao();
}

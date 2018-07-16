package com.example.arielo.momaentregable.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.arielo.momaentregable.model.pojo.Artist;

import java.util.List;

/**
 * Created by Arielo on 15/7/2018.
 */

public class Database {
    private RoomAppDatabase db;

    public Database(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    //DAO Artist
    public List<Artist> getAllArtist(){
        return db.artistaDao().getAllArtists();
    }

    public void insertAll(Artist... artists){
        db.artistaDao().insertAll(artists);
    }

    public void delete(Artist artist){
        db.artistaDao().delete(artist);
    }

}

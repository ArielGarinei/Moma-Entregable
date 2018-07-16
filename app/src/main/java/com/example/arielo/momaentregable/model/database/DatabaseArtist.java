package com.example.arielo.momaentregable.model.database;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.arielo.momaentregable.model.pojo.Artist;

import java.util.List;

/**
 * Created by Arielo on 15/7/2018.
 */

public class DatabaseArtist {
    private RoomAppDatabase db;

    public DatabaseArtist(Context context) {
        this.db = Room.databaseBuilder(context, RoomAppDatabase.class, "database-name").allowMainThreadQueries().build();
    }

    //DAO Artist
    public List<Artist> getAllArtist(){
        return db.artistDao().getAllArtists();
    }

    public void insertAll(Artist... artists){
        db.artistDao().insertAll(artists);
    }

    public void delete(Artist artist){
        db.artistDao().delete(artist);
    }

}

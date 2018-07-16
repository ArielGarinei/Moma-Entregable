package com.example.arielo.momaentregable.controller;

import android.content.Context;

import com.example.arielo.momaentregable.model.database.Database;
import com.example.arielo.momaentregable.model.pojo.Artist;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class ArtistsControllerRoom {

    private Context context;

    public ArtistsControllerRoom(Context context) {
        this.context = context;
    }

    public List<Artist> getArtists(){
        Database database = new Database(context);
        return database.getAllArtist();
    }

    public void addArtist(Artist artist){
        Database database = new Database(context);
        database.insertAll(artist);
    }

    public void removeArtist(Artist artist){
        Database database = new Database(context);
        database.delete(artist);
    }
}
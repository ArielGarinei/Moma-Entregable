package com.example.arielo.momaentregable.controller;

import android.content.Context;

import com.example.arielo.momaentregable.model.database.DatabaseArtist;
import com.example.arielo.momaentregable.model.pojo.Artist;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class ArtistControllerRoom {

    private Context context;

    public ArtistControllerRoom(Context context) {
        this.context = context;
    }

    public List<Artist> getArtists(){
        DatabaseArtist databaseArtist = new DatabaseArtist(context);
        return databaseArtist.getAllArtist();
    }

    public void addArtist(Artist artist){
        DatabaseArtist databaseArtist = new DatabaseArtist(context);
        databaseArtist.insertAll(artist);
    }

    public void removeArtist(Artist artist){
        DatabaseArtist databaseArtist = new DatabaseArtist(context);
        databaseArtist.delete(artist);
    }
}
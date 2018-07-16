package com.example.arielo.momaentregable.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.arielo.momaentregable.model.pojo.Artist;

import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

@Dao
public interface DAOArtist {

    @Query("SELECT * FROM Artist")
    List<Artist> getAllArtists();

    @Query("SELECT * FROM Artist WHERE name Like :name")
    Artist getArtistWhithName(String name);

    @Insert
    void insertAll(Artist... artist);

    @Delete
    void delete(Artist artist);
}
package com.example.arielo.momaentregable.model.pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arielo on 24/6/2018.
 */
@Entity
public class Artist implements Serializable {

    @PrimaryKey
    @NonNull
    @PropertyName("artistId")
    public String artistId;

    @PropertyName("Influenced_by")
    public String influencedBy;

    @PropertyName("name")
    public String name;

    @PropertyName("nationality")
    public String nationality;

    public String getInfluencedBy() {
        return influencedBy;
    }

    public void setInfluencedBy(String influencedBy) {
        this.influencedBy = influencedBy;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}

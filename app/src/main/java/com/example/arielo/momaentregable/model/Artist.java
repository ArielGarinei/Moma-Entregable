package com.example.arielo.momaentregable.model;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Arielo on 24/6/2018.
 */

public class Artist implements Serializable {
    @PropertyName("Influenced_by")
    private String influencedBy;

    @PropertyName("artistId")
    private String artistId;

    @PropertyName("name")
    private String name;

    @PropertyName("nationality")
    private String nationality;

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

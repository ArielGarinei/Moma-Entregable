package com.example.arielo.momaentregable.model;
import java.io.Serializable;

public class Pintura implements Serializable {
    private String image;
    private String name;
    private String artistId;

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}

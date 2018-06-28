package com.example.arielo.momaentregable.model;

import java.io.Serializable;

/**
 * Created by Arielo on 17/6/2018.
 */

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
}

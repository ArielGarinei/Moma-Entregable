package com.example.arielo.momaentregable.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

public class ContenedorPintura {
    @SerializedName("paints")
    List<Pintura> pinturaList;

    public ContenedorPintura(List<Pintura> pinturaList) {
        this.pinturaList = pinturaList;
    }

    public List<Pintura> getPinturaList() {
        return pinturaList;
    }
}

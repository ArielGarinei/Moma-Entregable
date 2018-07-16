package com.example.arielo.momaentregable.model.contenedores;

import com.example.arielo.momaentregable.model.pojo.Paint;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

public class ContenedorPintura {
    @SerializedName("paints")
    List<Paint> paintList;

    public ContenedorPintura(List<Paint> paintList) {
        this.paintList = paintList;
    }

    public List<Paint> getPaintList() {
        return paintList;
    }
}

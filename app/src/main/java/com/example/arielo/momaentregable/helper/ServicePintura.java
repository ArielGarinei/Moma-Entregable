package com.example.arielo.momaentregable.helper;

import com.example.arielo.momaentregable.model.contenedores.PaintConteiner;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Arielo on 17/6/2018.
 */

public interface ServicePintura {
    @GET("/bins/x858r/")
    Call<PaintConteiner>getPintura();
}

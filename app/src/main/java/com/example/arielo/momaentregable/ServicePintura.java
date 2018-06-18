package com.example.arielo.momaentregable;

import com.example.arielo.momaentregable.model.ContenedorPintura;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Arielo on 17/6/2018.
 */

public interface ServicePintura {
    @GET("/bins/x858r/")
    Call<ContenedorPintura>getPintura();
}

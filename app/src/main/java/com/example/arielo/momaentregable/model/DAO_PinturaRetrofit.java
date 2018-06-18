package com.example.arielo.momaentregable.model;

import com.example.arielo.momaentregable.ResultListener;
import com.example.arielo.momaentregable.ServicePintura;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arielo on 17/6/2018.
 */

public class DAO_PinturaRetrofit {

    private  String baseURL;
    private  Retrofit retrofit;
    private  ServicePintura servicePintura;

    public DAO_PinturaRetrofit() {
        baseURL = "https://api.myjson.com/bins/x858r/";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePintura = retrofit.create(ServicePintura.class);
    }
    public void obtenerPinturasDeInternet(final ResultListener<List<Pintura>> escuchadorDelControlador){
        Call<ContenedorPintura> retrofitListener = servicePintura.getPintura();
        retrofitListener.enqueue(new Callback<ContenedorPintura>() {
            @Override
            public void onResponse(Call<ContenedorPintura> call, Response<ContenedorPintura> response) {
                ContenedorPintura contenedorPintura = response.body();
                escuchadorDelControlador.finish(contenedorPintura.getPinturaList());
            }

            @Override
            public void onFailure(Call<ContenedorPintura> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<Pintura>());
            }
        });

    }
}

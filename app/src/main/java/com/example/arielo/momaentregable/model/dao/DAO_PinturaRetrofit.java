package com.example.arielo.momaentregable.model.dao;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.helper.ServicePintura;
import com.example.arielo.momaentregable.model.contenedores.ContenedorPintura;
import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAO_PinturaRetrofit {
    private  String baseURL;
    private  Retrofit retrofit;
    private  ServicePintura servicePintura;

    public DAO_PinturaRetrofit() {
        baseURL = "https://api.myjson.com";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePintura = retrofit.create(ServicePintura.class);
    }

    public void obtenerPinturasDeInternet(final ResultListener<List<Paint>> escuchadorDelControlador){
        Call<ContenedorPintura> retrofitListener = servicePintura.getPintura();
        retrofitListener.enqueue(new Callback<ContenedorPintura>() {
            @Override
            public void onResponse(Call<ContenedorPintura> call, Response<ContenedorPintura> response) {
                ContenedorPintura contenedorPintura = response.body();
                escuchadorDelControlador.finish(contenedorPintura.getPaintList());
            }

            @Override
            public void onFailure(Call<ContenedorPintura> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<Paint>());
            }
        });

    }
}

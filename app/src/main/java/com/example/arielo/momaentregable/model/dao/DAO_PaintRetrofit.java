package com.example.arielo.momaentregable.model.dao;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.helper.ServicePintura;
import com.example.arielo.momaentregable.model.contenedores.PaintConteiner;
import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAO_PaintRetrofit {
    private  String baseURL;
    private  Retrofit retrofit;
    private  ServicePintura servicePintura;

    public DAO_PaintRetrofit() {
        baseURL = "https://api.myjson.com";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicePintura = retrofit.create(ServicePintura.class);
    }

    public void obtenerPaintsDeInternet(final ResultListener<List<Paint>> escuchadorDelControlador){
        Call<PaintConteiner> retrofitListener = servicePintura.getPintura();
        retrofitListener.enqueue(new Callback<PaintConteiner>() {
            @Override
            public void onResponse(Call<PaintConteiner> call, Response<PaintConteiner> response) {
                PaintConteiner paintConteiner = response.body();
                escuchadorDelControlador.finish(paintConteiner.getPaintList());
            }

            @Override
            public void onFailure(Call<PaintConteiner> call, Throwable t) {
                escuchadorDelControlador.finish(new ArrayList<Paint>());
            }
        });

    }
}

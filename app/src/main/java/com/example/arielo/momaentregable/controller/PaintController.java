package com.example.arielo.momaentregable.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.model.dao.DAO_PaintRetrofit;
import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

public class PaintController {

    Context context;

    public PaintController(Context context) {
        this.context = context;
    }

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorVista){

        if(hayInternet()){
            ResultListener<List<Paint>> escuchadorControlador = new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    PaintControllerRoom controllerRoomPaints = new PaintControllerRoom(context);
                    for (Paint unPaint : resultado ) {
                        controllerRoomPaints.removePaint(unPaint);
                        controllerRoomPaints.addPaint(unPaint);
                    }
                    escuchadorVista.finish(resultado);
                }
            };

            DAO_PaintRetrofit dao_paintRetrofit = new DAO_PaintRetrofit();
            dao_paintRetrofit.obtenerPaintsDeInternet(escuchadorControlador);
        }
        else {
            PaintControllerRoom paintControllerRoom = new PaintControllerRoom(context);
            escuchadorVista.finish(paintControllerRoom.getPaints());
        }
    }

    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}

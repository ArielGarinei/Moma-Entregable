package com.example.arielo.momaentregable.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.model.dao.DAO_PinturaRetrofit;
import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

public class PinturaController {

    Context context;

    public PinturaController(Context context) {
        this.context = context;
    }

    public void obtenerPaints(final ResultListener<List<Paint>> escuchadorVista){

        if(hayInternet()){                                                                          //Si hay internet busco en retrofit
            ResultListener<List<Paint>> escuchadorControlador = new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    PinturaControllerRoom controllerRoomPaints = new PinturaControllerRoom(context);
                    for (Paint unPaint : resultado ) {                                              //Actualizo la lista en Room
                        controllerRoomPaints.removePaint(unPaint);
                        controllerRoomPaints.addPaint(unPaint);
                    }
                    escuchadorVista.finish(resultado);
                }
            };

            DAO_PinturaRetrofit dao_pinturaRetrofit = new DAO_PinturaRetrofit();
            dao_pinturaRetrofit.obtenerPinturasDeInternet(escuchadorControlador);
        }
        else {                                                                                     //Sino en room
            PinturaControllerRoom pinturaControllerRoom = new PinturaControllerRoom(context);
            escuchadorVista.finish(pinturaControllerRoom.getPaints());
        }
    }

    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            //Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            //Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

package com.example.arielo.momaentregable.controller;

import com.example.arielo.momaentregable.ResultListener;
import com.example.arielo.momaentregable.model.DAO_PinturaArchivo;
import com.example.arielo.momaentregable.model.DAO_PinturaRetrofit;
import com.example.arielo.momaentregable.model.Pintura;

import java.security.Permission;
import java.util.List;

/**
 * Created by Arielo on 17/6/2018.
 */

public class PinturaController {

    public void obtenerPintura(final ResultListener<List<Pintura>> escuchadorDeLaVista){
        ResultListener<List<Pintura>> escuchadorDelControlador = new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        };
        List<Pintura> pinturaList = null;
        if (hayInternet()){
            DAO_PinturaRetrofit dao_pinturaRetrofit = new DAO_PinturaRetrofit();
            dao_pinturaRetrofit.obtenerPinturasDeInternet(escuchadorDelControlador);
        }/*else {
            DAO_PinturaArchivo dao_pinturaArchivo = new DAO_PinturaArchivo();
            pinturaList = dao_pinturaArchivo.obtenerPinturasDeArchivo();
            escuchadorDeLaVista.finish(pinturaList);
        }*/
    }

    public Boolean hayInternet(){
        return true;
    }
}

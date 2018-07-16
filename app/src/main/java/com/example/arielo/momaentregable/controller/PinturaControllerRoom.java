package com.example.arielo.momaentregable.controller;

import android.content.Context;

import com.example.arielo.momaentregable.model.database.DatabasePaints;
import com.example.arielo.momaentregable.model.pojo.Paint;

import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class PinturaControllerRoom {

    private Context context;

    public PinturaControllerRoom(Context context) {
        this.context = context;
    }

    public List<Paint> getPaints(){
        DatabasePaints dataBase = new DatabasePaints(context);
        return dataBase.getAllPaints();
    }

    public void addPaint(Paint paint){
        DatabasePaints database = new DatabasePaints(context);
        database.insertAll(paint);
    }

    public void removePaint(Paint paint){
        DatabasePaints database = new DatabasePaints(context);
        database.delete(paint);
    }
}
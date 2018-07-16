package com.example.arielo.momaentregable.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.model.pojo.Artist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arielo on 16/7/2018.
 */

public class ArtistController {
    Context context;

    public ArtistController(Context context) {
        this.context = context;
    }

    public void obtenerArtists(final ResultListener<List<Artist>> escuchadorDeLaVista){

        if(hayInternet()){                                                                          //Si hay internet
            final List<Artist> artistList = new ArrayList<>();
            DatabaseReference mDataBase;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            mDataBase = firebaseDatabase.getReference();
            DatabaseReference reference = mDataBase.child("artists");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArtistControllerRoom controllerRoomArtists = new ArtistControllerRoom(context);
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()){
                        Artist artistLeido = dataSnapshotChild.getValue(Artist.class);
                        artistList.add(artistLeido);

                        controllerRoomArtists.removeArtist(artistLeido);
                        controllerRoomArtists.addArtist(artistLeido);
                    }
                    //Toast.makeText(context, artistList.toString(), Toast.LENGTH_SHORT).show();
                    escuchadorDeLaVista.finish(artistList);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Error al cargar!", Toast.LENGTH_SHORT).show();
                }
            };
            reference.addValueEventListener(valueEventListener);


        } else {                                                                                    //Si no hay internet
            ArtistControllerRoom artistControllerRoom = new ArtistControllerRoom(context);
            escuchadorDeLaVista.finish(artistControllerRoom.getArtists());
        }
    }

    private boolean hayInternet(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(context, "Hay internet", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "NO hay internet", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}
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

        if(hayInternet()){
            final List<Artist> artistList = new ArrayList<>();
            DatabaseReference databaseReference;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
            DatabaseReference reference = databaseReference.child("artists");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    ArtistControllerRoom controllerRoomArtists = new ArtistControllerRoom(context);
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()){
                        Artist unArtist = dataSnapshotChild.getValue(Artist.class);
                        artistList.add(unArtist);

                        controllerRoomArtists.removeArtist(unArtist);
                        controllerRoomArtists.addArtist(unArtist);
                    }
                    escuchadorDeLaVista.finish(artistList);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            reference.addValueEventListener(valueEventListener);


        } else {
            ArtistControllerRoom artistControllerRoom = new ArtistControllerRoom(context);
            escuchadorDeLaVista.finish(artistControllerRoom.getArtists());
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
package com.example.arielo.momaentregable.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.arielo.momaentregable.helper.ResultListener;
import com.example.arielo.momaentregable.model.pojo.Artist;
import com.example.arielo.momaentregable.model.pojo.User;
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

public class UserController {

    Context context;

    public UserController(Context context) {
        this.context = context;
    }

    public void obtenerUsers(final ResultListener<List<User>> escuchadorDeLaVista){

        if(hayInternet()){                                                                          //Si hay internet
            final List<User> userList = new ArrayList<>();
            DatabaseReference mDataBase;
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            mDataBase = firebaseDatabase.getReference();
            DatabaseReference reference = mDataBase.child("Usuarios");
            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    UserControllerRoom userControllerRoom = new UserControllerRoom(context);
                    for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()){
                        User UserLeido = dataSnapshotChild.getValue(User.class);
                        userList.add(UserLeido);

                        userControllerRoom.removeUser(UserLeido);
                        userControllerRoom.addUser(UserLeido);
                    }
                    //Toast.makeText(context, userList.toString(), Toast.LENGTH_SHORT).show();
                    escuchadorDeLaVista.finish(userList);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(context, "Error al cargar!", Toast.LENGTH_SHORT).show();
                }
            };
            reference.addValueEventListener(valueEventListener);


        } else {                                                                                    //Si no hay internet
            UserControllerRoom userControllerRoom = new UserControllerRoom(context);
            escuchadorDeLaVista.finish(userControllerRoom.getUser());
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

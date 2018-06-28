package com.example.arielo.momaentregable.view.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.ResultListener;
import com.example.arielo.momaentregable.controller.PinturaController;
import com.example.arielo.momaentregable.controller.adapter.RecyclerViewAdapter;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.model.Pintura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecyclerView extends Fragment {
    private List<Pintura> pinturaList;
    private RecyclerView recyclerView;
    private List<Artist> artistList;


    public FragmentRecyclerView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        cargarPinturas();
        leerSimple(getContext());



        return view;
    }
    public void leerSimple(final Context context){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = firebaseDatabase.getReference();
        DatabaseReference referencePrimerMensaje = mDatabase.child("artists").child("0");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Artist artist = dataSnapshot.getValue(Artist.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        referencePrimerMensaje.addListenerForSingleValueEvent(valueEventListener);
    }

    public void  cargarPinturas(){
        PinturaController pinturaController = new PinturaController();
        pinturaController.obtenerPintura(new ResultListener<List<Pintura>>() {
        @Override
        public void finish(List<Pintura> resultado) {
            pinturaList = resultado;
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(pinturaList);
            recyclerView.setAdapter(recyclerViewAdapter);

        }
    });
    }

}

package com.example.arielo.momaentregable.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.ResultListener;
import com.example.arielo.momaentregable.controller.PinturaController;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.model.Pintura;
import com.example.arielo.momaentregable.view.adapter.RecyclerViewAdapter;
import com.example.arielo.momaentregable.view.adapter.ViewPagerAdapter;
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
public class DetalleFragmentViewPager extends Fragment {
    private ViewPager viewPager;
    private List<Pintura> pinturaList;
    private List<Artist> artistList;
    private ViewPagerAdapter viewPagerAdapter;
    private PinturaController pinturaController;
    public static final String POSICION = "posicion";



    public DetalleFragmentViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_fragment_view_pager, container, false);
        viewPager = view.findViewById(R.id.contenedorDeFragmentosViewPager);
        LeerLista(view);



        pinturaController = new PinturaController();
        pinturaController.obtenerPintura(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> resultado) {
                pinturaList = resultado;
                viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), artistList, pinturaList);
                viewPager.setAdapter(viewPagerAdapter);
                Bundle bundle = getArguments();
                int posicion = bundle.getInt(POSICION);
                viewPager.setCurrentItem(posicion);
                viewPager.setOffscreenPageLimit(10);
            }
        });



        return view;
    }
    public void LeerLista(View view){
        DatabaseReference mDatabase;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase= firebaseDatabase.getReference();
        DatabaseReference referencelista = mDatabase.child("artists");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList = new ArrayList<>();
                for (DataSnapshot dataSnapshotChild : dataSnapshot.getChildren()) {
                    Artist artistEnPosicion = dataSnapshotChild.getValue(Artist.class);
                    artistList.add(artistEnPosicion);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        referencelista.addListenerForSingleValueEvent(valueEventListener);
    }
}

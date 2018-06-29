package com.example.arielo.momaentregable.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.model.Pintura;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetalleFragment extends Fragment {
    View view;
    List<Artist> artistList;
    ImageView imageViewPintura;
    TextView textViewNombrePintura;
    TextView textViewNombreArtista;
    TextView textViewInfluencias;
    public static final String ARTISTA = "artist";
    public static final String PINTURA = "paint";

    public static DetalleFragment creadorDeFragmentos (Pintura pintura, Artist artist){
        DetalleFragment detalleFragment = new DetalleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARTISTA,artist);
        bundle.putSerializable(PINTURA,pintura);
        detalleFragment.setArguments(bundle);
        return detalleFragment;

    }

    public DetalleFragment() {
        // Required empty public constructor
        imageViewPintura = view.findViewById(R.id.imageViewPintura);
        textViewNombrePintura = view.findViewById(R.id.textViewNombreDeLaPintura);
        textViewNombreArtista= view.findViewById(R.id.textViewNombreDelArtista);
        textViewInfluencias= view.findViewById(R.id.textViewInfluencias);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle, container, false);
        Bundle bundle = getArguments();
        final Artist artist = (Artist) bundle.getSerializable(ARTISTA);
        final Pintura pintura = (Pintura) bundle.getSerializable(PINTURA);





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
    }

}
/*
 Artist artistEnPosicion = null;
 for (Artist artist : artistList) {
         if (artist.getArtistId() == pinturaEnPosicion.getArtistId()){
         artistEnPosicion = artist;
         }
         }*/

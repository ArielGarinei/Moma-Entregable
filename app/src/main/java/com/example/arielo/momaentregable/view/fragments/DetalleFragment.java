package com.example.arielo.momaentregable.view.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.model.Pintura;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
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
        textViewNombrePintura.setText(pintura.getName());
        textViewNombreArtista.setText(artist.getName());
        textViewInfluencias.setText(artist.getInfluencedBy());
        downloadFacha(pintura.getImage());






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
    public void downloadFacha(String child) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child(child);

        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
            final File finalLocalFile = localFile;
            imageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Local temp file has been created
                    Bitmap bitmapDeImagen = BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                    imageViewPintura.setImageBitmap(bitmapDeImagen);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle any errors
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
 Artist artistEnPosicion = null;
 for (Artist artist : artistList) {
         if (artist.getArtistId() == pinturaEnPosicion.getArtistId()){
         artistEnPosicion = artist;
         }
         }*/

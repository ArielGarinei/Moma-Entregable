package com.example.arielo.momaentregable.view.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Artist;
import com.example.arielo.momaentregable.model.Pintura;
import com.firebase.ui.storage.images.FirebaseImageLoader;
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
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class DetalleFragment extends Fragment {
    View view;
    List<Artist> artistList;
    ImageView imageViewPintura;
    ImageView imageViewFoto;
    TextView textViewNombrePintura;
    TextView textViewNombreArtista;
    TextView textViewInfluencias;
    TextView textViewNacionalidad;
    FloatingActionButton buttonFoto;
    FloatingActionButton buttonUpload;
    ProgressBar progressBar;


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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_detalle, container, false);
    /*    progressBar.setVisibility(View.VISIBLE);
        imageViewFoto.setVisibility(View.GONE);
        imageViewPintura.setVisibility(View.GONE);
        textViewNacionalidad.setVisibility(View.GONE);
        textViewInfluencias.setVisibility(View.GONE);
        textViewNombreArtista.setVisibility(View.GONE);
        textViewNombrePintura.setVisibility(View.GONE);*/
        imageViewPintura = view.findViewById(R.id.imageViewPintura);
        textViewNombrePintura = view.findViewById(R.id.textViewNombreDeLaPintura);
        textViewNombreArtista= view.findViewById(R.id.textViewNombreDelArtista);
        textViewInfluencias= view.findViewById(R.id.textViewInfluencias);
        textViewNacionalidad= view.findViewById(R.id.textViewNacionalidadDelArtista);
        imageViewFoto = view.findViewById(R.id.imageViewFoto);
        buttonFoto = view.findViewById(R.id.botonCamara);
        buttonUpload = view.findViewById(R.id.botonUpload);
        Bundle bundle = getArguments();
        final Artist artist = (Artist) bundle.getSerializable(ARTISTA);
        final Pintura pintura = (Pintura) bundle.getSerializable(PINTURA);
        textViewNombrePintura.setText(pintura.getName());
        textViewNombreArtista.setText(artist.getName());
        textViewNacionalidad.setText(artist.getNationality());
        textViewInfluencias.setText(artist.getInfluencedBy());
        downloadImagenFirebaseUI(imageViewPintura,pintura);
        buttonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeFacha();
            }
        });
        buttonUpload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uploadFacha();
    }
});

        return view;
    }

    public void downloadImagenFirebaseUI(ImageView imageView, Pintura pintura){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child(pintura.getImage());
        Glide.with(getContext()).using(new FirebaseImageLoader()).load(imageRef).into(imageView);
    }

    public void uploadFacha() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference imageRef = storageRef.child("fotosUsuarios");

        imageViewFoto.setDrawingCacheEnabled(true);
        imageViewFoto.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageViewFoto.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(getContext(), "Upload Failure", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                Toast.makeText(getContext(), "Upload Succes", Toast.LENGTH_SHORT).show();

                // ...
            }
        });
    }

    public void takeFacha() {
        EasyImage.openCamera(this, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, getActivity(), new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
                //Some error handling
            }

            @Override
            public void onImagesPicked(List<File> imagesFiles, EasyImage.ImageSource source, int type) {
                //Handle the images
                File imageFile = imagesFiles.get(0);
                Bitmap bitmapDeImagen = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageViewFoto.setImageBitmap(bitmapDeImagen);


            }
        });
    }

    public void progresBar(){
            progressBar.setVisibility(View.GONE);
            imageViewFoto.setVisibility(View.VISIBLE);
            imageViewPintura.setVisibility(View.VISIBLE);
            textViewNacionalidad.setVisibility(View.VISIBLE);
            textViewInfluencias.setVisibility(View.VISIBLE);
            textViewNombreArtista.setVisibility(View.VISIBLE);
            textViewNombrePintura.setVisibility(View.VISIBLE);
    }
}


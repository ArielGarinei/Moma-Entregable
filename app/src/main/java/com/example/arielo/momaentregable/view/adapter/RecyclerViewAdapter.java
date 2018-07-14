package com.example.arielo.momaentregable.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Pintura;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter{
    private List<Pintura> pinturaList;
    private Context context;
    private EscuchadorDePinturas escuchadorDePinturas;

    public RecyclerViewAdapter(List<Pintura> pinturaList,EscuchadorDePinturas escuchadorDePinturas) {
        this.pinturaList = pinturaList;
        this.escuchadorDePinturas = escuchadorDePinturas;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater  layoutInflater = LayoutInflater.from(context);
        View celda = layoutInflater.inflate(R.layout.celda_pintura,parent,false);
        ViewHolderPintura viewHolderPintura = new ViewHolderPintura(celda);
        return viewHolderPintura;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pintura pinturaEnPosicion = pinturaList.get(position);
        ViewHolderPintura viewHolderPintura = (ViewHolderPintura) holder;
        viewHolderPintura.bindPintura(pinturaEnPosicion);

    }

    @Override
    public int getItemCount() {
        return pinturaList.size();
    }

    private class ViewHolderPintura extends RecyclerView.ViewHolder{
        ImageView imageViewImagePinturaCelda;
        TextView textViewArtistaPintura;
        TextView textViewTituloPintura;
        View view;
        public ViewHolderPintura(View itemView) {
            super(itemView);
            this.view = itemView;
            imageViewImagePinturaCelda = itemView.findViewById(R.id.imageViewPinturaCelda);
           // textViewArtistaPintura = itemView.findViewById(R.id.textViewArtistaPintura);
            textViewTituloPintura = itemView.findViewById(R.id.textViewNombreDeLaPintura);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicionSeleccionada = getAdapterPosition();
                    escuchadorDePinturas.seleccionaronLaPintura(posicionSeleccionada);

                }
            });
        }

        public void bindPintura(Pintura pinturaEnPosicion) {


            //textViewTituloPintura.setText(pinturaEnPosicion.getName());
            //textViewTituloPintura.setText(pinturaEnPosicion.getName());
            textViewTituloPintura.setText(pinturaEnPosicion.getName());
            downloadImagenFirebaseUI(imageViewImagePinturaCelda,pinturaEnPosicion);

        }

    }

    public interface EscuchadorDePinturas{
        public void seleccionaronLaPintura(int posicion);
    }
    public void downloadImagenFirebaseUI(ImageView imageView, Pintura pintura){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        StorageReference imageRef = storageReference.child(pintura.getImage());
        Glide.with(context).using(new FirebaseImageLoader()).load(imageRef).into(imageView);
    }
}

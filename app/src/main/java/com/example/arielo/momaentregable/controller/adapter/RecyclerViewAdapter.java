package com.example.arielo.momaentregable.controller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.Pintura;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Arielo on 17/6/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter{
    private List<Pintura> pinturaList;

    public RecyclerViewAdapter(List<Pintura> pinturaList) {
        this.pinturaList = pinturaList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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
        ImageView textViewImagePintura;
        TextView textViewArtistaPintura;
        TextView textViewTituloPintura;

        public ViewHolderPintura(View itemView) {
            super(itemView);
            //textViewImagePintura = itemView.findViewById(R.id.textViewImagePintura);
            //textViewArtistaPintura = itemView.findViewById(R.id.textViewArtistaPintura);
            textViewTituloPintura = itemView.findViewById(R.id.textViewNombreDeLaPintura);
        }

        public void bindPintura(Pintura pinturaEnPosicion){
            //textViewTituloPintura.setText(pinturaEnPosicion.getName());
            //textViewTituloPintura.setText(pinturaEnPosicion.getName());
            textViewTituloPintura.setText(pinturaEnPosicion.getName());
        }
    }
}

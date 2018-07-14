package com.example.arielo.momaentregable.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arielo.momaentregable.R;

/**
 * Created by Arielo on 11/7/2018.
 */

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView nombreUsuarioEnviado;
    private TextView mensajeUsuarioEnviado;
    private TextView horaUsuarioEnviado;
    private ImageView fotoMensajePerfilUsuarioEnviado;
    private ImageView fotoMensajeUsuarioEnviado;

    public HolderMensaje(View itemView) {
        super(itemView);
        nombreUsuarioEnviado = itemView.findViewById(R.id.textViewNombreUsuario);
        mensajeUsuarioEnviado = itemView.findViewById(R.id.textViewMensaje);
        horaUsuarioEnviado = itemView.findViewById(R.id.textViewHora);
        fotoMensajePerfilUsuarioEnviado = itemView.findViewById(R.id.imageViewFoto);
        fotoMensajeUsuarioEnviado = itemView.findViewById(R.id.imageViewMensaje);
    }

    public TextView getNombreUsuarioEnviado() {
        return nombreUsuarioEnviado;
    }

    public void setNombreUsuarioEnviado(TextView nombreUsuarioEnviado) {
        this.nombreUsuarioEnviado = nombreUsuarioEnviado;
    }

    public TextView getMensajeUsuarioEnviado() {
        return mensajeUsuarioEnviado;
    }

    public void setMensajeUsuarioEnviado(TextView mensajeUsuarioEnviado) {
        this.mensajeUsuarioEnviado = mensajeUsuarioEnviado;
    }

    public TextView getHoraUsuarioEnviado() {
        return horaUsuarioEnviado;
    }

    public void setHoraUsuarioEnviado(TextView horaUsuarioEnviado) {
        this.horaUsuarioEnviado = horaUsuarioEnviado;
    }

    public ImageView getFotoMensajePerfilUsuarioEnviado() {
        return fotoMensajePerfilUsuarioEnviado;
    }

    public void setFotoMensajePerfilUsuarioEnviado(ImageView fotoMensajePerfilUsuarioEnviado) {
        this.fotoMensajePerfilUsuarioEnviado = fotoMensajePerfilUsuarioEnviado;
    }

    public ImageView getFotoMensajeUsuarioEnviado() {
        return fotoMensajeUsuarioEnviado;
    }

    public void setFotoMensajeUsuarioEnviado(ImageView fotoMensajeUsuarioEnviado) {
        this.fotoMensajeUsuarioEnviado = fotoMensajeUsuarioEnviado;
    }
}


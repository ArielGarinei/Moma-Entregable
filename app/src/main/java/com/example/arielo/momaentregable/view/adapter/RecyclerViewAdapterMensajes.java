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
import com.example.arielo.momaentregable.model.pojo.MessageReceive;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Arielo on 11/7/2018.
 */

public class RecyclerViewAdapterMensajes extends RecyclerView.Adapter {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser usuarioActual;
    private List<MessageReceive> listMensaje = new ArrayList<>();
    private Context context;
    private static final int ENVIADO = 0;
    private static final int RECIBIDO = 1;


    public RecyclerViewAdapterMensajes(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        usuarioActual = firebaseAuth.getCurrentUser();
    }

    public void addMensaje(MessageReceive mensajeRecibir){
        listMensaje.add(mensajeRecibir);
        notifyItemInserted(listMensaje.size());
    }


    @Override
    public int getItemViewType(int position) {
        if (listMensaje.get(position).getEmisor().equals(usuarioActual.getUid())){
            return R.layout.celda_chat;
        }else {
            return R.layout.celda_chat2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder  holderMensaje = null;
        View celda;
        switch (viewType){
            case R.layout.celda_chat:
                celda = LayoutInflater.from(context).inflate(R.layout.celda_chat,parent,false);
                holderMensaje  = new HolderMensajeEviado(celda);
                break;
            case R.layout.celda_chat2:
                View celda2 = LayoutInflater.from(context).inflate(R.layout.celda_chat2,parent,false);
                holderMensaje  = new HolderMensajeRecibido(celda2);
                break;
        }
        return holderMensaje;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderMensajeEviado){
            ((HolderMensajeEviado) holder).getNombreUsuario().setText(listMensaje.get(position).getNombre());
            ((HolderMensajeEviado) holder).getMensajeUsuario().setText(listMensaje.get(position).getMensaje());
            if(listMensaje.get(position).getType_mensaje().equals("2")){
                ((HolderMensajeEviado) holder).getFotoMensajeUsuario().setVisibility(View.VISIBLE);
                ((HolderMensajeEviado) holder).getMensajeUsuario().setVisibility(View.VISIBLE);
                Glide.with(context).load(listMensaje.get(position).getUrlFoto()).into(((HolderMensajeEviado) holder).getFotoMensajeUsuario());
            }else if(listMensaje.get(position).getType_mensaje().equals("1")){
                ((HolderMensajeEviado) holder).getFotoMensajeUsuario().setVisibility(View.GONE);
                ((HolderMensajeEviado) holder).getMensajeUsuario().setVisibility(View.VISIBLE);
            }
            if(listMensaje.get(position).getFotoPerfil()== null){
                ((HolderMensajeEviado) holder).getFotoMensajePerfilUsuario().setImageResource(R.mipmap.ic_launcher);
            }else{
                Glide.with(context).load(listMensaje.get(position).getFotoPerfil()).into(((HolderMensajeEviado) holder).getFotoMensajePerfilUsuario());
            }
            Long codigoHora = listMensaje.get(position).getHora();
            Date date = new Date(codigoHora);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            ((HolderMensajeEviado) holder).getHoraUsuario().setText(simpleDateFormat.format(date));
        }else if (holder instanceof HolderMensajeRecibido){
            ((HolderMensajeRecibido) holder).getNombreUsuario().setText(listMensaje.get(position).getNombre());
            ((HolderMensajeRecibido) holder).getMensajeUsuario().setText(listMensaje.get(position).getMensaje());
            if(listMensaje.get(position).getType_mensaje().equals("2")){
                ((HolderMensajeRecibido) holder).getFotoMensajeUsuario().setVisibility(View.VISIBLE);
                ((HolderMensajeRecibido) holder).getMensajeUsuario().setVisibility(View.VISIBLE);
                Glide.with(context).load(listMensaje.get(position).getUrlFoto()).into(((HolderMensajeRecibido) holder).getFotoMensajeUsuario());
            }else if(listMensaje.get(position).getType_mensaje().equals("1")){
                ((HolderMensajeRecibido) holder).getFotoMensajeUsuario().setVisibility(View.GONE);
                ((HolderMensajeRecibido) holder).getMensajeUsuario().setVisibility(View.VISIBLE);
            }
            if(listMensaje.get(position).getFotoPerfil()==null){
                ((HolderMensajeRecibido) holder).getFotoMensajePerfilUsuario().setImageResource(R.mipmap.ic_launcher);
            }else{
                Glide.with(context).load(listMensaje.get(position).getFotoPerfil()).into(((HolderMensajeRecibido) holder).getFotoMensajePerfilUsuario());
            }
            Long codigoHora = listMensaje.get(position).getHora();
            Date date = new Date(codigoHora);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            ((HolderMensajeRecibido) holder).getHoraUsuario().setText(simpleDateFormat.format(date));

        }

    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

    private class HolderMensajeEviado extends RecyclerView.ViewHolder{
        private TextView nombreUsuario;
        private TextView mensajeUsuario;
        private TextView horaUsuario;
        private ImageView fotoMensajePerfilUsuario;
        private ImageView fotoMensajeUsuario;

        public HolderMensajeEviado(View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.textViewNombreUsuario);
            mensajeUsuario = itemView.findViewById(R.id.textViewMensaje);
            horaUsuario = itemView.findViewById(R.id.textViewHora);
            fotoMensajePerfilUsuario = itemView.findViewById(R.id.imageViewFoto);
            fotoMensajeUsuario = itemView.findViewById(R.id.imageViewMensaje);
        }

        public TextView getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(TextView nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public TextView getMensajeUsuario() {
            return mensajeUsuario;
        }

        public void setMensajeUsuario(TextView mensajeUsuario) {
            this.mensajeUsuario = mensajeUsuario;
        }

        public TextView getHoraUsuario() {
            return horaUsuario;
        }

        public void setHoraUsuario(TextView horaUsuario) {
            this.horaUsuario = horaUsuario;
        }

        public ImageView getFotoMensajePerfilUsuario() {
            return fotoMensajePerfilUsuario;
        }

        public void setFotoMensajePerfilUsuario(ImageView fotoMensajePerfilUsuario) {
            this.fotoMensajePerfilUsuario = fotoMensajePerfilUsuario;
        }

        public ImageView getFotoMensajeUsuario() {
            return fotoMensajeUsuario;
        }

        public void setFotoMensajeUsuario(ImageView fotoMensajeUsuario) {
            this.fotoMensajeUsuario = fotoMensajeUsuario;
        }
    }
    private class HolderMensajeRecibido extends RecyclerView.ViewHolder {
        private TextView nombreUsuario;
        private TextView mensajeUsuario;
        private TextView horaUsuario;
        private ImageView fotoMensajePerfilUsuario;
        private ImageView fotoMensajeUsuario;

        public HolderMensajeRecibido(View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.textViewNombreUsuarioRecibido);
            mensajeUsuario = itemView.findViewById(R.id.textViewMensajeRecibido);
            horaUsuario = itemView.findViewById(R.id.textViewHoraRecibido);
            fotoMensajePerfilUsuario = itemView.findViewById(R.id.imageViewFotoRecibido);
            fotoMensajeUsuario = itemView.findViewById(R.id.imageViewMensajeFotoRecibido);
        }

        public TextView getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(TextView nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public TextView getMensajeUsuario() {
            return mensajeUsuario;
        }

        public void setMensajeUsuario(TextView mensajeUsuario) {
            this.mensajeUsuario = mensajeUsuario;
        }

        public TextView getHoraUsuario() {
            return horaUsuario;
        }

        public void setHoraUsuario(TextView horaUsuario) {
            this.horaUsuario = horaUsuario;
        }

        public ImageView getFotoMensajePerfilUsuario() {
            return fotoMensajePerfilUsuario;
        }

        public void setFotoMensajePerfilUsuario(ImageView fotoMensajePerfilUsuario) {
            this.fotoMensajePerfilUsuario = fotoMensajePerfilUsuario;
        }

        public ImageView getFotoMensajeUsuario() {
            return fotoMensajeUsuario;
        }

        public void setFotoMensajeUsuario(ImageView fotoMensajeUsuario) {
            this.fotoMensajeUsuario = fotoMensajeUsuario;
        }
    }
}







/*



        holder.getNombreUsuario().setText(listMensaje.get(position).getName());
                holder.getMensajeUsuario().setText(listMensaje.get(position).getMensaje());
                if(listMensaje.get(position).getType_mensaje().equals("2")){
                holder.getFotoMensajeUsuario().setVisibility(View.VISIBLE);
                holder.getMensajeUsuario().setVisibility(View.VISIBLE);
                Glide.with(context).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensajeUsuario());
                }else if(listMensaje.get(position).getType_mensaje().equals("1")){
                holder.getFotoMensajeUsuario().setVisibility(View.GONE);
                holder.getMensajeUsuario().setVisibility(View.VISIBLE);
                }
                if(listMensaje.get(position).getFotoPerfil().isEmpty()){
                holder.getFotoMensajePerfilUsuario().setImageResource(R.mipmap.ic_launcher);
                }else{
                Glide.with(context).load(listMensaje.get(position).getFotoPerfil()).into(holder.getFotoMensajePerfilUsuario());
                }
                Long codigoHora = listMensaje.get(position).getHora();
                Date date = new Date(codigoHora);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
                holder.getHoraUsuario().setText(simpleDateFormat.format(date));*/

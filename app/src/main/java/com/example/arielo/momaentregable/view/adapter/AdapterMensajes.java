package com.example.arielo.momaentregable.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.arielo.momaentregable.R;
import com.example.arielo.momaentregable.model.MensajeRecibir;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Arielo on 11/7/2018.
 */

public class AdapterMensajes extends RecyclerView.Adapter<HolderMensaje> {

    private List<MensajeRecibir> listMensaje = new ArrayList<>();
    private Context context;

    public AdapterMensajes(Context context) {
        this.context = context;
    }

    public void addMensaje(MensajeRecibir mensajeRecibir){
        listMensaje.add(mensajeRecibir);
        notifyItemInserted(listMensaje.size());
    }

    @Override
    public HolderMensaje onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.celda_chat,parent,false);
        return new HolderMensaje(view);
    }

    @Override
    public void onBindViewHolder(HolderMensaje holder, int position) {
        holder.getNombreUsuarioEnviado().setText(listMensaje.get(position).getNombre());
        holder.getMensajeUsuarioEnviado().setText(listMensaje.get(position).getMensaje());
        if(listMensaje.get(position).getType_mensaje().equals("2")){
            holder.getFotoMensajeUsuarioEnviado().setVisibility(View.VISIBLE);
            holder.getMensajeUsuarioEnviado().setVisibility(View.VISIBLE);
            Glide.with(context).load(listMensaje.get(position).getUrlFoto()).into(holder.getFotoMensajeUsuarioEnviado());
        }else if(listMensaje.get(position).getType_mensaje().equals("1")){
            holder.getFotoMensajeUsuarioEnviado().setVisibility(View.GONE);
            holder.getMensajeUsuarioEnviado().setVisibility(View.VISIBLE);
        }
        if(listMensaje.get(position).getFotoPerfil().isEmpty()){
            holder.getFotoMensajePerfilUsuarioEnviado().setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context).load(listMensaje.get(position).getFotoPerfil()).into(holder.getFotoMensajePerfilUsuarioEnviado());
        }
        Long codigoHora = listMensaje.get(position).getHora();
        Date date = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");//a pm o am
        holder.getHoraUsuarioEnviado().setText(sdf.format(date));
    }

    @Override
    public int getItemCount() {
        return listMensaje.size();
    }

}

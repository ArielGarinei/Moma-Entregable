package com.example.arielo.momaentregable.model.pojo;

import java.util.Map;

/**
 * Created by Arielo on 11/7/2018.
 */

public class MessageSend extends Message {
    private Map hora;

    public MessageSend() {
    }

    public MessageSend(Map hora) {
        this.hora = hora;
    }

    public MessageSend(String mensaje, String nombre, String fotoPerfil, String type_mensaje, String tipoMensaje, Map hora) {
        super(mensaje, nombre, fotoPerfil, type_mensaje, tipoMensaje);
        this.hora = hora;
    }

    public MessageSend(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, String tipoMensaje, Map hora) {
        super(mensaje, urlFoto, nombre, fotoPerfil, type_mensaje, tipoMensaje);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }
}

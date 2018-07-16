package com.example.arielo.momaentregable.model.pojo;

/**
 * Created by Arielo on 11/7/2018.
 */

public class MessageReceive extends Message {
    private Long hora;

    public MessageReceive() {
    }

    public MessageReceive(Long hora) {
        this.hora = hora;
    }

    public MessageReceive(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, String tipoMensaje, Long hora) {
        super(mensaje, urlFoto, nombre, fotoPerfil, type_mensaje,tipoMensaje);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}

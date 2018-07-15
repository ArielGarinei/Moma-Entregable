package com.example.arielo.momaentregable.model;

/**
 * Created by Arielo on 11/7/2018.
 */

public class MensajeRecibir extends Mensaje {
    private Long hora;

    public MensajeRecibir() {
    }

    public MensajeRecibir(Long hora) {
        this.hora = hora;
    }

    public MensajeRecibir(String mensaje, String urlFoto, String nombre, String fotoPerfil, String type_mensaje, String tipoMensaje, Long hora) {
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

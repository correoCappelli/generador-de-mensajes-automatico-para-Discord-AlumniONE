package com.alumnione.mensajeautodiscord.modelos;

import java.time.LocalDateTime;

public record DatosListadoMensaje(
    Long id,
    String texto,
    LocalDateTime fechaYHora

) {
    public DatosListadoMensaje(Mensaje mensaje) {
        this(
        mensaje.getId(),
        mensaje.getTexto(),
        mensaje.getFechaYHora()
        );
    }
}

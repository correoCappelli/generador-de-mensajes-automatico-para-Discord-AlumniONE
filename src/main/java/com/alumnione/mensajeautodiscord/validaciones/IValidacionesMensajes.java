package com.alumnione.mensajeautodiscord.validaciones;

import com.alumnione.mensajeautodiscord.modelos.DatosRegistroMensaje;
import com.alumnione.mensajeautodiscord.modelos.Mensaje;

import java.time.LocalDateTime;

public interface IValidacionesMensajes {
    void validar(DatosRegistroMensaje mensaje);
}

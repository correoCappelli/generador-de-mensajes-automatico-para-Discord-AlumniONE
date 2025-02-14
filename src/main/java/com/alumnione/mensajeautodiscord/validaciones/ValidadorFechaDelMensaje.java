package com.alumnione.mensajeautodiscord.validaciones;

import com.alumnione.mensajeautodiscord.modelos.DatosRegistroMensaje;
import com.alumnione.mensajeautodiscord.modelos.Mensaje;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ValidadorFechaDelMensaje implements IValidacionesMensajes {


    @Override
    public void validar(DatosRegistroMensaje mensaje) {
        if(!(mensaje.fechaYHora() instanceof LocalDateTime)){
            throw new ValidacionExcepcion("revisar fecha y hora ingresada");
        }
    }
}

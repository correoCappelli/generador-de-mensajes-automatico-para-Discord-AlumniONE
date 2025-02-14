package com.alumnione.mensajeautodiscord.validaciones;

import com.alumnione.mensajeautodiscord.modelos.DatosRegistroMensaje;
import org.springframework.stereotype.Component;

@Component
public class ValidadoTextoDelMensaje implements IValidacionesMensajes{
    @Override
    public void validar(DatosRegistroMensaje mensaje) {
        String textoMensaje= mensaje.texto();
        if(textoMensaje.length()>300){
            throw new ValidacionExcepcion("no se pueden superar los 300 chars");
        }

    }
}

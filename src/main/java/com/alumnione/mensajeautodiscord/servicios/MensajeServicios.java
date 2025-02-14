package com.alumnione.mensajeautodiscord.servicios;

import com.alumnione.mensajeautodiscord.modelos.Mensaje;
import com.alumnione.mensajeautodiscord.repositorios.MensajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MensajeServicios {
    @Autowired
    MensajeRepositorio mensajeRepositorio;

    public Mensaje guardarMensaje(Mensaje mensaje) {
        return mensajeRepositorio.save(mensaje);
    }

    public List<Mensaje> listarMensajes() {
        return mensajeRepositorio.findAll();
    }

    public void borrarMensaje(Long id) {
        mensajeRepositorio.deleteById(id);
    }

    public List<Mensaje> listarProximosCincoMensajesMensajes() {
        LocalDateTime ahora=LocalDateTime.now();
        LocalDateTime ahoraMasCincoMinutos=ahora.plusMinutes(5);
        List<Mensaje> mensajes=mensajeRepositorio.findByFechaYHoraBetween(ahora,ahoraMasCincoMinutos);
        return mensajes;
    }
}

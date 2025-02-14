package com.alumnione.mensajeautodiscord.controladores;

import com.alumnione.mensajeautodiscord.modelos.DatosListadoMensaje;
import com.alumnione.mensajeautodiscord.modelos.DatosRegistroMensaje;
import com.alumnione.mensajeautodiscord.modelos.Mensaje;
import com.alumnione.mensajeautodiscord.repositorios.MensajeRepositorio;
import com.alumnione.mensajeautodiscord.servicios.MensajeServicios;
import com.alumnione.mensajeautodiscord.validaciones.IValidacionesMensajes;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MensajeControlador {
    @Autowired
    private MensajeServicios mensajeServicios;
    @Autowired
    private MensajeRepositorio mensajeRepositorio;
    @Autowired
    private List<IValidacionesMensajes> validacionesMensajes;


    @PostMapping("/mensajes")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity crearMensaje(@RequestBody @Valid DatosRegistroMensaje mensaje) {

        validacionesMensajes.forEach(v -> v.validar(mensaje));

        Mensaje nuevoMensaje = new Mensaje(mensaje);
        return ResponseEntity.ok(mensajeServicios.guardarMensaje(nuevoMensaje));
    }

    @GetMapping("/mensajes")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity<List<DatosListadoMensaje>> listarMensajes() {
        return ResponseEntity.ok(mensajeServicios.listarMensajes().stream()
                .map(DatosListadoMensaje::new)
                .toList());
    }

    @DeleteMapping("/mensajes/{id}")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity<Void> borrarMensaje(@PathVariable Long id) {
        Mensaje mensajeABorrar = mensajeRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
        mensajeServicios.borrarMensaje(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/panelweb")
    @CrossOrigin(origins = "http://localhost:63342")
    public ResponseEntity<List<DatosListadoMensaje>> listarProximosCincoMensajes() {
        return ResponseEntity.ok(mensajeServicios.listarProximosCincoMensajesMensajes().stream()
                .map(DatosListadoMensaje::new)
                .toList());
    }

}

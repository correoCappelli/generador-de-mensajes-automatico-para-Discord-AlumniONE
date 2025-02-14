package com.alumnione.mensajeautodiscord.repositorios;

import com.alumnione.mensajeautodiscord.modelos.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MensajeRepositorio extends JpaRepository<Mensaje,Long> {
    List<Mensaje> findAllByFechaYHora(LocalDateTime now);

    List<Mensaje> findByFechaYHoraBetween(LocalDateTime ahora, LocalDateTime ahoraMasCincoMinutos);
}

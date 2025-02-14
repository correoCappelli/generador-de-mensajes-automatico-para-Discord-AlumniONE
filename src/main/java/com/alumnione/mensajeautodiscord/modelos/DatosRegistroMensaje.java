package com.alumnione.mensajeautodiscord.modelos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record DatosRegistroMensaje(
        @NotBlank
        String texto,
        LocalDateTime fechaYHora
) {
}

package com.alumnione.mensajeautodiscord.modelos;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@Getter
@Setter
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private LocalDateTime fechaYHora;

    public Mensaje(DatosRegistroMensaje mensaje) {
        this.texto = mensaje.texto();
        this.fechaYHora = mensaje.fechaYHora();
    }

    public String getTexto() {
        return texto;
    }
}

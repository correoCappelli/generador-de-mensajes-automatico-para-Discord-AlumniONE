package com.alumnione.mensajeautodiscord.validaciones;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String message) {
      super(message);
    }
}

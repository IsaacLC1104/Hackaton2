package com.agenda.excepciones;

public class DatosInvalidosException extends ExcepcionAgenda {
    public DatosInvalidosException(String mensaje) {
        super("❌ Error de validación: " + mensaje);
    }
}
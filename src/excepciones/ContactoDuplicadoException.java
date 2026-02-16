package com.agenda.excepciones;

public class ContactoDuplicadoException extends ExcepcionAgenda {


    public ContactoDuplicadoException(String nombre) {
        super("❌ Error: El contacto '" + nombre + "' ya existe");
    }
}
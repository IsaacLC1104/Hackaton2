package com.agenda.excepciones;

public class ContactoNoEncontradoException extends ExcepcionAgenda {


    public ContactoNoEncontradoException(String nombre) {
        super("❌ Error: El contacto '" + nombre + "' no existe");
    }
}
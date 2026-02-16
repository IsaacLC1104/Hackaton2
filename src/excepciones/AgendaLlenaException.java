package com.agenda.excepciones;

public class AgendaLlenaException extends ExcepcionAgenda {
    public AgendaLlenaException() {

        super("❌ Error: La agenda está llena");
    }
}
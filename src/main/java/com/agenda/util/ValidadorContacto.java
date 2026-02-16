package com.agenda.util;

import com.agenda.modelo.Contacto;

public class ValidadorContacto {
    private Contacto[] contactos;
    private int numeroContactos;
    private static final int TAMANIO_POR_DEFECTO = 10;

    /**
     * Constructor con tamaño personalizado
     */
    public Agenda(int tamanio) {
        if (tamanio <= 0) {
            tamanio = TAMANIO_POR_DEFECTO;
        }
        this.contactos = new Contacto[tamanio];
        this.numeroContactos = 0;
    }

    /**
     * Constructor con tamaño por defecto (10)
     */
    public Agenda() {
        this(TAMANIO_POR_DEFECTO);
    }

    /**
     * Añade un contacto a la agenda
     */
    public void anadirContacto(Contacto contacto) throws ExcepcionAgenda {
        // Validar datos
        ValidadorContacto.validarNombre(contacto.getNombre());
        ValidadorContacto.validarTelefono(contacto.getTelefono());

        // Normalizar datos
        contacto.setNombre(ValidadorContacto.normalizarNombre(contacto.getNombre()));
        contacto.setTelefono(ValidadorContacto.normalizarTelefono(contacto.getTelefono()));

        // Verificar si está llena
        if (agendaLlena()) {
            throw new AgendaLlenaException();
        }

        // Verificar si ya existe
        if (existeContacto(contacto)) {
            throw new ContactoDuplicadoException(contacto.getNombre());
        }

        // Añadir contacto
        contactos[numeroContactos] = contacto;
        numeroContactos++;
    }

    /**
     * Verifica si existe un contacto
     */
    public boolean existeContacto(Contacto contacto) {
        for (int i = 0; i < numeroContactos; i++) {
            if (contactos[i].equals(contacto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si la agenda está llena
     */
    public boolean agendaLlena() {
        return numeroContactos >= contactos.length;
    }

    /**
     * Retorna el número de huecos libres
     */
    public int huecosLibres() {
        return contactos.length - numeroContactos;
    }

    public void listarContactos() {
        if (numeroContactos == 0) {
            System.out.println("\n📭 La agenda está vacía");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("📖 LISTA DE CONTACTOS (" + numeroContactos + "/" + contactos.length + ")");
        System.out.println("=".repeat(50));
        System.out.printf("%-20s | %s%n", "NOMBRE", "TELÉFONO");
        System.out.println("-".repeat(50));

        for (int i = 0; i < numeroContactos; i++) {
            System.out.println((i + 1) + ". " + contactos[i]);
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Busca un contacto por nombre
     */
    public Contacto buscarContacto(String nombre) throws ContactoNoEncontradoException {
        for (int i = 0; i < numeroContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return contactos[i];
            }
        }
        throw new ContactoNoEncontradoException(nombre);
    }
}
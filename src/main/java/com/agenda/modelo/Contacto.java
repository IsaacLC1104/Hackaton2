package com.agenda.modelo;

public class Contacto {
    public void eliminarContacto(String nombre) throws ContactoNoEncontradoException {
        int indice = -1;

        // Buscar el índice del contacto
        for (int i = 0; i < numeroContactos; i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            throw new ContactoNoEncontradoException(nombre);
        }

        // Desplazar elementos hacia la izquierda
        for (int i = indice; i < numeroContactos - 1; i++) {
            contactos[i] = contactos[i + 1];
        }

        contactos[numeroContactos - 1] = null;
        numeroContactos--;
    }

    /**
     * Retorna el número de contactos actual
     */
    public int getNumeroContactos() {
        return numeroContactos;
    }

    /**
     * Retorna la capacidad total
     */
    public int getCapacidadTotal() {
        return contactos.length;
    }

}

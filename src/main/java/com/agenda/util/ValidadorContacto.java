package com.agenda.util;

import com.agenda.excepciones.DatosInvalidosException;
import com.agenda.excepciones.ExcepcionAgenda;

public class ValidadorContacto {
    private static final int MIN_LONGITUD_NOMBRE = 2;
    private static final int MAX_LONGITUD_NOMBRE = 50;
    private static final int LONGITUD_TELEFONO = 10;

    /**
     * Valida el nombre del contacto
     */
    public static void validarNombre(String nombre) throws DatosInvalidosException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío");
        }

        String nombreLimpio = nombre.trim();

        if (nombreLimpio.length() < MIN_LONGITUD_NOMBRE) {
            throw new DatosInvalidosException("El nombre debe tener al menos " + MIN_LONGITUD_NOMBRE + " caracteres");
        }

        if (nombreLimpio.length() > MAX_LONGITUD_NOMBRE) {
            throw new DatosInvalidosException("El nombre no puede tener más de " + MAX_LONGITUD_NOMBRE + " caracteres");
        }

        if (!nombreLimpio.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            throw new DatosInvalidosException("El nombre solo puede contener letras y espacios");
        }
    }

    /**
     * Valida el teléfono del contacto
     */
    public static void validarTelefono(String telefono) throws DatosInvalidosException {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new DatosInvalidosException("El teléfono no puede estar vacío");
        }

        String telefonoLimpio = telefono.replaceAll("[\\s-]", "");

        if (!telefonoLimpio.matches("^[0-9]+$")) {
            throw new DatosInvalidosException("El teléfono solo puede contener números");
        }

        if (telefonoLimpio.length() != LONGITUD_TELEFONO) {
            throw new DatosInvalidosException("El teléfono debe tener exactamente " + LONGITUD_TELEFONO + " dígitos");
        }
    }

    /**
     * Normaliza el nombre (trim y capitalización)
     */
    public static String normalizarNombre(String nombre) {
        return nombre.trim();
    }

    /**
     * Normaliza el teléfono (elimina espacios y guiones)
     */
    public static String normalizarTelefono(String telefono) {
        return telefono.replaceAll("[\\s-]", "");
    }
}
package com.agenda.vista;

import com.agenda.excepciones.ContactoNoEncontradoException;
import com.agenda.excepciones.ExcepcionAgenda;
import com.agenda.negocio.Agenda;
import com.agenda.modelo.Contacto;


import java.util.Scanner;

public class MenuConsola {
    private Agenda agenda;
    private Scanner scanner;

    public MenuConsola() {
        this.scanner = new Scanner(System.in);
        solicitarTamanioAgenda();
    }

    /**
     * Solicita el tamaño de la agenda al inicio
     */
    private void solicitarTamanioAgenda() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    📒 AGENDA DE CONTACTOS - JAVA      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("\nDesea crear agenda con tamano personalizado? (S/N): ");
        String respuesta = scanner.nextLine().trim().toUpperCase();

        if (respuesta.equals("S")) {
            System.out.print("Ingrese el tamano de la agenda: ");
            try {
                int tamanio = Integer.parseInt(scanner.nextLine().trim());
                this.agenda = new Agenda(tamanio);
                System.out.println("✅ Agenda creada con capacidad para " + tamanio + " contactos");
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Tamano invalido. Se usara tamano por defecto (10)");
                this.agenda = new Agenda();
            }
        } else {
            this.agenda = new Agenda();
            System.out.println("✅ Agenda creada con tamano por defecto (10 contactos)");
        }
    }

    /**
     * Muestra el menú principal
     */
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║           MENU PRINCIPAL              ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ 1.  Anadir contacto                 ║");
            System.out.println("║ 2.  Listar todos los contactos      ║");
            System.out.println("║ 3.  Buscar contacto                 ║");
            System.out.println("║ 4.    Eliminar contacto              ║");
            System.out.println("║ 5.    Ver espacios disponibles        ║");
            System.out.println("║ 6.     Salir                           ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("\nSeleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                ejecutarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("\n Opcion invalida. Ingrese un numero del 1 al 6");
                opcion = 0;
            }
        } while (opcion != 6);
    }

    /**
     * Ejecuta la opción seleccionada
     */
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                anadirContacto();
                break;
            case 2:
                agenda.listarContactos();
                break;
            case 3:
                buscarContacto();
                break;
            case 4:
                eliminarContacto();
                break;
            case 5:
                mostrarEspaciosDisponibles();
                break;
            case 6:
                System.out.println("\n👋 Hasta luego! Agenda cerrada.");
                break;
            default:
                System.out.println("\n Opcion invalida. Intente nuevamente.");
        }
    }

    /**
     * Añade un contacto
     */
    private void anadirContacto() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("➕ ANADIR NUEVO CONTACTO");
        System.out.println("=".repeat(50));

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Telefono (10 dígitos): ");
        String telefono = scanner.nextLine();

        try {
            Contacto nuevoContacto = new Contacto(nombre, telefono);
            agenda.anadirContacto(nuevoContacto);
            System.out.println("\n Contacto anadido exitosamente");
            System.out.println("📊 Espacios libres: " + agenda.huecosLibres());
        } catch (ExcepcionAgenda e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    /**
     * Busca un contacto
     */
    private void buscarContacto() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("BUSCAR CONTACTO");
        System.out.println("=".repeat(50));

        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();

        try {
            Contacto encontrado = agenda.buscarContacto(nombre);
            System.out.println("\nContacto encontrado:");
            System.out.println("━".repeat(50));
            System.out.printf("%-20s | %s%n", "NOMBRE", "TELEFONO");
            System.out.println("━".repeat(50));
            System.out.println(encontrado);
            System.out.println("━".repeat(50));
        } catch (ContactoNoEncontradoException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    /**
     * Elimina un contacto
     */
    private void eliminarContacto() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ELIMINAR CONTACTO");
        System.out.println("=".repeat(50));

        System.out.print("Ingrese el nombre del contacto a eliminar: ");
        String nombre = scanner.nextLine();

        try {
            agenda.eliminarContacto(nombre);
            System.out.println("\nContacto eliminado exitosamente");
            System.out.println("Espacios libres: " + agenda.huecosLibres());
        } catch (ContactoNoEncontradoException e) {
            System.out.println("\n" + e.getMessage());
        }
    }

    /**
     * Muestra espacios disponibles
     */
    private void mostrarEspaciosDisponibles() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("INFORMACION DE LA AGENDA");
        System.out.println("=".repeat(50));
        System.out.println(" Contactos registrados: " + agenda.getNumeroContactos());
        System.out.println(" Capacidad total: " + agenda.getCapacidadTotal());
        System.out.println(" Espacios libres: " + agenda.huecosLibres());

        if (agenda.agendaLlena()) {
            System.out.println("Estado: AGENDA LLENA");
        } else {
            double porcentaje = (agenda.getNumeroContactos() * 100.0) / agenda.getCapacidadTotal();
            System.out.printf("Ocupacion: %.1f%%%n", porcentaje);
        }
        System.out.println("=".repeat(50));
    }

    /**
     * Cierra el scanner
     */
    public void cerrar() {
        scanner.close();
    }
}
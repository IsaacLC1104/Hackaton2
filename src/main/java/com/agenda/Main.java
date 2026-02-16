package com.agenda;

import com.agenda.vista.MenuConsola;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MenuConsola menu = new MenuConsola();
        menu.mostrarMenu();
        menu.cerrar();

    }
}
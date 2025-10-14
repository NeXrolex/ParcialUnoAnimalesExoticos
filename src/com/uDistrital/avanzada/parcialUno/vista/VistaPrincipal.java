/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.vista;

import java.util.Scanner;


/**
 *
 * @author jeiso
 */
public class VistaPrincipal {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
}

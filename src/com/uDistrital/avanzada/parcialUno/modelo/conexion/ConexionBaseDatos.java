/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.conexion;

/**
 * Permite la conexion entre el driver y la base de datos a traves del cliente
 *
 *
 * @author jeison, Alex
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Implementa de nuestra interface IConexion

public class ConexionBaseDatos {

    //Configuraciones para el ingreso a la BD
    private static String URLBD = "jdbc:mysql://localhost:3007/mascotas";
    private static String usuario = "root";
    private static String contrasena = "";
    //Referencia para nuestra conexion
    private Connection cn;

    
}

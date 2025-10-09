/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

/**
 * Permite la conexion entre el driver y la base de datos a traves del cliente
 *
 *
 * @author jeison,Alex
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection cn = null;
    /*Direccion de la base de datos, indica que usaremos mysql
      el puerto de entrada y el nombre de la tabla en la base
     */
    private static final String URLBD = "jdbc:mysql://localhost:3370/mascotas";
    //Nuestro usuario para conectar a la base de datos
    private static String usuario = "root";
    //Contrasena para acceder
    private static String contrasena = "";

    /**
     * Metodo
     *
     * @return Objeto de tipo conexion
     */
    public static Connection getConexion() {
        try {
            //Establece la coneccion con los parametro arriba definidos
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            //Atrapa los errores que puedan surgir al conectar la base de datos
        }
        return cn; // nos retorna la coneccion
    }

    /**
     * Desconecta de la base de datos
     *
     */
    public static void desconectar() {
        cn = null;
        /*Es necesario cada vez que queramos acceder a la base de 
        datos hacer una desconexion*/
    }
}

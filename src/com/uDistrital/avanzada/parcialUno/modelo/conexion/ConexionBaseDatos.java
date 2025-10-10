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



public class ConexionBaseDatos{

    //Configuraciones para el ingreso a la Base de datos
    private static String URLBD = "jdbc:mysql://localhost:3007/mascotas";
    /*El primer parametro para establecer que estamos usando, despues el
    perto utilizado y por ultimo el nombre de la Base de datos
    */
    private static String usuario = "root";
    private static String contrasena = "";
    //Referencia para nuestra conexion
    private static Connection cn = null;

        /**
        Metodo encargado de conectar a la base de tados 
        */
        public static Connection getConexion() {
            try {
                
                //Establecemos la conexion con la base de datos
                cn = DriverManager.getConnection(URLBD, usuario, contrasena);
                //Usamos los parametros asignados anteriormente
            } catch (SQLException ex) {          
            }
            return cn;
        }

        /**
        * Metodo utulizado para desconectar la base de datos
        * Su implementacion es importante para mas adelante hacer mas 
        * consultas desde otros apartados
        */
        public static void desconectar() {
        cn = null;
    }

}

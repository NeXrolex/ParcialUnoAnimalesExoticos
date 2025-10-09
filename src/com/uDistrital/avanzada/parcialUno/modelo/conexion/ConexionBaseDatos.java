/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.conexion;

/**
 * Permite la conexion entre el driver y la base de datos a traves del cliente
 *
 *
 * @author jeiso
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.IConexion;

public class ConexionBaseDatos implements IConexion {

    private static Connection cn = null;
    //Tenemos 
    private static String URLBD = "jdbc:mysql://localhost:3007/mascotas";
    private static String usuario = "root";
    private static String contrasena = "";

    @Override
    public void conectar() throws Exception {
        try {
            cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {

        }

    }

    @Override
    public void desconectar() throws Exception {
        cn = null;
    }
}

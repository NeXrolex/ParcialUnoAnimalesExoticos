/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.DAO;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.modelo.conexion.ConexionBaseDatos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jeiso
 */
public class MascotaDAO {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public MascotaDAO() {
        con = null;
        st = null;
        rs = null;
    }
    public MascotaVO consultarMascotaApodo(String apodo) {
        MascotaVO mascota = null;
        String consulta = "SELECT * FROM mascotas where apodo='" + apodo + "'";
        try {
            con = (Connection) ConexionBaseDatos.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                mascota = new MascotaVO();
                mascota.setApodo(rs.getString("apodo"));
                mascota.setNombreComun(rs.getString("nombreComun"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setAlimentoPrincipal("alimentoPrincipal");                
            }
            st.close();
            ConexionBaseDatos.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return mascota;
    }
    public ArrayList<MascotaVO> listaDeMascotas() {
        ArrayList<MascotaVO> misMascotas = new ArrayList<MascotaVO>();
        String consulta = "SELECT * FROM estudiantes";
        try {
            con = ConexionBaseDatos.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setApodo(rs.getString("apodo"));
                mascota.setNombreComun(rs.getString("nombre"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setAlimentoPrincipal("alimentoPrincipal");
                misMascotas.add(mascota);
            }
            st.close();
            ConexionBaseDatos.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return misMascotas;
    }
    public ArrayList<MascotaVO> buscarTipo(String tipo,String nombreComun,
            String clasificacion,String familia, String genero, String especie,
             String alimento) {
        ArrayList<MascotaVO> misMascotas = new ArrayList<MascotaVO>();
        String consulta = null ;
        if(tipo != null && tipo.equalsIgnoreCase("nombre")){
        consulta = "SELECT * FROM mascotas WHERE nombre LIKE '"+ nombreComun + "%'";
        }
        if(tipo != null && tipo.equalsIgnoreCase("clasificacion")){
        consulta = "SELECT * FROM mascotas WHERE clasificacion LIKE '"+ clasificacion + "%'";
        }
        if(tipo != null && tipo.equalsIgnoreCase("familia")){
        consulta = "SELECT * FROM mascotas WHERE familia LIKE '"+ familia + "%'";
        }
        if(tipo != null && tipo.equalsIgnoreCase("genero")){
        consulta = "SELECT * FROM mascotas WHERE genero LIKE '"+ genero + "%'";
        }
        if(tipo != null && tipo.equalsIgnoreCase("clasificacion")){
        consulta = "SELECT * FROM mascotas WHERE especie LIKE '"+ especie + "%'";
        }
        if(tipo != null && tipo.equalsIgnoreCase("clasificacion")){
        consulta = "SELECT * FROM mascotas WHERE alimento LIKE '"+ alimento + "%'";
        }
        try {
            con = ConexionBaseDatos.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setApodo(rs.getString("apodo"));
                mascota.setNombreComun(rs.getString("nombre"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setAlimentoPrincipal("alimentoPrincipal");
                misMascotas.add(mascota);
            }
            st.close();
            ConexionBaseDatos.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return misMascotas;
    }
}

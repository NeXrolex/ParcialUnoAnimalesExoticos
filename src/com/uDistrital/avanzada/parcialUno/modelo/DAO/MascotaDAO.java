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
    public MascotaVO consultarMascota(String codigo) {
        MascotaVO mascota = null;
        String consulta = "SELECT * FROM estudiantes where codigo='" + codigo + "'";
        try {
            con = (Connection) ConexionBaseDatos.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                mascota = new MascotaVO();
                mascota.setApodo(rs.getString("apodo"));
                mascota.setNombreComun(rs.getString("nombre"));
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.DAO;

import com.mysql.jdbc.PreparedStatement;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.modelo.conexion.ConexionBaseDatos;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.ICreate;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.IModifier;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.IRead;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex, Jeison
 */
public class MascotaDAO implements ICreate<MascotaVO>, IRead<MascotaVO>,
        IModifier {

    //Llevan en Col al inicio porque nos referimos a columnas de datos de sql
    private static final String TBL = "mascotas"; //tabla sql(nombre de la tabla)
    private static final String colApodo = "apodo";//clave                
    private static final String colNombreComun = "nombreComun";
    private static final String colClasificacion = "clasificacion";
    private static final String colFamilia = "familia";
    private static final String colGenero = "genero";
    private static final String colEspecie = "especie";
    private static final String colAlimento = "alimentoPrincipal";

    /**
     * Inserta una nueva mascota
     *
     * @param mascota Mascota a insertar(Nuestra clave es el apodo)
     * @throws Exception Si ocurre un error de conexion
     */
    @Override
    public void insertar(MascotaVO mascota) throws Exception {

        /*
        Construimos una sentencia parametrizada aplocando los paramatros
        arriba definidos 
        
         */
        final String sql = "INSERT INTO " + TBL + " ("
                + colApodo + "," + colNombreComun + "," + colClasificacion + ","
                + colFamilia + "," + colGenero + "," + colEspecie + ","
                + colAlimento + ") VALUES (?,?,?,?,?,?,?)";

        /*
        Cierra automaticante la conexion al salir del bloque, esto
        es importante po si despies queremos acceder a la Base de datos
        
         */
        try (Connection c = ConexionBaseDatos.getConexion(); PreparedStatement 
                ps = (PreparedStatement) /*Soporte para anadir
                        parametros a sql*/ c.prepareStatement(sql)) {

            ps.setString(1, mascota.getApodo());
            ps.setString(2, mascota.getNombreComun());
            ps.setString(3, mascota.getClasificacion());
            ps.setString(4, mascota.getFamilia());
            ps.setString(5, mascota.getGenero());
            ps.setString(6, mascota.getEspecie());
            ps.setString(7, mascota.getAlimentoPrincipal());

            ps.executeUpdate();//Ejecuta el insert

        }

    }

    /**
     * Consulta a una mascota por su apodo(Clave identificadora)
     *
     * @param apodo Clave de la mascota
     * @return Mascota encontrada o null sino se encontro
     * @throws Exception Si ocurre un error sql de conexion
     */
    @Override
    public MascotaVO consultar(String apodo) throws Exception {
        
        //
        final String sql = "SELECT * FROM " + TBL + " WHERE " + colApodo + "=?";
        
        /*
        Cierra automaticante la conexion al salir del bloque, esto
        es importante po si despies queremos acceder a la Base de datos  
         */
        try (Connection c = ConexionBaseDatos.getConexion(); PreparedStatement
                ps = (PreparedStatement) c.prepareStatement(sql)) {
            
            // Establece el primer ? del sql
            ps.setString(1, apodo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null; /*verificamos si existe una mascota 
                con ese apodo*/
            }
        }
    }
    
    /**
     * Modifica los datos de una mascota existente
     * 
     * @param mascota Mascota con los nuevos valores
     * @throws Exception Si ocurre un error de conexion
     */
    @Override
    public void modificar(MascotaVO mascota) throws Exception {
        
         final String sql = "UPDATE " + TBL + " SET " +
                colNombreComun   + "=?," +
                colClasificacion + "=?," +
                colFamilia + "=?," +
                colGenero + "=?," +
                colEspecie + "=?," +
                colAlimento + "=? " +
                "WHERE " + colApodo + "=?";
         /*
        Cierra automaticante la conexion al salir del bloque, esto
        es importante po si despies queremos acceder a la Base de datos  
         */
        try (Connection c = ConexionBaseDatos.getConexion();
             PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql)) {

            ps.setString(1, mascota.getNombreComun());
            ps.setString(2, mascota.getClasificacion());
            ps.setString(3, mascota.getFamilia());
            ps.setString(4, mascota.getGenero());
            ps.setString(5, mascota.getEspecie());
            ps.setString(6, mascota.getAlimentoPrincipal());
            ps.setString(7, mascota.getApodo());

            ps.executeUpdate();
        }
    

    }
    
    /**
     * Elimina una mascota, como antes mencionado por nuestra llave Apodo
     * 
     * @param apodo Identificador de la mascota
     * @throws Exception Si ocurre un error de conexion
     */
    @Override
    public void eliminar(String apodo) throws Exception {
        //Sentencia final que funciona para eliminar de la bs
        final String sql = "DELETE FROM " + TBL + " WHERE " + colApodo + "=?";
        
        /*
        Cierra automaticante la conexion al salir del bloque, esto
        es importante po si despies queremos acceder a la Base de datos  
         */
        try (Connection c = ConexionBaseDatos.getConexion();
             PreparedStatement ps = (PreparedStatement)
                     c.prepareStatement(sql)) {

            ps.setString(1, apodo);
            ps.executeUpdate();
        }
    }

    
    /**
     * Metodo privado que funciona como traductor de una fila en un
     * objeto del dominio 
     * 
     * @param rs
     * @return
     * @throws SQLException 
     */
    
    /*
    
    ---- De aui en adelante tenemos los especializdos del DAO para mascota ----
    
    */
    
    /**
     * Lista todas las mascotas, ordenadas por apodo.
     *
     * @return lista completa.
     * @throws Exception si ocurre error de acceso a datos.
     */
    public List<MascotaVO> listarTodas() throws Exception {
        final String sql = "SELECT * FROM " + TBL + " ORDER BY " + colApodo;

        List<MascotaVO> out = new ArrayList<>();
        try (Connection c = ConexionBaseDatos.getConexion();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) out.add(map(rs));
        }
        return out;
    }
    
    /**
     * Consulta por clasificación (devuelve lista).
     *
     * @param clasificacion valor exacto a filtrar.
     * @return mascotas con esa clasificación.
     * @throws Exception si ocurre error de acceso a datos.
     */
    public List<MascotaVO> consultarPorClasificacion(String clasificacion) 
            throws Exception {
        final String sql = "SELECT * FROM " + TBL + " WHERE " + colClasificacion
                + "=? ORDER BY " + colApodo;

        List<MascotaVO> out = new ArrayList<>();
        try (Connection c = ConexionBaseDatos.getConexion();
             PreparedStatement ps = (PreparedStatement)
                     c.prepareStatement(sql)) {

            ps.setString(1, clasificacion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(map(rs));
            }
        }
        return out;
    }

    /**
     * Consulta por familia (devuelve lista).
     *
     * @param familia valor exacto a filtrar.
     * @return mascotas de esa familia.
     * @throws Exception si ocurre error de acceso a datos.
     */
    public List<MascotaVO> consultarPorFamilia(String familia)
            throws Exception {
        final String sql = "SELECT * FROM " + TBL + " WHERE " +
                colFamilia + "=? ORDER BY " + colApodo;

        List<MascotaVO> out = new ArrayList<>();
        try (Connection c = ConexionBaseDatos.getConexion();
             PreparedStatement ps = (PreparedStatement)
                     c.prepareStatement(sql)) {

            ps.setString(1, familia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(map(rs));
            }
        }
        return out;
    }

    /**
     * Consulta por tipo de alimento (devuelve lista).
     *
     * @param tipoAlimento valor exacto a filtrar 
     * @return mascotas con ese tipo de alimento.
     * @throws Exception si ocurre error de acceso a datos.
     */
    public List<MascotaVO> consultarPorTipoAlimento(String tipoAlimento)
            throws Exception {
        final String sql = "SELECT * FROM " + TBL + " WHERE " + colAlimento +
                "=? ORDER BY " + colApodo;

        List<MascotaVO> out = new ArrayList<>();
        try (Connection c = ConexionBaseDatos.getConexion();
             PreparedStatement ps = (PreparedStatement)
                     c.prepareStatement(sql)) {

            ps.setString(1, tipoAlimento);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) out.add(map(rs));
            }
        }
        return out;
    }
    
    private MascotaVO map(ResultSet rs) throws SQLException {
        return new MascotaVO(
                rs.getString(colApodo),
                rs.getString(colNombreComun),
                rs.getString(colClasificacion),
                rs.getString(colFamilia),
                rs.getString(colGenero),
                rs.getString(colEspecie),
                rs.getString(colAlimento)
        );
        
    }
}

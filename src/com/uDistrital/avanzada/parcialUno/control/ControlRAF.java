/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.DAO.MascotaDAO;
import com.uDistrital.avanzada.parcialUno.modelo.DAO.RAFDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.List;

/**
 *
 * @author santi
 */
public class ControlRAF {
    private RAFDAO rafDAO;
    private MascotaDAO mascotaDAO;

     public ControlRAF() {
        this.rafDAO = new RAFDAO();
        this.mascotaDAO = new MascotaDAO();
    }
    
    /**
     * Guarda todas las mascotas de la BD en el archivo RAF
     * Este método debe llamarse al finalizar el programa (botón SALIR)
     * 
     * @throws Exception Si ocurre un error al guardar
     */
    public void guardarEstadoBDEnRAF() throws Exception {
        try {
            // 1. Obtener todas las mascotas de la base de datos
            List<MascotaVO> mascotasBD = mascotaDAO.listarTodas();
            
            // 2. Validar que haya datos para guardar
            if (mascotasBD == null || mascotasBD.isEmpty()) {
                throw new Exception("No hay mascotas en la BD para guardar en RAF");
            }
            
            // 3. Guardar todas las mascotas en el archivo RAF
            // El método guardarTodas LIMPIA el archivo y escribe todas las mascotas
            rafDAO.guardarTodas(mascotasBD);
            
            System.out.println("✓ Estado de la BD guardado exitosamente en RAF");
            System.out.println("✓ Total de mascotas guardadas: " + mascotasBD.size());
            
        } catch (Exception e) {
            throw new Exception("Error al guardar estado de BD en RAF: " + e.getMessage());
        }
    }
    
    /**
     * Carga las mascotas desde el archivo RAF 
     * Útil si quieres verificar qué se guardó
     * 
     * @return Lista de mascotas leídas desde el RAF
     * @throws Exception Si ocurre un error al leer
     */
    public List<MascotaVO> cargarMascotasDesdeRAF() throws Exception {
        try {
            return rafDAO.leerTodas();
        } catch (Exception e) {
            throw new Exception("Error al cargar mascotas desde RAF: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si el archivo RAF tiene datos
     * 
     * @return true si el archivo contiene información
     */
    public boolean rafTieneDatos() {
        return rafDAO.tieneDatos();
    }
    
    /**
     * Limpia todo el contenido del archivo RAF
     * 
     * @throws Exception Si ocurre un error al limpiar
     */
    public void limpiarRAF() throws Exception {
        rafDAO.limpiar();
    }
    
    /**
     * obtiene el tamaño del archivo
     * 
     * @return Tamaño en bytes
     */
    public long obtenerTamanioRAF() {
        return rafDAO.obtenerTamanio();
    }
    
    
    
}

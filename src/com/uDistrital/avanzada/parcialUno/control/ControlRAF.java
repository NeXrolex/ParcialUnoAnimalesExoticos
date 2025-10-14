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
 * Clase encargada del manejo del random access file 
 * 
 *
 * @author santi, Alex
 */
public class ControlRAF {
    private RAFDAO rafDAO;
    
    /**
     * Constructor que crea a su colaborador inmediato 
     * 
     */
     public ControlRAF() {
        this.rafDAO = new RAFDAO();
    }
    
    /**
     * Determina si existen datos en el raf
     * 
     * @return true or false depende el archivo
     * @throws Exception Si ocurre un error de lectura
     */
    public boolean tieneDisponible() throws Exception {
        return rafDAO.tieneDatos();
    }

    /**
     * Lee todas las mascotas disponibles para el momento en el raf
     * 
     * @return Mascotas en el raf
     * @throws Exception Si ocurre un error de lectura
     */
    public List<MascotaVO> leerTodas() throws Exception {
        return rafDAO.leerTodas();
    }

    /**
     * Guarda todas las mascotas 
     * 
     * @param mascotas Mascotas a cuardar
     * @throws Exception Si ocurre un error con el archivo
     */
    public void guardarTodas(List<MascotaVO> mascotas) throws Exception {
        rafDAO.guardarTodas(mascotas);
    }

    /**
     * Limpia el archivo actual
     * Funcional para sobreescribir de manera mas limpia
     * @throws Exception 
     */
    public void limpiar() throws Exception {
        rafDAO.limpiar();
    }
    
    
    
}

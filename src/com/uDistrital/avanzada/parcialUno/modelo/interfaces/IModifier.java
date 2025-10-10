/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.interfaces;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;

/**
 *
 * @author jeiso,alex
 */
public interface IModifier {
    /**
     * Contrato para modificar una mascota
     * @param mascota mascota a modificar
     * @throws Exception si ocurre un error
     */
    void  modificar (MascotaVO mascota) throws Exception;
    /**
     * Contrato para eliminar una mascota
     * @param apodo identificador de la mascota a eliminar
     * @throws Exception    
     */
    void eliminar (String apodo) throws Exception;
}

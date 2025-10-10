/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.interfaces;

/**Interface que implementa la R del CRUD 
 *
 * @author Alex 
 */
public interface IRead<t> {
    
    /**
     * Contrato para consultar datos 
     * 
     * @param dato Dato a consultar
     * @throws Exception Si ocurre un error
     */
    public t consultar(String dato) throws Exception;
    
    
}

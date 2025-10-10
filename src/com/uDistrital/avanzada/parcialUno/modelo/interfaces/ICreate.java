/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.interfaces;

/**
 * Interface que cubre la C del CRUD
 *
 * @author Alex
 */
public interface ICreate<t> {

    /**
     * Contrato para insertar elementos
     * 
     * @param elemento Elemento a insertar
     * @throws Exception Si ocurre algun error 
     */
    public void insertar(t elemento) throws Exception;
    
}

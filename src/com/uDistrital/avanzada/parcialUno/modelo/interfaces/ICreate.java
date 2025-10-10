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
    * Define los contratos para las operaciones de CREACION
    *
    */
    public void insertar(t elemento) throws Exception;
    
}

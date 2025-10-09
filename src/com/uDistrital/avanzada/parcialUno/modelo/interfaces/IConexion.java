/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.interfaces;

/**
 * Interface generica para la conexion de archivos
 * Usada para Bases de datos, Random Access file, Properties y archivos de 
 * Serializacion.
 *
 * @author jeison, Alex
 */
public interface IConexion {
    
    //Prepara el recurso de conexion
    public void conectar() throws Exception;
    //Cierra el recurso
    public void desconectar() throws Exception;
    
}

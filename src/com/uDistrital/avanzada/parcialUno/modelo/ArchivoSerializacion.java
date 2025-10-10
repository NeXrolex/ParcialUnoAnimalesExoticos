/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;



/**
 * Clase encargada de manejar archivos de serilaizacion 
 *
 *
 * @author jeison, Alex
 */
public class ArchivoSerializacion {
    //Referencia fisica del archivo
    private File archivo;

    /**
    * Encargado de recibir la ruta del archivo y asignarala
    *
    * @param ruta Ruta de localizacion del archivo
    */
    public ArchivoSerializacion(String ruta) throws IOException{

        this.archivo = new File(ruta);

        if(!archivo.exists()){ //Si no existe el archivo lo crea
            archivo.createNewFile();
            
        }
        
    }

    /**
    * Encargado de abrir la escitura para selrializar objetos
    * Para nuestra inplementacion solo necesitamos escribit
    *
    */
    public ObjectOutputStream abrirEscritura() throws IOException{

        return new ObjectOutputStream(
            new FileOutputStream(archivo, false));
        
    } 

    public File getArchivo(){

        return archivo;
        
    }    

    

    
    
}

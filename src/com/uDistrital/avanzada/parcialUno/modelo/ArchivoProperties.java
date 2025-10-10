/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author jeison, Alex
 */
public class ArchivoProperties {

    //
    private final File archivo;
    
    /**
     *Constructor que asigna la ruta por defecto del archivo
     * 
     * @param ruta Ruta del archivo
     */
    public ArchivoProperties(String ruta) {
        this.archivo = new File(ruta);
    }

    /**
     * Punto de acceso al archivo para lectura.
     *
     * @return Un flujo de entrada para leer el contenido del archivo.
     * @throws IOException Si ocurre un error al intentar abrir el archivo.
     */
    public InputStream open() throws IOException {
        return new FileInputStream(archivo);
    }
    
    /**
     * Obtiene el onjeto del archivo
     * 
     * @return Archivo
     */
    public File getArchivo() {
        return archivo;
    }

}

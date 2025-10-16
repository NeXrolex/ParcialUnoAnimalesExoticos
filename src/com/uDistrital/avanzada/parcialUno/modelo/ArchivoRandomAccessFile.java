/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Clase encarda de maneja el RAF
 *
 * @author jeison,Alex
 */
public class ArchivoRandomAccessFile {

    //Referencia del archivo
    private final File archivo;

    /**
     * Constrcutor que inicializa el objeto de archivoy garantiza su existencia
     *
     * @param ruta Ruta del archivo raf
     * @throws IOException Si no existe los crea
     */
    public ArchivoRandomAccessFile(String ruta) throws IOException {
        this.archivo = new File(ruta);
        // Garantizar existencia física del archivo y su directorio
        File parent = archivo.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs(); //crea sus directorios si no existe
        }

        if (!archivo.exists()) {
            //Crea el archivo facia en caso de no existir
            archivo.createNewFile();
        }
    }

    /**
     * Abre un RandomAccessFile en el modo indicado: si lectura o escritura
     *
     * @param modo Modo de apertura
     * @throws IOExcdeption Si ocurre un error de apertura
     */
    public RandomAccessFile abrir(String modo) throws IOException {
        return new RandomAccessFile(archivo, modo);
    }

    /**
     * indica si el archivo contiene informacion
     *
     * @return true si el archivo tiene algun byte
     */
    public boolean tieneDatos() {
        return archivo.length() > 0;
    }

    /**
     * Elimina el contenido del archivo(Mas no el archivo :) )
     *
     * @throws IOException Si ocurre un error al limpiar el archivo
     */
    public void limpiar() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            raf.setLength(0); // deja el archivo vacío
        }
    }

    /**
     * Regresa el tamano en bytes del archivo
     *
     * @return longitud del archivo
     */
    public long tamanio() {
        return archivo.length();
    }

    /**
     * Devuelve la refrencia asociada al archivo
     *
     * @return Archivo representacion fisica del archivo
     */
    public File getArchivo() {
        return archivo;
    }
}

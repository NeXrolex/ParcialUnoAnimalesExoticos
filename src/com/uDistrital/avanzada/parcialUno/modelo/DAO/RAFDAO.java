/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.DAO;

import com.uDistrital.avanzada.parcialUno.modelo.ArchivoRandomAccessFile;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.ICreate;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.IRead;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para manejo de archivo de acceso aleatorio
 * 
 * @author jeiso, Alex
 */
public class RAFDAO implements ICreate<MascotaVO>, IRead<List<MascotaVO>> {
    
    private ArchivoRandomAccessFile archivoRAF;
    private static final String RUTA_ARCHIVO = "data/mascotas.dat";
    
    /**
     * Constructor que inicializa el archivo RAF
     */
    public RAFDAO() {
        try {
            archivoRAF = new ArchivoRandomAccessFile(RUTA_ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al inicializar RAFDAO: " + e.getMessage());
        }
    }
    
    
    
    /**
     * Inserta una mascota en el archivo RAF
     * 
     * @param mascota Mascota a insertar
     * @throws Exception Si ocurre un error al escribir
     */
    @Override
    public void insertar(MascotaVO mascota) throws Exception {
        try (RandomAccessFile raf = archivoRAF.abrir("rw")) {
            // Posicionarse al final del archivo para agregar
            raf.seek(raf.length());
            
            // Escribir cada campo de la mascota
            raf.writeUTF(mascota.getApodo());
            raf.writeUTF(mascota.getNombreComun());
            raf.writeUTF(mascota.getClasificacion());
            raf.writeUTF(mascota.getFamilia());
            raf.writeUTF(mascota.getGenero());
            raf.writeUTF(mascota.getEspecie());
            raf.writeUTF(mascota.getAlimentoPrincipal());
            
        } catch (IOException e) {
            throw new Exception("Error al insertar mascota en RAF: " + e.getMessage());
        }
    }
    
    /**
     * Inserta múltiples mascotas REESCRIBIENDO todo el archivo
     * Útil para guardar el estado completo de la BD al salir
     * 
     * @param mascotas Lista de mascotas a guardar
     * @throws Exception Si ocurre un error
     */
    public void guardarTodas(List<MascotaVO> mascotas) throws Exception {
        try {
            // Limpiar el archivo antes de escribir
            archivoRAF.limpiar();
            
            // Escribir todas las mascotas
            for (MascotaVO mascota : mascotas) {
                insertar(mascota);
            }
            
        } catch (Exception e) {
            throw new Exception("Error al guardar todas las mascotas: " + e.getMessage());
        }
    }
    
    // ========== IMPLEMENTACIÓN DE IRead ==========
    
    /**
     * Consulta todas las mascotas del archivo RAF
     * 
     * @param dato Parámetro no usado (por firma de la interfaz)
     * @return Lista de todas las mascotas en el archivo
     * @throws Exception Si ocurre un error al leer
     */
    @Override
    public List<MascotaVO> consultar(String dato) throws Exception {
        return leerTodas();
    }
    
    /**
     * Lee TODAS las mascotas del archivo RAF
     * 
     * @return Lista con todas las mascotas almacenadas
     * @throws Exception Si ocurre un error al leer
     */
    public List<MascotaVO> leerTodas() throws Exception {
        List<MascotaVO> mascotas = new ArrayList<>();
        
        // Si el archivo está vacío, retornar lista vacía
        if (!archivoRAF.tieneDatos()) {
            return mascotas;
        }
        
        try (RandomAccessFile raf = archivoRAF.abrir("r")) {
            
            // Leer mientras haya datos
            while (raf.getFilePointer() < raf.length()) {
                try {
                    // Leer cada campo de la mascota
                    String apodo = raf.readUTF();
                    String nombreComun = raf.readUTF();
                    String clasificacion = raf.readUTF();
                    String familia = raf.readUTF();
                    String genero = raf.readUTF();
                    String especie = raf.readUTF();
                    String alimentoPrincipal = raf.readUTF();
                    
                    // Crear y agregar la mascota a la lista
                    MascotaVO mascota = new MascotaVO(
                        apodo, nombreComun, clasificacion,
                        familia, genero, especie, alimentoPrincipal
                    );
                    mascotas.add(mascota);
                    
                } catch (EOFException e) {
                    // Fin del archivo alcanzado
                    break;
                }
            }
            
        } catch (IOException e) {
            throw new Exception("Error al leer mascotas del RAF: " + e.getMessage());
        }
        
        return mascotas;
    }
    
    /**
     * Busca una mascota específica por su apodo
     * 
     * @param apodo Apodo de la mascota a buscar
     * @return MascotaVO si la encuentra, null si no existe
     * @throws Exception Si ocurre un error al buscar
     */
    public MascotaVO buscarPorApodo(String apodo) throws Exception {
        List<MascotaVO> todasLasMascotas = leerTodas();
        
        for (MascotaVO mascota : todasLasMascotas) {
            if (mascota.getApodo().equalsIgnoreCase(apodo)) {
                return mascota;
            }
        }
        
        return null; // No encontrada
    }
    
    /**
     * Verifica si el archivo tiene datos
     * 
     * @return true si el archivo contiene información
     */
    public boolean tieneDatos() {
        return archivoRAF.tieneDatos();
    }
    
    /**
     * Limpia todo el contenido del archivo
     * 
     * @throws Exception Si ocurre un error al limpiar
     */
    public void limpiar() throws Exception {
        try {
            archivoRAF.limpiar();
        } catch (IOException e) {
            throw new Exception("Error al limpiar archivo RAF: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene el tamaño del archivo en bytes
     * 
     * @return Tamaño en bytes
     */
    public long obtenerTamanio() {
        return archivoRAF.tamanio();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.DAO;

import com.uDistrital.avanzada.parcialUno.modelo.ArchivoSerializacion;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.ICreate;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author jeiso
 */
public class SerializacionDAO implements ICreate<List<MascotaVO>> {

    private ArchivoSerializacion archivoSerializacion;
    private static final String nombreArchivo = "mascotas.ser";

    public SerializacionDAO() {

    }

    @Override
    public void insertar(List<MascotaVO> mascotas) throws Exception {
        serializarEnRuta(mascotas, "data");
    }

    public void serializarEnRuta(List<MascotaVO> mascotas,
            String rutaCarpeta) throws Exception {
        if (mascotas == null || mascotas.isEmpty()) {
            throw new Exception("No hay mascotas para serializar");
        }
        if (rutaCarpeta == null || rutaCarpeta.trim().isEmpty()) {
            throw new Exception("La ruta de la carpeta no puede estar vacía");
        }
        try {
            String rutaCompleta = rutaCarpeta.trim()
                    + java.io.File.separator + nombreArchivo;
            archivoSerializacion = new ArchivoSerializacion(rutaCompleta);
            serializarMascotas(mascotas);        
        }catch(IOException e){
        throw new Exception("Error al serializar "+e.getMessage());
        }
    }
    public void serializarMascotas(List<MascotaVO> mascotas)throws IOException{
    try(ObjectOutputStream salidaObjeto = archivoSerializacion.abrirEscritura()){
        salidaObjeto.writeObject(mascotas);
        
    }catch (IOException e) {
            throw new IOException("Error al escribir el archivo serializado: " + e.getMessage());
        }
    }
        public String getNombreArchivo() {
        return nombreArchivo;
    }
    public ArchivoSerializacion getArchivoSerializacion() {
        return archivoSerializacion;
    }    


}

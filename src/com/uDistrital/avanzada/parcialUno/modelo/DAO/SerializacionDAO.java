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
 * Dao encargado de serializar las mascotas implementa Icreate para escribir los
 * objetos serializados
 *
 * @author jeiso, Alex
 */
public class SerializacionDAO implements ICreate<List<MascotaVO>> {

    private ArchivoSerializacion archivoSerializacion;
    private static final String nombreArchivo = "mascotas.ser";

    /**
     * contructor
     */
    public SerializacionDAO() {

    }

    /**
     * metodo implementado de Icreate
     *
     * @param mascotas lista de mascotas a serializar
     * @throws Exception si ocurre in error al serializar
     */
    @Override
    public void insertar(List<MascotaVO> mascotas) throws Exception {
        serializarEnRuta(mascotas, "data");
    }

    /**
     * Serializa las mascotas en una ruta espesifica
     *
     * @param mascotas mascotas a serializar
     * @param rutaCarpeta ruta de la carpeta donde guardar
     * @throws Exception si ocurre un error
     */
    public void serializarEnRuta(List<MascotaVO> mascotas,
            String rutaCarpeta) throws Exception {
        // validamos que la lista no este vacia
        if (mascotas == null || mascotas.isEmpty()) {
            throw new Exception("No hay mascotas para serializar");
        }
        // validamos que la ruta no sea nula
        if (rutaCarpeta == null || rutaCarpeta.trim().isEmpty()) {
            throw new Exception("La ruta de la carpeta no puede estar vacía");
        }
        try {
            // construimos la ruta 
            String rutaCompleta = rutaCarpeta.trim()
                    + java.io.File.separator + nombreArchivo;
            // iniciamos el archivo de serializacion
            archivoSerializacion = new ArchivoSerializacion(rutaCompleta);
            // serializamos la ruta
            serializarMascotas(mascotas);
        } catch (IOException e) {
            throw new Exception("Error al serializar " + e.getMessage());
        }
    }
    /**
     * serializa la lista de mascotas 
     * @param mascotas mascotas a serializar
     * @throws IOException si pcurre un error al escribir 
     */
    public void serializarMascotas(List<MascotaVO> mascotas) throws IOException {
        try (ObjectOutputStream salidaObjeto = archivoSerializacion.abrirEscritura()) {
            // escribe la lista de mascotas 
            salidaObjeto.writeObject(mascotas);

        } catch (IOException e) {
            throw new IOException("Error al escribir el archivo serializado: " + e.getMessage());
        }
    }
    /**
     * Obtiene el nombre del archivo que se va a crear
     * @return nombre del archivo serializable
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public ArchivoSerializacion getArchivoSerializacion() {
        // obtiene la referencia del archivo serializacion
        return archivoSerializacion;
    }

}

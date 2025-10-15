/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.DAO.SerializacionDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.List;
import java.util.Objects;

/**
 * Controla la serializacion de onjetos(Importante porque al final vamos a
 * enviar eso)
 *
 * @author Alex
 */
public class ControlSerializacion {

    private final SerializacionDAO serDao;

    /**
     * Constructor por defecto: el control se autoabastece de su DAO.
     *
     */
    public ControlSerializacion() {
        this.serDao = new SerializacionDAO();
    }

    /**
     * Exporta la lista de mascotas en la carpeta indicada (proveniente del
     * JFileChooser). Delegación directa al DAO, usando únicamente su API
     * existente.
     *
     * @param lista lista a serializar (puede contener nulos).
     * @param carpetaDestino carpeta donde guardar (no vacía).
     * @throws Exception si insumos inválidos o error de E/S.
     */
    public void exportar(List<MascotaVO> lista, String carpetaDestino)
            throws Exception {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException
        ("No hay mascotas para serializar.");
        }
        if (carpetaDestino == null || carpetaDestino.trim().isEmpty()) {
            throw new IllegalArgumentException
        ("La carpeta de destino no puede estar vacía.");
        }
        //Directoal dao para que cumpla su responsabilidad
        serDao.serializarEnRuta(lista, carpetaDestino.trim());
    }
}

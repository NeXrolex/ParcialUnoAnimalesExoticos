/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

/**
 *
 * @author santi
 */
import com.uDistrital.avanzada.parcialUno.modelo.DAO.PropertiesDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.ArrayList;

import java.util.List;

public class ControlProperties {

    private final PropertiesDAO dao;
    private final ControlVista cVista;


    public ControlProperties(String rutaArchivo, ControlVista controlVista) {
        this.dao = new PropertiesDAO(rutaArchivo);
        this.cVista = controlVista;
    }

    /**
     * Carga las mascotas desde el archivo properties y completa los campos faltantes
     * mediante el ControlVista (cuando esté implementado).
     *
     * @return lista de mascotas con todos los datos completados
     */
    public List<MascotaVO> cargarMascotasCompletas() {
        List<MascotaVO> listaFinal = new ArrayList<>();
        try {
            List<MascotaVO> mascotas = dao.listarTodas();

            for (MascotaVO m : mascotas) {
                // Verificamos si hay al menos un campo vacío
                if (tieneCamposIncompletos(m)) {
                    // Si la vista no está implementada aún, se puede omitir o dejar en null
                    if (cVista != null) {
                        cVista.pedirDatosFaltantes(m);
                    }
                }
                listaFinal.add(m);
            }

        } catch (Exception e) {
            // Aquí no hacemos System.out, solo propagamos el error a una capa superior
            throw new RuntimeException("Error al cargar mascotas desde Properties", e);
        }

        return listaFinal;
    }

    /**
     * Verifica si una mascota tiene al menos un campo vacío o nulo.
     */
    private boolean tieneCamposIncompletos(MascotaVO m) {
        return esVacio(m.getNombreComun())
            || esVacio(m.getApodo())
            || esVacio(m.getClasificacion())
            || esVacio(m.getFamilia())
            || esVacio(m.getGenero())
            || esVacio(m.getEspecie())
            || esVacio(m.getAlimentoPrincipal());
    }

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}

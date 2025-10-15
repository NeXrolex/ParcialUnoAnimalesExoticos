/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

/**
 *
 * @author Alex
 */
import com.uDistrital.avanzada.parcialUno.modelo.DAO.PropertiesDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.ArrayList;

import java.util.List;

public final class ControlProperties {

    private final PropertiesDAO proDao;

    /**
     * @param ruta Ruta del archivo .properties (p.ej.
     * "src/data/mascotas.properties")
     */
    public ControlProperties(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del properties"
                    + " no puede estar vacía.");
        }
        this.proDao = new PropertiesDAO(ruta.trim());
    }

    /**
     * Carga todas las mascotas definidas en el .properties.
     */
    public List<MascotaVO> cargarTodas() throws Exception {
        return proDao.listarTodas();
    }

    /**
     * Consulta por apodo dentro del .properties.
     */
    public MascotaVO consultarPorApodo(String apodo) throws Exception {
        return proDao.consultar(apodo);
    }

    /**
     * Lista sólo las mascotas con datos faltantes según el enunciado.
     */
    public List<MascotaVO> filtrarIncompletas(List<MascotaVO> lista) {
        List<MascotaVO> out = new ArrayList<>();
        if (lista == null) {
            return out;
        }
        for (MascotaVO m : lista) {
            if (esIncompleta(m)) {
                out.add(m);
            }
        }
        return out;
    }

    /**
     * Reglas de completitud: apodo, nombre, clasificación, familia, género,
     * especie, alimento.
     */
    public boolean esIncompleta(MascotaVO m) {
        if (m == null) {
            return true;
        }
        return vacio(m.getApodo())
                || vacio(m.getNombreComun())
                || vacio(m.getClasificacion())
                || vacio(m.getFamilia())
                || vacio(m.getGenero())
                || vacio(m.getEspecie())
                || vacio(m.getAlimentoPrincipal());
    }

    private boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}

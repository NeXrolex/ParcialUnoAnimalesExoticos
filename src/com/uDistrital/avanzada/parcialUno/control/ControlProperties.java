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
     * Constructor que valida que la ruta sea una cadena valida
     * 
     * @param ruta Ruta del archivo .properties (p.ej.
     * "src/data/mascotas.properties")
     * @throws IllegalArgumentException si la ruta es nula
     */
    public ControlProperties(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            throw new IllegalArgumentException("La ruta del properties"
                    + " no puede estar vacía.");
        }
        this.proDao = new PropertiesDAO(ruta.trim());
    }

    /**
     * Carga todas las mascotas definidas en el archivo de porpieddes
     * 
     * @return Lista de objetosMascotaVO
     * @throws Exception si ocurre un error al leer el archivo
     */
    public List<MascotaVO> cargarTodas() throws Exception {
        return proDao.listarTodas();
    }

    /**
     * Consulta una mascota por su apodo
     * 
     * @param apodo Identificador
     * @return Mascota correspondiente 
     * @throws Exception Si ocurre un error en la comsulta
     */
    public MascotaVO consultarPorApodo(String apodo) throws Exception {
        return proDao.consultar(apodo);
    }

    /**
     * Filtra en una lista las mascotas imcopletas
     * 
     * @param lista Lista de mascotas a analizar
     * @return Lsia con las mascotas que representan datos faltantes 
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
     * 
     * @param m mascota a evaluar
     * @return Si le falta algun dato obligatorio
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
    /**
     * Método utilitario privado.
     * Evalúa si una cadena es nula o está compuesta sólo por espacios en 
     * blanco.
     *
     * @param s Cadena a validar.
     * @return true si la cadena es null o vacía (tras eliminar espacios),
     * false en caso contrario.
     */
    private boolean vacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}

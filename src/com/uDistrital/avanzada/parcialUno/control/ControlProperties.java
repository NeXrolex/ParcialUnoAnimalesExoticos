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



    public ControlProperties(String rutaArchivo) {
        this.dao = new PropertiesDAO(rutaArchivo);
    }

    /**
     * Carga las mascotas desde el archivo properties y completa los campos faltantes
     * mediante el ControlVista (cuando esté implementado).
     *
     * @return lista de mascotas con todos los datos completados
     */
    public List<MascotaVO> cargarMascotas() {
        List<MascotaVO> listaFinal = new ArrayList<>();       
        try {
//            System.out.println("-----PRUEBA-----");
        List<MascotaVO> mascotas = dao.listarTodas();
//        System.out.println("🐾 Mascotas cargadas desde DAO: " + mascotas.size());
//        System.out.println("===== DEPURACIÓN: Datos cargados desde properties =====");
//            for (MascotaVO m : mascotas) {
//                
//                System.out.println("Mascota: " + m.getApodo());
//                System.out.println(" - Nombre: '" + m.getNombreComun()+ "'");
//                System.out.println(" - Clasificación: '" + m.getClasificacion() + "'");
//                System.out.println(" - Familia: '" + m.getFamilia() + "'");
//                System.out.println(" - Género: '" + m.getGenero() + "'");
//                System.out.println(" - Especie: '" + m.getEspecie() + "'");
//                System.out.println(" - Alimento: '" + m.getAlimentoPrincipal() + "'");
//                System.out.println("----------------------------------");
//
//                // Verificamos si hay campos incompletos
//                if (tieneCamposIncompletos(m)) {
//                    System.out.println("⚠ Mascota con datos incompletos detectada: " + m.getApodo());
//                }
//
//                listaFinal.add(m);
//            }
//            System.out.println("========================================================");
    } catch (Exception e) {
        throw new RuntimeException("Error al cargar las mascotas desde Properties", e);
    }
        return listaFinal;
    
    }


    public List<MascotaVO> obtenerMascotasIncompletas(List<MascotaVO> lista) {
        List<MascotaVO> incompletas = new ArrayList<>();
        for (MascotaVO m : lista) {
            if (tieneCamposIncompletos(m)) {
                incompletas.add(m);
            }
        }
        return incompletas;
    }
    
    /**
     * 
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

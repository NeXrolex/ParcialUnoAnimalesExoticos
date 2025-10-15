/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.DAO.MascotaDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * @author Alex
 */
public class ControlAnimal {
    private final MascotaDAO dao;

    public ControlAnimal() {
        this.dao = new MascotaDAO();
    }
    /**
     * Recibe listas de mascotas (originales y completadas),
     * las combina y las guarda en la base de datos.
     * @param mascotas
     * @param incompletas
     */
    public void procesarMascotas(List<MascotaVO> mascotas, List<MascotaVO> incompletas) {
        List<MascotaVO> listaPropBD = combinarListas(mascotas, incompletas);
        guardarMascotasEnBD(listaPropBD);
    }
    /**
     * Combina ambas listas, eliminando duplicados por apodo.
     */
    private List<MascotaVO> combinarListas(List<MascotaVO> originales, List<MascotaVO> incompletas) {
        List<MascotaVO> resultado = new ArrayList<>(originales);

        for (MascotaVO m : incompletas) {
            resultado.removeIf(x -> x.getNombreComun().equalsIgnoreCase(m.getNombreComun()));
            resultado.add(m);
        }
        return resultado;
    }
    public void guardarMascotasEnBD(List<MascotaVO> listaPropBD) {
        for (MascotaVO m : listaPropBD) {
            try {
                // Verifica si ya existe la mascota
                MascotaVO existente = dao.consultar(m.getApodo());

                if (existente == null) {
                    dao.insertar(m); // ✅ Si no existe, la insertamos
                } else {
                    dao.modificar(m); // 🔁 Si ya existe, la actualizamos
                }

            } catch (Exception e) {
                throw new RuntimeException("Error al guardar las mascotas en la Base de Datos", e);
            }
        }
    }
}

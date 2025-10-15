/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ControlVista implements ActionListener {

    private VistaPrincipal vista;
    private ControlGeneral cGeneral;

    public ControlVista(VistaPrincipal vista, ControlGeneral cGeneral) {
        this.vista = vista;
        this.cGeneral = cGeneral;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // En el futuro manejaremos botones, etc.
    }

    public void adminMascotasIncompletas(List<MascotaVO> incompletas) {
        if (incompletas.isEmpty()) {
            vista.mostrarMensaje("Todas las mascotas estan completas.");
        } else {
            vista.mostrarMensaje("Se encontraron mascotas con datos incompletos:");
            for (MascotaVO m : incompletas) {
                vista.mostrarMensaje("- " + m.getNombreComun());
            }
            completarDatos(incompletas);
        }
    }
    private void completarDatos(List<MascotaVO> incompletas) {
        for (MascotaVO m : incompletas) {
            vista.mostrarMensaje("\n--- Completando datos de: " + m.getNombreComun() + " ---");

            if (esVacio(m.getApodo())) {
                m.setApodo(vista.leerDato("Ingrese el apodo: "));
            }
            if (esVacio(m.getClasificacion())) {
                m.setClasificacion(vista.leerDato("Ingrese la clasificacion: "));
            }
            if (esVacio(m.getFamilia())) {
                m.setFamilia(vista.leerDato("Ingrese la familia: "));
            }
            if (esVacio(m.getGenero())) {
                m.setGenero(vista.leerDato("Ingrese el genero: "));
            }
            if (esVacio(m.getEspecie())) {
                m.setEspecie(vista.leerDato("Ingrese la especie: "));
            }
            if (esVacio(m.getAlimentoPrincipal())) {
                m.setAlimentoPrincipal(vista.leerDato("Ingrese el alimento principal: "));
            }

            vista.mostrarMensaje("Datos completados para " + m.getNombreComun()+" "+ m.getApodo());
        }
        vista.mostrarMensaje("datos almacenados correctamente");
        // En este punto, las mascotas ya están completas en memoria
        
    }

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }
}

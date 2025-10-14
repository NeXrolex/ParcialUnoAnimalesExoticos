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

    public void mostrarMascotasIncompletas(List<MascotaVO> incompletas) {
        if (incompletas.isEmpty()) {
            vista.mostrarMensaje("Todas las mascotas estan completas.");
        } else {
            vista.mostrarMensaje("Se encontraron mascotas con datos incompletos:");
            for (MascotaVO m : incompletas) {
                vista.mostrarMensaje("- " + m.getApodo() + " (" + m.getEspecie() + ")");
            }
        }
    }
}

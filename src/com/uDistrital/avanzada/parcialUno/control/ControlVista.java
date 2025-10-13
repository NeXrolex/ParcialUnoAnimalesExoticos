/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Alex
 */
public class ControlVista implements ActionListener {
    
    private ControlGeneral cGeneral;
    private VistaPrincipal vista;

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    public ControlVista(VistaPrincipal vista) {
        this.vista = vista;
    }
    public void pedirDatosFaltantes(MascotaVO m) {
        if (esVacio(m.getApodo())) {
            m.setApodo(pedirDatosFaltantes("apodo", m.getEspecie()));
        }
        if (esVacio(m.getAlimentoPrincipal())) {
            m.setAlimentoPrincipal(pedirDatosFaltantes("alimentación", m.getEspecie()));
        }
        if (esVacio(m.getFamilia())) {
            m.setFamilia(pedirDatosFaltantes("familia", m.getEspecie()));
        }
        if (esVacio(m.getClasificacion())) {
            m.setClasificacion(pedirDatosFaltantes("clasificación", m.getEspecie()));
        }
        if (esVacio(m.getGenero())) {
            m.setGenero(pedirDatosFaltantes("género", m.getEspecie()));
        }
        if (esVacio(m.getNombreComun())) {
            m.setNombreComun(pedirDatosFaltantes("nombre común", m.getEspecie()));
        }
    }

    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }

}

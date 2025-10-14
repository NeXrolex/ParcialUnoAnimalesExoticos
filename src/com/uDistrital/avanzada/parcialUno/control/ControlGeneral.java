/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.util.List;
/**
 *
 * @author Alex
 */
public class ControlGeneral {

   
    private  ControlProperties cProperties;
    private ControlVista cVista;

    public void iniciarPrograma() {
        VistaPrincipal vista = new VistaPrincipal();
        this.cVista = new ControlVista(vista, this);
        this.cProperties = new ControlProperties("src/data/mascotas.properties");

        // Cargar mascotas
        List<MascotaVO> mascotas = cProperties.cargarMascotas();

        // Verificar mascotas incompletas
        List<MascotaVO> incompletas = cProperties.obtenerMascotasIncompletas(mascotas);
        cVista.mostrarMascotasIncompletas(incompletas);
    }

}

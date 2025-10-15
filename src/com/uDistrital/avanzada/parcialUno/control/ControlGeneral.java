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
    private final ControlAnimal cAnimal;

    public ControlGeneral() {
        this.cProperties = new ControlProperties("src/data/mascotas.properties");
        VistaPrincipal vista = new VistaPrincipal();
        this.cVista = new ControlVista(vista, this);
        this.cAnimal = new ControlAnimal();
    }    
    public void iniciarPrograma() {
        // Cargar mascotas
        List<MascotaVO> mascotas = cProperties.cargarMascotas();

        // Verificar mascotas incompletas
        List<MascotaVO> incompletas = cProperties.obtenerMascotasIncompletas(mascotas);
        
        cVista.adminMascotasIncompletas(incompletas);       

       cAnimal.procesarMascotas(mascotas, incompletas);
    }


}

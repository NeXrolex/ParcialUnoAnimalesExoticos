/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

/**
 *
 * @author jeison, Alex
 */
public class Animal {
    private String nombreComun;
    private String clasificacion;
    private String familia;
    private String genero;
    private String especie;
    private String alimentoPrincipal;

    public Animal(String nombreComun, String clasificacion, String familia, String genero, String especie, String alimentoPrincipal) {
        this.nombreComun = nombreComun;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.alimentoPrincipal = alimentoPrincipal;
    }

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getAlimentoPrincipal() {
        return alimentoPrincipal;
    }

    public void setAlimentoPrincipal(String alimentoPrincipal) {
        this.alimentoPrincipal = alimentoPrincipal;
    }
    
    
}

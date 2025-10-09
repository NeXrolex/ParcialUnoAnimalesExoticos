/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

import java.io.Serializable;

/**
 *
 * @author jeisoS,Alex
 */
public class MascotaVO implements Serializable {

    private String nombreComun;
    private String apodo;
    private String clasificacion;
    private String familia;
    private String genero;
    private String especie;
    private String alimentoPrincipal;
    
    /**
     * Constructor vacio
     */
    public MascotaVO() {

    }

    /**
     * Constructor encargado de asignar los valores comunes de una mascoata
     *
     *
     * @param nombreComun Nombre con el cual de conoce el animal
     * @param apodo Apodo otargado
     * @param clasificacion Clasificacion de animal
     * @param familia Familia a la que representa la mascota
     * @param genero Genero al cual pertenece el animal
     * @param especie Especie a la que pertenece la mascota
     * @param alimentoPrincipal Alimento del cual se alimenta el animal *
     */

    public MascotaVO(String nombreComun, String apodo, String clasificacion,
            String familia, String genero, String especie, String alimentoPrincipal) {

        this.nombreComun = nombreComun;
        this.apodo = apodo;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.alimentoPrincipal = alimentoPrincipal;

    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    

    public String getNombreComun() {
        return nombreComun;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
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

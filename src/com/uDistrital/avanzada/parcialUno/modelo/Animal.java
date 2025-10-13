/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

/**
 * Clase madre que representa a un animal contiene atributos comunes de un
 * animal
 *
 * @author jeison, Alex
 */
public class Animal {

    protected String nombreComun;
    protected String clasificacion;
    protected String familia;
    protected String genero;
    protected String especie;
    protected String alimentoPrincipal;

    /**
     * Constructor que asigna los valores con un animal
     *
     * @param nombreComun Nombre con el cual de conoce el animal
     * @param clasificacion Clasificacion de animal
     * @param familia Familia a la que representa el animal
     * @param genero Genero al cual pertenece el animal
     * @param especie Especie a la que pertenece el animal
     * @param alimentoPrincipal Aliemnto principal del animal
     */
    public Animal (){}
    public Animal(String nombreComun, String clasificacion, String familia, String genero, String especie, String alimentoPrincipal) {
        this.nombreComun = nombreComun;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.alimentoPrincipal = alimentoPrincipal;
    }

    /**
     * Obtiene el nombre común del animal.
     *
     * @return el nombre común como String.
     */
    public String getNombreComun() {
        return nombreComun;
    }

    /**
     * Establece el nombre común del animal.
     *
     * @param nombreComun el nombre común como String.
     */
    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }

    /**
     * Obtiene la clasificación del animal.
     *
     * @return la clasificación como String.
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * Establece la clasificación del animal.
     *
     * @param clasificacion la clasificación como String.
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * Obtiene la familia a la que pertenece el animal.
     *
     * @return la familia como String.
     */
    public String getFamilia() {
        return familia;
    }

    /**
     * Establece la familia del animal.
     *
     * @param familia la familia como String.
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }

    /**
     * Obtiene el género al que pertenece el animal.
     *
     * @return el género como String.
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género del animal.
     *
     * @param genero el género como String.
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la especie a la que pertenece el animal.
     *
     * @return la especie como String.
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie del animal.
     *
     * @param especie la especie como String.
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene el alimento principal del animal.
     *
     * @return el alimento principal como String.
     */
    public String getAlimentoPrincipal() {
        return alimentoPrincipal;
    }

    /**
     * Establece el alimento principal del animal.
     *
     * @param alimentoPrincipal el alimento principal como String.
     */
    public void setAlimentoPrincipal(String alimentoPrincipal) {
        this.alimentoPrincipal = alimentoPrincipal;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

/**
 *
 * @author jeisoS
 */
public class MascotaVO {

    private String nombreComun;
    private String apodo;
    private String clasificacion;
    private String familia;
    private String genero;
    private String especie;
    private String alimentoPrincipal;

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

}

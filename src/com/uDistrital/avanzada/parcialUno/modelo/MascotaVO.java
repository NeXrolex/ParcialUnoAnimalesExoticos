/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo;

import java.io.Serializable;

/**
 * Representa una mascota en el sistema Extiende de animal
 *
 * @author jeisoS,Alex
 */
public class MascotaVO extends Animal {

    private String apodo; //Identificador especializado de un animal

    /**
     * Constructor encargado de asignar los valores comunes de una mascota
     *
     *
     * @param nombreComun Nombre con el cual de conoce el animal
     * @param apodo Apodo otargado
     * @param clasificacion Clasificacion de animal
     * @param familia Familia a la que representa la mascota
     * @param genero Genero al cual pertenece la mascota
     * @param especie Especie a la que pertenece la mascota
     * @param alimentoPrincipal Alimento del cual se alimenta el animal *
     */
    public MascotaVO(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie,
            String alimentoPrincipal) {
        super(nombreComun, clasificacion, familia, genero, especie,
                alimentoPrincipal);
        this.apodo = apodo;
    }

    /**
     * Devuelve el apodo de la Mascota
     *
     * @return el apodo actual
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Establece el apodo de la mascota
     *
     * @param apodo el nuevo apodo a asignar
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

}

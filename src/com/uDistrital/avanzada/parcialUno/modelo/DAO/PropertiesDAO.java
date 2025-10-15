/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.modelo.DAO;

import com.uDistrital.avanzada.parcialUno.modelo.ArchivoProperties;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.modelo.interfaces.IRead;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 *
 * @author Alex
 */
public class PropertiesDAO implements IRead<MascotaVO> {

    private final ArchivoProperties archivo;

    /**
     * Constructor que recibe la ruta del archivo
     *
     * @param ruta Ruta del archivoProperties
     */
    public PropertiesDAO(String ruta) {
        this.archivo = new ArchivoProperties(ruta);
    }

    @Override
    /**
     * Cumple el contrato de IRead
     *
     * @param apodo Apodo a buscar
     * @return La mascota encontrada o no
     * @throws Excepton Si ocurre un error de lectura del codigo
     *
     */
    public MascotaVO consultar(String apodo) throws Exception {

        Properties p = new Properties();

        //Carga completamenta el archivo en memoria
        try (InputStream in = archivo.open()) {
            p.load(in);
        }

        //Recorre las claves hasta encontrar el apodo 
        for (int i = 1;; i++) {
            /* no tiene techo porque pueden haber 
            mas registros*/
            String base = "mascota" + i + ".";
            String apodoActual = p.getProperty(base + "apodo");
            if (apodoActual == null) {
                break; // no hay más registros
            }
            if (apodoActual.equalsIgnoreCase(apodo)) {
                return construirMascota(p, base);// Si encuentra el apodo
            }
        }

        return null; // no encontrada
    }

    /**
     * Metodo propio del DAo, convierte los bloques en obejtos ylos agrupa en
     * una lista.
     *
     *
     * @return Lista de mascotas cargadas desde properties
     * @throws Exception Si ocurre un error al abrir el archivo
     */
    public List<MascotaVO> listarTodas() throws Exception {
        Properties p = new Properties();
        List<MascotaVO> lista = new ArrayList<>();

        try (InputStream in = archivo.open()) {
            p.load(in);
        }
        //
        for (int i = 1;; i++) {
            String base = "mascota" + i + ".";
            if (p.getProperty(base + "apodo") == null && p.getProperty(base + "nombreComun") == null) {
                // si ni apodo ni nombreComun existen, asumimos que ya no hay más registros
                break; // secorta nuestro ciclo porque ya no hay mas mascotas
            }
            lista.add(construirMascota(p, base));/* trannsforma cada elemento 
            del properties en un objeto,segun la estructura DAO y lo anade
            a la lista*/
        }
        return lista;
    }

    /**
     * Convierte el bloque de datos de properties en objeto de dominio real
     *
     *
     * @param p Conjunto de claves y valores
     * @param base Bloque de datos
     * @return Objeto fisico
     */
    private MascotaVO construirMascota(Properties p, String base) {
        //lee los valores en cada campo
        String apodo = safe(p.getProperty(base + "apodo"));
        String nombreComun = safe(p.getProperty(base + "nombreComun"));
        String clasificacion = safe(p.getProperty(base + "clasificacion"));
        String familia = safe(p.getProperty(base + "familia"));
        String genero = safe(p.getProperty(base + "genero"));
        String especie = safe(p.getProperty(base + "especie"));
        String alimento = safe(p.getProperty(base + "alimentoPrincipal"));

        /*Ese safe es de lo mas importante puesto que algunos campos van a
        estar vacios como indica el enunciado del parcial y no los toma 
        como Null sino que los convierte en cadenas vacias*/
        //Estos siguiendo el patron DAO
        return new MascotaVO(
                apodo,
                nombreComun,
                clasificacion,
                familia,
                genero,
                especie,
                alimento
        );
    }

    /**
     * Convierte valores nulos a cadenas vacías y elimina espacios.
     *
     * @param Valor valor de la propiedad
     * @return cadena no nula
     */
    private static String safe(String valor) {
        return valor == null ? "" : valor.trim();
    }

}

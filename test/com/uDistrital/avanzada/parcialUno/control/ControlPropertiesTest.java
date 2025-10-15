/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Clase de prueba unitaria para los métodos principales de
 * {@link ControlProperties}.
 *
 * <p>
 * Su propósito es verificar que las operaciones sobre las mascotas (carga,
 * consulta y filtrado) funcionen correctamente al manipular los datos
 * provenientes del archivo de propiedades.</p>
 *
 * <p>
 * <b>Nota:</b> Ya que la logica de la clase ControlProperties solo carga datos
 * ya pre establecidos y los gestiona, se logra que el usuario no puede
 * modificarla, por lo que se reduce notablemente la aparicion de bugs.</p>
 *
 * @author Santi
 */
public class ControlPropertiesTest {

    private ControlProperties control;

    @BeforeClass
    public static void iniciarClase() {
        System.out.println("Iniciando pruebas de ControlProperties...");
    }

    @AfterClass
    public static void finalizarClase() {
        System.out.println("Finalizando pruebas de ControlProperties.");
    }

    @Before
    public void setUp() {
        control = new ControlProperties("src/data/mascotas.properties");
    }

    @After
    public void limpiar() {
        control = null;
    }

    /**
     * Prueba del método {@link ControlProperties#cargarTodas()}.
     *
     * <p>
     * Verifica que el método cargue correctamente todas las mascotas desde el
     * archivo de propiedades y las retorne como una lista.</p>
     *
     * @throws Exception si ocurre un error durante la lectura del archivo.
     */
    @Test
    public void testCargarTodas() throws Exception {
        System.out.println("→ Ejecutando testCargarTodas");
        List<MascotaVO> result = control.cargarTodas();
        assertNotNull("El método cargarTodas() no debe retornar null.", result);
    }

    /**
     * Prueba del método {@link ControlProperties#consultarPorApodo(String)}.
     *
     * <p>
     * Evalúa si el método es capaz de encontrar una mascota específica según el
     * apodo proporcionado.</p>
     *
     * @param apodo apodo de la mascota a consultar.
     * @throws Exception si ocurre un error en la búsqueda.
     */
    @Test
    public void testConsultarPorApodo() throws Exception {
        System.out.println("→ Ejecutando testConsultarPorApodo");
        MascotaVO result = control.consultarPorApodo("no_existe");
        assertNull("Debe retornar null si no encuentra una mascota con ese apodo.", result);
    }

    /**
     * Prueba del método {@link ControlProperties#filtrarIncompletas(List)}.
     *
     * <p>
     * Comprueba si el método filtra correctamente aquellas mascotas que no
     * tienen todos sus datos completos y las agrega a una nueva lista.</p>
     *
     * @param lista lista de mascotas cargadas desde el archivo de propiedades.
     */
    @Test
    public void testFiltrarIncompletas() {
        System.out.println("→ Ejecutando testFiltrarIncompletas");
        List<MascotaVO> result = control.filtrarIncompletas(null);
        assertNotNull(result);
        assertTrue("Si la lista es null, debe retornar lista vacía.", result.isEmpty());
    }

    /**
     * Prueba del método {@link ControlProperties#esIncompleta(MascotaVO)}.
     *
     * <p>
     * Valida si el método identifica correctamente una mascota con datos
     * incompletos.</p>
     *
     * @param m objeto {@link MascotaVO} a evaluar.
     */
    @Test
    public void testEsIncompleta() {
        System.out.println("→ Ejecutando testEsIncompleta");
        MascotaVO mascota = new MascotaVO();
        mascota.setApodo("Tom");
        mascota.setNombreComun(null); // falta dato
        boolean resultado = control.esIncompleta(mascota);
        assertTrue("Debe retornar true si la mascota tiene datos faltantes.", resultado);
    }

}

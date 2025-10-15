/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.Animal;
import com.uDistrital.avanzada.parcialUno.modelo.DAO.MascotaDAO;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar los datos de un animal en el sistema
 *
 *
 * @author Alex
 */
public class ControlAnimal {
    private final MascotaDAO dao;

    public ControlAnimal() {
        this.dao = new MascotaDAO();
    }
    /**
     * Recibe listas de mascotas (originales y completadas),
     * las combina y las guarda en la base de datos.
     * @param mascotas
     * @param incompletas
     */
    public void procesarMascotas(List<MascotaVO> mascotas, List<MascotaVO> incompletas) {
        List<MascotaVO> listaPropBD = combinarListas(mascotas, incompletas);
        guardarMascotasEnBD(listaPropBD);
    }
    /**
     * Combina ambas listas, eliminando duplicados por apodo.
     */
    private List<MascotaVO> combinarListas(List<MascotaVO> originales, List<MascotaVO> incompletas) {
        List<MascotaVO> resultado = new ArrayList<>(originales);

        for (MascotaVO m : incompletas) {
            resultado.removeIf(x -> x.getNombreComun().equalsIgnoreCase(m.getNombreComun()));
            resultado.add(m);
        }
        return resultado;
    }
    public void guardarMascotasEnBD(List<MascotaVO> listaPropBD) {
        for (MascotaVO m : listaPropBD) {
            try {
                // Verifica si ya existe la mascota
                MascotaVO existente = dao.consultar(m.getApodo());

                if (existente == null) {
                    dao.insertar(m); // ✅ Si no existe, la insertamos
                } else {
                    dao.modificar(m); // 🔁 Si ya existe, la actualizamos
                }

            } catch (Exception e) {
                throw new RuntimeException("Error al guardar las mascotas en la Base de Datos", e);
            }
        }
    }

    private final MascotaDAO masDao = new MascotaDAO();
    
    /**
     * Registra una mascota en el sistema
     * 
     * @param apodo Apodo de la mascota, identificador
     * @param nombreComun Nombre comun de la mascota
     * @param clasificacion Clasificacion de la mascota
     * @param familia Faamilia de la mascota
     * @param genero Genero de la mascota
     * @param especie Especie de la mascota
     * @param alimentoPrincipal Alimento principal de la mascota
     * @throws Exception Si ocurre un error al registrar la mascota
     */
    public void registrar(String apodo, String nombreComun,
            String clasificacion, String familia, String genero,
            String especie, String alimentoPrincipal) throws Exception {

        validarApodo(apodo);
        //Convierte los datos primitivos en un objeto para manejarlo
        Animal aVO = new MascotaVO(apodo, nombreComun, clasificacion, 
                familia, genero, especie, alimentoPrincipal);
        //Damos la informacion al dao para que trabaje su responsabilidad 
        //de base de detos
        masDao.insertar((MascotaVO) aVO);

    }
    
    /**
     * Permite hacer busquedas e mascotas por medio de su apodo
     * 
     * @param apodo Identificador del animal
     * @return El animal encontrado
     * @throws Exception si ocurre un error al buscar la mascota
     */
    public Animal consultar(String apodo) throws Exception{
        validarApodo(apodo);
        return masDao.consultar(apodo);
    }
    
    /**
     * Se encarga de listar todos los animales disponibles
     * 
     * @return Lista con todos las mascotas
     * @throws Exception Si ocurre un error al momento de listar
     */
    public List<MascotaVO> listarTodas() throws Exception {
        List<MascotaVO> hijos = masDao.listarTodas();
        return new ArrayList<>(hijos); // copia para exponer como List<Animal>
    }
    
    /**
     * Modifica un animal en el sistema
     * 
     * @param apodo Apodo de la mascota 
     * @param nombreComun Nombre comun de la mascota
     * @param clasificacion Classificacion de la mascota
     * @param familia Familia de la madscota
     * @param genero Genero de la mascota
     * @param especie Especie de la mascota
     * @param alimentoPrincipal A;imento principal de la mascota
     * @throws Exception Si ocurre un error
     */
    public void modificar(
            String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, 
            String alimentoPrincipal) throws Exception {
        validarApodo(apodo);
        MascotaVO vo = new MascotaVO(
                apodo, nombreComun, clasificacion, familia, genero,
                especie, alimentoPrincipal);
        masDao.modificar(vo);
    }
    
    /**
     * Elimina una mascota del sistema
     * 
     * @param apodo Apodo de la mascota(Identificador)
     * @throws Exception Si ocurre un error al eliminar la mascota
     */
    public void eliminar(String apodo) throws Exception {
        validarApodo(apodo);
        masDao.eliminar(apodo);
    }
    
    /**
     * Busqueda especialixada de un animal
     * 
     * @param clasificacion Clasificacion 
     * @return Animales con esa clasificacion
     * @throws Exception Si ocurre un error al hacer busqueda
     */
    public List<MascotaVO> consultarPorClasificacion(String clasificacion) 
            throws Exception {
        return new ArrayList<>(masDao.consultarPorClasificacion(clasificacion));
    }
    
    /**
     * Busqueda especialixada de un animal
     * 
     * @param familia Familia de un animal
     * @return Animales con es famila en comun
     * @throws Exception Si ocurre un error al buscar un animal
     */
    public List<MascotaVO> consultarPorFamilia(String familia) 
            throws Exception {
        return new ArrayList<>(masDao.consultarPorFamilia(familia));
    }
    
    /**
     * Busqueda especialixada de un animal
     * 
     * @param tipoAlimento Tipo de alimento
     * @return Animales on ese tipo de alimento en comun 
     * @throws Exception Si ocurre un error al buscar un animal
     */
    public List<MascotaVO> consultarPorTipoAlimento(String tipoAlimento) 
            throws Exception {
        return new ArrayList<>(masDao.consultarPorTipoAlimento(tipoAlimento));
    }
    
    /**
     * Propio de la clase para validar que el identificador existe
     * 
     * @param apodo Identificador de una mascota
     */
    private void validarApodo(String apodo) {
        if (apodo == null || apodo.trim().isEmpty()) {
            throw new IllegalArgumentException("El apodo no puede"
                    + " estar vacío.");
        }
    }

}

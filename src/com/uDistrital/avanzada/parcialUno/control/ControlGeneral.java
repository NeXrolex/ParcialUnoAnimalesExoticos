/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.Animal;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Control general que orquesta toda la lógica del negocio Coordina los
 * diferentes controles especializados y gestiona el flujo principal
 *
 *
 * @author Alex
 */
public final class ControlGeneral {

    /* Controles especializados (vecinos inmediatos) */
    private final ControlProperties cProperties;
    private final ControlRAF cRAF;
    private final ControlSerializacion cSerializacion;
    private final ControlAnimal cAnimal; // NO se modifica esta clase

    /* Vecino de presentación */
    private ControlVista cVista;

    /**
     * Constructor por defecto
     */
    public ControlGeneral() {
        this.cProperties = new ControlProperties("D:\\Proyectos programacion\\Parcial1\\ParcialUnoAnimalesExoticos\\src\\data\\mascotas.properties");
        this.cRAF = new ControlRAF();
        this.cSerializacion = new ControlSerializacion();
        this.cAnimal = new ControlAnimal();

        //ARRANQUE AUTOMÁTICO EN EL EDT Swing
        javax.swing.SwingUtilities.invokeLater(this::iniciarPrograma);
    }

    /**
     * metodo encargado de arrancar el programa
     */
    public void iniciarPrograma() {
        try {
            // ControlGeneral NO crea la vista; sólo crea e inyecta ControlVista
            this.cVista = new ControlVista(this);

            // Carga inicial conforme enunciado
            flujoCargaInicial();
        } catch (Exception ignored) {

        }
    }

    /* =================== Flujo de carga inicial =================== */
    /**
     * genera el flujo del programa
     */
    private void flujoCargaInicial() {
        try {

            // Fallback a .properties: completar antes de habilitar el resto
            List<MascotaVO> desdeProp = cProperties.cargarTodas();
            if (desdeProp == null || desdeProp.isEmpty()) {
//                System.out.println("no se puede");
                return;
            }

            List<MascotaVO> incompletas = cProperties.filtrarIncompletas(desdeProp);
            if (!incompletas.isEmpty()) {
                cVista.mostrarMascotasIncompletas(incompletas); // aquí la vista pedirá datos y nos devolverá
            } else {
                cargarMascotasABD(desdeProp);
            }
        } catch (Exception e) {
           
        }
    }

    /**
     * Recibe mascotas ya completadas por la vista y las inserta en BD.
     *
     * @param completadas lista completada desde la vista
     * @throws Exception error de inserción
     */
    public void aceptarMascotasCompletadas(List<MascotaVO> completadas) throws Exception {
        cargarMascotasABD(completadas);
    }

    /**
     * Verifica la existencia previa de cada mascota por apodo antes de
     * insertarla. Solo registra mascotas nuevas, ignorando duplicados y valores
     * nulos.
     *
     * @param mascotas lista de mascotas a cargar
     * @throws Exception
     */
    private void cargarMascotasABD(List<MascotaVO> mascotas) throws Exception {
        if (mascotas == null) {
            return;
        }
        for (MascotaVO m : mascotas) {
            if (m == null) {
                continue;
            }
            Animal existente = cAnimal.consultar(m.getApodo());
            if (existente == null) {
                cAnimal.registrar(
                        m.getApodo(),
                        m.getNombreComun(),
                        m.getClasificacion(),
                        m.getFamilia(),
                        m.getGenero(),
                        m.getEspecie(),
                        m.getAlimentoPrincipal()
                );
            }
        }
    }

    /* =================== API para la vista: CRUD & consultas =================== */
    /**
     *
     * @param apodo identificador único de la mascota
     * @param nombreComun nombre común del animal
     * @param clasificacion clasificación taxonómica principal
     * @param familia familia taxonómica
     * @param genero género taxonómico
     * @param especie especie taxonómica
     * @param alimento tipo de alimento principal
     * @throws Exception si ya existe una mascota con el mismo apodo o error en
     * registro
     */
    public void registrarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimento) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente != null) {
            throw new Exception("Ya existe una mascota con el apodo: " + apodo);
        }
        cAnimal.registrar(apodo, nombreComun, clasificacion, familia, genero, especie, alimento);
    }

    /**
     * valida la existencia de las mascotas antes de la modificacion
     *
     * @param apodo identificador único de la mascota a modificar
     * @param nombreComun nuevo nombre común
     * @param clasificacion nueva clasificación
     * @param familia nueva familia
     * @param genero nuevo género
     * @param especie nueva especie
     * @param alimento nuevo tipo de alimento
     * @throws Exception si no existe la mascota o error en modificación
     */
    public void modificarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimento) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        cAnimal.modificar(apodo, nombreComun, clasificacion, familia, genero, especie, alimento);
    }

    /**
     * verifica la mascota a eliminar
     *
     * @param apodo identificador único de la mascota a eliminar
     * @throws Exception si no existe la mascota o error en eliminación
     */
    public void eliminarMascota(String apodo) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        cAnimal.eliminar(apodo);
    }

    /**
     * consulta mascota por su apodo
     *
     * @param apodo identificador único de la mascota
     * @return objeto MascotaVO si existe, null en caso contrario
     * @throws Exception si ocurre error durante la consulta
     */
    public MascotaVO consultarMascota(String apodo) throws Exception {
        Animal a = cAnimal.consultar(apodo);
        return (a instanceof MascotaVO) ? (MascotaVO) a : null;
    }

    /**
     * lista las mascotas registradas en el sistema
     *
     * @return lista inmutable de todas las mascotas; lista vacía si no hay
     * datos
     * @throws Exception si ocurre error durante la consulta
     */
    public List<MascotaVO> listarTodasLasMascotas() throws Exception {
        List<MascotaVO> l = cAnimal.listarTodas();
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    /**
     * Consulta mascotas por clasificacion
     *
     * @param clasificacion clasificación a buscar
     * @return lista de mascotas que coinciden con la clasificación; lista vacía
     * si no hay coincidencias
     * @throws Exception si ocurre error durante la consulta
     */
    public List<MascotaVO> consultarPorClasificacion(String clasificacion) throws Exception {
        List<MascotaVO> l = cAnimal.consultarPorClasificacion(clasificacion);
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    /**
     * Consulta mascotas por familia
     *
     * @param familia familia a buscar 
     * @return lista de mascotas que pertenecen a la familia; lista vacía si no
     * hay coincidencias
     * @throws Exception si ocurre error durante la consulta
     */
    public List<MascotaVO> consultarPorFamilia(String familia) throws Exception {
        List<MascotaVO> l = cAnimal.consultarPorFamilia(familia);
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    /**
     * Consulta mascota por alimento
     *
     * @param alimento tipo de alimento 
     * @return lista de mascotas con ese tipo de alimentación; lista vacía si no
     * hay coincidencias
     * @throws Exception si ocurre error durante la consulta
     */
    public List<MascotaVO> consultarPorTipoAlimentoVO(String alimento) throws Exception {
        List<MascotaVO> l = cAnimal.consultarPorTipoAlimento(alimento);
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    /* =================== Persistencias transversales =================== */
    /**
     * Serializa todas las mascotas en una carpeta elegida por el usuario.
     *
     * @param carpetaDestino ruta de carpeta
     * @throws Exception si no hay datos o si falla la escritura
     */
    public void serializarMascotas(String carpetaDestino) throws Exception {
        List<MascotaVO> mascotas = listarTodasLasMascotas();
        if (mascotas.isEmpty()) {
            throw new Exception("No hay mascotas para serializar.");
        }
        cSerializacion.exportar(mascotas, carpetaDestino);
    }

    /**
     * Guarda el estado actual en RAF (llamado al salir). Implementa: limpiar()
     * + guardarTodas(listarTodas()).
     */
    public void guardarEstadoEnRAF() throws Exception {
        List<MascotaVO> todas = listarTodasLasMascotas();
        cRAF.limpiar();                 // dejamos archivo limpio
        cRAF.guardarTodas(todas);       // persistimos todo el estado actual
    }
}

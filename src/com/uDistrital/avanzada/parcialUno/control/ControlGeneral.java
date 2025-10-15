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
     * Constructor por defecto (ajusta la ruta del .properties según tu
     * proyecto).
     */
    public ControlGeneral() {
    this.cProperties    = new ControlProperties("D:\\Proyectos programacion\\Parcial1\\ParcialUnoAnimalesExoticos\\src\\data\\mascotas.properties");
    this.cRAF           = new ControlRAF();
    this.cSerializacion = new ControlSerializacion();
    this.cAnimal        = new ControlAnimal();

    //ARRANQUE AUTOMÁTICO EN EL EDT Swing
    javax.swing.SwingUtilities.invokeLater(this::iniciarPrograma);
}

    /**
     * Arranque del sistema. - Crea ControlVista (que a su vez crea y muestra la
     * VistaPrincipal). - Ejecuta el flujo de carga inicial (RAF -> .properties
     * + completar).
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
    private void flujoCargaInicial() {
        try {
            
            
            // Fallback a .properties: completar antes de habilitar el resto
            List<MascotaVO> desdeProp = cProperties.cargarTodas();
            if (desdeProp == null || desdeProp.isEmpty()) {
                System.out.println("no se puede");
                return;
            }

            List<MascotaVO> incompletas = cProperties.filtrarIncompletas(desdeProp);
            if (!incompletas.isEmpty()) {
                cVista.mostrarMascotasIncompletas(incompletas); // aquí la vista pedirá datos y nos devolverá
            } else {
                cargarMascotasABD(desdeProp);
            }
        }catch (Exception e) { e.printStackTrace(); }
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
     * Inserta en BD evitando duplicados por apodo.
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
    public void registrarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimento) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente != null) {
            throw new Exception("Ya existe una mascota con el apodo: " + apodo);
        }
        cAnimal.registrar(apodo, nombreComun, clasificacion, familia, genero, especie, alimento);
    }

    public void modificarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimento) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        cAnimal.modificar(apodo, nombreComun, clasificacion, familia, genero, especie, alimento);
    }

    public void eliminarMascota(String apodo) throws Exception {
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        cAnimal.eliminar(apodo);
    }

    public MascotaVO consultarMascota(String apodo) throws Exception {
        Animal a = cAnimal.consultar(apodo);
        return (a instanceof MascotaVO) ? (MascotaVO) a : null;
    }

    public List<MascotaVO> listarTodasLasMascotas() throws Exception {
        List<MascotaVO> l = cAnimal.listarTodas();
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    public List<MascotaVO> consultarPorClasificacion(String clasificacion) throws Exception {
        List<MascotaVO> l = cAnimal.consultarPorClasificacion(clasificacion);
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

    public List<MascotaVO> consultarPorFamilia(String familia) throws Exception {
        List<MascotaVO> l = cAnimal.consultarPorFamilia(familia);
        return (l == null) ? Collections.emptyList() : new ArrayList<>(l);
    }

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

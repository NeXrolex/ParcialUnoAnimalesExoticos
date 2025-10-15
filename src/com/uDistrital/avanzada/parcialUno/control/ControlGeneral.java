/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.Animal;
import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.util.List;

/**
 * Control general que orquesta toda la lógica del negocio
 * Coordina los diferentes controles especializados y gestiona el
 * flujo principal
 * 
 * 
 * @author Alex
 */
public class ControlGeneral {
    
    //Conoce a todos sus controles cercanos
    private ControlProperties cProperties;
    private ControlVista cVista;
    private final ControlAnimal cAnimal;

    public ControlGeneral() {
        this.cProperties = new ControlProperties("src/data/mascotas.properties");
        VistaPrincipal vista = new VistaPrincipal();
        this.cVista = new ControlVista(vista, this);
        this.cAnimal = new ControlAnimal();
    }    
    public void iniciarPrograma() {
        // Cargar mascotas
        List<MascotaVO> mascotas = cProperties.cargarMascotas();

        // Verificar mascotas incompletas
        List<MascotaVO> incompletas = cProperties.obtenerMascotasIncompletas(mascotas);
        
        cVista.adminMascotasIncompletas(incompletas);       

       cAnimal.procesarMascotas(mascotas, incompletas);
    }


    private ControlRAF cRAF;
    private ControlSerializacion cSerializacion;
    private ControlAnimal cAnimal;

    /**
     * Constructor que inicializa los controles especializados
     */
    public ControlGeneral() {
        this.cProperties = new ControlProperties
        ("src/data/mascotas.properties");//El profe nos menciono en clase que 
        //para este podiamos quemar la ruta
        this.cRAF = new ControlRAF();
        this.cSerializacion = new ControlSerializacion();
        this.cAnimal = new ControlAnimal();
        
        iniciarPrograma();
    }

    /**
     * Inicia el programa completo
     * - Crea la vista
     * - Carga datos desde properties o RAF
     * - Completa mascotas incompletas
     * - Muestra la interfaz
     */
    public void iniciarPrograma() {
        try {
            // 1. Crear e inicializar la vista
            VistaPrincipal vista = new VistaPrincipal();
            this.cVista = new ControlVista(vista, this);
            
            // 2. Verificar si hay datos previos en RAF
            if (cRAF.rafTieneDatos()) {
                cargarDesdeRAF();
            } else {
                cargarDesdeProperties();
            }
            
            // 3. Hacer visible la vista
            vista.setVisible(true);
            
        } catch (Exception e) {
            System.err.println("Error al iniciar programa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga mascotas desde el archivo properties
     * Verifica si hay mascotas incompletas y solicita completarlas
     */
    private void cargarDesdeProperties() {
        try {
            // Cargar mascotas desde properties
            List<MascotaVO> mascotas = cProperties.cargarMascotas();
            
            if (mascotas.isEmpty()) {

                return;
            }
            
            // Verificar mascotas incompletas
            List<MascotaVO> incompletas = cProperties.obtenerMascotasIncompletas(mascotas);
            
            if (!incompletas.isEmpty()) {
                cVista.mostrarMascotasIncompletas(incompletas);
                // El ControlVista se encargará de completar las incompletas
            } else {
                // Todas están completas, cargar a BD
                cargarMascotasABD(mascotas);
            }
            
        } catch (Exception e) {
        }
    }

    /**
     * Carga mascotas desde el archivo RAF a la base de datos
     */
    private void cargarDesdeRAF() {
        try {
            List<MascotaVO> mascotas = cRAF.cargarMascotasDesdeRAF();
            
            if (mascotas.isEmpty()) {
                cargarDesdeProperties();
                return;
            }
            
            cargarMascotasABD(mascotas);

            
        } catch (Exception e) {
            cargarDesdeProperties();
        }
    }

    /**
     * Carga una lista de mascotas a la base de datos
     * Evita duplicados
     * 
     * @param mascotas Lista de mascotas a cargar
     */
    private void cargarMascotasABD(List<MascotaVO> mascotas) {
        int cargadas = 0;
        int duplicadas = 0;
        
        for (MascotaVO mascota : mascotas) {
            try {
                // Verificar si ya existe
                Animal existente = cAnimal.consultar(mascota.getApodo());
                
                if (existente == null) {
                    // No existe, insertar
                    cAnimal.registrar(
                        mascota.getApodo(),
                        mascota.getNombreComun(),
                        mascota.getClasificacion(),
                        mascota.getFamilia(),
                        mascota.getGenero(),
                        mascota.getEspecie(),
                        mascota.getAlimentoPrincipal()
                    );
                    cargadas++;
                } else {
                    duplicadas++;
                }
            } catch (Exception e) {
                System.err.println("Error al cargar mascota " + mascota.getApodo() + ": " + e.getMessage());
            }
        }
        
    }

    // ==================== OPERACIONES CRUD ====================
    
    /**
     * Registra una nueva mascota en la base de datos
     * Valida que no exista una mascota con los mismos datos
     * 
     * @param apodo Apodo de la mascota (identificador único)
     * @param nombreComun Nombre común del animal
     * @param clasificacion Clasificación taxonómica
     * @param familia Familia del animal
     * @param genero Género del animal
     * @param especie Especie del animal
     * @param alimentoPrincipal Tipo de alimento principal
     * @throws Exception Si ocurre un error al registrar
     */
    public void registrarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimentoPrincipal) throws Exception {
        
        // Validar que no exista una mascota con el mismo apodo
        Animal existente = cAnimal.consultar(apodo);
        if (existente != null) {
            throw new Exception("Ya existe una mascota con el apodo: " + apodo);
        }
        
        // Validar que no exista una mascota idéntica
        List<MascotaVO> todas = cAnimal.listarTodas();
        for (MascotaVO m : todas) {
            if (sonIguales(m, nombreComun, clasificacion, familia, genero, especie, alimentoPrincipal)) {
                throw new Exception("Ya existe una mascota idéntica en la base de datos.");
            }
        }
        
        // Registrar la mascota
        cAnimal.registrar(apodo, nombreComun, clasificacion, familia, genero, especie, alimentoPrincipal);
    }

    /**
     * Modifica los datos de una mascota existente
     * No permite modificar familia, género ni especie
     * 
     * @param apodo Apodo de la mascota a modificar
     * @param nombreComun Nuevo nombre común
     * @param clasificacion Nueva clasificación
     * @param familia Familia (no se modifica, pero se valida)
     * @param genero Género (no se modifica, pero se valida)
     * @param especie Especie (no se modifica, pero se valida)
     * @param alimentoPrincipal Nuevo alimento principal
     * @throws Exception Si ocurre un error al modificar
     */
    public void modificarMascota(String apodo, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimentoPrincipal) throws Exception {
        
        // Verificar que la mascota existe
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        
        // Modificar (el ControlAnimal ya maneja la modificación)
        cAnimal.modificar(apodo, nombreComun, clasificacion, familia, genero, especie, alimentoPrincipal);
    }

    /**
     * Elimina una mascota de la base de datos
     * 
     * @param apodo Apodo de la mascota a eliminar
     * @throws Exception Si ocurre un error al eliminar
     */
    public void eliminarMascota(String apodo) throws Exception {
        // Verificar que la mascota existe
        Animal existente = cAnimal.consultar(apodo);
        if (existente == null) {
            throw new Exception("No existe una mascota con el apodo: " + apodo);
        }
        
        cAnimal.eliminar(apodo);
    }

    /**
     * Consulta una mascota por su apodo
     * 
     * @param apodo Apodo de la mascota
     * @return La mascota encontrada o null
     * @throws Exception Si ocurre un error en la consulta
     */
    public MascotaVO consultarMascota(String apodo) throws Exception {
        Animal animal = cAnimal.consultar(apodo);
        return (MascotaVO) animal;
    }

    /**
     * Lista todas las mascotas de la base de datos
     * 
     * @return Lista de todas las mascotas
     * @throws Exception Si ocurre un error al listar
     */
    public List<MascotaVO> listarTodasLasMascotas() throws Exception {
        return cAnimal.listarTodas();
    }

    /**
     * Consulta mascotas por clasificación
     * 
     * @param clasificacion Clasificación a buscar
     * @return Lista de mascotas con esa clasificación
     * @throws Exception Si ocurre un error en la consulta
     */
    public List<MascotaVO> consultarPorClasificacion(String clasificacion) throws Exception {
        return cAnimal.consultarPorClasificacion(clasificacion);
    }

    /**
     * Consulta mascotas por familia
     * 
     * @param familia Familia a buscar
     * @return Lista de mascotas de esa familia
     * @throws Exception Si ocurre un error en la consulta
     */
    public List<MascotaVO> consultarPorFamilia(String familia) throws Exception {
        return cAnimal.consultarPorFamilia(familia);
    }

    /**
     * Consulta mascotas por tipo de alimento
     * 
     * @param tipoAlimento Tipo de alimento a buscar
     * @return Lista de mascotas con ese tipo de alimento
     * @throws Exception Si ocurre un error en la consulta
     */
    public List<MascotaVO> consultarPorTipoAlimentoVO(String tipoAlimento) throws Exception {
    return cAnimal.consultarPorTipoAlimento(tipoAlimento);
}

    // ==================== OPERACIONES DE PERSISTENCIA ====================
    
    /**
     * Guarda el estado actual de la base de datos en el archivo RAF
     * Se ejecuta al salir del programa
     * 
     * @throws Exception Si ocurre un error al guardar
     */
    public void guardarEstadoEnRAF() throws Exception {
        cRAF.guardarEstadoBDEnRAF();
    }

    /**
     * Serializa las mascotas de la base de datos
     * Omite el campo de alimento principal según requerimientos
     * 
     * @param carpetaDestino Carpeta donde guardar el archivo serializado
     * @throws Exception Si ocurre un error al serializar
     */
    public void serializarMascotas(String carpetaDestino) throws Exception {
        List<MascotaVO> mascotas = cAnimal.listarTodas();
        
        if (mascotas.isEmpty()) {
            throw new Exception("No hay mascotas para serializar.");
        }
        
        cSerializacion.exportar(mascotas, carpetaDestino);
    }

    // ==================== MÉTODOS AUXILIARES ====================
    
    /**
     * Verifica si una mascota tiene los mismos datos que los proporcionados
     * 
     * @param mascota Mascota a comparar
     * @param nombreComun Nombre común a comparar
     * @param clasificacion Clasificación a comparar
     * @param familia Familia a comparar
     * @param genero Género a comparar
     * @param especie Especie a comparar
     * @param alimentoPrincipal Alimento a comparar
     * @return true si todos los campos son iguales
     */
    private boolean sonIguales(MascotaVO mascota, String nombreComun, String clasificacion,
            String familia, String genero, String especie, String alimentoPrincipal) {
        return mascota.getNombreComun().equalsIgnoreCase(nombreComun)
                && mascota.getClasificacion().equalsIgnoreCase(clasificacion)
                && mascota.getFamilia().equalsIgnoreCase(familia)
                && mascota.getGenero().equalsIgnoreCase(genero)
                && mascota.getEspecie().equalsIgnoreCase(especie)
                && mascota.getAlimentoPrincipal().equalsIgnoreCase(alimentoPrincipal);
    }

    /**
     * Convierte una lista de Animal a lista de MascotaVO
     * 
     * @param animales Lista de animales
     * @return Lista de MascotaVO
     */
    private List<MascotaVO> convertirAMascotaVO(List<Animal> animales) {
        List<MascotaVO> mascotas = new java.util.ArrayList<>();
        for (Animal a : animales) {
            if (a instanceof MascotaVO) {
                mascotas.add((MascotaVO) a);
            }
        }
        return mascotas;
    }

    /**
     * Completa los datos de una mascota incompleta
     * 
     * @param mascota Mascota con datos incompletos
     * @throws Exception Si ocurre un error al completar
     */
    public void completarMascota(MascotaVO mascota) throws Exception {
        // Registrar la mascota completada en la BD
        cAnimal.registrar(
            mascota.getApodo(),
            mascota.getNombreComun(),
            mascota.getClasificacion(),
            mascota.getFamilia(),
            mascota.getGenero(),
            mascota.getEspecie(),
            mascota.getAlimentoPrincipal()
        );
    }
}

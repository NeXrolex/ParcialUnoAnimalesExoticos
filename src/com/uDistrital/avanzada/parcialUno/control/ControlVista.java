/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Control de la vista - Maneja la lógica de presentación
 * Separa los eventos de la vista y coordina con ControlGeneral
 * Responsabilidades:
 * - Validar entradas del usuario
 * - Coordinar eventos de botones
 * - Formatear datos para mostrar
 * - Gestionar diálogos de usuario
 * 
 * @author Alex
 */
public class ControlVista {

    private VistaPrincipal vista;
    private ControlGeneral cGeneral;
    
    // Listeners especializados (separación de eventos)
    private ListenerAdicionar listenerAdicionar;
    private ListenerConsultar listenerConsultar;
    private ListenerModificar listenerModificar;
    private ListenerEliminar listenerEliminar;
    private ListenerSerializar listenerSerializar;
    private ListenerLimpiar listenerLimpiar;
    private ListenerSalir listenerSalir;

    /**
     * Constructor que inicializa el control de la vista
     * Configura todos los listeners de los botones
     * 
     * @param vista Vista principal de la aplicación
     * @param cGeneral Control general para operaciones de negocio
     */
    public ControlVista(VistaPrincipal vista, ControlGeneral cGeneral) {
        this.vista = vista;
        this.cGeneral = cGeneral;
        inicializarListeners();
    }

    /**
     * Inicializa y asigna todos los listeners a los botones
     * Implementa el principio de separación de eventos
     */
    private void inicializarListeners() {
        listenerAdicionar = new ListenerAdicionar();
        listenerConsultar = new ListenerConsultar();
        listenerModificar = new ListenerModificar();
        listenerEliminar = new ListenerEliminar();
        listenerSerializar = new ListenerSerializar();
        listenerLimpiar = new ListenerLimpiar();
        listenerSalir = new ListenerSalir();
        
        vista.asignarListenerAdicionar(listenerAdicionar);
        vista.asignarListenerConsultar(listenerConsultar);
        vista.asignarListenerModificar(listenerModificar);
        vista.asignarListenerEliminar(listenerEliminar);
        vista.asignarListenerSerializar(listenerSerializar);
        vista.asignarListenerLimpiar(listenerLimpiar);
        vista.asignarListenerSalir(listenerSalir);
    }

    /**
     * Muestra un mensaje informativo en consola
     * 
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println("INFO: " + mensaje);
    }

    /**
     * Muestra un error al usuario
     * 
     * @param mensaje Mensaje de error
     */
    public void mostrarError(String mensaje) {
        vista.mostrarMensaje(mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra una advertencia al usuario
     * 
     * @param mensaje Mensaje de advertencia
     */
    public void mostrarAdvertencia(String mensaje) {
        vista.mostrarMensaje(mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra información al usuario
     * 
     * @param mensaje Mensaje informativo
     */
    public void mostrarInformacion(String mensaje) {
        vista.mostrarMensaje(mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Carga los datos de una mascota en los campos de la vista
     * 
     * @param mascota Mascota a cargar
     */
    private void cargarDatosEnVista(MascotaVO mascota) {
        if (mascota.getApodo() != null && !mascota.getApodo().trim().isEmpty()) {
            vista.setApodo(mascota.getApodo());
        }
        if (mascota.getNombreComun() != null && !mascota.getNombreComun().trim().isEmpty()) {
            vista.setNombreComun(mascota.getNombreComun());
        }
        if (mascota.getFamilia() != null && !mascota.getFamilia().trim().isEmpty()) {
            vista.setFamilia(mascota.getFamilia());
        }
        if (mascota.getGenero() != null && !mascota.getGenero().trim().isEmpty()) {
            vista.setGenero(mascota.getGenero());
        }
        if (mascota.getEspecie() != null && !mascota.getEspecie().trim().isEmpty()) {
            vista.setEspecie(mascota.getEspecie());
        }
    }

    /**
     * Actualiza los datos de una mascota desde los campos de la vista
     * 
     * @param mascota Mascota a actualizar
     */
    private void actualizarMascotaDesdeVista(MascotaVO mascota) {
        if (esVacio(mascota.getApodo())) {
            mascota.setApodo(vista.getApodo());
        }
        if (esVacio(mascota.getNombreComun())) {
            mascota.setNombreComun(vista.getNombreComun());
        }
        if (esVacio(mascota.getClasificacion())) {
            mascota.setClasificacion(vista.getClasificacion());
        }
        if (esVacio(mascota.getFamilia())) {
            mascota.setFamilia(vista.getFamilia());
        }
        if (esVacio(mascota.getGenero())) {
            mascota.setGenero(vista.getGenero());
        }
        if (esVacio(mascota.getEspecie())) {
            mascota.setEspecie(vista.getEspecie());
        }
        if (esVacio(mascota.getAlimentoPrincipal())) {
            mascota.setAlimentoPrincipal(vista.getAlimento());
        }
    }

    /**
     * Obtiene una descripción de los campos incompletos de una mascota
     * 
     * @param mascota Mascota a verificar
     * @return String con los campos incompletos
     */
    private String getCamposIncompletos(MascotaVO mascota) {
        StringBuilder campos = new StringBuilder();
        
        if (esVacio(mascota.getApodo())) campos.append("Apodo, ");
        if (esVacio(mascota.getNombreComun())) campos.append("Nombre, ");
        if (esVacio(mascota.getClasificacion())) campos.append("Clasificación, ");
        if (esVacio(mascota.getFamilia())) campos.append("Familia, ");
        if (esVacio(mascota.getGenero())) campos.append("Género, ");
        if (esVacio(mascota.getEspecie())) campos.append("Especie, ");
        if (esVacio(mascota.getAlimentoPrincipal())) campos.append("Alimento, ");
        
        String resultado = campos.toString();
        return resultado.isEmpty() ? "Ninguno" : resultado.substring(0, resultado.length() - 2);
    }

    /**
     * Valida que una mascota tenga todos sus campos completos
     * 
     * @param mascota Mascota a validar
     * @return true si todos los campos están completos
     */
    private boolean validarMascotaCompleta(MascotaVO mascota) {
        return !esVacio(mascota.getApodo())
                && !esVacio(mascota.getNombreComun())
                && !esVacio(mascota.getClasificacion())
                && !esVacio(mascota.getFamilia())
                && !esVacio(mascota.getGenero())
                && !esVacio(mascota.getEspecie())
                && !esVacio(mascota.getAlimentoPrincipal());
    }

    /**
     * Valida los campos de entrada de la vista
     * 
     * @return true si todos los campos requeridos están completos
     */
    private boolean validarCamposVista() {
        if (esVacio(vista.getApodo())) {
            mostrarError("El apodo es obligatorio.");
            return false;
        }
        if (esVacio(vista.getNombreComun())) {
            mostrarError("El nombre común es obligatorio.");
            return false;
        }
        if (esVacio(vista.getFamilia())) {
            mostrarError("La familia es obligatoria.");
            return false;
        }
        if (esVacio(vista.getGenero())) {
            mostrarError("El género es obligatorio.");
            return false;
        }
        if (esVacio(vista.getEspecie())) {
            mostrarError("La especie es obligatoria.");
            return false;
        }
        return true;
    }

    /**
     * Muestra una lista de mascotas en el área de resultados
     * 
     * @param mascotas Lista de mascotas a mostrar
     */
    private void mostrarListaMascotas(List<MascotaVO> mascotas) {
        if (mascotas.isEmpty()) {
            vista.mostrarResultado("No se encontraron mascotas.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS (").append(mascotas.size()).append(" mascota(s)) ===\n\n");
        
        vista.mostrarResultado(sb.toString());
    }

    /**
     * Verifica si un string está vacío o es nulo
     * 
     * @param valor String a verificar
     * @return true si está vacío o es nulo
     */
    private boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    // listeners internos
    
    /**
     * Listener para el botón Adicionar
     * Valida y registra una nueva mascota
     */
    private class ListenerAdicionar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Validar campos
                if (!validarCamposVista()) {
                    return;
                }
                
                // Obtener datos de la vista
                String apodo = vista.getApodo();
                String nombreComun = vista.getNombreComun();
                String clasificacion = vista.getClasificacion();
                String familia = vista.getFamilia();
                String genero = vista.getGenero();
                String especie = vista.getEspecie();
                String alimento = vista.getAlimento();
                
                // Registrar en el control general
                cGeneral.registrarMascota(apodo, nombreComun, clasificacion,
                        familia, genero, especie, alimento);
                
                mostrarInformacion("Mascota registrada exitosamente.");
                vista.limpiarCampos();
                
                // Actualizar lista
                List<MascotaVO> todas = cGeneral.listarTodasLasMascotas();
                mostrarListaMascotas(todas);
                
            } catch (Exception ex) {
                mostrarError("Error al adicionar mascota: " + ex.getMessage());
            }
        }
    }

    /**
     * Listener para el botón Consultar
     * Realiza consultas según el criterio seleccionado
     */
    private class ListenerConsultar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String criterio = vista.getCriterioConsulta();
                String valor = vista.getValorConsulta();
                
                if (esVacio(valor)) {
                    mostrarError("Debe ingresar un valor para consultar.");
                    return;
                }
                
                List<MascotaVO> resultados;
                
                switch (criterio) {
                    case "Apodo":
                        MascotaVO mascota = cGeneral.consultarMascota(valor);
                        resultados = new java.util.ArrayList<>();
                        if (mascota != null) {
                            resultados.add(mascota);
                            // Cargar datos en la vista
                            vista.setApodo(mascota.getApodo());
                            vista.setNombreComun(mascota.getNombreComun());
                            vista.setFamilia(mascota.getFamilia());
                            vista.setGenero(mascota.getGenero());
                            vista.setEspecie(mascota.getEspecie());
                        }
                        break;
                    case "Clasificación":
                        resultados = cGeneral.consultarPorClasificacion(valor);
                        break;
                    case "Familia":
                        resultados = cGeneral.consultarPorFamilia(valor);
                        break;
                    case "Alimento":
                        resultados = cGeneral.consultarPorTipoAlimentoVO(valor);
                        break;
                    default:
                        resultados = cGeneral.listarTodasLasMascotas();
                }
                
                mostrarListaMascotas(resultados);
                
            } catch (Exception ex) {
                mostrarError("Error al consultar: " + ex.getMessage());
            }
        }
    }

    /**
     * Listener para el botón Modificar
     * Modifica los datos de una mascota existente
     */
    private class ListenerModificar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Validar campos
                if (!validarCamposVista()) {
                    return;
                }
                
                String apodo = vista.getApodo();
                
                // Verificar que la mascota existe
                MascotaVO existente = cGeneral.consultarMascota(apodo);
                if (existente == null) {
                    mostrarError("No existe una mascota con ese apodo. " +
                               "Consulte primero la mascota a modificar.");
                    return;
                }
                
                // Confirmar modificación
                int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Está seguro de modificar la mascota: " + apodo + "?\n" +
                    "NOTA: No se puede modificar Familia, Género ni Especie.",
                    "Confirmar Modificación",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }
                
                // Obtener datos de la vista
                String nombreComun = vista.getNombreComun();
                String clasificacion = vista.getClasificacion();
                String alimento = vista.getAlimento();
                
                // Mantener familia, género y especie originales
                String familia = existente.getFamilia();
                String genero = existente.getGenero();
                String especie = existente.getEspecie();
                
                // Modificar
                cGeneral.modificarMascota(apodo, nombreComun, clasificacion,
                        familia, genero, especie, alimento);
                
                mostrarInformacion("Mascota modificada exitosamente.");
                
                // Mostrar mascota actualizada
                MascotaVO actualizada = cGeneral.consultarMascota(apodo);
                List<MascotaVO> resultado = new java.util.ArrayList<>();
                resultado.add(actualizada);
                mostrarListaMascotas(resultado);
                
            } catch (Exception ex) {
                mostrarError("Error al modificar mascota: " + ex.getMessage());
            }
        }
    }

    /**
     * Listener para el botón Eliminar
     * Elimina una mascota de la base de datos
     */
    private class ListenerEliminar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String apodo = vista.getApodo();
                
                if (esVacio(apodo)) {
                    mostrarError("Debe consultar o ingresar el apodo de la mascota a eliminar.");
                    return;
                }
                
                // Verificar que existe
                MascotaVO mascota = cGeneral.consultarMascota(apodo);
                if (mascota == null) {
                    mostrarError("No existe una mascota con ese apodo.");
                    return;
                }
                
                // Confirmar eliminación
                int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Está seguro de eliminar la mascota: " + apodo + "?\n" +
                    "Esta acción no se puede deshacer.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (confirmacion != JOptionPane.YES_OPTION) {
                    return;
                }
                
                // Eliminar
                cGeneral.eliminarMascota(apodo);
                
                mostrarInformacion("Mascota eliminada exitosamente.");
                vista.limpiarCampos();
                
                // Mostrar lista actualizada
                List<MascotaVO> todas = cGeneral.listarTodasLasMascotas();
                mostrarListaMascotas(todas);
                
            } catch (Exception ex) {
                mostrarError("Error al eliminar mascota: " + ex.getMessage());
            }
        }
    }

    /**
     * Listener para el botón Serializar
     * Serializa las mascotas a un archivo
     */
    private class ListenerSerializar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Abrir diálogo para seleccionar carpeta
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setDialogTitle("Seleccione carpeta de destino");
                
                int resultado = fileChooser.showSaveDialog(vista);
                
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    String carpeta = fileChooser.getSelectedFile().getAbsolutePath();
                    
                    // Serializar
                    cGeneral.serializarMascotas(carpeta);
                    
                    mostrarInformacion(
                        "Mascotas serializadas exitosamente en:\n" + carpeta +
                        "\n\nNOTA: El archivo NO incluye el campo 'Alimento Principal' " +
                        "según requerimientos del IDPYBA."
                    );
                }
                
            } catch (Exception ex) {
                mostrarError("Error al serializar: " + ex.getMessage());
            }
        }
    }

    /**
     * Listener para el botón Limpiar
     * Limpia todos los campos de entrada
     */
    private class ListenerLimpiar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            vista.limpiarCampos();
            vista.mostrarResultado("");
            mostrarMensaje("Campos limpiados.");
        }
    }

    /**
     * Listener para el botón Salir
     * Guarda el estado y cierra la aplicación
     */
    private class ListenerSalir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Confirmar salida
                int confirmacion = JOptionPane.showConfirmDialog(
                    vista,
                    "¿Está seguro de salir?\n" +
                    "Se guardará el estado actual en el archivo RAF.",
                    "Confirmar Salida",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Guardar estado en RAF
                    cGeneral.guardarEstadoEnRAF();
                    
                    mostrarInformacion("Estado guardado exitosamente. ¡Hasta pronto!");
                    
                    // Cerrar aplicación
                    System.exit(0);
                }
                
            } catch (Exception ex) {
                mostrarError("Error al guardar estado: " + ex.getMessage());
                
                // Preguntar si desea salir sin guardar
                int forzar = JOptionPane.showConfirmDialog(
                    vista,
                    "Ocurrió un error al guardar. ¿Desea salir sin guardar?",
                    "Error al Guardar",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (forzar == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Este método queda para compatibilidad, pero los eventos
        // son manejados por los listeners especializados internos
    }
}

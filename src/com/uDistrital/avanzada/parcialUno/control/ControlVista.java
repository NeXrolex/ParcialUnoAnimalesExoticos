/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.control;

import com.uDistrital.avanzada.parcialUno.modelo.MascotaVO;
import com.uDistrital.avanzada.parcialUno.vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Control de la vista - Maneja la lógica de presentación Separa los eventos de
 * la vista y coordina con ControlGeneral Responsabilidades: - Validar entradas
 * del usuario - Coordinar eventos de botones - Formatear datos para mostrar -
 * Gestionar diálogos de usuario
 *
 * * <p>
 * <b>Responsabilidades principales:</b></p>
 * <ul>
 * <li>Escuchar y responder a los eventos de la vista (botones y
 * formularios).</li>
 * <li>Validar entradas de usuario antes de enviarlas al controlador
 * general.</li>
 * <li>Invocar métodos del `ControlGeneral` para realizar operaciones CRUD y de
 * serialización.</li>
 * <li>Mostrar mensajes e información procesada a través de la vista.</li>
 * <li>Coordinar el flujo de datos entre la vista y el modelo
 * (`MascotaVO`).</li>
 * </ul>
 *
 *
 * @author Alex
 */
public final class ControlVista implements ActionListener {

    /**
     * Referencia a sus dos vecinos la interfaz grafica y logica del programa
     */
    private final VistaPrincipal vista;
    private final ControlGeneral cGeneral;

    /// --------------------------- COMANDOS DE ACCIÓN --------------------------- //
    private static final String CMD_ADICIONAR = "ADICIONAR";
    private static final String CMD_CONSULTAR = "CONSULTAR";
    private static final String CMD_MODIFICAR = "MODIFICAR";
    private static final String CMD_ELIMINAR = "ELIMINAR";
    private static final String CMD_SERIALIZAR = "SERIALIZAR";
    private static final String CMD_LIMPIAR = "LIMPIAR";
    private static final String CMD_SALIR = "SALIR";

    /**
     * Constructor principal.
     *
     * El ControlGeneral crea este controlador; aquí se crea y muestra la vista.
     * Recibe la inyeccion del controlGneral
     *
     * * <p>
     * Inicializa la vista, registra los listeners y comandos asociados a cada
     * acción de usuario.</p>
     *
     */
    public ControlVista(ControlGeneral cGeneral) {
        this.cGeneral = cGeneral;
        this.vista = new VistaPrincipal(); // no se cambia el layout existente
        registrarListenersYComandos();
        this.vista.setVisible(true);//Mostramos la vista
    }

    /**
     * Registra todos los listeners de botones de la vista y asigna los comandos
     * de acción correspondientes.
     */
    private void registrarListenersYComandos() {
        // Listener único
        vista.asignarListenerAdicionar(this);
        vista.asignarListenerConsultar(this);
        vista.asignarListenerModificar(this);
        vista.asignarListenerEliminar(this);
        vista.asignarListenerSerializar(this);
        vista.asignarListenerLimpiar(this);
        vista.asignarListenerSalir(this);

        // ActionCommands (si ya los tienes, puedes omitir estas llamadas)
        vista.asignarActionCommandAdicionar(CMD_ADICIONAR);
        vista.asignarActionCommandConsultar(CMD_CONSULTAR);
        vista.asignarActionCommandModificar(CMD_MODIFICAR);
        vista.asignarActionCommandEliminar(CMD_ELIMINAR);
        vista.asignarActionCommandSerializar(CMD_SERIALIZAR);
        vista.asignarActionCommandLimpiar(CMD_LIMPIAR);
        vista.asignarActionCommandSalir(CMD_SALIR);
    }

    /**
     * Método principal de respuesta a eventos.
     *
     * <p>
     * Evalúa el comando de acción del evento y ejecuta el método
     * correspondiente (adicionar, consultar, etc.).</p>
     *
     * @param e evento capturado desde la interfaz gráfica.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand() == null ? "" : e.getActionCommand();
        switch (cmd) {
            case CMD_ADICIONAR:
                onAdicionar();
                break;
            case CMD_CONSULTAR:
                onConsultar();
                break;
            case CMD_MODIFICAR:
                onModificar();
                break;
            case CMD_ELIMINAR:
                onEliminar();
                break;
            case CMD_SERIALIZAR:
                onSerializar();
                break;
            case CMD_LIMPIAR:
                onLimpiar();
                break;
            case CMD_SALIR:
                onSalir();
                break;
            default:
                /* no-op */ break;
        }
    }

    /* =============================== helpers =============================== */
    /**
     * Verifica si una cadena es nula o vacía.
     *
     * @param s texto a evaluar.
     * @return true si la cadena está vacía o solo contiene espacios.
     */
    private boolean esVacio(String s) {
        return s == null || s.trim().isEmpty();
    }

    /**
     * Valida los campos obligatorios en la vista antes de procesar una acción.
     *
     * @return true si todos los campos requeridos están completos y false si de
     * lo contrario aun faltan datos.
     */
    private boolean validarObligatorios() {
        if (esVacio(vista.getApodo())) {
            vista.error("El apodo es obligatorio.");
            return false;
        }
        if (esVacio(vista.getNombreComun())) {
            vista.error("El nombre común es obligatorio.");
            return false;
        }
        if (esVacio(vista.getFamilia())) {
            vista.error("La familia es obligatoria.");
            return false;
        }
        if (esVacio(vista.getGenero())) {
            vista.error("El género es obligatorio.");
            return false;
        }
        if (esVacio(vista.getEspecie())) {
            vista.error("La especie es obligatoria.");
            return false;
        }
        return true;
    }

    /**
     * Muestra en la vista una lista formateada de mascotas.
     *
     * @param mascotas lista de {@link MascotaVO} a mostrar, permite refrescar
     * la informacion visual que el usuario ve en pantalla.
     */
    private void pintarLista(List<MascotaVO> mascotas) {
        if (mascotas == null || mascotas.isEmpty()) {
            vista.mostrarResultado("No se encontraron mascotas.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("=== RESULTADOS (").append(mascotas.size()).append(") ===\n");
        for (MascotaVO m : mascotas) {
            sb.append("- ").append(m.getApodo()).append(" | ")
                    .append(m.getNombreComun()).append(" | ")
                    .append(m.getClasificacion()).append(" | ")
                    .append(m.getFamilia()).append(" | ")
                    .append(m.getGenero()).append(" | ")
                    .append(m.getEspecie()).append(" | ALIM: ")
                    .append(m.getAlimentoPrincipal()).append('\n');
        }
        vista.mostrarResultado(sb.toString());
    }

    /**
     * Carga los datos de una mascota en los campos de la vista.
     *
     * <p>
     * Este método se utiliza principalmente después de una consulta individual,
     * para que los datos del objeto {@link MascotaVO} aparezcan en los campos
     * de texto del formulario, facilitando operaciones posteriores como la
     * modificación o eliminación.</p>
     *
     * @param m Objeto {@link MascotaVO} cuyos datos serán cargados en la vista.
     */
    private void cargarDatosEnVista(MascotaVO m) {
        if (m == null) {
            return;
        }
        if (!esVacio(m.getApodo())) {
            vista.setApodo(m.getApodo());
        }
        if (!esVacio(m.getNombreComun())) {
            vista.setNombreComun(m.getNombreComun());
        }
        if (!esVacio(m.getFamilia())) {
            vista.setFamilia(m.getFamilia());
        }
        if (!esVacio(m.getGenero())) {
            vista.setGenero(m.getGenero());
        }
        if (!esVacio(m.getEspecie())) {
            vista.setEspecie(m.getEspecie());
        }
    }

    /**
     * Determina qué campos de una mascota se encuentran vacíos o incompletos.
     *
     * <p>
     * Devuelve una cadena que lista los nombres de los campos faltantes. Se
     * utiliza para mostrar al usuario qué información debe completar antes de
     * registrar o persistir los datos.</p>
     *
     * @param m Objeto {@link MascotaVO} a evaluar.
     * @return Cadena con los nombres de los campos faltantes, separados por
     * comas.
     */
    private String camposFaltantes(MascotaVO m) {
        List<String> faltan = new ArrayList<>();
        if (esVacio(m.getApodo())) {
            faltan.add("Apodo");
        }
        if (esVacio(m.getNombreComun())) {
            faltan.add("Nombre");
        }
        if (esVacio(m.getClasificacion())) {
            faltan.add("Clasificación");
        }
        if (esVacio(m.getFamilia())) {
            faltan.add("Familia");
        }
        if (esVacio(m.getGenero())) {
            faltan.add("Género");
        }
        if (esVacio(m.getEspecie())) {
            faltan.add("Especie");
        }
        if (esVacio(m.getAlimentoPrincipal())) {
            faltan.add("Alimento");
        }
        return String.join(", ", faltan);
    }

    /* =============================== flujo INCOMPLETAS =============================== */
    /**
     * Muestra y gestiona el flujo de mascotas con datos incompletos.
     *
     * <p>
     * Este método cumple dos funciones:
     * <ol>
     * <li>Informa al usuario qué mascotas tienen información faltante,
     * detallando los campos que deben completarse.</li>
     * <li>Solicita los datos faltantes mediante la vista, actualiza los objetos
     * y finalmente los envía al {@link ControlGeneral} para su registro o
     * actualización definitiva.</li>
     * </ol>
     *
     * Si no se detectan registros incompletos, muestra un mensaje
     * informativo.</p>
     *
     * @param incompletas Lista de mascotas con información faltante.
     */
    public void mostrarMascotasIncompletas(List<MascotaVO> incompletas) {
        if (incompletas == null || incompletas.isEmpty()) {
            vista.info("Todas las mascotas están completas.");
            return;
        }
        StringBuilder sb = new StringBuilder("Se encontraron mascotas con datos incompletos:\n");
        for (MascotaVO m : incompletas) {
            sb.append("- ").append(!esVacio(m.getNombreComun()) ? m.getNombreComun() : "(sin nombre)")
                    .append(" | faltantes: ").append(camposFaltantes(m)).append('\n');
        }
        vista.warn(sb.toString());

        // pedir datos faltantes a través de la vista
        for (MascotaVO m : incompletas) {
            vista.info("Completando: " + (!esVacio(m.getNombreComun()) ? m.getNombreComun() : "(sin nombre)"));
            if (esVacio(m.getApodo())) {
                m.setApodo(vista.pedirDato("Ingrese el apodo: "));
            }
            if (esVacio(m.getNombreComun())) {
                m.setNombreComun(vista.pedirDato("Ingrese el nombre común: "));
            }
            if (esVacio(m.getClasificacion())) {
                m.setClasificacion(vista.pedirDato("Ingrese la clasificación: "));
            }
            if (esVacio(m.getFamilia())) {
                m.setFamilia(vista.pedirDato("Ingrese la familia: "));
            }
            if (esVacio(m.getGenero())) {
                m.setGenero(vista.pedirDato("Ingrese el género: "));
            }
            if (esVacio(m.getEspecie())) {
                m.setEspecie(vista.pedirDato("Ingrese la especie: "));
            }
            if (esVacio(m.getAlimentoPrincipal())) {
                m.setAlimentoPrincipal(vista.pedirDato("Ingrese el alimento principal: "));
            }
        }

        try {
            cGeneral.aceptarMascotasCompletadas(incompletas);
            vista.info("Datos completados y cargados correctamente a la BD.");
        } catch (Exception ex) {
            vista.error("Error al cargar mascotas completadas: " + ex.getMessage());
        }
    }

    /* ================================== handlers ================================== */
    /**
     * Acción asociada al botón **Adicionar**.
     *
     * <p>
     * Valida los campos obligatorios y registra una nueva mascota en la base de
     * datos mediante {@link ControlGeneral#registrarMascota}. Luego actualiza
     * la vista mostrando todas las mascotas actuales.</p>
     */
    private void onAdicionar() {
        try {
            if (!validarObligatorios()) {
                return;
            }
            cGeneral.registrarMascota(
                    vista.getApodo(),
                    vista.getNombreComun(),
                    vista.getClasificacion(),
                    vista.getFamilia(),
                    vista.getGenero(),
                    vista.getEspecie(),
                    vista.getAlimento()
            );
            vista.info("Mascota registrada exitosamente.");
            vista.limpiarCampos();
            pintarLista(cGeneral.listarTodasLasMascotas());
        } catch (Exception ex) {
            vista.error("Error al adicionar: " + ex.getMessage());
        }
    }

    /**
     * Acción asociada al botón **Consultar**.
     *
     * <p>
     * Permite buscar mascotas por diferentes criterios (apodo, clasificación,
     * familia o tipo de alimento). Si la búsqueda es individual, los datos se
     * cargan en la vista.</p>
     */
    private void onConsultar() {
        try {
            String criterio = vista.getCriterioConsulta();
            String valor = vista.getValorConsulta();
            if (esVacio(valor)) {
                vista.error("Ingrese un valor para consultar.");
                return;
            }

            List<MascotaVO> resultados;
            switch (criterio) {
                case "Apodo": {
                    MascotaVO m = cGeneral.consultarMascota(valor);
                    resultados = new ArrayList<>();
                    if (m != null) {
                        resultados.add(m);
                        cargarDatosEnVista(m);
                    }
                    break;
                }
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
            pintarLista(resultados);
        } catch (Exception ex) {
            vista.error("Error al consultar: " + ex.getMessage());
        }
    }

    /**
     * Acción asociada al botón **Modificar**.
     *
     * <p>
     * Permite editar una mascota ya registrada. Solo se actualizan algunos
     * campos (nombre, clasificación y alimento), mientras que familia, género y
     * especie permanecen sin cambios.</p>
     */
    private void onModificar() {
        try {
            if (!validarObligatorios()) {
                return;
            }
            String apodo = vista.getApodo();
            MascotaVO existente = cGeneral.consultarMascota(apodo);
            if (existente == null) {
                vista.error("No existe una mascota con ese apodo.");
                return;
            }

            boolean ok = vista.confirmar("¿Modificar " + apodo + "?\nNOTA: No se cambia Familia/Género/Especie.");
            if (!ok) {
                return;
            }

            cGeneral.modificarMascota(
                    apodo,
                    vista.getNombreComun(),
                    vista.getClasificacion(),
                    existente.getFamilia(),
                    existente.getGenero(),
                    existente.getEspecie(),
                    vista.getAlimento()
            );
            vista.info("Mascota modificada.");
            MascotaVO actual = cGeneral.consultarMascota(apodo);
            pintarLista(actual != null ? java.util.Collections.singletonList(actual)
                    : java.util.Collections.emptyList());
        } catch (Exception ex) {
            vista.error("Error al modificar: " + ex.getMessage());
        }
    }

    /**
     * Acción asociada al botón **Eliminar**.
     *
     * <p>
     * Elimina una mascota de la base de datos previa confirmación del usuario.
     * Actualiza la lista visible después de la eliminación.</p>
     */
    private void onEliminar() {
        try {
            String apodo = vista.getApodo();
            if (esVacio(apodo)) {
                vista.error("Ingrese o consulte el apodo a eliminar.");
                return;
            }
            MascotaVO m = cGeneral.consultarMascota(apodo);
            if (m == null) {
                vista.error("No existe una mascota con ese apodo.");
                return;
            }

            boolean ok = vista.confirmar("¿Eliminar " + apodo + "?\nEsta acción no se puede deshacer.");
            if (!ok) {
                return;
            }

            cGeneral.eliminarMascota(apodo);
            vista.info("Mascota eliminada.");
            vista.limpiarCampos();
            pintarLista(cGeneral.listarTodasLasMascotas());
        } catch (Exception ex) {
            vista.error("Error al eliminar: " + ex.getMessage());
        }
    }

    /**
     * Acción asociada al botón **Serializar**.
     *
     * <p>
     * Permite exportar los datos de mascotas a un archivo en la carpeta elegida
     * por el usuario. Cumple el requisito de excluir el campo “alimento
     * principal” del archivo serializado.</p>
     */
    private void onSerializar() {
        try {
            String carpeta = vista.elegirCarpetaSerializacion();
            if (carpeta == null) {
                return; // cancelado
            }
            cGeneral.serializarMascotas(carpeta);
            vista.info("Serializado en:\n" + carpeta
                    + "\n\nNota: El archivo NO incluye 'Alimento Principal' (requisito).");
        } catch (Exception ex) {
            vista.error("Error al serializar: " + ex.getMessage());
        }
    }

    /**
     * Limpia los campos de la vista y borra los resultados mostrados.
     */
    private void onLimpiar() {
        vista.limpiarCampos();
        vista.mostrarResultado("");
        vista.info("Campos limpiados.");
    }

    /**
     * Cierra la aplicación guardando el estado actual en el archivo RAF.
     *
     * <p>
     * Antes de salir, confirma la acción con el usuario y, en caso de error,
     * permite forzar el cierre sin guardar.</p>
     */
    private void onSalir() {
        try {
            boolean ok = vista.confirmar("¿Salir? Se guardará el estado actual en RAF.");
            if (!ok) {
                return;
            }
            cGeneral.guardarEstadoEnRAF();
            vista.info("Estado guardado. ¡Hasta pronto!");
            System.exit(0);
        } catch (Exception ex) {
            vista.error("Error al guardar estado: " + ex.getMessage());
            boolean force = vista.confirmar("¿Salir sin guardar?");
            if (force) {
                System.exit(0);
            }
        }
    }
}

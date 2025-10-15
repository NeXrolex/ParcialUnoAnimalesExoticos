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
public final class ControlVista implements ActionListener {

    private final VistaPrincipal vista;
    private final ControlGeneral cGeneral;

    private static final String CMD_ADICIONAR  = "ADICIONAR";
    private static final String CMD_CONSULTAR  = "CONSULTAR";
    private static final String CMD_MODIFICAR  = "MODIFICAR";
    private static final String CMD_ELIMINAR   = "ELIMINAR";
    private static final String CMD_SERIALIZAR = "SERIALIZAR";
    private static final String CMD_LIMPIAR    = "LIMPIAR";
    private static final String CMD_SALIR      = "SALIR";

    /** El ControlGeneral crea este controlador; aquí se crea y muestra la vista. */
    public ControlVista(ControlGeneral cGeneral) {
        this.cGeneral = cGeneral;
        this.vista = new VistaPrincipal(); // no se cambia el layout existente
        registrarListenersYComandos();
        this.vista.setVisible(true);
    }

    /* ================================== wiring ================================== */

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand() == null ? "" : e.getActionCommand();
        switch (cmd) {
            case CMD_ADICIONAR:  onAdicionar();  break;
            case CMD_CONSULTAR:  onConsultar();  break;
            case CMD_MODIFICAR:  onModificar();  break;
            case CMD_ELIMINAR:   onEliminar();   break;
            case CMD_SERIALIZAR: onSerializar(); break;
            case CMD_LIMPIAR:    onLimpiar();    break;
            case CMD_SALIR:      onSalir();      break;
            default: /* no-op */ break;
        }
    }

    /* =============================== helpers =============================== */

    private boolean esVacio(String s) { return s == null || s.trim().isEmpty(); }

    private boolean validarObligatorios() {
        if (esVacio(vista.getApodo()))       { vista.error("El apodo es obligatorio."); return false; }
        if (esVacio(vista.getNombreComun())) { vista.error("El nombre común es obligatorio."); return false; }
        if (esVacio(vista.getFamilia()))     { vista.error("La familia es obligatoria."); return false; }
        if (esVacio(vista.getGenero()))      { vista.error("El género es obligatorio."); return false; }
        if (esVacio(vista.getEspecie()))     { vista.error("La especie es obligatoria."); return false; }
        return true;
    }

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

    private void cargarDatosEnVista(MascotaVO m) {
        if (m == null) return;
        if (!esVacio(m.getApodo()))        vista.setApodo(m.getApodo());
        if (!esVacio(m.getNombreComun()))  vista.setNombreComun(m.getNombreComun());
        if (!esVacio(m.getFamilia()))      vista.setFamilia(m.getFamilia());
        if (!esVacio(m.getGenero()))       vista.setGenero(m.getGenero());
        if (!esVacio(m.getEspecie()))      vista.setEspecie(m.getEspecie());
    }

    private String camposFaltantes(MascotaVO m) {
        List<String> faltan = new ArrayList<>();
        if (esVacio(m.getApodo()))             faltan.add("Apodo");
        if (esVacio(m.getNombreComun()))       faltan.add("Nombre");
        if (esVacio(m.getClasificacion()))     faltan.add("Clasificación");
        if (esVacio(m.getFamilia()))           faltan.add("Familia");
        if (esVacio(m.getGenero()))            faltan.add("Género");
        if (esVacio(m.getEspecie()))           faltan.add("Especie");
        if (esVacio(m.getAlimentoPrincipal())) faltan.add("Alimento");
        return String.join(", ", faltan);
    }

    /* =============================== flujo INCOMPLETAS =============================== */

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
            if (esVacio(m.getApodo()))             m.setApodo(vista.pedirDato("Ingrese el apodo: "));
            if (esVacio(m.getNombreComun()))       m.setNombreComun(vista.pedirDato("Ingrese el nombre común: "));
            if (esVacio(m.getClasificacion()))     m.setClasificacion(vista.pedirDato("Ingrese la clasificación: "));
            if (esVacio(m.getFamilia()))           m.setFamilia(vista.pedirDato("Ingrese la familia: "));
            if (esVacio(m.getGenero()))            m.setGenero(vista.pedirDato("Ingrese el género: "));
            if (esVacio(m.getEspecie()))           m.setEspecie(vista.pedirDato("Ingrese la especie: "));
            if (esVacio(m.getAlimentoPrincipal())) m.setAlimentoPrincipal(vista.pedirDato("Ingrese el alimento principal: "));
        }

        try {
            cGeneral.aceptarMascotasCompletadas(incompletas);
            vista.info("Datos completados y cargados correctamente a la BD.");
        } catch (Exception ex) {
            vista.error("Error al cargar mascotas completadas: " + ex.getMessage());
        }
    }

    /* ================================== handlers ================================== */

    private void onAdicionar() {
        try {
            if (!validarObligatorios()) return;
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

    private void onConsultar() {
        try {
            String criterio = vista.getCriterioConsulta();
            String valor = vista.getValorConsulta();
            if (esVacio(valor)) { vista.error("Ingrese un valor para consultar."); return; }

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

    private void onModificar() {
        try {
            if (!validarObligatorios()) return;
            String apodo = vista.getApodo();
            MascotaVO existente = cGeneral.consultarMascota(apodo);
            if (existente == null) { vista.error("No existe una mascota con ese apodo."); return; }

            boolean ok = vista.confirmar("¿Modificar " + apodo + "?\nNOTA: No se cambia Familia/Género/Especie.");
            if (!ok) return;

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

    private void onEliminar() {
        try {
            String apodo = vista.getApodo();
            if (esVacio(apodo)) { vista.error("Ingrese o consulte el apodo a eliminar."); return; }
            MascotaVO m = cGeneral.consultarMascota(apodo);
            if (m == null) { vista.error("No existe una mascota con ese apodo."); return; }

            boolean ok = vista.confirmar("¿Eliminar " + apodo + "?\nEsta acción no se puede deshacer.");
            if (!ok) return;

            cGeneral.eliminarMascota(apodo);
            vista.info("Mascota eliminada.");
            vista.limpiarCampos();
            pintarLista(cGeneral.listarTodasLasMascotas());
        } catch (Exception ex) {
            vista.error("Error al eliminar: " + ex.getMessage());
        }
    }

    private void onSerializar() {
        try {
            String carpeta = vista.elegirCarpetaSerializacion();
            if (carpeta == null) return; // cancelado
            cGeneral.serializarMascotas(carpeta);
            vista.info("Serializado en:\n" + carpeta +
                       "\n\nNota: El archivo NO incluye 'Alimento Principal' (requisito).");
        } catch (Exception ex) {
            vista.error("Error al serializar: " + ex.getMessage());
        }
    }

    private void onLimpiar() {
        vista.limpiarCampos();
        vista.mostrarResultado("");
        vista.info("Campos limpiados.");
    }

    private void onSalir() {
        try {
            boolean ok = vista.confirmar("¿Salir? Se guardará el estado actual en RAF.");
            if (!ok) return;
            cGeneral.guardarEstadoEnRAF();
            vista.info("Estado guardado. ¡Hasta pronto!");
            System.exit(0);
        } catch (Exception ex) {
            vista.error("Error al guardar estado: " + ex.getMessage());
            boolean force = vista.confirmar("¿Salir sin guardar?");
            if (force) System.exit(0);
        }
    }
}

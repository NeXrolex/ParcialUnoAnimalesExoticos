/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uDistrital.avanzada.parcialUno.vista;

import java.util.Scanner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Clase encargada de la vista del programa
 *
 * @author jeiso
 */
public class VistaPrincipal {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerDato(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
public final class VistaPrincipal extends JFrame {

    //paneles usados 
    private JPanel panelSuperior;
    private JPanel panelCentral;
    private JPanel panelFinal;
    //entradas
    private JTextField textoApodo;
    private JTextField textoNombreComun;
    private JComboBox<String> opcionClasificacion;
    private JTextField textoFamilia;
    private JTextField textoGenero;
    private JTextField textoEspecie;
    private JComboBox<String> opcionAlimento;
    // muestra de resultados 
    private JTextArea resultados;
    private JScrollPane resultadosScroll;
    // botones interacciones
    private JButton btnAdicionar;
    private JButton btnConsultar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnSerializar;
    private JButton btnLimpiar;
    private JButton btnSalir;
    //opciones para consultas
    private JComboBox<String> opcionConsulta;
    private JTextField textoConsulta;

    public VistaPrincipal() {
        iniciarVentana();
        iniciarComponentes();
        configurarLayout();
    }

    public void iniciarVentana() {
        setTitle("Gestion de mascotas");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void iniciarComponentes() {
        //Panel superior Titulo
        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(70, 130, 180));
        JLabel lblTitulo = new JLabel("GESTIÓN DE MASCOTAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperior.add(lblTitulo);
        //panel centra para consultas 
        panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelDatos = crearPanelDatos();

        resultados = new JTextArea(10, 50);
        resultados.setEditable(false);
        resultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultadosScroll = new JScrollPane(resultados);
        resultados.setBorder(BorderFactory.createTitledBorder("Resultados"));

        panelCentral.add(panelDatos, BorderLayout.NORTH);
        panelCentral.add(resultadosScroll, BorderLayout.CENTER);

        // Panel inferior - Botones
        panelFinal = crearPanelBotones();

    }

    private JPanel crearPanelDatos() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Datos de la mascotas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Apodo
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Apodo:"), gbc);
        gbc.gridx = 1;
        textoApodo = new JTextField(20);
        panel.add(textoApodo, gbc);

        // Nombre común
        gbc.gridx = 2;
        panel.add(new JLabel("Nombre Común:"), gbc);
        gbc.gridx = 3;
        textoNombreComun = new JTextField(20);
        panel.add(textoNombreComun, gbc);

        // Clasificación
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Clasificación:"), gbc);
        gbc.gridx = 1;
        String[] clasificaciones = {"Mamífero", "Reptil", "Ave", "Anfibio", "Pez", "Invertebrado"};
        opcionClasificacion = new JComboBox<>(clasificaciones);
        panel.add(opcionClasificacion, gbc);

        // Familia
        gbc.gridx = 2;
        panel.add(new JLabel("Familia:"), gbc);
        gbc.gridx = 3;
        textoFamilia = new JTextField(20);
        panel.add(textoFamilia, gbc);

        // Género
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        textoGenero = new JTextField(20);
        panel.add(textoGenero, gbc);

        // Especie
        gbc.gridx = 2;
        panel.add(new JLabel("Especie:"), gbc);
        gbc.gridx = 3;
        textoEspecie = new JTextField(20);
        panel.add(textoEspecie, gbc);

        // Alimento
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Alimento Principal:"), gbc);
        gbc.gridx = 1;
        String[] alimentos = {"Lácteos", "Carnes", "Verduras", "Frutas",
            "Forrajes", "Cereales", "Leguminosas", "Omnívoros"};
        opcionAlimento = new JComboBox<>(alimentos);
        panel.add(opcionAlimento, gbc);

        // Panel de consulta
        gbc.gridx = 2;
        panel.add(new JLabel("Consultar por:"), gbc);
        gbc.gridx = 3;
        String[] criterios = {"Apodo", "Clasificación", "Familia", "Alimento"};
        opcionConsulta = new JComboBox<>(criterios);
        panel.add(opcionConsulta, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(new JLabel("Valor:"), gbc);
        gbc.gridx = 3;
        textoConsulta = new JTextField(20);
        panel.add(textoConsulta, gbc);

        return panel;
    }

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        btnAdicionar = crearBoton("Adicionar", new Color(34, 139, 34));
        btnConsultar = crearBoton("Consultar", new Color(30, 144, 255));
        btnModificar = crearBoton("Modificar", new Color(255, 140, 0));
        btnEliminar = crearBoton("Eliminar", new Color(220, 20, 60));
        btnSerializar = crearBoton("Serializar", new Color(138, 43, 226));
        btnLimpiar = crearBoton("Limpiar", new Color(128, 128, 128));
        btnSalir = crearBoton("Salir", new Color(0, 0, 0));

        panel.add(btnAdicionar);
        panel.add(btnConsultar);
        panel.add(btnModificar);
        panel.add(btnEliminar);
        panel.add(btnSerializar);
        panel.add(btnLimpiar);
        panel.add(btnSalir);

        return panel;
    }

    /**
     * Crea un botón con estilo personalizado
     *
     * @param texto Texto del botón
     * @param color Color de fondo del botón
     * @return Botón configurado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setPreferredSize(new Dimension(110, 35));
        return boton;
    }

    private void configurarLayout() {
        add(panelSuperior, BorderLayout.NORTH);
        add(panelCentral, BorderLayout.CENTER);
        add(panelFinal, BorderLayout.SOUTH);
    }

    public void asignarListenerAdicionar(ActionListener listener) {
        btnAdicionar.addActionListener(listener);
    }

    public void asignarListenerConsultar(ActionListener listener) {
        btnConsultar.addActionListener(listener);
    }

    public void asignarListenerModificar(ActionListener listener) {
        btnModificar.addActionListener(listener);
    }

    public void asignarListenerEliminar(ActionListener listener) {
        btnEliminar.addActionListener(listener);
    }

    public void asignarListenerSerializar(ActionListener listener) {
        btnSerializar.addActionListener(listener);
    }

    public void asignarListenerLimpiar(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }

    public void asignarListenerSalir(ActionListener listener) {
        btnSalir.addActionListener(listener);
    }

    /**
     * Limpia todos los campos de entrada
     */
    public void limpiarCampos() {
        textoApodo.setText("");
        textoNombreComun.setText("");
        textoFamilia.setText("");
        textoGenero.setText("");
        textoEspecie.setText("");
        textoConsulta.setText("");
        opcionClasificacion.setSelectedIndex(0);
        opcionAlimento.setSelectedIndex(0);
    }

    /**
     * Muestra un mensaje en el área de resultados
     *
     * @param mensaje Mensaje a mostrar
     */
    public void mostrarResultado(String mensaje) {
        resultados.setText(mensaje);
    }

    /**
     * Agrega texto al área de resultados sin limpiar el contenido previo
     *
     * @param mensaje Mensaje a agregar
     */
    public void agregarResultado(String mensaje) {
        resultados.append(mensaje + "\n");
    }

    /**
     * Muestra un cuadro de diálogo con un mensaje
     *
     * @param mensaje Mensaje a mostrar
     * @param titulo Título del diálogo
     * @param tipo Tipo de mensaje (información, error, etc)
     */
    public void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    // ========== GETTERS PARA LOS DATOS ==========
    public String getApodo() {
        return textoApodo.getText().trim();
    }

    public String getNombreComun() {
        return textoNombreComun.getText().trim();
    }

    public String getClasificacion() {
        return (String) opcionClasificacion.getSelectedItem();
    }

    public String getFamilia() {
        return textoFamilia.getText().trim();
    }

    public String getGenero() {
        return textoGenero.getText().trim();
    }

    public String getEspecie() {
        return textoEspecie.getText().trim();
    }

    public String getAlimento() {
        return (String) opcionAlimento.getSelectedItem();
    }

    public String getCriterioConsulta() {
        return (String) opcionConsulta.getSelectedItem();
    }

    public String getValorConsulta() {
        return textoConsulta.getText().trim();
    }

    // ========== SETTERS PARA CARGAR DATOS ==========
    public void setApodo(String apodo) {
        textoApodo.setText(apodo);
    }

    public void setNombreComun(String nombre) {
        textoNombreComun.setText(nombre);
    }

    public void setFamilia(String familia) {
        textoFamilia.setText(familia);
    }

    public void setGenero(String genero) {
        textoGenero.setText(genero);
    }

    public void setEspecie(String especie) {
        textoEspecie.setText(especie);
    }

    // ========== GETTERS PARA LOS BOTONES ==========
    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnSerializar() {
        return btnSerializar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

}

package co.uptc.edu.Vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import co.uptc.edu.Controlador.Controlador;
import co.uptc.edu.modelo.Libro;

public class Ventana_Libro extends JFrame {


	    private static final long serialVersionUID = 1L;

	    private JTextField txtISBN, txtTitulo, txtAutor, txtAnio, txtCategoria, txtEditorial, txtPaginas, txtPrecio, txtStock, txtRutaImagen;
	    private JComboBox<String> cmbFormato, cmbIva;
	    private JLabel lblVistaPrevia;

	    private JButton btnRegistrar, btnBuscar, btnActualizar, btnEliminar, btnVerDetalle, btnSeleccionarImagen, btnLimpiar;

	    private JTable tablaLibros;
	    private DefaultTableModel modeloTabla;

	    private Controlador controlador;

	    public Ventana_Libro() {
	        controlador = new Controlador();
	        configurarVentana();
	        inicializarComponentes();
	        configurarEventos();
	        cargarTabla();
	    }

	    private void configurarVentana() {
	        setTitle("Tienda Virtual de Libros - CRUD de Gestión");
	        setSize(1180, 750);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout(10, 10));
	    }

	    private void inicializarComponentes() {
	        // Panel Superior
	        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        JLabel titulo = new JLabel("GESTIÓN DE LIBROS (MYSQL / JDBC)");
	        titulo.setFont(new Font("Arial", Font.BOLD, 22));
	        panelSuperior.add(titulo);
	        add(panelSuperior, BorderLayout.NORTH);

	        // Panel Formulario
	        JPanel panelFormulario = new JPanel(new GridBagLayout());
	        panelFormulario.setBorder(BorderFactory.createTitledBorder("Información del Libro"));
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(4, 4, 4, 4);
	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        agregarCampo(panelFormulario, gbc, "ISBN:", txtISBN = new JTextField(), 0);
	        agregarCampo(panelFormulario, gbc, "Título:", txtTitulo = new JTextField(), 1);
	        agregarCampo(panelFormulario, gbc, "Autor:", txtAutor = new JTextField(), 2);
	        agregarCampo(panelFormulario, gbc, "Año publicación:", txtAnio = new JTextField(), 3);
	        agregarCampo(panelFormulario, gbc, "Categoría:", txtCategoria = new JTextField(), 4);
	        agregarCampo(panelFormulario, gbc, "Editorial:", txtEditorial = new JTextField(), 5);
	        agregarCampo(panelFormulario, gbc, "Páginas:", txtPaginas = new JTextField(), 6);
	        agregarCampo(panelFormulario, gbc, "Precio Base ($):", txtPrecio = new JTextField(), 7);

	        // IVA (%)
	        gbc.gridx = 0; gbc.gridy = 8; gbc.weightx = 0;
	        panelFormulario.add(new JLabel("IVA (%):"), gbc);
	        cmbIva = new JComboBox<>(new String[]{"19.0", "5.0", "0.0"});
	        gbc.gridx = 1; gbc.weightx = 1;
	        panelFormulario.add(cmbIva, gbc);

	        agregarCampo(panelFormulario, gbc, "Stock:", txtStock = new JTextField(), 9);

	        // Formato
	        gbc.gridx = 0; gbc.gridy = 10; gbc.weightx = 0;
	        panelFormulario.add(new JLabel("Formato:"), gbc);
	        cmbFormato = new JComboBox<>(new String[]{"FÍSICO", "DIGITAL"});
	        gbc.gridx = 1; gbc.weightx = 1;
	        panelFormulario.add(cmbFormato, gbc);

	        // Imagen Portada
	        gbc.gridx = 0; gbc.gridy = 11; gbc.weightx = 0;
	        panelFormulario.add(new JLabel("Portada:"), gbc);
	        JPanel panelFoto = new JPanel(new BorderLayout(5, 0));
	        txtRutaImagen = new JTextField();
	        btnSeleccionarImagen = new JButton("Buscar...");
	        panelFoto.add(txtRutaImagen, BorderLayout.CENTER);
	        panelFoto.add(btnSeleccionarImagen, BorderLayout.EAST);
	        gbc.gridx = 1; gbc.weightx = 1;
	        panelFormulario.add(panelFoto, gbc);

	        // Panel Vista Previa Imagen
	        JPanel panelImagen = new JPanel(new BorderLayout());
	        panelImagen.setBorder(BorderFactory.createTitledBorder("Vista Previa Portada"));
	        lblVistaPrevia = new JLabel("Sin Portada", SwingConstants.CENTER);
	        lblVistaPrevia.setPreferredSize(new Dimension(150, 180));
	        panelImagen.add(lblVistaPrevia, BorderLayout.CENTER);

	        // Panel Botones laterales
	        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 5, 5));
	        btnRegistrar = new JButton("Registrar (Create)");
	        btnBuscar = new JButton("Buscar por ISBN");
	        btnActualizar = new JButton("Actualizar (Update)");
	        btnEliminar = new JButton("Eliminar (Delete)");
	        btnVerDetalle = new JButton("Ver Ficha Técnica");
	        btnLimpiar = new JButton("Limpiar Campos");

	        panelBotones.add(btnRegistrar);
	        panelBotones.add(btnBuscar);
	        panelBotones.add(btnActualizar);
	        panelBotones.add(btnEliminar);
	        panelBotones.add(btnVerDetalle);
	        panelBotones.add(btnLimpiar);

	        JPanel panelFormConFoto = new JPanel(new BorderLayout(10, 10));
	        panelFormConFoto.add(panelFormulario, BorderLayout.CENTER);
	        panelFormConFoto.add(panelImagen, BorderLayout.EAST);

	        JPanel panelIzquierdo = new JPanel(new BorderLayout(10, 10));
	        panelIzquierdo.add(panelFormConFoto, BorderLayout.CENTER);
	        panelIzquierdo.add(panelBotones, BorderLayout.SOUTH);
	        add(panelIzquierdo, BorderLayout.WEST);

	        // Tabla
	        String[] columnas = {"ID", "ISBN", "Título", "Autor", "Año", "Categoría", "Editorial", "Páginas", "Precio", "IVA", "Stock", "Formato", "Imagen"};
	        modeloTabla = new DefaultTableModel(columnas, 0) {
	            @Override
	            public boolean isCellEditable(int row, int column) { return false; }
	        };

	        tablaLibros = new JTable(modeloTabla);
	        tablaLibros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        tablaLibros.setAutoCreateRowSorter(true);

	        JScrollPane scrollTabla = new JScrollPane(tablaLibros);
	        scrollTabla.setBorder(BorderFactory.createTitledBorder("Catálogo de Libros Registrados"));
	        add(scrollTabla, BorderLayout.CENTER);
	    }

	    private void agregarCampo(JPanel panel, GridBagConstraints gbc, String etiqueta, JTextField campo, int fila) {
	        gbc.gridx = 0; gbc.gridy = fila; gbc.weightx = 0;
	        panel.add(new JLabel(etiqueta), gbc);
	        gbc.gridx = 1; gbc.weightx = 1;
	        panel.add(campo, gbc);
	    }

	    private void configurarEventos() {
	        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());
	        btnRegistrar.addActionListener(e -> registrarLibro());
	        btnBuscar.addActionListener(e -> buscarLibro());
	        btnActualizar.addActionListener(e -> actualizarLibro());
	        btnEliminar.addActionListener(e -> eliminarLibro());
	        btnVerDetalle.addActionListener(e -> verDetalleLibro());
	        btnLimpiar.addActionListener(e -> limpiarCampos());

	        tablaLibros.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                cargarLibroSeleccionado();
	            }
	        });
	    }

	    private void cargarTabla() {
	        try {
	            modeloTabla.setRowCount(0);
	            List<Libro> libros = controlador.listarLibros();
	            for (Libro l : libros) {
	                modeloTabla.addRow(new Object[]{
	                        l.getId(), l.getIsbn(), l.getTitulo(), l.getAutor(), l.getAnioPublicacion(),
	                        l.getCategoria(), l.getEditorial(), l.getPaginas(), l.getPrecio(),
	                        l.getPorcentajeIva() + "%", l.getStock(), l.getFormato(), l.getImagen()
	                });
	            }
	        } catch (Exception ex) {
	            mostrarError("Error al cargar la base de datos: " + ex.getMessage());
	        }
	    }

	    private boolean validarCampos() {
	        if (txtISBN.getText().trim().isEmpty() || txtTitulo.getText().trim().isEmpty() ||
	            txtAutor.getText().trim().isEmpty() || txtAnio.getText().trim().isEmpty() ||
	            txtCategoria.getText().trim().isEmpty() || txtEditorial.getText().trim().isEmpty() ||
	            txtPaginas.getText().trim().isEmpty() || txtPrecio.getText().trim().isEmpty() ||
	            txtStock.getText().trim().isEmpty()) {
	            mostrarError("Todos los campos de texto son obligatorios.");
	            return false;
	        }

	        if (!controlador.esNumeroEntero(txtAnio.getText().trim())) {
	            mostrarError("El año debe ser un número entero.");
	            return false;
	        }
	        if (!controlador.esNumeroEntero(txtPaginas.getText().trim())) {
	            mostrarError("Las páginas deben ser un número entero mayor a cero.");
	            return false;
	        }
	        if (!controlador.esNumeroDecimal(txtPrecio.getText().trim())) {
	            mostrarError("El precio debe ser un valor decimal válido.");
	            return false;
	        }
	        if (!controlador.esNumeroEntero(txtStock.getText().trim())) {
	            mostrarError("El stock debe ser un número entero.");
	            return false;
	        }
	        return true;
	    }

	    private Libro obtenerLibroFormulario() {
	        return new Libro(
	                txtISBN.getText().trim(),
	                txtTitulo.getText().trim(),
	                txtAutor.getText().trim(),
	                Integer.parseInt(txtAnio.getText().trim()),
	                txtCategoria.getText().trim(),
	                txtEditorial.getText().trim(),
	                Integer.parseInt(txtPaginas.getText().trim()),
	                Double.parseDouble(txtPrecio.getText().trim()),
	                Double.parseDouble(cmbIva.getSelectedItem().toString()),
	                Integer.parseInt(txtStock.getText().trim()),
	                cmbFormato.getSelectedItem().toString(),
	                txtRutaImagen.getText().trim().isEmpty() ? "default_cover.png" : txtRutaImagen.getText().trim()
	        );
	    }

	    private void registrarLibro() {
	        if (!validarCampos()) return;
	        try {
	            Libro l = obtenerLibroFormulario();
	            if (controlador.registrarLibro(l)) {
	                JOptionPane.showMessageDialog(this, "Libro registrado en MySQL con éxito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
	                cargarTabla();
	                limpiarCampos();
	            }
	        } catch (Exception ex) {
	            mostrarError("Error al registrar: " + ex.getMessage());
	        }
	    }

	    private void buscarLibro() {
	        String isbn = txtISBN.getText().trim();
	        if (isbn.isEmpty()) {
	            isbn = JOptionPane.showInputDialog(this, "Ingrese el ISBN a buscar:");
	        }
	        if (isbn != null && !isbn.trim().isEmpty()) {
	            try {
	                Libro l = controlador.buscarPorISBN(isbn.trim());
	                if (l != null) {
	                    mostrarLibroEnFormulario(l);
	                    seleccionarLibroEnTabla(l.getIsbn());
	                } else {
	                    mostrarError("No se encontró ningún libro con ese ISBN.");
	                }
	            } catch (Exception ex) {
	                mostrarError("Error al buscar: " + ex.getMessage());
	            }
	        }
	    }

	    private void actualizarLibro() {
	        if (!validarCampos()) return;
	        try {
	            Libro l = obtenerLibroFormulario();
	            if (controlador.actualizarLibro(l)) {
	                JOptionPane.showMessageDialog(this, "Libro actualizado en MySQL.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                cargarTabla();
	                limpiarCampos();
	            } else {
	                mostrarError("No se pudo encontrar el libro para actualizar.");
	            }
	        } catch (Exception ex) {
	            mostrarError("Error al actualizar: " + ex.getMessage());
	        }
	    }

	    private void eliminarLibro() {
	        String isbn = txtISBN.getText().trim();
	        if (isbn.isEmpty()) {
	            mostrarError("Ingrese o seleccione un libro para eliminar.");
	            return;
	        }
	        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el libro " + isbn + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
	        if (confirm == JOptionPane.YES_OPTION) {
	            try {
	                if (controlador.eliminarLibro(isbn)) {
	                    JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                    cargarTabla();
	                    limpiarCampos();
	                }
	            } catch (Exception ex) {
	                mostrarError(ex.getMessage());
	            }
	        }
	    }

	    private void verDetalleLibro() {
	        int fila = tablaLibros.getSelectedRow();
	        if (fila >= 0) {
	            try {
	                int filaModelo = tablaLibros.convertRowIndexToModel(fila);
	                String isbn = modeloTabla.getValueAt(filaModelo, 1).toString();
	                Libro l = controlador.buscarPorISBN(isbn);
	                Ventana_Detalle_Libro vDetalle = new Ventana_Detalle_Libro(l);
	                vDetalle.setVisible(true);
	            } catch (Exception ex) {
	                mostrarError("Error al abrir ficha técnica: " + ex.getMessage());
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Seleccione un libro de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    private void seleccionarImagen() {
	        JFileChooser fileChooser = new JFileChooser();
	        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            txtRutaImagen.setText(file.getAbsolutePath());
	            mostrarImagenEnLabel(file.getAbsolutePath());
	        }
	    }

	    private void mostrarImagenEnLabel(String ruta) {
	        if (ruta != null && !ruta.isEmpty() && new File(ruta).exists()) {
	            ImageIcon icon = new ImageIcon(ruta);
	            Image img = icon.getImage().getScaledInstance(140, 170, Image.SCALE_SMOOTH);
	            lblVistaPrevia.setIcon(new ImageIcon(img));
	            lblVistaPrevia.setText("");
	        } else {
	            lblVistaPrevia.setIcon(null);
	            lblVistaPrevia.setText("Sin Portada");
	        }
	    }

	    private void mostrarLibroEnFormulario(Libro libro) {
	        txtISBN.setText(libro.getIsbn());
	        txtTitulo.setText(libro.getTitulo());
	        txtAutor.setText(libro.getAutor());
	        txtAnio.setText(String.valueOf(libro.getAnioPublicacion()));
	        txtCategoria.setText(libro.getCategoria());
	        txtEditorial.setText(libro.getEditorial());
	        txtPaginas.setText(String.valueOf(libro.getPaginas()));
	        txtPrecio.setText(String.valueOf(libro.getPrecio()));
	        cmbIva.setSelectedItem(String.valueOf(libro.getPorcentajeIva()));
	        txtStock.setText(String.valueOf(libro.getStock()));
	        cmbFormato.setSelectedItem(libro.getFormato());
	        txtRutaImagen.setText(libro.getImagen());
	        mostrarImagenEnLabel(libro.getImagen());
	    }

	    private void cargarLibroSeleccionado() {
	        int fila = tablaLibros.getSelectedRow();
	        if (fila != -1) {
	            int filaModelo = tablaLibros.convertRowIndexToModel(fila);
	            String isbn = modeloTabla.getValueAt(filaModelo, 1).toString();
	            try {
	                Libro libro = controlador.buscarPorISBN(isbn);
	                if (libro != null) mostrarLibroEnFormulario(libro);
	            } catch (Exception ex) {
	                mostrarError(ex.getMessage());
	            }
	        }
	    }

	    private void seleccionarLibroEnTabla(String isbn) {
	        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	            if (modeloTabla.getValueAt(i, 1).toString().equalsIgnoreCase(isbn)) {
	                int filaVista = tablaLibros.convertRowIndexToView(i);
	                tablaLibros.setRowSelectionInterval(filaVista, filaVista);
	                tablaLibros.scrollRectToVisible(tablaLibros.getCellRect(filaVista, 0, true));
	                break;
	            }
	        }
	    }

	    private void limpiarCampos() {
	        txtISBN.setText(""); txtTitulo.setText(""); txtAutor.setText("");
	        txtAnio.setText(""); txtCategoria.setText(""); txtEditorial.setText("");
	        txtPaginas.setText(""); txtPrecio.setText(""); txtStock.setText("");
	        txtRutaImagen.setText("");
	        cmbFormato.setSelectedIndex(0); cmbIva.setSelectedIndex(0);
	        lblVistaPrevia.setIcon(null); lblVistaPrevia.setText("Sin Portada");
	        tablaLibros.clearSelection();
	        txtISBN.requestFocus();
	    }

	    private void mostrarError(String mensaje) {
	        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	    }
	
}

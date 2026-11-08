package co.uptc.edu.vista;

	import java.awt.BorderLayout;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import java.awt.Insets;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.GridLayout;

	import java.util.List;

	import javax.swing.BorderFactory;
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.ListSelectionModel;
	import javax.swing.table.DefaultTableModel;

	import co.uptc.edu.controlador.*;
	import co.uptc.edu.modelo.*;

	public class VentanaLibro extends JFrame { 

	    // ==========================================
	    // CAMPOS DEL FORMULARIO
	    // ==========================================

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JTextField txtISBN;
	    private JTextField txtTitulo;
	    private JTextField txtAutor;
	    private JTextField txtAnio;
	    private JTextField txtCategoria;
	    private JTextField txtEditorial;
	    private JTextField txtPaginas;
	    private JTextField txtPrecio;
	    private JTextField txtStock;

	    private JComboBox<String> cmbFormato;

	    // ==========================================
	    // BOTONES
	    // ==========================================

	    private JButton btnRegistrar;
	    private JButton btnBuscar;
	    private JButton btnActualizar;
	    private JButton btnEliminar;
	    private JButton btnLimpiar;

	    // ==========================================
	    // TABLA
	    // ==========================================

	    private JTable tablaLibros;

	    private DefaultTableModel modeloTabla;

	    // ==========================================
	    // BACKEND
	    // ==========================================

	    private Controlador Controlador;

	    // ==========================================
	    // CONSTRUCTOR
	    // ==========================================

	    public VentanaLibro() {

	        Controlador = new Controlador();

	        configurarVentana();

	        inicializarComponentes();

	        configurarEventos();

	        cargarTabla();
	    }

	    // ==========================================
	    // CONFIGURAR VENTANA
	    // ==========================================

	    private void configurarVentana() {

	        setTitle("Tienda Virtual de Libros - CRUD");

	        setSize(1100, 700);

	        setDefaultCloseOperation(
	                JFrame.EXIT_ON_CLOSE
	        );

	        setLocationRelativeTo(null);

	        setLayout(new BorderLayout(10, 10));
	    }

	    // ==========================================
	    // CREAR COMPONENTES
	    // ==========================================

	    private void inicializarComponentes() {

	        // ======================================
	        // PANEL SUPERIOR
	        // ======================================

	        JPanel panelSuperior = new JPanel(
	                new FlowLayout(FlowLayout.CENTER)
	        );

	        JLabel titulo = new JLabel(
	                "GESTIÓN DE LIBROS"
	        );

	        titulo.setFont(
	                new Font("Arial", Font.BOLD, 26)
	        );

	        panelSuperior.add(titulo);

	        add(panelSuperior, BorderLayout.NORTH);

	        // ======================================
	        // PANEL FORMULARIO
	        // ======================================

	        JPanel panelFormulario = new JPanel(
	                new GridBagLayout()
	        );

	        panelFormulario.setBorder(
	                BorderFactory.createTitledBorder(
	                        "Información del libro"
	                )
	        );

	        GridBagConstraints gbc =
	                new GridBagConstraints();

	        gbc.insets = new Insets(5, 5, 5, 5);

	        gbc.fill = GridBagConstraints.HORIZONTAL;

	        // ISBN
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "ISBN:",
	                txtISBN = new JTextField(),
	                0
	        );

	        // Titulo
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Título:",
	                txtTitulo = new JTextField(),
	                1
	        );

	        // Autor
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Autor:",
	                txtAutor = new JTextField(),
	                2
	        );

	        // Año
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Año publicación:",
	                txtAnio = new JTextField(),
	                3
	        );

	        // Categoría
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Categoría:",
	                txtCategoria = new JTextField(),
	                4
	        );

	        // Editorial
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Editorial:",
	                txtEditorial = new JTextField(),
	                5
	        );

	        // Páginas
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Páginas:",
	                txtPaginas = new JTextField(),
	                6
	        );

	        // Precio
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Precio:",
	                txtPrecio = new JTextField(),
	                7
	        );

	        // Stock
	        agregarCampo(
	                panelFormulario,
	                gbc,
	                "Stock:",
	                txtStock = new JTextField(),
	                8
	        );

	        // Formato
	        gbc.gridx = 0;
	        gbc.gridy = 9;
	        gbc.weightx = 0;

	        panelFormulario.add(
	                new JLabel("Formato:"),
	                gbc
	        );

	        cmbFormato = new JComboBox<>(
	                new String[]{
	                        "FÍSICO",
	                        "DIGITAL"
	                }
	        );

	        gbc.gridx = 1;
	        gbc.weightx = 1;

	        panelFormulario.add(
	                cmbFormato,
	                gbc
	        );

	        // ======================================
	        // PANEL BOTONES
	        // ======================================

	        JPanel panelBotones = new JPanel(
	                new GridLayout(5, 1, 5, 5)
	        );

	        btnRegistrar = new JButton(
	                "Registrar"
	        );

	        btnBuscar = new JButton(
	                "Buscar"
	        );

	        btnActualizar = new JButton(
	                "Actualizar"
	        );

	        btnEliminar = new JButton(
	                "Eliminar"
	        );

	        btnLimpiar = new JButton(
	                "Limpiar"
	        );

	        panelBotones.add(btnRegistrar);
	        panelBotones.add(btnBuscar);
	        panelBotones.add(btnActualizar);
	        panelBotones.add(btnEliminar);
	        panelBotones.add(btnLimpiar);

	        // ======================================
	        // PANEL IZQUIERDO
	        // ======================================

	        JPanel panelIzquierdo = new JPanel(
	                new BorderLayout(10, 10)
	        );

	        panelIzquierdo.add(
	                panelFormulario,
	                BorderLayout.CENTER
	        );

	        panelIzquierdo.add(
	                panelBotones,
	                BorderLayout.SOUTH
	        );

	        add(
	                panelIzquierdo,
	                BorderLayout.WEST
	        );

	        // ======================================
	        // TABLA
	        // ======================================

	        String[] columnas = {
	                "ISBN",
	                "Título",
	                "Autor",
	                "Año",
	                "Categoría",
	                "Editorial",
	                "Páginas",
	                "Precio",
	                "Stock",
	                "Formato"
	        };

	        modeloTabla = new DefaultTableModel(
	                columnas,
	                0
	        ) {

	            @Override
	            public boolean isCellEditable(
	                    int row,
	                    int column) {

	                return false;
	            }
	        };

	        tablaLibros = new JTable(
	                modeloTabla
	        );

	        tablaLibros.setSelectionMode(
	                ListSelectionModel.SINGLE_SELECTION
	        );

	        tablaLibros.setAutoCreateRowSorter(true);

	        JScrollPane scrollTabla =
	                new JScrollPane(tablaLibros);

	        scrollTabla.setBorder(
	                BorderFactory.createTitledBorder(
	                        "Catálogo de libros"
	                )
	        );

	        add(
	                scrollTabla,
	                BorderLayout.CENTER
	        );
	    }

	    // ==========================================
	    // AGREGAR CAMPO AL FORMULARIO
	    // ==========================================

	    private void agregarCampo(
	            JPanel panel,
	            GridBagConstraints gbc,
	            String etiqueta,
	            JTextField campo,
	            int fila) {

	        gbc.gridx = 0;
	        gbc.gridy = fila;
	        gbc.weightx = 0;

	        panel.add(
	                new JLabel(etiqueta),
	                gbc
	        );

	        gbc.gridx = 1;
	        gbc.weightx = 1;

	        panel.add(
	                campo,
	                gbc
	        );
	    }

	    // ==========================================
	    // EVENTOS
	    // ==========================================

	    private void configurarEventos() {

	        btnRegistrar.addActionListener(
	                e -> registrarLibro()
	        );

	        btnBuscar.addActionListener(
	                e -> buscarLibro()
	        );

	        btnActualizar.addActionListener(
	                e -> actualizarLibro()
	        );

	        btnEliminar.addActionListener(
	                e -> eliminarLibro()
	        );

	        btnLimpiar.addActionListener(
	                e -> limpiarCampos()
	        );

	        // Al seleccionar una fila
	        tablaLibros.getSelectionModel()
	                .addListSelectionListener(e -> {

	                    if (!e.getValueIsAdjusting()) {

	                        cargarLibroSeleccionado();
	                    }
	                });
	    }

	    // ==========================================
	    // VALIDAR CAMPOS
	    // ==========================================

	    private boolean validarCampos() {

	        if (txtISBN.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El ISBN es obligatorio."
	            );

	            txtISBN.requestFocus();

	            return false;
	        }

	        if (txtTitulo.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El título es obligatorio."
	            );

	            txtTitulo.requestFocus();

	            return false;
	        }

	        if (txtAutor.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El autor es obligatorio."
	            );

	            txtAutor.requestFocus();

	            return false;
	        }

	        if (txtAnio.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El año de publicación es obligatorio."
	            );

	            txtAnio.requestFocus();

	            return false;
	        }

	        if (txtCategoria.getText().trim().isEmpty()) {

	            mostrarError(
	                    "La categoría es obligatoria."
	            );

	            txtCategoria.requestFocus();

	            return false;
	        }

	        if (txtEditorial.getText().trim().isEmpty()) {

	            mostrarError(
	                    "La editorial es obligatoria."
	            );

	            txtEditorial.requestFocus();

	            return false;
	        }

	        if (txtPaginas.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El número de páginas es obligatorio."
	            );

	            txtPaginas.requestFocus();

	            return false;
	        }

	        if (txtPrecio.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El precio es obligatorio."
	            );

	            txtPrecio.requestFocus();

	            return false;
	        }

	        if (txtStock.getText().trim().isEmpty()) {

	            mostrarError(
	                    "El stock es obligatorio."
	            );

	            txtStock.requestFocus();

	            return false;
	        }

	        // Validar ISBN
	        if (!txtISBN.getText()
	                .trim()
	                .matches("[A-Za-z0-9]{10}")) {

	            mostrarError(
	                    "El ISBN debe tener exactamente 10 caracteres alfanuméricos."
	            );

	            txtISBN.requestFocus();

	            return false;
	        }

	        // Validar autor
	        if (!txtAutor.getText()
	                .trim()
	                .matches("[A-Za-zÁÉÍÓÚáéíóúÑñ ]+")) {

	            mostrarError(
	                    "El autor solo puede contener letras y espacios."
	            );

	            txtAutor.requestFocus();

	            return false;
	        }

	        int anio;

	        try {

	            anio = Integer.parseInt(
	                    txtAnio.getText().trim()
	            );

	        } catch (NumberFormatException e) {

	            mostrarError(
	                    "El año debe ser un número entero."
	            );

	            txtAnio.requestFocus();

	            return false;
	        }

	        int anioActual =
	                java.time.Year.now().getValue();

	        if (anio > anioActual) {

	            mostrarError(
	                    "El año no puede ser mayor al año actual."
	            );

	            txtAnio.requestFocus();

	            return false;
	        }

	        int paginas;

	        try {

	            paginas = Integer.parseInt(
	                    txtPaginas.getText().trim()
	            );

	        } catch (NumberFormatException e) {

	            mostrarError(
	                    "Las páginas deben ser un número entero."
	            );

	            txtPaginas.requestFocus();

	            return false;
	        }

	        if (paginas <= 0) {

	            mostrarError(
	                    "El número de páginas debe ser mayor que cero."
	            );

	            txtPaginas.requestFocus();

	            return false;
	        }

	        double precio;

	        try {

	            precio = Double.parseDouble(
	                    txtPrecio.getText().trim()
	            );

	        } catch (NumberFormatException e) {

	            mostrarError(
	                    "El precio debe ser un número decimal."
	            );

	            txtPrecio.requestFocus();

	            return false;
	        }

	        if (precio < 0) {

	            mostrarError(
	                    "El precio no puede ser negativo."
	            );

	            txtPrecio.requestFocus();

	            return false;
	        }

	        int stock;

	        try {

	            stock = Integer.parseInt(
	                    txtStock.getText().trim()
	            );

	        } catch (NumberFormatException e) {

	            mostrarError(
	                    "El stock debe ser un número entero."
	            );

	            txtStock.requestFocus();

	            return false;
	        }

	        if (stock < 0) {

	            mostrarError(
	                    "El stock no puede ser negativo."
	            );

	            txtStock.requestFocus();

	            return false;
	        }

	        return true;
	    }

	    // ==========================================
	    // CREAR OBJETO LIBRO
	    // ==========================================

	    private Libro obtenerLibroFormulario() {

	        return new Libro(
	                txtISBN.getText().trim(),
	                txtTitulo.getText().trim(),
	                txtAutor.getText().trim(),
	                Integer.parseInt(
	                        txtAnio.getText().trim()
	                ),
	                txtCategoria.getText().trim(),
	                txtEditorial.getText().trim(),
	                Integer.parseInt(
	                        txtPaginas.getText().trim()
	                ),
	                Double.parseDouble(
	                        txtPrecio.getText().trim()
	                ),
	                Integer.parseInt(
	                        txtStock.getText().trim()
	                ),
	                cmbFormato.getSelectedItem().toString()
	        );
	    }

	    // ==========================================
	    // CREATE
	    // ==========================================

	    private void registrarLibro() {

	        if (!validarCampos()) {
	            return;
	        }

	        Libro libro =
	                obtenerLibroFormulario();

	        boolean registrado =
	                Controlador.registrarLibro(libro);

	        if (registrado) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Libro registrado correctamente.",
	                    "Registro exitoso",
	                    JOptionPane.INFORMATION_MESSAGE
	            );

	            cargarTabla();

	            limpiarCampos();

	        } else {

	            mostrarError(
	                    "No se pudo registrar el libro.\n"
	                    + "El ISBN ya existe."
	            );
	        }
	    }

	    // ==========================================
	    // READ
	    // ==========================================

	    private void buscarLibro() {

	        String isbn =
	                txtISBN.getText().trim();

	        if (isbn.isEmpty()) {

	            mostrarError(
	                    "Ingrese un ISBN para buscar."
	            );

	            txtISBN.requestFocus();

	            return;
	        }

	        Libro libro =
	                Controlador.buscarPorISBN(isbn);

	        if (libro == null) {

	            mostrarError(
	                    "No se encontró un libro con el ISBN indicado."
	            );

	            return;
	        }

	        mostrarLibroEnFormulario(libro);

	        seleccionarLibroEnTabla(libro.getIsbn());
	    }

	    // ==========================================
	    // UPDATE
	    // ==========================================

	    private void actualizarLibro() {

	        if (!validarCampos()) {
	            return;
	        }

	        Libro libro =
	                obtenerLibroFormulario();

	        boolean actualizado =
	                Controlador.actualizarLibro(libro);

	        if (actualizado) {

	            JOptionPane.showMessageDialog(
	                    this,
	                    "Libro actualizado correctamente.",
	                    "Actualización exitosa",
	                    JOptionPane.INFORMATION_MESSAGE
	            );

	            cargarTabla();

	        } else {

	            mostrarError(
	                    "No se encontró el libro para actualizar."
	            );
	        }
	    }

	    // ==========================================
	    // DELETE
	    // ==========================================

	    private void eliminarLibro() {

	        String isbn =
	                txtISBN.getText().trim();

	        if (isbn.isEmpty()) {

	            mostrarError(
	                    "Ingrese el ISBN del libro que desea eliminar."
	            );

	            txtISBN.requestFocus();

	            return;
	        }

	        Libro libro =
	                Controlador.buscarPorISBN(isbn);

	        if (libro == null) {

	            mostrarError(
	                    "El libro no existe."
	            );

	            return;
	        }

	        int respuesta =
	                JOptionPane.showConfirmDialog(
	                        this,
	                        "¿Está seguro de eliminar el libro?\n\n"
	                        + libro.getTitulo(),
	                        "Confirmar eliminación",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.WARNING_MESSAGE
	                );

	        if (respuesta ==
	                JOptionPane.YES_OPTION) {

	            boolean eliminado =
	                    Controlador.eliminarLibro(isbn);

	            if (eliminado) {

	                JOptionPane.showMessageDialog(
	                        this,
	                        "Libro eliminado correctamente.",
	                        "Eliminación exitosa",
	                        JOptionPane.INFORMATION_MESSAGE
	                );

	                cargarTabla();

	                limpiarCampos();
	            }
	        }
	    }

	    // ==========================================
	    // CARGAR TABLA
	    // ==========================================

	    private void cargarTabla() {

	        modeloTabla.setRowCount(0);

	        List<Libro> libros =
	                Controlador.listarLibros();

	        for (Libro libro : libros) {

	            Object[] fila = {

	                    libro.getIsbn(),
	                    libro.getTitulo(),
	                    libro.getAutor(),
	                    libro.getAnioPublicacion(),
	                    libro.getCategoria(),
	                    libro.getEditorial(),
	                    libro.getPaginas(),
	                    libro.getPrecio(),
	                    libro.getStock(),
	                    libro.getFormato()
	            };

	            modeloTabla.addRow(fila);
	        }
	    }

	    // ==========================================
	    // MOSTRAR LIBRO EN FORMULARIO
	    // ==========================================

	    private void mostrarLibroEnFormulario(
	            Libro libro) {

	        txtISBN.setText(
	                libro.getIsbn()
	        );

	        txtTitulo.setText(
	                libro.getTitulo()
	        );

	        txtAutor.setText(
	                libro.getAutor()
	        );

	        txtAnio.setText(
	                String.valueOf(
	                        libro.getAnioPublicacion()
	                )
	        );

	        txtCategoria.setText(
	                libro.getCategoria()
	        );

	        txtEditorial.setText(
	                libro.getEditorial()
	        );

	        txtPaginas.setText(
	                String.valueOf(
	                        libro.getPaginas()
	                )
	        );

	        txtPrecio.setText(
	                String.valueOf(
	                        libro.getPrecio()
	                )
	        );

	        txtStock.setText(
	                String.valueOf(
	                        libro.getStock()
	                )
	        );

	        cmbFormato.setSelectedItem(
	                libro.getFormato()
	        );
	    }

	    // ==========================================
	    // CARGAR LIBRO SELECCIONADO
	    // ==========================================

	    private void cargarLibroSeleccionado() {

	        int fila =
	                tablaLibros.getSelectedRow();

	        if (fila == -1) {
	            return;
	        }

	        int filaModelo =
	                tablaLibros.convertRowIndexToModel(
	                        fila
	                );

	        String isbn =
	                modeloTabla.getValueAt(
	                        filaModelo,
	                        0
	                ).toString();

	        Libro libro =
	                Controlador.buscarPorISBN(isbn);

	        if (libro != null) {

	            mostrarLibroEnFormulario(libro);
	        }
	    }

	    // ==========================================
	    // SELECCIONAR LIBRO EN TABLA
	    // ==========================================

	    private void seleccionarLibroEnTabla(
	            String isbn) {

	        for (int i = 0;
	             i < modeloTabla.getRowCount();
	             i++) {

	            String isbnTabla =
	                    modeloTabla.getValueAt(i, 0)
	                            .toString();

	            if (isbnTabla.equalsIgnoreCase(isbn)) {

	                int filaVista =
	                        tablaLibros.convertRowIndexToView(i);

	                tablaLibros.setRowSelectionInterval(
	                        filaVista,
	                        filaVista
	                );

	                tablaLibros.scrollRectToVisible(
	                        tablaLibros.getCellRect(
	                                filaVista,
	                                0,
	                                true
	                        )
	                );

	                break;
	            }
	        }
	    }

	    // ==========================================
	    // LIMPIAR CAMPOS
	    // ==========================================

	    private void limpiarCampos() {

	        txtISBN.setText("");
	        txtTitulo.setText("");
	        txtAutor.setText("");
	        txtAnio.setText("");
	        txtCategoria.setText("");
	        txtEditorial.setText("");
	        txtPaginas.setText("");
	        txtPrecio.setText("");
	        txtStock.setText("");

	        cmbFormato.setSelectedIndex(0);

	        tablaLibros.clearSelection();

	        txtISBN.requestFocus();
	    }

	    // ==========================================
	    // MENSAJE DE ERROR
	    // ==========================================

	    private void mostrarError(String mensaje) {

	        JOptionPane.showMessageDialog(
	                this,
	                mensaje,
	                "Error",
	                JOptionPane.ERROR_MESSAGE
	        );
	    }
	
	
	}



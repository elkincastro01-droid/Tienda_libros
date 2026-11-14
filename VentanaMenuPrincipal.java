package co.uptc.edu.Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaMenuPrincipal extends JFrame {

	    private static final long serialVersionUID = 1L;

	    public VentanaMenuPrincipal() {
	        initUI();
	    }

	    private void initUI() {
	        setTitle("Sistema de Gestión - Tienda Virtual de Libros UPTC");
	        setSize(680, 460);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setResizable(false);

	        // Barra de Menús
	        JMenuBar menuBar = new JMenuBar();
	        JMenu menuInventario = new JMenu("Catálogo e Inventario");
	        JMenuItem itemGestion = new JMenuItem("Gestión de Libros (CRUD)");

	        itemGestion.addActionListener(e -> {
	            Ventana_Libro vLibro = new Ventana_Libro();
	            vLibro.setVisible(true);
	        });

	        menuInventario.add(itemGestion);
	        menuBar.add(menuInventario);
	        setJMenuBar(menuBar);

	        // Panel Principal
	        JPanel panelCentral = new JPanel(new BorderLayout());
	        JLabel lblTitulo = new JLabel("SISTEMA ADMINISTRATIVO DE LA TIENDA VIRTUAL", SwingConstants.CENTER);
	        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
	        panelCentral.add(lblTitulo, BorderLayout.NORTH);

	        JLabel lblLogo = new JLabel();
	        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
	        try {
	            ImageIcon icon = new ImageIcon(getClass().getResource("/co/uptc/edu/recursos/banner_tienda.jpg"));
	            if (icon.getImage() != null) {
	                Image img = icon.getImage().getScaledInstance(650, 360, Image.SCALE_SMOOTH);
	                lblLogo.setIcon(new ImageIcon(img));
	            }
	        } catch (Exception ex) {
	            lblLogo.setText("Bienvenido a la Tienda Virtual de Libros");
	            lblLogo.setFont(new Font("Arial", Font.ITALIC, 20));
	        }

	        panelCentral.add(lblLogo, BorderLayout.CENTER);
	        add(panelCentral);
	    }
	
}

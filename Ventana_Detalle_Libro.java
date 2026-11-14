package co.uptc.edu.Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import co.uptc.edu.modelo.Libro;

public class Ventana_Detalle_Libro extends JFrame {


	    private static final long serialVersionUID = 1L;

	    public Ventana_Detalle_Libro(Libro libro) {
	        setTitle("Ficha Técnica del Libro");
	        setSize(430, 540);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout(10, 10));

	        JLabel lblTituloHeader = new JLabel("FICHA TÉCNICA DEL EJEMPLAR", SwingConstants.CENTER);
	        lblTituloHeader.setFont(new Font("Arial", Font.BOLD, 14));
	        lblTituloHeader.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
	        add(lblTituloHeader, BorderLayout.NORTH);

	        JLabel lblFoto = new JLabel("", SwingConstants.CENTER);
	        if (libro.getImagen() != null && new File(libro.getImagen()).exists()) {
	            ImageIcon icon = new ImageIcon(libro.getImagen());
	            Image img = icon.getImage().getScaledInstance(180, 220, Image.SCALE_SMOOTH);
	            lblFoto.setIcon(new ImageIcon(img));
	        } else {
	            lblFoto.setText("[Portada no disponible]");
	        }
	        add(lblFoto, BorderLayout.CENTER);

	        JPanel panelInfo = new JPanel(new GridLayout(10, 1, 3, 3));
	        panelInfo.setBorder(BorderFactory.createTitledBorder("Detalles del Libro"));
	        panelInfo.add(new JLabel(" ISBN: " + libro.getIsbn()));
	        panelInfo.add(new JLabel(" Título: " + libro.getTitulo()));
	        panelInfo.add(new JLabel(" Autor: " + libro.getAutor()));
	        panelInfo.add(new JLabel(" Categoría: " + libro.getCategoria() + " | Editorial: " + libro.getEditorial()));
	        panelInfo.add(new JLabel(" Año: " + libro.getAnioPublicacion() + " | Páginas: " + libro.getPaginas()));
	        panelInfo.add(new JLabel(" Formato: " + libro.getFormato()));
	        panelInfo.add(new JLabel(" Precio Base: $" + String.format("%.2f", libro.getPrecio())));
	        panelInfo.add(new JLabel(" Impuesto IVA (" + libro.getPorcentajeIva() + "%): $" + String.format("%.2f", libro.calcularImpuesto())));
	        panelInfo.add(new JLabel(" Precio Total Final: $" + String.format("%.2f", libro.calcularPrecioTotal())));
	        panelInfo.add(new JLabel(" Stock Disponible: " + libro.getStock() + " unidades"));

	        add(panelInfo, BorderLayout.SOUTH);
	    }
	
}

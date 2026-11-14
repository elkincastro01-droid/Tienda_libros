package co.uptc.edu.main;

import javax.swing.SwingUtilities;
import co.uptc.edu.Vista.VentanaMenuPrincipal;

public class Main {

	    public Main() {}

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            VentanaMenuPrincipal ventanaPrincipal = new VentanaMenuPrincipal();
	            ventanaPrincipal.setVisible(true);
	        });
	    }
	}


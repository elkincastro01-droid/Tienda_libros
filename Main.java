package co.uptc.edu.main;

import javax.swing.SwingUtilities;

import co.uptc.edu.vista.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	SwingUtilities.invokeLater(() -> {

		            VentanaLibro ventana =
		                    new VentanaLibro();

		            ventana.setVisible(true);
		        });
		    }
		
}

package utils;

import javax.swing.JOptionPane;

public class Salir {
	
	public void salir() {
		 int opcion = JOptionPane.showConfirmDialog(
		            null,
		            "¿Deseas salir del programa?",
		            "Confirmación",
		            JOptionPane.YES_NO_OPTION
		        );
		        
		        if (opcion == JOptionPane.YES_OPTION) {
		            System.exit(0); // Terminar el programa
		        } else {
		        	JOptionPane.showMessageDialog(null, "Continuando en el programa...");
		            
		        }
		
	}

}

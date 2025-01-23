package clases;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class validaciones {
	
	// para nombres y apellidos, con mayúscula en cada espacio y aceptando acentos
	public static void validarNombresyApellidos(KeyEvent e, JTextField textField, int maxLength) {
	    char key = e.getKeyChar();
	    boolean mayusculas = (key >= 65 && key <= 90) || key == 209 || "ÁÉÍÓÚÜ".indexOf(key) != -1; // A-Z, Ñ, o vocales con acento
	    boolean minusculas = (key >= 97 && key <= 122) || key == 241 || "áéíóúü".indexOf(key) != -1; // a-z, ñ, o vocales con acento
	    boolean espacio = key == 32;

	    if (!(minusculas || mayusculas || espacio)) {
	        e.consume();
	    }

	    if (textField.getText().trim().length() == maxLength) {
	        e.consume();
	        JOptionPane.showMessageDialog(null, "¡Solo puede ingresar " + maxLength + " caracteres!", 
	                "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    String texto = textField.getText();

	    if (texto.isEmpty() || texto.endsWith(" ")) {
	        e.setKeyChar(Character.toUpperCase(key));
	    } else {
	        e.setKeyChar(Character.toLowerCase(key));
	    }
	}
	
	
	
	// Validación que incluye: la primera letra siempre mayúscula, ñ, acentos, puntos, comas y guiones
	public static void validarTextoConFormato(KeyEvent e, JTextField textField, int maxLength) {
	    char key = e.getKeyChar();
	    boolean mayusculas = (key >= 65 && key <= 90) || key == 209 || "ÁÉÍÓÚÜ".indexOf(key) != -1; // A-Z, Ñ, o vocales con acento
	    boolean minusculas = (key >= 97 && key <= 122) || key == 241 || "áéíóúü".indexOf(key) != -1; // a-z, ñ, o vocales con acento
	    boolean permitido = mayusculas || minusculas || key == 32 || key == 46 || key == 44 || key == 45; // Espacio, punto, coma, guión

	    // Si el carácter no está permitido, lo consumimos
	    if (!permitido) {
	        e.consume();
	        return;
	    }

	    // Limitar la longitud máxima del texto
	    if (textField.getText().trim().length() >= maxLength) {
	        e.consume();
	        JOptionPane.showMessageDialog(null, "¡Solo puede ingresar " + maxLength + " caracteres!", 
	                "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Convertir la primera letra a mayúscula
	    String texto = textField.getText();
	    if (texto.isEmpty()) { // Si el campo está vacío, forzar la primera letra en mayúscula
	        e.setKeyChar(Character.toUpperCase(key));
	    }
	}



    
    
    
    
    ///////////////////////////////////////JTextArea
    // Longitud de un jtextArea
    public static void validarLongitud(KeyEvent e, JTextArea textArea, int maxLength) {
      
        if (textArea.getText().trim().length() == maxLength) {
            e.consume();  
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar " + maxLength + " caracteres!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Método para capitalizar la primera letra del JTextArea
    public static void capitalizarPrimeraLetra(JTextArea textArea) {
        String text = textArea.getText();

        if (!text.isEmpty() && Character.isLetter(text.charAt(0)) && Character.isLowerCase(text.charAt(0))) {
            textArea.setText(Character.toUpperCase(text.charAt(0)) + text.substring(1));
        }
    }
    
    ///////////////////////////////////////JDateChooser
    // Evitar la escritura
    public static void deshabilitarEscrituraJDateChooser(JDateChooser dateChooser) {
        JTextField dateEditor = ((JTextField) dateChooser.getDateEditor().getUiComponent());
        dateEditor.setEditable(false);
        dateEditor.setForeground(Color.BLACK);
    }
    
    
    ///////////////////////////////////////ID
    // solo números y un tamaño máximo
    public static void validarNumerosID(KeyEvent e, JTextField textField, int maxLength) {
        int key = e.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            e.consume(); 
        }

        if (textField.getText().trim().length() == maxLength) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar " + maxLength + " números!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    ///////////////////////////////////////Identidad
    public static void validarIdentidad(KeyEvent e, JTextField textField) {
        int key = e.getKeyChar();
        String currentText = textField.getText().trim();
        boolean numeros = key >= 48 && key <= 57;  // Números (0-9)
        boolean guion = key == 45;  // Guión (-)

        if (!(numeros || guion)) {
            e.consume();
        }

        if (guion && (currentText.length() != 4 && currentText.length() != 9)) {
            e.consume();
        }

        if (currentText.length() == 15) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar 13 números y 2 guiones!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    ///////////////////////////////////////Telefono
    // Telefono: 8 numeros y 1 guion
    public static void validarTelefono(KeyEvent e, JTextField textField) {
        int key = e.getKeyChar();
        String currentText = textField.getText().trim();
        boolean numeros = key >= 48 && key <= 57;  // Números (0-9)
        boolean guion = key == 45;  // Guión (-)

        if (!(numeros || guion)) {
            e.consume();
        }

        // Solo permitir guión en la posición 5
        if (guion && currentText.length() != 4) {
            e.consume();
        }

        if (currentText.length() == 9) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar 8 números y 1 guion!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    ///////////////////////////////////////////correo electronico
    //formato y los dominios permitidos
    public static void validarCorreo(FocusEvent e, JTextField textField) {
        String correo = textField.getText().trim();

        // Expresión regular para validar correos electrónicos
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+(com|org|net|edu|gov|mil|biz|info|hn)$";
        String[] dominiosPermitidos = { "gmail.com", "outlook.com", "yahoo.com", "yahoo.es", "unah.hn", "unah.edu.hn", "hotmail.com" };

        if (!correo.matches(emailRegex) || !esDominioPermitido(correo, dominiosPermitidos)) {
            //JOptionPane.showMessageDialog(null, "Correo electrónico no válido. Debe tener un formato como ejemplo@gmail.com, ejemplo@outlook.com, etc.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            textField.requestFocus();
        }
    }

    // Método auxiliar para verificar si el dominio está permitido
    private static boolean esDominioPermitido(String correo, String[] dominiosPermitidos) {
        for (String dominio : dominiosPermitidos) {
            if (correo.endsWith(dominio)) {
                return true;
            }
        }
        return false;
    }
    
    
    ///////////////////////////////////////////Solo numeros
    // un tamaño máximo en un JTextField
    public static void validarSoloNumeros(KeyEvent e, JTextField textField, int maxLength) {
        int key = e.getKeyChar();
        
        boolean numeros = key >= 48 && key <= 57;
        if (!numeros) {
            e.consume();  // No permitir caracteres que no sean números
        }

        if (textField.getText().trim().length() == maxLength) {
            e.consume(); 
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar " + maxLength + " números!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    
    // Método para validar solo letras con un máximo de 50 caracteres
    public static void validarSoloLetras(KeyEvent e, JTextField textField) {
        char key = e.getKeyChar();
        
        boolean mayusculas = (key >= 65 && key <= 90) || key == 209;  // A-Z o Ñ
        boolean minusculas = (key >= 97 && key <= 122) || key == 241; // a-z o ñ
        boolean espacio = key == 32; // Permitir espacio

        if (!(mayusculas || minusculas || espacio)) {
            e.consume();
        }

        if (textField.getText().trim().length() == 50) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar 50 caracteres!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String texto = textField.getText();

        if (texto.isEmpty() || texto.endsWith(" ")) {
            e.setKeyChar(Character.toUpperCase(key));
        } else {
            e.setKeyChar(Character.toLowerCase(key));
        }
    }
    
    
 // Método para validar letras y números con un máximo de 50 caracteres
    public static void validarLetrasYNumeros(KeyEvent e, JTextField textField) {
        char key = e.getKeyChar();
        boolean mayusculas = (key >= 65 && key <= 90) || key == 209;  
        boolean minusculas = (key >= 97 && key <= 122) || key == 241; 
        boolean numeros = (key >= 48 && key <= 57); 
        boolean espacio = key == 32; 
        
        if (!(mayusculas || minusculas || numeros || espacio)) {
            e.consume();
        }

        if (textField.getText().trim().length() == 50) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar 50 caracteres!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String texto = textField.getText();

        if (texto.isEmpty() || texto.endsWith(" ")) {
            e.setKeyChar(Character.toUpperCase(key));
        } else {
            e.setKeyChar(Character.toLowerCase(key));
        }
    }
    
    
    //validacion de numeros enteros, 6 digitos
    public static void validarCantidadesEnterasLargas(KeyEvent e, JTextField textField) {
        char key = e.getKeyChar();

        // Validar si el carácter es un número
        if (!Character.isDigit(key)) {
            e.consume(); // Bloquear caracteres no válidos
            return;
        }

        // Validar el tamaño máximo de 6 dígitos
        if (textField.getText().length() >= 6) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar hasta 6 dígitos!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    
    
    //validacion de numeros enteros, 2 digitos
    public static void validarCantidadesEnterasCortas(KeyEvent e, JTextField textField) {
        char key = e.getKeyChar();

        if (!Character.isDigit(key)) {
            e.consume(); // Bloquear caracteres no válidos
            return;
        }
        
        if (textField.getText().length() >= 2) {
            e.consume();
            JOptionPane.showMessageDialog(null, "¡Solo puede ingresar hasta 2 dígitos!", 
            		"Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }


}

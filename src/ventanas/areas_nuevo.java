package ventanas;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.areas;
import clases.validaciones;
import consultas.consultas_areas;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class areas_nuevo extends JFrame {
	public JTextField txtarea;
	public JButton btnregresar;
	public JButton btnactualizar;
	public JButton btnlimpiar;
	public JButton btnguardar;
	public JCheckBox chxeditar;
	public JTextField txtfecha;
	public consultas_areas consultas;
	public JTextField txtid;
	
	
	public areas_nuevo() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
		setResizable(false);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1050, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
	
		
		JLabel lblDatosDelCargo = new JLabel("DATOS DEL ÁREA\r\n");
		lblDatosDelCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelCargo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDatosDelCargo.setBackground(new Color(255, 153, 0));
		lblDatosDelCargo.setBounds(20, 25, 400, 33);
		getContentPane().add(lblDatosDelCargo);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(419, 10, 596, 54);
		getContentPane().add(panel_titulo_1);
		
		btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_area();
			}
		});
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		btnguardar.setBounds(478, 17, 90, 23);
		panel_titulo_1.add(btnguardar);
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtarea.setText("");			}
		});
		btnlimpiar.setToolTipText("Limpiar los campos");
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		btnlimpiar.setBounds(383, 17, 90, 23);
		panel_titulo_1.add(btnlimpiar);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar_area();
			}
		});
		btnactualizar.setToolTipText("Actualizar registro");
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		btnactualizar.setBounds(478, 17, 90, 23);
		panel_titulo_1.add(btnactualizar);
		
		btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areas_tabla tabla = new areas_tabla();
				tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
		 		tabla.construirTabla();
				dispose();
			}
		});
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(26, 17, 90, 23);
		panel_titulo_1.add(btnregresar);
		
		chxeditar = new JCheckBox("Editar registro");
		chxeditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chxeditar.isSelected()) {
		        	 
		             habilitarCampos(true); 
		             btnactualizar.setVisible(true); 
		             btnlimpiar.setVisible(true);
		         } else {
		        	 
		             habilitarCampos(false); 
		             btnactualizar.setVisible(false);
		             btnlimpiar.setVisible(false);
		         }
			}
		});
		chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chxeditar.setBounds(249, 17, 105, 21);
		panel_titulo_1.add(chxeditar);
		
		JPanel panel_titulo_1_1 = new JPanel();
		panel_titulo_1_1.setLayout(null);
		panel_titulo_1_1.setBackground(SystemColor.menu);
		panel_titulo_1_1.setBounds(20, 71, 995, 494);
		getContentPane().add(panel_titulo_1_1);
		
		JLabel lblNombreDelCargo = new JLabel("Nombre del área");
		lblNombreDelCargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDelCargo.setBounds(80, 81, 141, 25);
		panel_titulo_1_1.add(lblNombreDelCargo);
		
		txtarea = new JTextField(10);
		txtarea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarTextoConFormato(e, txtarea, 70);
        	}

		});
		txtarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtarea.setBounds(231, 77, 253, 33);
		panel_titulo_1_1.add(txtarea);
		
		JLabel lblhoy_es = new JLabel("Fecha de creación\r\n");
		lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
		lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhoy_es.setBounds(599, 85, 143, 25);
		panel_titulo_1_1.add(lblhoy_es);
		
		txtfecha = new JTextField();
		txtfecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtfecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtfecha.setEditable(false);
		txtfecha.setColumns(10);
		txtfecha.setBackground(SystemColor.menu);
		txtfecha.setBounds(752, 81, 109, 33);
		panel_titulo_1_1.add(txtfecha);
		
		 setFechaActual(txtfecha);
		 
		 txtid = new JTextField();
		 txtid.setBackground(SystemColor.menu);
		 txtid.setBounds(645, 10, 0, 3);
		 panel_titulo_1_1.add(txtid);
		 txtid.setColumns(10);
		 
		consultas = new consultas_areas();
	}
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public boolean validaciones() {
		if (txtarea.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Nombre del área' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }
		 return true;
	}
	
	
    
	private void setFechaActual(JTextField txtFechaCreacion) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaActual = new Date();
        txtFechaCreacion.setText(sdf.format(fechaActual));
        txtFechaCreacion.setEditable(false); 
    }

	private void guardar_area() {
        if (!validaciones()) {
            return; 
        }

        try {
        	areas area = new areas();
        	area.setAreas(txtarea.getText());
        	area.setFecha_creacion(new SimpleDateFormat("dd-MM-yyyy").parse(txtfecha.getText()));

            if (consultas.verificarExistenciaArea(area.getAreas())) {
                JOptionPane.showMessageDialog(null, "El área ya existe en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            if (consultas.guardarArea(area)) {
                JOptionPane.showMessageDialog(null, "El área se ha registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                areas_tabla tabla = new areas_tabla();
                tabla.setLocationRelativeTo(null);
	            tabla.setVisible(true);
	            tabla.construirTabla();
	            dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el área", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	private void actualizar_area() {
	    if (!validaciones()) {
	        return; 
	    }

	    if (txtid.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo de Id área está vacío", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        areas area = new areas();
	        int idArea = Integer.parseInt(txtid.getText().trim()); 
	        area.setId_areas(idArea);
	        area.setAreas(txtarea.getText());
	        area.setFecha_creacion(new SimpleDateFormat("dd-MM-yyyy").parse(txtfecha.getText()));

	        if (consultas.verificarExistenciaArea(area.getAreas()) &&
	            !consultas.isAreaIdMatch(area.getId_areas(), area.getAreas())) {
	            JOptionPane.showMessageDialog(null, "El nombre del área ya existe en otro registro", "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }

	        if (consultas.actualizarArea(area)) {
	            JOptionPane.showMessageDialog(null, "El área se ha actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            areas_tabla n =  new areas_tabla();
	            n.setVisible(true);
	            n.setLocationRelativeTo(null);
	            n.construirTabla();
	            dispose();
	            
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al actualizar el área", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Error El Id debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


    public void habilitarCampos(boolean habilitar) {
        txtarea.setEnabled(habilitar);
        txtfecha.setEnabled(habilitar);
        btnguardar.setEnabled(habilitar);
        btnactualizar.setEnabled(habilitar);
        btnlimpiar.setEnabled(habilitar);
    }
}

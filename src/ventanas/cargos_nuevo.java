package ventanas;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.cargos;
import clases.validaciones;
import consultas.consultas_cargos;

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
public class cargos_nuevo extends JFrame {
	public JTextField txtcargo;
	public JButton btnregresar;
	public JButton btnactualizar;
	public JButton btnlimpiar;
	public JButton btnguardar;
	public JCheckBox chxeditar;
	public JTextField txtfecha;
	 private consultas_cargos consultas;
	 public JTextField txtid;
	
	
	public cargos_nuevo() {
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
		
	
		
		JLabel lblDatosDelCargo = new JLabel("DATOS DEL CARGO");
		lblDatosDelCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelCargo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDatosDelCargo.setBackground(new Color(255, 153, 0));
		lblDatosDelCargo.setBounds(20, 24, 410, 33);
		getContentPane().add(lblDatosDelCargo);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(428, 10, 587, 54);
		getContentPane().add(panel_titulo_1);
		
		btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_cargo();
			}
		});
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		btnguardar.setBounds(465, 17, 90, 23);
		panel_titulo_1.add(btnguardar);
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtcargo.setText("");			}
		});
		btnlimpiar.setToolTipText("Limpiar los campos");
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		btnlimpiar.setBounds(370, 17, 90, 23);
		panel_titulo_1.add(btnlimpiar);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar_cargo();
			}
		});
		btnactualizar.setToolTipText("Actualizar registro");
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		btnactualizar.setBounds(465, 17, 90, 23);
		panel_titulo_1.add(btnactualizar);
		
		btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_tabla tabla = new cargos_tabla();
				tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
		 		tabla.construirTabla();
				dispose();
			}
		});
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(46, 17, 90, 23);
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
		chxeditar.setBounds(259, 17, 105, 21);
		panel_titulo_1.add(chxeditar);
		
		JPanel panel_titulo_1_1 = new JPanel();
		panel_titulo_1_1.setLayout(null);
		panel_titulo_1_1.setBackground(SystemColor.menu);
		panel_titulo_1_1.setBounds(20, 72, 995, 496);
		getContentPane().add(panel_titulo_1_1);
		
		JLabel lblNombreDelCargo = new JLabel("Nombre del cargo");
		lblNombreDelCargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDelCargo.setBounds(88, 98, 141, 25);
		panel_titulo_1_1.add(lblNombreDelCargo);
		
		txtcargo = new JTextField(10);
		txtcargo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarTextoConFormato(e, txtcargo, 70);
        	}

		});
		txtcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcargo.setBounds(239, 94, 253, 33);
		panel_titulo_1_1.add(txtcargo);
		
		JLabel lblhoy_es = new JLabel("Fecha de creación\r\n");
		lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
		lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhoy_es.setBounds(645, 94, 143, 25);
		panel_titulo_1_1.add(lblhoy_es);
		
		txtfecha = new JTextField();
		txtfecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtfecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtfecha.setEditable(false);
		txtfecha.setColumns(10);
		txtfecha.setBackground(SystemColor.menu);
		txtfecha.setBounds(798, 90, 109, 33);
		panel_titulo_1_1.add(txtfecha);
		
		 setFechaActual(txtfecha);
		 
		 txtid = new JTextField();
		 txtid.setBackground(SystemColor.menu);
		 txtid.setBounds(645, 10, 0, 3);
		 panel_titulo_1_1.add(txtid);
		 txtid.setColumns(10);
		 
		consultas = new consultas_cargos();
	}
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public boolean validaciones() {
		if (txtcargo.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo 'Nombre del cargo' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
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

	private void guardar_cargo() {
        if (!validaciones()) {
            return; // Si las validaciones fallan, no procede
        }

        try {
            cargos cargo = new cargos();
            cargo.setCargos(txtcargo.getText());
            cargo.setFecha_creacion(new SimpleDateFormat("dd-MM-yyyy").parse(txtfecha.getText()));

            if (consultas.verificarExistenciaCargo(cargo.getCargos())) {
                JOptionPane.showMessageDialog(null, "El cargo ya existe en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            if (consultas.guardarCargo(cargo)) {
                JOptionPane.showMessageDialog(null, "El cargo se ha registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargos_tabla tabla = new cargos_tabla();
                tabla.setLocationRelativeTo(null);
	            tabla.setVisible(true);
	            tabla.construirTabla();
	            dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar el cargo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	private void actualizar_cargo() {
	    if (!validaciones()) {
	        return; 
	    }

	    if (txtid.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "El campo de Id cargo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try {
	        cargos cargo = new cargos();
	        
	        int idCargo = Integer.parseInt(txtid.getText().trim()); 
	        cargo.setId_cargos(idCargo);
	        cargo.setCargos(txtcargo.getText());
	        cargo.setFecha_creacion(new SimpleDateFormat("dd-MM-yyyy").parse(txtfecha.getText()));

	        if (consultas.verificarExistenciaCargo(cargo.getCargos()) &&
	            !consultas.isCargoIdMatch(cargo.getId_cargos(), cargo.getCargos())) {
	            JOptionPane.showMessageDialog(null, "El nombre del cargo ya existe en otro registro.", "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }

	        if (consultas.actualizarCargo(cargo)) {
	            JOptionPane.showMessageDialog(null, "El cargo se ha actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            cargos_tabla tabla = new cargos_tabla();
                tabla.setLocationRelativeTo(null);
	            tabla.setVisible(true);
	            tabla.construirTabla();
	            dispose();
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al actualizar el cargo", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Error: El Id debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error al realizar la operación", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


  
    public void habilitarCampos(boolean habilitar) {
        txtcargo.setEnabled(habilitar);
        txtfecha.setEnabled(habilitar);
        btnguardar.setEnabled(habilitar);
        btnactualizar.setEnabled(habilitar);
        btnlimpiar.setEnabled(habilitar);
    }
}

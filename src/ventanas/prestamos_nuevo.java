package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import clases.prestamos;
import clases.validaciones;
import conexion.conexion;
import consultas.consultas_prestamos;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class prestamos_nuevo extends JFrame{
	public JTextField txtapellidos;
	public JTextField txtcuenta;
	public JTextField txtcodigo;
	public JTextField txtcargo;
	public JTextField txtarea;
	public JTextField txtmonto;
	public JTextField txtid;
	public JTextField txtinteres_deducible;
	public JTextField txtcuota;
	public JTextField txtcargo_autorizado1;
	public JTextField txtcargo_autorizado2;
	public JComboBox<String> cbxnombres;
	public JDateChooser fecha_aprobacion;
	public JTextArea txadireccion;
	public JTextArea txamotivo;
	public JButton btnguardar;
	public JButton btnactualizar;
	public JButton btnregresar;
	public JButton btnlimpiar;
	public JCheckBox chxeditar;
	public JTextField txtplazo;
	public JTextField txttasa;
	public JTextField txtautorizado1;
	public JTextField txtautorizado2;
	
	
	
	
	
	public prestamos_nuevo() {
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
		
		JPanel panel_datos = new JPanel();
		panel_datos.setLayout(null);
		panel_datos.setBackground(SystemColor.menu);
		panel_datos.setBounds(34, 85, 975, 498);
		getContentPane().add(panel_datos);
		
		txtapellidos = new JTextField();
		txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtapellidos.setEditable(false);
		txtapellidos.setColumns(10);
		txtapellidos.setBounds(31, 128, 208, 33);
		panel_datos.add(txtapellidos);
		
		JLabel lblnombres = new JLabel("Nombres");
		lblnombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres.setBounds(31, 37, 166, 25);
		panel_datos.add(lblnombres);
		
		JLabel lblapellidos = new JLabel("Apellidos");
		lblapellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblapellidos.setBounds(31, 104, 166, 25);
		panel_datos.add(lblapellidos);
		
		JLabel lblTelfono = new JLabel("No. cuenta");
		lblTelfono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTelfono.setBounds(268, 104, 200, 25);
		panel_datos.add(lblTelfono);
		
		txtcuenta = new JTextField();
		txtcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcuenta.setEditable(false);
		txtcuenta.setColumns(10);
		txtcuenta.setBounds(268, 127, 208, 33);
		panel_datos.add(txtcuenta);
		
		JLabel lblarea = new JLabel("Área");
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(750, 104, 200, 25);
		panel_datos.add(lblarea);
		
		JLabel lblCdigo = new JLabel("Código cooperativista");
		lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCdigo.setBounds(268, 37, 221, 25);
		panel_datos.add(lblCdigo);
		
		txtcodigo = new JTextField();
		txtcodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcodigo.setEditable(false);
		txtcodigo.setColumns(10);
		txtcodigo.setBounds(268, 61, 207, 33);
		panel_datos.add(txtcodigo);
		
		JLabel lblcargo = new JLabel("Cargo");
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(749, 37, 200, 25);
		panel_datos.add(lblcargo);
		
		txtcargo = new JTextField();
		txtcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcargo.setEditable(false);
		txtcargo.setColumns(10);
		txtcargo.setBounds(749, 61, 200, 33);
		panel_datos.add(txtcargo);
		
		txtarea = new JTextField();
		txtarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtarea.setEditable(false);
		txtarea.setColumns(10);
		txtarea.setBounds(750, 128, 200, 33);
		panel_datos.add(txtarea);
		
		cbxnombres = new JComboBox<String>();
		cbxnombres.setSelectedIndex(-1);
		cbxnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxnombres.setBounds(30, 61, 208, 33);
		panel_datos.add(cbxnombres);
		
		JLabel lblDatosDel_1 = new JLabel("_______ Datos del solicitante ___________________________________________________________________________________");
		lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDel_1.setForeground(Color.GRAY);
		lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDel_1.setBounds(30, 10, 919, 28);
		panel_datos.add(lblDatosDel_1);
		
		JLabel lblTiempo = new JLabel("Plazo de pago en meses");
		lblTiempo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTiempo.setBounds(268, 219, 221, 25);
		panel_datos.add(lblTiempo);
		
		JLabel lblIntereses = new JLabel("Tasa de interés");
		lblIntereses.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIntereses.setBounds(268, 298, 208, 25);
		panel_datos.add(lblIntereses);
		
		JLabel lblhoy_es = new JLabel("Fecha de aprobación");
		lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
		lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhoy_es.setBounds(31, 219, 208, 25);
		panel_datos.add(lblhoy_es);
		
		JLabel lblDatosDel_2 = new JLabel("_______ Datos del préstamo ___________________________________________________________________________________");
		lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDel_2.setForeground(Color.GRAY);
		lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDel_2.setBounds(31, 181, 919, 28);
		panel_datos.add(lblDatosDel_2);
		
		JLabel lblcorreo_electronico_1 = new JLabel("Dirección");
		lblcorreo_electronico_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico_1.setBounds(508, 37, 200, 25);
		panel_datos.add(lblcorreo_electronico_1);
		
		JLabel lblNombreDeQuien_1 = new JLabel("Monto solicitado");
		lblNombreDeQuien_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDeQuien_1.setBounds(31, 298, 208, 25);
		panel_datos.add(lblNombreDeQuien_1);
		
		txtmonto = new JTextField();
		
		txtmonto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtmonto.setColumns(10);
		txtmonto.setBounds(31, 321, 208, 33);
		panel_datos.add(txtmonto);
		txtmonto.addKeyListener(new java.awt.event.KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarCantidadesEnterasLargas(e, txtmonto);
		    }
		});

		
		txtid = new JTextField();
		txtid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(964, 10, 1, 6);
		panel_datos.add(txtid);
		
		fecha_aprobacion = new JDateChooser();
		fecha_aprobacion.setDateFormatString("dd-MM-yy");
		fecha_aprobacion.setBounds(31, 242, 208, 33);
		panel_datos.add(fecha_aprobacion);
		
		txadireccion = new JTextArea();
		txadireccion.setBackground(SystemColor.control);
		txadireccion.setEditable(false);
		txadireccion.setWrapStyleWord(true);
		txadireccion.setLineWrap(true);
		txadireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txadireccion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txadireccion.setBounds(507, 61, 208, 100);
		panel_datos.add(txadireccion);
		
		txamotivo = new JTextArea();
		txamotivo.setWrapStyleWord(true);
		txamotivo.setLineWrap(true);
		txamotivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txamotivo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txamotivo.setBounds(742, 243, 208, 111);
		panel_datos.add(txamotivo);
		txamotivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarLongitud(e, txamotivo, 100);
				validaciones.capitalizarPrimeraLetra(txamotivo);
			}
		});
		txamotivo.setLineWrap(true); // Activa el ajuste de líneas
		txamotivo.setWrapStyleWord(true); // Ajusta el texto en palabras completas
		txamotivo.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // agrega borde
		
		
		JLabel lblcorreo_electronico_1_1 = new JLabel("Motivo del préstamo");
		lblcorreo_electronico_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico_1_1.setBounds(743, 219, 207, 25);
		panel_datos.add(lblcorreo_electronico_1_1);
		
		JLabel lblNombreDeQuien_1_1 = new JLabel("Interés deducible mensual");
		lblNombreDeQuien_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDeQuien_1_1.setBounds(508, 298, 221, 25);
		panel_datos.add(lblNombreDeQuien_1_1);
		
		txtinteres_deducible = new JTextField();
		txtinteres_deducible.setBackground(new Color(255, 255, 255));
		txtinteres_deducible.setEditable(false);
		txtinteres_deducible.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtinteres_deducible.setColumns(10);
		txtinteres_deducible.setBounds(508, 321, 208, 33);
		panel_datos.add(txtinteres_deducible);
		
		txtcuota = new JTextField();
		txtcuota.setBackground(new Color(255, 255, 255));
		txtcuota.setEditable(false);
		txtcuota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcuota.setColumns(10);
		txtcuota.setBounds(508, 242, 208, 33);
		panel_datos.add(txtcuota);
		
		JLabel lblNombreDeQuien_1_1_1 = new JLabel("Cuota mensual");
		lblNombreDeQuien_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDeQuien_1_1_1.setBounds(508, 219, 200, 25);
		panel_datos.add(lblNombreDeQuien_1_1_1);
		
		JLabel lblNombreDelFiscal = new JLabel("Autorizado por ");
		lblNombreDelFiscal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombreDelFiscal.setBounds(509, 376, 223, 25);
		panel_datos.add(lblNombreDelFiscal);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCargo.setBounds(743, 376, 166, 25);
		panel_datos.add(lblCargo);
		
		JLabel lblnombres_3_1 = new JLabel("Autorizado por ");
		lblnombres_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres_3_1.setBounds(32, 376, 227, 25);
		panel_datos.add(lblnombres_3_1);
		
		JLabel lblnombres_4_1 = new JLabel("Cargo");
		lblnombres_4_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres_4_1.setBounds(269, 376, 166, 25);
		panel_datos.add(lblnombres_4_1);
		
		txtcargo_autorizado1 = new JTextField();
		txtcargo_autorizado1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcargo_autorizado1.setColumns(10);
		txtcargo_autorizado1.setBounds(268, 402, 208, 33);
		panel_datos.add(txtcargo_autorizado1);
		txtcargo_autorizado1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarTextoConFormato(e, txtcargo_autorizado1, 50);
			}
		});
		
		
		txtcargo_autorizado2 = new JTextField();
		txtcargo_autorizado2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcargo_autorizado2.setColumns(10);
		txtcargo_autorizado2.setBounds(742, 402, 208, 33);
		panel_datos.add(txtcargo_autorizado2);
		txtcargo_autorizado2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarTextoConFormato(e, txtcargo_autorizado2, 50);
			}
		});
		
		
		txttasa = new JTextField();
		txttasa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttasa.setColumns(10);
		txttasa.setBounds(268, 321, 208, 33);
		panel_datos.add(txttasa);
		txttasa.addKeyListener(new java.awt.event.KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarCantidadesEnterasCortas(e, txttasa);
		    }
		});
		
		
		
		txtplazo = new JTextField();
		txtplazo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtplazo.setColumns(10);
		txtplazo.setBounds(268, 242, 208, 33);
		panel_datos.add(txtplazo);
		txtplazo.addKeyListener(new java.awt.event.KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarCantidadesEnterasCortas(e, txtplazo);
		    }
		});

		
		txtautorizado1 = new JTextField();
		txtautorizado1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtautorizado1.setColumns(10);
		txtautorizado1.setBounds(31, 402, 208, 33);
		panel_datos.add(txtautorizado1);
		txtautorizado1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarNombresyApellidos(e, txtautorizado1, 70);
			}
		});
		
		txtautorizado2 = new JTextField();
		txtautorizado2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtautorizado2.setColumns(10);
		txtautorizado2.setBounds(508, 402, 208, 33);
		panel_datos.add(txtautorizado2);
		txtautorizado2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarNombresyApellidos(e, txtautorizado2, 70);
			}
		});
		
		
		
		JLabel lblDatosDelPrstamo = new JLabel("NUEVO PRÉSTAMO");
		lblDatosDelPrstamo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDelPrstamo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblDatosDelPrstamo.setBackground(new Color(255, 153, 0));
		lblDatosDelPrstamo.setBounds(34, 31, 519, 36);
		getContentPane().add(lblDatosDelPrstamo);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setLayout(null);
		panel_botones.setBackground(SystemColor.menu);
		panel_botones.setBounds(541, 21, 468, 65);
		getContentPane().add(panel_botones);
		
		btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
			        guardarPrestamo(); // Método que guarda el préstamo
			    }
			}
		});
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		btnguardar.setBounds(367, 17, 90, 23);
		panel_botones.add(btnguardar);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validarCampos()) {
			        actualizarPrestamo(); // Método que guarda el préstamo
			    }
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		btnactualizar.setBounds(367, 17, 90, 23);
		panel_botones.add(btnactualizar);
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				
			}
		});
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		btnlimpiar.setBounds(272, 17, 90, 23);
		panel_botones.add(btnlimpiar);
		
		btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prestamos_tabla t = new prestamos_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(10, 17, 90, 23);
		panel_botones.add(btnregresar);
		
		chxeditar = new JCheckBox("Editar registro");
		chxeditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean habilitar = chxeditar.isSelected();
			    cambiarEstadoComponentes(habilitar);
			}
		});
		chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chxeditar.setBounds(146, 17, 119, 21);
		panel_botones.add(chxeditar);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		establecerFechaActual();
		
		//cbxnombres y cbxautorizado1 y 2
		cargarNombresEnComboBoxes();
	    completarDatosPorNombre();
	    
	    //calculo de interes mensual y letra
	    agregarListeners();
		
		
		
	}//class
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	private void establecerFechaActual() {
	    fecha_aprobacion.setDate(new java.util.Date()); // Establece la fecha actual
	}
	
	
	//cargar nombres en el cbxnombres y cbxautorizado1 y 2
	private void cargarNombresEnComboBoxes() {
	    String sql = "SELECT nombres_empleado FROM socios";
	    conexion conn = new conexion(); 

	    try (Connection con = conn.conectar();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {
	        
	        cbxnombres.removeAllItems();

	        cbxnombres.addItem("");

	        while (rs.next()) {
	            String nombre = rs.getString("nombres_empleado");
	            cbxnombres.addItem(nombre);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar nombres: " + e.getMessage());
	    }
	}


	private void completarDatosPorNombre() {
	    cbxnombres.addActionListener(e -> {
	        String nombreSeleccionado = (String) cbxnombres.getSelectedItem();

	        if (nombreSeleccionado != null && !nombreSeleccionado.isEmpty()) {
	            String sql = "SELECT apellidos_empleado, id_empleado, cuenta_empleado, direccion_empleado, cargo_empleado, area_empleado " +
	                         "FROM socios WHERE nombres_empleado = ?";
	            conexion conn = new conexion(); 

	            try (Connection con = conn.conectar(); 
	                 PreparedStatement pst = con.prepareStatement(sql)) {
	                
	                pst.setString(1, nombreSeleccionado);

	                try (ResultSet rs = pst.executeQuery()) {
	                    if (rs.next()) {
	                        txtapellidos.setText(rs.getString("apellidos_empleado"));
	                        txtcodigo.setText(rs.getString("id_empleado"));
	                        txtcuenta.setText(rs.getString("cuenta_empleado"));
	                        txadireccion.setText(rs.getString("direccion_empleado"));
	                        txtcargo.setText(rs.getString("cargo_empleado"));
	                        txtarea.setText(rs.getString("area_empleado"));
	                    }
	                }
	            } catch (SQLException ex) {
	                JOptionPane.showMessageDialog(this, "Error al completar datos: " + ex.getMessage());
	            }
	        }
	    });
	}
	
	
	private void calcularInteresDeducible() {
	    try {
	        double monto = Double.parseDouble(txtmonto.getText());
	        double tasa = Double.parseDouble(txttasa.getText()) / 100;

	        double interesDeducible = monto * tasa;
	        txtinteres_deducible.setText(String.format("%.2f", interesDeducible)); // Muestra 2 decimales
	    } catch (NumberFormatException e) {
	        txtinteres_deducible.setText(""); // Limpia el campo si hay error
	    }
	}


	
	

	private void calcularLetraMensual() {
	    try {
	        double monto = Double.parseDouble(txtmonto.getText());
	        int plazo = Integer.parseInt(txtplazo.getText());

	        double cuota = monto / plazo;
	        txtcuota.setText(String.format("%.2f", cuota)); // Muestra 2 decimales
	    } catch (NumberFormatException e) {
	        txtcuota.setText(""); // Limpia el campo si hay error
	    }
	}


	
	
	
	private void agregarListeners() {
	    // Listener para txtmonto
	    txtmonto.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }
	    });

	    // Listener para txttasa
	    txttasa.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }
	    });

	    // Listener para txtplazo
	    txtplazo.getDocument().addDocumentListener(new DocumentListener() {
	        @Override
	        public void insertUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void removeUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }

	        @Override
	        public void changedUpdate(DocumentEvent e) {
	            realizarCalculos();
	        }
	    });
	}

	// Método que realiza ambos cálculos
	private void realizarCalculos() {
	    calcularInteresDeducible();
	    calcularLetraMensual();
	}
	
	
	private void limpiarFormulario() {
	    txtcodigo.setText("");
	    cbxnombres.setSelectedIndex(0);
	    txtapellidos.setText("");
	    txtcuenta.setText("");
	    txtcargo.setText("");
	    txtarea.setText("");
	    txadireccion.setText("");
	    fecha_aprobacion.setDate(new java.util.Date());
	    txtmonto.setText("");
	    txtplazo.setText("");
	    txttasa.setText("");
	    txtinteres_deducible.setText("");
	    txtcuota.setText("");
	    txamotivo.setText("");
	    txtautorizado1.setText("");
	    txtcargo_autorizado1.setText("");
	    txtautorizado2.setText("");
	    txtcargo_autorizado2.setText("");
	}

	
	public boolean validarCampos() {
	    if (cbxnombres.getSelectedIndex() == -1 || cbxnombres.getSelectedItem().toString().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "El campo 'Nombres' no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (fecha_aprobacion.getDate() == null) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de aprobación.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtmonto.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "El campo 'Monto solicitado' no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtplazo.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "El campo 'Plazo de pago' no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txttasa.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "El campo 'Tasa de interés' no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtautorizado1.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del primer autorizado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtcargo_autorizado1.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar el cargo del primer autorizado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtautorizado2.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar el nombre del segundo autorizado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txtcargo_autorizado2.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar el cargo del segundo autorizado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    if (txamotivo.getText().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Debe ingresar el motivo del préstamo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return false;
	    }

	    return true;
	}

	
	
	private void guardarPrestamo() {
	    if (txtmonto.getText().isEmpty() || txttasa.getText().isEmpty() || txtplazo.getText().isEmpty()) {
	    	JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    try {
	        // Crear un objeto de la clase prestamos
	        prestamos nuevoPrestamo = new prestamos();

	        nuevoPrestamo.setId_empleado(txtcodigo.getText());
	        nuevoPrestamo.setNombres_empleado((String) cbxnombres.getSelectedItem());
	        nuevoPrestamo.setApellidos_empleado(txtapellidos.getText());
	        nuevoPrestamo.setCuenta_empleado(txtcuenta.getText());
	        nuevoPrestamo.setDireccion_empleado(txadireccion.getText());
	        nuevoPrestamo.setCargo_empleado(txtcargo.getText());
	        nuevoPrestamo.setArea_empleado(txtarea.getText());
	        nuevoPrestamo.setFecha_aprobacion(fecha_aprobacion.getDate());
	        nuevoPrestamo.setMonto_solicitado(Integer.parseInt(txtmonto.getText()));
	        nuevoPrestamo.setPlazo_pago(Integer.parseInt(txtplazo.getText()));
	        nuevoPrestamo.setTasa_interes(Integer.parseInt(txttasa.getText()));
	        nuevoPrestamo.setInteres_deducible_mensual(Double.parseDouble(txtinteres_deducible.getText()));
	        nuevoPrestamo.setLetra_mensual(Double.parseDouble(txtcuota.getText()));
	        nuevoPrestamo.setMotivo_prestamo(txamotivo.getText());
	        nuevoPrestamo.setAutorizado1((String) txtautorizado1.getText());
	        nuevoPrestamo.setCargo_autorizado1(txtcargo_autorizado1.getText());
	        nuevoPrestamo.setAutorizado2((String) txtautorizado2.getText());
	        nuevoPrestamo.setCargo_autorizado2(txtcargo_autorizado2.getText());

	        // Guardar en la base de datos
	        consultas_prestamos consulta = new consultas_prestamos();
	        if (consulta.guardarPrestamo(nuevoPrestamo)) {
	        	JOptionPane.showMessageDialog(null, "El préstamo se ha guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            prestamos_tabla t = new prestamos_tabla();
		            t.setVisible(true);
		            t.setLocationRelativeTo(null);
		            t.construirTabla();
		            dispose();
	            
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al guardar el préstamo", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Error en los valores numéricos: " + e.getMessage());
	    }
	}
	
	public void cambiarEstadoComponentes(boolean habilitar) {
	    cbxnombres.setEnabled(habilitar);
	    fecha_aprobacion.setEnabled(habilitar);
	    txtmonto.setEnabled(habilitar);
	    txtplazo.setEnabled(habilitar);
	    txttasa.setEnabled(habilitar);
	    txamotivo.setEnabled(habilitar);
	    txtautorizado1.setEnabled(habilitar);
	    txtcargo_autorizado1.setEnabled(habilitar);
	    txtautorizado2.setEnabled(habilitar);
	    txtcargo_autorizado2.setEnabled(habilitar);

	    btnactualizar.setVisible(habilitar);
	    btnlimpiar.setVisible(habilitar);
	    btnguardar.setVisible(false); // Siempre oculto
	}
	
	
	private void actualizarPrestamo() {
	    if (validarCampos()) { // Asegúrate de que los campos están completos
	        try {

	            // Validar y convertir los campos numéricos
	            int montoSolicitado = txtmonto.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtmonto.getText().trim());
	            int plazoPago = txtplazo.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtplazo.getText().trim());
	            int tasaInteres = txttasa.getText().trim().isEmpty() ? 0 : Integer.parseInt(txttasa.getText().trim());
	            double interesDeducible = txtinteres_deducible.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtinteres_deducible.getText().trim());
	            double letraMensual = txtcuota.getText().trim().isEmpty() ? 0.0 : Double.parseDouble(txtcuota.getText().trim());

	            // Crear un objeto del préstamo con los datos actualizados
	            prestamos prestamoActualizado = new prestamos();
	            prestamoActualizado.setId(Integer.parseInt(txtid.getText())); // Asegúrate de capturar el ID del préstamo
	            prestamoActualizado.setId_empleado(txtcodigo.getText());
	            prestamoActualizado.setNombres_empleado((String) cbxnombres.getSelectedItem());
	            prestamoActualizado.setApellidos_empleado(txtapellidos.getText());
	            prestamoActualizado.setCuenta_empleado(txtcuenta.getText());
	            prestamoActualizado.setDireccion_empleado(txadireccion.getText());
	            prestamoActualizado.setCargo_empleado(txtcargo.getText());
	            prestamoActualizado.setArea_empleado(txtarea.getText());
	            prestamoActualizado.setFecha_aprobacion(fecha_aprobacion.getDate());
	            prestamoActualizado.setMonto_solicitado(montoSolicitado);
	            prestamoActualizado.setPlazo_pago(plazoPago);
	            prestamoActualizado.setTasa_interes(tasaInteres);
	            prestamoActualizado.setInteres_deducible_mensual(interesDeducible);
	            prestamoActualizado.setLetra_mensual(letraMensual);
	            prestamoActualizado.setMotivo_prestamo(txamotivo.getText());
	            prestamoActualizado.setAutorizado1(txtautorizado1.getText());
	            prestamoActualizado.setCargo_autorizado1(txtcargo_autorizado1.getText());
	            prestamoActualizado.setAutorizado2(txtautorizado2.getText());
	            prestamoActualizado.setCargo_autorizado2(txtcargo_autorizado2.getText());

	            // Llamar al método de actualización en consultas_prestamos
	            consultas_prestamos consulta = new consultas_prestamos();
	            if (consulta.actualizarPrestamo(prestamoActualizado)) {
	                JOptionPane.showMessageDialog(this, "Préstamo actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		                prestamos_tabla t = new prestamos_tabla();
			            t.setVisible(true);
			            t.setLocationRelativeTo(null);
			            t.construirTabla();
			            dispose();
		            
	            } else {
	                JOptionPane.showMessageDialog(this, "Error al actualizar el préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(this, "Error en los valores numéricos: " + e.getMessage(), 
	                                          "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}




}

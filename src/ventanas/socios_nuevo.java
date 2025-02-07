package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import clases.socios;
import clases.validaciones;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_socios;


@SuppressWarnings("serial")
public class socios_nuevo extends JFrame{
	public JTextField txtidentidad;
	public JTextField txtnombres;
	public JTextField txtapellidos;
	public JTextField txttel;
	public JTextField txtcorreo;
	public JTextField txtruta;
	public JTextField txtid_empleado;
	
	public JRadioButton buttonmasculino;
	public JRadioButton buttonfemenino;
	public JRadioButton buttonotro;
	public ButtonGroup grupoSexo;
	
	@SuppressWarnings("rawtypes")
	public JComboBox cbxcargo;
	@SuppressWarnings("rawtypes")
	public JComboBox cbxestado_civil;
	@SuppressWarnings("rawtypes")
	public JComboBox cbxarea;
	
	public JDateChooser fecha_nacimiento;
	public consultas_socios consultas;
	public JDateChooser fecha_inicio;
	public JDateChooser fecha_renuncia;
	
	public JTextArea txadireccion;
	
	public JButton btnseleccionar_foto;
	public JButton btneliminar_foto;
	public JButton btnguardar;
	public JButton btnactualizar;
	public JButton btnlimpiar;
	public JButton btnregresar;
	public JLabel lblfoto;
	public Date fechaActual = new Date();
	public JTextField txtcuenta;
	public JPanel panel_datos;
	
	ImageIcon icono_fotografia = new ImageIcon(getClass().getResource("/imagenes/camara.png"));

	public JTextField txtid;
	public JCheckBox chxeditar;
	public JTextField txtidOriginal;
	
	consultas_socios consulta = new consultas_socios();
	

	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public socios_nuevo() {
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
		
		panel_datos = new JPanel();
		panel_datos.setBackground(SystemColor.menu);
		panel_datos.setLayout(null);
		panel_datos.setBounds(25, 86, 985, 475);
		getContentPane().add(panel_datos);
		
		
        
		
		txtidentidad = new JTextField(15);
		txtidentidad.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarIdentidad(e, txtidentidad);
		    }
		});

		txtidentidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtidentidad.setColumns(10);
		txtidentidad.setBounds(29, 123, 253, 33);
		panel_datos.add(txtidentidad);
		
		
		
		
		txtnombres = new JTextField();
		txtnombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarNombresyApellidos(e, txtnombres, 70);
        	}

		});
		txtnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtnombres.setColumns(10);
		txtnombres.setBounds(29, 190, 253, 33);
		panel_datos.add(txtnombres);
		
		txtapellidos = new JTextField();
		txtapellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarNombresyApellidos(e, txtapellidos, 70);
        	}

		});
		txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtapellidos.setColumns(10);
		txtapellidos.setBounds(29, 260, 253, 33);
		panel_datos.add(txtapellidos);
		
		buttonmasculino = new JRadioButton("Masculino");
		buttonmasculino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonmasculino.setBackground(SystemColor.menu);
		buttonmasculino.setBounds(329, 127, 99, 33);
		panel_datos.add(buttonmasculino);
		
		buttonfemenino = new JRadioButton("Femenino");
		buttonfemenino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonfemenino.setBackground(SystemColor.menu);
		buttonfemenino.setBounds(429, 127, 95, 33);
		panel_datos.add(buttonfemenino);
		
		buttonotro = new JRadioButton("Otro");
		buttonotro.setBackground(SystemColor.menu);
		buttonotro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonotro.setBounds(526, 127, 78, 33);
		panel_datos.add(buttonotro);
		
		grupoSexo = new ButtonGroup();
		grupoSexo.add(buttonmasculino);
		grupoSexo.add(buttonfemenino);
		grupoSexo.add(buttonotro);
		
		JLabel lblidentidad = new JLabel("Número de identidad");
		lblidentidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblidentidad.setBounds(31, 99, 251, 25);
		panel_datos.add(lblidentidad);
		
		JLabel lblnombres = new JLabel("Nombres");
		lblnombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres.setBounds(30, 166, 166, 25);
		panel_datos.add(lblnombres);
		
		JLabel lblapellidos = new JLabel("Apellidos");
		lblapellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblapellidos.setBounds(29, 236, 166, 25);
		panel_datos.add(lblapellidos);
		
		JLabel lblsexo = new JLabel("Género");
		lblsexo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblsexo.setBounds(332, 96, 57, 25);
		panel_datos.add(lblsexo);
		
		JLabel lblfecha_nacimiento = new JLabel("Fecha de nacimiento");
		lblfecha_nacimiento.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblfecha_nacimiento.setBounds(329, 170, 217, 25);
		panel_datos.add(lblfecha_nacimiento);
		
		JLabel lbltelefono = new JLabel("Número de teléfono");
		lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltelefono.setBounds(29, 307, 200, 25);
		panel_datos.add(lbltelefono);
		
		txttel = new JTextField();
		txttel.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarTelefono(e, txttel);
		    }
		});

		txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttel.setColumns(10);
		txttel.setBounds(29, 336, 253, 33);
		panel_datos.add(txttel);
		
		JLabel lblarea = new JLabel("Área de trabajo");
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(630, 379, 200, 25);
		panel_datos.add(lblarea);
		
		txtcorreo = new JTextField();
		txtcorreo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaciones.validarCorreo(e, txtcorreo);
			}
		});
		txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcorreo.setColumns(10);
		txtcorreo.setBounds(29, 403, 253, 33);
		panel_datos.add(txtcorreo);
		
		JLabel lblcorreo_electronico = new JLabel("Correo electrónico");
		lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico.setBounds(30, 379, 166, 25);
		panel_datos.add(lblcorreo_electronico);
		
		JPanel panel_foto = new JPanel();
		panel_foto.setLayout(null);
		panel_foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_foto.setBackground(Color.WHITE);
		panel_foto.setBounds(824, 26, 125, 126);
		panel_datos.add(panel_foto);
		
		lblfoto = new JLabel("");
		lblfoto.setToolTipText("Fotografia");
		lblfoto.setForeground(Color.BLUE);
		lblfoto.setBackground(Color.WHITE);
		lblfoto.setBounds(10, 10, 106, 106);
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		btnseleccionar_foto = new JButton("Seleccionar foto");
		btnseleccionar_foto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser seleccionador = new JFileChooser();

					FileNameExtensionFilter formatos = new FileNameExtensionFilter("JPG & JPEG & PNG", "jpg", "jpeg", "png");
					seleccionador.setFileFilter(formatos);

					int seleccion = seleccionador.showOpenDialog(null);
					seleccionador.setDialogTitle("Buscar fotografia del socios...");

					if (seleccion == JFileChooser.APPROVE_OPTION) {
						File ruta = seleccionador.getSelectedFile();
						String ruta_fotografia = String.valueOf(ruta);
						ImageIcon imagen = null;

						try {
							imagen = new ImageIcon(ruta.toURI().toURL());
						} catch (MalformedURLException ex) {
							JOptionPane.showMessageDialog(null, "Error en el formato o al encontrar la imagen.");
							Logger.getLogger(socios_nuevo.class.getName()).log(Level.SEVERE, null, ex);
						}
						lblfoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lblfoto.getWidth(),
								lblfoto.getHeight(), Image.SCALE_SMOOTH)));
						txtruta.setText(ruta_fotografia);
					}
			}
		});
		btnseleccionar_foto.setForeground(Color.BLACK);
		btnseleccionar_foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnseleccionar_foto.setBackground(SystemColor.menu);
		btnseleccionar_foto.setBounds(660, 84, 136, 23);
		panel_datos.add(btnseleccionar_foto);
		
		btneliminar_foto = new JButton("Eliminar foto");
		btneliminar_foto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					txtruta.setText("");
					lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
							lblfoto.getHeight(), Image.SCALE_SMOOTH)));
				
			}
		});
		btneliminar_foto.setForeground(Color.BLACK);
		btneliminar_foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btneliminar_foto.setBackground(SystemColor.menu);
		btneliminar_foto.setBounds(660, 117, 136, 23);
		panel_datos.add(btneliminar_foto);
		
		txtruta = new JTextField();
		txtruta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtruta.setEditable(false);
		txtruta.setColumns(10);
		txtruta.setBackground(SystemColor.menu);
		txtruta.setBounds(824, 162, 125, 20);
		panel_datos.add(txtruta);
		
		JLabel lbldireccion = new JLabel("Dirección");
		lbldireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldireccion.setBounds(329, 236, 200, 25);
		panel_datos.add(lbldireccion);
		
		fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setToolTipText("Seleccione la fecha de nacimiento");
		fecha_nacimiento.setBackground(new Color(255, 255, 255));
		//fecha_nacimiento.setForeground(Color.BLACK);
		fecha_nacimiento.setBounds(328, 194, 254, 33);
		fecha_nacimiento.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_nacimiento);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_nacimiento);
		
			//Rango de años
	        Calendar minDate = Calendar.getInstance();//Establecer el rango de años (1935 - 2004)
	        minDate.set(1920, Calendar.JANUARY, 1); // Año mínimo: 1935
	        Calendar maxDate = Calendar.getInstance();
	        maxDate.set(2007, Calendar.DECEMBER, 31); // Año máximo: 2004
	        fecha_nacimiento.setSelectableDateRange(minDate.getTime(), maxDate.getTime());// Configurar el rango de fechas
		
		JLabel lbltitulo_foto = new JLabel("Fotografía");
		lbltitulo_foto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo_foto.setBounds(630, 26, 119, 25);
		panel_datos.add(lbltitulo_foto);
		
		JLabel lblid = new JLabel("Código del socio");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblid.setBounds(31, 26, 198, 25);
		panel_datos.add(lblid);
		
		txtid_empleado = new JTextField();
		txtid_empleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//validaciones.validarNumerosID(e, txtid_empleado, 3);
			}
		});
		txtid_empleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtid_empleado.setColumns(10);
		txtid_empleado.setBounds(29, 50, 253, 33);
		panel_datos.add(txtid_empleado);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeInicio.setBounds(630, 178, 200, 25);
		panel_datos.add(lblFechaDeInicio);
		
		fecha_inicio = new JDateChooser();
		fecha_inicio.setToolTipText("Seleccione la fecha de inicio laboral");
		fecha_inicio.setBackground(new Color(255, 255, 255));
		//fecha_inicio.setForeground(Color.BLACK);
		fecha_inicio.setBounds(630, 203, 251, 33);
		fecha_inicio.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_inicio);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_inicio);
		
		

			
			
		JLabel lblFechaDeRenuncia = new JLabel("Fecha de baja");
		lblFechaDeRenuncia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeRenuncia.setBounds(630, 246, 166, 25);
		panel_datos.add(lblFechaDeRenuncia);
		
		fecha_renuncia = new JDateChooser();
		fecha_renuncia.setToolTipText("Seleccione la fecha de renuncia");
		fecha_renuncia.setBackground(new Color(255, 255, 255));
		fecha_renuncia.setForeground(new Color(0, 0, 0));
		fecha_renuncia.setBounds(630, 269, 251, 33);
		fecha_renuncia.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_renuncia);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_renuncia);
		
			
		
		JLabel lblcargo = new JLabel("Cargo");
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(329, 377, 200, 25);
		panel_datos.add(lblcargo);
		
		cbxcargo = new JComboBox<String>();
		cbxcargo.setToolTipText("Seleccione");
		cbxcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxcargo.setBackground(Color.WHITE);
		cbxcargo.setBounds(329, 403, 253, 33);
		cbxcargo.setSelectedIndex(-1);
		panel_datos.add(cbxcargo);
		
		txadireccion = new JTextArea();
		txadireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarLongitud(e, txadireccion, 150);
				validaciones.capitalizarPrimeraLetra(txadireccion);
				
			}
		});
		txadireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txadireccion.setBounds(329, 262, 253, 107);
		panel_datos.add(txadireccion);
		txadireccion.setLineWrap(true); // Activa el ajuste de líneas
		txadireccion.setWrapStyleWord(true); // Ajusta el texto en palabras completas
		txadireccion.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // agrega borde
		
		cbxarea = new JComboBox<String>();
		cbxarea.setModel(new DefaultComboBoxModel(new String[] {"Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento"}));
		cbxarea.setToolTipText("Seleccione");
		cbxarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxarea.setBackground(Color.WHITE);
		cbxarea.setBounds(630, 403, 251, 33);
		cbxarea.setSelectedIndex(-1);
		panel_datos.add(cbxarea);
		
		JLabel lblcuenta = new JLabel("Número de cuenta bancaria");
		lblcuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcuenta.setBounds(630, 312, 251, 25);
		panel_datos.add(lblcuenta);
		
		txtcuenta = new JTextField();
		txtcuenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarSoloNumeros(e, txtcuenta, 30);
			}
		});
		
		txtcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcuenta.setColumns(10);
		txtcuenta.setBounds(630, 336, 251, 33);
		txtcuenta.setText("0");
		panel_datos.add(txtcuenta);
		
		JLabel lblestado_civil = new JLabel("Estado civil");
		lblestado_civil.setBounds(329, 26, 200, 25);
		panel_datos.add(lblestado_civil);
		lblestado_civil.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		cbxestado_civil = new JComboBox<String>();
		cbxestado_civil.setBounds(329, 53, 253, 33);
		panel_datos.add(cbxestado_civil);
		cbxestado_civil.setModel(new DefaultComboBoxModel(new String[] {"Soltero(a)", "Casado(a)", "Viudo(a)"}));
		cbxestado_civil.setToolTipText("Seleccione");
		cbxestado_civil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxestado_civil.setSelectedIndex(-1);
		cbxestado_civil.setBackground(Color.WHITE);
		
		txtidOriginal = new JTextField();
		txtidOriginal.setEditable(false);
		txtidOriginal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtidOriginal.setColumns(10);
		txtidOriginal.setBounds(974, 10, 1, 2);
		panel_datos.add(txtidOriginal);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(482, 25, 528, 62);
		getContentPane().add(panel_titulo_1);
		
		btnguardar = new JButton("Guardar");
		btnguardar.setBounds(415, 18, 90, 23);
		panel_titulo_1.add(btnguardar);
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setIcon(null);
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_empleado();
			}
		});
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		
		 btnlimpiar = new JButton("Limpiar");
		 btnlimpiar.setToolTipText("Limpiar los campos");
		 btnlimpiar.setBounds(320, 18, 90, 23);
		 panel_titulo_1.add(btnlimpiar);
		 btnlimpiar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		limpiar();
		 	}
		 });
		 btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		 btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		 
		 btnactualizar = new JButton("Actualizar");
		 btnactualizar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		actualizarEmpleado();
		 	}
		 });
		 btnactualizar.setSelectedIcon(null);
		 btnactualizar.setIcon(null);
		 btnactualizar.setToolTipText("Actualizar registro");
		 btnactualizar.setBounds(415, 17, 90, 23);
		 panel_titulo_1.add(btnactualizar);

		 btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		 btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		 
		 btnregresar = new JButton("Regresar");
		 btnregresar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		socios_tabla tabla= new socios_tabla();
		 		tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
		 		tabla.construirTabla();
				dispose();
		 	}
		 });
		 btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		 btnregresar.setToolTipText("Regresar a la tabla");
		 btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		 btnregresar.setBounds(10, 17, 90, 23);
		 panel_titulo_1.add(btnregresar);
		 
		 chxeditar = new JCheckBox("Editar registro");
		 chxeditar.setBounds(131, 19, 152, 21);
		 panel_titulo_1.add(chxeditar);
		 chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 chxeditar.addActionListener(new ActionListener() {
		     @Override
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
		
		JLabel lbltitulo = new JLabel("NUEVO SOCIO");
		lbltitulo.setBounds(27, 31, 459, 33);
		getContentPane().add(lbltitulo);
		lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lbltitulo.setBackground(new Color(255, 153, 0));
		
		txtid = new JTextField();
		txtid.setBounds(445, 33, 12, -4);
		getContentPane().add(txtid);
		txtid.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBackground(new Color(255, 255, 255));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
	
		// Rango de años para los jdatechooser
		Calendar fechaMin = Calendar.getInstance();
		fechaMin.set(1920, Calendar.JANUARY, 1);
		fecha_inicio.setMinSelectableDate(fechaMin.getTime()); 
		fecha_inicio.setMaxSelectableDate(fechaActual);      

		
		//renuncia
		Calendar fechaMinimo = Calendar.getInstance();
		fechaMinimo.set(1920, Calendar.JANUARY, 1);
		fecha_renuncia.setMinSelectableDate(fechaMinimo.getTime()); 
		fecha_renuncia.setMaxSelectableDate(fechaActual); 
		
		 cargarCargosEnComboBox();
		 cargarAreasEnComboBox();


	}///////////////////FIN CLASS/////////////////////

		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		public boolean validarCamposEmpleadoGuardar() {
		    Date fechaSeleccionada = fecha_nacimiento.getDate();
		    Date fechaSeleccionada2 = fecha_inicio.getDate();

		    if (txtid_empleado.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Código' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txtidentidad.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Número de identidad' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txtnombres.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Nombres' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txtapellidos.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Apellidos' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (!buttonfemenino.isSelected() && !buttonmasculino.isSelected() && !buttonotro.isSelected()) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un sexo para el socios", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (fechaSeleccionada == null) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de nacimiento", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (fechaSeleccionada2 == null) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de ingreso", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txadireccion.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Dirección' no puede estar vacía", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txttel.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Número de teléfono' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txtcorreo.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Correo electrónico' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (txtcuenta.getText().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Número de cuenta bancaria' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxestado_civil.getSelectedItem() == null || cbxestado_civil.getSelectedItem().toString().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un estado civil", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxarea.getSelectedItem() == null || cbxarea.getSelectedItem().toString().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un área", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxcargo.getSelectedItem() == null || cbxcargo.getSelectedItem().toString().trim().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un cargo", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxestado_civil.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un estado civil", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxarea.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un área", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cbxcargo.getSelectedItem() == null) {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un cargo", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }


		    return true;
		}
		
		
		public void guardar_empleado() {
		    if (!validarCamposEmpleadoGuardar()) {
		        return; 
		    }

		    Date fechaSeleccionada = fecha_nacimiento.getDate();
		    Date fechaSeleccionada2 = fecha_inicio.getDate();
		    Date fechaSeleccionada3 = fecha_renuncia.getDate(); 

		    socios clase = new socios();

		    clase.setId_empleado(txtid_empleado.getText());
		    clase.setIdentidad_empleado(txtidentidad.getText());
		    clase.setNombres_empleado(txtnombres.getText());
		    clase.setApellidos_empleado(txtapellidos.getText());

		    if (buttonmasculino.isSelected()) {
		        clase.setSexo_empleado("Masculino");
		    } else if (buttonfemenino.isSelected()) {
		        clase.setSexo_empleado("Femenino");
		    } else if (buttonotro.isSelected()) {
		        clase.setSexo_empleado("Otro");
		    }

		    clase.setNacimiento_empleado(fechaSeleccionada);
		    clase.setCivil_empleado(cbxestado_civil.getSelectedItem().toString());
		    clase.setDireccion_empleado(txadireccion.getText());
		    clase.setTel_empleado(txttel.getText());
		    clase.setCorreo_empleado(txtcorreo.getText());
		    clase.setCargo_empleado(cbxcargo.getSelectedItem().toString());
		    clase.setArea_empleado(cbxarea.getSelectedItem().toString());
		    clase.setInicio_empleado(fechaSeleccionada2);

		    if (fechaSeleccionada3 == null) {
		        clase.setRenuncia_empleado(null);
		    } else {
		        clase.setRenuncia_empleado(fechaSeleccionada3);
		    }

		    clase.setFotografia_empleado(txtruta.getText());
		    clase.setCuenta_empleado(txtcuenta.getText());

		    String campoDuplicado = consulta.empleadoExiste(
		        clase.getId_empleado(), 
		        clase.getIdentidad_empleado());

		    if (campoDuplicado != null) {
		        JOptionPane.showMessageDialog(null, "Error, ya existe un cooperativista con el mismo " + campoDuplicado, "Error de duplicado", JOptionPane.ERROR_MESSAGE);
		    } else {
		        if (consulta.guardar_empleado(clase, fechaSeleccionada, fechaSeleccionada2, fechaSeleccionada3)) {
		            JOptionPane.showMessageDialog(null, "El cooperativista se ha guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            socios_tabla tabla = new socios_tabla();
		            tabla.setLocationRelativeTo(null);
		            tabla.setVisible(true);
		            tabla.construirTabla();
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error, el cooperativista no se ha guardado", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		}
		
		public void limpiar() {
			txtid_empleado.setText("");
			txtidentidad.setText("");
			txtnombres.setText("");
			txtapellidos.setText("");
			buttonfemenino.setSelected(false);
			buttonmasculino.setSelected(false);
			buttonotro.setSelected(false);
			fecha_nacimiento.setDate(null);
			cbxestado_civil.setSelectedIndex(-1);
			txadireccion.setText("");
			txttel.setText("");
			txtcorreo.setText("");
			cbxcargo.setSelectedIndex(-1);
			cbxarea.setSelectedIndex(-1);
			fecha_inicio.setDate(null);
			fecha_renuncia.setDate(null);
			txtruta.setText("");
			txtcuenta.setText("0");
			lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
					lblfoto.getHeight(), Image.SCALE_SMOOTH)));

		}
	
		public void ver_empleado(String idEmpleado, String identidad, String nombres, String apellidos, String sexo, Date fechaNacimiento,
                String estadoCivil, String direccion, String telefono, String correo, String cargo, String area, 
                Date fechaInicio, Date fechaRenuncia, String fotografia, String cuenta) {

			txtid_empleado.setText(idEmpleado);
			txtidentidad.setText(identidad);
			txtnombres.setText(nombres);
			txtapellidos.setText(apellidos);
			txtcuenta.setText(cuenta);
			txtruta.setText(fotografia);  
			
			if (sexo.equalsIgnoreCase("Masculino")) {
				buttonmasculino.setSelected(true);
			} else if (sexo.equalsIgnoreCase("Femenino")) {
				buttonfemenino.setSelected(true);
			} else {
				buttonotro.setSelected(true);
			}
			
			fecha_nacimiento.setDate(fechaNacimiento);
			cbxestado_civil.setSelectedItem(estadoCivil);
			txadireccion.setText(direccion);
			txttel.setText(telefono);
			txtcorreo.setText(correo);
			cbxcargo.setSelectedItem(cargo);
			cbxarea.setSelectedItem(area);
			fecha_inicio.setDate(fechaInicio);
			fecha_renuncia.setDate(fechaRenuncia);
			
			if (fotografia != null && !fotografia.isEmpty()) {
				ImageIcon icon = new ImageIcon(fotografia);
				Image img = icon.getImage().getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_SMOOTH);
				lblfoto.setIcon(new ImageIcon(img));
			} else {
				lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
						lblfoto.getHeight(), Image.SCALE_SMOOTH)));
			}
			
			txtid_empleado.setEditable(false);
			txtidentidad.setEditable(false);
			txtnombres.setEditable(false);
			txtapellidos.setEditable(false);
			buttonmasculino.setEnabled(false);
			buttonfemenino.setEnabled(false);
			buttonotro.setEnabled(false);
			fecha_nacimiento.setEnabled(false);
			cbxestado_civil.setEnabled(false);
			txadireccion.setEditable(false);
			txttel.setEditable(false);
			txtcorreo.setEditable(false);
			cbxcargo.setEnabled(false);
			cbxarea.setEnabled(false);
			fecha_inicio.setEnabled(false);
			fecha_renuncia.setEnabled(false);
			txtcuenta.setEditable(false);
			txtruta.setEditable(false);
			
			btnseleccionar_foto.setVisible(false);
			btneliminar_foto.setVisible(false);
			
			txtid_empleado.setForeground(Color.BLACK);
			txtidentidad.setForeground(Color.BLACK);
			txtnombres.setForeground(Color.BLACK);
			txtapellidos.setForeground(Color.BLACK);
			buttonmasculino.setForeground(Color.BLACK);
			buttonfemenino.setForeground(Color.BLACK);
			buttonotro.setForeground(Color.BLACK);
			fecha_nacimiento.setForeground(Color.BLACK);
			cbxestado_civil.setForeground(Color.BLACK);
			txadireccion.setForeground(Color.BLACK);
			txttel.setForeground(Color.BLACK);
			txtcorreo.setForeground(Color.BLACK);
			cbxcargo.setForeground(Color.BLACK);
			cbxarea.setForeground(Color.BLACK);
			fecha_inicio.setForeground(Color.BLACK);
			fecha_renuncia.setForeground(Color.BLACK);
			txtcuenta.setForeground(Color.BLACK);
			txtruta.setForeground(Color.BLACK); 
			lblfoto.setForeground(Color.BLACK);
			
			setVisible(true);
		}
		
		
		private void habilitarCampos(boolean habilitar) {
		    txtid_empleado.setEditable(habilitar);
		    txtidentidad.setEditable(habilitar);
		    txtnombres.setEditable(habilitar);
		    txtapellidos.setEditable(habilitar);
		    txttel.setEditable(habilitar);
		    txtcorreo.setEditable(habilitar);
		    txtcuenta.setEditable(habilitar);
		    txadireccion.setEditable(habilitar); 
		    txadireccion.setEnabled(habilitar);  
		    cbxestado_civil.setEnabled(habilitar);
		    buttonmasculino.setEnabled(habilitar);
		    buttonfemenino.setEnabled(habilitar);
		    buttonotro.setEnabled(habilitar);
		    fecha_nacimiento.setEnabled(habilitar);
		    fecha_inicio.setEnabled(habilitar);
		    fecha_renuncia.setEnabled(habilitar);
		    cbxcargo.setEnabled(habilitar);
		    cbxarea.setEnabled(habilitar);
		    btnseleccionar_foto.setVisible(habilitar);  
		    btneliminar_foto.setVisible(habilitar);    

		    Color colorTexto = habilitar ? Color.BLACK : Color.GRAY;  
		    txtid_empleado.setForeground(colorTexto);
		    txtidentidad.setForeground(colorTexto);
		    txtnombres.setForeground(colorTexto);
		    txtapellidos.setForeground(colorTexto);
		    txttel.setForeground(colorTexto);
		    txtcorreo.setForeground(colorTexto);
		    txtcuenta.setForeground(colorTexto);
		    txadireccion.setForeground(colorTexto);  
		}

		
		@SuppressWarnings("unused")
		public boolean validarCamposEmpleado() {
		    String idEmpleadoStr = txtid_empleado.getText().trim();
		    String cuentaEmpleado = txtcuenta.getText().trim();
		    String nombresEmpleado = txtnombres.getText().trim();
		    String apellidosEmpleado = txtapellidos.getText().trim();
		    String telefonoEmpleado = txttel.getText().trim();
		    String identidadEmpleado = txtidentidad.getText().trim();
		    String correoEmpleado = txtcorreo.getText().trim();
		    String direccionEmpleado = txadireccion.getText().trim();
		    String sexoEmpleado = "";

		    if (identidadEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Identidad' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (nombresEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Nombres' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (apellidosEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Apellidos' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;            
		    }
		    if (direccionEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Dirección' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (correoEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Correo electrónico' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (idEmpleadoStr.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Código' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (cuentaEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Número de cuenta bancaria' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }
		    if (telefonoEmpleado.isEmpty()) {
		        JOptionPane.showMessageDialog(null, "El campo 'Número de teléfono' no puede estar vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }

		    if (buttonmasculino.isSelected()) {
		        sexoEmpleado = "Masculino";
		    } else if (buttonfemenino.isSelected()) {
		        sexoEmpleado = "Femenino";
		    } else if (buttonotro.isSelected()) {
		        sexoEmpleado = "Otro";
		    } else {
		        JOptionPane.showMessageDialog(null, "Debe seleccionar un sexo para el cooperativista", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return false;
		    }

		    return true;
		}
		
		public void actualizarEmpleado() {
		    try {
		        if (!validarCamposEmpleado()) {
		            return; 
		        }
		    
		        String idOriginalStr = txtidOriginal.getText().trim();
		        //String idEmpleadoStr = txtid_empleado.getText().trim();
		        //int idOriginal = Integer.parseInt(idOriginalStr); 
		        //int idEmpleado = Integer.parseInt(idEmpleadoStr);
		        String idOriginal = idOriginalStr; 
		        String idEmpleado = txtid_empleado.getText().trim();
		        
		        String cuentaEmpleado = txtcuenta.getText().trim();
		        String nombresEmpleado = txtnombres.getText().trim();
		        String apellidosEmpleado = txtapellidos.getText().trim();
		        String telefonoEmpleado = txttel.getText().trim();
		        String identidadEmpleado = txtidentidad.getText().trim();
		        String correoEmpleado = txtcorreo.getText().trim();
		        String direccionEmpleado = txadireccion.getText().trim();
		        String sexoEmpleado = "";

		        if (buttonmasculino.isSelected()) {
		            sexoEmpleado = "Masculino";
		        } else if (buttonfemenino.isSelected()) {
		            sexoEmpleado = "Femenino";
		        } else if (buttonotro.isSelected()) {
		            sexoEmpleado = "Otro";
		        }

		        socios empleadoActualizado = new socios();
		        empleadoActualizado.setId_empleado(idEmpleado);
		        empleadoActualizado.setIdentidad_empleado(identidadEmpleado);
		        empleadoActualizado.setNombres_empleado(nombresEmpleado);
		        empleadoActualizado.setApellidos_empleado(apellidosEmpleado);
		        empleadoActualizado.setSexo_empleado(sexoEmpleado);
		        empleadoActualizado.setNacimiento_empleado(fecha_nacimiento.getDate());
		        //empleadoActualizado.setCivil_empleado(cbxestado_civil.getSelectedItem().toString());
		        Object estadoCivilSeleccionado = cbxestado_civil.getSelectedItem();
		        empleadoActualizado.setCivil_empleado(estadoCivilSeleccionado != null ? estadoCivilSeleccionado.toString() : "");

		        
		        
		        empleadoActualizado.setDireccion_empleado(direccionEmpleado);
		        empleadoActualizado.setTel_empleado(telefonoEmpleado);
		        empleadoActualizado.setCorreo_empleado(correoEmpleado);
		        //empleadoActualizado.setCargo_empleado(cbxcargo.getSelectedItem().toString());
		        Object cargoSeleccionado = cbxcargo.getSelectedItem();
		        empleadoActualizado.setCargo_empleado(cargoSeleccionado != null ? cargoSeleccionado.toString() : "");

		        //empleadoActualizado.setArea_empleado(cbxarea.getSelectedItem().toString());
		        Object areaSeleccionada = cbxarea.getSelectedItem();
		        empleadoActualizado.setArea_empleado(areaSeleccionada != null ? areaSeleccionada.toString() : "");

		        
		        
		        empleadoActualizado.setInicio_empleado(fecha_inicio.getDate());
		        empleadoActualizado.setRenuncia_empleado(fecha_renuncia.getDate());
		        empleadoActualizado.setFotografia_empleado(txtruta.getText().trim());
		        empleadoActualizado.setCuenta_empleado(cuentaEmpleado);

		        boolean idModificado = idEmpleado != idOriginal;
		        boolean identidadModificada = !identidadEmpleado.equals(consulta.obtenerIdentidadEmpleado(idOriginal));

		        if ((idModificado && consulta.existeIdEmpleado(idEmpleado, idOriginal)) ||
		            (identidadModificada && consulta.existeIdentidadEmpleado(identidadEmpleado, idOriginal))) {
		            JOptionPane.showMessageDialog(null, "El código o la identidad ya están en uso por otro cooperativista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            return;
		        }

		        // Lógica para actualizar el socios en la base de datos
		        if (consulta.actualizar_empleado(empleadoActualizado, idOriginal, empleadoActualizado.getId_empleado(),
		                                         empleadoActualizado.getNacimiento_empleado(), empleadoActualizado.getInicio_empleado(),
		                                         empleadoActualizado.getRenuncia_empleado())) {
		            JOptionPane.showMessageDialog(null, "El cooperativista se ha actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            socios_tabla tabla = new socios_tabla();
		            tabla.setVisible(true);
		            tabla.setLocationRelativeTo(null);
		            tabla.construirTabla();
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el cooperativista", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(null, "Id de cooperativista no válido o vacío", "Error", JOptionPane.ERROR_MESSAGE);
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
		        ex.printStackTrace();
		    }
		}
		
		
		@SuppressWarnings("unchecked")
		private void cargarCargosEnComboBox() {
		    consultas_cargos consultas = new consultas_cargos();
		    List<String> cargos = consultas.obtenerCargos();
		    cbxcargo.removeAllItems();
		    cbxcargo.addItem(" ");
		    
		    for (String cargo : cargos) {
		        cbxcargo.addItem(cargo);
		    }
		    
		    cbxcargo.setSelectedIndex(0);
		}

		

		@SuppressWarnings("unchecked")
		private void cargarAreasEnComboBox() {
		    consultas_areas c = new consultas_areas();
		    List<String> areas = c.obtenerAreas();
		    cbxarea.removeAllItems();
		    cbxarea.addItem(" ");
		    
		    for (String area : areas) {
		        cbxarea.addItem(area);
		    }
		    
		    cbxarea.setSelectedIndex(0);
		}


}//end

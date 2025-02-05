package principal;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import clases.apotaciones;
import clases.prestamos;
import clases.socios;
import consultas.consultas_aportaciones;
import consultas.consultas_reportes;
import consultas.consultas_socios;
import reportes.reporte_aportaciones_mensual;
import reportes.reporte_financiero_general;
import reportes.reporte_financiero_socio;
import reportes.reporte_general_anual_ext;
import reportes.reporte_general_mensual;
import reportes.reporte_prestamos_mensual;
import ventanas.aportaciones_nuevo;
import ventanas.aportaciones_tabla;
import ventanas.areas_nuevo;
import ventanas.areas_tabla;
import ventanas.cargos_nuevo;
import ventanas.cargos_tabla;
import ventanas.prestamos_nuevo;
import ventanas.prestamos_tabla;
import ventanas.socios_nuevo;
import ventanas.socios_tabla;



@SuppressWarnings("serial")
public class menu_principal extends JFrame{
	
	ImageIcon icono_fotografia = new ImageIcon(getClass().getResource("/imagenes/cooperativa.png"));


	public JLabel lblfoto;
	public JLabel relojFechaLabel;
	private JLabel lblBienvenido;
	public JMenu mnCooperativistas;
	public JMenu menu_permisos;
	public JMenu menu_incapacidades;
	public JMenu menu_vacaciones;
	public JMenu menu_cargos;
	public JMenu menu_roles;
	public JMenu menu_respaldos;
	public JMenu menu_usuarios;
	public JMenu menu_areas;
	public JMenu mnInformes;
	public JMenu menu_reportes;
	public JMenu menu_salir;
	
	
	public menu_principal() {
		setType(Type.UTILITY);
		setResizable(false);
		getContentPane().setBackground(SystemColor.text);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1050, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.light"));
		menuBar.setBounds(0, 0, 1036, 46);
		getContentPane().add(menuBar);
		
		menu_cargos = new JMenu("Cargos");
		menu_cargos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_cargos);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Tabla de registros de cargos");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_tabla t = new cargos_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_1.setBackground(Color.WHITE);
		menu_cargos.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("Agregar un nuevo cargo");
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_nuevo c = new cargos_nuevo();
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				c.btnactualizar.setVisible(false);
				c.chxeditar.setVisible(false);
				c.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_3_1.setBackground(Color.WHITE);
		menu_cargos.add(mntmNewMenuItem_3_1);
		
		menu_areas = new JMenu("Áreas");
		menu_areas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_areas);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("Tabla de registros de áreas");
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areas_tabla t = new areas_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_1_1.setBackground(Color.WHITE);
		menu_areas.add(mntmNewMenuItem_2_1_1);
		
		JMenuItem mntmNewMenuItem_3_1_1 = new JMenuItem("Agregar una nueva área");
		mntmNewMenuItem_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areas_nuevo n = new areas_nuevo();
				n.setVisible(true);
				n.setLocationRelativeTo(null);
				n.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_3_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_3_1_1.setBackground(Color.WHITE);
		menu_areas.add(mntmNewMenuItem_3_1_1);
		
		mnCooperativistas = new JMenu("Socios");
		mnCooperativistas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnCooperativistas);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tabla de registros de socios");
		mntmNewMenuItem.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socios_tabla tabla = new socios_tabla();
		 		tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				
				dispose();
			}
		});
		mnCooperativistas.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Agregar un nuevo socio\r\n");
		mntmNewMenuItem_1.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				socios_nuevo nuevo = new socios_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.txtidOriginal.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				dispose();
				
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnCooperativistas.add(mntmNewMenuItem_1);
		
		JMenu mnAportaciones = new JMenu("Aportaciones");
		mnAportaciones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnAportaciones);
		
		JMenuItem mntmTablaDeRegistros = new JMenuItem("Tabla de registros de aportaciones");
		mntmTablaDeRegistros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aportaciones_tabla tabla = new aportaciones_tabla();
			    tabla.setVisible(true);
			    tabla.setLocationRelativeTo(null);
			    dispose(); // Cierra la ventana actual si es necesario
			}
		});
		mntmTablaDeRegistros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTablaDeRegistros.setBackground(Color.WHITE);
		mnAportaciones.add(mntmTablaDeRegistros);
		
		JMenuItem mntmNewMenuItem_1_1 = new JMenuItem("Agregar una nueva aportación\r\n");
		mntmNewMenuItem_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aportaciones_nuevo an = new aportaciones_nuevo();
				an.setVisible(true);
				an.setLocationRelativeTo(null);
				an.chxeditar.setVisible(false);
				an.btnactualizar.setVisible(false);
				an.btnImprimir.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1.setBackground(Color.WHITE);
		mnAportaciones.add(mntmNewMenuItem_1_1);
		
		JMenu mnPrstamos = new JMenu("Préstamos");
		mnPrstamos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnPrstamos);
		
		JMenuItem mntmTablaDeRegistros_2 = new JMenuItem("Tabla de registros de préstamos");
		mntmTablaDeRegistros_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prestamos_tabla t = new prestamos_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmTablaDeRegistros_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTablaDeRegistros_2.setBackground(Color.WHITE);
		mnPrstamos.add(mntmTablaDeRegistros_2);
		
		JMenuItem mntmNewMenuItem_1_1_1 = new JMenuItem("Agregar un nuevo préstamo\r\n");
		mntmNewMenuItem_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prestamos_nuevo p = new prestamos_nuevo();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				p.chxeditar.setVisible(false);
				p.btnactualizar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1.setBackground(Color.WHITE);
		mnPrstamos.add(mntmNewMenuItem_1_1_1);
		
		JMenu mnConsultas = new JMenu("Consultas");
		mnConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnConsultas);
		
		JMenuItem mntmTablaDeRegistros_2_1_1 = new JMenuItem("Estado financiero del socio");
		mntmTablaDeRegistros_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTablaDeRegistros_2_1_1.setBackground(Color.WHITE);
		mnConsultas.add(mntmTablaDeRegistros_2_1_1);
		
		JMenu mnReportes = new JMenu("Reportes de socios");
		mnReportes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnReportes);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_4 = new JMenuItem("Reporte estado financiero de un socio");
		mntmNewMenuItem_1_1_1_1_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener lista de socios desde consultas_socios
		        consultas_socios consultasEmpleados = new consultas_socios();
		        List<socios> empleados = consultasEmpleados.obtenerNombresEmpleados();

		        if (empleados.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "No hay socios registrados", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Crear JComboBox con los nombres de socios
		        JComboBox<String> comboEmpleados = new JComboBox<>();
		        for (socios emp : empleados) {
		            comboEmpleados.addItem(emp.getNombres_empleado() + " " + emp.getApellidos_empleado());
		        }

		        // Crear panel para el JOptionPane
		        JPanel panel = new JPanel();
		        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		        panel.add(new JLabel("Seleccione un empleado:     "));
		        panel.add(comboEmpleados);

		        int resultado = JOptionPane.showConfirmDialog(null, panel, "Reporte financiero de un socio", 
		                                                      JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (resultado == JOptionPane.OK_OPTION) {
		            String nombreSeleccionado = (String) comboEmpleados.getSelectedItem();
		            socios empleadoSeleccionado = empleados.stream()
		                    .filter(emp -> (emp.getNombres_empleado() + " " + emp.getApellidos_empleado()).equals(nombreSeleccionado))
		                    .findFirst()
		                    .orElse(null);

		            if (empleadoSeleccionado == null) {
		                JOptionPane.showMessageDialog(null, "No se pudo encontrar el empleado seleccionado.", 
		                                              "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Crear nombre del archivo
		            String fechaActual = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
		            String nombreArchivo = "Reporte_financiero_" + empleadoSeleccionado.getNombres_empleado() + "_" 
		                    + empleadoSeleccionado.getApellidos_empleado() + "_" + fechaActual + ".pdf";

		            // Selección de ruta para guardar el archivo
		            JFileChooser fileChooser = new JFileChooser();
		            fileChooser.setDialogTitle("Guardar Reporte");
		            fileChooser.setSelectedFile(new java.io.File(nombreArchivo));

		            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

		                // Validar si el archivo ya existe
		                java.io.File archivo = new java.io.File(rutaArchivo);
		                if (archivo.exists()) {
		                    int respuesta = JOptionPane.showConfirmDialog(null, 
		                            "El archivo ya existe. ¿Desea sobrescribirlo?", "Confirmar Sobrescritura", 
		                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		                    if (respuesta == JOptionPane.NO_OPTION) {
		                        JOptionPane.showMessageDialog(null, "El archivo no se guardará.", 
		                                                      "Información", JOptionPane.INFORMATION_MESSAGE);
		                        return;
		                    }
		                }

		                // Llamar al método para generar el reporte
		                reporte_financiero_socio reporte = new reporte_financiero_socio();
		                reporte.generarReportePDF(empleadoSeleccionado.getId_empleado(), rutaArchivo);

		                // Abrir el archivo en Microsoft Edge
		                try {
		                    Runtime.getRuntime().exec("cmd /c start msedge \"" + rutaArchivo + "\"");
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo en Microsoft Edge.", 
		                                                  "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
		    }
		});





		mntmNewMenuItem_1_1_1_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_4.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_1_1_1_1_4);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_4_1 = new JMenuItem("Reporte estado financiero de socios general");
		mntmNewMenuItem_1_1_1_1_4_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Crear instancia de la clase del reporte
		            reporte_financiero_general reporte = new reporte_financiero_general();

		            // Usar JFileChooser para seleccionar la ubicación del archivo
		            JFileChooser fileChooser = new JFileChooser();
		            fileChooser.setDialogTitle("Guardar reporte financiero general");
		            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		            // Proponer un nombre de archivo por defecto
		            String fechaActual = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
		            fileChooser.setSelectedFile(new java.io.File("Reporte_financiero_general_" + fechaActual + ".pdf"));

		            int userSelection = fileChooser.showSaveDialog(null);

		            if (userSelection == JFileChooser.APPROVE_OPTION) {
		                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

		                // Asegurar que el archivo tenga extensión .pdf
		                if (!rutaArchivo.endsWith(".pdf")) {
		                    rutaArchivo += ".pdf";
		                }

		                // Validar si el archivo ya existe
		                java.io.File archivo = new java.io.File(rutaArchivo);
		                if (archivo.exists()) {
		                    int respuesta = JOptionPane.showConfirmDialog(null, 
		                        "El archivo ya existe. ¿Desea sobrescribirlo?", 
		                        "Confirmar Sobrescritura", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		                    if (respuesta == JOptionPane.NO_OPTION) {
		                        JOptionPane.showMessageDialog(null, "El archivo no será guardado.", 
		                            "Información", JOptionPane.INFORMATION_MESSAGE);
		                        return;
		                    }
		                }

		                // Llamar al método para generar el reporte
		                reporte.generarReporteFinancieroGeneral(rutaArchivo);


		                // Mostrar mensaje de éxito
		                JOptionPane.showMessageDialog(null, "Reporte generado exitosamente en:\n" + rutaArchivo, 
		                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

		                // Intentar abrir el archivo con el visor predeterminado
		                try {
		                    if (Desktop.isDesktopSupported()) {
		                        Desktop.getDesktop().open(new java.io.File(rutaArchivo));
		                    } else {
		                        throw new UnsupportedOperationException("Abrir archivos no soportado en esta máquina.");
		                    }
		                } catch (Exception ex) {
		                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo automáticamente.", 
		                        "Advertencia", JOptionPane.WARNING_MESSAGE);
		                }
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el reporte: " + ex.getMessage(), 
		                "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		mntmNewMenuItem_1_1_1_1_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_4_1.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_1_1_1_1_4_1);
		
		JMenuItem mntmTablaDeRegistros_2_1 = new JMenuItem("Reporte de socios general");
		mntmTablaDeRegistros_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTablaDeRegistros_2_1.setBackground(Color.WHITE);
		mnReportes.add(mntmTablaDeRegistros_2_1);
		
		JMenuItem mntmNewMenuItem_1_1_1_1 = new JMenuItem("Reporte de socios activos");
		mntmNewMenuItem_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_1_1_1_1);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_3 = new JMenuItem("Reporte de socios inactivos");
		mntmNewMenuItem_1_1_1_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_3.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_1_1_1_1_3);
		
		JMenu mnReportesVarios = new JMenu("Reportes varios");
		mnReportesVarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnReportesVarios);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_2_2 = new JMenuItem("Reporte mensual de préstamos ");
		mntmNewMenuItem_1_1_1_1_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    // Opciones para los meses
			    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
			                      "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
			    int añoActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

			    // Crear JComboBox para meses y JTextField para el año
			    JComboBox<String> comboMes = new JComboBox<>(meses);
			    JTextField txtAnio = new JTextField(String.valueOf(añoActual)); // Año actual por defecto

			    // Crear panel para JOptionPane
			    JPanel panel = new JPanel();
			    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			    panel.add(new JLabel("Seleccione el mes:"));
			    panel.add(comboMes);
			    panel.add(Box.createVerticalStrut(10)); // Espacio entre componentes
			    panel.add(new JLabel("Ingrese el año:"));
			    panel.add(txtAnio);

			    // Mostrar el JOptionPane
			    int resultado = JOptionPane.showConfirmDialog(null, panel, "Seleccionar Mes y Año", 
			                                                  JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			    if (resultado == JOptionPane.OK_OPTION) {
			        try {
			            // Obtener valores seleccionados
			            int mesSeleccionado = comboMes.getSelectedIndex() + 1; // Mes en formato 1-12
			            int añoSeleccionado = Integer.parseInt(txtAnio.getText().trim());

			            // Validar año ingresado
			            if (añoSeleccionado < 2025) {
			                JOptionPane.showMessageDialog(null, "El año debe ser 2025 o posterior.", "Error", JOptionPane.ERROR_MESSAGE);
			                return;
			            }

			            // Realizar consulta de préstamos
			            consultas_reportes consultas = new consultas_reportes();
			            List<prestamos> prestamos = consultas.obtenerPrestamosMensuales(mesSeleccionado, añoSeleccionado);

			            // Validar si hay datos
			            if (prestamos.isEmpty()) {
			                JOptionPane.showMessageDialog(null, "No hay datos para el mes y año seleccionados.", 
			                                              "Información", JOptionPane.INFORMATION_MESSAGE);
			                return;
			            }

			            // Generar el reporte
			            reporte_prestamos_mensual reporte = new reporte_prestamos_mensual();
			            reporte.mostrarDialogoYGenerarReporte(prestamos);

			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(null, "Por favor ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    }
			}

		});

		mntmNewMenuItem_1_1_1_1_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_2_2.setBackground(Color.WHITE);
		mnReportesVarios.add(mntmNewMenuItem_1_1_1_1_2_2);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_2_1 = new JMenuItem("Reporte mensual de aportaciones ");
		mntmNewMenuItem_1_1_1_1_2_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Opciones de meses
		        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
		                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

		        // Crear JComboBox para seleccionar el mes
		        JComboBox<String> comboMes = new JComboBox<>(meses);

		        // Recuperar los años disponibles desde aportaciones_nuevo
		        String[] aniosDisponibles = obtenerAniosDesdeAportacionesNuevo(); // Método simulado
		        JComboBox<String> comboAnio = new JComboBox<>(aniosDisponibles);

		        // Crear panel para el JOptionPane
		        JPanel panel = new JPanel();
		        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		        panel.add(new JLabel("Seleccione el mes:"));
		        panel.add(comboMes);
		        panel.add(Box.createVerticalStrut(20)); // Espacio entre componentes
		        panel.add(new JLabel("Seleccione el año:"));
		        panel.add(comboAnio);

		        int resultado = JOptionPane.showConfirmDialog(null, panel, "Reporte mensual de aportaciones", 
		                                                      JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (resultado == JOptionPane.OK_OPTION) {
		            String mes = (String) comboMes.getSelectedItem();
		            String anio = (String) comboAnio.getSelectedItem();

		            // Llamar al método para obtener datos
		            consultas_aportaciones consultas = new consultas_aportaciones();
		            List<apotaciones> empleados = consultas.obtenerDatosAportaciones(mes, anio);

		            if (empleados.isEmpty()) {
		                JOptionPane.showMessageDialog(null, "No hay datos disponibles para el mes seleccionado", 
		                                              "Advertencia", JOptionPane.INFORMATION_MESSAGE);
		                return;
		            }

		            // Selección de ruta para guardar el archivo
		            JFileChooser fileChooser = new JFileChooser();
		            fileChooser.setDialogTitle("Guardar reporte mensual de aportaciones");

		            // Establecer nombre por defecto
		            fileChooser.setSelectedFile(new java.io.File("Reporte_aportaciones_" + mes + "_" + anio + ".pdf"));

		            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

		                // Llamar al método para generar el reporte
		                reporte_aportaciones_mensual reporte = new reporte_aportaciones_mensual();
		                reporte.generarReportePDF(mes, anio, empleados, rutaArchivo);

		                // Abrir el archivo en Microsoft Edge
		                try {
		                    Runtime.getRuntime().exec("cmd /c start msedge \"" + rutaArchivo + "\"");
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo en Microsoft Edge", 
		                                                  "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        }
		    }

		    // Método simulado para obtener años desde aportaciones_nuevo
		    private String[] obtenerAniosDesdeAportacionesNuevo() {
		        // Simula los años disponibles (reemplaza esto con tu implementación real)
		        return new String[] {"2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035"};
		    }
		});

		mntmNewMenuItem_1_1_1_1_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_2_1.setBackground(Color.WHITE);
		mnReportesVarios.add(mntmNewMenuItem_1_1_1_1_2_1);
		
		JMenuItem mntmNewMenuItem_1_1_1_1_2 = new JMenuItem("Reporte general mensual");
		mntmNewMenuItem_1_1_1_1_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Opciones de meses
		        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		        int anioActual = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

		        // Crear JComboBox para el mes y JTextField para el año
		        JComboBox<String> comboMes = new JComboBox<>(meses);
		        JTextField txtAnio = new JTextField(String.valueOf(anioActual));

		        // Crear panel para el JOptionPane
		        JPanel panel = new JPanel();
		        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		        panel.add(new JLabel("Seleccione el mes:"));
		        panel.add(comboMes);
		        panel.add(Box.createVerticalStrut(10));
		        panel.add(new JLabel("Ingrese el año:"));
		        panel.add(txtAnio);

		        // Mostrar el JOptionPane
		        int resultadoDialogo = JOptionPane.showConfirmDialog(null, panel, "Generar Reporte Mensual", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		        if (resultadoDialogo != JOptionPane.OK_OPTION) {
		            return; // Cancelar si el usuario no presiona OK
		        }

		        // Obtener los valores seleccionados
		        int mesSeleccionado = comboMes.getSelectedIndex() + 1;
		        String anioSeleccionado = txtAnio.getText().trim();

		        if (anioSeleccionado.isEmpty() || !anioSeleccionado.matches("\\d{4}")) {
		            JOptionPane.showMessageDialog(null, "Ingrese un año válido.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Configurar JFileChooser
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.setDialogTitle("Guardar Reporte General Mensual");
		        fileChooser.setSelectedFile(new File("Reporte_general_mensual_" + meses[mesSeleccionado - 1] + "_" + anioSeleccionado + ".pdf"));

		        // Mostrar diálogo para seleccionar ubicación del archivo
		        int resultado = fileChooser.showSaveDialog(null);
		        if (resultado == JFileChooser.APPROVE_OPTION) {
		            File archivo = fileChooser.getSelectedFile();

		            // Validar si el archivo ya existe
		            if (archivo.exists()) {
		                int opcionSobrescribir = JOptionPane.showConfirmDialog(null,
		                        "El archivo ya existe. ¿Desea sobrescribirlo?", "Confirmación",
		                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		                if (opcionSobrescribir != JOptionPane.YES_OPTION) {
		                    return; // Cancelar si no desea sobrescribir
		                }
		            }

		            // Llamar al método para generar el reporte
		            reporte_general_mensual reporte = new reporte_general_mensual();
		            reporte.generarReporteMensualPDF(mesSeleccionado, Integer.parseInt(anioSeleccionado), archivo.getAbsolutePath());

		            // Abrir el archivo en Microsoft Edge
		            try {
		                Runtime.getRuntime().exec("cmd /c start msedge \"" + archivo.getAbsolutePath() + "\"");
		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo en Microsoft Edge.", "Error", JOptionPane.ERROR_MESSAGE);
		                ex.printStackTrace();
		            }
		        }
		    }
		});


		mntmNewMenuItem_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_1_1_1_1_2.setBackground(Color.WHITE);
		mnReportesVarios.add(mntmNewMenuItem_1_1_1_1_2);
		
		JMenuItem mntmTablaDeRegistros_2_1_2 = new JMenuItem("Reporte general anual");
		mntmTablaDeRegistros_2_1_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String anio = JOptionPane.showInputDialog(null, "Ingrese el año:", "Generar Reporte Anual", JOptionPane.QUESTION_MESSAGE);
		        
		        if (anio != null && anio.matches("\\d{4}")) {
		            new reporte_general_anual_ext().generarReporteAnualPDF(
		                Integer.parseInt(anio), 
		                "reporte_general_" + anio + ".pdf"
		            );
		        }
		    }
		});

		mntmTablaDeRegistros_2_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTablaDeRegistros_2_1_2.setBackground(Color.WHITE);
		mnReportesVarios.add(mntmTablaDeRegistros_2_1_2);
		
		
		
		menu_salir = new JMenu("Salir");
		menu_salir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_salir);
		
		JMenuItem mntmSalirDelSistema = new JMenuItem("Salir del sistema");
		mntmSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar_ventana();
			}
		});
		mntmSalirDelSistema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmSalirDelSistema.setBackground(Color.WHITE);
		menu_salir.add(mntmSalirDelSistema);
		

		
		
		
		JLabel lblNewLabel = new JLabel("Sistema de la Cooperativa");
		lblNewLabel.setBounds(0, 193, 504, 58);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 30));
		
		JPanel panel_foto = new JPanel();
		panel_foto.setBackground(new Color(240, 248, 255));
		panel_foto.setBounds(511, 101, 487, 455);
		getContentPane().add(panel_foto);
		panel_foto.setLayout(null);
		
		lblfoto = new JLabel("");
		lblfoto.setBackground(new Color(255, 255, 255));
		lblfoto.setBounds(34, 24, 422, 407);
		panel_foto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		JLabel lblPermisosEIncapacidades = new JLabel("de Ahorros y Préstamos");
		lblPermisosEIncapacidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermisosEIncapacidades.setFont(new Font("Georgia", Font.BOLD, 30));
		lblPermisosEIncapacidades.setBounds(0, 236, 504, 58);
		getContentPane().add(lblPermisosEIncapacidades);
		
		JLabel lblTcwsTcwhs = new JLabel("TCWS / TCWHS");
		lblTcwsTcwhs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcwsTcwhs.setFont(new Font("Georgia", Font.BOLD, 30));
		lblTcwsTcwhs.setBounds(0, 337, 504, 58);
		getContentPane().add(lblTcwsTcwhs);
		
		JLabel lblCopyright = new JLabel("Copyright © 2025 Sistema de la Cooperativa de Ahorros y Préstamos COAHPMNUN");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCopyright.setBounds(10, 491, 487, 35);
		getContentPane().add(lblCopyright);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados.\r\n");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(0, 509, 497, 35);
		getContentPane().add(lblTodosLosDerechos);
		
		relojFechaLabel = new JLabel("");
		relojFechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		relojFechaLabel.setBounds(804, 56, 204, 35);
		getContentPane().add(relojFechaLabel);
		
		JLabel lblYVacaciones = new JLabel("COAHPMUN");
		lblYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblYVacaciones.setFont(new Font("Georgia", Font.BOLD, 30));
		lblYVacaciones.setBounds(0, 281, 504, 58);
		getContentPane().add(lblYVacaciones);
		
		JLabel lblVersin = new JLabel("Versión 1.1");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblVersin.setBounds(20, 536, 477, 24);
		getContentPane().add(lblVersin);
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBienvenido.setBounds(516, 56, 278, 35);
		getContentPane().add(lblBienvenido);
		
		iniciarRelojYFecha();
		
		
		
		
		
		
    }//class
	
	
	
	/*public void setNombreUsuario(String nombreUsuario) {
        if (lblBienvenido != null) { // Validar que el JLabel esté inicializado
            lblBienvenido.setText("Bienvenido, " + nombreUsuario);
        } else {
            System.err.println("lblBienvenido no está inicializado");
        }
    }*/

  
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	

	
	private void iniciarRelojYFecha() {
		
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaActual = formatoFecha.format(new Date());
                String horaActual = formatoHora.format(new Date());
                //relojFechaLabel.setText("Hoy es: " + fechaActual + " | Hora: " + horaActual);
                relojFechaLabel.setText(fechaActual + "       |       " + horaActual);
            }
        });
        timer.start();
    }
	
	
	
	/*public void cargarPermisos(String nombreUsuario) {
	    consultas_roles consultas = new consultas_roles();
	    
	    // Consulta cada permiso para este usuario
	    boolean socios = consultas.tienePermiso(nombreUsuario, "permisos_empleados");
	    boolean ausenciaLaboral = consultas.tienePermiso(nombreUsuario, "permisos_ausencia_laboral");
	    boolean incapacidades = consultas.tienePermiso(nombreUsuario, "permisos_incapacidades");
	    boolean vacaciones = consultas.tienePermiso(nombreUsuario, "permisos_vacaciones");
	    boolean cargos = consultas.tienePermiso(nombreUsuario, "permisos_cargos");
	    boolean areas = consultas.tienePermiso(nombreUsuario, "permisos_areas");
	    boolean reportes = consultas.tienePermiso(nombreUsuario, "permisos_reportes");
	    boolean respaldos = consultas.tienePermiso(nombreUsuario, "permisos_respaldos");
	    boolean usuarios = consultas.tienePermiso(nombreUsuario, "permisos_usuarios");

	    // Deshabilitar menús según permisos
	    menu_empleados.setEnabled(socios);
	    menu_permisos.setEnabled(ausenciaLaboral);
	    menu_incapacidades.setEnabled(incapacidades);
	    menu_vacaciones.setEnabled(vacaciones);
	    menu_cargos.setEnabled(cargos);
	    menu_areas.setEnabled(areas);
	    menu_reportes.setEnabled(reportes);
	    menu_respaldos.setEnabled(respaldos);
	    menu_usuarios.setEnabled(usuarios);
	}*/
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					menu_principal frame = new menu_principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

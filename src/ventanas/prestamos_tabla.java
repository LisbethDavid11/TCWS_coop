package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.prestamos;
import conexion.conexion;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_prestamos;
import principal.menu_principal;
import reportes.reporte_constancia_prestamo;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class prestamos_tabla extends JFrame {
	private final String placeHolderText = "No., nombres, apellidos, código"; 
	String filtroCodigo;
	public JTable tablePrestamos;
    public DefaultTableModel modeloTabla;
    public TableRowSorter<TableModel> rowSorter;
    public JScrollPane scrollPane;
	public JTextField txtbusqueda;
	public JLabel lblresultado_busqueda;
	public JComboBox<String> cbxbusquedaArea;
	public JComboBox<String> cbxbusquedaCargo;
	public JButton btneliminar;
	public JButton btnImprimir;
	
	
	@SuppressWarnings("deprecation")
	public prestamos_tabla() {
		getContentPane().setBackground(new Color(255, 255, 255));
		
		setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblPrstamosRegistrados = new JLabel("PRÉSTAMOS REGISTRADOS");
        lblPrstamosRegistrados.setHorizontalAlignment(SwingConstants.LEFT);
        lblPrstamosRegistrados.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblPrstamosRegistrados.setBounds(25, 31, 514, 26);
        getContentPane().add(lblPrstamosRegistrados);
        
        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(22, 78, 985, 46);
        getContentPane().add(panelbusqueda);
        
        txtbusqueda = new JTextField();
        txtbusqueda.setText("Nombres, apellidos, identidad, estado civil y teléfono");
        txtbusqueda.setForeground(Color.GRAY);
        txtbusqueda.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtbusqueda.setColumns(10);
        txtbusqueda.setBounds(68, 10, 271, 27);
        panelbusqueda.add(txtbusqueda);
        
        
        InputMap map = txtbusqueda.getInputMap(JComponent.WHEN_FOCUSED); 
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "null");
        txtbusqueda.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtbusqueda.getText().length() == 50)
                    ke.consume();

                if (txtbusqueda.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "No está permitido ingresar espacios vacíos");
                    txtbusqueda.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
                filtro(); // Aplicar filtro al soltar la tecla
            }
        });
        
        JLabel lblbuscar = new JLabel("Buscar");
        lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        lblbuscar.setForeground(Color.BLACK);
        lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblbuscar.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblbuscar);
        
        cbxbusquedaCargo = new JComboBox<String>();
        cbxbusquedaCargo.setSelectedIndex(-1);
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaCargo.setBounds(438, 9, 136, 26);
        panelbusqueda.add(cbxbusquedaCargo);
        
        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(379, 7, 66, 26);
        panelbusqueda.add(lblCargo);
        
        cbxbusquedaArea = new JComboBox<String>();
        cbxbusquedaArea.setSelectedIndex(-1);
        cbxbusquedaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaArea.setBounds(638, 9, 136, 26);
        panelbusqueda.add(cbxbusquedaArea);
        
        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(584, 7, 56, 26);
        panelbusqueda.add(lblarea);
        
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(549, 22, 458, 56);
        getContentPane().add(panelbotones);
        
        JButton btnMenu = new JButton("Menú");
        btnMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 menu_principal menu = new menu_principal();
                 menu.setVisible(true);
                 menu.setLocationRelativeTo(null);
                 dispose();
        	}
        });
        btnMenu.setToolTipText("Regresar al menú principal");
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnMenu.setBackground(UIManager.getColor("Button.highlight"));
        btnMenu.setBounds(10, 17, 90, 23);
        panelbotones.add(btnMenu);
        
        JButton btnNuevoEmpleado = new JButton("Nuevo");
        btnNuevoEmpleado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		prestamos_nuevo n = new prestamos_nuevo();
        		n.setVisible(true);
        		n.setLocationRelativeTo(null);
        		n.btnactualizar.setVisible(false);
        		n.chxeditar.setVisible(false);
        		dispose();
        	    
        	
        	}
        });
        btnNuevoEmpleado.setToolTipText("Nuevo registro");
        btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
        btnNuevoEmpleado.setBounds(358, 17, 90, 23);
        panelbotones.add(btnNuevoEmpleado);
        
        btneliminar = new JButton("Eliminar");
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);
        
        btneliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablePrestamos.getSelectedRow();

                // Validar que se ha seleccionado una fila
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor, seleccione una fila para continuar.", 
                        "Advertencia", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(null, 
	                    "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos", 
	                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    int fila = tablePrestamos.convertRowIndexToModel(filaSeleccionada); // Convertir índice visual a modelo

                    int id = Integer.parseInt(tablePrestamos.getModel().getValueAt(fila, 0).toString());

                    consultas_prestamos consulta = new consultas_prestamos();
                    if (consulta.eliminarPrestamo(id)) {
                        ((DefaultTableModel) tablePrestamos.getModel()).removeRow(fila);
                        JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos", 
	                    		"Éxito", JOptionPane.INFORMATION_MESSAGE );
                        actualizarConteoRegistros();
                        
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Error al intentar eliminar el registro.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.setToolTipText("Imprimir registro");
        btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnImprimir.setBackground(UIManager.getColor("Button.highlight"));
        btnImprimir.setBounds(163, 18, 90, 23);
        panelbotones.add(btnImprimir);

        
        btnImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validar que se haya seleccionado una fila en la tabla
                int filaSeleccionada = tablePrestamos.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null,
                        "Por favor, seleccione un registro en la tabla para imprimir la constancia.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    // Obtener los datos del préstamo desde la fila seleccionada
                    int filaModelo = tablePrestamos.convertRowIndexToModel(filaSeleccionada);
                    prestamos datosPrestamo = obtenerPrestamoDeTabla(filaModelo); // Usamos el método mejorado

                    // Validar que los datos necesarios no estén vacíos
                    if (datosPrestamo.getNombres_empleado() == null || datosPrestamo.getNombres_empleado().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                            "Error: No se pudieron recuperar los datos del préstamo seleccionado.",
                            "Error de datos",
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Generar un nombre de archivo por defecto
                    String nombreArchivoPorDefecto = "constancia_prestamo_" +
                        datosPrestamo.getNombres_empleado().replace(" ", "_") + "_" +
                        datosPrestamo.getApellidos_empleado().replace(" ", "_") + ".pdf";

                    // Crear un JFileChooser para seleccionar la ruta de guardado
                    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
                    fileChooser.setDialogTitle("Guardar Constancia de Préstamo");
                    fileChooser.setSelectedFile(new java.io.File(nombreArchivoPorDefecto));
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos PDF", "pdf"));

                    int userSelection = fileChooser.showSaveDialog(null);
                    if (userSelection == javax.swing.JFileChooser.APPROVE_OPTION) {
                        // Obtener la ruta seleccionada
                        String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                        if (!rutaArchivo.endsWith(".pdf")) {
                            rutaArchivo += ".pdf";
                        }

                        // Generar el PDF utilizando la clase reporte_constancia_prestamo
                        reporte_constancia_prestamo constancia = new reporte_constancia_prestamo();
                        constancia.generarConstanciaPrestamo(datosPrestamo, rutaArchivo);

                        JOptionPane.showMessageDialog(null,
                            "Constancia generada correctamente en: " + rutaArchivo,
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);

                        // Abrir automáticamente el archivo generado
                        Runtime.getRuntime().exec("cmd /c start msedge \"" + rutaArchivo + "\"");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                        "Ocurrió un error al generar la constancia: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        
        
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(22, 131, 990, 440);
        getContentPane().add(panel_1);
        
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_1.add(scrollPane);
        
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(744, 390, 222, 27);
        panel_1.add(lblresultado_busqueda);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
		
		
		
		
		cargarCargosEnComboBox();
        cargarAreasEnComboBox();
        
        // Configuracion para los JComboBox
        cbxbusquedaCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        cbxbusquedaArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        
        // Configuración del placeholder
        txtbusqueda.setText(placeHolderText);
        txtbusqueda.setForeground(Color.GRAY);

        txtbusqueda.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtbusqueda.getText().equals(placeHolderText)) {
                	txtbusqueda.setText("");
                	txtbusqueda.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtbusqueda.getText().isEmpty()) {
                	txtbusqueda.setForeground(Color.GRAY);
                	txtbusqueda.setText(placeHolderText);
                }
            }
        });
		
		
		
	}//class
	
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	public void construirTabla() {
	    // Definir títulos de las columnas
	    String titulos[] = { 
	        "No.", "Código", "Nombres", "Apellidos", "Cuenta", "Dirección", 
	        "Cargo", "Área", "Fecha", "Monto", "Plazo", 
	        "Interés", "Interés Mensual", "Letra Mensual", "Motivo", 
	        "Autorizado por", "Cargo", "Autorizado por", "Cargo"
	    };

	    // Obtener la información de la base de datos
	    String informacion[][] = obtenerMatriz(); 

	    // Crear modelo de tabla no editable
	    DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Deshabilitar edición de celdas
	        }
	    };

	    // Inicializar la tabla con el modelo
	    tablePrestamos = new JTable(modeloTabla) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Deshabilitar edición de celdas
	        }
	    };

	    // Configurar propiedades de la tabla
	    tablePrestamos.setRowHeight(25);
	    tablePrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
	    tablePrestamos.getTableHeader().setOpaque(false);
	    tablePrestamos.getTableHeader().setBackground(new Color(255, 255, 0));
	    tablePrestamos.getTableHeader().setForeground(Color.BLACK);

	    // Agregar la tabla al JScrollPane
	    scrollPane.setViewportView(tablePrestamos);

	    // Configurar sorter para filtros
	    rowSorter = new TableRowSorter<>(tablePrestamos.getModel());
	    tablePrestamos.setRowSorter(rowSorter);

	    // Ajustar el ancho de las columnas (personaliza según necesites)
	    tablePrestamos.getColumnModel().getColumn(0).setPreferredWidth(30); // ID
	    tablePrestamos.getColumnModel().getColumn(1).setPreferredWidth(50); // ID Empleado

	    actualizarConteoRegistros();

	 // Evento de doble clic en la tabla
	    tablePrestamos.addMouseListener(new MouseAdapter() {
	        @SuppressWarnings("unused")
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) { // Doble clic
			        int filaSeleccionada = tablePrestamos.getSelectedRow();
			        if (filaSeleccionada != -1) { // Validar que hay una fila seleccionada
			            int fila = tablePrestamos.convertRowIndexToModel(filaSeleccionada); // Convertir índice visual a modelo

			            try {
			                // Obtener el ID del registro seleccionado
			                String id = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 0)); // Columna 0 es el ID

			                // Obtener otros datos de la fila
			                String idEmpleado = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 1));
			                String nombres = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 2));
			                String apellidos = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 3));
			                String cuenta = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 4));
			                String direccion = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 5));
			                String cargo = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 6));
			                String area = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 7));

			                // Convertir fecha de String a Date
			                String fechaStr = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 8));
			                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			                Date fechaAprobacion = dateFormat.parse(fechaStr);

			                String monto = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 9));
			                String plazo = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 10));
			                String tasa = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 11));
			                String motivo = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 14));
			                String autorizado1 = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 15));
			                String cargoAutorizado1 = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 16));
			                String autorizado2 = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 17));
			                String cargoAutorizado2 = String.valueOf(tablePrestamos.getModel().getValueAt(fila, 18));

			                // Abrir ventana prestamos_nuevo
			                prestamos_nuevo ventanaNuevo = new prestamos_nuevo();

			                // Cargar datos en los componentes
			                ventanaNuevo.txtid.setText(id); // Asegúrate de que este JTextField sea parte de la ventana
			                ventanaNuevo.cbxnombres.setSelectedItem(nombres);
			                ventanaNuevo.txtapellidos.setText(apellidos);
			                ventanaNuevo.txtcuenta.setText(cuenta);
			                ventanaNuevo.txadireccion.setText(direccion);
			                ventanaNuevo.txtcargo.setText(cargo);
			                ventanaNuevo.txtarea.setText(area);
			                ventanaNuevo.fecha_aprobacion.setDate(fechaAprobacion);
			                ventanaNuevo.txtmonto.setText(monto);
			                ventanaNuevo.txtplazo.setText(plazo);
			                ventanaNuevo.txttasa.setText(tasa);
			                ventanaNuevo.txamotivo.setText(motivo);
			                ventanaNuevo.txtautorizado1.setText(autorizado1);
			                ventanaNuevo.txtcargo_autorizado1.setText(cargoAutorizado1);
			                ventanaNuevo.txtautorizado2.setText(autorizado2);
			                ventanaNuevo.txtcargo_autorizado2.setText(cargoAutorizado2);

			                // Desactivar componentes por defecto
			                ventanaNuevo.cambiarEstadoComponentes(false);

			                // Mostrar ventana
			                ventanaNuevo.setVisible(true);
			                ventanaNuevo.setLocationRelativeTo(null);

			                // Cerrar ventana actual
			                dispose();
			            } catch (Exception ex) {
			                JOptionPane.showMessageDialog(null, "Error al procesar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			                ex.printStackTrace();
			            }
			        }
			    }
			}
	    });
	}
	
	

	
	public static String[][] obtenerMatriz() {
	    ArrayList<Object[]> prestamos = buscarPrestamosConMatriz();
	    String matrizInfo[][] = new String[prestamos.size()][19];

	    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yy");

	    for (int i = 0; i < prestamos.size(); i++) {
	        Object[] prestamo = prestamos.get(i);
	        for (int j = 0; j < prestamo.length; j++) {
	            if (prestamo[j] instanceof java.util.Date) {
	                matrizInfo[i][j] = outputFormat.format(prestamo[j]);
	            } else {
	                matrizInfo[i][j] = prestamo[j] != null ? prestamo[j].toString() : "";
	            }
	        }
	    }
	    return matrizInfo;
	}

	
	public static ArrayList<Object[]> buscarPrestamosConMatriz() {
	    ArrayList<Object[]> listaPrestamos = new ArrayList<>();
	    String sql = "SELECT * FROM prestamos";
	    conexion conn = new conexion();

	    try (Connection con = conn.conectar();
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {
	            Object[] fila = new Object[19]; // Número de columnas
	            fila[0] = rs.getInt("id");
	            fila[1] = rs.getString("id_empleado");
	            fila[2] = rs.getString("nombres_empleado");
	            fila[3] = rs.getString("apellidos_empleado");
	            fila[4] = rs.getString("cuenta_empleado");
	            fila[5] = rs.getString("direccion_empleado");
	            fila[6] = rs.getString("cargo_empleado");
	            fila[7] = rs.getString("area_empleado");
	            fila[8] = rs.getDate("fecha_aprobacion");
	            fila[9] = rs.getInt("monto_solicitado");
	            fila[10] = rs.getInt("plazo_pago");
	            fila[11] = rs.getInt("tasa_interes");
	            fila[12] = rs.getDouble("interes_deducible_mensual");
	            fila[13] = rs.getDouble("letra_mensual");
	            fila[14] = rs.getString("motivo_prestamo");
	            fila[15] = rs.getString("autorizado1");
	            fila[16] = rs.getString("cargo_autorizado1");
	            fila[17] = rs.getString("autorizado2");
	            fila[18] = rs.getString("cargo_autorizado2");
	            listaPrestamos.add(fila);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al consultar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }

	    return listaPrestamos;
	}

	
	 public void filtro() {
	        filtroCodigo = txtbusqueda.getText();
	        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 0, 1, 2, 3));

	        // Actualizar el conteo de registros visibles
	        actualizarConteoRegistros();
	    }
	
	
	
	 private void actualizarConteoRegistros() {
	        int registrosVisibles = tablePrestamos.getRowCount(); 
	        lblresultado_busqueda.setText("Registros: " + registrosVisibles);
	    }

	 
	 private void cargarCargosEnComboBox() {
		    consultas_cargos consultas = new consultas_cargos();
		    List<String> cargos = consultas.obtenerCargos();
		    cbxbusquedaCargo.removeAllItems();
		    cbxbusquedaCargo.addItem(" ");
		    
		    for (String cargo : cargos) {
		    	cbxbusquedaCargo.addItem(cargo);
		    }
		    
		    cbxbusquedaCargo.setSelectedIndex(0);
		}
	    
	    
	    
	    private void cargarAreasEnComboBox() {
		    consultas_areas consultas = new consultas_areas();
		    List<String> areas = consultas.obtenerAreas();
		    cbxbusquedaArea.removeAllItems();
		    cbxbusquedaArea.addItem(" ");
		    
		    for (String area : areas) {
		    	cbxbusquedaArea.addItem(area);
		    }
		    
		    cbxbusquedaArea.setSelectedIndex(0);
		}
	    
	    private void aplicarFiltros() {
	        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
	        String filtroArea = (String) cbxbusquedaArea.getSelectedItem();

	        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

	        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
	            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 6)); 
	        }

	        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
	            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 7)); 
	        }

	       

	        if (filtros.isEmpty()) {
	        	rowSorter.setRowFilter(null); 
	        } else {
	            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
	            rowSorter.setRowFilter(combinedFilter);
	        }

	        // Actualizar el conteo de registros visibles
	        actualizarConteoRegistros();
	    }
	    
	    
	    private prestamos obtenerPrestamoDeTabla(int filaSeleccionada) {
	        prestamos prestamo = new prestamos();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy"); // Ajusta según el formato real de las fechas

	        try {
	            // Manejo de valores que podrían ser nulos
	        	prestamo.setId(getValueAsInteger(filaSeleccionada,0));
	            prestamo.setId_empleado(getValueAsString(filaSeleccionada, 1));
	            prestamo.setNombres_empleado(getValueAsString(filaSeleccionada, 2));
	            prestamo.setApellidos_empleado(getValueAsString(filaSeleccionada, 3));
	            prestamo.setCuenta_empleado(getValueAsString(filaSeleccionada, 4));
	            prestamo.setDireccion_empleado(getValueAsString(filaSeleccionada, 5));
	            prestamo.setCargo_empleado(getValueAsString(filaSeleccionada, 6));
	            prestamo.setArea_empleado(getValueAsString(filaSeleccionada, 7));

	            // Manejo de fecha
	            String fechaTexto = getValueAsString(filaSeleccionada, 8);
	            if (!fechaTexto.isEmpty()) {
	                Date fechaAprobacion = dateFormat.parse(fechaTexto);
	                prestamo.setFecha_aprobacion(fechaAprobacion);
	            }

	            // Manejo de valores numéricos
	            prestamo.setMonto_solicitado(getValueAsInteger(filaSeleccionada, 9));
	            prestamo.setPlazo_pago(getValueAsInteger(filaSeleccionada, 10));
	            prestamo.setTasa_interes(getValueAsInteger(filaSeleccionada, 11));
	            prestamo.setInteres_deducible_mensual(getValueAsDouble(filaSeleccionada, 12));
	            prestamo.setLetra_mensual(getValueAsDouble(filaSeleccionada, 13));

	            // Otros datos
	            prestamo.setMotivo_prestamo(getValueAsString(filaSeleccionada, 14));
	            prestamo.setAutorizado1(getValueAsString(filaSeleccionada, 15));
	            prestamo.setCargo_autorizado1(getValueAsString(filaSeleccionada, 16));
	            prestamo.setAutorizado2(getValueAsString(filaSeleccionada, 17));
	            prestamo.setCargo_autorizado2(getValueAsString(filaSeleccionada, 18));

	        } catch (ParseException e) {
	            JOptionPane.showMessageDialog(null, 
	                    "Error al convertir la fecha: " + e.getMessage(), 
	                    "Error de formato de fecha", 
	                    JOptionPane.ERROR_MESSAGE);
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, 
	                    "Error al convertir un valor numérico: " + e.getMessage(), 
	                    "Error de formato", 
	                    JOptionPane.ERROR_MESSAGE);
	        }

	        return prestamo;
	    }

	    // Métodos auxiliares para obtener valores
	    private String getValueAsString(int fila, int columna) {
	        Object value = tablePrestamos.getValueAt(fila, columna);
	        return value != null ? value.toString() : "";
	    }

	    private int getValueAsInteger(int fila, int columna) {
	        String value = getValueAsString(fila, columna);
	        return !value.isEmpty() ? Integer.parseInt(value) : 0;
	    }

	    private double getValueAsDouble(int fila, int columna) {
	        String value = getValueAsString(fila, columna);
	        return !value.isEmpty() ? Double.parseDouble(value) : 0.0;
	    }



}

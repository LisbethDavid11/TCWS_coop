package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import clases.apotaciones;
import clases.prestamos;
import consultas.consultas_aportaciones;
import reportes.reporte_aportaciones_mensual;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class aportaciones_nuevo extends JFrame {
	
	private DefaultTableModel modeloTabla;
	private JTable tablaEmpleados;
	public JScrollPane scrollPane;
	public JComboBox<String> cbxmes;
	public JComboBox<String> cbxaño;
	public JCheckBox chkigual;
	public JButton btnbuscar;
	public JButton btnregresar;
	public JButton btnguardar;
	public JLabel lblresultado_busqueda;
	public JDateChooser fecha_registro;
	public JCheckBox chxeditar;
	public JButton btnImprimir;
	public JButton btnactualizar;
	
	
	public aportaciones_nuevo() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
        setResizable(false);
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

        
        JPanel panel_scroll = new JPanel();
        panel_scroll.setLayout(null);
        panel_scroll.setBackground(SystemColor.menu);
        panel_scroll.setBounds(25, 129, 990, 440);
        getContentPane().add(panel_scroll);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_scroll.add(scrollPane);
        
        tablaEmpleados = new JTable();
        scrollPane.setViewportView(tablaEmpleados); // Agrega la tabla al scroll pane
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(744, 390, 222, 27);
        panel_scroll.add(lblresultado_busqueda);
        
        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 76, 990, 45);
        getContentPane().add(panelbusqueda);
        
        JLabel lblmes = new JLabel("Mes");
        lblmes.setHorizontalAlignment(SwingConstants.LEFT);
        lblmes.setForeground(Color.BLACK);
        lblmes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblmes.setBounds(170, 10, 67, 26);
        panelbusqueda.add(lblmes);
        
        cbxmes = new JComboBox<String>();
        cbxmes.setSelectedIndex(-1);
        cbxmes.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxmes.setBounds(219, 10, 126, 26);
        panelbusqueda.add(cbxmes);
        
        cbxaño = new JComboBox<String>();
        cbxaño.setSelectedIndex(-1);
        cbxaño.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxaño.setBounds(54, 10, 101, 26);
        panelbusqueda.add(cbxaño);
        
        JLabel lblAo = new JLabel("Año");
        lblAo.setHorizontalAlignment(SwingConstants.LEFT);
        lblAo.setForeground(Color.BLACK);
        lblAo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblAo.setBounds(10, 10, 67, 26);
        panelbusqueda.add(lblAo);
        
        chkigual = new JCheckBox("Igual al mes anterior");
        chkigual.setFont(new Font("Segoe UI", Font.BOLD, 15));
        chkigual.setBounds(365, 11, 195, 21);
        panelbusqueda.add(chkigual);
        
        btnbuscar = new JButton("Buscar registro");
        btnbuscar.setToolTipText("Regresar a la tabla");
        btnbuscar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnbuscar.setBackground(UIManager.getColor("Button.highlight"));
        btnbuscar.setBounds(591, 10, 126, 23);
        panelbusqueda.add(btnbuscar);
        
        fecha_registro = new JDateChooser();
        fecha_registro.setBackground(new Color(240, 240, 240));
        fecha_registro.setForeground(new Color(0, 0, 0));
        fecha_registro.setBounds(864, 10, 116, 25);
        panelbusqueda.add(fecha_registro);
        
        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFecha.setBounds(799, 10, 67, 26);
        panelbusqueda.add(lblFecha);
        
        
        JLabel lblNuevaAportacinMensual = new JLabel("NUEVA APORTACIÓN MENSUAL");
        lblNuevaAportacinMensual.setHorizontalAlignment(SwingConstants.LEFT);
        lblNuevaAportacinMensual.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblNuevaAportacinMensual.setBounds(28, 29, 514, 26);
        getContentPane().add(lblNuevaAportacinMensual);
        
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 20, 463, 56);
        getContentPane().add(panelbotones);
        
        btnregresar = new JButton("Regresar");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aportaciones_tabla tabla = new aportaciones_tabla();
        		tabla.setVisible(true);
        		tabla.setLocationRelativeTo(null);
        		dispose();
        	
        	}
        });
        btnregresar.setToolTipText("Regresar a la tabla");
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.setBounds(10, 17, 90, 23);
        panelbotones.add(btnregresar);
        
        btnguardar = new JButton("Guardar");
        btnguardar.setToolTipText("Guardar registro");
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(333, 33, 90, 23);
        panelbotones.add(btnguardar);
        
        btnImprimir = new JButton("Imprimir");
        btnImprimir.addActionListener(e -> {
            try {
                // Obtener el mes y el año seleccionados en el formulario
                String mes = (String) cbxmes.getSelectedItem();
                String anio = (String) cbxaño.getSelectedItem();

                if (mes == null || anio == null) {
                    JOptionPane.showMessageDialog(this, "Seleccione el mes y el año para generar el reporte.", 
                                                  "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Validar que existan datos en la tabla antes de intentar generar el reporte
                if (modeloTabla.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No hay datos en la tabla para generar el reporte.", 
                                                  "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Crear una lista con los datos de la tabla para el reporte
                consultas_aportaciones consultas = new consultas_aportaciones();
                List<apotaciones> empleados = consultas.obtenerDetallesPorMesYAño(mes, anio);

                if (empleados == null || empleados.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros para el mes " + mes + " del año " + anio + ".", 
                                                  "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Usar JFileChooser para seleccionar la ubicación donde guardar el reporte
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar reporte de aportaciones");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setSelectedFile(new java.io.File("reporte_mensual_aportaciones_" + anio + "_" + mes + ".pdf"));

                int userSelection = fileChooser.showSaveDialog(this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();

                    // Asegurarse de que el archivo tenga la extensión .pdf
                    if (!rutaArchivo.endsWith(".pdf")) {
                        rutaArchivo += ".pdf";
                    }

                    // Generar el reporte usando la clase `reporte_aportaciones_mensual`
                    reporte_aportaciones_mensual reporte = new reporte_aportaciones_mensual();
                    reporte.generarReportePDF(mes, anio, empleados, rutaArchivo);

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al generar el reporte: " + ex.getMessage(), 
                                              "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        btnImprimir.setToolTipText("Imprimir registro");
        btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnImprimir.setBackground(UIManager.getColor("Button.highlight"));
        btnImprimir.setBounds(255, 17, 90, 23);
        panelbotones.add(btnImprimir);
        
        btnactualizar = new JButton("Actualizar");
        btnactualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarAportaciones();
        		
        	}
        });
        btnactualizar.setToolTipText("Actualizar registro");
        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
        btnactualizar.setBounds(351, 18, 90, 23);
        panelbotones.add(btnactualizar);
        
        chxeditar = new JCheckBox("Editar registro");
        chxeditar.setHorizontalAlignment(SwingConstants.TRAILING);
        chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chxeditar.setBounds(106, 17, 142, 21);
        panelbotones.add(chxeditar);
        
        
        
        cargarCombos();
        configurarTabla();
        establecerFechaActualEnJDateChooser();
        
        btnbuscar.addActionListener(e -> buscarEmpleados());
        btnguardar.addActionListener(e -> guardarAportaciones());

        
   
     


        
	}//class
	
	
		private void cargarCombos() {
		    // Cargar los meses
		    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
		                      "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
		    for (String mes : meses) {
		        cbxmes.addItem(mes);
		    }
	
		    // Cargar los años
		    int anioActual = java.time.LocalDate.now().getYear();
		    for (int i = anioActual - 2; i <= anioActual + 10; i++) {
		        cbxaño.addItem(String.valueOf(i));
		    }
	
		    // Seleccionar el mes y año actuales por defecto
		    cbxmes.setSelectedIndex(java.time.LocalDate.now().getMonthValue() - 1);
		    cbxaño.setSelectedItem(String.valueOf(anioActual));
		}
		
		
		private void establecerFechaActualEnJDateChooser() {
		    // Establece la fecha actual
		    java.util.Date fechaActual = new java.util.Date();
		    fecha_registro.setDate(fechaActual); // Fecha actual en el JDateChooser

		    // Configura el formato de la fecha como dd-MM-yy
		    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yy");
		    fecha_registro.setDateFormatString(formato.toPattern());
		}



		
		
		// Configurar la tabla
		private void configurarTabla() {
			String[] columnas = {"No.", "Código", "Nombres", "Apellidos", "Aportación", "Interés", "Cuota", "Total"};
		    modeloTabla = new DefaultTableModel(null, columnas) {
		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return column >= 4; // Solo permitir edición en Aportación, Interés y Cuota
		        }
		    };
		    tablaEmpleados.setModel(modeloTabla);

		    // Ajustar el tamaño de las columnas
		    tablaEmpleados.getColumnModel().getColumn(0).setPreferredWidth(0); // No.
		    tablaEmpleados.getColumnModel().getColumn(1).setPreferredWidth(2); // ID Empleado
		    tablaEmpleados.getColumnModel().getColumn(4).setPreferredWidth(1);
		    tablaEmpleados.getColumnModel().getColumn(5).setPreferredWidth(1);
		    tablaEmpleados.getColumnModel().getColumn(6).setPreferredWidth(1);
		    tablaEmpleados.getColumnModel().getColumn(7).setPreferredWidth(2);
		    
		    
		    
		    // Cambiar fuente de contenido y encabezado
		    tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		    tablaEmpleados.setRowHeight(25);
		    tablaEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));

		    // Cambiar color del encabezado
		    JTableHeader header = tablaEmpleados.getTableHeader();
		    header.setDefaultRenderer(new DefaultTableCellRenderer() {
		        @Override
		        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		            c.setBackground(new Color(255, 255, 0)); // Azul oscuro
		            //c.setForeground(Color.WHITE); // Texto blanco
		            return c;
		        }
		    });
		

		    
		    // Agregar funcionalidad tipo Excel (arrastrar valores hacia abajo)
		    tablaEmpleados.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mousePressed(MouseEvent e) {
		            JTable table = (JTable) e.getSource();
		            int col = table.columnAtPoint(e.getPoint());
		            int row = table.rowAtPoint(e.getPoint());

		            // Verificar si es la columna de aportación (index 4)
		            if (col == 4) {
		                copiarValorHaciaAbajo(table, row, col);
		            }
		        }
		    });
		}

		private void copiarValorHaciaAbajo(JTable table, int startRow, int col) {
		    if (startRow < 0 || startRow >= table.getRowCount()) {
		        System.err.println("Fila inicial inválida para copiar: " + startRow);
		        return;
		    }

		    Object valor = table.getValueAt(startRow, col); // Valor de la celda seleccionada
		    for (int i = startRow + 1; i < table.getRowCount(); i++) {
		        table.setValueAt(valor, i, col); // Copiar valor a las filas siguientes
		    }
		}


		
		
		
		
		private void buscarEmpleados() {
		    if (cbxmes.getSelectedIndex() == -1 || cbxaño.getSelectedIndex() == -1) {
		        JOptionPane.showMessageDialog(this, "Debe seleccionar un mes y un año", 
		                                      "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    // Obtener mes y año seleccionados
		    String mesSeleccionado = (String) cbxmes.getSelectedItem();
		    String anioSeleccionado = (String) cbxaño.getSelectedItem();

		    // Validar si ya existe un registro para el mes y año seleccionados
		    consultas_aportaciones consultas = new consultas_aportaciones();
		    if (consultas.existeRegistro(mesSeleccionado, anioSeleccionado)) {
		        JOptionPane.showMessageDialog(this, "Ya existe un registro para el mes " + mesSeleccionado + " del año " + anioSeleccionado + ".", 
		                                      "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    // Determinar el mes y año anteriores si el JCheckBox está seleccionado
		    String mesConsulta = mesSeleccionado;
		    String anioConsulta = anioSeleccionado;

		    if (chkigual.isSelected()) {
		        String[] mesYAnioAnterior = consultas.obtenerMesYAnioAnterior(cbxmes.getSelectedIndex() + 1, Integer.parseInt(anioSeleccionado));
		        mesConsulta = mesYAnioAnterior[0];
		        anioConsulta = mesYAnioAnterior[1];
		    }

		    // Limpia la tabla antes de mostrar resultados
		    modeloTabla.setRowCount(0);

		    // Recupera los empleados activos
		    List<String[]> empleados = consultas.obtenerEmpleadosActivos();

		    if (empleados == null || empleados.isEmpty()) {
		        JOptionPane.showMessageDialog(this, "No se encontraron empleados activos", 
		                                      "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    int numero = 1; // Contador para la columna "No."
		    for (String[] empleado : empleados) {
		        String idEmpleado = empleado[0];
		        String nombres = empleado[1];
		        String apellidos = empleado[2];

		        double aportacion = 0.0;
		        double interes = 0.0;
		        double cuota = 0.0;

		        // Recuperar datos del mes anterior si el JCheckBox está seleccionado
		        if (chkigual.isSelected()) {
		            apotaciones datosAnteriores = consultas.obtenerAportacionPorEmpleado(idEmpleado, mesConsulta, anioConsulta);
		            if (datosAnteriores != null) {
		                aportacion = datosAnteriores.getAportacion();
		                interes = datosAnteriores.getInteres();
		                cuota = datosAnteriores.getCuota();
		            }
		        }

		        double total = aportacion + interes + cuota;
		        modeloTabla.addRow(new Object[]{numero++, idEmpleado, nombres, apellidos, aportacion, interes, cuota, total});
		    }

		    lblresultado_busqueda.setText("Resultados: " + empleados.size());
		    calcularCuotasEIntereses();
		}



		private void guardarAportaciones() {
		    int filas = modeloTabla.getRowCount();
		    if (filas == 0) {
		        JOptionPane.showMessageDialog(this, "No hay datos para guardar.", 
		                                      "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    consultas_aportaciones consultas = new consultas_aportaciones();
		    boolean exito = true;

		    // Obtener la fecha seleccionada del JDateChooser
		    java.util.Date fechaSeleccionada = fecha_registro.getDate();
		    java.sql.Date fechaRegistro = (fechaSeleccionada != null) 
		            ? new java.sql.Date(fechaSeleccionada.getTime()) 
		            : new java.sql.Date(System.currentTimeMillis());

		    // Obtener el mes y el año seleccionados como texto
		    String mesSeleccionado = (String) cbxmes.getSelectedItem(); // Texto del mes
		    String añoSeleccionado = (String) cbxaño.getSelectedItem(); // Año en texto

		    // Iterar sobre las filas de la tabla
		    for (int i = 0; i < filas; i++) {
		        apotaciones aportacion = new apotaciones();
		        try {
		            aportacion.setId_empleado(Integer.parseInt(modeloTabla.getValueAt(i, 1).toString())); // ID Empleado
		            aportacion.setNombres_empleado(modeloTabla.getValueAt(i, 2).toString()); // Nombres
		            aportacion.setApellidos_empleado(modeloTabla.getValueAt(i, 3).toString()); // Apellidos
		            aportacion.setMes(mesSeleccionado); // Mes en texto
		            aportacion.setAño(añoSeleccionado); // Año en texto
		            aportacion.setAportacion(Double.parseDouble(modeloTabla.getValueAt(i, 4).toString())); // Aportación
		            aportacion.setInteres(Double.parseDouble(modeloTabla.getValueAt(i, 5).toString())); // Interés
		            aportacion.setCuota(Double.parseDouble(modeloTabla.getValueAt(i, 6).toString())); // Cuota
		            aportacion.setFecha_registro(fechaRegistro); // Fecha de registro
		        } catch (Exception ex) {
		            System.err.println("Error al procesar fila " + i + ": " + ex.getMessage());
		            exito = false;
		            continue;
		        }

		        // Intentar guardar la aportación
		        if (!consultas.registrarAportacion(aportacion)) {
		            exito = false;
		            break;
		        }
		    }

		    // Mostrar mensaje de éxito o error
		    if (exito) {
		        JOptionPane.showMessageDialog(this, "Aportaciones guardadas exitosamente.", 
		                                      "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        modeloTabla.setRowCount(0); // Limpiar la tabla después de guardar
		    } else {
		        JOptionPane.showMessageDialog(this, "Error al guardar aportaciones.", 
		                                      "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		
		public void cargarDetalles(List<apotaciones> detalles) {
		    // Limpia la tabla existente en el formulario
		    modeloTabla.setRowCount(0);

		    // Recorre la lista de detalles y añade cada uno a la tabla
		    for (apotaciones detalle : detalles) {
		        double aportacion = detalle.getAportacion();
		        double interes = detalle.getInteres();
		        double cuota = detalle.getCuota();
		        double total = aportacion + interes + cuota;

		        modeloTabla.addRow(new Object[]{
		            detalle.getId_aportaciones(),        // No. (id clave primaria)
		            detalle.getId_empleado(),           // Código del empleados
		            detalle.getNombres_empleado(),      // Nombres del empleados
		            detalle.getApellidos_empleado(),    // Apellidos del empleados
		            String.format("%.2f", aportacion),  // Aportación
		            String.format("%.2f", interes),     // Interés
		            String.format("%.2f", cuota),       // Cuota
		            String.format("%.2f", total)        // Total calculado
		        });
		    }

		    // Configura los controles del formulario con los detalles del primer registro
		    if (!detalles.isEmpty()) {
		        apotaciones primerDetalle = detalles.get(0);

		        cbxmes.setSelectedItem(primerDetalle.getMes());
		        cbxaño.setSelectedItem(primerDetalle.getAño());
		        fecha_registro.setDate(primerDetalle.getFecha_registro());
		    } else {
		        JOptionPane.showMessageDialog(this, "No hay detalles para cargar.", "Información", JOptionPane.INFORMATION_MESSAGE);
		    }

		    // Ocultar botones y desactivar componentes
		    btnguardar.setVisible(false);
		    btnImprimir.setVisible(false);
		    btnactualizar.setVisible(false);
		    habilitarComponentes(false);

		    // Configurar acción para el JCheckBox
		    chxeditar.addActionListener(e -> {
		        boolean editar = chxeditar.isSelected();
		        habilitarComponentes(editar);
		        btnactualizar.setVisible(editar);
		        btnImprimir.setVisible(editar);
		    });
		}

		private void habilitarComponentes(boolean habilitar) {
		    cbxmes.setEnabled(habilitar);
		    cbxaño.setEnabled(habilitar);
		    chkigual.setEnabled(habilitar);
		    btnbuscar.setEnabled(habilitar);
		    fecha_registro.setEnabled(habilitar);
		    tablaEmpleados.setEnabled(habilitar);
		}



		
		private void calcularCuotasEIntereses() {
		    consultas_aportaciones consultas = new consultas_aportaciones();

		    // Validar si hay filas en el modelo de la tabla
		    if (modeloTabla.getRowCount() == 0) {
		        JOptionPane.showMessageDialog(this, "No hay empleados en la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
		        return;
		    }

		    // Validar que haya al menos 8 columnas
		    if (modeloTabla.getColumnCount() < 8) {
		        JOptionPane.showMessageDialog(this, "La tabla no tiene suficientes columnas.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    // Obtener mes y año seleccionados
		    int mes = cbxmes.getSelectedIndex() + 1;
		    int anio = Integer.parseInt((String) cbxaño.getSelectedItem());

		    // Iterar por cada fila de la tabla
		    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
		        String idEmpleado = modeloTabla.getValueAt(i, 1).toString();

		        // Obtener préstamos activos
		        List<prestamos> prestamosActivos = consultas.obtenerPrestamosActivosPorMes(idEmpleado, mes, anio);

		        if (!prestamosActivos.isEmpty()) {
		            prestamos prestamo = prestamosActivos.get(0);
		            modeloTabla.setValueAt(prestamo.getInteres_deducible_mensual(), i, 5); // Interés
		            modeloTabla.setValueAt(prestamo.getLetra_mensual(), i, 6);             // Cuota
		            double total = prestamo.getInteres_deducible_mensual() + prestamo.getLetra_mensual();
		            modeloTabla.setValueAt(total, i, 7);                                  // Total
		        }
		    }
		}

		
		
		
		private void actualizarAportaciones() {
		    int filas = modeloTabla.getRowCount();
		    if (filas == 0) {
		        JOptionPane.showMessageDialog(this, "No hay datos para actualizar.", 
		                                      "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    consultas_aportaciones consultas = new consultas_aportaciones();
		    boolean exito = true;

		    // Obtener los valores del ComboBox y la fecha
		    String mesSeleccionado = (String) cbxmes.getSelectedItem(); // Mes
		    int añoSeleccionado = Integer.parseInt((String) cbxaño.getSelectedItem()); // Año
		    java.util.Date fechaSeleccionada = fecha_registro.getDate();
		    java.sql.Date fechaCreacion = (fechaSeleccionada != null) 
		            ? new java.sql.Date(fechaSeleccionada.getTime()) 
		            : null;

		    if (fechaCreacion == null) {
		        JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de creación válida.", 
		                                      "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }

		    for (int i = 0; i < filas; i++) {
		        try {
		            int idAportaciones = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString()); // ID Aportaciones
		            double aportacion = Double.parseDouble(modeloTabla.getValueAt(i, 4).toString()); // Aportación
		            double interes = Double.parseDouble(modeloTabla.getValueAt(i, 5).toString()); // Interés
		            double cuota = Double.parseDouble(modeloTabla.getValueAt(i, 6).toString()); // Cuota

		            // Crear el objeto de actualización
		            apotaciones aportacionActualizada = new apotaciones();
		            aportacionActualizada.setId_aportaciones(idAportaciones);
		            aportacionActualizada.setAportacion(aportacion);
		            aportacionActualizada.setInteres(interes);
		            aportacionActualizada.setCuota(cuota);
		            aportacionActualizada.setMes(mesSeleccionado);
		            aportacionActualizada.setAño(String.valueOf(añoSeleccionado));
		            aportacionActualizada.setFecha_registro(fechaCreacion);

		            // Intentar actualizar la base de datos
		            if (!consultas.actualizarAportacion(aportacionActualizada)) {
		                exito = false;
		                JOptionPane.showMessageDialog(this, 
		                        "No se pudo actualizar el registro con ID " + idAportaciones + ".", 
		                        "Error", JOptionPane.ERROR_MESSAGE);
		                break;
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            exito = false;
		        }
		    }

		    if (exito) {
		        JOptionPane.showMessageDialog(this, "Aportaciones actualizadas correctamente.", 
		                                      "Éxito", JOptionPane.INFORMATION_MESSAGE);
		        
		        
		    } else {
		        JOptionPane.showMessageDialog(this, "Ocurrió un error al actualizar las aportaciones.", 
		                                      "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}



		


		
		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
}//end

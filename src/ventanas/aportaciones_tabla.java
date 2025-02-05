package ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import clases.apotaciones;
import consultas.consultas_aportaciones;
import principal.menu_principal;

@SuppressWarnings("serial")
public class aportaciones_tabla extends JFrame{
	
	private DefaultTableModel modeloTabla;
	private JTable tablaAportaciones; 
	public JScrollPane scrollPane;
	public JButton btnMenu;
	public JButton btnNuevoEmpleado;
	public JButton btneliminar;
	public JComboBox<String> cbxbusquedaMes;
	public JComboBox<String> cbxbusquedaAnio;
	public JLabel lblresultado_busqueda;
	
	
	
	public aportaciones_tabla() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblAportacionesMensualesRegistradas = new JLabel("APORTACIONES REGISTRADAS");
        lblAportacionesMensualesRegistradas.setHorizontalAlignment(SwingConstants.LEFT);
        lblAportacionesMensualesRegistradas.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblAportacionesMensualesRegistradas.setBounds(27, 31, 514, 26);
        getContentPane().add(lblAportacionesMensualesRegistradas);
        
        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(24, 78, 985, 46);
        getContentPane().add(panelbusqueda);
        
        cbxbusquedaAnio = new JComboBox<String>();
        cbxbusquedaAnio.setSelectedIndex(-1);
        cbxbusquedaAnio.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaAnio.setBounds(69, 12, 136, 26);
        panelbusqueda.add(cbxbusquedaAnio);
        
        JLabel lblMes = new JLabel("Año");
        lblMes.setHorizontalAlignment(SwingConstants.LEFT);
        lblMes.setForeground(Color.BLACK);
        lblMes.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblMes.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblMes);
        
        JLabel lblMes_1 = new JLabel("Mes");
        lblMes_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblMes_1.setForeground(Color.BLACK);
        lblMes_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblMes_1.setBounds(259, 10, 66, 26);
        panelbusqueda.add(lblMes_1);
        
        cbxbusquedaMes = new JComboBox<String>();
        cbxbusquedaMes.setSelectedIndex(-1);
        cbxbusquedaMes.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaMes.setBounds(318, 12, 136, 26);
        panelbusqueda.add(cbxbusquedaMes);
        
        JPanel panel_tabla = new JPanel();
        panel_tabla.setLayout(null);
        panel_tabla.setBackground(SystemColor.menu);
        panel_tabla.setBounds(24, 131, 990, 440);
        getContentPane().add(panel_tabla);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_tabla.add(scrollPane);
        
        tablaAportaciones = new JTable();
        scrollPane.setViewportView(tablaAportaciones); 
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(744, 390, 222, 27);
        panel_tabla.add(lblresultado_busqueda);
        
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(551, 22, 458, 56);
        getContentPane().add(panelbotones);
        
        btnMenu = new JButton("Menú");
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
        
        btnNuevoEmpleado = new JButton("Nuevo");
        btnNuevoEmpleado.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		aportaciones_nuevo nuevo = new aportaciones_nuevo();
        		nuevo.setVisible(true);
        		nuevo.setLocationRelativeTo(null);
        		nuevo.chxeditar.setVisible(false);
        		nuevo.btnactualizar.setVisible(false);
        		nuevo.btnImprimir.setVisible(false);
        		dispose();
        	}
        });
        btnNuevoEmpleado.setToolTipText("Nuevo registro");
        btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
        btnNuevoEmpleado.setBounds(358, 17, 90, 23);
        panelbotones.add(btnNuevoEmpleado);
        
        btneliminar = new JButton("Eliminar");
        btneliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminarRegistro();
        	}
        });
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
		
	
		configurarTabla();
        cargarDatos();
        inicializarCombosBusqueda();

        tablaAportaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    cargarFormulario();
                }
            }
        });
        
        cbxbusquedaMes.addActionListener(e -> {
            String mesSeleccionado = (String) cbxbusquedaMes.getSelectedItem();
            String anioSeleccionado = (String) cbxbusquedaAnio.getSelectedItem();

            if (anioSeleccionado != null) { // Si el año está seleccionado
                cargarDatosPorMesYAño(mesSeleccionado, anioSeleccionado);
            }
        });


        
	}//class
	
	
	private void configurarTabla() {
	    // Definir los encabezados de la tabla
	    String[] columnas = {"No.", "Año", "Mes", "Fecha de creación"};

	    // Crear el modelo de la tabla deshabilitando la edición de celdas
	    modeloTabla = new DefaultTableModel(null, columnas) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No permite la edición en ninguna celda
	        }
	    };
	    tablaAportaciones.setModel(modeloTabla);

	    // Ajustar el tamaño de las columnas
	    tablaAportaciones.getColumnModel().getColumn(0).setPreferredWidth(50);  // No.
	    tablaAportaciones.getColumnModel().getColumn(1).setPreferredWidth(100); // Año
	    tablaAportaciones.getColumnModel().getColumn(2).setPreferredWidth(100); // Mes
	    tablaAportaciones.getColumnModel().getColumn(3).setPreferredWidth(150); // Fecha de creación

	    // Configurar la fuente de la tabla y altura de las filas
	    tablaAportaciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    tablaAportaciones.setRowHeight(25);

	    // Configurar la fuente y color del encabezado
	    JTableHeader header = tablaAportaciones.getTableHeader();
	    header.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    header.setDefaultRenderer(new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            c.setBackground(new Color(255, 255, 0)); // Fondo amarillo
	            c.setForeground(Color.BLACK);           // Texto negro
	            setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto del encabezado
	            return c;
	        }
	    });
	}


	
	

	private void cargarDatos() {
	    consultas_aportaciones consultas = new consultas_aportaciones();
	    List<apotaciones> lista = consultas.obtenerAportacionesPorMesYAño("", String.valueOf(java.time.LocalDate.now().getYear()));

	    // Limpia la tabla antes de agregar nuevos datos
	    modeloTabla.setRowCount(0);

	    // Agregar los datos obtenidos desde la base de datos
	    int contador = 1;
	    for (apotaciones registro : lista) {
	        modeloTabla.addRow(new Object[]{
	            contador++,                 // Número
	            registro.getAño(),          // Año
	            registro.getMes(),          // Mes
	            registro.getFecha_registro() // Fecha de creación
	        });
	    }

	    lblresultado_busqueda.setText("Resultados: " + lista.size());
	}



	private void cargarFormulario() {
	    int filaSeleccionada = tablaAportaciones.getSelectedRow();
	    if (filaSeleccionada == -1) {
	        JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para continuar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Obtener los valores de mes y año de la tabla
	    String anio = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
	    String mes = modeloTabla.getValueAt(filaSeleccionada, 2).toString();

	    // Realizar consulta para obtener los detalles
	    consultas_aportaciones consultas = new consultas_aportaciones();
	    List<apotaciones> detalles = consultas.obtenerDetallesPorMesYAño(mes, anio);

	    if (detalles == null || detalles.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "No se encontraron detalles para el mes " + mes + " del año " + anio + ".", "Información", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }

	    // Pasar los detalles al formulario aportaciones_nuevo
	    aportaciones_nuevo formulario = new aportaciones_nuevo();
	    formulario.cargarDetalles(detalles); // Método en `aportaciones_nuevo`
	    formulario.setVisible(true);
	    formulario.setLocationRelativeTo(null);

	    // Cerrar la ventana actual
	    dispose();
	}



    
    private void eliminarRegistro() {
        // Validar si hay una fila seleccionada
        int filaSeleccionada = tablaAportaciones.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener el mes y el año del registro seleccionado
        String mes = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        String anio = modeloTabla.getValueAt(filaSeleccionada, 1).toString();

        // Confirmación antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar todos los registros para el mes " + mes + " del año " + anio + "? Esta acción no se puede deshacer.", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return; // Si no confirma, no hace nada
        }

        // Llamar al método de consultas para eliminar los registros
        consultas_aportaciones consultas = new consultas_aportaciones();
        boolean exito = consultas.eliminarAportacionesPorMesYAño(mes, anio);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Los registros se eliminaron correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            modeloTabla.removeRow(filaSeleccionada);
            cargarDatos();
            
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al intentar eliminar los registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void inicializarCombosBusqueda() {
        // Agregar meses al combo de búsqueda
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        cbxbusquedaMes.addItem(""); // Elemento en blanco para mostrar todos los registros
        for (String mes : meses) {
            cbxbusquedaMes.addItem(mes);
        }

        // Agregar años al combo de búsqueda
        int anioActual = java.time.LocalDate.now().getYear();
        for (int i = anioActual - 2; i <= anioActual + 10; i++) {
            cbxbusquedaAnio.addItem(String.valueOf(i));
        }

        // Establecer valores por defecto
        cbxbusquedaAnio.setSelectedItem(String.valueOf(anioActual));
        cbxbusquedaMes.setSelectedIndex(0); // Selecciona el elemento en blanco
    }


    
    
    private void cargarDatosPorMesYAño(String mes, String anio) {
        consultas_aportaciones consultas = new consultas_aportaciones();
        List<apotaciones> registros = consultas.obtenerAportacionesPorMesYAño(mes, anio);

        // Limpia la tabla antes de cargar nuevos datos
        modeloTabla.setRowCount(0);

        if (registros == null || registros.isEmpty()) {
            lblresultado_busqueda.setText("Resultados: 0");
            JOptionPane.showMessageDialog(this, "No se encontraron registros para los criterios seleccionados.", 
                                          "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Agregar los registros a la tabla
        int contador = 1;
        for (apotaciones registro : registros) {
            modeloTabla.addRow(new Object[]{
                contador++,                // Número
                registro.getAño(),         // Año
                registro.getMes(),         // Mes
                registro.getFecha_registro() // Fecha de creación
            });
        }

        lblresultado_busqueda.setText("Resultados: " + registros.size());
    }




    
   
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}

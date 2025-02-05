package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.socios;
import conexion.conexion;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_socios;
import principal.menu_principal;

@SuppressWarnings({ "deprecation", "serial" })
public class socios_tabla extends JFrame {

    public TableRowSorter<TableModel> trsfiltroCodigo;
    String filtroCodigo;
    socios clase_empleado = new socios();
    public JLabel lblCargo;
    public DefaultTableModel tableModel;
    //public JTable tableEmpleados;
    public conexion dbConnection;
    public JTextField txtb;
    public JPanel contentPane;
    public JTable table;
    public JScrollPane scrollPane;
    public JButton btnNuevoEmpleado;
    public JButton btnMenu;
    public JComboBox<String> cbxbusquedaCargo;
    public JComboBox<String> cbxbusquedaArea;
    public JComboBox<String> cbxbusquedaSexo;
    public JButton btneliminar;
    public JPanel panelbusqueda;
    private final String placeHolderText = "Nombres, apellidos, identidad, estado civil y teléfono"; 
    public JLabel lblresultado_busqueda;

    ImageIcon icono_fotografia = new ImageIcon(getClass().getResource("/imagenes/logoTC.jpeg"));

    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public socios_tabla() {
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

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        contentPane.setBounds(0, 0, 1036, 724);
        getContentPane().add(contentPane);

        panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 79, 985, 46);
        contentPane.add(panelbusqueda);

        txtb = new JTextField();
        txtb.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtb.setColumns(10);
        txtb.setBounds(68, 10, 271, 27);
        panelbusqueda.add(txtb);

        InputMap map = txtb.getInputMap(JComponent.WHEN_FOCUSED); 
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "null");
        txtb.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtb.getText().length() == 50)
                    ke.consume();

                if (txtb.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "No está permitido ingresar espacios vacíos");
                    txtb.setText("");
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

        cbxbusquedaCargo = new JComboBox<>();
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaCargo.setBounds(438, 9, 136, 26);
        cbxbusquedaCargo.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedaCargo);

        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(379, 7, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaArea = new JComboBox<>();
        cbxbusquedaArea.setModel(new DefaultComboBoxModel(new String[] {"", " "}));
        cbxbusquedaArea.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaArea.setBounds(638, 9, 136, 26);
        cbxbusquedaArea.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedaArea);

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(584, 7, 56, 26);
        panelbusqueda.add(lblarea);

        cbxbusquedaSexo = new JComboBox<>();
        cbxbusquedaSexo.setModel(new DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "Otro", " " }));
        cbxbusquedaSexo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaSexo.setBounds(839, 11, 136, 26);
        cbxbusquedaSexo.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedaSexo);

        JLabel lblsexo = new JLabel("Sexo");
        lblsexo.setHorizontalAlignment(SwingConstants.LEFT);
        lblsexo.setForeground(Color.BLACK);
        lblsexo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblsexo.setBounds(784, 7, 56, 26);
        panelbusqueda.add(lblsexo);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(25, 132, 990, 440);
        contentPane.add(panel_1);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_1.add(scrollPane);
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(744, 390, 222, 27);
        panel_1.add(lblresultado_busqueda);

        JLabel lbltitulo = new JLabel("SOCIOS REGISTRADOS");
        lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbltitulo.setBounds(28, 32, 514, 26);
        contentPane.add(lbltitulo);

        JPanel panelbotones = new JPanel();
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 23, 458, 56);
        contentPane.add(panelbotones);
        panelbotones.setLayout(null);
       

        btnMenu = new JButton("Menú");
        btnMenu.setBackground(UIManager.getColor("Button.highlight"));
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnMenu.setToolTipText("Regresar al menú principal");
        btnMenu.setBounds(10, 17, 90, 23);
        panelbotones.add(btnMenu);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu_principal menu = new menu_principal();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                dispose();
            }
        });

        btnNuevoEmpleado = new JButton("Nuevo");
        btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
        btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevoEmpleado.setBounds(358, 17, 90, 23);
        panelbotones.add(btnNuevoEmpleado);
        btnNuevoEmpleado.setIcon(null);
        btnNuevoEmpleado.setToolTipText("Nuevo registro");
        btnNuevoEmpleado.addActionListener(new ActionListener() {
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
        
        btneliminar = new JButton("Eliminar");
        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.setToolTipText("Imprimir registro");
        btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnImprimir.setBackground(UIManager.getColor("Button.highlight"));
        btnImprimir.setBounds(163, 18, 90, 23);
        panelbotones.add(btnImprimir);
        btneliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    int filaSeleccionada;
        	    try {
        	      
        	        filaSeleccionada = table.getSelectedRow();
        	        if (filaSeleccionada == -1) {
        	        		JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para continuar", 
        	        				"Advertencia", JOptionPane.WARNING_MESSAGE);
                        } else {
        	            
        	            int confirmacion = JOptionPane.showConfirmDialog(null, 
        	                    "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos", 
        	                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        	            
        	            if (confirmacion == JOptionPane.YES_OPTION) {
        	                
        	                String id = table.getValueAt(filaSeleccionada, 0).toString();

        	                consultas_socios consulta = new consultas_socios();
        	                
        	                if (consulta.eliminar_empleado(Integer.parseInt(id))) {
        	                   
        	                    ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
        	                    JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos", 
        	                    		"Éxito", JOptionPane.INFORMATION_MESSAGE );
        	                } else {
        	                    
        	                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro de la base de datos.", 
        	                    		"Error", JOptionPane.ERROR_MESSAGE);
        	                }
        	            } 
        	        }
        	    } catch (HeadlessException ex) {
        	        JOptionPane.showMessageDialog(null, "Error, inténtelo nuevamente", 
        	        		"Error en la operación", JOptionPane.ERROR_MESSAGE);
        	    }
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

        cbxbusquedaSexo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        
        // Configuración del placeholder
        txtb.setText(placeHolderText);
        txtb.setForeground(Color.GRAY);

        txtb.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtb.getText().equals(placeHolderText)) {
                    txtb.setText("");
                    txtb.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtb.getText().isEmpty()) {
                    txtb.setForeground(Color.GRAY);
                    txtb.setText(placeHolderText);
                }
            }
        });
        
    }//class

    private void actualizarConteoRegistros() {
        int registrosVisibles = table.getRowCount(); // Obtiene el número de filas visibles en la tabla
        lblresultado_busqueda.setText("Registros: " + registrosVisibles);
    }

  
    
    public void construirTabla() {
        // Definir títulos de las columnas
        String titulos[] = { 
            "No.", "Código", "Identidad", "Nombres", "Apellidos", "Sexo", "Nacimiento", 
            "Estado civil", "Dirección", "Teléfono", "Correo", "Cargo", "Área", 
            "Inicio", "Renuncia", "Fotografía", "No.cuenta" 
        };

        String informacion[][] = obtenerMatriz(); // Método debe estar implementado en esta clase

        // Crear modelo de tabla no editable
        DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Deshabilitar edición de celdas
            }
        };

        // Inicializar la tabla con el modelo
        table = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Celdas no editables
            }
        };

        // Configurar propiedades de la tabla
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(255, 255, 0));
        table.getTableHeader().setForeground(Color.BLACK);

        // Agregar la tabla al JScrollPane
        scrollPane.setViewportView(table); // scrollPane debe estar inicializado en la clase

        // Configurar sorter para el filtro de búsqueda
        trsfiltroCodigo = new TableRowSorter<>(table.getModel());
        table.setRowSorter(trsfiltroCodigo);

        // Ajustar el ancho de las columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(30); // Número
        table.getColumnModel().getColumn(1).setPreferredWidth(40); // ID

        // Formato para las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        
        actualizarConteoRegistros();

        // Agregar evento de doble clic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic
                    int filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada != -1) { // Validar que hay una fila seleccionada
                        int fila = table.convertRowIndexToModel(filaSeleccionada); // Convertir índice visual a modelo

                        try {
                            // Obtener datos de la fila seleccionada
                            String idOriginalStr = String.valueOf(table.getModel().getValueAt(fila, 0));
                            String idEmpleado = String.valueOf(table.getModel().getValueAt(fila, 1));
                            String identidad = String.valueOf(table.getModel().getValueAt(fila, 2));
                            String nombres = String.valueOf(table.getModel().getValueAt(fila, 3));
                            String apellidos = String.valueOf(table.getModel().getValueAt(fila, 4));
                            String sexo = String.valueOf(table.getModel().getValueAt(fila, 5));

                            Date fechaNacimiento = null;
                            Date fechaInicio = null;
                            Date fechaRenuncia = null;

                            try {
                                // Parsear fechas
                                fechaNacimiento = dateFormat.parse(String.valueOf(table.getModel().getValueAt(fila, 6)));
                                fechaInicio = dateFormat.parse(String.valueOf(table.getModel().getValueAt(fila, 13)));
                                String fechaRenunciaStr = String.valueOf(table.getModel().getValueAt(fila, 14));
                                if (!fechaRenunciaStr.isEmpty() && !fechaRenunciaStr.equals("null")) {
                                    fechaRenuncia = dateFormat.parse(fechaRenunciaStr);
                                }
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(null, "Error al procesar las fechas", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Obtener otros datos
                            String estadoCivil = String.valueOf(table.getModel().getValueAt(fila, 7));
                            String direccion = String.valueOf(table.getModel().getValueAt(fila, 8));
                            String telefono = String.valueOf(table.getModel().getValueAt(fila, 9));
                            String correo = String.valueOf(table.getModel().getValueAt(fila, 10));
                            String cargo = String.valueOf(table.getModel().getValueAt(fila, 11));
                            String area = String.valueOf(table.getModel().getValueAt(fila, 12));
                            String fotografia = String.valueOf(table.getModel().getValueAt(fila, 15));
                            String cuenta = String.valueOf(table.getModel().getValueAt(fila, 16));

                            // Enviar datos a socios_nuevo
                            socios_nuevo ventanaNuevo = new socios_nuevo();
                            ventanaNuevo.ver_empleado(idEmpleado, identidad, nombres, apellidos, sexo, fechaNacimiento, estadoCivil,
                                    direccion, telefono, correo, cargo, area, fechaInicio, fechaRenuncia, fotografia, cuenta);

                            ventanaNuevo.txtidOriginal.setText(idOriginalStr);
                            ventanaNuevo.setLocationRelativeTo(null);

                            // Configurar botones en socios_nuevo
                            ventanaNuevo.btnguardar.setVisible(false);
                            ventanaNuevo.btnactualizar.setVisible(false);
                            ventanaNuevo.btnlimpiar.setVisible(false);

                            dispose(); // Cerrar la ventana actual
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al procesar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    
   
    private void aplicarFiltros() {
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea = (String) cbxbusquedaArea.getSelectedItem();
        String filtroSexo = (String) cbxbusquedaSexo.getSelectedItem();

        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 11)); 
        }

        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 12)); 
        }

        if (filtroSexo != null && !filtroSexo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroSexo, 5));
        }

        if (filtros.isEmpty()) {
            trsfiltroCodigo.setRowFilter(null); 
        } else {
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
            trsfiltroCodigo.setRowFilter(combinedFilter);
        }

        // Actualizar el conteo de registros visibles
        actualizarConteoRegistros();
    }

    
    

    public static String[][] obtenerMatriz() {
        ArrayList<socios> miLista = buscarUsuariosConMatriz();
        String matrizInfo[][] = new String[miLista.size()][17];

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0; i < miLista.size(); i++) {
        	matrizInfo[i][0] = miLista.get(i).getId() + "";
            matrizInfo[i][1] = miLista.get(i).getId_empleado() + "";
            matrizInfo[i][2] = miLista.get(i).getIdentidad_empleado() + "";
            matrizInfo[i][3] = miLista.get(i).getNombres_empleado() + "";
            matrizInfo[i][4] = miLista.get(i).getApellidos_empleado() + "";
            matrizInfo[i][5] = miLista.get(i).getSexo_empleado() + "";

            Date nacimiento = miLista.get(i).getNacimiento_empleado();
            matrizInfo[i][6] = outputFormat.format(nacimiento); 

            matrizInfo[i][7] = miLista.get(i).getCivil_empleado() + "";
            matrizInfo[i][8] = miLista.get(i).getDireccion_empleado() + "";
            matrizInfo[i][9] = miLista.get(i).getTel_empleado() + "";
            matrizInfo[i][10] = miLista.get(i).getCorreo_empleado() + "";
            matrizInfo[i][11] = miLista.get(i).getCargo_empleado() + "";
            matrizInfo[i][12] = miLista.get(i).getArea_empleado() + "";

            Date inicio = miLista.get(i).getInicio_empleado();
            matrizInfo[i][13] = outputFormat.format(inicio); 

            Date renuncia = miLista.get(i).getRenuncia_empleado();
            if (renuncia != null) {
                matrizInfo[i][14] = outputFormat.format(renuncia); 
            } else {
                matrizInfo[i][14] = ""; 
            }

            matrizInfo[i][15] = miLista.get(i).getFotografia_empleado() + "";
            matrizInfo[i][16] = miLista.get(i).getCuenta_empleado() + "";
        }
        return matrizInfo;
    }


    public static ArrayList<socios> buscarUsuariosConMatriz() {
        conexion conex = new conexion();
        ArrayList<socios> miLista = new ArrayList<>();
        try {
            Statement estatuto = conex.conectar().createStatement();
            ResultSet rs = estatuto.executeQuery("select * from socios");

            while (rs.next()) {
                socios empleado = new socios();
                empleado.setId(Integer.parseInt(rs.getString("id")));
                empleado.setId_empleado(rs.getString("id_empleado"));
                empleado.setIdentidad_empleado(rs.getString("identidad_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                empleado.setSexo_empleado(rs.getString("sexo_empleado"));
                empleado.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                empleado.setCivil_empleado(rs.getString("civil_empleado"));
                empleado.setDireccion_empleado(rs.getString("direccion_empleado"));
                empleado.setTel_empleado(rs.getString("tel_empleado"));
                empleado.setCorreo_empleado(rs.getString("correo_empleado"));
                empleado.setCargo_empleado(rs.getString("cargo_empleado"));
                empleado.setArea_empleado(rs.getString("area_empleado"));
                empleado.setInicio_empleado(rs.getDate("inicio_empleado"));
                empleado.setRenuncia_empleado(rs.getDate("renuncia_empleado"));
                empleado.setFotografia_empleado(rs.getString("fotografia_empleado"));
                empleado.setCuenta_empleado(rs.getString("cuenta_empleado"));
                miLista.add(empleado);
            }
            rs.close();
            estatuto.close();
            conex.desconectar(null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return miLista;
    }

    public void filtro() {
        filtroCodigo = txtb.getText();
        trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 2, 3, 4, 7, 9));

        // Actualizar el conteo de registros visibles
        actualizarConteoRegistros();
    }
    

    private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
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
}//end

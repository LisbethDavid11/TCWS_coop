package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.cargos;
import consultas.consultas_cargos;
import principal.menu_principal;

import javax.swing.JPanel;
import java.awt.SystemColor;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

@SuppressWarnings({ "serial", "unused" })
public class cargos_tabla extends JFrame{
	
	public TableRowSorter<TableModel> trsfiltroCodigo;
	String filtroCodigo;
	public JTextField txtb;
	public JTable tablaCargos; 
	public JScrollPane scrollPane;
	private final String placeHolderText = "Nombre del cargo y fecha";
	public JLabel lblresultado_busqueda;
	
	
	
	
	@SuppressWarnings("deprecation")
	public cargos_tabla() {
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
		
		JLabel lbltitulo = new JLabel("CARGOS REGISTRADOS");
		lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lbltitulo.setBounds(10, 25, 360, 24);
		getContentPane().add(lbltitulo);
		
		JPanel panelbotones = new JPanel();
		panelbotones.setLayout(null);
		panelbotones.setBackground(SystemColor.menu);
		panelbotones.setBounds(418, 10, 608, 56);
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
		btnMenu.setBounds(32, 23, 90, 23);
		panelbotones.add(btnMenu);
		
		JButton btnNuevoEmpleado = new JButton("Nuevo");
		btnNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_nuevo nuevo = new cargos_nuevo();
				nuevo.setVisible(true);
                nuevo.setLocationRelativeTo(null);
                nuevo.btnactualizar.setVisible(false);
                nuevo.chxeditar.setVisible(false);
                dispose();
			}
		});
		btnNuevoEmpleado.setToolTipText("Nuevo registro");
		btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
		btnNuevoEmpleado.setBounds(497, 23, 90, 23);
		panelbotones.add(btnNuevoEmpleado);
		
		JButton btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar_cargos();
			}
		});
		btneliminar.setToolTipText("Eliminar registro");
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setBackground(UIManager.getColor("Button.highlight"));
		btneliminar.setBounds(402, 23, 90, 23);
		panelbotones.add(btneliminar);
		
		JPanel panelbusqueda = new JPanel();
		panelbusqueda.setLayout(null);
		panelbusqueda.setBackground(SystemColor.menu);
		panelbusqueda.setBounds(10, 66, 1016, 46);
		getContentPane().add(panelbusqueda);
		
		txtb = new JTextField();
		txtb.setForeground(Color.GRAY);
		txtb.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtb.setColumns(10);
		txtb.setBounds(68, 10, 311, 27);
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
                filtro(); 
            }
        });
		
		JLabel lblbuscar = new JLabel("Buscar");;
		lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblbuscar.setForeground(Color.BLACK);
		lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblbuscar.setBounds(10, 10, 66, 26);
		panelbusqueda.add(lblbuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(10, 118, 1016, 452);
		getContentPane().add(panel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 996, 370);
		panel_1.add(scrollPane);
		
		lblresultado_busqueda = new JLabel("");
		lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblresultado_busqueda.setBounds(785, 402, 210, 27);
		panel_1.add(lblresultado_busqueda);
		
		
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
        
		
		
	}
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	public void construirTabla() {
	    String titulos[] = { "No.", "Nombre del cargo", "Fecha de creación" };
	    String informacion[][] = obtenerMatrizCargos();

	    DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; 
	        }
	    };

	    tablaCargos = new JTable(modeloTabla);
	    scrollPane.setViewportView(tablaCargos);
	    trsfiltroCodigo = new TableRowSorter<>(modeloTabla); 
	    
	    // Configurar propiedades de la tabla
	    tablaCargos.setRowHeight(25);
        tablaCargos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaCargos.getTableHeader().setOpaque(false);
        tablaCargos.getTableHeader().setBackground(new Color(255, 255, 0));
        tablaCargos.getTableHeader().setForeground(Color.BLACK);
	    
	    tablaCargos.setRowSorter(trsfiltroCodigo);
	    tablaCargos.getColumnModel().getColumn(0).setPreferredWidth(20);
	    tablaCargos.getColumnModel().getColumn(1).setPreferredWidth(50);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    
	    actualizarConteoRegistros();

	    tablaCargos.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) { 
	                int filaSeleccionada = tablaCargos.getSelectedRow();
	                if (filaSeleccionada != -1) { 
	                    int fila = tablaCargos.convertRowIndexToModel(filaSeleccionada); 

	                    try {
	                        String idCargoStr = String.valueOf(tablaCargos.getModel().getValueAt(fila, 0)); 
	                        String nombreCargo = String.valueOf(tablaCargos.getModel().getValueAt(fila, 1)); 
	                        String fechaCreacionStr = String.valueOf(tablaCargos.getModel().getValueAt(fila, 2)); 

	                        // Parseo de la fecha de creación
	                        Date fechaCreacion = dateFormat.parse(fechaCreacionStr);

	                        cargos_nuevo ventanaCargoNuevo = new cargos_nuevo();
	                        ventanaCargoNuevo.txtid.setText(idCargoStr);
	                        ventanaCargoNuevo.txtcargo.setText(nombreCargo);
	                        ventanaCargoNuevo.txtfecha.setText(dateFormat.format(fechaCreacion));
	                        ventanaCargoNuevo.habilitarCampos(false);
	                        ventanaCargoNuevo.btnguardar.setVisible(false);
	                        ventanaCargoNuevo.btnactualizar.setVisible(false);
	                        ventanaCargoNuevo.btnlimpiar.setVisible(false);
	                        ventanaCargoNuevo.setLocationRelativeTo(null);
	                        ventanaCargoNuevo.setVisible(true);
	                        dispose(); 
	                    } catch (ParseException ex) {
	                        JOptionPane.showMessageDialog(null, "Error al procesar los datos", "Error", JOptionPane.ERROR_MESSAGE);
	                        ex.printStackTrace();
	                    }
	                }
	            }
	        }
	    });
	}


    private String[][] obtenerMatrizCargos() {
        List<String[]> listaCargos = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String sql = "SELECT id_cargos, cargos, fecha_creacion FROM cargos";
        Connection con = new consultas_cargos().conectar();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] cargo = new String[3];
                cargo[0] = String.valueOf(rs.getInt("id_cargos")); 
                cargo[1] = rs.getString("cargos"); 
                cargo[2] = dateFormat.format(rs.getDate("fecha_creacion")); 

                listaCargos.add(cargo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close(); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String[][] matrizCargos = new String[listaCargos.size()][3];
        for (int i = 0; i < listaCargos.size(); i++) {
            matrizCargos[i] = listaCargos.get(i);
        }

        return matrizCargos;
    }
    
    
    
    
    private void actualizarConteoRegistros() {
	    int registrosVisibles = tablaCargos.getRowCount(); // Obtiene el número de filas visibles en la tabla
	    lblresultado_busqueda.setText("Registros: " + registrosVisibles);
	}
    
    
    

    public void filtro() {
        filtroCodigo = txtb.getText();
        trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 1,2));
        actualizarConteoRegistros();
    }
    
    
    
    
	
    
    public void eliminar_cargos() {
        int filaSeleccionada = tablaCargos.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fila para continuar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar el registro seleccionado?"
        		+ "\nEsto también lo eliminará permanentemente de la base de datos", 
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        int filaModelo = tablaCargos.convertRowIndexToModel(filaSeleccionada); 
        String idCargoStr = String.valueOf(tablaCargos.getModel().getValueAt(filaModelo, 0)); 

        try {
            int idCargo = Integer.parseInt(idCargoStr);
            consultas_cargos consultas = new consultas_cargos();

            if (consultas.eliminarCargo(idCargo)) {
                JOptionPane.showMessageDialog(this, "El cargo se ha eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                construirTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el cargo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: El Id del cargo debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar la eliminación", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


	
	

}

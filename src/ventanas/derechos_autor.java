package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import principal.menu_principal;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class derechos_autor extends JFrame{
	public derechos_autor() {
		
		setResizable(false);
		getContentPane().setBackground(SystemColor.text);
		
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1050, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(33, 66, 964, 482);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDerechosDeAutor = new JLabel("Derechos de autor");
		lblDerechosDeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDerechosDeAutor.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblDerechosDeAutor.setBounds(10, 44, 933, 41);
		panel.add(lblDerechosDeAutor);
		
		JTextArea txtrCopyright = new JTextArea();
		txtrCopyright.setToolTipText("");
		txtrCopyright.setBackground(SystemColor.control);
		txtrCopyright.setEditable(false);
		txtrCopyright.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		txtrCopyright.setText("Copyright © 2024 Sistema Control de Empleados, Permisos, Incapacidades y Vacaciones. Todos los derechos reservados.\r\n\r\nEste software y su documentación están protegidos por las leyes de derechos de autor y otras leyes internacionales de \r\npropiedad intelectual. \r\n\r\nEl uso no autorizado de este software está estrictamente prohibido.\r\n \r\nNo se permite la modificación, descompilación, distribución no autorizada, ingeniería inversa ni el uso comercial sin el \r\nconsentimiento previo por escrito del titular del copyright.\r\n\r\nEl nombre y logotipo de Sistema Control de Empleados, Permisos e Incapacidades son marcas registradas y están \r\nprotegidos por la ley.\r\n\r\nPara consultas sobre licencias o permisos de uso, comuníquese con: lisbethdavid711@gmail.com || +504 3385-8855\r\n");
		txtrCopyright.setBounds(73, 111, 821, 326);
		panel.add(txtrCopyright);
		
		JButton btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_principal principal= new menu_principal();
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(907, 20, 90, 23);
		getContentPane().add(btnregresar);
	}
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}

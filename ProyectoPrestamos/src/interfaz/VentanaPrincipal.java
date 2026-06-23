package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame framePrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.framePrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		framePrincipal = new JFrame();
		framePrincipal.setBounds(100, 100, 667, 445);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		framePrincipal.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelUsuario = new JPanel();
		tabbedPane.addTab("Usuario", null, panelUsuario, null);
		panelUsuario.setLayout(null);
		
		JButton btnCrearUsuario = new JButton("Nuevo");
		btnCrearUsuario.setBounds(10, 10, 84, 20);
		panelUsuario.add(btnCrearUsuario);
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.setBounds(104, 10, 84, 20);
		panelUsuario.add(btnModificarUsuario);
		
		JButton btnBorrarUsuario = new JButton("Borrar");
		btnBorrarUsuario.setBounds(198, 10, 84, 20);
		panelUsuario.add(btnBorrarUsuario);
		
		JButton btnConsultarUsuario = new JButton("Consultar");
		btnConsultarUsuario.setBounds(292, 10, 84, 20);
		panelUsuario.add(btnConsultarUsuario);
		
		JPanel panelPrestamo = new JPanel();
		tabbedPane.addTab("Prestamo", null, panelPrestamo, null);
		
		JPanel panelItems = new JPanel();
		tabbedPane.addTab("Items", null, panelItems, null);
		panelItems.setLayout(null);
		
		JButton btnCrearItem = new JButton("Nuevo");
		btnCrearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearItem.setBounds(10, 10, 84, 20);
		panelItems.add(btnCrearItem);
		
		JButton btnModificarItem = new JButton("Modificar");
		btnModificarItem.setBounds(104, 10, 84, 20);
		panelItems.add(btnModificarItem);
		
		JButton btnBorrarItem = new JButton("Borrar");
		btnBorrarItem.setBounds(198, 10, 84, 20);
		panelItems.add(btnBorrarItem);
		
		JButton btnConsultarItem = new JButton("Consultar");
		btnConsultarItem.setBounds(292, 10, 84, 20);
		panelItems.add(btnConsultarItem);
	}
}

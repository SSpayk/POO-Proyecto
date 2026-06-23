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
		
		JPanel panelAdministracion = new JPanel();
		tabbedPane.addTab("Administracion", null, panelAdministracion, null);
		panelAdministracion.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panelAdministracion.add(tabbedPane_1, BorderLayout.CENTER);
		
		JPanel panelUsuarios = new JPanel();
		tabbedPane_1.addTab("Usuarios", null, panelUsuarios, null);
		panelUsuarios.setLayout(null);
		
		JButton btnCrearPersona = new JButton("Nuevo");
		btnCrearPersona.setBounds(10, 10, 84, 20);
		panelUsuarios.add(btnCrearPersona);
		
		JButton btnModificarPersona = new JButton("Modificar");
		btnModificarPersona.setBounds(104, 10, 84, 20);
		panelUsuarios.add(btnModificarPersona);
		
		JButton btnBorrarPersona = new JButton("Borrar");
		btnBorrarPersona.setBounds(198, 10, 84, 20);
		panelUsuarios.add(btnBorrarPersona);
		
		JButton btnConsultarPersona = new JButton("Consultar");
		btnConsultarPersona.setBounds(292, 10, 84, 20);
		panelUsuarios.add(btnConsultarPersona);
		
		JPanel panelItems = new JPanel();
		tabbedPane_1.addTab("Items", null, panelItems, null);
		panelItems.setLayout(null);
		
		JButton btnCrearItem = new JButton("Nuevo");
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
		
		JPanel panelCategorias = new JPanel();
		tabbedPane_1.addTab("Categorias", null, panelCategorias, null);
		panelCategorias.setLayout(null);
		
		JButton btnCrearCategoria = new JButton("Nueva");
		btnCrearCategoria.setBounds(10, 10, 84, 20);
		panelCategorias.add(btnCrearCategoria);
		
		JButton btnModificarCategoria = new JButton("Modificar");
		btnModificarCategoria.setBounds(104, 10, 84, 20);
		panelCategorias.add(btnModificarCategoria);
		
		JButton btnBorrarCategoria = new JButton("Borrar");
		btnBorrarCategoria.setBounds(198, 10, 84, 20);
		panelCategorias.add(btnBorrarCategoria);
		
		JButton btnConsultarCategoria = new JButton("Consultar");
		btnConsultarCategoria.setBounds(292, 10, 84, 20);
		panelCategorias.add(btnConsultarCategoria);
		
		JPanel panelTipos = new JPanel();
		tabbedPane_1.addTab("Tipos", null, panelTipos, null);
		panelTipos.setLayout(null);
		
		JButton btnCrearTipo = new JButton("Nuevo");
		btnCrearTipo.setBounds(10, 10, 84, 20);
		panelTipos.add(btnCrearTipo);
		
		JButton btnModificarTipo = new JButton("Modificar");
		btnModificarTipo.setBounds(104, 10, 84, 20);
		panelTipos.add(btnModificarTipo);
		
		JButton btnBorrarTipo = new JButton("Borrar");
		btnBorrarTipo.setBounds(198, 10, 84, 20);
		panelTipos.add(btnBorrarTipo);
		
		JButton btnConsultarTipo = new JButton("Consultar");
		btnConsultarTipo.setBounds(292, 10, 84, 20);
		panelTipos.add(btnConsultarTipo);
		
		JPanel panelPrestamo = new JPanel();
		tabbedPane.addTab("Prestamo", null, panelPrestamo, null);
		
		JPanel panelReportes = new JPanel();
		tabbedPane.addTab("Reportes", null, panelReportes, null);
		panelReportes.setLayout(null);
		
		JButton btnReporteUsuario = new JButton("Por Usuario");
		btnReporteUsuario.setBounds(10, 10, 91, 20);
		panelReportes.add(btnReporteUsuario);
		
		JButton btnReporteItem = new JButton("Por Item");
		btnReporteItem.setBounds(104, 10, 91, 20);
		panelReportes.add(btnReporteItem);
		
		JButton btnReporteCategoria = new JButton("Por Categoria");
		btnReporteCategoria.setBounds(198, 10, 91, 20);
		panelReportes.add(btnReporteCategoria);
		
		JButton btnReporteTipo = new JButton("Por Tipo");
		btnReporteTipo.setBounds(292, 10, 84, 20);
		panelReportes.add(btnReporteTipo);
	}
}

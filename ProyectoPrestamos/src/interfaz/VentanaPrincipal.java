package interfaz;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import control.Controladora;
import logica.Categoria;
import logica.Item;
import logica.Tipo;
import logica.Usuario;

public class VentanaPrincipal {

	private JFrame framePrincipal;
	private JTable tablaUsuarios;
	private JTable tablaItems;
	private JTable tablaCategorias;
	private JTable tablaTipos;

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
	
	private void cargarUsuarios() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
		model.setRowCount(0);
		List<Usuario> listaUsuarios = control.listaUsuario();
		for (Usuario usuario : listaUsuarios) {
			Object[] fila = new Object[] {usuario.getNombre(),usuario.getNumero(),usuario.getCorreo()};
			model.addRow(fila);
		}
	}
	
	private void cargarItems() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
		model.setRowCount(0);
		List<Item> listaItems = control.listaItems();
		for (Item items : listaItems) {
			Object[] fila = new Object[] {items.getCodigo(),items.getNombre(),items.getDescripcion()};
			model.addRow(fila);
		}
	}
	
	private void cargarTipo() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaTipos.getModel();
		model.setRowCount(0);
		List<Tipo> listaTipos = control.listaTipos();
		for (Tipo tipos : listaTipos) {
			Object[] fila = new Object[] {tipos.getNombre()};
			model.addRow(fila);
		}
	}
	
	private void cargarCategoria() {
		Controladora control = Controladora.getInstance();
		DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
		model.setRowCount(0);
		List<Categoria> listaCategorias = control.listaCategorias();
		for (Categoria categorias : listaCategorias) {
			Object[] fila = new Object[] {categorias.getNombre()};
			model.addRow(fila);
		}
	}
	
	private void crearUsuario() {
		JTextField campoNombre = new JTextField();
		JTextField campoNumero = new JTextField();
		JTextField campoCorreo = new JTextField();
		
		JPanel panel = new JPanel(new GridLayout(3,2,5,10));
		panel.add(new JLabel("Nombre:"));
		panel.add(campoNombre);
		panel.add(new JLabel("Numero"));
		panel.add(campoNumero);
		panel.add(new JLabel("Correo:"));
		panel.add(campoCorreo);
		
		int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel,"Crear Usuario", JOptionPane.OK_CANCEL_OPTION);
		if (respuesta == JOptionPane.OK_OPTION) {
			String nombre = campoNombre.getText().trim();
			String numero = campoNumero.getText().trim();
			String correo = campoCorreo.getText().trim();
			
			Controladora control = Controladora.getInstance();
			control.crearUsuario(nombre, numero, correo);
			cargarUsuarios();
		}
	}
	
	private void modificarUsuario() {
		int fila = tablaUsuarios.getSelectedRow();
		if (fila ==-1) {
			JOptionPane.showMessageDialog(framePrincipal,"Debe seleccionar un usuario.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
		JTextField campoNombre = new JTextField((String) model.getValueAt(fila, 0));
		JTextField campoNumero = new JTextField((String) model.getValueAt(fila, 1));
		JTextField campoCorreo = new JTextField((String) model.getValueAt(fila, 0));
		
		JPanel panel = new JPanel(new GridLayout(3,2,5,10));
		panel.add(new JLabel("Nombre:"));
		panel.add(campoNombre);
		panel.add(new JLabel("Numero"));
		panel.add(campoNumero);
		panel.add(new JLabel("Correo:"));
		panel.add(campoCorreo);
		
		int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel,"Modificar Usuario", JOptionPane.OK_CANCEL_OPTION);
		if (respuesta == JOptionPane.OK_OPTION) {
			String nombre = campoNombre.getText().trim();
			String numero = campoNumero.getText().trim();
			String correo = campoCorreo.getText().trim();
			
			Controladora control = Controladora.getInstance();
			control.modificarUsuario(fila,nombre,numero,correo);
			cargarUsuarios();
		}
	}
	
	private void borrarUsuario() {
		int fila = tablaUsuarios.getSelectedRow();
		if (fila ==-1) {
			JOptionPane.showMessageDialog(framePrincipal,"Debe seleccionar un usuario.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		DefaultTableModel model = (DefaultTableModel) tablaUsuarios.getModel();
		String nombre = (String) model.getValueAt(fila, 0);
		
		int respuesta = JOptionPane.showConfirmDialog(framePrincipal,"Se eliminara al usuario "+nombre+".","Confirmar",JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			Controladora control = Controladora.getInstance();
			boolean borrado = control.borrarUsuario(fila);
			if (borrado) {
				cargarUsuarios();
			}else {
				JOptionPane.showMessageDialog(framePrincipal,"No se puede borrar al usuario porque tiene prestamos activos","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
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
		btnCrearPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearUsuario();
			}
		});
		btnCrearPersona.setBounds(10, 10, 84, 20);
		panelUsuarios.add(btnCrearPersona);
		
		JButton btnModificarPersona = new JButton("Modificar");
		btnModificarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarUsuario();
			}
		});
		btnModificarPersona.setBounds(104, 10, 84, 20);
		panelUsuarios.add(btnModificarPersona);
		
		JButton btnBorrarPersona = new JButton("Borrar");
		btnBorrarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarUsuario();
			}
		});
		btnBorrarPersona.setBounds(198, 10, 84, 20);
		panelUsuarios.add(btnBorrarPersona);
		
		JButton btnConsultarPersona = new JButton("Consultar");
		btnConsultarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarPersona.setBounds(292, 10, 84, 20);
		panelUsuarios.add(btnConsultarPersona);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 623, 304);
		panelUsuarios.add(scrollPane);
		
		tablaUsuarios = new JTable();
		tablaUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Numero Telefonico", "Correo Electronico"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(168);
		tablaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(202);
		tablaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(277);
		scrollPane.setViewportView(tablaUsuarios);
		
		JPanel panelItems = new JPanel();
		tabbedPane_1.addTab("Items", null, panelItems, null);
		panelItems.setLayout(null);
		
		JButton btnCrearItem = new JButton("Nuevo");
		btnCrearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearItem.setBounds(10, 10, 84, 20);
		panelItems.add(btnCrearItem);
		
		JButton btnModificarItem = new JButton("Modificar");
		btnModificarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarItem.setBounds(104, 10, 84, 20);
		panelItems.add(btnModificarItem);
		
		JButton btnBorrarItem = new JButton("Borrar");
		btnBorrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrarItem.setBounds(198, 10, 84, 20);
		panelItems.add(btnBorrarItem);
		
		JButton btnConsultarItem = new JButton("Consultar");
		btnConsultarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarItem.setBounds(292, 10, 84, 20);
		panelItems.add(btnConsultarItem);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 40, 623, 304);
		panelItems.add(scrollPane_1);
		
		tablaItems = new JTable();
		tablaItems.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombre", "Descripcion"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaItems.getColumnModel().getColumn(0).setPreferredWidth(59);
		tablaItems.getColumnModel().getColumn(1).setPreferredWidth(188);
		tablaItems.getColumnModel().getColumn(2).setPreferredWidth(407);
		scrollPane_1.setViewportView(tablaItems);
		
		JPanel panelCategorias = new JPanel();
		tabbedPane_1.addTab("Categorias", null, panelCategorias, null);
		panelCategorias.setLayout(null);
		
		JButton btnCrearCategoria = new JButton("Nueva");
		btnCrearCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearCategoria.setBounds(10, 10, 84, 20);
		panelCategorias.add(btnCrearCategoria);
		
		JButton btnModificarCategoria = new JButton("Modificar");
		btnModificarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarCategoria.setBounds(104, 10, 84, 20);
		panelCategorias.add(btnModificarCategoria);
		
		JButton btnBorrarCategoria = new JButton("Borrar");
		btnBorrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrarCategoria.setBounds(198, 10, 84, 20);
		panelCategorias.add(btnBorrarCategoria);
		
		JButton btnConsultarCategoria = new JButton("Consultar");
		btnConsultarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarCategoria.setBounds(292, 10, 84, 20);
		panelCategorias.add(btnConsultarCategoria);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 40, 623, 304);
		panelCategorias.add(scrollPane_2);
		
		tablaCategorias = new JTable();
		tablaCategorias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaCategorias.getColumnModel().getColumn(0).setPreferredWidth(623);
		scrollPane_2.setViewportView(tablaCategorias);
		
		JPanel panelTipos = new JPanel();
		tabbedPane_1.addTab("Tipos", null, panelTipos, null);
		panelTipos.setLayout(null);
		
		JButton btnCrearTipo = new JButton("Nuevo");
		btnCrearTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearTipo.setBounds(10, 10, 84, 20);
		panelTipos.add(btnCrearTipo);
		
		JButton btnModificarTipo = new JButton("Modificar");
		btnModificarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificarTipo.setBounds(104, 10, 84, 20);
		panelTipos.add(btnModificarTipo);
		
		JButton btnBorrarTipo = new JButton("Borrar");
		btnBorrarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBorrarTipo.setBounds(198, 10, 84, 20);
		panelTipos.add(btnBorrarTipo);
		
		JButton btnConsultarTipo = new JButton("Consultar");
		btnConsultarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarTipo.setBounds(292, 10, 84, 20);
		panelTipos.add(btnConsultarTipo);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 40, 623, 304);
		panelTipos.add(scrollPane_3);
		
		tablaTipos = new JTable();
		tablaTipos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaTipos.getColumnModel().getColumn(0).setPreferredWidth(632);
		scrollPane_3.setViewportView(tablaTipos);
		
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

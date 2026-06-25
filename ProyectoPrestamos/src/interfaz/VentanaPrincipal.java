package interfaz;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
	
	// Metodos para los usuarios
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
	
	private void consultarUsuario() {
		int fila = tablaUsuarios.getSelectedRow();
		if(fila == -1) {
			JOptionPane.showMessageDialog(framePrincipal,"Debe seleccionar un usuario.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		DetalleUsuario dialogo = new DetalleUsuario(framePrincipal,fila);
		dialogo.setVisible(true);
	}
	
	// Metodos para los items
	private void crearItem() {
		Controladora control = Controladora.getInstance();
		
		JTextField campoNombre = new JTextField();
		JTextField campoCodigo = new JTextField();
		JTextField campoDescripcion = new JTextField();
		
		List<Tipo> tipos = control.listaTipos();
		JComboBox<String> comboTipo = new JComboBox<String>();
		for (Tipo t : tipos) {
			comboTipo.addItem(t.getNombre());
		}
		
		List<Categoria> categorias = control.listaCategorias();
		DefaultListModel<String> modeloCategorias = new DefaultListModel<String>();
		for (Categoria c : categorias) {
			modeloCategorias.addElement(c.getNombre());
		}
		
		JList<String> listaCategorias = new JList<>(modeloCategorias);
		listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollCategorias = new JScrollPane(listaCategorias);
		scrollCategorias.setPreferredSize(new java.awt.Dimension(200,80));
		
		JPanel panel = new JPanel(new GridLayout(5,2,5,10));
		panel.add(new JLabel("Nombre:"));
		panel.add(campoNombre);
		panel.add(new JLabel("Codigo:"));
		panel.add(campoCodigo);
		panel.add(new JLabel("Descripcion:"));
		panel.add(campoDescripcion);
		panel.add(new JLabel("Tipo:"));
		panel.add(comboTipo);
		panel.add(new JLabel("Categorias:"));
		
		int respuesta = JOptionPane.showConfirmDialog(framePrincipal,panel,"Nuevo Item",JOptionPane.OK_CANCEL_OPTION);
		if (respuesta == JOptionPane.OK_OPTION) {
		    String nombre = campoNombre.getText().trim();
		    String descripcion = campoDescripcion.getText().trim();
		    int indiceTipo = comboTipo.getSelectedIndex();

		    try {
		        Integer codigo = Integer.parseInt(campoCodigo.getText().trim());
		        List<Categoria> categoriasSeleccionadas = new ArrayList<>();
		        for (int i : listaCategorias.getSelectedIndices()) {
		            categoriasSeleccionadas.add(categorias.get(i));
		        }

		        control.crearItem(nombre, codigo, descripcion, indiceTipo, categoriasSeleccionadas);
		        cargarItems();
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(framePrincipal, "El codigo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
		
	}
	
	private void modificarItem() {
	    int fila = tablaItems.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar un item.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    Controladora control = Controladora.getInstance();
	    Item item = control.consultarItem(fila);

	    JTextField campoNombre = new JTextField(item.getNombre());
	    JTextField campoCodigo = new JTextField(String.valueOf(item.getCodigo()));
	    JTextField campoDescripcion = new JTextField(item.getDescripcion());

	    
	    List<Tipo> tipos = control.listaTipos();
	    JComboBox<String> comboTipo = new JComboBox<>();
	    for (Tipo t : tipos) {
	        comboTipo.addItem(t.getNombre());
	    }
	    comboTipo.setSelectedIndex(tipos.indexOf(item.getTipo()));

	    
	    List<Categoria> categorias = control.listaCategorias();
	    DefaultListModel<String> modeloCategorias = new DefaultListModel<>();
	    for (Categoria c : categorias) {
	        modeloCategorias.addElement(c.getNombre());
	    }
	    JList<String> listaCategorias = new JList<>(modeloCategorias);
	    listaCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	    
	    List<Integer> indicesSeleccionados = new ArrayList<>();
	    for (Categoria c : item.getCategorias()) {
	        indicesSeleccionados.add(categorias.indexOf(c));
	    }
	    int[] indices = new int[indicesSeleccionados.size()];
	    for (int i = 0; i < indices.length; i++) {
	        indices[i] = indicesSeleccionados.get(i);
	    }
	    listaCategorias.setSelectedIndices(indices);

	    JScrollPane scrollCategorias = new JScrollPane(listaCategorias);
	    scrollCategorias.setPreferredSize(new java.awt.Dimension(200, 80));

	    JPanel panel = new JPanel(new GridLayout(5, 2, 5, 10));
	    panel.add(new JLabel("Nombre:"));
	    panel.add(campoNombre);
	    panel.add(new JLabel("Codigo:"));
	    panel.add(campoCodigo);
	    panel.add(new JLabel("Descripcion:"));
	    panel.add(campoDescripcion);
	    panel.add(new JLabel("Tipo:"));
	    panel.add(comboTipo);
	    panel.add(new JLabel("Categorias:"));
	    panel.add(scrollCategorias);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel, "Modificar Item", JOptionPane.OK_CANCEL_OPTION);
	    if (respuesta == JOptionPane.OK_OPTION) {
	        String nombre = campoNombre.getText().trim();
	        String descripcion = campoDescripcion.getText().trim();
	        int indiceTipo = comboTipo.getSelectedIndex();

	        try {
	            Integer codigo = Integer.parseInt(campoCodigo.getText().trim());

	            List<Categoria> categoriasSeleccionadas = new ArrayList<>();
	            for (int i : listaCategorias.getSelectedIndices()) {
	                categoriasSeleccionadas.add(categorias.get(i));
	            }

	            control.modificarItem(fila, nombre, codigo, descripcion, indiceTipo, categoriasSeleccionadas);
	            cargarItems();
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(framePrincipal, "El codigo debe ser un numero.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void borrarItem() {
	    int fila = tablaItems.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar un item.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
	    String nombre = (String) model.getValueAt(fila, 1);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal,"Se eliminara el item " + nombre + ".","Confirmar", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        Controladora control = Controladora.getInstance();
	        boolean borrado = control.borrarItem(fila);
	        if (borrado) {
	            cargarItems();
	        } else {
	            JOptionPane.showMessageDialog(framePrincipal,"No se puede borrar el item porque esta en un prestamo activo.","Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void consultarItem() {
	    int fila = tablaItems.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal,"Debe seleccionar un item.","Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    DetalleItem dialogo = new DetalleItem(framePrincipal, fila);
	    dialogo.setVisible(true);
	}
	
	//Metodos para Categoria
	private void crearCategoria() {
	    JTextField campoNombre = new JTextField();

	    JPanel panel = new JPanel(new GridLayout(1, 2, 5, 10));
	    panel.add(new JLabel("Nombre:"));
	    panel.add(campoNombre);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel, "Nueva Categoria", JOptionPane.OK_CANCEL_OPTION);
	    if (respuesta == JOptionPane.OK_OPTION) {
	        String nombre = campoNombre.getText().trim();
	        Controladora control = Controladora.getInstance();
	        control.crearCategoria(nombre);
	        cargarCategoria();
	    }
	}
	
	private void modificarCategoria() {
	    int fila = tablaCategorias.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar una categoria.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
	    JTextField campoNombre = new JTextField((String) model.getValueAt(fila, 0));

	    JPanel panel = new JPanel(new GridLayout(1, 2, 5, 10));
	    panel.add(new JLabel("Nombre:"));
	    panel.add(campoNombre);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel, "Modificar Categoria", JOptionPane.OK_CANCEL_OPTION);
	    if (respuesta == JOptionPane.OK_OPTION) {
	        String nombre = campoNombre.getText().trim();
	        Controladora control = Controladora.getInstance();
	        control.modificarCategoria(fila, nombre);
	        cargarCategoria();
	    }
	}
	
	private void borrarCategoria() {
	    int fila = tablaCategorias.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar una categoria.","Error",JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
	    String nombre = (String) model.getValueAt(fila, 0);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal,"Se eliminara la categoria " + nombre + ".","Confirmar", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        Controladora control = Controladora.getInstance();
	        control.borrarCategoria(fila);
	        cargarCategoria();
	    }
	}
	
	private void consultarCategoria() {
	    int fila = tablaCategorias.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar una categoria.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    DetalleCategoria dialogo = new DetalleCategoria(framePrincipal, fila);
	    dialogo.setVisible(true);
	}
	
	//Metodos para tipo
	private void crearTipo() {
	    JTextField campoNombre = new JTextField();

	    JPanel panel = new JPanel(new GridLayout(1, 2, 5, 10));
	    panel.add(new JLabel("Nombre:"));
	    panel.add(campoNombre);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel, "Nuevo Tipo", JOptionPane.OK_CANCEL_OPTION);
	    if (respuesta == JOptionPane.OK_OPTION) {
	        String nombre = campoNombre.getText().trim();
	        Controladora control = Controladora.getInstance();
	        control.crearTipo(nombre);
	        cargarTipo();
	    }
	}
	
	private void modificarTipo() {
	    int fila = tablaTipos.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal,"Debe seleccionar un tipo.","Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaTipos.getModel();
	    JTextField campoNombre = new JTextField((String) model.getValueAt(fila, 0));

	    JPanel panel = new JPanel(new GridLayout(1, 2, 5, 10));
	    panel.add(new JLabel("Nombre:"));
	    panel.add(campoNombre);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal, panel,"Modificar Tipo", JOptionPane.OK_CANCEL_OPTION);
	    if (respuesta == JOptionPane.OK_OPTION) {
	        String nombre = campoNombre.getText().trim();
	        Controladora control = Controladora.getInstance();
	        control.modificarTipo(fila, nombre);
	        cargarTipo();
	    }
	}
	
	private void borrarTipo() {
	    int fila = tablaTipos.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar un tipo.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    DefaultTableModel model = (DefaultTableModel) tablaTipos.getModel();
	    String nombre = (String) model.getValueAt(fila, 0);

	    int respuesta = JOptionPane.showConfirmDialog(framePrincipal,"Se eliminara el tipo " + nombre + " y sus items pasaran al tipo General.","Confirmar", JOptionPane.YES_NO_OPTION);
	    if (respuesta == JOptionPane.YES_OPTION) {
	        Controladora control = Controladora.getInstance();
	        boolean borrado = control.borrarTipo(fila);
	        if (borrado) {
	            cargarTipo();
	        } else {
	            JOptionPane.showMessageDialog(framePrincipal,"No se puede borrar el tipo General.","Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	}
	
	private void consultarTipo() {
	    int fila = tablaTipos.getSelectedRow();
	    if (fila == -1) {
	        JOptionPane.showMessageDialog(framePrincipal, "Debe seleccionar un tipo.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    DetalleTipo dialogo = new DetalleTipo(framePrincipal, fila);
	    dialogo.setVisible(true);
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
		framePrincipal.setResizable(false);
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
				consultarUsuario();
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
				crearItem();
			}
		});
		btnCrearItem.setBounds(10, 10, 84, 20);
		panelItems.add(btnCrearItem);
		
		JButton btnModificarItem = new JButton("Modificar");
		btnModificarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarItem();
			}
		});
		btnModificarItem.setBounds(104, 10, 84, 20);
		panelItems.add(btnModificarItem);
		
		JButton btnBorrarItem = new JButton("Borrar");
		btnBorrarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarItem();
			}
		});
		btnBorrarItem.setBounds(198, 10, 84, 20);
		panelItems.add(btnBorrarItem);
		
		JButton btnConsultarItem = new JButton("Consultar");
		btnConsultarItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarItem();
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
				crearCategoria();
			}
		});
		btnCrearCategoria.setBounds(10, 10, 84, 20);
		panelCategorias.add(btnCrearCategoria);
		
		JButton btnModificarCategoria = new JButton("Modificar");
		btnModificarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCategoria();
			}
		});
		btnModificarCategoria.setBounds(104, 10, 84, 20);
		panelCategorias.add(btnModificarCategoria);
		
		JButton btnBorrarCategoria = new JButton("Borrar");
		btnBorrarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarCategoria();
			}
		});
		btnBorrarCategoria.setBounds(198, 10, 84, 20);
		panelCategorias.add(btnBorrarCategoria);
		
		JButton btnConsultarCategoria = new JButton("Consultar");
		btnConsultarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarCategoria();
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
				crearTipo();
			}
		});
		btnCrearTipo.setBounds(10, 10, 84, 20);
		panelTipos.add(btnCrearTipo);
		
		JButton btnModificarTipo = new JButton("Modificar");
		btnModificarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarTipo();
			}
		});
		btnModificarTipo.setBounds(104, 10, 84, 20);
		panelTipos.add(btnModificarTipo);
		
		JButton btnBorrarTipo = new JButton("Borrar");
		btnBorrarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarTipo();
			}
		});
		btnBorrarTipo.setBounds(198, 10, 84, 20);
		panelTipos.add(btnBorrarTipo);
		
		JButton btnConsultarTipo = new JButton("Consultar");
		btnConsultarTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarTipo();
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

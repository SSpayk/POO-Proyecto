package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import control.Controladora;
import logica.Categoria;
import logica.Item;


public class DetalleItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaCategorias;
	private int indiceItem;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DetalleItem(JFrame parent, int indiceItem) {
        super(parent, "Detalle Item", true);
        this.indiceItem = indiceItem;
        setBounds(100, 100, 450, 380);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Controladora control = Controladora.getInstance();
        Item item = control.consultarItem(indiceItem);

        JLabel labelNombre = new JLabel("Nombre:  " + item.getNombre());
        labelNombre.setBounds(15, 15, 300, 20);
        contentPanel.add(labelNombre);

        JLabel labelCodigo = new JLabel("Codigo:  " + item.getCodigo());
        labelCodigo.setBounds(15, 40, 300, 20);
        contentPanel.add(labelCodigo);

        JLabel labelDescripcion = new JLabel("Descripcion:  " + item.getDescripcion());
        labelDescripcion.setBounds(15, 65, 400, 20);
        contentPanel.add(labelDescripcion);

        JLabel labelTipo = new JLabel("Tipo:  " + item.getTipo().getNombre());
        labelTipo.setBounds(15, 90, 300, 20);
        contentPanel.add(labelTipo);

        String prestado = item.itemDisponible() ? "No" : "Si";
        JLabel labelPrestado = new JLabel("Prestado:  " + prestado);
        labelPrestado.setBounds(15, 115, 300, 20);
        contentPanel.add(labelPrestado);

        JLabel labelCategorias = new JLabel("Categorias:");
        labelCategorias.setBounds(15, 145, 200, 20);
        contentPanel.add(labelCategorias);

        tablaCategorias = new JTable();
        tablaCategorias.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nombre" }
        ));

        JScrollPane scroll = new JScrollPane(tablaCategorias);
        scroll.setBounds(15, 170, 410, 130);
        contentPanel.add(scroll);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                okButton.addActionListener(e -> dispose());
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }

        cargarCategorias();
    }
	
	private void cargarCategorias() {
        Controladora control = Controladora.getInstance();
        Item item = control.consultarItem(indiceItem);
        List<Categoria> categorias = item.getCategorias();

        DefaultTableModel model = (DefaultTableModel) tablaCategorias.getModel();
        model.setRowCount(0);
        for (Categoria c : categorias) {
            model.addRow(new Object[] { c.getNombre() });
        }
    }

}

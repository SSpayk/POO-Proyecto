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
import logica.Item;
import logica.Prestamo;

public class DetallePrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaItems;
    private int indicePrestamo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
    public DetallePrestamo(JFrame parent, int indicePrestamo) {
        super(parent, "Detalle Prestamo", true);
        this.indicePrestamo = indicePrestamo;
        setBounds(100, 100, 500, 420);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Controladora control = Controladora.getInstance();
        Prestamo prestamo = control.listaPrestamos().get(indicePrestamo);

        JLabel labelUsuario = new JLabel("Usuario:  " + prestamo.getUsuario().getNombre());
        labelUsuario.setBounds(15, 15, 350, 20);
        contentPanel.add(labelUsuario);

        JLabel labelFecha = new JLabel("Fecha:  " + prestamo.getFecha());
        labelFecha.setBounds(15, 40, 350, 20);
        contentPanel.add(labelFecha);

        String infoAlerta = (prestamo.getAlerta() != null) ? "Si" : "No";
        JLabel labelAlerta = new JLabel("Tiene alerta:  " + infoAlerta);
        labelAlerta.setBounds(15, 65, 350, 20);
        contentPanel.add(labelAlerta);

        JLabel labelItems = new JLabel("Items del prestamo:");
        labelItems.setBounds(15, 100, 200, 20);
        contentPanel.add(labelItems);

        tablaItems = new JTable();
        tablaItems.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Codigo", "Nombre" }
        ));
        tablaItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tablaItems);
        scroll.setBounds(15, 125, 340, 230);
        contentPanel.add(scroll);

        JButton btnRetornar = new JButton("Retornar item");
        btnRetornar.setBounds(370, 125, 110, 29);
        btnRetornar.addActionListener(e -> retornarItem());
        contentPanel.add(btnRetornar);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            JButton okButton = new JButton("OK");
            okButton.addActionListener(e -> dispose());
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }

        cargarItems();
    }

    private void cargarItems() {
        Controladora control = Controladora.getInstance();
        Prestamo prestamo = control.listaPrestamos().get(indicePrestamo);
        List<Item> items = prestamo.obtenerItems();

        DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
        model.setRowCount(0);
        for (Item item : items) {
            model.addRow(new Object[] { item.getCodigo(), item.getNombre() });
        }
    }

    private void retornarItem() {
        int fila = tablaItems.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un item.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Controladora control = Controladora.getInstance();
        Prestamo prestamo = control.listaPrestamos().get(indicePrestamo);
        Item item = prestamo.obtenerItems().get(fila);

        control.retornarItemPrestamo(indicePrestamo, item);
        cargarItems();
    }

}

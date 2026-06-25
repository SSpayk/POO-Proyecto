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
import logica.Tipo;

public class DetalleTipo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaItems;
    private int indiceTipo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
    public DetalleTipo(JFrame parent, int indiceTipo) {
        super(parent, "Detalle Tipo", true);
        this.indiceTipo = indiceTipo;
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Controladora control = Controladora.getInstance();
        Tipo tipo = control.consultarTipo(indiceTipo);

        JLabel labelNombre = new JLabel("Nombre:  " + tipo.getNombre());
        labelNombre.setBounds(15, 15, 300, 20);
        contentPanel.add(labelNombre);

        JLabel labelItems = new JLabel("Items del tipo:");
        labelItems.setBounds(15, 50, 200, 20);
        contentPanel.add(labelItems);

        tablaItems = new JTable();
        tablaItems.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Codigo", "Nombre", "Descripcion" }
        ));

        JScrollPane scroll = new JScrollPane(tablaItems);
        scroll.setBounds(15, 75, 410, 195);
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

        cargarItems();
    }
    
    private void cargarItems() {
        Controladora control = Controladora.getInstance();
        Tipo tipo = control.consultarTipo(indiceTipo);
        List<Item> items = tipo.getItems();

        DefaultTableModel model = (DefaultTableModel) tablaItems.getModel();
        model.setRowCount(0);
        for (Item item : items) {
            model.addRow(new Object[] {
                item.getCodigo(),
                item.getNombre(),
                item.getDescripcion()
            });
        }
    }

}

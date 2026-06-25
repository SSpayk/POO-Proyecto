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
import logica.Prestamo;
import logica.Usuario;


public class DetalleUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaPrestamos;
	private int indiceUsuario;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DetalleUsuario(JFrame parent, int indiceUsuario) {
        super(parent, "Detalle Usuario", true);
        setResizable(false);
        this.indiceUsuario = indiceUsuario;
        setBounds(100, 100, 450, 350);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Controladora control = Controladora.getInstance();
        Usuario usuario = control.consultarUsuario(indiceUsuario);

        JLabel labelNombre = new JLabel("Nombre:  " + usuario.getNombre());
        labelNombre.setBounds(15, 15, 300, 20);
        contentPanel.add(labelNombre);

        JLabel labelNumero = new JLabel("Numero:  " + usuario.getNumero());
        labelNumero.setBounds(15, 40, 300, 20);
        contentPanel.add(labelNumero);

        JLabel labelCorreo = new JLabel("Correo:   " + usuario.getCorreo());
        labelCorreo.setBounds(15, 65, 300, 20);
        contentPanel.add(labelCorreo);

        JLabel labelPrestamos = new JLabel("Prestamos del usuario:");
        labelPrestamos.setBounds(15, 100, 200, 20);
        contentPanel.add(labelPrestamos);

        tablaPrestamos = new JTable();
        tablaPrestamos.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Fecha", "Cantidad Items", "Tiene Alerta" }
        ));

        JScrollPane scroll = new JScrollPane(tablaPrestamos);
        scroll.setBounds(15, 125, 410, 140);
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

        cargarPrestamos();
    }

    private void cargarPrestamos() {
        Controladora control = Controladora.getInstance();
        Usuario usuario = control.consultarUsuario(indiceUsuario);
        List<Prestamo> prestamos = usuario.getPrestamos();

        DefaultTableModel model = (DefaultTableModel) tablaPrestamos.getModel();
        model.setRowCount(0);
        for (Prestamo p : prestamos) {
            String tieneAlerta = (p.getAlerta() != null) ? "Si" : "No";
            model.addRow(new Object[] {p.getFecha(),p.obtenerItems().size(),tieneAlerta});
        }
    }
}

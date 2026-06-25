package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import control.Controladora;
import logica.Item;
import logica.Prestamo;
import logica.Usuario;

public class HacerPrestamo extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboUsuarios;
    private JTable tablaDisponibles;
    private JTable tablaPrestamo;
    
    private List<Item> itemsSeleccionados = new ArrayList<Item>();
    
    private JCheckBox checkAlerta;
    private JTextField campoFecha;
    private JCheckBox checkRecurrente;
    private JTextField campoRepeticiones;
    private JTextField campoIntervalo;
    private JButton btnGuardar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
    public HacerPrestamo(JFrame parent) {
        super(parent, "Hacer Prestamo", true);
        setBounds(100, 100, 750, 600);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(null);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        Controladora control = Controladora.getInstance();

        // ----- Seleccion de usuario -----
        JLabel labelUsuario = new JLabel("Usuario:");
        labelUsuario.setBounds(15, 15, 80, 20);
        contentPanel.add(labelUsuario);

        comboUsuarios = new JComboBox<>();
        for (Usuario u : control.listaUsuario()) {
            comboUsuarios.addItem(u.getNombre());
        }
        comboUsuarios.setBounds(95, 15, 200, 25);
        contentPanel.add(comboUsuarios);

        JLabel labelDisponibles = new JLabel("Items disponibles:");
        labelDisponibles.setBounds(15, 55, 200, 20);
        contentPanel.add(labelDisponibles);

        tablaDisponibles = new JTable();
        tablaDisponibles.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Codigo", "Nombre" }
        ));
        tablaDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollDisponibles = new JScrollPane(tablaDisponibles);
        scrollDisponibles.setBounds(15, 80, 280, 250);
        contentPanel.add(scrollDisponibles);

        JButton btnAgregar = new JButton("Agregar >");
        btnAgregar.setBounds(310, 150, 110, 29);
        contentPanel.add(btnAgregar);

        JButton btnQuitar = new JButton("< Quitar");
        btnQuitar.setBounds(310, 190, 110, 29);
        contentPanel.add(btnQuitar);

        JLabel labelPrestamo = new JLabel("Items del prestamo:");
        labelPrestamo.setBounds(435, 55, 200, 20);
        contentPanel.add(labelPrestamo);

        tablaPrestamo = new JTable();
        tablaPrestamo.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Codigo", "Nombre" }
        ));
        tablaPrestamo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPrestamo = new JScrollPane(tablaPrestamo);
        scrollPrestamo.setBounds(435, 80, 280, 250);
        contentPanel.add(scrollPrestamo);
        
     
        checkAlerta = new JCheckBox("Crear alerta");
        checkAlerta.setBounds(15, 345, 150, 20);
        contentPanel.add(checkAlerta);

        JLabel labelFecha = new JLabel("Fecha (dd-mm-aaaa):");
        labelFecha.setBounds(15, 375, 140, 20);
        contentPanel.add(labelFecha);

        campoFecha = new JTextField();
        campoFecha.setBounds(160, 375, 150, 25);
        campoFecha.setEnabled(false);
        contentPanel.add(campoFecha);

        checkRecurrente = new JCheckBox("Recurrente");
        checkRecurrente.setBounds(15, 410, 150, 20);
        checkRecurrente.setEnabled(false);
        contentPanel.add(checkRecurrente);

        JLabel labelRepeticiones = new JLabel("Repeticiones:");
        labelRepeticiones.setBounds(15, 440, 140, 20);
        contentPanel.add(labelRepeticiones);

        campoRepeticiones = new JTextField();
        campoRepeticiones.setBounds(160, 440, 150, 25);
        campoRepeticiones.setEnabled(false);
        contentPanel.add(campoRepeticiones);

        JLabel labelIntervalo = new JLabel("Intervalo (dias):");
        labelIntervalo.setBounds(15, 475, 140, 20);
        contentPanel.add(labelIntervalo);

        campoIntervalo = new JTextField();
        campoIntervalo.setBounds(160, 475, 150, 25);
        campoIntervalo.setEnabled(false);
        contentPanel.add(campoIntervalo);

        checkAlerta.addActionListener(e -> {
            boolean activo = checkAlerta.isSelected();
            campoFecha.setEnabled(activo);
            checkRecurrente.setEnabled(activo);
            campoRepeticiones.setEnabled(activo);
            campoIntervalo.setEnabled(activo);
        });

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            btnGuardar = new JButton("Guardar");
            buttonPane.add(btnGuardar);

            JButton btnCancelar = new JButton("Cancelar");
            btnCancelar.addActionListener(e -> dispose());
            buttonPane.add(btnCancelar);
        }

        btnAgregar.addActionListener(e -> agregarItem());
        btnQuitar.addActionListener(e -> quitarItem());
        btnGuardar.addActionListener(e -> guardarPrestamo());

        cargarDisponibles();
    }

    private void cargarDisponibles() {
        Controladora control = Controladora.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaDisponibles.getModel();
        model.setRowCount(0);
        for (Item item : control.listaItems()) {
            
            if (item.itemDisponible() && !itemsSeleccionados.contains(item)) {
                model.addRow(new Object[] { item.getCodigo(), item.getNombre() });
            }
        }
    }

    private void cargarPrestamo() {
        DefaultTableModel model = (DefaultTableModel) tablaPrestamo.getModel();
        model.setRowCount(0);
        for (Item item : itemsSeleccionados) {
            model.addRow(new Object[] { item.getCodigo(), item.getNombre() });
        }
    }

    private void agregarItem() {
    	int fila = tablaDisponibles.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un item disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Controladora control = Controladora.getInstance();
        DefaultTableModel model = (DefaultTableModel) tablaDisponibles.getModel();
        int codigo = (Integer) model.getValueAt(fila, 0);

        for (Item item : control.listaItems()) {
            if (item.getCodigo() == codigo && item.itemDisponible() && !itemsSeleccionados.contains(item)) {
                itemsSeleccionados.add(item);
                break;
            }
        }

        cargarDisponibles();
        cargarPrestamo();
    }

    private void quitarItem() {
    	int fila = tablaPrestamo.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un item del prestamo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        itemsSeleccionados.remove(fila);

        cargarDisponibles();
        cargarPrestamo();
    }
    
    private void guardarPrestamo() {
        Controladora control = Controladora.getInstance();

        if (itemsSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un item al prestamo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int indiceUsuario = comboUsuarios.getSelectedIndex();
        Prestamo prestamo = control.hacerPrestamo(indiceUsuario, itemsSeleccionados);

        if (checkAlerta.isSelected()) {
            try {
                LocalDate fecha = LocalDate.parse(campoFecha.getText().trim());
                LocalDateTime fechaAlerta = fecha.atStartOfDay();
                boolean recurrente = checkRecurrente.isSelected();
                int repeticiones = Integer.parseInt(campoRepeticiones.getText().trim());
                int intervalo = Integer.parseInt(campoIntervalo.getText().trim());

                int indicePrestamo = control.listaPrestamos().indexOf(prestamo);
                control.crearAlerta(indicePrestamo, fechaAlerta, recurrente, repeticiones, intervalo);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos de alerta invalidos. El prestamo se creo sin alerta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        dispose();
    }

}

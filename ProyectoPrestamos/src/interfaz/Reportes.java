package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Reportes extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Reportes(JFrame parent, String titulo, String contenido) {
        super(parent, titulo, true);
        setBounds(100, 100, 500, 450);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JTextArea areaTexto = new JTextArea();
        areaTexto.setText(contenido);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(areaTexto);
        contentPanel.add(scroll, BorderLayout.CENTER);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            JButton okButton = new JButton("Cerrar");
            okButton.addActionListener(e -> dispose());
            buttonPane.add(okButton);
            getRootPane().setDefaultButton(okButton);
        }
    }

}

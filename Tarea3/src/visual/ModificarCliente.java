package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Utilidades;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ModificarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Cliente auxCliente=null;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JFormattedTextField ftxtCedula;
	private JFormattedTextField ftxtTelefono;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ModificarCliente dialog = new ModificarCliente(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ModificarCliente(Cliente cliente) {
		auxCliente=cliente;
		setTitle("Modificar Cliente");
		setBounds(100, 100, 509, 195);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(null);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(10, 24, 61, 20);
			panel.add(lblCedula);
			
			ftxtCedula = new JFormattedTextField(Utilidades.getMascaraCedula());
			ftxtCedula.setEditable(false);
			ftxtCedula.setBounds(71, 24, 95, 20);
			panel.add(ftxtCedula);
			
			JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
			lblTelefono.setBounds(10, 68, 61, 20);
			panel.add(lblTelefono);
			
			ftxtTelefono = new JFormattedTextField(Utilidades.getMascaraTelefono());
			ftxtTelefono.setBounds(71, 68, 95, 20);
			panel.add(ftxtTelefono);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(186, 24, 61, 20);
			panel.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(257, 24, 200, 20);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
			lblDireccin.setBounds(186, 68, 61, 20);
			panel.add(lblDireccin);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(257, 68, 200, 20);
			panel.add(txtDireccion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!(ftxtTelefono.getText().equalsIgnoreCase(auxCliente.getTelefono()))||!(txtDireccion.getText().equalsIgnoreCase(auxCliente.getDireccion()))) {
							auxCliente.setTelefono(ftxtTelefono.getText());
							auxCliente.setDireccion(txtDireccion.getText());
							JOptionPane.showMessageDialog(null,"Cliente Modificado de manera exitosa" , "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
							loadCliente();
						}
					}
				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadCliente();
	}
	private void loadCliente() {
		if(auxCliente!=null) {
			ftxtCedula.setText(auxCliente.getCedula());
			txtNombre.setText(auxCliente.getNombre());
			txtDireccion.setText(auxCliente.getDireccion());
			ftxtTelefono.setText(auxCliente.getTelefono());
		}
	}

}

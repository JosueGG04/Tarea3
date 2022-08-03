package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.javafx.scene.control.SelectedCellsMap;

import logico.Administracion;
import logico.Cliente;
import logico.Factura;
import logico.Utilidades;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnCompras;
	private Cliente selected=null; 
	private Object[] row;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListadoClientes dialog = new ListadoClientes();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ListadoClientes() {
		setResizable(false);
		setTitle("Lista de Clientes");
		setBounds(100, 100, 800, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index>=0) {
							String cedula = table.getValueAt(index, 0).toString();
							selected=Administracion.getInstance().buscarClienteByCedula(cedula);
							btnCompras.setEnabled(true);
							btnModificar.setEnabled(true);
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				Utilidades.modelCliente = new DefaultTableModel();
				String[] headers = {"Cedula", "Nombre", "teléfono", "Dirección", "Cantidad de Compras"};
				Utilidades.modelCliente.setColumnIdentifiers(headers);
				
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
				table.setModel(Utilidades.modelCliente);
			}
		}
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCompras = new JButton("Compras");
				btnCompras.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListadoFacturas listadoFacturas = new ListadoFacturas(selected);
						listadoFacturas.setModal(true);
						listadoFacturas.setVisible(true);
					}
				});
				{
					btnModificar = new JButton("Modificar");
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							ModificarCliente modificarCliente=new ModificarCliente(selected);
							modificarCliente.setModal(true);
							modificarCliente.setVisible(true);
						}
					});
					btnModificar.setEnabled(false);
					btnModificar.setActionCommand("OK");
					buttonPane.add(btnModificar);
				}
				btnCompras.setEnabled(false);
				btnCompras.setActionCommand("OK");
				buttonPane.add(btnCompras);
				getRootPane().setDefaultButton(btnCompras);
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
		LoadClientes();
	}
	private void LoadClientes() {
		Utilidades.modelCliente.setRowCount(0);
		row = new Object[Utilidades.modelCliente.getColumnCount()];
		for (Cliente cliente : Administracion.getInstance().getTodosLosClientes()) {
			row[0]=cliente.getCedula();
			row[1]=cliente.getNombre();
			row[2]=cliente.getTelefono();
			row[3]=cliente.getDireccion();
			row[4]=cliente.getMisFacturas().size();
			Utilidades.modelCliente.addRow(row);
		}
	}

}

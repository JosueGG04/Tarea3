package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administracion;
import logico.Cliente;
import logico.Factura;
import logico.Utilidades;
import servidor.FormatoFactura;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class ListadoFacturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Object[] row;
	private Factura selected=null;
	private JButton btnDetalles;
	private Cliente auxCliente=null;
	private FormatoFactura formato = new FormatoFactura();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListadoFacturas dialog = new ListadoFacturas(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ListadoFacturas(Cliente cliente) {
		setResizable(false);
		auxCliente=cliente;
		if (auxCliente==null) {
			setTitle("Lista de Facturas");
		}
		else {
			setTitle("Compras de "+auxCliente.getNombre());
		}
		setBounds(100, 100, 800, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index>=0) {
							String codigo = table.getValueAt(index, 0).toString();
							selected=Administracion.getInstance().buscarFacturaByCode(codigo);
							btnDetalles.setEnabled(true);
						}
					}
				});
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] headers = {"Codigo",  "Nombre","Cedula del cliente", "Fecha de compra", "Cantidad", "Monto"};
				Utilidades.modelFactura.setColumnIdentifiers(headers);
				table.setModel(Utilidades.modelFactura);
			}
		}
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDetalles = new JButton("Detalles");
				btnDetalles.setEnabled(false);
				btnDetalles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ListadoQuesos listadoQuesos = new ListadoQuesos(selected);
						listadoQuesos.setModal(true);
						listadoQuesos.setVisible(true);
					}
				});
				btnDetalles.setActionCommand("OK");
				buttonPane.add(btnDetalles);
				getRootPane().setDefaultButton(btnDetalles);
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
		loadFacturas();
	}
	private void loadFacturas() {
		ArrayList<Factura> cargar=null;
		if (auxCliente==null) {
			cargar=Administracion.getInstance().getTodasLasFacturas();
		}
		else {
			cargar=auxCliente.getMisFacturas();
		}
		Utilidades.modelFactura.setRowCount(0);
		row = new Object[Utilidades.modelFactura.getColumnCount()];
		for (Factura factura : cargar) {
			row[0]=factura.getCodigoFactura();
			row[1]=Administracion.getInstance().buscarClienteByCedula(factura.getCedulaCliente()).getNombre();
			row[2]=factura.getCedulaCliente();
			row[3]=Utilidades.formatoFecha.format(factura.getFecha());
			row[4]=factura.getMisQuesos().size();
			row[5]=String.format("%.2f",factura.MontoFactura());
			Utilidades.modelFactura.addRow(row);
		}
	}

}

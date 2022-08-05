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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import logico.Administracion;
import logico.Cilindrico;
import logico.Cliente;
import logico.Esferico;
import logico.Factura;
import logico.Hueco;
import logico.Queso;
import logico.Utilidades;
import servidor.FormatoFactura;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class RegFactura extends JDialog {

	private ArrayList<Queso> quesosDisponibles=new ArrayList<Queso>();
	private ArrayList<Queso> quesosEnCarrito=new ArrayList<Queso>();	
	private final JPanel contentPanel = new JPanel();
	private Object[] row;
	private Object[] row2;
	private Cliente auxCliente= null;
	private Queso selected=null;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTable tableDisponible;
	private JTable tableCarrito;
	private JButton btnRegresar;
	private JButton btnAgregar;
	private JTextField txtMonto;
	private JFormattedTextField ftxtCedula;
	private JTextField txtCodigo;
	private JFormattedTextField ftxtTelefono;
	private boolean buscado=false;
	private JButton btnBuscar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			RegFactura dialog = new RegFactura();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public RegFactura() {
		setResizable(false);
		setTitle("Facturación");
		setBounds(100, 100, 499, 501);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 473, 138);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setBounds(10, 19, 61, 20);
		panel.add(lblCedula);
		
		ftxtCedula = new JFormattedTextField(Utilidades.getMascaraCedula());
		ftxtCedula.setBounds(81, 19, 200, 20);
		panel.add(ftxtCedula);
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(291, 58, 61, 20);
		panel.add(lblTelefono);
		
		ftxtTelefono = new JFormattedTextField(Utilidades.getMascaraTelefono());
		ftxtTelefono.setEditable(false);
		ftxtTelefono.setBounds(362, 58, 95, 20);
		panel.add(ftxtTelefono);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 58, 61, 20);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(81, 58, 200, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 97, 61, 20);
		panel.add(lblDireccin);
		
		txtDireccion = new JTextField();
		txtDireccion.setEditable(false);
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(81, 97, 200, 20);
		panel.add(txtDireccion);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ftxtCedula.getText().replaceAll("-", "").equalsIgnoreCase("___________"))&&!(ftxtCedula.getText().replaceAll("_", "").equalsIgnoreCase("---"))) {
					buscado=true;
					auxCliente=Administracion.getInstance().buscarClienteByCedula(ftxtCedula.getText());
					btnBuscar.setEnabled(false);
					if (auxCliente!=null) {
						txtDireccion.setText(auxCliente.getDireccion());
						txtNombre.setText(auxCliente.getNombre());
						ftxtTelefono.setText(auxCliente.getTelefono());
						ftxtCedula.setEditable(false);
					}
					else {
						ftxtCedula.setEditable(false);
						ftxtTelefono.setEditable(true);
						txtNombre.setEditable(true);
						txtDireccion.setEditable(true);
					}
				}
				
			}
		});
		btnBuscar.setActionCommand("OK");
		btnBuscar.setBounds(365, 96, 90, 23);
		panel.add(btnBuscar);
		
		JLabel lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(291, 19, 61, 20);
		panel.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(362, 19, 95, 20);
		txtCodigo.setText("F-"+Administracion.getGeneradorCodeFactura());
		panel.add(txtCodigo);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Quesos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 160, 473, 237);
		contentPanel.add(panel_1);
		String[] headers = {"Codigo","Tipo","Precio"};
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisponibleACarrito(selected);
				loadQuesos();
				loadQuesosEnCarrito();
				LoadMonto();
				tableDisponible.clearSelection();
			}
		});
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(192, 63, 89, 23);
		panel_1.add(btnAgregar);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarritoADisponible(selected);
				loadQuesos();
				loadQuesosEnCarrito();
				LoadMonto();
				tableCarrito.clearSelection();
			}
		});
		btnRegresar.setEnabled(false);
		btnRegresar.setBounds(192, 149, 89, 23);
		panel_1.add(btnRegresar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(8, 21, 180, 205);
		panel_1.add(scrollPane);
		
		
		tableDisponible = new JTable();
		tableDisponible.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableDisponible);
		tableDisponible.setShowVerticalLines(false);
		tableDisponible.setShowGrid(false);
		tableDisponible.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableDisponible.getSelectedRow();
				if(index>=0) {
					String codigo = tableDisponible.getValueAt(index, 0).toString();
					selected=Administracion.getInstance().buscarQuesoByCode(codigo);
					btnRegresar.setEnabled(false);
					btnAgregar.setEnabled(true);
				}
				
			}
		});
		Utilidades.model.setColumnIdentifiers(headers);
		tableDisponible.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDisponible.setModel(Utilidades.model);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(289, 21, 180, 205);
		panel_1.add(scrollPane_1);
		
		tableCarrito = new JTable();
		scrollPane_1.setViewportView(tableCarrito);
		tableCarrito.setShowVerticalLines(false);
		tableCarrito.setShowGrid(false);
		
		tableCarrito.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tableCarrito.getSelectedRow();
				if(index>=0) {
					String codigo = tableCarrito.getValueAt(index, 0).toString();
					selected=Administracion.getInstance().buscarQuesoByCode(codigo);
					btnRegresar.setEnabled(true);
					btnAgregar.setEnabled(false);
				}
				
			}
		});
		Utilidades.model2.setColumnIdentifiers(headers);
		tableCarrito.setModel(Utilidades.model2);
		tableCarrito.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCarrito.setFillsViewportHeight(true);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(310, 398, 50, 20);
		contentPanel.add(lblMonto);
		
		txtMonto = new JTextField();
		txtMonto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMonto.setEditable(false);
		txtMonto.setColumns(10);
		txtMonto.setBounds(352, 398, 121, 20);
		contentPanel.add(txtMonto);
		
		
		setLocationRelativeTo(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnConfirmar = new JButton("Confirmar");
				btnConfirmar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (quesosEnCarrito.size()>0) {
							if (buscado&&!(txtNombre.toString().equalsIgnoreCase(""))) {
								if(auxCliente==null) {
									auxCliente = new Cliente(ftxtCedula.getText(), txtNombre.getText(), ftxtTelefono.getText(), txtDireccion.getText());
									Administracion.getInstance().insertarCliente(auxCliente);
								}
								Factura auxFactura = new Factura(txtCodigo.getText(), ftxtCedula.getText());
								Administracion.getInstance().insertarQuesosEnFactura(auxFactura, quesosEnCarrito);
								Administracion.getInstance().insertarFactura(auxFactura, auxCliente);
								FormatoFactura.crearArchivo(auxFactura);
								JOptionPane.showMessageDialog(null,"La factura a sido registrada de manera exitosa" , "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
								quesosVendidos();
								buscado=false;
								loadEverything();
								clean();
								
							}
							else {
								JOptionPane.showMessageDialog(null, "No se puede crear una factura sin cliente!", "ERROR", 0);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "No se puede crear una factura sin quesos!", "ERROR", 0);
						}
					}
					
				});
				btnConfirmar.setActionCommand("OK");
				buttonPane.add(btnConfirmar);
				getRootPane().setDefaultButton(btnConfirmar);
			}
			{
				JButton btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		loadQuesosDisponibles();
		loadEverything();
	}
	private void loadQuesosDisponibles() {
		for (Queso queso : Administracion.getInstance().getTodosLosQuesos()) {
			if (queso.isDisponible()) {
				quesosDisponibles.add(queso);
			}
		}
		
	}
	private void loadQuesos() {
		Utilidades.model.setRowCount(0);
		row = new Object[Utilidades.model.getColumnCount()];
		for (Queso queso : quesosDisponibles) {
			row[0]=queso.getCodigo();
			
			if (queso instanceof Hueco) {
				row[1]="Hueco";
			}
			else if (queso instanceof Cilindrico) {
				row[1]="Cilindro";
			}
			if (queso instanceof Esferico) {
				row[1]="Esferico";
			}
			row[2]=String.format("%.2f",queso.precio());
			Utilidades.model.addRow(row);
		}
	}
	private void loadQuesosEnCarrito() {
		Utilidades.model2.setRowCount(0);
		row2 = new Object[Utilidades.model2.getColumnCount()];
		for (Queso queso : quesosEnCarrito) {
			row2[0]=queso.getCodigo();
			if (queso instanceof Hueco) {
				row2[1]="Hueco";
			}
			else if (queso instanceof Cilindrico) {
				row2[1]="Cilindro";
			}
			if (queso instanceof Esferico) {
				row2[1]="Esferico";
			}row2[2]=String.format("%.2f",queso.precio());
			Utilidades.model2.addRow(row2);
		}
	}
	
	private void DisponibleACarrito(Queso queso) {
		if(quesosDisponibles.remove(queso)) {
			quesosEnCarrito.add(queso);
		}
	}
	
	private void CarritoADisponible(Queso queso) {
		if(quesosEnCarrito.remove(queso)) {
			quesosDisponibles.add(queso);
		}
	}
	
	private float Monto() {
		float monto=0;
		for (Queso queso : quesosEnCarrito) {
			monto+=queso.precio();	
		}
		return monto;
	}
	
	private void LoadMonto() {
		txtMonto.setText(String.format("%.2f",Monto()));
	}
	
	private void loadEverything() {
		loadQuesos();
		loadQuesosEnCarrito();
		LoadMonto();
	}
	
	private void quesosVendidos() {
		for (Queso queso : quesosEnCarrito) {
			queso.setDisponible(false);
		}
		quesosEnCarrito.clear();
	}
	
	private void clean() {
		txtDireccion.setText("");
		txtNombre.setText("");
		txtCodigo.setText("F-"+Administracion.getGeneradorCodeFactura());
		ftxtCedula.setText("");
		ftxtTelefono.setText("");
		ftxtCedula.setEditable(true);
		ftxtTelefono.setEditable(false);
		txtNombre.setEditable(false);
		txtDireccion.setEditable(false);
		btnBuscar.setEnabled(true);
		
	}
}

package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.border.TitledBorder;

import logico.Administracion;
import logico.Cilindrico;
import logico.Esferico;
import logico.Factura;
import logico.Hueco;
import logico.Queso;
import logico.Utilidades;

import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoQuesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	
	private Object[] row;
	private Queso selected=null;
	private Factura auxFactura=null;
	private JTextField txtTipo;
	private JTextField txtCodigo;
	private JTextField txtPrecioBase;
	private JTextField txtPrecioUnitario;
	private JTextField txtPrecioVenta;
	private JTextField txtVolumen;
	private JTextField txtRadio;
	private JTextField txtLongitud;
	private JTextField txtRadioInterno;
	private JLabel lblCodigo;
	private JLabel lblPrecioBase;
	private JLabel lblPrecioUnitario;
	private JLabel lblRadio;
	private JLabel lblLongitud;
	private JLabel lblRadioInterno;
	private JLabel lblPrecioVenta;
	private JLabel lblVolumen;
	private JLabel lblTipo;
	private JButton btnModificar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ListadoQuesos dialog = new ListadoQuesos(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ListadoQuesos(Factura factura) {
		auxFactura= factura;
		setResizable(false);
		if (auxFactura==null) {
			setTitle("Lista de Quesos");
		}
		else {
			setTitle("Lista de Quesos de la Factura: "+factura.getCodigoFactura());
		}
		setBounds(100, 100, 830, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setModal(true);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setViewportBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(255, 11, 552, 370);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = table.getSelectedRow();
						if(index>=0) {
							String codigo = table.getValueAt(index, 0).toString();
							selected=Administracion.getInstance().buscarQuesoByCode(codigo);
							LoadQuesoIndividual(selected);
							if(selected.isDisponible()==true) {
								btnModificar.setEnabled(true);
							}
							else {
								btnModificar.setEnabled(false);
							}
						}
						
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] headers = {"Codigo", "Tipo", "Volumen" ,"Precio", "Vendido"};
				Utilidades.modelQueso.setColumnIdentifiers(headers);
				table.setModel(Utilidades.modelQueso);
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel Informacion = new JPanel();
			Informacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n Del Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			Informacion.setBounds(10, 11, 235, 370);
			contentPanel.add(Informacion);
			Informacion.setLayout(null);
			
			lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(10, 19, 100, 20);
			Informacion.add(lblCodigo);
			
			txtCodigo = new JTextField();
			txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
			txtCodigo.setEditable(false);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(110, 19, 110, 20);
			Informacion.add(txtCodigo);
			
			lblPrecioBase = new JLabel("Precio Base:");
			lblPrecioBase.setBounds(10, 97, 100, 20);
			Informacion.add(lblPrecioBase);
			
			lblPrecioUnitario = new JLabel("Precio Unitario:");
			lblPrecioUnitario.setBounds(10, 136, 100, 20);
			Informacion.add(lblPrecioUnitario);
			
			lblRadio = new JLabel("Radio:");
			lblRadio.setBounds(10, 253, 100, 20);
			Informacion.add(lblRadio);
			
			lblLongitud = new JLabel("Longitud:");
			lblLongitud.setVisible(false);
			lblLongitud.setBounds(10, 292, 100, 20);
			Informacion.add(lblLongitud);
			
			lblRadioInterno = new JLabel("Radio Interno:");
			lblRadioInterno.setVisible(false);
			lblRadioInterno.setBounds(10, 331, 100, 20);
			Informacion.add(lblRadioInterno);
			
			lblPrecioVenta = new JLabel("Precio Venta:");
			lblPrecioVenta.setBounds(10, 175, 100, 20);
			Informacion.add(lblPrecioVenta);
			
			lblVolumen = new JLabel("Volumen:");
			lblVolumen.setBounds(10, 214, 100, 20);
			Informacion.add(lblVolumen);
			
			lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 58, 100, 20);
			Informacion.add(lblTipo);
			
			txtTipo = new JTextField();
			txtTipo.setHorizontalAlignment(SwingConstants.LEFT);
			txtTipo.setEditable(false);
			txtTipo.setColumns(10);
			txtTipo.setBounds(110, 58, 110, 20);
			Informacion.add(txtTipo);
			
			txtPrecioBase = new JTextField();
			txtPrecioBase.setEditable(false);
			txtPrecioBase.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPrecioBase.setBounds(110, 97, 110, 20);
			Informacion.add(txtPrecioBase);
			txtPrecioBase.setColumns(10);
			
			txtPrecioUnitario = new JTextField();
			txtPrecioUnitario.setEditable(false);
			txtPrecioUnitario.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPrecioUnitario.setColumns(10);
			txtPrecioUnitario.setBounds(110, 136, 110, 20);
			Informacion.add(txtPrecioUnitario);
			
			txtPrecioVenta = new JTextField();
			txtPrecioVenta.setEditable(false);
			txtPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPrecioVenta.setColumns(10);
			txtPrecioVenta.setBounds(110, 175, 110, 20);
			Informacion.add(txtPrecioVenta);
			
			txtVolumen = new JTextField();
			txtVolumen.setEditable(false);
			txtVolumen.setHorizontalAlignment(SwingConstants.RIGHT);
			txtVolumen.setColumns(10);
			txtVolumen.setBounds(110, 214, 110, 20);
			Informacion.add(txtVolumen);
			
			txtRadio = new JTextField();
			txtRadio.setEditable(false);
			txtRadio.setHorizontalAlignment(SwingConstants.RIGHT);
			txtRadio.setColumns(10);
			txtRadio.setBounds(110, 253, 110, 20);
			Informacion.add(txtRadio);
			
			txtLongitud = new JTextField();
			txtLongitud.setVisible(false);
			txtLongitud.setEditable(false);
			txtLongitud.setHorizontalAlignment(SwingConstants.RIGHT);
			txtLongitud.setColumns(10);
			txtLongitud.setBounds(110, 292, 110, 20);
			Informacion.add(txtLongitud);
			
			txtRadioInterno = new JTextField();
			txtRadioInterno.setVisible(false);
			txtRadioInterno.setEditable(false);
			txtRadioInterno.setHorizontalAlignment(SwingConstants.RIGHT);
			txtRadioInterno.setColumns(10);
			txtRadioInterno.setBounds(110, 331, 110, 20);
			Informacion.add(txtRadioInterno);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegQuesos regQuesos= new RegQuesos(selected);
						regQuesos.setModal(true);
						regQuesos.setVisible(true);
						loadQuesos();
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
		loadQuesos();
	}
	private void loadQuesos() {
		ArrayList<Queso> cargar=null;
		if (auxFactura==null) {
			cargar=Administracion.getInstance().getTodosLosQuesos();
		}
		else {
			cargar=auxFactura.getMisQuesos();
		}	
		Utilidades.modelQueso.setRowCount(0);
		row = new Object[Utilidades.modelQueso.getColumnCount()];
		for (Queso queso : cargar) {
			row[0]=queso.getCodigo();
			if (queso instanceof Hueco) {
				row[1]="Hueco";
				Hueco hueco=(Hueco) queso;
				row[2]=String.format("%.2f",hueco.volumen());
			}
			else if (queso instanceof Cilindrico) {
				row[1]="Cilindro";
				Cilindrico cilindrico=(Cilindrico) queso;
				row[2]=String.format("%.2f",cilindrico.volumen());
			}
			if (queso instanceof Esferico) {
				row[1]="Esferico";
				Esferico esferico=(Esferico)queso;
				row[2]=String.format("%.2f",esferico.volumen());
			}
			row[3]=String.format("%.2f",queso.precio());
			if (queso.isDisponible()) {
				row[4]="no";
			}else {
				row[4]="si";
			}
			Utilidades.modelQueso.addRow(row);
		}
	}
	private void LoadQuesoIndividual(Queso queso) {
		txtCodigo.setVisible(true);
		txtTipo.setVisible(true);
		txtPrecioBase.setVisible(true);
		txtPrecioUnitario.setVisible(true);
		txtPrecioVenta.setVisible(true);
		txtVolumen.setVisible(true);
		txtRadio.setVisible(true);
		
		lblCodigo.setVisible(true);
		lblTipo.setVisible(true);
		lblPrecioBase.setVisible(true);
		lblPrecioUnitario.setVisible(true);
		lblPrecioVenta.setVisible(true);
		lblVolumen.setVisible(true);
		lblRadio.setVisible(true);
		
		txtCodigo.setText(queso.getCodigo());
		txtPrecioBase.setText(Float.toString(queso.getPrecioBase()));
		txtPrecioUnitario.setText(Float.toString(queso.getPrecioUnitario()));
		txtPrecioVenta.setText(String.format("%.2f",queso.precio()));
		
		if(queso instanceof Hueco) {
			txtLongitud.setVisible(true);
			txtRadioInterno.setVisible(true);
			
			lblLongitud.setVisible(true);
			lblRadioInterno.setVisible(true);
			
			Hueco hueco = (Hueco) queso;
			
			txtTipo.setText("Hueco");
			txtLongitud.setText(Float.toString(hueco.getLongitud()));
			txtRadio.setText(Float.toString((hueco.getRadio())));
			txtRadioInterno.setText(Float.toString((hueco.getRadioInterior())));
			txtVolumen.setText(String.format("%.2f",(hueco.volumen())));
			
		}else if (queso instanceof Cilindrico) {
			txtLongitud.setVisible(true);
			txtRadioInterno.setVisible(false);		
			
			lblLongitud.setVisible(true);
			lblRadioInterno.setVisible(false);
			Cilindrico cilindrico= (Cilindrico)queso;
			
			txtTipo.setText("Cilindrico");
			txtLongitud.setText(Float.toString(cilindrico.getLongitud()));
			txtRadio.setText(Float.toString(cilindrico.getRadio()));
			txtVolumen.setText(String.format("%.2f",cilindrico.volumen()));
		}
		if(queso instanceof Esferico) {
			txtLongitud.setVisible(false);
			txtRadioInterno.setVisible(false);
			
			lblLongitud.setVisible(false);
			lblRadioInterno.setVisible(false);
			Esferico esferico=(Esferico)queso;
			
			txtTipo.setText("Esferico");
			txtRadio.setText(Float.toString(esferico.getRadio()));
			txtVolumen.setText(String.format("%.2f", esferico.volumen()));
		}
	}
}

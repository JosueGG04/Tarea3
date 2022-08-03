package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Administracion;
import logico.Cilindrico;
import logico.Esferico;
import logico.Hueco;
import logico.Queso;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class RegQuesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JPanel panelDatosEsferico;
	private JPanel panelDatosCilindrico;
	private JPanel panelDatosHueco;
	private JRadioButton rbEsferico;
	private JRadioButton rbCilindrico;
	private JRadioButton rbHueco;
	private Queso auxQueso = null;
	private JSpinner spnPrecioBase;
	private JSpinner spnPrecioUnitario;
	private JSpinner spnRadioE;
	private JSpinner spnRadioH;
	private JSpinner spnLongitudH;
	private JSpinner spnRadioInterno;
	private JSpinner spnRadioC;
	private JSpinner spnLongitudC;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			RegQuesos dialog = new RegQuesos(null);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public RegQuesos(Queso queso) {
		setResizable(false);
		auxQueso=queso;
		Administracion.getInstance();
		if (auxQueso==null) {
			setTitle("Registrar queso");
		}
		else {
			setTitle("modificar queso");
		}
		setModal(true);
		setBounds(100, 100, 326, 480);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		
		{
			JPanel panelQueso = new JPanel();
			panelQueso.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n Principal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelQueso.setBounds(5, 5, 309, 150);
			contentPanel.add(panelQueso);
			panelQueso.setLayout(null);
			
			JLabel lblCodigo = new JLabel("Codigo:");
			lblCodigo.setBounds(10, 22, 80, 20);
			panelQueso.add(lblCodigo);
			
			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			
			txtCodigo.setText("QE-"+Administracion.generadorCodeEsfera);
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(90, 22, 200, 20);
			panelQueso.add(txtCodigo);
			
			JLabel lblPrecioBase = new JLabel("Precio Base:");
			lblPrecioBase.setBounds(10, 64, 80, 20);
			panelQueso.add(lblPrecioBase);
			
			JLabel lblPrecioUnitario = new JLabel("Precio Unitario:");
			lblPrecioUnitario.setBounds(10, 106, 80, 20);
			panelQueso.add(lblPrecioUnitario);
			
			spnPrecioUnitario = new JSpinner();
			spnPrecioUnitario.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnPrecioUnitario.setBounds(90, 106, 200, 20);
			panelQueso.add(spnPrecioUnitario);
			
			spnPrecioBase = new JSpinner();
			spnPrecioBase.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			spnPrecioBase.setBounds(90, 64, 200, 20);
			panelQueso.add(spnPrecioBase);
			
			
			
		}
		{
			JPanel panelTipo = new JPanel();
			panelTipo.setBorder(new TitledBorder(null, "Tipo de Queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelTipo.setBounds(5, 166, 309, 100);
			contentPanel.add(panelTipo);
			panelTipo.setLayout(null);
			
			
			rbEsferico = new JRadioButton("Esf\u00E9rico");
			rbEsferico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbEsferico.isSelected()) {
						txtCodigo.setText("QE-"+Administracion.generadorCodeEsfera);
						rbCilindrico.setSelected(false);
						rbHueco.setSelected(false);
						panelDatosEsferico.setVisible(true);
						panelDatosHueco.setVisible(false);
						panelDatosCilindrico.setVisible(false);
					}
				}
			});
			rbEsferico.setSelected(true);
			rbEsferico.setBounds(17, 38, 80, 23);
			panelTipo.add(rbEsferico);
			
			rbCilindrico = new JRadioButton("Cilindrico");
			rbCilindrico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbCilindrico.isSelected()) {
						txtCodigo.setText("QC-"+Administracion.generadorCodeCilindro);
						rbEsferico.setSelected(false);
						rbHueco.setSelected(false);
						panelDatosEsferico.setVisible(false);
						panelDatosHueco.setVisible(false);
						panelDatosCilindrico.setVisible(true);
					}
				}
			});
			rbCilindrico.setBounds(114, 38, 80, 23);
			panelTipo.add(rbCilindrico);
			
			rbHueco = new JRadioButton("Hueco");
			rbHueco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(rbHueco.isSelected()) {
						txtCodigo.setText("QH-"+Administracion.generadorCodeHueco);
						rbEsferico.setSelected(false);
						rbCilindrico.setSelected(false);
						panelDatosEsferico.setVisible(false);
						panelDatosHueco.setVisible(true);
						panelDatosCilindrico.setVisible(false);
					}
				}
			});
			rbHueco.setBounds(211, 38, 80, 23);
			panelTipo.add(rbHueco);
			
			if(auxQueso!=null) {
				rbCilindrico.setEnabled(false);
				rbEsferico.setEnabled(false);
				rbHueco.setEnabled(false);
				
				if (auxQueso instanceof Hueco) {
					rbHueco.setSelected(true);
					rbEsferico.setSelected(false);
					rbCilindrico.setSelected(false);
				}
				else if (auxQueso instanceof Cilindrico) {
					rbCilindrico.setSelected(true);
					rbEsferico.setSelected(false);
					rbHueco.setSelected(false);
				}
				if (auxQueso instanceof Esferico) {
					rbEsferico.setSelected(true);
					rbCilindrico.setSelected(false);
					rbHueco.setSelected(false);
				}
			}
		}
		
		panelDatosHueco = new JPanel();
		panelDatosHueco.setVisible(false);
		
		
		panelDatosEsferico = new JPanel();
		panelDatosEsferico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosEsferico.setBounds(5, 277, 309, 120);
		contentPanel.add(panelDatosEsferico);
		panelDatosEsferico.setLayout(null);
		
		JLabel lblRadioEsferico = new JLabel("Radio:");
		lblRadioEsferico.setBounds(10, 21, 80, 20);
		panelDatosEsferico.add(lblRadioEsferico);
		
		spnRadioE = new JSpinner();
		spnRadioE.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioE.setBounds(90, 21, 200, 20);
		panelDatosEsferico.add(spnRadioE);
		panelDatosHueco.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosHueco.setBounds(5, 277, 449, 120);
		contentPanel.add(panelDatosHueco);
		panelDatosHueco.setLayout(null);
		
		spnRadioH = new JSpinner();
		spnRadioH.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioH.setBounds(90, 21, 200, 20);
		panelDatosHueco.add(spnRadioH);
		
		JLabel lblRadioHueco = new JLabel("Radio:");
		lblRadioHueco.setBounds(10, 21, 80, 20);
		panelDatosHueco.add(lblRadioHueco);
		
		JLabel lblLongitudH = new JLabel("Longitud:");
		lblLongitudH.setBounds(10, 52, 80, 20);
		panelDatosHueco.add(lblLongitudH);
		
		spnLongitudH = new JSpinner();
		spnLongitudH.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitudH.setBounds(90, 52, 200, 20);
		panelDatosHueco.add(spnLongitudH);
		
		JLabel lblRadioInterno = new JLabel("Radio Interno:");
		lblRadioInterno.setBounds(10, 83, 80, 20);
		panelDatosHueco.add(lblRadioInterno);
		
		spnRadioInterno = new JSpinner();
		spnRadioInterno.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioInterno.setBounds(90, 83, 200, 20);
		panelDatosHueco.add(spnRadioInterno);
		
		panelDatosCilindrico = new JPanel();
		panelDatosCilindrico.setVisible(false);
		panelDatosCilindrico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos del Queso", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosCilindrico.setBounds(5, 277, 449, 120);
		contentPanel.add(panelDatosCilindrico);
		panelDatosCilindrico.setLayout(null);
		
		
		spnRadioC = new JSpinner();
		spnRadioC.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnRadioC.setBounds(90, 21, 200, 20);
		panelDatosCilindrico.add(spnRadioC);
		
		JLabel lblRadioCilindrico = new JLabel("Radio:");
		lblRadioCilindrico.setBounds(10, 21, 80, 20);
		panelDatosCilindrico.add(lblRadioCilindrico);
		
		JLabel lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(10, 52, 80, 20);
		panelDatosCilindrico.add(lblLongitud);
		spnLongitudC = new JSpinner();
		spnLongitudC.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
		spnLongitudC.setBounds(90, 52, 200, 20);
		panelDatosCilindrico.add(spnLongitudC);
		
		if(auxQueso!=null){
			spnRadioE.setEnabled(false);
			spnRadioH.setEnabled(false);
			spnLongitudH.setEnabled(false);
			spnRadioInterno.setEnabled(false);
			spnRadioC.setEnabled(false);
			spnLongitudC.setEnabled(false);
			if (auxQueso instanceof Hueco) {
				panelDatosEsferico.setVisible(false);
				panelDatosHueco.setVisible(true);
				panelDatosCilindrico.setVisible(false);
			}
			else if (auxQueso instanceof Cilindrico) {
				panelDatosEsferico.setVisible(false);
				panelDatosHueco.setVisible(false);
				panelDatosCilindrico.setVisible(true);
			}
			if (auxQueso instanceof Esferico) {
				panelDatosEsferico.setVisible(true);
				panelDatosHueco.setVisible(false);
				panelDatosCilindrico.setVisible(false);
			}
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				if (auxQueso!=null) {
					okButton.setText("Modificar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean hecho = false;
						if (auxQueso==null) {
							if(Float.valueOf((spnPrecioBase.getValue().toString()))==0 || Float.valueOf(spnPrecioUnitario.getValue().toString())==0) {
								JOptionPane.showMessageDialog(null, "Se han dejado campos vacios!", "ERROR", 0);
							}
							else {
								if (rbEsferico.isSelected()&&Float.valueOf((spnRadioE.getValue().toString()))!=0) {
									Esferico esferico = new Esferico(txtCodigo.getText(),Float.valueOf((spnPrecioBase.getValue().toString())),Float.valueOf(spnPrecioUnitario.getValue().toString()),Float.valueOf((spnRadioE.getValue().toString())));
									Administracion.getInstance().insertarQueso(esferico);
									auxQueso=esferico;
									hecho = true;
								}
								else if (rbEsferico.isSelected()) {
									JOptionPane.showMessageDialog(null, "Se han dejado campos vacios!", "ERROR", 0);
								}
								if (rbCilindrico.isSelected()&&Float.valueOf((spnRadioC.getValue().toString()))!=0&&Float.valueOf((spnLongitudC.getValue().toString()))!=0) {
									Cilindrico cilindrico = new Cilindrico(txtCodigo.getText(),Float.valueOf(spnPrecioBase.getValue().toString()),Float.valueOf(spnPrecioUnitario.getValue().toString()),Float.valueOf((spnRadioC.getValue().toString())),Float.valueOf((spnLongitudC.getValue().toString())));
									Administracion.getInstance().insertarQueso(cilindrico);
									auxQueso=cilindrico;
									hecho = true;
								}
								else if (rbCilindrico.isSelected()) {
									JOptionPane.showMessageDialog(null, "Se han dejado campos vacios!", "ERROR", 0);
								}
								if (rbHueco.isSelected()&&Float.valueOf((spnRadioH.getValue().toString()))!=0&&Float.valueOf((spnLongitudH.getValue().toString()))!=0&&Float.valueOf((spnRadioInterno.getValue().toString()))!=0&&Float.valueOf((spnRadioInterno.getValue().toString()))<=Float.valueOf((spnRadioH.getValue().toString()))) {
									Hueco hueco = new Hueco(txtCodigo.getText(),Float.valueOf((spnPrecioBase.getValue().toString())),Float.valueOf(spnPrecioUnitario.getValue().toString()),Float.valueOf((spnRadioH.getValue().toString())),Float.valueOf((spnLongitudH.getValue().toString())),Float.valueOf((spnRadioInterno.getValue().toString())));
									Administracion.getInstance().insertarQueso(hueco);
									auxQueso=hueco;
									hecho = true;
								}
								else if (rbHueco.isSelected()&&Float.valueOf((spnRadioInterno.getValue().toString()))>=Float.valueOf((spnRadioH.getValue().toString()))) {
									JOptionPane.showMessageDialog(null, "El radio interior no puede ser mayor al exterior!", "ERROR", 0);
								}
								else if (rbHueco.isSelected()) {
									JOptionPane.showMessageDialog(null, "Se han dejado campos vacios!", "ERROR", 0);
								}
								if(hecho==true) {
									JOptionPane.showMessageDialog(null,"Queso registrado de manera exitosa\nEl precio del queso registrado es: " + String.format("%.2f", auxQueso.precio()) , "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
									clean();
									auxQueso=null;
								}
							}
							
						}else {
							auxQueso.setPrecioBase(Float.valueOf((spnPrecioBase.getValue().toString())));
							auxQueso.setPrecioUnitario(Float.valueOf(spnPrecioUnitario.getValue().toString()));
							JOptionPane.showMessageDialog(null,"Queso Modificado de manera exitosa\nEl precio del queso modificado es: " + String.format("%.2f", auxQueso.precio()) , "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
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
		loadQueso();
	}
	public void loadQueso() {
		if(auxQueso!=null) {
			txtCodigo.setText(auxQueso.getCodigo());
			spnPrecioBase.setValue(auxQueso.getPrecioBase());
			spnPrecioUnitario.setValue(auxQueso.getPrecioUnitario());
			if (auxQueso instanceof Hueco) {
				Hueco h = (Hueco)auxQueso;
				spnLongitudH.setValue(h.getLongitud());
				spnRadioH.setValue(h.getRadio());
				spnRadioInterno.setValue(h.getRadioInterior());
			}
			else if (auxQueso instanceof Cilindrico) {
				Cilindrico c = (Cilindrico)auxQueso;
				spnLongitudC.setValue(c.getLongitud());
				spnRadioC.setValue(c.getRadio());
			}
			if (auxQueso instanceof Esferico) {
				Esferico e = (Esferico)auxQueso; 
				spnRadioE.setValue(e.getRadio());
			}
		}
	}
	
	public void clean() {
		if(rbEsferico.isSelected()) {
			txtCodigo.setText("QE-"+Administracion.generadorCodeEsfera);	
		}		
		if(rbCilindrico.isSelected()) {
			txtCodigo.setText("QC-"+Administracion.generadorCodeCilindro);
		}
		if(rbHueco.isSelected()) {
			txtCodigo.setText("QH-"+Administracion.generadorCodeHueco);	
		}
		spnRadioE.setValue(0);
		spnRadioC.setValue(0);
		spnLongitudC.setValue(0);
		spnRadioH.setValue(0);
		spnLongitudH.setValue(0);
		spnRadioInterno.setValue(0);
		spnPrecioUnitario.setValue(0);
		spnPrecioBase.setValue(0);
	}
}
	

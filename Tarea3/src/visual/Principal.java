package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Queseria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 421);
		dim = getToolkit().getScreenSize();
		setSize(dim.width-100, dim.height-35);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuesos = new JMenu("Quesos");
		menuBar.add(mnQuesos);
		
		JMenuItem mntmRegistrarQueso = new JMenuItem("Registrar Queso");
		mntmRegistrarQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQuesos regQuesos = new RegQuesos(null);
				regQuesos.setModal(true);
				regQuesos.setVisible(true);
				
			}
		});
		mnQuesos.add(mntmRegistrarQueso);
		
		JMenuItem mntmListaDeQuesos = new JMenuItem("Lista de Quesos");
		mntmListaDeQuesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoQuesos listadoQuesos=new ListadoQuesos(null);
				listadoQuesos.setModal(true);
				listadoQuesos.setVisible(true);
				
			}
		});
		mnQuesos.add(mntmListaDeQuesos);
		
		JMenu mnFacturas = new JMenu("Facturas");
		menuBar.add(mnFacturas);
		
		JMenuItem mntmFacturacion = new JMenuItem("Facturaci\u00F3n");
		mntmFacturacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegFactura regFactura = new RegFactura();
				regFactura.setModal(true);
				regFactura.setVisible(true);
			}
		});
		mnFacturas.add(mntmFacturacion);
		
		JMenuItem mntmListaDeFacturas = new JMenuItem("Lista de Facturas");
		mntmListaDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoFacturas listadoFacturas = new ListadoFacturas(null);
				listadoFacturas.setModal(true);
				listadoFacturas.setVisible(true);
			}
		});
		mnFacturas.add(mntmListaDeFacturas);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmListaDeClientes = new JMenuItem("Lista de Clientes");
		mntmListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoClientes listadoClientes = new ListadoClientes();
				listadoClientes.setModal(true);
				listadoClientes.setVisible(true);
			}
		});
		mnClientes.add(mntmListaDeClientes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}

package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Administracion;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	static Socket sfd = null;
	static DataInputStream EntradaSocket;
	static DataOutputStream SalidaSocket;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					sfd = new Socket("127.0.0.1",7001);
					EntradaSocket = new DataInputStream(new BufferedInputStream(sfd.getInputStream()));
					SalidaSocket = new DataOutputStream(new BufferedOutputStream(sfd.getOutputStream()));
				}
				catch (UnknownHostException uhe)
				{
					System.out.println("No se puede acceder al servidor.");
					System.exit(1);
				}
				catch (IOException ioe)
				{
					System.out.println("Comunicación rechazada.");
					System.exit(1);
				}
				Principal frame = new Principal();
				frame.setVisible(true);
				
				
				
				FileInputStream queseria;
				FileOutputStream queseria2;
				ObjectInputStream queseriaRead;
				ObjectOutputStream queseriaWrite;
				try {
					queseria = new FileInputStream ("queseria.dat");
					queseriaRead = new ObjectInputStream(queseria);
					Administracion temp = (Administracion)queseriaRead.readObject();
					Administracion.setAdministracion(temp);
					Administracion.getInstance().inicializarCodigos();
					queseria.close();
					queseriaRead.close();
				} catch (FileNotFoundException e) {
					try {
						queseria2 = new  FileOutputStream("queseria.dat");
						queseriaWrite = new ObjectOutputStream(queseria2);
						queseriaWrite.writeObject(Administracion.getInstance());
						queseria2.close();
						queseriaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException e) {
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
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
		
		//inicio de los cambios para archivos
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				FileOutputStream queseria2;
				ObjectOutputStream queseriaWrite;
				try {
					queseria2 = new  FileOutputStream("queseria.dat");
					queseriaWrite = new ObjectOutputStream(queseria2);
					queseriaWrite.writeObject(Administracion.getInstance());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		//fin
		
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

	public static void crearRespaldo(String codigoFactura) {
		try {
			SalidaSocket.writeUTF(codigoFactura);
			SalidaSocket.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

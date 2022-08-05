package logico;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;



public class Administracion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Cliente> todosLosClientes;
	private ArrayList<Queso> todosLosQuesos;
	private ArrayList<Factura> todasLasFacturas;
	private static Administracion admin = null;
	public static int generadorCodeFactura=1;
	public static int generadorCodeCilindro=1;
	public static int generadorCodeEsfera=1;
	public static int generadorCodeHueco=1;
	
	
	private Administracion() {
		super();
		this.todasLasFacturas = new ArrayList<Factura>();
		this.todosLosClientes = new ArrayList<Cliente>();
		this.todosLosQuesos = new ArrayList<Queso>();
	}
	
	public static Administracion getInstance() {
		if (admin==null) {
			admin=new Administracion();
		}
		return admin;
	}
	//nuevo
	private void contarFacturas() {
		int i=0;
		for (Factura factura : todasLasFacturas) {
			i++;
		}
		generadorCodeFactura=i+1;
	}
	private void contarEsferico() {
		int i=0;
		for (Queso queso: getTodosLosQuesos()) {
			if (queso instanceof Esferico) {
				i++;
			}
		}
		generadorCodeEsfera=i+1;
	}
	private void contarCilindro() {
		int i=0;
		for (Queso queso: getTodosLosQuesos()) {
			if (queso instanceof Cilindrico) {
				if (!(queso instanceof Hueco)) {
					i++;
				}
				
			}
		}
		generadorCodeCilindro=i+1;
	}
	private void contarHueco() {
		int i=0;
		for (Queso queso: getTodosLosQuesos()) {
			if (queso instanceof Hueco) {
				i++;
			}
		}
		generadorCodeHueco=i+1;
	}
	public void inicializarCodigos() {
		contarCilindro();
		contarEsferico();
		contarFacturas();
		contarHueco();
	}
	//fin
	public ArrayList<Cliente> getTodosLosClientes() {
		return todosLosClientes;
	}

	public ArrayList<Queso> getTodosLosQuesos() {
		return todosLosQuesos;
	}
	
	public ArrayList<Factura> getTodasLasFacturas() {
		return todasLasFacturas;
	}

	public static int getGeneradorCodeFactura() {
		return generadorCodeFactura;
	}

	public static int getGeneradorCodeCilindro() {
		return generadorCodeCilindro;
	}

	public static int getGeneradorCodeEsfera() {
		return generadorCodeEsfera;
	}

	public static int getGeneradorCodeHueco() {
		return generadorCodeHueco;
	}

	public Cliente buscarClienteByCedula(String cedula){
		Cliente aux=null;
		boolean encontrado=false;
		int i = 0;
		while(encontrado==false&&i<todosLosClientes.size()) {
			if(todosLosClientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				aux=todosLosClientes.get(i);
				encontrado=true;
			}
			i++;
		}
		return aux;
	}
	
	public Queso buscarQuesoByCode(String codigo){
		Queso aux=null;
		boolean encontrado=false;
		int i = 0;
		while(encontrado==false&&i<todosLosQuesos.size()) {
			if(todosLosQuesos.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				aux=todosLosQuesos.get(i);
				encontrado=true;
			}
			i++;
		}
		return aux;
	}
	
	public void insertarQueso(Queso queso) {
		if(queso instanceof Hueco) {
			generadorCodeHueco++;
		}
		else if(queso instanceof Cilindrico) {
			generadorCodeCilindro++;
		}
		if(queso instanceof Esferico) {
			generadorCodeEsfera++;
		}
		todosLosQuesos.add(queso);
	}
	
	public void insertarFactura(Factura factura, Cliente cliente) {
		todasLasFacturas.add(factura);
		cliente.getMisFacturas().add(factura);
		generadorCodeFactura++;
	}
	
	public void insertarCliente(Cliente cliente) {
		todosLosClientes.add(cliente);
	}

	public Factura buscarFacturaByCode(String codigo) {
		Factura aux=null;
		boolean encontrado=false;
		int i=0;
		while (encontrado==false&&i<todasLasFacturas.size()) {
			if(todasLasFacturas.get(i).getCodigoFactura().equalsIgnoreCase(codigo)) {
				aux=todasLasFacturas.get(i);
				encontrado=true;
			}
			i++;
		}
		return aux;
	}
	
	public void insertarQuesosEnFactura(Factura factura, ArrayList<Queso> quesos) {
		for (Queso queso : quesos) {
			factura.getMisQuesos().add(queso);
		}
	}
	
	//cambios tarea 3

	public static void setAdministracion(Administracion administracion) {
		Administracion.admin = administracion;
	}
	
	//fin
	
}

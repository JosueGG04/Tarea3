package logico;

import java.util.ArrayList;

public class Cliente {
	private String cedula;
	private String nombre;
	private String Telefono;
	private String Direccion;
	private ArrayList<Factura> misFacturas; 
	


	public Cliente(String cedula, String nombre, String telefono, String direccion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		Telefono = telefono;
		Direccion = direccion;
		this.misFacturas = new ArrayList<Factura>();
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	
	
}

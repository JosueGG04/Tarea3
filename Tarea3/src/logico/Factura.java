package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoFactura;
	private ArrayList<Queso> misQuesos;
	private String cedulaCliente;
	private Date fecha; 

	public Factura(String codigoFactura, String cedulaCliente) {
		super();
		this.codigoFactura = codigoFactura;
		this.misQuesos = new ArrayList<Queso>();
		this.cedulaCliente = cedulaCliente;
		this.fecha = new Date();
	}

	public String getCodigoFactura() {
		return codigoFactura;
	}

	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}
	
	public float MontoFactura() {
		float monto=0;
		for (Queso queso : misQuesos){
			monto+=queso.precio();	
		}
		return monto;
	}
	
}

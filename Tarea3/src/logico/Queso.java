package logico;

import java.io.Serializable;

public abstract class Queso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String codigo;
	protected float precioBase;
	protected float precioUnitario;
	protected boolean disponible; 
	protected double pi=Math.PI;
	
	public abstract float volumen();

	public Queso(String codigo, float precioBase, float precioUnitario) {
		super();
		this.codigo = codigo;
		this.precioBase = precioBase;
		this.precioUnitario = precioUnitario;
		disponible=true;
	}



	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getCodigo() {
		return codigo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public float precio() {
		float precioReal=precioBase+(precioUnitario*volumen());
		return precioReal;
	}	
}

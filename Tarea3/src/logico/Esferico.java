package logico;

public class Esferico extends Queso {

	protected float radio;
	

	public Esferico(String codigo,float precioBase, float precioUnitario, float radio) {
		super(codigo, precioBase, precioUnitario);
		this.radio = radio;
	}


	public float getRadio() {
		return radio;
	}


	public float volumen() {
		float vol=(float) ((4.0/3)*pi*Math.pow(radio, 3));
		return vol;
	}

}

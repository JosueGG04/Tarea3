package logico;

public class Cilindrico extends Queso {
	
	protected float longitud;
	protected float radio;


	public Cilindrico(String codigo, float precioBase, float precioUnitario, float radio,float longitud) {
		super(codigo, precioBase, precioUnitario);
		this.longitud = longitud;
		this.radio = radio;
	}


	public float getLongitud() {
		return longitud;
	}


	public float getRadio() {
		return radio;
	}


	public float volumen() {
		float vol=(float)(longitud*(pi*Math.pow(radio, 2)));
		return vol;
	}

}

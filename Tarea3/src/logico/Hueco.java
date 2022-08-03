package logico;

public class Hueco extends Cilindrico {

	private float radioInterior;

	public Hueco(String codigo, float precioBase, float precioUnitario, float radio, float longitud,
			float radioInterior) {
		super(codigo, precioBase, precioUnitario, longitud, radio);
		this.radioInterior = radioInterior;
	}


	public float getRadioInterior() {
		return radioInterior;
	}


	public void setRadioInterior(float radioInterior) {
		this.radioInterior = radioInterior;
	}
	
	public float volumen() {
		float vol=(float)(longitud*(pi*Math.pow(radio-radioInterior, 2)));
		return vol;
	}
	
	

}

package servidor;

import logico.Administracion;
import logico.Cilindrico;
import logico.Esferico;
import logico.Factura;
import logico.Hueco;
import logico.Queso;
import logico.Utilidades;

public class FormatoFactura {

	public String creandoStringFactura(Factura factura) {
		String aux = "";
		aux.concat("==========================================================\n");
		aux.concat("¡Bienvenidos!\n");
		aux.concat("Fecha: " + Utilidades.formatoFecha.format(factura.getFecha())+"\n");
		aux.concat("Nombre: "+Administracion.getInstance().buscarClienteByCedula(factura.getCedulaCliente()).getNombre()+"\n");
		aux.concat("Teléfono: "+Administracion.getInstance().buscarClienteByCedula(factura.getCedulaCliente()).getTelefono()+"\n");
		aux.concat("==========================================================\n");
		aux.concat("Quesos:");
		for (Queso queso : factura.getMisQuesos()) {
			aux.concat(queso.getCodigo()+" Vol "+String.format("%.2f", queso.volumen())+" $"+String.format("%.2f", queso.precio())+"\n");
		}
		aux.concat(String.format("$%.2f", factura.MontoFactura())+"\n");
		aux.concat("==========================================================\n");
		aux.concat("¡Gracias por preferirnos!");
		System.out.print(aux);
		return aux;
	}
}


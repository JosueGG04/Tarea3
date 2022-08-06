package servidor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import logico.Administracion;
import logico.Cilindrico;
import logico.Esferico;
import logico.Factura;
import logico.Hueco;
import logico.Queso;
import logico.Utilidades;

public class FormatoFactura {

	public static String creandoStringFactura(Factura factura) {
		String aux = "";
		aux =aux.concat("==========================================================\n");
		aux =aux.concat("¡Bienvenidos!\n");
		aux =aux.concat("Fecha: " + Utilidades.formatoFecha.format(factura.getFecha())+"\n");
		aux =aux.concat("Nombre: "+Administracion.getInstance().buscarClienteByCedula(factura.getCedulaCliente()).getNombre()+"\n");
		aux =aux.concat("Teléfono: "+Administracion.getInstance().buscarClienteByCedula(factura.getCedulaCliente()).getTelefono()+"\n");
		aux =aux.concat("==========================================================\n");
		aux =aux.concat("Quesos:\n");
		for (Queso queso : factura.getMisQuesos()) {
			aux =aux.concat(queso.getCodigo()+" Vol "+String.format("%.2f", queso.volumen())+" $"+String.format("%.2f", queso.precio())+"\n");
		}
		aux =aux.concat(String.format("Total: $%.2f", factura.MontoFactura())+"\n");
		aux =aux.concat("==========================================================\n");
		aux =aux.concat("¡Gracias por preferirnos!\n");
		return aux;
	}
	
	public static void crearArchivo(Factura f) {
		File archivo = new File (f.getCodigoFactura()+".txt");
        FileWriter escritor;
		try {
			escritor = new FileWriter(archivo);
			String info = creandoStringFactura(f);
        for (int i=0; i<info.length(); i++)
            escritor.write(info.charAt(i));
        escritor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


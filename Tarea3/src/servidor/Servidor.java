package servidor;

import java.io.*;
import java.net.*;
import java.util.*;


public class Servidor extends Thread
{
	
	public static void main (String args[])
	{
		ServerSocket sfd = null;
		try
		{
			sfd = new ServerSocket(7001);
		}
		catch (IOException ioe)
		{
			System.out.println("Comunicación rechazada."+ioe);
			System.exit(1);
		}

		while (true)
		{
			try
			{
				Socket nsfd = sfd.accept();
				DataInputStream FlujoLectura;
				DataOutputStream FlujoEscritura;
				FlujoLectura = new DataInputStream(new BufferedInputStream(nsfd.getInputStream()));
				FlujoEscritura = new DataOutputStream(new BufferedOutputStream(nsfd.getOutputStream()));
				while(true)
				{
					try{
						String linea = FlujoLectura.readUTF();
						if (!linea.equals("")) {
							File archivo = new File (linea+".txt");
							FileInputStream lector = new FileInputStream(archivo);
							BufferedReader mejorado = new BufferedReader(new InputStreamReader(lector));
							File respaldo = new File (linea+"_respaldo.txt");
							FileOutputStream escritor = new FileOutputStream(respaldo);
							BufferedWriter escritorMejorado = new BufferedWriter(new OutputStreamWriter(escritor));
							String linea2="";
							while ((linea2=mejorado.readLine())!=null) {
								escritorMejorado.append(linea2);
								escritorMejorado.newLine();
							}
							
							escritorMejorado.close();
							mejorado.close();
							
						}
					}
					catch(IOException ioe){
						
					}
				}

			}
			catch(IOException ioe)
			{
				System.out.println("Error: "+ioe);
			}
		}
	}


}
package lectorExcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileManager {

	File[] archivos;
	File directorio;
	//Reader reader = new Reader("pedidosPrueba.xlsx");

	public FileManager(String nombreDirectorio) {
		directorio = new File(nombreDirectorio);
	}
	
	// Esta es la función principal, recorre todos los archivos que se encuentren en
	// el directorio que se reciba por parámetro. 
	public void recorrerArchivos() {
		archivos = directorio.listFiles(); 
			
		for (int i=0; i<archivos.length; i++){
			copyFile(archivos[i],"pedidosPrueba.xlsx");
			
			Date horaIni = new Date();
			
			System.out.println(archivos[i]);

			Date horaFin = new Date();
			
			
			imprimirDuracion(horaIni,horaFin);
		}		
	}

	// A partir de un directorio y el nombre de un archivo, retorna el 
	// archivo especificado correspondiente al directorio.
	public File find(String path, String nombre) {
		File archivo = new File(path);
		if (nombre.equalsIgnoreCase(archivo.getName())) 
			return archivo;
		if (archivo.isDirectory()) {
			for (String s : archivo.list()) {
				File archivoEncontrado = find(path + File.separator + s, nombre);
				if (archivoEncontrado != null) 
					return archivoEncontrado;
			}
		}
		return null;
	}
	
	public File copyFile(File origen, String nombreDestino) {
		File outFile = new File(nombreDestino);

		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in = new FileInputStream(origen);
			out = new FileOutputStream(outFile);
			
			int c;
			while( (c = in.read() ) != -1)
				out.write(c);

			in.close();
			out.close();
		} catch(IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
		return outFile;
	}

	private void imprimirDuracion(Date hrIni, Date hrFin) {
		long millis = hrFin.getTime() - hrIni.getTime(); 
		
		String ret = String.format("%d min, %d sec", 
				TimeUnit.MILLISECONDS.toMinutes(millis),
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
		);
		
		System.out.println("Tiempo de ejecución: "+ ret);
	}
}


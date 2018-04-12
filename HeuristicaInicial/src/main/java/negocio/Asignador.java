package negocio;

import java.util.List;

import reader.ExcelReader;

public class Asignador {
	
	ExcelReader reader;
	List<Clase> clases;
	List<Aula> aulas;
	List<Asignacion> asignaciones;
	
	
	public void asignar() {
		clases = reader.readClases("Pedidos");
		clases = reader.readClases("Aulas");
		
		for(Asignacion asignacion: asignaciones) {
		
		}
	}
	

}

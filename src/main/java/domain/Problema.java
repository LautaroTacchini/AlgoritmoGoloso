package domain;

import java.util.Set;
import java.util.TreeSet;

public class Problema {
	
	Set<Aula> aulas;
	Set<Clase> clases;
	
	// Pueda que no tenga senntido tener las preasignaciones en el problema.
	// Habria que fijarse si puede ir.
	Set<Asignacion> preasignadas;

	public Problema(Set<Aula> aulas, Set<Clase> clases, Set<Asignacion> preasignadas) {
		// TreeSet se encarga de que los conjuntos estén ordenados, lo que 
		// simplifica el debugging.
		this.aulas = new TreeSet<Aula>(aulas);
		this.clases = new TreeSet<Clase>(clases);
		this.preasignadas = new TreeSet<Asignacion>(preasignadas);
	}
}

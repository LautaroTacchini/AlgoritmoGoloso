package domain;

public class Aula implements Comparable<Aula>{
	
	CodigoAula codAula;
	public String edificio;
	public String nombre;
	public int capacidad;
	public int pizarrones;
	
	public Aula(String edificio, String nombre, int capacidad, int pizarrones){//TODO Aca capacidad puede ir a otro lado. Esta repetido CodigoDeAula.
		codAula = new CodigoAula(edificio,nombre);
		this.edificio = edificio;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.pizarrones = pizarrones;
	}
		
	public int compareTo(Aula that) {
		return this.codAula.hashCode() - that.codAula.hashCode();
	}
	
	public String toString() {
		return nombre;
	}
}

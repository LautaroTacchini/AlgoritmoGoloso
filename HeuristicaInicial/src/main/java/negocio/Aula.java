package negocio;

public class Aula implements Comparable<Aula>{
	
	CodigoDeAula codAula;
	int capacidad;
	
	public Aula(CodigoDeAula codAula, int capacidad){
		this.codAula = codAula;
		this.capacidad = capacidad;
	}
	
	public int compareTo(Aula that) {
		return this.codAula.hashCode() - that.codAula.hashCode();
	}
	
	public String toString() {
		return codAula.toString();
	}
}

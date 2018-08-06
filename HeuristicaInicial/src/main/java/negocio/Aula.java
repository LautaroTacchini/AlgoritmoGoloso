package negocio;

public class Aula implements Comparable<Aula>{
	
	CodigoDeAula codAula;
	int pab;
	int numero;
	int capacidad;
	
	public Aula(int pab, int numero, int capacidad){//TODO Aca capacidad puede ir a otro lado. Esta repetido CodigoDeAula.
		codAula = new CodigoDeAula(pab,numero);
		this.pab = pab;
		this.numero = numero;
		this.capacidad = capacidad;
	}
	
	public int compareTo(Aula that) {
		return this.codAula.hashCode() - that.codAula.hashCode();
	}
	
	public String toString() {
		return codAula.toString();
	}
}

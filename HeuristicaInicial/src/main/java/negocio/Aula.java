package negocio;

public class Aula implements Comparable<Aula>{
	Integer id;
	int pab;
	int numero;
	int capacidad;
	
	public Aula(Integer pab, Integer numero, int capacidad){
		this.id  = Integer.valueOf(pab.toString() + numero.toString());
		this.pab = pab;
		this.numero = numero;
		this.capacidad = capacidad;
	}
	
	public int compareTo(Aula that) {
		return this.id.hashCode() - that.id.hashCode();
	}
}

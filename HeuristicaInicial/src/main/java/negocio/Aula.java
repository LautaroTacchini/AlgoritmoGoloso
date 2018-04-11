package negocio;

public class Aula implements Comparable<Aula>{
	Integer id;
	int capacidad;
	
	public int compareTo(Aula that) {
		return this.id.hashCode() - that.id.hashCode();
	}

//	public String toString() {
//		properties.findAll { !(it.key in ["class", "metaClass"]);
//	}
}

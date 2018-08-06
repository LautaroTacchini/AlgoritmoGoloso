package domain.logic;

public class Asignacion {
	private Clase clase;
	private Aula aula;
	
	public Asignacion (Clase clase, Aula aula) {
		this.clase = clase;
		this.aula = aula;
	}
	
	public Clase getClase() {
		return clase;
	}
	
	public Aula getAula() {
		return aula;
	}
	
	@Override
	public String toString() {
		return clase + "-" + aula;
	}

}

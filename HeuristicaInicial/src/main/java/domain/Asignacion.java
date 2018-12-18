package domain;

public class Asignacion {
	public Clase clase;
	public Aula aula;
	
	public Asignacion (Clase c, Aula a) {
		clase = c;
		aula = a;
	}
	
	public String toString() {
		return clase + "-" + aula;
	}

}

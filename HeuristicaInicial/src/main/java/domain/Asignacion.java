package domain;

public class Asignacion {
	Clase clase;
	Aula aula;
	
	Asignacion (Clase c, Aula a) {
		clase = c;
		aula = a;
	}
	
	public String toString() {
		return clase + "-" + aula;
	}

}

package domain.logic;

public class Preferencia {
	Clase clase;
	String edificio;
	
	public Preferencia(Clase clase, String edificio){
		this.clase = clase;
		this.edificio = edificio;
	}
	
	public Clase getClase() {
		return clase;
	}
	
	@Override
	public String toString() {
		return clase + "-" + edificio;
	}


}

package domain;

public class Preferencia {
	public Clase clase;
	public String edificio;
	
	Preferencia(Clase clase, String edificio){
		this.clase = clase;
		this.edificio = edificio;
	}
	
	@Override
	public String toString() {
		return clase + "-" + edificio;
	}


}

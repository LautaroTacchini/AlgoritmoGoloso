package domain;

import java.util.Set;

public class Preferidor {
	
	Set<String> edificios;
	
	public Preferidor(Set<String> edif) {
		edificios = edif;
	}
	
	public Preferencia preferir(Clase clase, String edificio) {
		if(!edificios.contains(edificio)) {
			throw new RuntimeException("No se encontró el edificio: " + edificio);	
		}
		return new Preferencia(clase,edificio);
	}

}

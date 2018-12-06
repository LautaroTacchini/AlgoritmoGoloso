package core;

import java.util.HashMap;
import java.util.Map;

public enum AulaEnum {
	EDIFICIO, AULA, CAPACIDAD, PIZARRONES;
		
	static Map<String,AulaEnum> map = new HashMap<String,AulaEnum>();

	static {
		map.put("Pabellón",EDIFICIO);
		map.put("Aula",AULA);
		map.put("Capacidad",CAPACIDAD);
		map.put("Pizarrones", PIZARRONES);
	}
	
	public String toString() {
		switch(this) {
			case EDIFICIO: return "EDIFICIO";
		    case AULA: return "AULA";
		    case CAPACIDAD: return "CAPACIDAD";
		    case PIZARRONES: return "PIZARRONES";
		    default: throw new IllegalArgumentException();
		}
	}
}

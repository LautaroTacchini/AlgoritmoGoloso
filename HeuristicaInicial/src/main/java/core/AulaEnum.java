package core;

import java.util.HashMap;
import java.util.Map;

public enum AulaEnum {
	EDIFICIO, AULA,CAPACIDAD;
		
	static Map<String,AulaEnum> map = new HashMap<>();

	static {
		map.put("Pabellón",EDIFICIO);
		map.put("Aula",AULA);
		map.put("Capacidad",CAPACIDAD);
	}
	
	public String toString() {
		switch(this) {
			case EDIFICIO: return "EDIFICIO";
		    case AULA: return "AULA";
		    case CAPACIDAD: return "CAPACIDAD";
		    default: throw new IllegalArgumentException();
		}
	}
}

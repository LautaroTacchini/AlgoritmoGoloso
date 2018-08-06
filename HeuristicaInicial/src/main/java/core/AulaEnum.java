package core;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum AulaEnum {
	EDIFICIO, AULA,CAPACIDAD;
	
//	static Map<AulaEnum,String> map = new EnumMap<>(AulaEnum.class);
//	static {
//		map.put(EDIFICIO, "Pabellón");
//		map.put(AULA, "Aula");
//		map.put(CAPACIDAD, "Capacidad");
//	}
	
	static Map<String,AulaEnum> map = new HashMap<>();
	static {
		map.put("Pabellón",EDIFICIO);
		map.put("Aula",AULA);
		map.put("Capacidad",CAPACIDAD);
	}

	
	public static AulaEnum parse(String str) {
		str = str.toUpperCase();
		
		return valueOf(str.toUpperCase().replaceAll("ÁÉÍÓÚ", "AEIOU"));
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

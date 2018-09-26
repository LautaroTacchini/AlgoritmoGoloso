package core;

import java.util.HashMap;
import java.util.Map;

// otro nombre ORDEN: COLOCACION DE LAS COSAS
public enum PedidoEnum {
	NOMBRE,DESDE,HASTA,DIA,KANT,AULA,EDIFICIO;
	
	static Map<String,PedidoEnum> map = new HashMap<>();

	//FIXME esto está hardcodeado, habría que definir los nombres que se encuentran en el excel desde el archivo 
	//		de configuración.
	static {
		map.put("Asignatura",NOMBRE);
		map.put("Desde",DESDE);
		map.put("Hasta",HASTA);
		map.put("Día",DIA);
		map.put("Kant",KANT);
		map.put("Aula",AULA);
		map.put("Pab.",EDIFICIO);
	}
	
}

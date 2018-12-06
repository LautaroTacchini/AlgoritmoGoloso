package core;

import java.util.Map;

import domain.Asignacion;
import domain.Aula;
import domain.Clase;

class Puntuador {	
	Map<String,Integer> puntajes;

	Problema problema;
	
	Puntuador(Map<String, Integer> puntajes, Problema problema){
		this.puntajes = puntajes;
		this.problema = problema;
		
		this.puntajes.put("alumnoSinAula", 100);
		this.puntajes.put("alumnoConCambioDePabellon",1);
		this.puntajes.put("pizarronMenos",10);
		this.puntajes.put("alumnoExtraCapacidad", 1);
	}
	
	public int puntaje(Clase clase, Aula aula) {
		return 0;
	}
	
	public int puntajePorNoAsignar(Clase clase) {
		// Recorro las asignaciones y comparo cada una de ellas
		// con la clase recibida por parametro, si la clase recibida
		// es igual a alguna de las preasignadas correspondientes al 
		// problema, se le asigna puntaje 0. FIXME
		for(Asignacion a : problema.preasignadas) {
			if(a.toString() == clase.id.toString()) {
				return 0;
			}
		}
		
		return - puntajes.get("alumnoSinAula")*clase.cantidadInscriptos;	
	}
	
	// TODO documentar. 
	//
	public int puntaje2(Clase c, Aula a) {
		// Cambios de pabellon
		int puntos = (c.edifPreferido.equals("INDISTINTO") ?  0 : 
						(a.edificio == c.edifPreferido ? 0 
							: -puntajes.get("alumnoConCambioDePabellon") * c.cantidadInscriptos));
				
		// Pizarrones menos de los pedidos
		puntos -= (c.minimoPizarrones <= a.pizarrones? 0 : 
						c.minimoPizarrones - a.pizarrones)*puntajes.get("pizarronMenos");
		
		// Capacidad del aula excedida
		puntos -= (c.cantidadInscriptos <= a.capacidad? 0 : 
						c.cantidadInscriptos - a.capacidad)*puntajes.get("alumnoExtraCapacidad"); 
		
		// confiamos en que los inscriptos quepan en la capacidad
		int exceso = a.capacidad / ((c.cantidadInscriptos + 2) / 2);
		if (3 <= exceso)
			puntos -= exceso - 3;
						
		return puntos; 
	}
}
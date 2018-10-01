package core;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import domain.logic.Aula;
import domain.logic.Clase;

public class Asignador {
		
	//ASIGNACIONES
	Map<Aula,BitSet> disponibilidad = new HashMap <>(); //aca voy tachando
	
	public Asignador() {
//		for(Aula a: aulas) {
//			BitSet bitSet = new BitSet();
//			// Todos los dias de la semana, de lunes a domingo,de 0 a 24 con intervalos de 30 min.
//			bitSet.set(24*2*7); 
//			map.put(a, bitSet);
//		}
	}
		
	// TODO
	// Ver si esta aula no fue asignada a otra materia.
	// Tengo este aula y esta materia, y quiero que la asignes.
	// Si hay un horario no disponible, no se puede preasignar!!!!!!!!
	public void asignar(Aula aula, Clase clase) {
		// Validar si la capacidad del aula es compatible con la clase que se quiere asignar.
    	if(!clase.puedeUsar(aula))
    		throw new RuntimeException("Capacidad no valida");
    	
	}
}
	

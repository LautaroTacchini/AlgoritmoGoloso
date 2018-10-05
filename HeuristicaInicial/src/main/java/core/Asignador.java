package core;

import java.util.BitSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import domain.logic.Aula;
import domain.logic.Clase;
import domain.logic.DiaSemana;

public class Asignador {
		
	//ASIGNACIONES
	Map<Aula,BitSet> disponibilidad = new HashMap <>(); //aca voy tachando
	
	public Asignador() {}
		
	// Ver si esta aula no fue asignada a otra materia.
	// Tengo este aula y esta materia, y quiero que la asignes.
	// Si hay un horario no disponible, no se puede preasignar!!!!!!!!
	public void asignar(Clase clase,Aula aula) {
		
		// Validar si la capacidad del aula es compatible con la clase que se quiere asignar.
    	if(!clase.puedeUsar(aula))
    		throw new RuntimeException("Capacidad no valida");
    	
    	BitSet bitset = disponibilidad.get(aula);
    	if(bitset == null) {
    		// Todos los dias de la semana, de lunes a domingo,de 0 a 24 con intervalos de 30 min.
    		bitset = new BitSet(24*2*7);
    		disponibilidad.put(aula, bitset);
    	}
    	
    	int desde = obtenerIndice(clase.diaSemana, clase.horaDesde);
    	int hasta = obtenerIndice(clase.diaSemana, clase.horaHasta);
    	BitSet intervaloClase = bitset.get(desde,hasta);
    	
    	// Si el intervalo correspondiente a ese dia está vacío, significa que el aula está disponible en ese horario.
    	if(intervaloClase.isEmpty()) 
    		bitset.set(desde, hasta);	
	}
	
	public void preferir(Clase clase,String edificio) {
		//FIXME verificar si este edificio existe
		
	}
	
	private int obtenerIndice(DiaSemana dia, Date hora) {
		return dia.ordinal()*48 + hora.getHours()*2 + (hora.getMinutes() > 0 ? 1 : 0);
	}
}
	

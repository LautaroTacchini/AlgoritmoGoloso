package core;

import java.util.Map;
import java.util.Set;

import domain.Asignacion;
import domain.Aula;
import domain.Clase;

public class Puntuador {	
	Map<String,Integer> puntajes;
	
	public Puntuador(Map<String, Integer> puntajes){
		this.puntajes = puntajes;
		
		this.puntajes.put("alumnoSinAula", 100);
		this.puntajes.put("alumnoConCambioDePabellon",1);
		this.puntajes.put("pizarronMenos",10);
		this.puntajes.put("alumnoExtraCapacidad", 1);
	}
	
	public int puntajePorNoAsignar(Clase clase) {
		return - puntajes.get("alumnoSinAula")*clase.cantidadInscriptos;	
	}
	
	// Dada una clase y un aula, esta clase se encarga de calcular los puntos correspondientes
	// a ese par clase-aula que representa una asignación. 
	public int puntaje(Clase c, Aula a) {
		// Cambios de pabellon
		int puntos = (c.edifPreferido.equals("INDISTINTO") ?  0 : 
						(a.edificio.equals(c.edifPreferido)) ? 0 
							: -puntajes.get("alumnoConCambioDePabellon") * c.cantidadInscriptos);
				
		// Pizarrones menos de los pedidos
		puntos -= (c.minimoPizarrones <= a.pizarrones? 0 : 
						c.minimoPizarrones - a.pizarrones)*puntajes.get("pizarronMenos");
		
		// Capacidad del aula excedida
		puntos -= (c.cantidadInscriptos <= a.capacidad? 0 : 
						c.cantidadInscriptos - a.capacidad) *puntajes.get("alumnoExtraCapacidad"); 
		
		// confiamos en que los inscriptos quepan en la capacidad
		int exceso = a.capacidad / ((c.cantidadInscriptos + 2) / 2);

		if (3 <= exceso)
			puntos -= exceso - 3;
						
		return puntos; 
	}
	
	public int puntajeNoComparteC1(Clase c1, Aula a1, Set<Asignacion> asignaciones) {
		int penalidad = 0;
		for (Asignacion asignacion: asignaciones) {
			if(c1.id > asignacion.clase.id 
				&& tienenVariableY(c1, asignacion.clase) 
				&& a1.compareTo(asignacion.aula)!=0) {
					penalidad -= (int) ((0.5*(c1.cantidadInscriptos + asignacion.clase.cantidadInscriptos)*0.10) + 1);
			}
		}
		return penalidad;
	}

	// TODO: ¿Debería preguntarse si ambas asignaciones requieren un mismo tipo
	// de aula? ¿Qué pasa si una asignación necesita un labo y la otra no?
	boolean tienenVariableY(Clase c1, Clase c2) {
		return c1 != c2 &&
				!c1.seSolapaCon(c2.minutoInicial()) && !c1.seSolapaCon(c2.minutoFinal()) &&
				!c2.seSolapaCon(c1.minutoInicial()) && !c2.seSolapaCon(c1.minutoFinal()) &&
				c1.comparteComisionCon(c2);
	}
}
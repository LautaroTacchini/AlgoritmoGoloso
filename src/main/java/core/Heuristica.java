package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Asignacion;
import domain.Asignador;
import domain.Aula;
import domain.AulaComparador;
import domain.Preferencia;
import domain.PreferenciaComparador;

public class Heuristica {
	
	Asignador asig;
	List<Aula> aulas;
	
	Puntuador puntuador;
	
	public int puntaje = 0;

	public Heuristica(Set<Aula> aulas, Puntuador puntuador, Asignador asig){
		
		this.asig = asig;
		
		this.aulas = new ArrayList<Aula>(aulas);
	
		this.puntuador = asig.puntuador;
		
		puntaje = asig.puntajePreasignaciones;
	}
	
	public Set<Asignacion> asignar(Set<Preferencia> prefs) {
		Set<Asignacion> ret = new HashSet<Asignacion>();

		while(!prefs.isEmpty()) {
			Preferencia prefConMaxKant = Collections.max(prefs,new PreferenciaComparador());
			Aula a = elegirAula(prefConMaxKant);

			if(a != null) {
				// Actualizacion del puntaje del aula a
				puntaje += puntuador.puntaje(prefConMaxKant.clase,a);
				ret.add(asig.asignar(prefConMaxKant.clase,a));
				System.out.println(prefConMaxKant.clase.id + "	"+
						prefConMaxKant.clase.nombre + "	"+ 
						prefConMaxKant.clase.comision + "	"+
						prefConMaxKant.clase.diaSemana +"	"+
						prefConMaxKant.clase.edifPreferido + "	"+
						a.edificio + "	"+ 
						a.nombre+ "	" + 
						a.capacidad+ "	"+ 
						prefConMaxKant.clase.cantidadInscriptos);
			}
			
			else {
				puntaje += puntuador.puntajePorNoAsignar(prefConMaxKant.clase);
				System.out.println(prefConMaxKant.clase.id + "	"+
						prefConMaxKant.clase.nombre + "	"+ 
						prefConMaxKant.clase.comision + "	"+
						prefConMaxKant.clase.diaSemana +"	"+
						prefConMaxKant.clase.edifPreferido + "	"+
						"	"+ 
						"	"+ 
						"	"+ 
						prefConMaxKant.clase.cantidadInscriptos);
			}
			
			prefs.remove(prefConMaxKant);
		}
		return ret;
	}
			
	private Aula elegirAula (Preferencia pref) {
		int mejorPuntaje = Integer.MIN_VALUE;
		Aula ret = null;
		Collections.sort(this.aulas, new AulaComparador());
		    	
		// Aca me fijo que aula engancha con la preferencia
		for(Aula a: aulas) {
			if(!asig.sePuedeAsignar(pref.clase,a) || !asig.puedeUsar(pref.clase,a))
				continue;
			
			if(mejorPuntaje >= puntuador.puntaje(pref.clase, a)) 
				continue;
						
			mejorPuntaje = puntuador.puntaje(pref.clase, a);	
			ret = a;
		}
		return ret;		
	}

}

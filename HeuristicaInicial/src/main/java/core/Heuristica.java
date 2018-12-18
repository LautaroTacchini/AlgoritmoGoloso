package core;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Asignacion;
import domain.Asignador;
import domain.Aula;
import domain.AulaComparador;
import domain.Preferencia;

public class Heuristica {
	
	Asignador asig;
	List<Aula> aulas;
	Map<Aula,BitSet> disp;
	
	Puntuador puntuador;
	
	public int puntaje = 0;
	
	public Heuristica(Set<Aula> aulas, Map<Aula, BitSet> disp, Puntuador puntuador){
		
		asig = new Asignador(disp);
		this.disp = disp;
		
		this.aulas = new ArrayList<Aula>(aulas);
		Collections.sort(this.aulas, new AulaComparador());
		
		this.puntuador = puntuador;
		
	}
	
	public Set<Asignacion> asignar(Set<Preferencia> prefs) {
		Set<Asignacion> ret = new HashSet<Asignacion>();
		
//		for(Preferencia p: prefs) {
//			Aula a = elegirAula(p);
//			if(a != null){
//				ret.add(asig.asignar(p.clase,a));
//			}
//		}	
		
		while(!prefs.isEmpty()) {
			Preferencia prefConMaxKant = maxKant(prefs);
			Aula a = elegirAula(prefConMaxKant);
			if(a != null && asig.puedeUsar(prefConMaxKant.clase, a)) {
				// Actualizacion del puntaje del aula a
				puntaje += puntuador.puntaje(prefConMaxKant.clase,a);
				ret.add(asig.asignar(prefConMaxKant.clase,a));
			}
			
			else 
				puntaje += puntuador.puntajePorNoAsignar(prefConMaxKant.clase);
			
			prefs.remove(prefConMaxKant);
		}
		return ret;
	}
			
	private Aula elegirAula (Preferencia pref) {
		int mejorPuntaje = Integer.MIN_VALUE;
		Aula ret = null;
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
			
	private Preferencia maxKant(Set<Preferencia> prefs) {
		Preferencia max = Collections.max(prefs, new Comparator<Preferencia>() {
			public int compare(Preferencia p1, Preferencia p2) {
				int cmp;
				if (p1.clase.cantidadInscriptos > p2.clase.cantidadInscriptos)
				   cmp = +1;
				else if (p1.clase.cantidadInscriptos < p2.clase.cantidadInscriptos)
				   cmp = -1;
				else
				   cmp = 0;
				return cmp;
		    }
		});
		return max;
	}	
}

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
	
	public Heuristica(Set<Aula> aulas, Map<Aula, BitSet> disp){
		
		asig = new Asignador(disp);
		this.disp = disp;
		
		this.aulas = new ArrayList<Aula>(aulas);
		Collections.sort(this.aulas, new AulaComparador());
	}
	
	public Set<Asignacion> asignar(Set<Preferencia> prefs) {
		Set<Asignacion> ret = new HashSet<Asignacion>();
		
		while(!prefs.isEmpty()) {
			Preferencia prefConMaxKant = maxKant(prefs);
			Aula a = elegirAula(prefConMaxKant);
			if(a != null) {
				ret.add(asig.asignar(prefConMaxKant.clase,a));
			}
			prefs.remove(prefConMaxKant);
		}
		return ret;
	}
	
	private Aula elegirAula (Preferencia pref) {
		// Aca me fijo que aula engancha con la preferencia
		for(Aula a: aulas) {
			if(asig.sePuedeAsignar(pref.clase,a)) {
				return a;
			}
		}
		return null;		
	}
	
	private Preferencia maxKant(Set<Preferencia> prefs ) {
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

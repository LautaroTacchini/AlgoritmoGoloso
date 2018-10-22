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
	Set<Aula> aulas;
	List<Aula> listaAulas;
	Map<Aula,BitSet> disp;
	
	public Heuristica(Set<Aula> aulas, Map<Aula, BitSet> disp){
		
		asig = new Asignador(disp);
		this.aulas = aulas;
		this.disp = disp;
		
		listaAulas = new ArrayList<>(aulas);
		Collections.sort(listaAulas, new AulaComparador());
	}
	
	public Set<Asignacion> asignar(Set<Preferencia> prefs) {
		Set<Asignacion> ret = new HashSet<>();
		Preferencia max = maxKant(prefs);
		
		// Aca me fijo que aula engancha con la preferencia
		// luego me fijo la disponibilidad
		for(Aula a: listaAulas) {		
			ret.add(asig.asignar(max.clase,a));
		}
		return ret;
	}
	
	private Preferencia maxKant(Set<Preferencia> prefs ) {
		Preferencia max = Collections.max(prefs, new Comparator<Preferencia>() {
			@Override
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

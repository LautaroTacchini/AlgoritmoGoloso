package core;

import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import domain.Asignador;
import domain.Aula;
import domain.Preferencia;

public class Heuristica {
	
	Asignador asignador;
	Set<Preferencia> preferencias;
	Set<Aula> aulas;
	Map<Aula,BitSet> disponibilidad;
	
	public Heuristica(Asignador asig, Set<Preferencia> prefs, Set<Aula> aulas, Map<Aula, BitSet> disp){
		asignador = asig;
		preferencias = prefs;
		this.aulas = aulas;
		disponibilidad = disp;		
	}
	
	public void asignarPorKant () {
		Preferencia max = maxKant();
		
		for(Aula a: aulas) {			
			asignador.asignar(max.clase,a);	
		}
	}

	private Preferencia maxKant() {
		Preferencia max = Collections.max(preferencias , new Comparator<Preferencia>() {
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

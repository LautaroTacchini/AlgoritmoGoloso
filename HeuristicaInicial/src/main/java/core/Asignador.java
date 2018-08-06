package core;

import java.util.BitSet;
import java.util.List;
import java.util.Map;

import domain.logic.Aula;
import domain.logic.Clase;
import excel.module.SheetReader;

public class Asignador {
	
	SheetReader reader;
	List<Clase> clases;
	
	//ASIGNACIONES
	Map<Aula,BitSet> map; //aca voy tachando
	
	public Asignador() {
		List<Aula> aulas = null;
		for(Aula a: aulas) {
			BitSet bitSet = new BitSet();
			bitSet.set(24*2*7); // Todos los dias de la semana, de lunes a domingo,de 0 a 24 con intervalos de 30 min.
			map.put(a, bitSet);
		}
	}
		
	public void asignar() {
		for(Clase c: clases) {	
			List<Aula> aulas = null;
			for(Aula a: aulas) {
				if(c.puedeUsar(a)) {//MAS CHANGUI DE TOLERACIA QUE HABRIA QUE SACARLO DEL EXCEL.
					if(!(map.get(a).get(0)) && !(map.get(a).get(1))) {// Hora de inicio, todas las horas en el medio hasta hora de fin.
						//asignar.
					}
				}
			}		
		}			
	}
}
	

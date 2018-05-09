package negocio;

import java.util.BitSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

import reader.ExcelReader;

public class Asignador {
	
	ExcelReader reader;
	List<Clase> clases;
	
	//ASIGNACIONES
	Map<Aula,BitSet> map; //aca voy tachando
	
	public Asignador() {
		List<Aula> aulas;
		aulas = reader.readAulas("Aulas");
		for(Aula a: aulas) {
			BitSet bitSet = new BitSet();
			bitSet.set(336);// Todos los dias de la semana, de lunes a domingo,de 0 a 24 con intervalos de 30 min.
			map.put(a, bitSet);
		}
	}
	
	public void realizarPreasignaciones() {
		// Recorrer las preasignaciones y tacharlas.
	}
	
	public void asignar() {
		clases = reader.readClases("Pedidos");
		for(Clase c: clases) {	
			DiaSemana diaSemana = c.diaSemana;
			int kant = c.cantidadInscriptos;
			Date horaDesde = c.horaDesde;
			Date horaHasta = c.horaHasta;
			List<Aula> aulas;
			aulas = reader.readAulas("Aulas");
			for(Aula a: aulas) {
				if(a.capacidad <= kant + 5) {//MAS CHANGUI DE TOLERACIA QUE HABRIA QUE SACARLO DEL EXCEL.
					if(!(map.get(a).get(0)) && !(map.get(a).get(1))) {// Hora de inicio, todas las horas en el medio hasta hora de fin.
						//asignar.
					}
				}
			}
				
		}
			/* TODO 
			* Agarro cada clase
			* me fijo el dia y hora de la clase
			* me fijo la cantidad de alumnos de la clase
			* Hay que recorrer el mapa.
			* busco un aula que cumpla con la cantidad de alumnos
			* cuando encuentro, me fijo si esta disponible el dia y horario que necesito.
				* si lo tengo -> asigno
				* sino -> busco el siguiente aula.
			*
			*/
	}
}
	

package lectorExcels;

import java.util.ArrayList;
import java.util.List;

public class Fila {
	List<String> list;
	
	Fila(){
		list = new ArrayList<String>();
	}
	
	public void add(String string) {
		list.add(string);
	}
	
	public String getNombre() {
		return list.get(0);
	}
	
	@Override
	public String toString() {
		String ret = "";
		for(String s: list) {
			ret = ret + ";" +s;
		}
		return ret;
	}
	
	/* Primero hay que leer los datos de cada fila, una vez que ya los tengo, 
	 * construyo un objeto que me resulte conveniente con esos datos, cuando 
	 * haya construido el objeto, tengo que, a partir de sus datos ir revisando
	 * las asignaciones y las aulas que ya hayan sido asignadas segun algun criterio
	 * asigno el aula segun pueda, y luego continuo con la siguiente fila hasta terminar
	 * cuando termino tengo que guardar todo los resultados hechos en un nuevo excel.
	 */

}

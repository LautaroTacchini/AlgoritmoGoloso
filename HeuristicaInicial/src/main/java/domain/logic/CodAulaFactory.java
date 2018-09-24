package domain.logic;

import java.util.Set;

public class CodAulaFactory {
	
	Set<Aula> aulas;
	
	public CodAulaFactory(Set<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public CodigoAula build(String nombreEdificio, String nombreAula){
		if(!validate(nombreEdificio,nombreAula))
			throw new RuntimeException();
		
		return new CodigoAula(nombreAula, nombreEdificio);		
	}
	
	private boolean validate(String nombreEdificio, String nombreAula) {
		for(Aula a: aulas) {
			if(a.nombre.equals(nombreAula) && a.edificio.equals(nombreEdificio)) {
				return true;
			}
		}
		return false;
	}

}

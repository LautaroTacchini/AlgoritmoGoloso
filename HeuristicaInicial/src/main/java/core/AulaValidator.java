package core;

import java.io.IOException;
import java.util.Set;

import domain.logic.Aula;
import domain.logic.CodigoDeAula;


public class AulaValidator {
	
	Set<Aula> aulas;
	
	public AulaValidator() { }
	
	public AulaValidator(Set<Aula> aulas) throws IOException {
		this.aulas = aulas;
	}
	
	public boolean validate(CodigoDeAula codAula) {
		//TODO implementar.
		return false;
	}

}

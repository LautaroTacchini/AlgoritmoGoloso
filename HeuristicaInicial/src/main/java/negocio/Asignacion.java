package negocio;

import java.util.Date;


public class Asignacion {
	private Clase clase;
	private Aula aula;
			
	// Representa la solicitud de una materia para ser dictada un dia
	// en especifico con una horar de inicio y de fin.
	public Asignacion(int id, DiaSemana dia, Date hrIni, Date hrFin,int aulaId){
		
	}
	
	@Override
	public String toString() {
		String ret = "";
		//ret = idMateria + diaSemana.toString() + horaIni + horaFin + aulaId;
		return ret;
	}
}

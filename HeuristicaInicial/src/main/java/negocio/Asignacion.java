package negocio;

import java.util.Date;


public class Asignacion {
	Integer idMateria;
	DiaSemana diaSemana;
	Date horaIni;
	Date horaFin;
	int aulaId;
			
	// Representa la solicitud de una materia para ser dictada un dia
	// en especifico con una horar de inicio y de fin.
	public Asignacion(int id, DiaSemana dia, Date hrIni, Date hrFin,int aulaId){
		this.idMateria = id;
		this.diaSemana = dia;
		this.horaIni = hrIni;
		this.horaFin = hrFin;
	}
	
	@Override
	public String toString() {
		String ret = "";
		ret = idMateria + diaSemana.toString() + horaIni + horaFin + aulaId;
		return ret;
	}
}

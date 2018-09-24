package domain.logic;

import java.util.Calendar;
import java.util.Date;

public class Clase {
	
	Integer id;
	String nombre;
	Date horaDesde, horaHasta;
	// Nota: para Calendar.DAY_OF_WEEK, SUNDAY == 1.
	DiaSemana diaSemana;
	int cantidadInscriptos;

	public Clase(Integer id,String nombre, Date horaDesde, Date horaHasta, DiaSemana d, int cantInscriptos) {
		this.id = id;
		this.nombre = nombre;
		assert horaDesde.after(horaHasta);
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		diaSemana = d;
		assert 0 <= cantInscriptos;
		cantidadInscriptos = cantInscriptos;
	}
	
	Intervalo minutoInicial() {
		Date minutoSiguiente = (Date) horaDesde.clone();
		minutoSiguiente.setMinutes(Calendar.MINUTE +1);
		return new Intervalo(diaSemana, horaDesde, minutoSiguiente);
	}
	
	Intervalo minutoFinal() {
		Date minutoAnterior = (Date) horaHasta.clone();
		minutoAnterior.setMinutes(Calendar.MINUTE - 1);
		//minutoAnterior[Calendar.MINUTE] -= 1;
		return new Intervalo(diaSemana, minutoAnterior, horaHasta);
	}

	int puntaje(Aula a) { return 0; }
	
	public boolean puedeUsar(Aula aula) {
		return cantidadInscriptos <= aula.capacidad; 
	}

	boolean seSolapaCon(Clase that) {
		return seSolapaCon(that.diaSemana, that.horaDesde, that.horaHasta);
	}
	
	boolean seSolapaCon(Intervalo iv) {
		return seSolapaCon(iv.dia, iv.horaInicio, iv.horaFin);
	}
	
	boolean seSolapaCon(DiaSemana d, Date horaInicio, Date horaFin) {
		// Si no caen el mismo día de la semana ya está
		if (diaSemana != d)
			return false;
		
		// Si no se solapan las horas desde-hasta, listo
		if (this.horaHasta.after(horaInicio) || this.horaHasta.equals(horaInicio) 
				|| horaFin.after(this.horaDesde) || horaFin.equals(this.horaDesde))
			return false;

		// Finalmente, se solapan :p
		return true;
	}
	
	int compareTo(Clase that) { return this.id.compareTo(that.id); }
	
	public String toString() {
		return id + nombre + cantidadInscriptos;
	}

}

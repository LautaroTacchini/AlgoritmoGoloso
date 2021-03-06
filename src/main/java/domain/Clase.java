package domain;

import java.util.Calendar;
import java.util.Date;

public class Clase {
	
	public Integer id;
	public String nombre;
	public Date horaDesde, horaHasta;
	// Nota: para Calendar.DAY_OF_WEEK, SUNDAY == 1.
	public DiaSemana diaSemana;
	public int cantidadInscriptos;
	public String edifPreferido;
	public int minimoPizarrones;
	public String comision;
	public int tolerancia;

	public Clase(Integer id,String nombre, Date horaDesde, Date horaHasta, DiaSemana d, int cantInscriptos, String edifPreferido, int minimoPizarrones, String comision, int tolerancia) {
		this.id = id;
		this.nombre = nombre;
		assert horaDesde.after(horaHasta);
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		diaSemana = d;
		assert 0 <= cantInscriptos;
		cantidadInscriptos = cantInscriptos;
		this.edifPreferido = edifPreferido;
		this.minimoPizarrones = minimoPizarrones;
		this.comision = comision;
		this.tolerancia = tolerancia;
	}
	
	public Intervalo minutoInicial() {
		Date minutoSiguiente = (Date) horaDesde.clone();
		minutoSiguiente.setMinutes(Calendar.MINUTE +1);
		return new Intervalo(diaSemana, horaDesde, minutoSiguiente);
	}
	
	public Intervalo minutoFinal() {
		Date minutoAnterior = (Date) horaHasta.clone();
		minutoAnterior.setMinutes(Calendar.MINUTE - 1);
		//minutoAnterior[Calendar.MINUTE] -= 1;
		return new Intervalo(diaSemana, minutoAnterior, horaHasta);
	}

	int puntaje(Aula a) { return 0; }
	
	@Deprecated
	// FIXME esto deberia ir al asignador.
	public boolean puedeUsar(Aula aula) {
		return cantidadInscriptos <= aula.capacidad; // + Tolerancia 
	}

	public boolean seSolapaCon(Clase that) {
		return seSolapaCon(that.diaSemana, that.horaDesde, that.horaHasta);
	}
	
	public boolean seSolapaCon(Intervalo iv) {
		return seSolapaCon(iv.dia, iv.horaInicio, iv.horaFin);
	}
	
	public boolean seSolapaCon(DiaSemana d, Date horaInicio, Date horaFin) {
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

	public boolean comparteComisionCon(Clase c) {
		return nombre == c.nombre && comision == c.comision;
	}
	
	int compareTo(Clase that) { return this.id.compareTo(that.id); }
	
	public String toString() {
		return "{ID: " + id + " Nombre: "+nombre + " Kant: " + cantidadInscriptos +"}";
	}

}

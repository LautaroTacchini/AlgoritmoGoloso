package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

class Intervalo {

	// see java.text.SimpleDateFormat
	static SimpleDateFormat sdf = new SimpleDateFormat("H:mm");// xej: 8:00, 12:00

	DiaSemana dia;
	Date horaInicio;
	Date horaFin;
	String txt;

	Intervalo (DiaSemana dia, Date horaInicio, Date horaFin) {
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;

		txt = String.format("[%s, %s, %s]", dia, sdf.format(horaInicio),
				sdf.format(horaFin));
	}

	public String toString() { return txt ;}

	//FIXME esto esta terriblemente mal, cambiate el that por object y fijate como se hace. 
	public boolean equals(Intervalo that) {
		return this.getClass() == that.getClass() && this.txt.equals(that.txt);
	}

	@Override
	public int hashCode() { return txt.hashCode(); }

}
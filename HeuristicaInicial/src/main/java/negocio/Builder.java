package negocio;

import java.util.Date;

public class Builder {
	
	Integer id;
	Clase clase;
	
	public Builder(String nombre, Date horaDesde, Date horaHasta, DiaSemana d, int cantInscriptos){
		clase = new Clase(id++,horaDesde,horaHasta,d,cantInscriptos);
	}

}

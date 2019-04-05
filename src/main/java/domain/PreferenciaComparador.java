package domain;

import java.util.Comparator;

public class PreferenciaComparador implements Comparator<Preferencia>{

	@Override
	public int compare(Preferencia o1, Preferencia o2) {
		return o1.clase.cantidadInscriptos - o2.clase.cantidadInscriptos;
	}

}

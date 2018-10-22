package domain;

import java.util.Comparator;

public class AulaComparador implements Comparator<Aula> {

	@Override
	public int compare(Aula o1, Aula o2) {
		return o1.capacidad - o2.capacidad ;
	}
}

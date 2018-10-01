package domain.logic;

public enum DiaSemana {
	DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO;
	
	public static DiaSemana parse(String str) {
		return valueOf(str.toUpperCase().replaceAll("ÁÉÍÓÚ", "AEIOU"));
	}
}

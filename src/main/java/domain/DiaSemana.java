package domain;

public enum DiaSemana {
	DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO;
	
	public static DiaSemana parse(String str) {
		str = str.toUpperCase().replaceAll("Á", "A");
		str = str.toUpperCase().replaceAll("É", "E");
		str = str.toUpperCase().replaceAll("Í", "I");
		str = str.toUpperCase().replaceAll("Ó", "O");
		str = str.toUpperCase().replaceAll("Ú", "U");
		return valueOf(str.toUpperCase().replaceAll("ÁÉÍÓÚ", "AEIOU"));
	}
}

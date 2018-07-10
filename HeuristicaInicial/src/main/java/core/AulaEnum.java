package core;

public enum AulaEnum {
	PAB, AULA;
	
	public static AulaEnum parse(String str) {
		return valueOf(str.toUpperCase().replaceAll("ÁÉÍÓÚ", "AEIOU"));
	}

}

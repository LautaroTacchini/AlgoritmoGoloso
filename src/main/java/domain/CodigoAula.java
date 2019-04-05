package domain;

// Como código de aula ahora está chequeado entonces no es facil hacer un map que sea de codigo de aula en Aula.
// Como no se puede hacer un Map, es necesario hacer una busqueda lineal.

@Deprecated
public class CodigoAula {

	public String nombreEdificio;
	public String nombreAula;

	/** 
	* Esto no es publico para que no se permita la construcción
	* de objetos invalidos.
	*/
	CodigoAula(String nombreEdificio, String nombreAula) {
		this.nombreEdificio = nombreEdificio;
		this.nombreAula = nombreAula;
	}

	public String toString() {
		return nombreEdificio + nombreAula;
	}
}

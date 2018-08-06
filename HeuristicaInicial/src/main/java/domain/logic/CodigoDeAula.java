package domain.logic;

public class CodigoDeAula {
		
	Integer edificio;
	Integer nroAula;
	
	public CodigoDeAula(int edificio, int nroAula) {
		this.edificio = edificio;
		this.nroAula = nroAula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edificio == 0) ? 0 : edificio.hashCode());
		result = prime * result + ((nroAula == 0) ? 0 : nroAula.hashCode());
		return result;		
	}

}

package domain.logic;

public class CodigoDeAula {
	Integer pabellon;
	Integer nroAula;
	
	public CodigoDeAula(int pabellon, int nroAula) {
		this.pabellon = pabellon;
		this.nroAula = nroAula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pabellon == 0) ? 0 : pabellon.hashCode());
		result = prime * result + ((nroAula == 0) ? 0 : nroAula.hashCode());
		return result;		
	}

}

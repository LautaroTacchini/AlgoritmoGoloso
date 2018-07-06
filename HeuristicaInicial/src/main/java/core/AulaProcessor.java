package core;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import negocio.Aula;

public class AulaProcessor {
	
	public AulaProcessor() {  }
	
	public void process(Row row){
		List<Aula> lista = new ArrayList<Aula>();
		
		for(Cell cell: row) {
			lista.add(buildAula(cell));
        }
	}
	
	private Aula buildAula(Cell cell) {
		Aula aula = null;

		if(cell.getRowIndex() >= 1) {
			int pab = 0;
			int nro = 0;
			int capacidad = 0;
			
			if(cell.getColumnIndex() == 0) 
				pab = (int) cell.getNumericCellValue();
						
		    if(cell.getColumnIndex() == 1) 
		    	nro = (int ) cell.getNumericCellValue();
		    
		    if(cell.getColumnIndex() == 2)  
		    	capacidad = (int) cell.getNumericCellValue();
		    
		    aula = new Aula(pab,nro,capacidad);
		}		
		return aula;
	}
}

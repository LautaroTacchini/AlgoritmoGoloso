package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import negocio.Clase;
import negocio.DiaSemana;

public class RowProcessor {
	
	public RowProcessor () {  }
	
	public List<Object> process(Row row){
		List<Object> lista = new ArrayList<Object>();
		
		for(Cell cell: row) {
        	fill(cell, lista);
        }
		
		return lista;
	}
	
	private void fill(Cell cell, List<Object> list) {
		
		if(cell.getRowIndex() >= 1) { 
		
			String nombre = "";
			Date horaDesde = null;
			Date horaHasta = null;
			DiaSemana diaSemana = null;
			int kantInscriptos = 0;
			Integer nroAula = 0;
			Integer nroPabellon = 0;
						
			if(cell.getColumnIndex() == 0) 
			   	nombre = cell.getStringCellValue();

			if(cell.getColumnIndex() == 3)
		    	diaSemana = DiaSemana.parse(cell.getStringCellValue());
		    
		    if(cell.getColumnIndex() == 4)
		    	nroPabellon = (int) cell.getNumericCellValue();
		   
		    if(cell.getColumnIndex() == 7) 
		    	horaDesde = cell.getDateCellValue();
		    
		    if(cell.getColumnIndex() == 8)
		    	horaHasta = cell.getDateCellValue();
		 
		    if(cell.getColumnIndex() == 11) 
		    	kantInscriptos = (int) cell.getNumericCellValue();
		    
		    Clase clase = new Clase(cell.getRowIndex(),nombre,horaDesde,horaHasta,diaSemana,kantInscriptos);
		    
		    if(cell.getColumnIndex() == 6) {

		    	if(cell.getCellTypeEnum() != CellType.BLANK) {
		    		nroAula = (int) cell.getNumericCellValue();
		    		
		    		//Aula aula = new Aula(nroAula,nroPabellon,0);
		    		//asig.create(clase, aula);
		    	}
		    	else 
		    		System.out.println("Preferencia");
		    }
		}
	}
}

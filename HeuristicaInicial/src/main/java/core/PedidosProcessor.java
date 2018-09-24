package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import domain.logic.Clase;
import domain.logic.CodAulaFactory;
import domain.logic.CodigoAula;
import domain.logic.DiaSemana;

public class PedidosProcessor {
	
	CodAulaFactory codAulaFactory;
	
	public PedidosProcessor (CodAulaFactory caf) {
		codAulaFactory = caf;
	}
	
	/** Recibe un row y devuelve una lista de objectos?
	 * 
	 * @param row
	 * @return
	 */
	
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
			String nombreEdificio = "";
						
			if(cell.getColumnIndex() == 0) 
			   	nombre = cell.getStringCellValue();

			if(cell.getColumnIndex() == 3)
		    	diaSemana = DiaSemana.parse(cell.getStringCellValue());
		    
		    if(cell.getColumnIndex() == 4)
		    	nombreEdificio = getCellValue(cell);
		   
		    if(cell.getColumnIndex() == 7) 
		    	horaDesde = cell.getDateCellValue();
		    
		    if(cell.getColumnIndex() == 8)
		    	horaHasta = cell.getDateCellValue();
		 
		    if(cell.getColumnIndex() == 11) 
		    	kantInscriptos = (int) cell.getNumericCellValue();
		    
		    Clase clase = new Clase(cell.getRowIndex(),nombre,horaDesde,horaHasta,diaSemana,kantInscriptos);
		    
		    if(cell.getColumnIndex() == 6) {
		    	String nombreAula = getCellValue(cell);
		    	
		    	if(cell.getCellTypeEnum() != CellType.BLANK) {//Primero ponel lo que va == y luego la negacion en el else 
		    		// Chequear si es un codigo de aula valido.
		    		// Validar si esa materia puede ir en ese aula.
		    		// Ver si esta aula no fue asignada a otra materia.
		    		// Recien aca creo la asignacion.
		    	}
		    	else {
		    		
		    	}
		    }
		}
	}
	
	private String getCellValue(Cell c) {
		c.getCellTypeEnum();
		if(c.getCellTypeEnum() == CellType.NUMERIC) 
			return String.valueOf((int) c.getNumericCellValue());
		
		if(c.getCellTypeEnum() == CellType.STRING)
			return c.getStringCellValue();

		return "hola";
	}
}

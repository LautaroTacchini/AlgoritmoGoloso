package core;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import negocio.Aula;
import negocio.CodigoDeAula;

public class AulaProcessor {
	
	EnumMap<AulaEnum, Integer> enumMap;
	
	public AulaProcessor() { }	
	
	public EnumMap<AulaEnum, Integer> fillEnumMap(Row row) throws Exception {
		if(row.getRowNum() > 0) {
			throw new RuntimeException();
		}
		
		enumMap = new EnumMap<AulaEnum,Integer>(AulaEnum.class);
				
		for(Cell c: row) {
			for(AulaEnum aulaEnum : AulaEnum.values()) {
				if(match(aulaEnum,c)) {
					if(enumMap.containsKey(aulaEnum))
						throw new RuntimeException();					
					else 
						enumMap.put(aulaEnum, c.getColumnIndex());
				}
			}
		}			
		if(enumMap.size() != AulaEnum.values().length) 
			throw new RuntimeException();

		return enumMap;
	}
	
	private boolean match(AulaEnum aulaEnum, Cell cell) {
		return aulaEnum.toString().equals(cell.getStringCellValue());
	}
	
	public List<Aula> process(Row row){
		
		if(row.getRowNum() == 0)
			throw new RuntimeException();

		List<Aula> lista = new ArrayList<Aula>();
			
		for(Cell cell: row) {
		   	if(buildAula(cell) != null)
            	lista.add(buildAula(cell));
        }
		return lista;
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
		    	nro = (int) cell.getNumericCellValue();
		    
		    if(cell.getColumnIndex() == 2)  
		    	capacidad = (int) cell.getNumericCellValue();
		    
		    aula = new Aula(new CodigoDeAula(pab,nro),capacidad);
		}		
		return aula;
	}
}

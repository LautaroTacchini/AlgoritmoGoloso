package core;

import java.util.EnumMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import domain.logic.Aula;

/** 
 * AulaProcessor es llamada por otra clase que lee los rows 
 * y se encarga de leer los elementos del row y procesa las 
 * aulas.
 * @author lautaro
 */
public class AulaProcessor {
	
	EnumMap<AulaEnum, Integer> columnOrder;
	
	public AulaProcessor() { }	
	
	/** fillEnumMap recibe un row y se encarga de llenar un enumMap
	 * este enumMap representa los nombres de las columnas de un archivo
	 * asociadas a un numero de columna.
	 */
	public EnumMap<AulaEnum, Integer> fillColumnOrder(Row row) throws Exception {
		
		if(row.getRowNum() > 0 || columnOrder!= null) {
			throw new RuntimeException();
		}		
		columnOrder = new EnumMap<AulaEnum,Integer>(AulaEnum.class);
				
		for(Cell c: row) {
			// TODO Revisar si podemos ponerle parse a esto de aca abajo.
			AulaEnum aulaEnum = AulaEnum.map.get(c.getStringCellValue());
			
			if(aulaEnum != null) {
				if(columnOrder.containsKey(aulaEnum)) {
					throw new RuntimeException();	
				}
				columnOrder.put(aulaEnum, c.getColumnIndex());
			}
			else 
				System.out.println(c.getStringCellValue());
		}
		if(columnOrder.size() != AulaEnum.values().length) {
			throw new RuntimeException();
		}

		return columnOrder;
	}
	
	/**
	 * Procesa los valores leidos de un row 
	 * y devuelve un aula.
	 * @param row
	 * @return
	 */
	public Aula process(Row row) {
		if(row.getRowNum() == 0 || columnOrder == null)
			throw new RuntimeException();
		
		int nroPab = getInt(AulaEnum.EDIFICIO,row);
		int nroAula = getInt(AulaEnum.AULA,row);
		int capacidad = getInt(AulaEnum.CAPACIDAD,row);
		
		return new Aula(nroPab, nroAula,capacidad);
	}
	
	private int getInt(AulaEnum aulaEnum, Row row) {
		return (int) row.getCell(columnOrder.get(aulaEnum)).getNumericCellValue();
	}
}

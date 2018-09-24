package core;

import java.util.EnumMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import domain.logic.Aula;

/** 
 * AulaProcessor es llamada por otra clase que lee los rows 
 * y se encarga de leer los elementos del row y procesa las 
 * aulas.
 * @author lautaro
 */
public class AulaProcessor implements RowProcessor<AulaEnum> {
	
	EnumMap<AulaEnum, Integer> columnOrder;
	
	public AulaProcessor() { }	
	
	/** fillEnumMap recibe un row y se encarga de llenar un enumMap
	 * este enumMap representa los nombres de las columnas de un archivo
	 * asociadas a un numero de columna.
	 */
	public EnumMap<AulaEnum, Integer> fillColumnOrder(Row row) {
		
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
		
		String nombreEdificio = getCellValue(getCell(AulaEnum.EDIFICIO,row));
		String nombreAula = getCellValue(getCell(AulaEnum.AULA,row));
		int capacidad = getInt(getCell(AulaEnum.CAPACIDAD,row));
		
		return new Aula(nombreEdificio, nombreAula,capacidad);
	}
	
	private Cell getCell(AulaEnum aulaEnum, Row row) {
		return row.getCell(columnOrder.get(aulaEnum));
	}
	
	private int getInt(Cell cell) {
		return (int) cell.getNumericCellValue();
	}
	
//	private String getString(AulaEnum aulaEnum, Row row) {
//		return getCellValue(row.getCell(columnOrder.get(aulaEnum)));
//	}
	
	private String getCellValue(Cell c) {
		c.getCellTypeEnum();
		
		if(c.getCellTypeEnum() == CellType.STRING)
			return c.getStringCellValue();

		if(c.getCellTypeEnum() == CellType.NUMERIC) 
			return String.valueOf((int) c.getNumericCellValue());

		throw new RuntimeException();
	}
}

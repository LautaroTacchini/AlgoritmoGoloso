package core;

import java.util.EnumMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import domain.logic.Aula;

public class AulaProcessor {
	
	EnumMap<AulaEnum, Integer> enumMap;
	
	public AulaProcessor() { }	
	
	public EnumMap<AulaEnum, Integer> fillEnumMap(Row row) throws Exception {
		if(row.getRowNum() > 0 || enumMap!= null) {
			throw new RuntimeException();
		}
		
		enumMap = new EnumMap<AulaEnum,Integer>(AulaEnum.class);
				
		for(Cell c: row) {
			// TODO Revisar si podemos ponerle parse a esto de aca abajo.
			AulaEnum aulaEnum = AulaEnum.map.get(c.getStringCellValue());
			
			if(aulaEnum != null) {
				if(enumMap.containsKey(aulaEnum)) {
					throw new RuntimeException();	
				}
				enumMap.put(aulaEnum, c.getColumnIndex());
			}
			else {
				System.out.println(c.getStringCellValue());
			}
		}
		if(enumMap.size() != AulaEnum.values().length) {
//			System.out.println("------------");
//			System.out.println(enumMap);
//			System.out.println(enumMap.size());
//			System.out.println(AulaEnum.values());
//			System.out.println(AulaEnum.values().length);
			throw new RuntimeException();
		}

		return enumMap;
	}
	
	public Aula process(Row row) {
		if(row.getRowNum() == 0 || enumMap == null)
			throw new RuntimeException();
		
		int nroPab = getInt(AulaEnum.EDIFICIO,row);
		int nroAula = getInt(AulaEnum.AULA,row);
		int capacidad = getInt(AulaEnum.CAPACIDAD,row);
		
		return new Aula(nroPab, nroAula,capacidad);
	}
	
	private int getInt(AulaEnum aulaEnum, Row row) {
		return (int) row.getCell(enumMap.get(aulaEnum)).getNumericCellValue();
	}
}

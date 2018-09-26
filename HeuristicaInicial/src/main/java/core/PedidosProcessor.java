package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;

import domain.logic.Aula;
import domain.logic.Clase;
import domain.logic.CodAulaFactory;
import domain.logic.DiaSemana;

public class PedidosProcessor implements RowProcessor<PedidoEnum>{
	
	CodAulaFactory caf;
	
	EnumMap<PedidoEnum, Integer> columnOrder;

	public PedidosProcessor (CodAulaFactory caf) {
		this.caf = caf;
	}
	
	public Object process(Row row){
		
		if(row.getRowNum() == 0 || columnOrder == null)
			throw new RuntimeException();
		
		String nombre = getCellValue(getCell(PedidoEnum.NOMBRE,row));
		Date hrDesde = getDate(getCell(PedidoEnum.DESDE,row));
		Date hrHasta = getDate(getCell(PedidoEnum.HASTA,row));
		String dia = getCellValue(getCell(PedidoEnum.DIA,row));
		int kant = getInt(getCell(PedidoEnum.KANT,row));
		String edificio = getCellValue(getCell(PedidoEnum.EDIFICIO,row));		
		
	    if(getCell(PedidoEnum.AULA,row).getCellTypeEnum() != CellType.BLANK) {
	    	String aula = getCellValue(getCell(PedidoEnum.AULA,row));
	    	//	String nombreAula = getCellValue(cell);
	    	// Chequear si es un codigo de aula valido.
	    	caf.build(edificio, aula);
	    	// Validar si esa materia puede ir en ese aula.
	    	// Ver si esta aula no fue asignada a otra materia.
	    	// Recien aca creo la asignacion.
	    }
	    else {
    		System.out.println("preferencia");
    	}
	    return null;
	}
	
	private Cell getCell(PedidoEnum pedidoEnum, Row row) {
		return row.getCell(columnOrder.get(pedidoEnum));
	}
	
	private int getInt(Cell cell) {
		return (int) cell.getNumericCellValue();
	}
	
	private Date getDate(Cell cell) {
		return cell.getDateCellValue();
	}
	
	private String getCellValue(Cell c) {
		
		if(c.getCellTypeEnum() == CellType.STRING)
			return c.getStringCellValue();

		if(c.getCellTypeEnum() == CellType.NUMERIC) 
			return String.valueOf((int) c.getNumericCellValue());
		
		throw new RuntimeException("No se está leyendo ni un String ni un Numeric");
	}

	@Override
	public EnumMap<PedidoEnum, Integer> fillColumnOrder(Row row) {
		if(row.getRowNum() > 0 || columnOrder!= null) {
			throw new RuntimeException();
		}		
		columnOrder = new EnumMap<PedidoEnum,Integer>(PedidoEnum.class);
				
		for(Cell c: row) {
			// TODO Revisar si podemos ponerle parse a esto de aca abajo.
			PedidoEnum pedidoEnum = PedidoEnum.map.get(c.getStringCellValue());
			
			if(pedidoEnum != null) {
				if(columnOrder.containsKey(pedidoEnum)) {
					throw new RuntimeException();	
				}
				columnOrder.put(pedidoEnum, c.getColumnIndex());
			}
			//else 
				//System.out.println(c.getStringCellValue());
		}
		if(columnOrder.size() != PedidoEnum.values().length) {
			throw new RuntimeException();
		}

		return columnOrder;
	}
}

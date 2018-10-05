package core;

import java.util.Date;
import java.util.EnumMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import domain.logic.Asignacion;
import domain.logic.Aula;
import domain.logic.Clase;
import domain.logic.DiaSemana;
import domain.logic.Preferencia;

public class PedidosProcessor implements RowProcessor<PedidoEnum>{
	
	AulaFinder af;
	Asignador asignador;
	
	EnumMap<PedidoEnum, Integer> columnOrder;

	public PedidosProcessor (AulaFinder af,Asignador asig) {
		this.af = af;
		this.asignador = asig;
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
		
    	Clase clase = new Clase(row.getRowNum(),nombre,hrDesde,hrHasta,DiaSemana.parse(dia), kant);

		// TODO agregar tema de "Tolerancia" de las aulas reales.
	    if(getCell(PedidoEnum.AULA,row).getCellTypeEnum() != CellType.BLANK) {
	    	String nombreAula = getCellValue(getCell(PedidoEnum.AULA,row));
	    	Aula aula = af.find(edificio, nombreAula);
	    	asignador.asignar(clase,aula);
	    	// Recien aca creo la asignacion.
	    	return new Asignacion(clase, aula);
	    }
	    else {
	    	// TODO verificar que los edificios existen.
	    	asignador.preferir(clase,edificio);
	    	return new Preferencia(clase,edificio);
	    }
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

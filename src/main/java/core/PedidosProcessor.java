package core;

import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import domain.Asignacion;
import domain.Asignador;
import domain.Aula;
import domain.Clase;
import domain.DiaSemana;
import domain.Preferencia;
import domain.Preferidor;
import main.Main;

public class PedidosProcessor implements RowProcessor<PedidoEnum>{
	
	AulaFinder af;
	Asignador asignador;
	Preferidor preferidor;
	public Set<Asignacion> preasignaciones = new HashSet<Asignacion>();
	
	EnumMap<PedidoEnum, Integer> columnOrder;
	
	public static String COMISION_INDISTINTA = "INDISTINTO";

	public PedidosProcessor (AulaFinder af,Asignador asig, Preferidor pref) {
		this.af = af;
		this.asignador = asig;
		preferidor = pref;
	}
	
	public Preferencia process(Row row){
		
		if(row.getRowNum() == 0 || columnOrder == null)
			throw new RuntimeException();
		
		String nombre = getCellValue(getCell(PedidoEnum.NOMBRE,row));
		Date hrDesde = getDate(getCell(PedidoEnum.DESDE,row));
		Date hrHasta = getDate(getCell(PedidoEnum.HASTA,row));
		String dia = getCellValue(getCell(PedidoEnum.DIA,row));
		int kant = getInt(getCell(PedidoEnum.KANT,row));
		int minPizarrones = getInt(getCell(PedidoEnum.MINPIZARRONES,row));
		String comision = getComision(row);// FIXME copy paste de edificio		
		String edificio = getEdifPref(row);
		int tolerancia = getInt(getCell(PedidoEnum.TOLERANCIA,row));

		Clase clase = new Clase(row.getRowNum(),nombre,hrDesde,hrHasta,DiaSemana.parse(dia), kant, edificio, minPizarrones, comision, tolerancia);

	    if(getCell(PedidoEnum.AULA,row).getCellTypeEnum() != CellType.BLANK) {
	    	String nombreAula = getCellValue(getCell(PedidoEnum.AULA,row));
	    	Aula aula = af.find(edificio, nombreAula);

	    	// Recien aca creo la asignacion.
	    	asignador.validarCapacidad(clase,aula);
	    	preasignaciones.add(asignador.asignar(clase,aula));
	    	return null;
	    }
	    else 
	    	return preferidor.preferir(clase, edificio);	    	  
	}

	private String getEdifPref(Row row) {
		String edificio;
		Cell c = getCell(PedidoEnum.EDIFICIO,row);
		if(c.getCellTypeEnum() == CellType.BLANK) {
			edificio = Main.EDIFICIO_INDISTINTO;
		} else {
			edificio = getCellValue(c);		
			if(edificio.equals("0")) {
				edificio = Main.EDIFICIO_INDISTINTO;
			}
		}
		return edificio;
	}
	
	private String getComision(Row row) {
		String comision;
		Cell c = getCell(PedidoEnum.COMISION,row);
		if(c.getCellTypeEnum() == CellType.BLANK) {
			comision = COMISION_INDISTINTA;
		} else {
			comision = getCellValue(c);		
			if(comision.equals("0")) {
				comision = COMISION_INDISTINTA;
			}
		}
		return comision;
	}
	
	private Cell getCell(PedidoEnum pedidoEnum, Row row) {
		return row.getCell(columnOrder.get(pedidoEnum),Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
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
		
		throw new RuntimeException("Formato no valido");
	}

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
		}
		if(columnOrder.size() != PedidoEnum.values().length) {
			throw new RuntimeException();
		}

		return columnOrder;
	}
}

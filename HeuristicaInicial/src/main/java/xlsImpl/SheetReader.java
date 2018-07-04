package xlsImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import negocio.Asignacion;
import negocio.Aula;
import negocio.Clase;
import negocio.DiaSemana;
import negocio.Preferencia;

public class SheetReader {
	File archivo;
	FileInputStream input;
	Workbook workbook;
	
	public SheetReader(String path) {
		archivo = new File (path);
		
		try {
			input = new FileInputStream(archivo);
			workbook = WorkbookFactory.create(new File(path));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}     
	}
		
	public List<Aula> readAulas(String nombreHoja) {		
		List<Aula> aulas = new ArrayList<Aula>();

        Sheet sheet = workbook.getSheet(nombreHoja);
        
        for (Row row: sheet) {
            for(Cell cell: row) {
            	if(construirAula(cell) != null) {
            		Aula aula = construirAula(cell);
                	aulas.add(aula);
            	}
            }
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return aulas;
	}
	

	public List<Object> readLines(String nombreHoja) {		
		List<Object> lista = new ArrayList<Object>();

        Sheet sheet = workbook.getSheet(nombreHoja);
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row: sheet) {
            for(Cell cell: row) {
            	fill(dataFormatter, cell, lista);
            }
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return lista;
	}
	
	private void fill(DataFormatter dataFormatter, Cell cell, List<Object> list) {
		
		if(cell.getRowIndex() >= 1) { 
		
			String nombre = "";
			Date horaDesde = null;
			Date horaHasta = null;
			DiaSemana diaSemana = null;
			int kantInscriptos = 0;
			Integer nroAula = 0;
			Integer nroPabellon = 0;
			
			Clase clase = null;
			
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
		    
		    clase = new Clase(cell.getRowIndex(),nombre,horaDesde,horaHasta,diaSemana,kantInscriptos);
		    
		    if(cell.getColumnIndex() == 6) {

		    	if(cell.getCellTypeEnum() != CellType.BLANK)
		    		System.out.println("Asignacion");
		    	else 
		    		System.out.println("Preferencia");
		    }
		}
	}	
	
	private Aula construirAula(Cell cell) {
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


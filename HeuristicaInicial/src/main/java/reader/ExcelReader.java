package reader;

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
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import negocio.Aula;
import negocio.Clase;
import negocio.DiaSemana;

public class ExcelReader {
	File archivo;
	FileInputStream input;
	Workbook workbook;
	
	public ExcelReader(String path) {
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
	
	public List<Clase> readClases(String nombreHoja) {		
		List<Clase> clases = new ArrayList<Clase>();

        Sheet sheet = workbook.getSheet(nombreHoja);

        DataFormatter dataFormatter = new DataFormatter();
        
        for (Row row: sheet) {
            for(Cell cell: row) {
                Clase clase = construirClase(dataFormatter, cell);
                clases.add(clase);
            }
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return clases;
	}
	
	public List<Aula> readAulas(String nombreHoja) {		
		List<Aula> aulas = new ArrayList<Aula>();

        Sheet sheet = workbook.getSheet(nombreHoja);
        
        for (Row row: sheet) {
            for(Cell cell: row) {
                Aula aula = construirAula(cell);
                aulas.add(aula);
            }
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
        System.out.println(aulas.get(0));
		return aulas;
	}
	

	private Clase construirClase(DataFormatter dataFormatter, Cell cell) {
		String nombre = null;
		Date horaDesde = null;
		Date horaHasta = null;
		DiaSemana diaSemana = null;
		int kantInscriptos = 0;
		boolean preasignada = false;
		
		if(cell.getRowIndex() >= 2) {
			
			if(cell.getColumnIndex() == 1)
			   	nombre = cell.getStringCellValue();
	 
		    if(cell.getColumnIndex() == 3)
		    	diaSemana = DiaSemana.parse(cell.getStringCellValue());
		   
		    if(cell.getColumnIndex() == 6 && cell.getNumericCellValue() != -1) 
		    		preasignada = true;
		    
		    if(cell.getColumnIndex() == 7) 
		    	horaDesde = cell.getDateCellValue();
		    
		    if(cell.getColumnIndex() == 8)
		    	horaHasta = cell.getDateCellValue();
		 
		    if(cell.getColumnIndex() == 11) 
		    	kantInscriptos = (int) cell.getNumericCellValue();		    
		}
		
		return new Clase(cell.getRowIndex(),nombre,horaDesde,horaHasta,diaSemana,kantInscriptos,preasignada);
	}	
	
	
	private Aula construirAula(Cell cell) {
		String id = "";
		int capacidad = 0;
		if(cell.getRowIndex() >= 2) {
			if(cell.getColumnIndex() == 0)
			   	id = cell.getStringCellValue();
	 
		    if(cell.getColumnIndex() == 1)
		    	id = id + cell.getStringCellValue();
		   
		    if(cell.getColumnIndex() == 2) 
		    	capacidad = (int) cell.getNumericCellValue();
		}
		return new Aula(Integer.valueOf(id),capacidad);
	}
}


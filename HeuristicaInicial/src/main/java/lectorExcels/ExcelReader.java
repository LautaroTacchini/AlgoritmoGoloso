package lectorExcels;

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
	
	public List<Clase> read() {		
		List<Clase> ret = new ArrayList<Clase>();

        Sheet sheet = workbook.getSheetAt(2);

        DataFormatter dataFormatter = new DataFormatter();
        
        for (Row row: sheet) {
            for(Cell cell: row) {
                Clase clase = construirClase(dataFormatter, cell);
                ret.add(clase);
                //String cellValue = dataFormatter.formatCellValue(cell);
            }
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return ret;
	}

	private Clase construirClase(DataFormatter dataFormatter, Cell cell) {
		Date horaDesde = null;
		Date horaHasta = null;
		DiaSemana diaSemana = null;
		int kantInscriptos = 0;
		
		if(cell.getRowIndex() >= 2) {
		    if(cell.getColumnIndex() == 3){
		    	diaSemana = DiaSemana.parse(cell.getStringCellValue());
		    }            
		    
		    if(cell.getColumnIndex() == 7) {
		    	horaDesde = cell.getDateCellValue();
		    }
		    
		    if(cell.getColumnIndex() == 8) {
		    	horaHasta = cell.getDateCellValue();
		    }
		    
		    if(cell.getColumnIndex() == 11) {
		    	kantInscriptos = (int) cell.getNumericCellValue();
		    }
		}
		
		return new Clase(cell.getRowIndex(),horaDesde,horaHasta,diaSemana,kantInscriptos);
	}	
}


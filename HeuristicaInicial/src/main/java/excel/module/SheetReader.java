package excel.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import core.RowProcessor;

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
	
	public List<Object> read(String nombreHoja) {		
		List<Object> lista = new ArrayList<Object>();
		
		Sheet sheet = workbook.getSheet(nombreHoja);
		
		for (Row row: sheet) {
			RowProcessor rowProc = new RowProcessor();
			lista = rowProc.process(row);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return lista;
	}		
}


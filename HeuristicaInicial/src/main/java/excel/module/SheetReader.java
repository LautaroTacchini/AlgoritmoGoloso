package excel.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import core.RowProcessor;

public class SheetReader {

	Sheet sheet;
	RowProcessor<?> rowProc;
	
	public SheetReader(String path, String nombreHoja, RowProcessor<?> rp) {
		File archivo = new File (path);
		this.rowProc = rp;
		
		try {
			Workbook workbook = WorkbookFactory.create(archivo);
			sheet = workbook.getSheet(nombreHoja);
			
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
	
	public List<Object> read() {		
		List<Object> lista = new ArrayList<Object>();
		for (Row row: sheet) {
			if(row.getRowNum() == 0)
				rowProc.fillColumnOrder(row);
			else if(row.getCell(0) != null) {
				lista.add(rowProc.process(row));				
			}
			else {
				throw new RuntimeException("No soporta filas en blanco");
			}
		}
		try {
			sheet.getWorkbook().close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return lista;
	}	
	
}


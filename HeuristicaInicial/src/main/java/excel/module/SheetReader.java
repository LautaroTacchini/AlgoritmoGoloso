package excel.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;

import core.AulaProcessor;
import core.PedidosProcessor;
import core.RowProcessor;
import domain.logic.Aula;
import domain.logic.CodAulaFactory;

public class SheetReader {
//	File archivo;
//	FileInputStream input;
//	Workbook workbook;
	Sheet sheet;
	RowProcessor rowProc;
	
	public SheetReader(String path, String nombreHoja, RowProcessor rp) {
		File archivo = new File (path);
		this.rowProc = rp;
		
		try {
			FileInputStream input = new FileInputStream(archivo);
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
			else
				lista.add(rowProc.process(row));
		}
		try {
			sheet.getWorkbook().close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return lista;
	}	
	
//	public Set<Aula> readAulas(String nombreHoja) throws Exception {		
//		Set<Aula> set = new HashSet<Aula>();
//		
//		Sheet sheet = workbook.getSheet(nombreHoja);
//		
//		AulaProcessor aulasProc = new AulaProcessor();
//
//		for (Row row: sheet) {
//			if(row.getRowNum() == 0)
//				aulasProc.fillColumnOrder(row);
//			else
//				set.add(aulasProc.process(row));
//		}
//		try {
//			workbook.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
//		return set;
//	}	
}


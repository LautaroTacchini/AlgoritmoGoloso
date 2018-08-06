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

import core.AulaProcessor;
import core.PedidosProcessor;
import domain.logic.Aula;

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
			PedidosProcessor rowProc = new PedidosProcessor();
			lista = rowProc.process(row);
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return lista;
	}	
	
	public Set<Aula> readAulas(String nombreHoja) throws Exception {		
		Set<Aula> set = new HashSet<Aula>();
		
		Sheet sheet = workbook.getSheet(nombreHoja);
		
		AulaProcessor aulasProc = new AulaProcessor();

		for (Row row: sheet) {
			if(row.getRowNum() == 0)
				aulasProc.fillEnumMap(row);
			else
				set.add(aulasProc.process(row));
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return set;
	}	
}


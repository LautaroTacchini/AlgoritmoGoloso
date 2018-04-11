package lectorExcels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import negocio.Builder;

public class Reader {
	File archivo;
	FileInputStream input;
	Workbook workbook;
	Builder builder;
	
	public Reader(String path) {
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
	
	public List<String> read() {		
		List<Fila> ret = new ArrayList<Fila>();
		List<String> retString = new ArrayList<String>();

		
		for(Sheet sheet: workbook) {
            System.out.println("=> " + sheet.getSheetName());
        }
		
		// Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                System.out.print(cellValue + "\t");
                retString.add(cellValue);
            }
            System.out.println();
        }
        try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return retString;
	}
	
}


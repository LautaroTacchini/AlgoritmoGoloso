package main;

import reader.ExcelReader;

public class Main {
	
	public static void main(String[] args){
		
//		FileManager fileManager = new FileManager("instancias");
//		fileManager.recorrerArchivos();
		
		ExcelReader reader = new ExcelReader("instancias/pedidos-2018-1.xls");
		reader.read();
	}
}

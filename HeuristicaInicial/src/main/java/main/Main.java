package main;

import lectorExcels.Reader;

public class Main {
	
	public static void main(String[] args){
		
//		FileManager fileManager = new FileManager("instancias");
//		fileManager.recorrerArchivos();
		
		Reader reader = new Reader("instancias/pedidos-2018-1.xls");
		reader.read();
	}
}

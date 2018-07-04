package main;

import xlsImpl.SheetReader;

public class Main {
	
	public static void main(String[] args){
		
//		FileManager fileManager = new FileManager("instancias");
//		fileManager.recorrerArchivos();
		
		SheetReader reader = new SheetReader("instancias/pedidos-2018-1-mod.xls");
		reader.readLines("Pedidos");
	}
}

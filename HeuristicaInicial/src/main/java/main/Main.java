package main;

import java.util.Set;

import domain.logic.Aula;
import excel.module.SheetReader;

public class Main {
	
	public static void main(String[] args) throws Exception{
				
		SheetReader reader = new SheetReader("instancias/pedidos-2018-1-mod.xls");
		reader.read("Pedidos");
		
		Set<Aula> aulas = reader.readAulas("Aulas1");
		
		for(Aula a: aulas) {
			System.out.println(a);
		}
				
		for(Aula a: aulas) {
			System.out.println(a);
		}

	}
}

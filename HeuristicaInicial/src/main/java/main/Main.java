package main;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.AulaProcessor;
import core.PedidosProcessor;
import domain.logic.Aula;
import domain.logic.Clase;
import domain.logic.CodAulaFactory;
import excel.module.SheetReader;

public class Main {
	
	public static void main(String[] args) throws Exception{
				
		String path = "instancias/pedidos-2018-1-mod.xls";
		
		AulaProcessor ap = new AulaProcessor();
		
		SheetReader reader = new SheetReader(path,"Aulas1",ap);
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Set<Aula> aulas = new HashSet<Aula>((Collection<Aula>)(List)reader.read());
		
		CodAulaFactory caf = new CodAulaFactory(aulas);
		for(Aula a: aulas) {
			System.out.println(a);
		}
		
		PedidosProcessor pp = new PedidosProcessor(caf);
		reader = new SheetReader(path,"Pedidos",pp);
		Set<Clase> clases = new HashSet<Clase>((Collection<Clase>)(List)reader.read());
		for(Clase c: clases) {
			System.out.println(c);
		}
	}
}

package main;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import core.AulaFinder;
import core.AulaProcessor;
import core.PedidosProcessor;
import domain.Asignacion;
import domain.Asignador;
import domain.Aula;
import domain.Preferidor;
import excel.module.SheetReader;

public class Main {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception{
				
		String path = "instancias/pedidos-2018-1-mod.xls";
		
		AulaProcessor ap = new AulaProcessor();
		SheetReader reader = new SheetReader(path,"Aulas1",ap);
		Set<Aula> aulas = new HashSet<Aula>((Collection<Aula>)(List)reader.read());
			
		AulaFinder af = new AulaFinder(aulas);
		Asignador asig = new Asignador();
		
		Set<String> edificios = new HashSet<>();
		for(Aula a: aulas) {
			edificios.add(a.edificio);
		}
		Preferidor pref = new Preferidor(edificios);
		
		PedidosProcessor pp = new PedidosProcessor(af,asig,pref);

		reader = new SheetReader(path,"Pedidos",pp);
		
		Set<Object> clases = new HashSet<>((Collection<Asignacion>)(List)reader.read());
		for(Object a: clases) {
			System.out.println(a);
		}
	}
}

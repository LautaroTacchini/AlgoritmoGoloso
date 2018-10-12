package main;

import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import core.AulaFinder;
import core.AulaProcessor;
import core.Heuristica;
import core.PedidosProcessor;
import domain.Asignador;
import domain.Aula;
import domain.Preferencia;
import domain.Preferidor;
import excel.module.SheetReader;

public class Main {
	
	public static String EDIFICIO_INDISTINTO = "INDISTINTO";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception{
		String path = "instancias/pedidos-2018-1-mod.xls";
		
		AulaProcessor ap = new AulaProcessor();
		SheetReader reader = new SheetReader(path,"Aulas1",ap);
		Set<Aula> aulas = new HashSet<Aula>((Collection<Aula>)(List)reader.read());
			
		AulaFinder af = new AulaFinder(aulas);
		Map<Aula,BitSet> disponibilidad = new HashMap <>(); //aca voy tachando	
		Asignador asig = new Asignador(disponibilidad);
		
		Set<String> edificios = new HashSet<>();
		for(Aula a: aulas) {
			edificios.add(a.edificio);
		}
		edificios.add(EDIFICIO_INDISTINTO);
		Preferidor pref = new Preferidor(edificios);
		
		PedidosProcessor pp = new PedidosProcessor(af,asig,pref);

		reader = new SheetReader(path,"Pedidos",pp);

		Set<Preferencia> preferencias = new HashSet<>((Collection<Preferencia>)(List)reader.read());
		for(Preferencia a: preferencias) {
			System.out.println(a);
		}
		Heuristica heu = new Heuristica(asig,preferencias,aulas,disponibilidad);
		System.out.println(disponibilidad);
		heu.asignarPorKant();
	}
}

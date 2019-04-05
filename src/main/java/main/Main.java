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
import core.Puntuador;
import domain.Asignacion;
import domain.Asignador;
import domain.Aula;
import domain.Preferencia;
import domain.Preferidor;
import excel.module.SheetReader;

public class Main {
	
	public static String EDIFICIO_INDISTINTO = "INDISTINTO";
	public static Set<Asignacion> preasignaciones;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws Exception{
		String path = "instancias/pedidos-2018-1.xls";
		
		AulaProcessor ap = new AulaProcessor();
		SheetReader reader = new SheetReader(path,"Aulas",ap);
		Set<Aula> aulas = new HashSet<Aula>((Collection<Aula>)(List)reader.read());
			
		AulaFinder af = new AulaFinder(aulas);
		Map<Aula,BitSet> disponibilidad = Asignador.crearDisponibilidad(aulas); //aca voy tachando	
		Asignador asig = new Asignador(disponibilidad);
		 
		Set<String> edificios = new HashSet<String>();
		for(Aula a: aulas) {
			edificios.add(a.edificio); 
		}
		edificios.add(EDIFICIO_INDISTINTO);
		Preferidor pref = new Preferidor(edificios);
		
		PedidosProcessor pp = new PedidosProcessor(af,asig,pref);
		preasignaciones = pp.preasignaciones;
		
		reader = new SheetReader(path,"Pedidos",pp);
		
		Set<Preferencia> preferencias = new HashSet<Preferencia>((Collection<Preferencia>)(List)reader.read());
		Puntuador puntuador = new Puntuador(new HashMap<String,Integer>());
		Heuristica heu = new Heuristica(aulas,puntuador, asig);
		
		System.out.println("ID	Matería	Turno	Día	Pab. preferido	Pab. asignado	Aula asignada	Capacidad	Inscriptos");

		for(Asignacion preasignacion: pp.preasignaciones) {
		   	System.out.println(preasignacion.clase.id + "	"+
		   			preasignacion.clase.nombre + "	"+ 
		   			preasignacion.clase.comision + "	"+
		   			preasignacion.clase.diaSemana +"	"+
		   			preasignacion.clase.edifPreferido + "	"+
		   			preasignacion.aula.edificio + "	"+ 
		   			preasignacion.aula.nombre+ "	" + 
		   			preasignacion.aula.capacidad+ "	"+ 
		   			preasignacion.clase.cantidadInscriptos);
	        	
		}
		heu.asignar(preferencias);
	
		System.out.println("Puntos de penalidad: " + heu.puntaje);
		
	}
}

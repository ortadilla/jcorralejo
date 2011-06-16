package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Ejercicio7 {

	private static int MAX_PRUEBAS = 100;
	private static int MAX_MOV = 8;
	private static int MAX_PAS = 20;
	
	private static int mejorSolucion;
	private static int costeAniadir = 0;
	private static int costeQuitar = 0;
	private static int costeReemplazar = 0;
	private static Set<Character> alfabeto = new HashSet<Character>();
	
	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					
					String origen = "";
					String destino = "";
					costeAniadir = 0;
					costeQuitar = 0;
					costeReemplazar = 0;
					
					StringTokenizer tokens = new StringTokenizer(linea);
					if(tokens.hasMoreTokens())
						origen = tokens.nextToken();
					if(tokens.hasMoreTokens())
						destino = tokens.nextToken();
					if(tokens.hasMoreTokens()){
						String[] costes = tokens.nextToken().split(",");
						costeAniadir = Integer.parseInt(costes[0]);
						costeQuitar = Integer.parseInt(costes[1]);
						costeReemplazar = Integer.parseInt(costes[2]);
					}
					
				
					mejorSolucion = Integer.MAX_VALUE;
					obtenerAlfabeto(origen, destino);
					transforma(new Movimiento(origen, 0, new ArrayList<String>()), destino);
					
					System.out.println(mejorSolucion);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void obtenerAlfabeto(String origen, String destino){
		alfabeto.clear();
		for(int i=0; i<destino.length(); i++)
			alfabeto.add(destino.charAt(i));
	}
	
	
	
	private static void transforma(Movimiento movOrigen, String destino){
		if(movOrigen.tablero.equals(destino)){
//			System.out.println("Solución encontrada, con coste "+movOrigen.costeMovimiento);
//			System.out.println(movOrigen);
			if(movOrigen.costeMovimiento<mejorSolucion)
				mejorSolucion = movOrigen.costeMovimiento;
		}
		
		else{
			List<Movimiento> movimientosPosibles = calculaMovimientosPosibles(movOrigen, destino.length());
			for(Movimiento movPos : movimientosPosibles){
				if(movPos.costeMovimiento<mejorSolucion)
					transforma(movPos, destino);
			}
		}
	}
	
	
	private static List<Movimiento> calculaMovimientosPosibles(Movimiento movimientoOrigen, int max){
		List<Movimiento> movimientos = new ArrayList<Ejercicio7.Movimiento>();
		List<Movimiento> tablerosHechos = new ArrayList<Ejercicio7.Movimiento>();
		
		//Aniadir
		if(movimientoOrigen.tablero.length()<max){
			for(Character c : alfabeto){
				String nuevoTablero = movimientoOrigen.tablero;
				for(int i=0; i<movimientoOrigen.tablero.length()+1; i++){
					String parte1 = nuevoTablero.substring(0, i);
					String parte2 = nuevoTablero.substring(i, movimientoOrigen.tablero.length());
					
					String tablero = parte1+c+parte2;
					int costeMovimiento = movimientoOrigen.costeMovimiento+costeAniadir;
					if(!movimientoOrigen.movimientos.contains(tablero) && movimientoOrigen.movimientos.size()<MAX_PAS){
						Movimiento mov = new Movimiento(tablero, costeMovimiento, movimientoOrigen.movimientos);
						if(!tablerosHechos.contains(mov) && costeMovimiento<MAX_MOV){
							tablerosHechos.add(mov);
							if(costeMovimiento<mejorSolucion){
								movimientos.add(mov);
//								System.out.println(mov);
							}
						}
					}
				}
			}
		}
		
		//Quitar
		if(movimientoOrigen.tablero.length()>0){
			String nuevoTablero = movimientoOrigen.tablero;
			for(int i=0; i<movimientoOrigen.tablero.length(); i++){
				String parte1 = nuevoTablero.substring(0, i);
				String parte2 = nuevoTablero.substring(i+1, movimientoOrigen.tablero.length());
				
				String tablero = parte1+parte2;
				int costeMovimiento = movimientoOrigen.costeMovimiento+costeQuitar;
				if(!movimientoOrigen.movimientos.contains(tablero) && movimientoOrigen.movimientos.size()<MAX_PAS){
					Movimiento mov = new Movimiento(tablero, costeMovimiento, movimientoOrigen.movimientos);
					if(!tablerosHechos.contains(mov) && costeMovimiento<MAX_MOV){
						tablerosHechos.add(mov);
						if(costeMovimiento<mejorSolucion){
							movimientos.add(mov);
//							System.out.println(mov);
						}
					}
				}
			}
		}
		
		//Reemplazar
		if(movimientoOrigen.tablero.length()>0){
			for(Character c : alfabeto){
				String nuevoTablero = movimientoOrigen.tablero;
				for(int i=0; i<movimientoOrigen.tablero.length(); i++){
					if(nuevoTablero.charAt(i)!=c){
						String parte1 = nuevoTablero.substring(0, i);
						String parte2 = nuevoTablero.substring(i+1, movimientoOrigen.tablero.length());

						String tablero = parte1+c+parte2;
						int costeMovimiento = movimientoOrigen.costeMovimiento+costeReemplazar;
						if(!movimientoOrigen.movimientos.contains(tablero) && movimientoOrigen.movimientos.size()<MAX_PAS){
							Movimiento mov = new Movimiento(tablero, costeMovimiento, movimientoOrigen.movimientos);
							if(!tablerosHechos.contains(mov) && costeMovimiento<MAX_MOV){
								tablerosHechos.add(mov);
								if(costeMovimiento<mejorSolucion){
									movimientos.add(mov);
//									System.out.println(mov);
								}
							}
						}
					}
				}
			}
		}
		
		
		return movimientos;
	}
	
	
	private static class Movimiento{
		List<String> movimientos;
		String tablero;
		int costeMovimiento;
		int heurist;
		
		public Movimiento(String tablero, int costeMovimiento, List<String> movAnteriores) {
			this.tablero = tablero;
			this.costeMovimiento = costeMovimiento;
			if(this.movimientos==null)
				this.movimientos = new ArrayList<String>();
			this.movimientos.addAll(movAnteriores);
			this.movimientos.add(tablero);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((tablero == null) ? 0 : tablero.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Movimiento other = (Movimiento) obj;
			if (tablero == null) {
				if (other.tablero != null)
					return false;
			} else if (!tablero.equals(other.tablero))
				return false;
			return true;
		}

		@Override
		public String toString() {
			String s = "";
			for(String t : movimientos){
				s += t + " --> ";
			}
			return s;
		}
	}
	
	
	

}

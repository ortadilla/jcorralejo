package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Ejercicio6 {
	
	private static int MAX_PRUEBAS = 100;
	private static Map<Integer, Integer> duracion =  new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> digitosNivel =  new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> segundosVueltaCompletaNivel =  new HashMap<Integer, Integer>();

	public static void main (String[] args){
		try{
			
			cargarDatos();
			
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					long suma = 0;

					StringTokenizer tokens = new StringTokenizer(linea);
					int segundos = 0;
					if(tokens.hasMoreTokens()){
						segundos = Integer.parseInt(tokens.nextToken());
						
						suma += lucesSeg1(segundos);
						suma += lucesSeg2(segundos);
						suma += lucesMin1(segundos);
						suma += lucesMin2(segundos);
						suma += lucesHora1(segundos);
						suma += lucesHora2(segundos);
					}
					
					System.out.println (suma);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static int lucesSeg1(int s){
		int suma = 0;
		
		int vueltasCompletas = s/10;
		suma += vueltasCompletas * sumarHasta(9);
		int vueltasIncomp = s%10;
		suma += sumarHasta(vueltasIncomp);
		
		return suma;
	}
	
	private static int lucesSeg2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/60;
		suma += 10 * vueltasCompletas * sumarHasta(5);
		int vueltasIncomp = s%60;
		int vueltasComAnt = vueltasIncomp/10;
		suma += 10 * sumarHasta(vueltasComAnt-1);
		int vueltasIncAnt = vueltasIncomp%10;
		suma += (vueltasIncAnt+1) * duracion.get(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesMin1(int s){
		int suma = 0;
		
		int vueltasCompletas = s/600;
		suma += 60 * vueltasCompletas * sumarHasta(9);
		int vueltasIncomp = s%600;
		int vueltasComAnt = vueltasIncomp/60;
		suma += 60 * sumarHasta(vueltasComAnt-1);
		int vueltasIncAnt = vueltasIncomp%60;
		suma += (vueltasIncAnt+1) * duracion.get(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesMin2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/3600;
		suma += 600 * vueltasCompletas * sumarHasta(5);
		int vueltasIncomp = s%3600;
		int vueltasComAnt = vueltasIncomp/600;
		suma += 600 * sumarHasta(vueltasComAnt-1);
		int vueltasIncAnt = vueltasIncomp%600;
		suma += (vueltasIncAnt+1) * duracion.get(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesHora1(int s){
		int suma = 0;
		
		int vueltasCompletas = s/36000;
		int vueltasCompletas10 = vueltasCompletas*10/24;
		suma += 3600 * vueltasCompletas10 * sumarHasta(9);
		int vueltasCompletas4 = (vueltasCompletas*10)%24;
		suma += 3600 * vueltasCompletas4 * sumarHasta(3);
		
		int vueltasIncomp = s%36000;
		int vueltasComAnt = vueltasIncomp/3600;
		suma += 3600 * sumarHasta(vueltasComAnt-1);
		int vueltasIncAnt = vueltasIncomp%3600;
		suma += (vueltasIncAnt+1) * duracion.get(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesHora2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/86400;
		suma += 36000 * vueltasCompletas * sumarHasta(3);
		int vueltasIncomp = s%86400;
		int vueltasComAnt = vueltasIncomp/36000;
		suma += 36000 * sumarHasta(vueltasComAnt-1);
		int vueltasIncAnt = vueltasIncomp%36000;
		suma += (vueltasIncAnt+1) * duracion.get(vueltasComAnt);
		
		return suma;
	}
	
	private static void cargarDatos(){
		duracion.put(0, 6);
		duracion.put(1, 2);
		duracion.put(2, 5);
		duracion.put(3, 5);
		duracion.put(4, 4);
		duracion.put(5, 5);
		duracion.put(6, 6);
		duracion.put(7, 3);
		duracion.put(8, 7);
		duracion.put(9, 6);
		
		digitosNivel.put(0, 0);
		digitosNivel.put(1, 10);
		digitosNivel.put(2, 6);
		digitosNivel.put(3, 10);
		digitosNivel.put(4, 6);
		digitosNivel.put(5, 10);
		digitosNivel.put(6, 3);
		
		segundosVueltaCompletaNivel.put(0, 1);
		segundosVueltaCompletaNivel.put(1, 10);
		segundosVueltaCompletaNivel.put(2, 60);
		segundosVueltaCompletaNivel.put(3, 600);
		segundosVueltaCompletaNivel.put(4, 3600);
		segundosVueltaCompletaNivel.put(5, 36000);
		segundosVueltaCompletaNivel.put(6, 86400);
	}
	
	private static int sumarHasta(int x){
		int suma = 0;
		if(x>=0){
			for(int i=0; i<=x; i++)
				suma += duracion.get(i);
		}
		return suma;
	}
}

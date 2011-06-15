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
						
						suma += lucesNivel(segundos, 1);
						suma += lucesNivel(segundos, 2);
						suma += lucesNivel(segundos, 3);
						suma += lucesNivel(segundos, 4);
						suma += lucesNivel(segundos, 5);
						suma += lucesNivel(segundos, 6);
					}
					
					System.out.println (suma);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static int lucesNivel(int s, int n){
		int suma = 0;

		int vueltasCompletas = s/segundosVueltaCompletaNivel.get(n);
		suma += vueltasCompletas * segundosVueltaCompletaNivel.get(n-1) * sumarHasta(digitosNivel.get(n-1)-1);

		int segIncomp = s%segundosVueltaCompletaNivel.get(n);
		int vueltasCompletasNivelAnterior = segIncomp/segundosVueltaCompletaNivel.get(n);
		suma += segundosVueltaCompletaNivel.get(n) * sumarHasta(vueltasCompletasNivelAnterior-1);
		int vueltasIncompletasNivelAnterior = segIncomp%segundosVueltaCompletaNivel.get(n);
		if(n>1)
			suma += (vueltasIncompletasNivelAnterior+1) * duracion.get(vueltasCompletasNivelAnterior);
		else if(n==1)
			suma += sumarHasta(vueltasIncompletasNivelAnterior);
		
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

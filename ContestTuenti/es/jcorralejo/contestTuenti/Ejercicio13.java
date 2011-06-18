package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Ejercicio13 {
	
	private static int MAX_PRUEBAS = 100;
	private static Map<Integer, Integer> duracion =  new HashMap<Integer, Integer>();

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
		suma += 6 + vueltasCompletas * sumarHasta(10);
		int vueltasIncomp = s%10;
		suma += sumarHasta(vueltasIncomp);
		
		return suma;
	}
	
	private static int lucesSeg2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/60;
		suma += 6 + vueltasCompletas * (sumarHasta(5) + 2);
		int vueltasIncomp = s%60;
		int vueltasComAnt = vueltasIncomp/10;
		suma += sumarHasta(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesMin1(int s){
		int suma = 0;
		
		int vueltasCompletas = s/600;
		suma += 6 + vueltasCompletas * sumarHasta(10);
		int vueltasIncomp = s%600;
		int vueltasComAnt = vueltasIncomp/60;
		suma += sumarHasta(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesMin2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/3600;
		suma += 6 + vueltasCompletas * (sumarHasta(5) + 2);
		int vueltasIncomp = s%3600;
		int vueltasComAnt = vueltasIncomp/600;
		suma += sumarHasta(vueltasComAnt);

		return suma;
	}
	
	private static int lucesHora1(int s){
		int suma = 0;
		
		int vueltasCompletas = s/36000;
		int vueltasCompletas10 = vueltasCompletas*10/24;
		suma += 6 + vueltasCompletas10 * sumarHasta(10);
		int vueltasCompletas4 = (vueltasCompletas*10)%24;
		suma += vueltasCompletas4 * (sumarHasta(3) + 2);
		
		int vueltasIncomp = s%36000;
		int vueltasComAnt = vueltasIncomp/3600;
		suma += sumarHasta(vueltasComAnt);
		
		return suma;
	}
	
	private static int lucesHora2(int s){
		int suma = 0;
		
		int vueltasCompletas = s/86400;
		suma += 6 + vueltasCompletas * (sumarHasta(2) + 2);
		int vueltasIncomp = s%86400;
		int vueltasComAnt = vueltasIncomp/36000;
		suma += sumarHasta(vueltasComAnt);
		
		return suma;
	}
	
	private static void cargarDatos(){
		duracion.put(1, 0);
		duracion.put(2, 4);
		duracion.put(3, 1);
		duracion.put(4, 1);
		duracion.put(5, 2);
		duracion.put(6, 1);
		duracion.put(7, 1);
		duracion.put(8, 3);
		duracion.put(9, 0);
		duracion.put(10, 2);
	}
	
	private static int sumarHasta(int x){
		int suma = 0;
		if(x>=0){
			for(int i=1; i<=x; i++)
				suma += duracion.get(i);
		}
		return suma;
	}

}

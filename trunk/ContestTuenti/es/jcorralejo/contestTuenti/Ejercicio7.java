package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ejercicio7 {

	private static int MAX_PRUEBAS = 100; 

	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					int coste = Integer.MAX_VALUE;
					String origen = "";
					String destino = "";
					int costeAniadir = 0;
					int costeQuitar = 0;
					int costeReemplazar = 0;
					
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
					
					
					System.out.println (coste);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

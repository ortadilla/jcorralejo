package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Ejercicio1 {
	
	private static int MAX_PRUEBAS = 100; 

	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					BigInteger suma = BigInteger.ZERO;
					linea = linea.replace('+', ' ');
					StringTokenizer tokens = new StringTokenizer(linea);
					while(tokens.hasMoreTokens())
						suma = suma.add(new BigInteger(tokens.nextToken()));
					System.out.println (suma);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

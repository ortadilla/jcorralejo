package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Ejercicio3 {

	private static int MAX_PRUEBAS = 100; 

	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					int suma = 0;
					StringTokenizer tokens = new StringTokenizer(linea);
					int max = 0;
					if(tokens.hasMoreTokens())
						max = Integer.parseInt(tokens.nextToken());
					
					for(int j=2; j<max; j++){
						if(esPrimo(j)){
							int inv = inverso(j);
							if(j!=inv && esPrimo(inv))
								suma += j;
						}
					}
					
					System.out.println (suma);
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static boolean esPrimo(int numero){
		int contador = 2;
		boolean primo = true;
		while ((primo) && (contador!=numero)){
			if (numero % contador == 0)
				primo = false;
			contador++;
		}
		return primo;
	}
	
	private static int inverso(int numero){
		int inv = 0;
		int res;
		int n = numero;
		while(n!=0){
			res =  n%10;
			n = n/10;
			inv = inv*10 + res;
		}
		return inv;
	}
}

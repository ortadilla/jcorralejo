package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ejercicio11 {

	public static void main (String args[]){

		try{

			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			String linea = br.readLine();
			if(linea!=null && !"".equals(linea)){
				int T = Integer.parseInt(String.valueOf(linea.charAt(0)));
				for(int j=0; j<T; j++){
					int k = 0;
					int df = 0;
					int n = 0;
					List<Integer> d = new ArrayList<Integer>();
					List<Integer> dEntre = new ArrayList<Integer>();

					linea = br.readLine();
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						if(tokens.hasMoreTokens())
							k = Integer.parseInt(tokens.nextToken());
					}
					linea = br.readLine();
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						if(tokens.hasMoreTokens())
							df = Integer.parseInt(tokens.nextToken());
					}
					linea = br.readLine();
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						if(tokens.hasMoreTokens())
							n = Integer.parseInt(tokens.nextToken());
					}
					linea = br.readLine();
					int disAnt = 0;
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						while(tokens.hasMoreTokens()){
							int disGas = Integer.parseInt(tokens.nextToken());
							d.add(disGas);
							dEntre.add(disGas-disAnt);
							disAnt = disGas;
						}
					}
					dEntre.add(df-disAnt);
					System.out.println(solucion(k, df, d, dEntre));
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static String solucion(int k, int df,List<Integer> d, List<Integer> dEntre){

		List<Boolean> sol = new ArrayList<Boolean>(); 
		int kmsRecorridos = 0;

		for(int i=0; i<dEntre.size(); i++){
			if(i<dEntre.size()-1 && (dEntre.get(i) + dEntre.get(i+1) + kmsRecorridos) > k){
				sol.add(i, true);
				kmsRecorridos = 0;
			}
			
			else if(i==dEntre.size()-1 && (dEntre.get(i) + kmsRecorridos) == k){
				sol.add(i, true);
				kmsRecorridos = 0;
			}
			
			else{
				sol.add(i, false);
				kmsRecorridos += dEntre.get(i);
			}
		}

		
		String paradas = "";
		for(int i=0; i<d.size(); i++){
			if(sol.get(i))
				paradas += d.get(i) + " " ;
		}
		
		if(paradas.isEmpty())
			paradas = "No stops ";

		return paradas.substring(0, paradas.length()-1);
	}




}

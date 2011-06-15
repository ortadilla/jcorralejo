package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ejercicio5 {
	private static int MAX_PRUEBAS = 100; 

	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					
					List<Integer> producciones = new ArrayList<Integer>();
					int numVacas = 0;
					int pesoMax = 0;
					List<Integer> pesosVacas = new ArrayList<Integer>();
					List<Integer> produccionesVacas = new ArrayList<Integer>();
					String[] argumentos = linea.split(" ");
					if(argumentos.length==4){
						numVacas = Integer.parseInt(argumentos[0]);
						pesoMax = Integer.parseInt(argumentos[1]);
						
						String[] pesos = argumentos[2].split(",");
						String[] prod = argumentos[3].split(",");
						for(int j=0; j<pesos.length; j++){
							pesosVacas.add(Integer.parseInt(pesos[j]));
							produccionesVacas.add(Integer.parseInt(prod[j]));
						}
					}
					
					for(int j=0; j<(Math.pow(2, numVacas)); j++){
						String bin = Integer.toString(Integer.parseInt(""+j, 10), 2);
						int peso = 0;
						int prod = 0;
						for(int k=0; k<bin.length(); k++){
							if(peso<=pesoMax){
								if(bin.charAt(k)=='1'){
									peso += pesosVacas.get(k);
									prod += produccionesVacas.get(k);
								}
							}else
								break;
							
							if(peso<=pesoMax && k==bin.length()-1)
								producciones.add(prod);
						}
					}
					
					if(!producciones.isEmpty()){
						Collections.sort(producciones);
						System.out.println (producciones.get(producciones.size()-1));
					}
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Ejercicio10 {

	private static Map<List<String>, String> combos = new HashMap<List<String>, String>();

	public static void main (String args[]){

		try{

			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			String linea = br.readLine();
			if(linea!=null && !"".equals(linea)){
				int N = Integer.parseInt(String.valueOf(linea.charAt(0)));
				for(int j=0; j<N; j++){
					List<String> combo = new ArrayList<String>();
					String accion = null;

					linea = br.readLine();
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						while(tokens.hasMoreTokens())
							combo.add(tokens.nextToken());
					}
					linea = br.readLine();
					if(linea!=null && !"".equals(linea)){
						StringTokenizer tokens = new StringTokenizer(linea);
						if(tokens.hasMoreTokens())
							accion = tokens.nextToken();
					}

					combos.put(combo, accion);
				}

				linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					int T = Integer.parseInt(String.valueOf(linea.charAt(0)));
					for(int j=0; j<T; j++){
						List<String> teclas = new ArrayList<String>();

						linea = br.readLine();
						if(linea!=null && !"".equals(linea)){
							StringTokenizer tokens = new StringTokenizer(linea);
							while(tokens.hasMoreTokens())
								teclas.add(tokens.nextToken());
						}

						System.out.println(accionAsociada(teclas));
					}
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static String accionAsociada(List<String> teclas){
		String accion = "";

		for(List<String> combinacion : combos.keySet()){
			boolean enc = true;
			for(String teclaCombinacion : combinacion){
				if(!teclas.contains(teclaCombinacion)){
					enc = false;
					break;
				}
			}
			if(enc){
				accion = combos.get(combinacion) ;
				break;
			}
		}


		return accion;
	}
}
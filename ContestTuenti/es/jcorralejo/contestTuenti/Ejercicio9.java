package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ejercicio9 {

	public static void main (String[] args){
		try{
			
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
				
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					int N = 0;
					N = Integer.parseInt(String.valueOf(linea.charAt(0)));
					for(int j=0; j<N; j++){
						int l = 0;
						int t = 0;
						linea = br.readLine();
						if(linea!=null && !"".equals(linea)){
							StringTokenizer tokens = new StringTokenizer(linea);
							if(tokens.hasMoreTokens())
								l = Integer.parseInt(tokens.nextToken());
						}
						linea = br.readLine();
						if(linea!=null && !"".equals(linea)){
							StringTokenizer tokens = new StringTokenizer(linea);
							if(tokens.hasMoreTokens())
								t = Integer.parseInt(tokens.nextToken());
						}
						
						
						
						boolean ninguno = true;
						int min = Math.min(t, l);
						String cad = "";
						for(int k=0; k<min;k++){
							if(t%2==0 && k%2!=0 || t%2!=0 && k%2==0){
								cad  += k+" ";
								ninguno = false;
							}
						}
						if(ninguno)
							cad = "All lights are off :(";
						else
							cad = cad.substring(0, cad.length()-1);
						System.out.println(cad);
						
					}
				}

			i++;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

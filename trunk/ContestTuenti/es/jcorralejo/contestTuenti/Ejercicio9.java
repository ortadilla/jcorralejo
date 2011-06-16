package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ejercicio9 {

	private static int MAX_PRUEBAS = 100; 
	private static int MAX_L = 64; 
	private static int MAX_T = 1000000; 
	
	private static boolean[][] tablero = new boolean[MAX_L][MAX_T];

	public static void main (String[] args){
		try{
			
			cargarTablero();

			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
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
						System.out.println();
						for(int k=0; k<l; k++){
							if(tablero[t][k]){
								System.out.print(k+" ");
								ninguno = false;
							}
						}
						if(ninguno)
							System.out.println("All lights are off :(");

					}
				}
			}

			i++;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static void cargarTablero(){
		for(int i=0; i<MAX_L; i++){
			for(int j=0; j<MAX_T; j++){
				if(i>j){
					if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0))
						tablero[i][j] = true;	
				}
			}
		}
		
//		for(int i=0; i<MAX_L; i++){
//			for(int j=0; j<MAX_T; j++){
//				System.out.print(tablero[i][j] ? '1' : '0');
//			}
//			System.out.println();
//		}
	}

}

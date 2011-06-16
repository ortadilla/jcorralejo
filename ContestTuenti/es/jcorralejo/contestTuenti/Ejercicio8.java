package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Ejercicio8 {
	
	private static int MAX_PRUEBAS = 100;

	public static void main(String[] args) {
		
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					String x = "";
					String y = "";
					StringTokenizer tokens = new StringTokenizer(linea);
					if(tokens.hasMoreTokens())
						x = tokens.nextToken();
					if(tokens.hasMoreTokens())
						y = tokens.nextToken();
					System.out.println(maxSubsecuencia(x, y));
				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private static String maxSubsecuencia(String x, String y){
		int M = x.length();
		int N = y.length();
		char[][] sol = new char[M][N];
		
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				if(x.charAt(i)==y.charAt(j))
					sol[i][j] = x.charAt(i);
				else
					sol[i][j] = '·';
			}
		}

		String maxSub = "";
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				String sub = "";
				if(sol[i][j]!='·'){
					sub += sol[i][j];
					for(int k=1; i+k<M && j+k<N; k++){
						if(sol[i+k][j+k]!='·')
							sub += sol[i+k][j+k];
						else
							break;
					}
				}
				if(maxSub.length()<sub.length())
					maxSub = sub;
			}
		}
		
//		for(int i=0; i<M; i++){
//			for(int j=0; j<N; j++){
//				System.out.print(sol[i][j]);
//			}
//			System.out.println();
//		}
		
		return maxSub;

	}

}

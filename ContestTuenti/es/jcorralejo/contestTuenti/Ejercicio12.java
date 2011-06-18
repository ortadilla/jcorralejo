package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Ejercicio12 {

	private static int INF = Integer.MAX_VALUE;
	private static class Camino{
		Integer origen, destino, coste;

		public Camino(Integer origen, Integer destino, Integer coste) {
			super();
			this.origen = origen;
			this.destino = destino;
			this.coste = coste;
		}
	}


	private static void calculaCaminos(List<Camino> caminos, int numCaminos, int numPlanetas, int origen, int destino){
		int distancia[] = new int[numPlanetas];
		for (int i=0; i < numPlanetas; ++i)
			distancia[i] = INF;

		distancia[origen] = 0;

		for (int i=0; i < numPlanetas; ++i){
			for (int j=0; j < numCaminos; ++j){
				if (distancia[caminos.get(j).origen] != INF) {
					if (distancia[caminos.get(j).origen] + caminos.get(j).coste < distancia[caminos.get(j).destino]){
						int coste = distancia[caminos.get(j).origen] + caminos.get(j).coste;
						if (coste < distancia[caminos.get(j).destino])
							distancia[caminos.get(j).destino] = coste;
					}
				}
			}
		}

		for (int i=0; i < numCaminos; ++i) {
			if (distancia[caminos.get(i).destino] > distancia[caminos.get(i).origen] + caminos.get(i).coste) {
				System.out.println("BAZINGA");
				return;
			}
		}

//		for (int i=0; i < numPlanetas; ++i) {
//			System.out.println("Distancia entre "+origen+" y "+i+" = "+distancia[i]);
//		}
		System.out.println(distancia[destino]);
	}
//	
//	public static void main (String args[]){
//		
//		Camino edges[] = new Camino[14];
//		edges[0] = new Camino(0,1,3000);
//		edges[1] = new Camino(0,2,2000);
//		edges[2] = new Camino(1,3,-2195);
//		edges[3] = new Camino(1,4,0);
//		edges[4] = new Camino(2,1,445);
//		edges[5] = new Camino(3,1,2900);
//		edges[6] = new Camino(3,5,500);
//		edges[7] = new Camino(4,0,2300);
//		edges[8] = new Camino(4,1,1000);
//		edges[9] = new Camino(4,2,1200);
//		edges[10] = new Camino(4,3,1800);
//		edges[11] = new Camino(5,0,0);
//		edges[12] = new Camino(5,2,1250);
//		edges[13] = new Camino(5,2,1250);
//           
//		calculaCaminos(edges, 14, 6, 0);
//	}
	
	private static int MAX_PRUEBAS = 100;
	
	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					StringTokenizer tokens = new StringTokenizer(linea);

					List<Camino> caminos = new ArrayList<Ejercicio12.Camino>();
					int numPlanetas = 0;
					int origen = 0;
					int destino = 0;
					if(tokens.hasMoreElements())
						numPlanetas = Integer.parseInt(tokens.nextToken());

					if(tokens.hasMoreElements())
						origen = Integer.parseInt(tokens.nextToken());

					if(tokens.hasMoreElements())
						destino = Integer.parseInt(tokens.nextToken());

					while(tokens.hasMoreTokens()){
						String[] camino = tokens.nextToken().split(",");
						int o = Integer.parseInt(camino[0]);
						int d = Integer.parseInt(camino[1]);
						int c = Integer.parseInt(camino[2]);

						caminos.add(new Camino(o, d, c));
					}

					calculaCaminos(caminos, caminos.size(), numPlanetas, origen, destino);

				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}

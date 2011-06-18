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
		
		@Override
		public String toString() {
			return origen+"->"+destino+"("+coste+")";
		}
	}
	
	private static class Ruta{
		List<Camino> ruta;
		int coste;
		public Ruta(int coste) {
			super();
			if(ruta==null)
				ruta = new ArrayList<Ejercicio12.Camino>();
			this.coste = coste;
		}
	}


	private static void calculaCaminos(List<Camino> caminos, int numCaminos, int numPlanetas, int origen, int destino){
		Ruta distancia[] = new Ruta[numPlanetas];
		for (int i=0; i < numPlanetas; ++i)
			distancia[i] = new Ruta(INF);

		distancia[origen].coste = 0;

		for (int i=0; i < numPlanetas; ++i){
			for (int j=0; j < numCaminos; ++j){
				if (distancia[caminos.get(j).origen].coste != INF) {
					if (distancia[caminos.get(j).origen].coste + caminos.get(j).coste < distancia[caminos.get(j).destino].coste){
						int coste = distancia[caminos.get(j).origen].coste + caminos.get(j).coste;
						if (coste < distancia[caminos.get(j).destino].coste){
							List<Camino> rutaAnterior = new ArrayList<Ejercicio12.Camino>(); 
							rutaAnterior.addAll(distancia[caminos.get(j).origen].ruta);
							distancia[caminos.get(j).destino].ruta = rutaAnterior;
							distancia[caminos.get(j).destino].ruta.add(new Camino(caminos.get(j).origen, caminos.get(j).destino, coste));
							distancia[caminos.get(j).destino].coste = coste;
						}
					}
				}
			}
		}

		boolean bazinga = false;
		int org , dest;
		for (int i=0; i < distancia[destino].ruta.size(); ++i) {
			org = distancia[destino].ruta.get(i).origen;
			dest = distancia[destino].ruta.get(i).destino;
			for(int j=0; j < distancia[destino].ruta.size(); j++){
				if(i!=j){
					if(org==distancia[destino].ruta.get(j).origen && dest==distancia[destino].ruta.get(j).destino){
						bazinga = true;
						break;
					}
				}
			}
			if(bazinga)
				break;
		}

		//		for (int i=0; i < numPlanetas; ++i) {
		//			System.out.println("Distancia entre "+origen+" y "+i+" = "+distancia[i]);
		//		}
		if(bazinga || distancia[destino].coste>=INF)
			System.out.println("BAZINGA");
		else
			System.out.println(distancia[destino].coste+ANIO_INICIAL);
	}

	private static int MAX_PRUEBAS = 100;
	private static int ANIO_INICIAL = 25000;

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

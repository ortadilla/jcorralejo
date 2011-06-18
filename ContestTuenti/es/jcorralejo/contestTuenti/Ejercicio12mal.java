package es.jcorralejo.contestTuenti;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Ejercicio12mal {

	private static int MAX_PRUEBAS = 100; 
	private static Map<Integer,List<Camino>> caminos = new HashMap<Integer, List<Camino>>();
	private static int mejorCamino = Integer.MAX_VALUE;
	private static List<Camino> caminosHechos = new ArrayList<Ejercicio12mal.Camino>();
	private static Camino mejor;

	public static void main (String[] args){
		try{
			BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
			int i=0;
			while(i<MAX_PRUEBAS){
				String linea = br.readLine();
				if(linea!=null && !"".equals(linea)){
					StringTokenizer tokens = new StringTokenizer(linea);

					int numPlanetas;
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

						if(!caminos.containsKey(o))
							caminos.put(o, new ArrayList<Camino>());

						caminos.get(o).add(new Camino(o, d, c, new ArrayList<Ejercicio12mal.Camino>(), null));
					}

					mejorCamino(new Camino(origen, origen, 0, new ArrayList<Ejercicio12mal.Camino>(), null), destino);

					System.out.println(mejorCamino==Integer.MAX_VALUE ? "BAZINGA" : mejorCamino+25000);
//					System.out.println(mejor);
					mejorCamino = Integer.MAX_VALUE;
					caminos.clear();
					caminosHechos.clear();


				}
				i++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void mejorCamino(Camino origen, int destino){
		if(origen.destino==destino){
			if(mejorCamino>origen.coste){
				mejorCamino = origen.coste;
				mejor = origen;
			}
		}else{
			List<Camino> caminosPosibles = obtenerCaminosPosibles(origen);
			for(Camino camino : caminosPosibles){
//				if(mejorCamino > camino.coste){
					mejorCamino(camino, destino);
//				}
			}
		}
	}

	private static List<Camino> obtenerCaminosPosibles(Camino c){
		List<Camino> caminosPosibles = new ArrayList<Ejercicio12mal.Camino>();
		if(caminos.containsKey(c.destino)){
			for(Camino camino : caminos.get(c.destino)){
				boolean conIn = contieneInverso(c, camino.origen, camino.destino);
				if (c.caminos!=null && !c.caminos.contains(camino) 
				&& ((conIn && camino.coste<0) || !conIn)){
					Camino caminoPosible = new Camino(camino.origen, camino.destino, c.coste+camino.coste, c.caminos, camino);
					
					if(!caminosHechos.contains(caminoPosible)){
						caminosHechos.add(caminoPosible);
						caminosPosibles.add(caminoPosible);
					}else{
						Camino caminoHecho = caminosHechos.get(caminosHechos.indexOf(caminoPosible));
						if(caminoHecho.coste>caminoPosible.coste){
							caminoHecho.coste = caminoPosible.coste;
							caminosPosibles.add(caminoPosible);
						}
					}
				}
			}
		}

		return caminosPosibles;
	}

	private static boolean contieneInverso(Camino camino, int origen, int destino){
		boolean contiene = false;
		for(Camino c : camino.caminos){
			if(c.origen==destino && c.destino==origen){
				contiene = true;
				break;
			}
		}
		return contiene;
	}



	public static class Camino extends Object{
		int origen;
		int destino;
		int coste;
		List<Camino> caminos;

		public Camino(int origen, int destino, int coste, List<Camino> camAnt, Camino camino) {
			super();
			this.origen = origen;
			this.destino = destino;
			this.coste = coste;

			if(this.caminos==null)
				this.caminos = new ArrayList<Ejercicio12mal.Camino>();
			if(camAnt!=null)
				this.caminos.addAll(camAnt);
			if(camino!=null)
				caminos.add(camino);

		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + destino;
			result = prime * result + origen;
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Camino other = (Camino) obj;
			if (destino != other.destino)
				return false;
			if (origen != other.origen)
				return false;
			return true;
		}



		@Override
		public String toString() {
			return origen+" -> "+destino+" ("+coste+")";
		}
	}
	
	



}

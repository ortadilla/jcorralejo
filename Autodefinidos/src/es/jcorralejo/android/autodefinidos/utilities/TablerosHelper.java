package es.jcorralejo.android.autodefinidos.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entidades.Casilla;
import entidades.Palabra;
import entidades.PalabrasTablero;
import entidades.Tablero;

public class TablerosHelper {
	
	public static final List<Tablero> tablerosPequenios = Arrays.asList(crearTableroPequenioUno());
	public static final List<Tablero> tablerosMedianos = null; //Arrays.asList(null);
	public static final List<Tablero> tablerosGrandes = null; //Arrays.asList(null);
	
	public static Tablero crearTablero(int tamanio){
		Tablero tablero = null;

		Random r = new Random(System.currentTimeMillis());
		if(Constantes.TAMANIO_PEQUENIO == tamanio){
			tablero = tablerosPequenios.get(r.nextInt(tablerosPequenios.size()));			
		}else if (Constantes.TAMANIO_MEDIANO == tamanio){
			tablero = tablerosMedianos.get(r.nextInt(tablerosMedianos.size()));			
		}else if (Constantes.TAMANIO_GRANDE == tamanio){
			tablero = tablerosGrandes.get(r.nextInt(tablerosGrandes.size()));			
		}
		
		return tablero;
	}
	
	private static Tablero crearTableroPequenioUno(){
		Tablero tablero = new Tablero(Constantes.TAMANIO_PEQUENIO, 1);
		for(int i=0; i<Constantes.TAMANIO_PEQUENIO_ALTO; i++){
			for(int j=0; j<Constantes.TAMANIO_PEQUENIO_ANCHO; j++){
				if(i==0 && j==0)
					tablero.setCasilla(i, j, new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_DERECHA_ABAJO, Casilla.DIRECCION_ABAJO_DERECHA), true));
				else if((i==2 && j==0) || (i==4 && j==0) || (i==6 && j==0))
					tablero.setCasilla(i, j, new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_DERECHA, Casilla.DIRECCION_ABAJO_DERECHA), true));
				else if((i==0 && j==2) || (i==0 && j==4))
					tablero.setCasilla(i, j, new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_DERECHA_ABAJO, Casilla.DIRECCION_ABAJO), true));
				else
					tablero.setCasilla(i, j, new Casilla(null, null, null, 0, null, false));
			}
		}
		
		return tablero;
	}
	

	public static void rellenarTablero(Tablero tablero){
		if(tablero!=null){
			List<PalabrasTablero> pts = PalabrasTableroHelper.getPalabraTablero(tablero.getTamanio()+"-"+tablero.getPlantilla());
			//Deberemos pillar o el que haya que cargar guardado o uno que no se haya jugado todavía
			PalabrasTablero pt = pts.get(0);
			for(int i=0; i<pt.getAlto(); i++){
				for(int j=0; j<pt.getAncho(); j++){
					Casilla palabras = pt.getPalabras(i, j);
					if(palabras!=null && palabras.getPalabras().size()>0){
						Casilla casilla = tablero.getCasilla(i, j);
						casilla.setPalabras(new ArrayList<Palabra>());
						for(int k=0; k<palabras.getPalabras().size(); k++){
							casilla.getPalabras().add(palabras.getPalabras().get(k));
						}
					}
				}
			}
		}
	}

}

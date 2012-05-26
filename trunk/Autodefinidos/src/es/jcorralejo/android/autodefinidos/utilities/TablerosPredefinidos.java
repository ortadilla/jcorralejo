package es.jcorralejo.android.autodefinidos.utilities;

import java.util.Arrays;

import entidades.Casilla;
import entidades.Tablero;

public class TablerosPredefinidos {
	
	public static final Tablero tableroPequenioUno = crearTableroPequenioUno();
	public static final Tablero tableroPequenioDos = null;
	public static final Tablero tableroMedianoUno = null;
	public static final Tablero tableroMedianoDos = null;
	public static final Tablero tableroMedianoTres = null;
	public static final Tablero tableroGrandeUno = null;
	public static final Tablero tableroGrandeDos = null;
	public static final Tablero tableroGrandeTres = null;
	public static final Tablero tableroGrandeCuatro = null;
	
	
	private static Tablero crearTableroPequenioUno(){
		Tablero tablero = new Tablero(Constantes.TAMANIO_PEQUENIO_ALTO, Constantes.TAMANIO_PEQUENIO_ANCHO);
		for(int i=0; i<Constantes.TAMANIO_PEQUENIO_ALTO; i++){
			for(int j=0; j<Constantes.TAMANIO_PEQUENIO_ANCHO; j++){
				if(i==0 && j==0)
					tablero.getTablero()[i][j] = new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_ABAJO_DERECHA, Casilla.DIRECCION_DERECHA_ABAJO), true);
				else if((i==2 && j==0) || (i==4 && j==0) || (i==6 && j==0))
					tablero.getTablero()[i][j] = new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_DERECHA, Casilla.DIRECCION_DERECHA_ABAJO), true);
				else if((i==0 && j==2) || (i==0 && j==4))
					tablero.getTablero()[i][j] = new Casilla(null, null, null, 2, Arrays.asList(Casilla.DIRECCION_ABAJO_DERECHA, Casilla.DIRECCION_ABAJO), true);
				else
					tablero.getTablero()[i][j] = new Casilla(null, null, null, 0, null, false);
			}
		}
		
		return tablero;
	}

}

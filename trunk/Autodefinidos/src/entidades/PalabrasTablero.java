package entidades;

import es.jcorralejo.android.autodefinidos.utilities.Constantes;

public class PalabrasTablero {
	
	public static final int PLANTILLA_PEQUENIA_UNO = 1;
	
	private int id;
	private int tamanio; 
	private int plantilla; 
	private int alto;
	private int ancho;
	private Casilla[][] palabras; 
	
	public Casilla getPalabras(int i, int j){
		Casilla casilla = null;
		if(i>=0 && j>=0 && i<getAlto() && j<getAncho())
			casilla = palabras[i][j];
		return casilla;	
	}
	
	public void setCasilla(int i, int j, Casilla casilla){
		if(i>=0 && j>=0 && i<alto && j<ancho)
			palabras[i][j] = casilla;
	}
	
	
	public PalabrasTablero(int id, int tamanio, int plantilla){
		this.id = id;
		this.tamanio = tamanio;
		this.plantilla = plantilla;
		
		int alto, ancho;
		if(Constantes.TAMANIO_PEQUENIO == tamanio){
			alto = Constantes.TAMANIO_PEQUENIO_ALTO;
			ancho = Constantes.TAMANIO_PEQUENIO_ANCHO;
		}
		else if (Constantes.TAMANIO_MEDIANO == tamanio){
			alto = Constantes.TAMANIO_MEDIANO_ALTO;
			ancho = Constantes.TAMANIO_MEDIANO_ANCHO;
		}
		else if (Constantes.TAMANIO_GRANDE == tamanio){
			alto = Constantes.TAMANIO_GRANDE_ALTO;
			ancho = Constantes.TAMANIO_GRANDE_ANCHO;
		}
		else
			throw new IllegalArgumentException("Tamaño de tablero no disponible");
		
		palabras = new Casilla[alto][ancho];
		this.alto = alto;
		this.ancho = ancho;
	}


	public int getTamanio() {
		return tamanio;
	}


	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}


	public int getPlantilla() {
		return plantilla;
	}


	public void setPlantilla(int plantilla) {
		this.plantilla = plantilla;
	}


	public int getAlto() {
		return alto;
	}


	public void setAlto(int alto) {
		this.alto = alto;
	}


	public int getAncho() {
		return ancho;
	}


	public void setAncho(int ancho) {
		this.ancho = ancho;
	}


}

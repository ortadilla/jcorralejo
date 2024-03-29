package entidades;

import es.jcorralejo.android.autodefinidos.utilities.Constantes;


public class Tablero {
	
	private Casilla[][] tablero;
	private int alto;
	private int ancho;
	private int tamanio;
	private int plantilla; // Indica la plantilla dentro de los tableros del mismo tama�o
	
	public Casilla getCasilla(int i, int j){
		Casilla casilla = null;
		if(i>=0 && j>=0 && i<getAlto() && j<getAncho())
			casilla = tablero[i][j];
		return casilla;	
	}
	
	public void setCasilla(int i, int j, Casilla casilla){
		if(i>=0 && j>=0 && i<getAlto() && j<getAncho())
			tablero[i][j] = casilla;
	}
	
	public Tablero(int tamanio, int plantilla){
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
			throw new IllegalArgumentException("Tama�o de tablero no disponible");
		
		tablero = new Casilla[alto][ancho];
		this.alto = alto;
		this.ancho = ancho;
	}
	
	public int getAlto(){
		return alto; 
	}
	
	public int getAncho(){
		return ancho; 
	}
	
	public void imprimir(boolean usuario){
		if(tablero!=null){
			for(int i=0; i<tablero.length; i++){
				System.out.println("");
				for(int j=0; j<tablero[i].length; j++){
					String casilla = "";
					if(tablero[i][j].isPregunta()){
						if(tablero[i][j].isDerecha())
							casilla += ">";
						if(tablero[i][j].isDerechaAbajo())
							casilla += "|>";
						if(tablero[i][j].isAbajo())
							casilla += "v";
						if(tablero[i][j].isAbajoDerecha())
							casilla += "-v";
					}else{
//						casilla = " "+(usuario ? tablero[i][j].getLetraUsuario() : tablero[i][j].getLetraCorrecta())+" ";
						casilla = " @ ";
					}
					System.out.print("\t\t"+casilla);
				}
			}
			System.out.println("");
		}
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

}

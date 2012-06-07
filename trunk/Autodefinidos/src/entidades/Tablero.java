package entidades;


public class Tablero {
	
	private Casilla[][] tablero;
	private int alto;
	private int ancho;
	
	public Tablero(int alto, int ancho) {
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

	public Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

}

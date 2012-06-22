package entidades;

public class Flecha {
	
	public static final int POSICION_ARRIBA = 1;
	public static final int POSICION_CENTRO = 2;
	public static final int POSICION_ABAJO = 3;
	
	private int direccion;
	private int posicion;
	
	
	public Flecha(int direccion, int posicion) {
		this.direccion = direccion;
		this.posicion = posicion;
	}
	
	public boolean isDerecha(){
		return direccion==Casilla.DIRECCION_DERECHA;
	}
	
	public boolean isDerechaAbajo(){
		return direccion==Casilla.DIRECCION_DERECHA_ABAJO;
	}
	
	public boolean isAbajo(){
		return direccion==Casilla.DIRECCION_ABAJO;
	}

	public boolean isAbajoDerecha(){
		return direccion==Casilla.DIRECCION_ABAJO_DERECHA;
	}
	
	public boolean isPosicionArriba(){
		return posicion==POSICION_ARRIBA;
	}
	
	public boolean isPosicionCentro(){
		return posicion==POSICION_CENTRO;
	}
	
	public boolean isPosicionAbajo(){
		return posicion==POSICION_ABAJO;
	}
	
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

}

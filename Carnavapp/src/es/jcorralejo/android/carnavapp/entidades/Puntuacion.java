package es.jcorralejo.android.carnavapp.entidades;

public class Puntuacion {
	
	private Agrupacion agrupacion;
	private String fase;
	private float puntos;
	public Agrupacion getAgrupacion() {
		return agrupacion;
	}
	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}
	public String getFase() {
		return fase;
	}
	public void setFase(String fase) {
		this.fase = fase;
	}
	public float getPuntos() {
		return puntos;
	}
	public void setPuntos(float puntos) {
		this.puntos = puntos;
	}

}

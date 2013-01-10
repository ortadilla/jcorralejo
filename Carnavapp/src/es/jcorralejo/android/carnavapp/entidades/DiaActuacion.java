package es.jcorralejo.android.carnavapp.entidades;

import java.util.List;

public class DiaActuacion {
	
	private String dia;
	private List<Agrupacion> agrupaciones;
	
	public DiaActuacion(String dia, List<Agrupacion> agrupaciones) {
		this.dia = dia;
		this.agrupaciones = agrupaciones;
	}
	
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}

	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}

	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}

}

package es.jcorralejo.android.carnavapp.entidades;

import java.util.Date;
import java.util.List;

public class DiaActuacion {
	
	private Date dia;
	private List<Agrupacion> agrupaciones;
	
	public DiaActuacion(Date dia, List<Agrupacion> agrupaciones) {
		this.dia = dia;
		this.agrupaciones = agrupaciones;
	}
	
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}

	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}

	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}

}

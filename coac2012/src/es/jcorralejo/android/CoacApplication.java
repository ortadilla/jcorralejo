package es.jcorralejo.android;

import java.util.Date;
import java.util.List;
import java.util.Map;

import es.jcorralejo.android.entidades.Agrupacion;

import android.app.Application;

public class CoacApplication extends Application {
	
	private List<Agrupacion> agrupaciones;
	private Map<Date, List<Agrupacion>> calendario;
	private Map<String,List<Agrupacion>> modalidades;
	
	
	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}
	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}
	public Map<Date, List<Agrupacion>> getCalendario() {
		return calendario;
	}
	public void setCalendario(Map<Date, List<Agrupacion>> calendario) {
		this.calendario = calendario;
	}
	public Map<String, List<Agrupacion>> getModalidades() {
		return modalidades;
	}
	public void setModalidades(Map<String, List<Agrupacion>> modalidades) {
		this.modalidades = modalidades;
	}

}

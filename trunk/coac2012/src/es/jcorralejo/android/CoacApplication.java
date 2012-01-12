package es.jcorralejo.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.jcorralejo.android.entidades.Agrupacion;

import android.app.Application;

public class CoacApplication extends Application {
	
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private Map<Date, List<Agrupacion>> calendario = new HashMap<Date, List<Agrupacion>>();
	private Map<String,List<Agrupacion>> modalidades = new HashMap<String, List<Agrupacion>>();
	
	public String getRssUrl(){
		return "http://jcorralejo.googlecode.com/svn/trunk/coac2012/coac2012.xml";
	}
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

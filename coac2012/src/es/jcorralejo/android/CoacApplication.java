package es.jcorralejo.android;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.utils.Constantes;

import android.app.Application;

public class CoacApplication extends Application {
	
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private Map<String, List<Agrupacion>> calendario = new HashMap<String, List<Agrupacion>>();
	private Map<String,List<Agrupacion>> modalidades = new HashMap<String, List<Agrupacion>>();
	private List<Integer> favoritas = new ArrayList<Integer>();
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		agrupaciones = new ArrayList<Agrupacion>();
		calendario = new HashMap<String, List<Agrupacion>>();
		modalidades = new HashMap<String, List<Agrupacion>>();
		modalidades.put(Constantes.MODALIDAD_CHIRIGOTA, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_COMPARSA, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CORO, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CUARTETO, new ArrayList<Agrupacion>());
	}
	
	public String getRssUrl(){
		return "http://jcorralejo.googlecode.com/svn/trunk/coac2012/coac2012.xml";
	}
	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}
	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}
	public Map<String, List<Agrupacion>> getCalendario() {
		return calendario;
	}
	public void setCalendario(Map<String, List<Agrupacion>> calendario) {
		this.calendario = calendario;
	}
	public Map<String, List<Agrupacion>> getModalidades() {
		return modalidades;
	}
	public void setModalidades(Map<String, List<Agrupacion>> modalidades) {
		this.modalidades = modalidades;
	}

	public List<Integer> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(List<Integer> favoritas) {
		this.favoritas = favoritas;
	}

}

package es.jcorralejo.android.carnavapp.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.jcorralejo.android.carnavapp.utils.Constantes;

public class Concurso {
	
	private List<Agrupacion> agrupaciones;
	public Map<String, List<Agrupacion>> getDias() {
		return dias;
	}

	public void setDias(Map<String, List<Agrupacion>> dias) {
		this.dias = dias;
	}
	private Map<String, List<Agrupacion>> modalidades;
	private List<Puntuacion> puntuaciones;
	private Map<String, List<DiaActuacion>> fases;
	private Map<String, List<Agrupacion>> dias;
	
	public Concurso() {
		agrupaciones = new ArrayList<Agrupacion>();
		
		modalidades = new HashMap<String, List<Agrupacion>>();
		modalidades.put(Constantes.MODALIDAD_CHIRIGOTA, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_COMPARSA, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CORO, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CUARTETO, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_INFANTIL, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_JUVENIL, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_ROMANCERO, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CALLEJERA, new ArrayList<Agrupacion>());
		
		puntuaciones = new ArrayList<Puntuacion>();
		fases = new HashMap<String, List<DiaActuacion>>();
		dias = new HashMap<String, List<Agrupacion>>();
	}
	
	public List<Agrupacion> getAgrupaciones() {
		return agrupaciones;
	}
	public void setAgrupaciones(List<Agrupacion> agrupaciones) {
		this.agrupaciones = agrupaciones;
	}
	public Map<String, List<Agrupacion>> getModalidades() {
		return modalidades;
	}
	public void setModalidades(Map<String, List<Agrupacion>> modalidades) {
		this.modalidades = modalidades;
	}
	public List<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}
	public void setPuntuaciones(List<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	public Map<String, List<DiaActuacion>> getFases() {
		return fases;
	}
	public void setFases(Map<String, List<DiaActuacion>> fases) {
		this.fases = fases;
	}

}

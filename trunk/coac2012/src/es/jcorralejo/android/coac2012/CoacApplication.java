package es.jcorralejo.android.coac2012;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Application;
import es.jcorralejo.android.coac2012.entidades.Agrupacion;
import es.jcorralejo.android.coac2012.entidades.Enlace;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class CoacApplication extends Application {
	
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private Map<String, List<Agrupacion>> calendario = new HashMap<String, List<Agrupacion>>();
	private Map<String,List<Agrupacion>> modalidades = new HashMap<String, List<Agrupacion>>();
	private List<Integer> favoritas = new ArrayList<Integer>();
	private Map<String, List<String>> concurso = new HashMap<String, List<String>>();
	private Map<String,List<Enlace>> enlaces = new HashMap<String, List<Enlace>>();
	
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
		modalidades.put(Constantes.MODALIDAD_INFANTIL, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_JUVENIL, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_ROMANCERO, new ArrayList<Agrupacion>());
		modalidades.put(Constantes.MODALIDAD_CALLEJERA, new ArrayList<Agrupacion>());
		
		List<String> preeliminares = new ArrayList<String>();
		preeliminares.add("21/01/2012");
		preeliminares.add("22/01/2012");
		preeliminares.add("23/01/2012");
		preeliminares.add("24/01/2012");
		preeliminares.add("25/01/2012");
		preeliminares.add("26/01/2012");
		preeliminares.add("27/01/2012");
		preeliminares.add("28/01/2012");
		preeliminares.add("29/01/2012");
		preeliminares.add("30/01/2012");
		preeliminares.add("31/01/2012");
		preeliminares.add("01/02/2012");
		preeliminares.add("02/02/2012");
		preeliminares.add("03/02/2012");
		preeliminares.add("04/02/2012");
		concurso.put(Constantes.FASE_PREELIMINAR, preeliminares);

		List<String> cuartos = new ArrayList<String>();
		cuartos.add("06/02/2012");
		cuartos.add("07/02/2012");
		cuartos.add("08/02/2012");
		cuartos.add("09/02/2012");
		cuartos.add("10/02/2012");
		cuartos.add("11/02/2012");
		concurso.put(Constantes.FASE_CUARTOS, cuartos);

		List<String> semis = new ArrayList<String>();
		semis.add("13/02/2012");
		semis.add("14/02/2012");
		semis.add("15/02/2012");
		concurso.put(Constantes.FASE_SEMIS, semis);

		List<String> final2 = new ArrayList<String>();
		final2.add("17/02/2012");
		concurso.put(Constantes.FASE_FINAL, final2);
		
	}
	
	public String getRssUrl(){
		return "http://jcorralejo.googlecode.com/svn/trunk/coac2012/coac2012.xml";
	}
	
	public String getTextoDia(String dia){
		String result = "";
		if(getConcurso().get(Constantes.FASE_PREELIMINAR).contains(dia))
			result = "Preeliminar "+ (getConcurso().get(Constantes.FASE_PREELIMINAR).indexOf(dia)+1);
		else if(getConcurso().get(Constantes.FASE_CUARTOS).contains(dia))
			result = "Cuarto de Final "+ (getConcurso().get(Constantes.FASE_CUARTOS).indexOf(dia)+1);
		else if(getConcurso().get(Constantes.FASE_SEMIS).contains(dia))
			result = "Semifinal "+ (getConcurso().get(Constantes.FASE_SEMIS).indexOf(dia)+1);
		else if(getConcurso().get(Constantes.FASE_FINAL).contains(dia))
			result = "Final";
		return result;
	}
	
	public boolean isPreeliminar(){
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy"); 
		String dia = sdf.format(new Date());
		return concurso.get(Constantes.FASE_PREELIMINAR).contains(dia);
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

	public Map<String, List<String>> getConcurso() {
		return concurso;
	}

	public void setConcurso(Map<String, List<String>> concurso) {
		this.concurso = concurso;
	}

	public Map<String, List<Enlace>> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(Map<String, List<Enlace>> enlaces) {
		this.enlaces = enlaces;
	}
	
}

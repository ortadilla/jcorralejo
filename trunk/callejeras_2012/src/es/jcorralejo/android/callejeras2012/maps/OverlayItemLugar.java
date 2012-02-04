package es.jcorralejo.android.callejeras2012.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;
import es.jcorralejo.android.callejeras2012.entidades.Lugar;

public class OverlayItemLugar extends OverlayItem{

	private Lugar lugar;
	private Agrupacion agrupacion;
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet, Lugar lugar, Agrupacion agrupacion) {
		super(point, title, snippet);
		this.lugar = lugar;
		this.agrupacion = agrupacion;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Agrupacion getAgrupacion() {
		return agrupacion;
	}

	public void setAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}


}

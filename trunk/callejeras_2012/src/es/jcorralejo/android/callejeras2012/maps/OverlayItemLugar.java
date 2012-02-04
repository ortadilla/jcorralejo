package es.jcorralejo.android.callejeras2012.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;

public class OverlayItemLugar extends OverlayItem{

	private long idLugar;
	private Agrupacion agrupacion;
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet, long idLugar, Agrupacion agrupacion) {
		super(point, title, snippet);
		this.idLugar = idLugar;
		this.agrupacion = agrupacion;
	}

	public long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}

	public Agrupacion getAgrupacion() {
		return agrupacion;
	}

	public void setIdAgrupacion(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
	}

}

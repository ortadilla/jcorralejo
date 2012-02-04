package es.jcorralejo.android.callejeras2012.maps;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class OverlayItemLugar extends OverlayItem{

	private long idLugar;
	private int idAgrupacion;
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	
	public OverlayItemLugar(GeoPoint point, String title, String snippet, long idLugar, int idAgrupacion) {
		super(point, title, snippet);
		this.idLugar = idLugar;
		this.idAgrupacion = idAgrupacion;
	}

	public long getIdLugar() {
		return idLugar;
	}

	public void setIdLugar(long idLugar) {
		this.idLugar = idLugar;
	}

	public int getIdAgrupacion() {
		return idAgrupacion;
	}

	public void setIdAgrupacion(int idAgrupacion) {
		this.idAgrupacion = idAgrupacion;
	}

}

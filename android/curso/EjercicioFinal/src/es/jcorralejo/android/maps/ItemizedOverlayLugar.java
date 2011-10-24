package es.jcorralejo.android.maps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;

public class ItemizedOverlayLugar extends ItemizedOverlay<OverlayItemLugar> {

	private ArrayList<OverlayItemLugar> mOverlays = new ArrayList<OverlayItemLugar>();
	private Context context;
	private OverlayItemLugar lugarPulsado = null;
	private float latitudPulsada, longitudPulsada;

	public ItemizedOverlayLugar(Context context,Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}
	
	public void add(double lat, double lon, String etiqueta, long idLugar) {
		int lt = (int) (lat * 1E6);
		int ln = (int) (lon * 1E6);
		GeoPoint punto = new GeoPoint(lt, ln);
		OverlayItemLugar item = new OverlayItemLugar(punto, etiqueta, null, idLugar);
		mOverlays.add(item);
		populate();
	}
	
	public void clear() {
		mOverlays.clear();
		populate();
	}
	
	@Override
	protected OverlayItemLugar createItem(int i) {
		return mOverlays.get(i);
	}
	
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		boolean result = super.onTap(p, mapView);
		latitudPulsada = (float) (p.getLatitudeE6() / 1E6);
		longitudPulsada = (float) (p.getLongitudeE6() / 1E6);
		return result;
	}
	
	@Override
	protected boolean onTap(int index) {
		boolean result = super.onTap(index);
		lugarPulsado = mOverlays.get(index);
		return result;
	}

	public OverlayItemLugar getLugarPulsado() {
		return lugarPulsado;
	}

	public void setLugarPulsado(OverlayItemLugar lugarPulsado) {
		this.lugarPulsado = lugarPulsado;
	}

	public float getLatitudPulsada() {
		return latitudPulsada;
	}

	public void setLatitudPulsada(float latitudPulsada) {
		this.latitudPulsada = latitudPulsada;
	}

	public float getLongitudPulsada() {
		return longitudPulsada;
	}

	public void setLongitudPulsada(float longitudPulsada) {
		this.longitudPulsada = longitudPulsada;
	}
	
}


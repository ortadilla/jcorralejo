package es.jcorralejo.android.maps;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import es.jcorralejo.android.utils.Constantes;

public class MiItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context context;
	int xDown, yDown;
	public boolean abrirOpciones = true;

	public MiItemizedOverlay(Context context,Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		this.context = context;
	}

	public void addLocalizacion(double lat, double lon, String etiqueta) {
		int lt = (int) (lat * 1E6);
		int ln = (int) (lon * 1E6);
		GeoPoint punto = new GeoPoint(lt, ln);
		OverlayItem item = new OverlayItem(punto, etiqueta, null);
		mOverlays.add(item);
		populate();
	}
	
	public void clear() {
		mOverlays.clear();
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}
	
	@Override
	public int size() {
		return mOverlays.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		String etiqueta = mOverlays.get(index).getTitle();
		Toast.makeText(context, etiqueta, Toast.LENGTH_LONG).show();
		abrirOpciones = false;
		return false;
	}
	
	/*
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		//Al pulsar guardamos las corrdenadas...
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			xDown=(int)event.getX();
			yDown=(int)event.getY();
        }
		//...y comprobamos al levantar el dedo si seguimos en el mismo punto.
		//En este caso, levantamos el popUp con las opciones sobre el mapa
		else if (event.getAction()==MotionEvent.ACTION_UP) {
            if ((int)event.getX()==xDown && (int)event.getY()==yDown) {
            	
            	boolean result = super.onTouchEvent(event, mapView);
            	if(abrirOpciones){
            		float[] coordenada = new float[2];
            		coordenada[0] = event.getX(); 
            		coordenada[1] = event.getY(); 
            		Bundle args = new Bundle();
            		args.putFloatArray(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
            		((Activity)context).showDialog(Constantes.DIALOG_OPCIONES_MAPA, args);
            		
            	}else
            		abrirOpciones = true;
            	
                return result;
            }
		}
		return super.onTouchEvent(event, mapView);
	}
	*/
	
}


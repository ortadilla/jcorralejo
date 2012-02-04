package es.jcorralejo.android.callejeras2012.maps;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import es.jcorralejo.android.callejeras2012.R;
import es.jcorralejo.android.callejeras2012.utils.Constantes;

public class MiLocationListener implements LocationListener {
	
	private Context context;
	private MapView mapa;
	/** Indica el lugar actual del GPS*/
	private ItemizedOverlayLugar puntoActual;
	
	public MiLocationListener(Context context, MapView mapa){
		this.context = context;
		this.mapa = mapa;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		mapa.invalidate();
        Drawable chincheta = mapa.getResources().getDrawable(R.drawable.ic_maps_indicator_current_position);
        List<Overlay> mapOverlays = mapa.getOverlays();
        //Eliminamos el punto anterior..
        mapOverlays.remove(puntoActual);
        //..y añadimos el nuevo
        puntoActual = new ItemizedOverlayLugar(chincheta, true);
        puntoActual.add(location.getLatitude(), location.getLongitude(), null, null, Constantes.NINGUN_LUGAR, null);
        mapOverlays.add(puntoActual);  
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		//Informamos al usuario y lo llevamos a las opciones de Ubicación...
		Toast.makeText(context, "GPS Desactivado. Para el correcto funcionamiento actívelo en la configuración de Ubicación", Toast.LENGTH_LONG).show();
		Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		context.startActivity(intent);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(context, "GPS Activado", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

	public ItemizedOverlayLugar getPuntoActual() {
		return puntoActual;
	}

	public void setPuntoActual(ItemizedOverlayLugar puntoActual) {
		this.puntoActual = puntoActual;
	}

}

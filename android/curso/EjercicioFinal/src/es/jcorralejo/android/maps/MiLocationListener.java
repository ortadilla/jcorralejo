package es.jcorralejo.android.maps;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import es.jcorralejo.android.R;
import es.jcorralejo.android.utils.Constantes;

public class MiLocationListener implements LocationListener {
	
	private Context context;
	private MapController mapController;
	private MapView mapa;
	private ItemizedOverlayLugar puntoActual;
	
	public MiLocationListener(Context context, MapController mapController, MapView mapa){
		this.context = context;
		this.mapController = mapController;
		this.mapa = mapa;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		mapa.invalidate();
        Drawable chincheta = mapa.getResources().getDrawable(R.drawable.ic_gps_actual);
        List<Overlay> mapOverlays = mapa.getOverlays();
        mapOverlays.remove(puntoActual);
        puntoActual = new ItemizedOverlayLugar(context, chincheta);
        puntoActual.add(location.getLatitude(), location.getLongitude(), null, Constantes.NINGUN_LUGAR);
        mapOverlays.add(puntoActual);  
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(context, context.getString(R.string.gps_desactivado), Toast.LENGTH_LONG).show();
		Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		context.startActivity(intent);
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(context, context.getString(R.string.gps_activado), Toast.LENGTH_LONG).show();
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {}

}

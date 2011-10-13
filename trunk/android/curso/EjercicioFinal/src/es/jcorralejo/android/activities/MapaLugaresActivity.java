package es.jcorralejo.android.activities;

import java.util.List;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.maps.MiItemizedOverlay;
import es.jcorralejo.android.maps.MiLocationListener;
import es.jcorralejo.android.utils.Constantes;

public class MapaLugaresActivity extends MapActivity {

	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private MiItemizedOverlay itemizedOverlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);

		mapa = (MapView) findViewById(R.id.mapview);
		mapa.displayZoomControls(true);
		mapa.setBuiltInZoomControls(true);
		mapa.setSatellite(true); // Activamos la vista satelite
		mapController = mapa.getController();
		mapController.setZoom(14); // Zoom x14

		//Añadimos el manejador del GPS
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MiLocationListener mlistener = new MiLocationListener(this, mapController);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlistener);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			// Marcamos el/los punto/s en el mapa
			Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
			itemizedOverlay = new MiItemizedOverlay(this, drawable);
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			Cursor cursor = managedQuery(uri, columnas, null, null, null);
			cursor.setNotificationUri(getContentResolver(), uri);
			startManagingCursor(cursor);

			// Si indicamos un lugar, sólo debemos mostrar ese
			Long idLugar = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			if(idLugar!=null && Constantes.TODOS_LUGARES!=idLugar)
				uri = ContentUris.withAppendedId(uri, idLugar);

			// Añadimos todos lo puntos
			while(cursor.moveToNext()) {
				String nombre = cursor.getString(1);
				float latitud = cursor.getFloat(4);
				float longitud = cursor.getFloat(5);

				itemizedOverlay.addLocalizacion(latitud, longitud, nombre);
			}
			
			// Centramos el mapa TODO
			Double latitud = 37.40*1E6;
			Double longitud = -5.99*1E6;
			GeoPoint loc = new GeoPoint(latitud.intValue(), longitud.intValue());
	        mapController.animateTo(loc);
	        int zoomActual = mapa.getZoomLevel();
	        for(int i=zoomActual; i<10; i++)
	            mapController.zoomIn();
			
			mapOverlays = mapa.getOverlays();
			mapOverlays.clear();
			mapOverlays.add(itemizedOverlay);
		}
		
	}


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}

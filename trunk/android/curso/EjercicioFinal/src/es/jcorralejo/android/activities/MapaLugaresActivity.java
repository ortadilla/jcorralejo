package es.jcorralejo.android.activities;

import java.util.List;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.maps.MiItemizedOverlay;
import es.jcorralejo.android.maps.MiLocationListener;
import es.jcorralejo.android.utils.Constantes;

public class MapaLugaresActivity extends MapActivity{

	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private MiItemizedOverlay itemizedOverlay;
	private LocationManager lm;
	boolean acercar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);

		mapa = (MapView) findViewById(R.id.mapview);
		mapa.displayZoomControls(true);
		mapa.setBuiltInZoomControls(true);
		mapa.setSatellite(true);
		mapController = mapa.getController();
		mapController.setZoom(6);

		//Añadimos el manejador del GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MiLocationListener mlistener = new MiLocationListener(this, mapController);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlistener);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		//Si el GPS no está habilitado
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) 
			Toast.makeText(this, R.string.msg_notificacion_no_gps, Toast.LENGTH_LONG).show();
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
			itemizedOverlay = new MiItemizedOverlay(this, drawable);
			
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			String where = null;
			
			// Si indicamos un lugar, sólo debemos mostrar ese
			Long idLugar = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			if(idLugar!=null && Constantes.TODOS_LUGARES!=idLugar){
				uri = ContentUris.withAppendedId(uri, idLugar);
				where = Lugar._ID+" = "+idLugar;
			}
			
			Cursor cursor = managedQuery(uri, columnas, where, null, null);
			cursor.setNotificationUri(getContentResolver(), uri);
			startManagingCursor(cursor);

			// Añadimos el/los punto/s en el mapa
			while(cursor.moveToNext()) {
				String nombre = cursor.getString(1);
				float latitud = cursor.getFloat(4);
				float longitud = cursor.getFloat(5);

				itemizedOverlay.addLocalizacion(latitud, longitud, nombre);
			}

			//Sólo aceramos el zoom cuando se ha enviado un único lugar
	        acercar = idLugar!=null && Constantes.TODOS_LUGARES!=idLugar;
	        
			// Animamos el mapa de punto a punto
			for (int x = 0; x < itemizedOverlay.size(); x++) {
				OverlayItem hito = itemizedOverlay.getItem(x);
				mapController.animateTo(hito.getPoint());
			}
			
			mapOverlays = mapa.getOverlays();
			mapOverlays.clear();
			mapOverlays.add(itemizedOverlay);
			
		
		// Si no se indica lugar es que estamos añadiendo uno, por lo que comprobamos si está activo 
		// el GPS y centramos el mapa en las coordenadas actuales del dispositivo 
		}else{
			//Comprobamos si tenemos localización por GPS...
			Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			//...y si no, comprobamos si tenemos localización por triangulación de antenas
			if(loc==null)
				loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(loc!=null){
				GeoPoint geoPoint = new GeoPoint((int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6));
				mapController.animateTo(geoPoint);
			}
		}
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		//Necesitamos hacer zoom en este método, ya que en onStart aun no se ha generado el imageView
		if(acercar){
			for(int i=mapa.getZoomLevel(); i<Constantes.ZOOM_MAX_MAPA; i++)
				mapController.zoomIn();
		}
	}

}

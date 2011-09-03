package activities;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import es.jcorralejo.android.R;

public class MapaLugaresActivity extends MapActivity {

	private MapView mapa;
	private MapController mapController;
//	private List<Overlay> mapOverlays;
	//	private MiItemizedOverlay itemizedoverlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);

		mapa = (MapView) findViewById(R.id.mapview);
		mapa.displayZoomControls(true);
		mapa.setBuiltInZoomControls(true);
		mapController = mapa.getController();
		mapController.setZoom(14); // Zoom x14
		mapa.setSatellite(true); // Activamos la vista satelite

		/*
		// Marcamos unos puntos en el mapa
		Drawable drawable = this.getResources().getDrawable(R.drawable.icon);
		itemizedoverlay = new MiItemizedOverlay(this, drawable);
		itemizedoverlay.addLocalizacion(41.669770, -0.812602, "Punto 1");
		itemizedoverlay.addLocalizacion(41.666597, -0.907145, "Punto 2");
		itemizedoverlay.addLocalizacion(41.658657, -0.886501,"Punto 3");

		mapOverlays = mapa.getOverlays();
		mapOverlays.clear();
		mapOverlays.add(itemizedoverlay);

		//Añadimos el manejador del GPS
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MiLocationListener mlistener = new MiLocationListener(this, mapController);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlistener);
		*/
	}


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}

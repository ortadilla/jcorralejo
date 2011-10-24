package es.jcorralejo.android.activities;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
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
import es.jcorralejo.android.maps.ItemizedOverlayLugar;
import es.jcorralejo.android.maps.MiLocationListener;
import es.jcorralejo.android.utils.Constantes;

public class MapaLugaresActivity extends MapActivity {

	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private ItemizedOverlayLugar itemizedOverlay;
	private LocationManager lm;
	
	boolean detallesLugar;
	int xDown, yDown;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		mapa = (MapView) findViewById(R.id.mapview);
		mapa.displayZoomControls(true);
		mapa.setBuiltInZoomControls(true);
		mapa.setSatellite(true);
		mapController = mapa.getController();
		mapController.setZoom(14);
		
		//Añadimos el manejador del GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		MiLocationListener mlistener = new MiLocationListener(this, mapController);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlistener);
	}
	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean result = super.dispatchTouchEvent(ev);
		
		if(!detallesLugar){
			//Al pulsar guardamos las corrdenadas...
			if (ev.getAction()==MotionEvent.ACTION_DOWN) {
				xDown=(int)ev.getX();
				yDown=(int)ev.getY();
	        }
			//...y comprobamos al levantar el dedo si seguimos en el mismo punto.
			else if (ev.getAction()==MotionEvent.ACTION_UP) {
	            if ((int)ev.getX()==xDown && (int)ev.getY()==yDown) {
	            	//Si no pulsamos un lugar ya definido levantamos el popUp con las opciones sobre el mapa
	            	if(itemizedOverlay.getLugarPulsado()==null){
	            		float[] coordenada = new float[2];
	            		coordenada[0] = ev.getX(); 
	            		coordenada[1] = ev.getY(); 
	            		Bundle args = new Bundle();
	            		args.putFloatArray(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
	            		showDialog(Constantes.DIALOG_OPCIONES_MAPA, args);
	            		return true;
	            	// Si pulsamos un lugar mostramos sus detalles
	            	}else{
	            		Intent i = new Intent();
	            		i.setClass(getApplicationContext(), LugarAcitivity.class);
	            		i.putExtra(Constantes.PARAMETRO_ID_LUGAR, itemizedOverlay.getLugarPulsado().getIdLugar());
	            		startActivity(i);
	            		itemizedOverlay.setLugarPulsado(null);
	            	}
	            }
			}
		}
		return result;
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
			// Abrimos el popUp para mostrar las opciones sobre los puntos del mapa 
			case Constantes.DIALOG_OPCIONES_MAPA:
				if(args!=null){
					final float[] coordenada = args.getFloatArray(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO);

					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					builder.setTitle(R.string.opciones);
					Resources resources = getResources();
					final CharSequence[] items = {resources.getString(R.string.agregar), resources.getString(R.string.cancelar)};
					builder.setItems(items, new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							//Si pulsamos "aceptar"...
							if(item==0){
								Intent i = new Intent();
								i.setClass(getApplicationContext(), EditarLugarActivity.class);
								i.putExtra(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
								startActivity(i);
							}
							
							//En cualquier caso eliminamos el dialog para que, si pulsamos de nuevo en otro punto,
							//se actualicen los argumentos que se pasan por parámetros
							removeDialog(Constantes.DIALOG_OPCIONES_MAPA);
						}
					});
					return builder.create();
				}
			default:
				return null;
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		// Comprobamos si está activo el GPS y centramos el mapa en las coordenadas actuales del dispositivo
		moverMapaAPosicionActual();
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			Drawable drawable = this.getResources().getDrawable(R.drawable.chincheta);
			itemizedOverlay = new ItemizedOverlayLugar(this, drawable);
			
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			String where = null;
			
			// Si indicamos un lugar, sólo debemos mostrar ese
			Long idLugar = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			if(idLugar!=null && Constantes.TODOS_LUGARES!=idLugar){
				uri = ContentUris.withAppendedId(uri, idLugar);
				where = Lugar._ID+" = "+idLugar;
				
				detallesLugar = true;
			}
			
			Cursor cursor = managedQuery(uri, columnas, where, null, null);
			cursor.setNotificationUri(getContentResolver(), uri);
			startManagingCursor(cursor);

			// Añadimos el/los punto/s en el mapa
			while(cursor.moveToNext()) {
				long id = cursor.getLong(0);
				String nombre = cursor.getString(1);
				float latitud = cursor.getFloat(4);
				float longitud = cursor.getFloat(5);

				itemizedOverlay.add(latitud, longitud, nombre, id);
			}

			if(itemizedOverlay.size()>0){
				mapOverlays = mapa.getOverlays();
				mapOverlays.clear();
				mapOverlays.add(itemizedOverlay);
				
				// Si sólo hay un punto animamos el mapa
				OverlayItem hito = itemizedOverlay.getItem(0);
				mapController.animateTo(hito.getPoint());

			}
		}
	}
	
	private void moverMapaAPosicionActual(){
		//Comprobamos si tenemos localización por GPS...
		Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//...y si no, comprobamos si tenemos localización por triangulación de antenas
		if(loc==null)
			loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(loc!=null){
			GeoPoint geoPoint = new GeoPoint((int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6));
			mapController.animateTo(geoPoint);
		}
		
		//Además, avisamos si el GPS no está habilitado
		if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) 
			Toast.makeText(this, R.string.msg_notificacion_no_gps, Toast.LENGTH_LONG).show();
	}
	
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		//Sólo aceramos el zoom cuando se ha enviado un único lugar
		//Necesitamos hacer zoom en este método, ya que en onStart aun no se ha generado el imageView
		if(detallesLugar){
			for(int i=mapa.getZoomLevel(); i<mapa.getMaxZoomLevel(); i++)
				mapController.zoomIn();
		}
	}

}

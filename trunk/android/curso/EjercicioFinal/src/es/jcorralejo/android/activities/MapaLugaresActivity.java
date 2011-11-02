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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.android.maps.Projection;

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
	private MiLocationListener mListener;
	
	boolean detallesLugar;
	boolean editarCoordenadas;
	boolean hacerZoom = false;
	int xDown, yDown;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		setTitle(R.string.nombre_lugares);
		
		mapa = (MapView) findViewById(R.id.mapview);
//		mapa.displayZoomControls(true);
//		mapa.setBuiltInZoomControls(true);
		mapa.setSatellite(true);
		mapController = mapa.getController();
		mapController.setZoom(14);
		mapOverlays = mapa.getOverlays();
		
		//Añadimos el manejador del GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mListener = new MiLocationListener(this, mapController, mapa);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 10, mListener);
		
		// Comprobamos si está activo el GPS y centramos el mapa en las coordenadas actuales del dispositivo
		moverMapaAPosicionActual();
	}
	
	
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean result = super.dispatchTouchEvent(ev);
		
		if(!detallesLugar){
			//Al pulsar guardamos las corrdenadas...
			int x = (int)ev.getX();
			int y = (int)ev.getY();
			if (ev.getAction()==MotionEvent.ACTION_DOWN) {
				xDown=x;
				yDown=y;
	        }
			//...y comprobamos al levantar el dedo si seguimos en el mismo punto.
			else if (ev.getAction()==MotionEvent.ACTION_UP) {
	            if (mismoLugar(x, y)) {
	            	//Si no pulsamos un lugar ya definido levantamos el popUp con las opciones sobre el mapa
	            	if(itemizedOverlay.getLugarPulsado()==null){
	            		Projection proj = mapa.getProjection();
		    			GeoPoint punto = proj.fromPixels(x, y); 
	            		float[] coordenada = new float[2];
	            		coordenada[0] = (float) (punto.getLatitudeE6() / 1E6);
	            		coordenada[1] = (float)(punto.getLongitudeE6() / 1E6); 

	            		Bundle args = new Bundle();
	            		args.putFloatArray(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
	            		
	            		//Si estamos editando las coordenadas de un lugar volvemos con el nuevo punto
	            		if(editarCoordenadas){
	            			Intent i = new Intent();
							i.setClass(getApplicationContext(), EditarLugarActivity.class);
							i.putExtra(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
							i.putExtra(Constantes.EDITAR_COORDENADA_LUGAR, editarCoordenadas);
							setResult(RESULT_OK, i);
							finish();
						//Si no, mostramos el popUp con todoas las opciones
	            		}else
	            			showDialog(Constantes.DIALOG_OPCIONES_MAPA, args);
	            		
	            		return true;
	            	// Si pulsamos un lugar mostramos sus detalles
	            	}else{
	            		Intent i = new Intent();
	            		i.setClass(getApplicationContext(), LugarActivity.class);
	            		i.putExtra(Constantes.PARAMETRO_ID_LUGAR, itemizedOverlay.getLugarPulsado().getIdLugar());
	            		startActivity(i);
	            		itemizedOverlay.setLugarPulsado(null);
	            	}
	            }
			}
		}
		return result;
	}
	
	private boolean mismoLugar(int x, int y){
		return (x>=xDown-5 && x<=xDown+5) && (y>=yDown-5 && y<=yDown+5); 
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
		
		final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
		Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
		String where = null;
		
		// Si indicamos un lugar, sólo debemos mostrar ese
		Bundle extras = getIntent().getExtras();
		Long idLugar = extras!=null ? extras.getLong(Constantes.PARAMETRO_ID_LUGAR) : null;
		if(idLugar!=null && Constantes.TODOS_LUGARES!=idLugar){
			uri = ContentUris.withAppendedId(uri, idLugar);
			where = Lugar._ID+" = "+idLugar;
			
			editarCoordenadas = extras!=null ? extras.getBoolean(Constantes.EDITAR_COORDENADA_LUGAR) : false;
			if(!editarCoordenadas)
				detallesLugar = true;
		}
		
		Cursor cursor = managedQuery(uri, columnas, where, null, null);
		cursor.setNotificationUri(getContentResolver(), uri);
		startManagingCursor(cursor);
		
		// Añadimos el/los punto/s en el mapa, aunque antes eliminamos todas las capas del mapa para limpiarlo
		Drawable chincheta = this.getResources().getDrawable(R.drawable.chincheta);
		itemizedOverlay = new ItemizedOverlayLugar(this, chincheta);
		mapOverlays.remove(itemizedOverlay);
		
		while(cursor.moveToNext() && !editarCoordenadas) {
			long id = cursor.getLong(0);
			String nombre = cursor.getString(1);
			float latitud = cursor.getFloat(4);
			float longitud = cursor.getFloat(5);

			itemizedOverlay.add(latitud, longitud, nombre, id);
		}
		
		if(itemizedOverlay.size()>0){
			mapOverlays.add(itemizedOverlay);

			// Si sólo hay un punto movemos el mapa hacia él
			if(detallesLugar){
				OverlayItem hito = itemizedOverlay.getItem(0);
				mapController.animateTo(hito.getPoint());
				hacerZoom = true;
			}
		}
		
	}
	
	private GeoPoint moverMapaAPosicionActual(){
		GeoPoint geoPoint = null;
		//Comprobamos si tenemos localización por GPS...
		Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//...y si no, comprobamos si tenemos localización por triangulación de antenas
		if(loc==null)
			loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(loc!=null){
			geoPoint = new GeoPoint((int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6));
			mapController.animateTo(geoPoint);
			
			//Si aun no tenemos posición actual guardada, la guardamos ahora
			if(mListener!=null && mListener.getPuntoActual()==null)
				mListener.onLocationChanged(loc);
		}else
			Toast.makeText(getBaseContext(), R.string.error_aniadir_posicion_actual, Toast.LENGTH_LONG).show();
		
		return geoPoint;
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
		if(detallesLugar && hasFocus && hacerZoom){
			boolean seguir = true;
			for(int i=mapa.getZoomLevel(); i<mapa.getMaxZoomLevel()-1; i++){
				seguir = mapController.zoomIn();
				if(!seguir){
					mapController.zoomOut();
					break;
				}
			}
			hacerZoom = false;
		}
	}
	
	/**
	 * "Inflamos" las opciones de menú de la pantalla principal 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_mapa, menu);
		return true;
	}
	
	/**
	 * Definimos las acciones correspondientes con cada opción de menú
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Al pulsar sobre "Ir posición actual" navegamos a la posición actual marcada por el GPS
			case R.id.mapaIrPosicionActual:
				moverMapaAPosicionActual();
				return true;
			// Al pulsar sobre "Añadir posición actual" navegamos a la creación de un nuevo lugar con las coordenadas actuales
			case R.id.mapaAiniadirPosicionActual:
				if(detallesLugar || editarCoordenadas){
					Toast.makeText(getBaseContext(), detallesLugar 
												   ? R.string.control_agregar_lugar_actual_detalles 
												   : R.string.control_agregar_lugar_actual_coordenadas, Toast.LENGTH_LONG).show();
				}else{
					GeoPoint punto = moverMapaAPosicionActual();
					if(punto!=null){
						float[] coordenada = new float[2];
						coordenada[0] = (float) (punto.getLatitudeE6() / 1E6);
						coordenada[1] = (float)(punto.getLongitudeE6() / 1E6); 
						Intent i = new Intent();
						i.setClass(getApplicationContext(), EditarLugarActivity.class);
						i.putExtra(Constantes.PARAMETRO_PUNTO_MAPA_SELECCIONADO, coordenada);
						startActivity(i);
					}
				}
				return true;
			// Al pulsar sobre "Modo Satélite" lo activamos
			case R.id.mapaVistaSatelite:
				mapa.setSatellite(true);
				return true;
			// Al pulsar sobre "Modo mapa" lo activamos
			case R.id.mapaVistaMapa:
				mapa.setSatellite(false);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


}

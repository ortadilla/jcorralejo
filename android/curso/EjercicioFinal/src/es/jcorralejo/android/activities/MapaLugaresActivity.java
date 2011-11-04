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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

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
import es.jcorralejo.android.maps.OverlayItemLugar;
import es.jcorralejo.android.utils.Constantes;

public class MapaLugaresActivity extends MapActivity {

	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private ItemizedOverlayLugar itemizedOverlay;
	private LocationManager lm;
	private MiLocationListener mListener;
	
	// Views para manejar el popUp con los datos de los Lugares pulsados
	private FrameLayout popUp;
	private LinearLayout contenidoPopUp;
	private LinearLayout textoPopUp;
	private TextView nombreLugar;
	private TextView descripcionLugar;
	private ImageView cerrarPopUp;
	
	boolean detallesLugar;
	boolean popUpPulsado;
	boolean editarCoordenadas;
	boolean hacerZoom;
	int xDown, yDown;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		setTitle(R.string.nombre_lugares);
		
		mapa = (MapView) findViewById(R.id.mapview);
		mapa.setSatellite(true);
		mapController = mapa.getController();
		mapController.setZoom(14);
		mapOverlays = mapa.getOverlays();
		
		//A�adimos el manejador del GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mListener = new MiLocationListener(this, mapa);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, mListener);
		
		// Comprobamos si est� activo el GPS y centramos el mapa en las coordenadas actuales del dispositivo
		moverMapaAPosicionActual();
		
		//"Inflamos" el contenido del popUp y obtenemos sus elementos
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contenidoPopUp = (LinearLayout) inflater.inflate(R.layout.balloon_overlay, contenidoPopUp);
		contenidoPopUp.setVisibility(LinearLayout.VISIBLE);
		textoPopUp = (LinearLayout) contenidoPopUp.findViewById(R.id.balloon_inner_layout);
		textoPopUp.setOnTouchListener(createTextoPopUpListener());
		nombreLugar = (TextView) contenidoPopUp.findViewById(R.id.balloon_item_title);
		descripcionLugar = (TextView) contenidoPopUp.findViewById(R.id.balloon_item_snippet);
		cerrarPopUp = (ImageView) contenidoPopUp.findViewById(R.id.close_img_button);
		cerrarPopUp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				contenidoPopUp.setVisibility(LinearLayout.GONE);
				itemizedOverlay.setLugarPulsado(null);
			}
		});

		//Creamos un FrameLayout que contenga el popUp
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;
		popUp = new FrameLayout(this);
		popUp.setPadding(0, 0, 10, 0);
		popUp.addView(contenidoPopUp, params);
		mapa.addView(popUp);
	}
	
	private OnTouchListener createTextoPopUpListener() {
		return new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				View l =  ((View) v.getParent()).findViewById(R.id.balloon_main_layout);
				Drawable d = l.getBackground();
				//Manejamos las pulsaciones para modificar el estado del fondo, y as� ver dos im�genes distintas
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int[] states = {android.R.attr.state_pressed};
					if (d.setState(states)) {
						d.invalidateSelf();
					}
					return true;
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					int newStates[] = {};
					if (d.setState(newStates)) {
						d.invalidateSelf();
					}
					
            		Intent i = new Intent();
            		i.setClass(getApplicationContext(), LugarActivity.class);
            		i.putExtra(Constantes.PARAMETRO_ID_LUGAR, itemizedOverlay.getLugarPulsado().getIdLugar());
            		startActivity(i);
            		itemizedOverlay.setLugarPulsado(null);
            		contenidoPopUp.setVisibility(LinearLayout.GONE);
            		popUpPulsado = true;

					return true;
				} else {
					return false;
				}
			}
		};
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
	            		if(!popUpPulsado){
	            			levantarPopUpAgregar(x, y);
	            			return true;
	            		}else
	            			popUpPulsado = false;
	            	// Si pulsamos un lugar mostramos el marco con sus detalles
	            	}else{
	            		crearCuadroResumen(itemizedOverlay.getLugarPulsado());
	        			mapController.animateTo(itemizedOverlay.getLugarPulsado().getPoint());
	            	}
	            }
			}
		}
		return result;
	}

	/**
	 * Levanta el popUp con las opciones para agregar un nuevo lugar, o devuelve las coordenadas
	 * en caso de estar editando la posici�n de un lugar ya existente
	 * @param x Posici�n X pulsada
	 * @param y Posici�n Y pulsada
	 */
	private void levantarPopUpAgregar(int x, int y){
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
	}
	
	/**
	 * Crear el cuadro resumen con el lugar indicado
	 * @param lugarPulsado
	 */
	private void crearCuadroResumen(OverlayItemLugar lugarPulsado){
		popUp.setVisibility(View.GONE);
		GeoPoint point = lugarPulsado.getPoint();
		MapView.LayoutParams params = new MapView.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, point, MapView.LayoutParams.BOTTOM_CENTER);
		params.mode = MapView.LayoutParams.MODE_MAP;
		popUp.setLayoutParams(params);
		popUp.setVisibility(View.VISIBLE);

		rellenarDatosPopUp(lugarPulsado);
	}
	
	/**
	 * Rellena los datos a mostrar en el cuadro resumen
	 * @param lugarPulsado
	 */
	private void rellenarDatosPopUp(OverlayItemLugar lugarPulsado){
		if (lugarPulsado != null){
			contenidoPopUp.setVisibility(LinearLayout.VISIBLE);
			if (lugarPulsado.getTitle() != null) {
				nombreLugar.setVisibility(LinearLayout.VISIBLE);
				nombreLugar.setText(lugarPulsado.getTitle());
			} else {
				nombreLugar.setVisibility(TextView.GONE);
			}
			if (lugarPulsado.getSnippet() != null) {
				descripcionLugar.setVisibility(TextView.VISIBLE);
				descripcionLugar.setText(lugarPulsado.getSnippet());
			} else {
				descripcionLugar.setVisibility(TextView.GONE);
			}
		}
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
							//se actualicen los argumentos que se pasan por par�metros
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
		
		// Si indicamos un lugar, s�lo debemos mostrar ese
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
		
		// A�adimos el/los punto/s en el mapa, aunque antes eliminamos todas las capas del mapa para limpiarlo
		Drawable chincheta = this.getResources().getDrawable(R.drawable.chincheta);
		itemizedOverlay = new ItemizedOverlayLugar(this, chincheta, false);
		mapOverlays.remove(itemizedOverlay);
		
		while(cursor.moveToNext() && !editarCoordenadas) {
			long id = cursor.getLong(0);
			String nombre = cursor.getString(1);
			String descripcion = cursor.getString(2);
			float latitud = cursor.getFloat(4);
			float longitud = cursor.getFloat(5);

			itemizedOverlay.add(latitud, longitud, nombre, getResumenDescripcionLugar(descripcion), id);
		}
		
		if(itemizedOverlay.size()>0){
			mapOverlays.add(itemizedOverlay);

			// Si s�lo hay un punto movemos el mapa hacia �l
			if(detallesLugar){
				OverlayItem hito = itemizedOverlay.getItem(0);
				mapController.animateTo(hito.getPoint());
				hacerZoom = true;
			}
		}
		
	}
	
	private String getResumenDescripcionLugar(String descripcion){
		String resumen = "";
		if(descripcion!=null){
			if(descripcion.length()>Constantes.NUM_CARACTERES_RESUMEN_DESCRIPCION)
				resumen = descripcion.substring(0, Constantes.NUM_CARACTERES_RESUMEN_DESCRIPCION)+"...";
			else
				resumen = descripcion;
		}
		return resumen;
	}
	
	private GeoPoint moverMapaAPosicionActual(){
		GeoPoint geoPoint = null;
		//Comprobamos si tenemos localizaci�n por GPS...
		Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//...y si no, comprobamos si tenemos localizaci�n por triangulaci�n de antenas
		if(loc==null)
			loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(loc!=null){
			geoPoint = new GeoPoint((int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6));
			mapController.animateTo(geoPoint);
			
			//Si aun no tenemos posici�n actual guardada, la guardamos ahora
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
		//S�lo aceramos el zoom cuando se ha enviado un �nico lugar
		//Necesitamos hacer zoom en este m�todo, ya que en onStart aun no se ha generado el imageView
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
	 * "Inflamos" las opciones de men� de la pantalla principal 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_mapa, menu);
		return true;
	}
	
	/**
	 * Definimos las acciones correspondientes con cada opci�n de men�
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Al pulsar sobre "Ir posici�n actual" navegamos a la posici�n actual marcada por el GPS
			case R.id.mapaIrPosicionActual:
				moverMapaAPosicionActual();
				return true;
			// Al pulsar sobre "A�adir posici�n actual" navegamos a la creaci�n de un nuevo lugar con las coordenadas actuales
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
			// Al pulsar sobre "Modo Sat�lite" lo activamos
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

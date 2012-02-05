package es.jcorralejo.android.callejeras2012.activities;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
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
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import es.jcorralejo.android.callejeras2012.CallejerasApplication;
import es.jcorralejo.android.callejeras2012.R;
import es.jcorralejo.android.callejeras2012.entidades.Lugar;
import es.jcorralejo.android.callejeras2012.maps.ItemizedOverlayLugar;
import es.jcorralejo.android.callejeras2012.maps.MiLocationListener;
import es.jcorralejo.android.callejeras2012.maps.OverlayItemLugar;
import es.jcorralejo.android.callejeras2012.utils.Constantes;


public class MapaActivity extends MapActivity {
	
	CallejerasApplication app;
	List<Lugar> lugares = new ArrayList<Lugar>();

	private MapView mapa;
	private MapController mapController;
	private List<Overlay> mapOverlays;
	private ItemizedOverlayLugar itemizedOverlayInteres;
	private ItemizedOverlayLugar itemizedOverlayAgrupacion;
	private LocationManager lm;
	private MiLocationListener mListener;
	
	// Views para manejar el popUp con los datos de los Lugares pulsados
	private FrameLayout popUp;
	private LinearLayout contenidoPopUp;
	private LinearLayout textoPopUp;
	private TextView nombreLugar;
	private TextView descripcionLugar;
	private ImageView cerrarPopUp;
	
	/** Indica si estamos viendo la posición de un luigar concreto */
	boolean detallesLugar;
	/** Indica si se ha pulsado en el popUp que se levanta al seleccionar un lugar */
	boolean popUpPulsado;
	/** Indica se debe hacer zoom al máximo */
	boolean hacerZoom;
	/** Indica la posición de la pantalla pulsada por el usuario */
	int xDown, yDown;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapa);
		
		mapa = (MapView) findViewById(R.id.mapview);
		mapa.setSatellite(true);
		mapController = mapa.getController();
		mapController.setZoom(16);
		mapOverlays = mapa.getOverlays();
		
		//Añadimos el manejador del GPS
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mListener = new MiLocationListener(this, mapa);
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 3, mListener);
		
		// Comprobamos si está activo el GPS y centramos el mapa en las coordenadas actuales del dispositivo
		moverMapaAPosicion(false);
		
		//"Inflamos" el contenido del popUp y obtenemos sus elementos
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		contenidoPopUp = (LinearLayout) inflater.inflate(R.layout.pop_up_lugar, contenidoPopUp);
		contenidoPopUp.setVisibility(LinearLayout.VISIBLE);
		textoPopUp = (LinearLayout) contenidoPopUp.findViewById(R.id.pop_up_lugar_inner_layout);
		textoPopUp.setOnTouchListener(crearTextoPopUpListener());
		nombreLugar = (TextView) contenidoPopUp.findViewById(R.id.pop_up_lugar_titulo);
		descripcionLugar = (TextView) contenidoPopUp.findViewById(R.id.pop_up_lugar_desc);
		cerrarPopUp = (ImageView) contenidoPopUp.findViewById(R.id.close_img_button);
		cerrarPopUp.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				contenidoPopUp.setVisibility(LinearLayout.GONE);
				itemizedOverlayInteres.setLugarPulsado(null);
				itemizedOverlayAgrupacion.setLugarPulsado(null);
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
	
	private OnTouchListener crearTextoPopUpListener() {
		return new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				View l =  ((View) v.getParent()).findViewById(R.id.pop_up_lugar_layout);
				Drawable d = l.getBackground();
				//Manejamos las pulsaciones para modificar el estado del fondo, y así ver dos imágenes distintas
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
					
					// Navegamos a ver los detalles de la agrupación
					if(itemizedOverlayInteres.getLugarPulsado()!=null && itemizedOverlayInteres.getLugarPulsado().getLugar()!=null){
						Intent i = new Intent();
						i.setClass(getApplicationContext(), LugarActivity.class);
						i.putExtra(Constantes.PARAMETRO_LUGAR, itemizedOverlayInteres.getLugarPulsado().getLugar());
						startActivity(i);
						itemizedOverlayInteres.setLugarPulsado(null);
						contenidoPopUp.setVisibility(LinearLayout.GONE);
						popUpPulsado = true;
					}
					
					else if (itemizedOverlayAgrupacion.getLugarPulsado()!=null && itemizedOverlayAgrupacion.getLugarPulsado().getAgrupacion()!=null){
						Intent i = new Intent();
						i.setClass(getApplicationContext(), AgrupacionActivity.class);
						i.putExtra(Constantes.PARAMETRO_AGRUPACION, itemizedOverlayAgrupacion.getLugarPulsado().getAgrupacion());
						startActivity(i);
						itemizedOverlayAgrupacion.setLugarPulsado(null);
						contenidoPopUp.setVisibility(LinearLayout.GONE);
						popUpPulsado = true;
					}

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
	            	// Si pulsamos un lugar mostramos el marco con sus detalles
	            	if(itemizedOverlayInteres.getLugarPulsado()!=null){
	            		crearCuadroResumen(itemizedOverlayInteres.getLugarPulsado());
	        			mapController.animateTo(itemizedOverlayInteres.getLugarPulsado().getPoint());
	            	}
	            	
	            	else if (itemizedOverlayAgrupacion.getLugarPulsado()!=null){
	            		crearCuadroResumen(itemizedOverlayAgrupacion.getLugarPulsado());
	            		mapController.animateTo(itemizedOverlayAgrupacion.getLugarPulsado().getPoint());
	            	}
	            }
			}
		}
		return result;
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
	
	/**
	 * Comprueba si la posición indicada es la misma que la guardada
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean mismoLugar(int x, int y){
		return (x>=xDown-5 && x<=xDown+5) && (y>=yDown-5 && y<=yDown+5); 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onStart() {
		super.onStart();
		
		// Añadimos el/los punto/s en el mapa, aunque antes eliminamos todas las capas del mapa para limpiarlo
		Drawable chincheta = this.getResources().getDrawable(R.drawable.chincheta);
		Drawable serpentina = this.getResources().getDrawable(R.drawable.serpentina);
		itemizedOverlayInteres = new ItemizedOverlayLugar(chincheta, false);
		itemizedOverlayAgrupacion = new ItemizedOverlayLugar(serpentina, false);
		mapOverlays.remove(itemizedOverlayInteres);
		mapOverlays.remove(itemizedOverlayAgrupacion);
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null)
			lugares = (List<Lugar>) extras.get(Constantes.PARAMETRO_LUGARES);
		
		for(Lugar lugar : lugares){
			//Puntos de interés
			if(lugar.getAgrupacion()==null)
				itemizedOverlayInteres.add(lugar.getLatitud(), lugar.getLongitud(), lugar.getNombre(), getResumenDescripcionLugar(lugar.getDescripcion()), lugar, lugar.getAgrupacion());
			else
				itemizedOverlayAgrupacion.add(lugar.getLatitud(), lugar.getLongitud(), lugar.getNombre(), lugar.getDescripcion(), null, lugar.getAgrupacion());
		}
		
		if(itemizedOverlayInteres.size()>0)
			mapOverlays.add(itemizedOverlayInteres);
		
		if(itemizedOverlayAgrupacion.size()>0)
			mapOverlays.add(itemizedOverlayAgrupacion);
		
	}
	
	/**
	 * Devuelve un resumen de la descripción indicada
	 * @param descripcion
	 * @return
	 */
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
	
	/**
	 * Mueve el mapa a la última posición conocida
	 * @return
	 */
	private void moverMapaAPosicion(boolean actual){
		if(actual){
			//Comprobamos si tenemos localización por GPS...
			Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			//...y si no, comprobamos si tenemos localización por triangulación de antenas
			if(loc==null)
				loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if(loc!=null){
				GeoPoint geoPoint = new GeoPoint((int)(loc.getLatitude()*1E6), (int)(loc.getLongitude()*1E6));
				mapController.animateTo(geoPoint);

				//Si aun no tenemos posición actual guardada, la guardamos ahora
				if(mListener!=null && mListener.getPuntoActual()==null)
					mListener.onLocationChanged(loc);
			}else
				Toast.makeText(getBaseContext(), "Imposible determinar la posición actual", Toast.LENGTH_LONG).show();
		}else{
			GeoPoint geoPoint = new GeoPoint((int)(Constantes.LATITUD_CADIZ*1E6), (int)(Constantes.LONGITUD_CADIZ*1E6));
			mapController.animateTo(geoPoint);
		}
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.mapa, menu);
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
				moverMapaAPosicion(true);
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

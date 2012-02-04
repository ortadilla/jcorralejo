package es.jcorralejo.android.callejeras2012.activities;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import es.jcorralejo.android.callejeras2012.CallejerasApplication;
import es.jcorralejo.android.callejeras2012.R;
import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;
import es.jcorralejo.android.callejeras2012.entidades.Lugar;
import es.jcorralejo.android.callejeras2012.utils.ConexionFTPUtil;
import es.jcorralejo.android.callejeras2012.utils.Constantes;

public class MenuActivity extends Activity{
	
	private static final long FRECUENCIA_ACTUALIZACION = 60*60*1000*1; // recarga cada hora 
	private CallejerasApplication app;
	private AdView adView1;
	private AdView adView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		app = (CallejerasApplication)getApplication();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		cargarAnuncios();
		
		cargarFavoritas();
		
		TextView callejeras = (TextView) findViewById(R.id.callejeras);
		callejeras.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), AgrupacionesActivity.class);
					intent.putExtra(Constantes.PARAMETRO_AGRUPACIONES, (ArrayList<Agrupacion>)app.getAgrupaciones());
					startActivity(intent);
				}
			}
		);
		TextView dondeAndan = (TextView) findViewById(R.id.dondeAndan);
		dondeAndan.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(v.getContext(), "Cargando la posición de las agrupaciones...", Toast.LENGTH_LONG).show();
					List<Lugar> lugares = obtenerLugaresActuales();
					if(!lugares.isEmpty()){
						Intent intent = new Intent();
						intent.setClass(getApplicationContext(), MapaActivity.class);
						intent.putExtra(Constantes.PARAMETRO_LUGARES, (ArrayList<Lugar>)lugares);
						startActivity(intent);
					}else{
						Toast.makeText(getBaseContext(), "Ninguna agrupación ha indicado su lugar recientemente...", Toast.LENGTH_LONG).show();
					}
				}
			}
		);
		TextView lugares = (TextView) findViewById(R.id.lugares);
		lugares.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), MapaActivity.class);
					intent.putExtra(Constantes.PARAMETRO_LUGARES, (ArrayList<Lugar>)app.getPuntosInteres());
					startActivity(intent);
				}
			}
		);
		TextView coac2012 = (TextView) findViewById(R.id.coac2012);
		coac2012.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					try{
						Intent i = new Intent(Intent.ACTION_VIEW);
						i.addCategory(Intent.CATEGORY_LAUNCHER);
						i.setClassName("es.jcorralejo.android", "es.jcorralejo.android.coac2012.activities.MenuActivity");
						startActivity(i);
					}catch (Exception e) {
						try{
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.addCategory(Intent.CATEGORY_LAUNCHER);
							i.setClassName("es.jcorralejo.android.coac2012", "es.jcorralejo.android.coac2012.activities.MenuActivity");
							startActivity(i);
						}catch(Exception e2){
							showDialog(Constantes.DIALOG_INSTALAR_COAC2012);
						}
					}
				}
			}
		);
		
		if(app.isError())
			finish();
	}
	
	private List<Lugar> obtenerLugaresActuales(){
		List<Lugar> lugaresActuales = new ArrayList<Lugar>();
		
		try {
			ConexionFTPUtil conexionFTPUtil = new ConexionFTPUtil();
			byte[] fichero = conexionFTPUtil.descargarFichero2("lugares.txt", "coac2012.webatu.com", "a3635692", "elmejor1", "public_html");
			
			String nombreFile = Environment.getExternalStorageDirectory().getAbsolutePath()+Constantes.PATH_IMAGENES+"lugares.txt";
			FileOutputStream fos = new FileOutputStream(nombreFile);
			fos.write(fichero);
			fos.flush();
			fos.close();
			BufferedReader bf = new BufferedReader(new FileReader(nombreFile));
			String linea;
			while ((linea = bf.readLine())!=null) 
				procesarLineaLugar(linea, lugaresActuales);
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), "Imposible determinar la posición de las agrupaciones...", Toast.LENGTH_LONG).show();
		}
		
		return lugaresActuales; 
	}
	
	private void procesarLineaLugar(String lineaLugar, List<Lugar> lugaresActuales){
		String[] datos = lineaLugar.split("\\|");
		Integer idAgrupacion = Integer.parseInt(datos[0]);
		float log = Float.parseFloat(datos[1]);
		float lat = Float.parseFloat(datos[2]);
		String dia = datos[3];
		String hora = datos[4];
		
		String[] datosDia = dia.split("/");
		String[] datosHora = hora.split(":");
		Calendar calendar = new GregorianCalendar(Integer.parseInt(datosDia[2]), Integer.parseInt(datosDia[1])-1, Integer.parseInt(datosDia[0]),
												  Integer.parseInt(datosHora[0]), Integer.parseInt(datosHora[1])); 
		Date fecha = new java.sql.Date(calendar.getTimeInMillis());
		Date ahora = new Date();
		long diferencia = ( ahora.getTime() - fecha.getTime() );
		if(diferencia<FRECUENCIA_ACTUALIZACION){
			Lugar lugar = new Lugar();
			Agrupacion agrupacion = getAgrupacionPorId(idAgrupacion);
			lugar.setAgrupacion(agrupacion);
			lugar.setLatitud(lat);
			lugar.setLongitud(log);
			lugar.setNombre(agrupacion.getNombre());
			float[] coordenada = {lat, log};
			lugar.setDescripcion(traducirCoordenadas(coordenada));
			lugaresActuales.add(lugar);
		}
	}
	
	protected String traducirCoordenadas(float[] coordenada){
		String dir = "";
		try {
			Geocoder gc = new Geocoder(this, Locale.getDefault());
			List<Address> addresses = gc.getFromLocation(coordenada[0], coordenada[1], 1);
			if (addresses.size() > 0) {
				Address address = addresses.get(0);
				for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
					dir += address.getAddressLine(i) + "\t";
				dir += address.getCountryName();
			}
		} catch (IOException e) {
			Log.e("Error al traducir coordenadas", e.getMessage());
		}
		return dir;
	}
	
    private Agrupacion getAgrupacionPorId(int id){
    	for(Agrupacion a : app.getAgrupaciones()){
    		if(a.getId()==id)
    			return a;
    	}
    	return null;
    }

	
	private void cargarAnuncios(){
		Set<String> key = new HashSet<String>();
		key.add("Carnaval"); 
		key.add("Cádiz"); 
		key.add("Comparsa"); 
		key.add("Chirigota"); 
		key.add("Coro"); 
		key.add("Cuarteto"); 
		key.add("Febrero"); 

		AdRequest r1 = new AdRequest();
		r1.setKeywords(key);
		adView1 = (AdView) findViewById(R.id.ad1);
	    adView1.loadAd(r1);

	    AdRequest r2 = new AdRequest();
	    r1.setKeywords(key);
	    adView2 = (AdView) findViewById(R.id.ad2);
	    adView2.loadAd(r2);
	}
	
	@Override
	public void onDestroy() {
		adView1.destroy();
		adView2.destroy();
		super.onDestroy();
	}

	private void cargarFavoritas(){
		SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
		String favString = prefs.getString(Constantes.PREFERENCE_FAVORITAS, "");
		if(favString!=null && !favString.equals("")){
			String[] split = favString.split("\\|");
			for(String fav : split)
				if(fav!=null && !fav.equals(""))
					app.getFavoritas().add(Integer.parseInt(fav));
		}

	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
			case Constantes.DIALOG_NO_DATOS:
				builder.setMessage("Ha ocurrido un error al procesar los datos del Servidor.\n\nPor favor, vuelva a intentarlo pasados unos minutos. Si persiste el problema contacte con el desarrollador");
				builder.setPositiveButton("Volver",
										  new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {
												app.cargarDatos(null);
											}
									  	  });
				return builder.create();

			case Constantes.DIALOG_ACERCA_DE:
				LayoutInflater li = LayoutInflater.from(this);
				View view = li.inflate(R.layout.acercade, null);
				builder.setIcon(R.drawable.icon)
				       .setTitle(getString(R.string.app_name))
				       .setPositiveButton("Contactar", 
									    	new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog, int which) {
													Intent i = new Intent(android.content.Intent.ACTION_SEND);
													i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"coac2012android@gmail.com"});
													i.setType("plain/text");
													startActivity(i);
										  	}})
				       .setNeutralButton("Donar", 
						    				new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog, int which) {
													Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://market.android.com/details?id=es.jcorralejo.android.coac2012"));
													startActivity(i);
												}})
				       .setNegativeButton("Volver", null)
				       .setView(view);
				return builder.create();

			case Constantes.DIALOG_INSTALAR_COAC2012:
				builder.setMessage("Le recomendamos que instale 'COAC2012' para poder seguir con todo detalle el concurso COAC en este 2012");
				builder.setPositiveButton("Instalar COAC2012",
						  new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.URL_COAC2012));
								startActivity(i);
					  	  }});
				builder.setNegativeButton("Volver",null);
				return builder.create();

			default:
				return null;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);;
		long ultima = prefs.getLong("ultima_actualizacion", 0);
		if ((System.currentTimeMillis() - ultima) > FRECUENCIA_ACTUALIZACION){ 
			app.cargarDatos(null);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion", System.currentTimeMillis());
			editor.commit();
		}
		
		cargarAnuncios();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuAcercaDe:
				showDialog(Constantes.DIALOG_ACERCA_DE);
				return true;
			case R.id.menuActualizar:
				final ProgressDialog pd = ProgressDialog.show(this, "Cargando datos","Por favor, espere mientras actualizamos los datos desde el servidor...", true, false);
				app.cargarDatos(pd);

				SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);;
				Editor editor = prefs.edit();
				editor.putLong("ultima_actualizacion", System.currentTimeMillis());
				editor.commit();
				return true;
			case R.id.menuQuit:
				finish();
				return true;
			case R.id.menuLoguearse:
				//TODO
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}

package es.jcorralejo.android.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.utils.Constantes;

public abstract class LugarAbstractActivity extends Activity{
	
	protected TextView nombreLugar;
	protected TextView descripcionLugar;
	protected ImageView imagenLugar;
	
	/**
	 * Identifica el id del lugar que se está mostrando
	 */
	protected long idLugar;
	
	/**
	 * Indica si se debe cargar la imagen que tiene el lugar en BD o la seleccionada en la galería
	 */
	protected boolean cargarImagenBD = true;
	
	/**
	 * Devuelve el layout a mostrar
	 * @return Layout a mostrar
	 */
	protected abstract int getLayout();
	
	/**
	 * Devuelve la Activity a la que volver
	 * @return Activity a la que volver
	 */
	@SuppressWarnings("rawtypes")
	protected abstract Class getActivityAnterior();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayout());
		setTitle(R.string.lugar);
		nombreLugar = (TextView) findViewById(R.id.lugarNombre);
		descripcionLugar = (TextView) findViewById(R.id.lugarDescripcion);
		imagenLugar = (ImageView) findViewById(R.id.lugarImagen);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		try {
			Bundle extras = getIntent().getExtras();
			idLugar = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
		
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			uri = ContentUris.withAppendedId(uri, idLugar);
			Cursor cursor = managedQuery(uri, columnas, null, null, null);
			// Queremos enterarnos si cambian los datos para recargar
			cursor.setNotificationUri(getContentResolver(), uri);
			
			// Para que la actividad se encarge de manejar el cursor según sus ciclos de vida
			startManagingCursor(cursor);
			
			// Tomamos los datos del Lugar
			if(cursor.moveToFirst()) {
				String nombre = cursor.getString(1);
				if(nombre!=null)
					nombreLugar.setText(nombre);
				String descr = cursor.getString(2);
				if(descr!=null)
					descripcionLugar.setText(descr);
				String imagen = cursor.getString(3);
				if(imagen!=null){
					if(cargarImagenBD)
						setImagen(Uri.parse(imagen));
				}else{
					if(cargarImagenBD)
						imagenLugar.setImageResource(R.drawable.no_imagen);
				}
			}
			
			cargarImagenBD = true;
		} catch (Exception e) {
			//Si se produce algún error al obtener los datos los lugar, avisamos al usuario
			//y cerramos la activity, volcando la traza del error
			Toast.makeText(this, R.string.msg_error_salir, Toast.LENGTH_SHORT).show();
			
			finish();
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), PrincipalActivity.class);
			startActivity(intent);
			
			e.printStackTrace();
		}
	}
	
	protected void setImagen(Uri uriImagen){
		if(uriImagen!=null){
			//Si la imagen es mayor al tamaño de la pantalla, la ajustamos al 80% de esta
			imagenLugar.setImageURI(uriImagen);
			imagenLugar.setMaxHeight((int) (getApplicationContext().getResources().getDisplayMetrics().heightPixels * Constantes.COEFICIENTE_REDUCCION_IMAGEN));
			imagenLugar.setMaxWidth((int)(getApplicationContext().getResources().getDisplayMetrics().widthPixels * Constantes.COEFICIENTE_REDUCCION_IMAGEN));
			imagenLugar.setAdjustViewBounds(true);
			imagenLugar.setScaleType(ScaleType.CENTER_INSIDE);
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			// Abrimos el popUp "Acerca de..." 
			case Constantes.DIALOG_PEDIR_CONFIRMACION:
				final AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.msg_condirmacion_eliminar);
				builder.setPositiveButton(R.string.si,
										  new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												//Eliminamos el lugar
												Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
												getContentResolver().delete(uri, Lugar._ID+" = "+idLugar, null);
												
												//y volvemos a la actividad desde la que hemos llegado
												Intent intent = new Intent();
												intent.setClass(getApplicationContext(), getActivityAnterior());
												startActivity(intent);}
									  	  });
				builder.setNegativeButton(R.string.no, 
										  new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												//No tenemos nada que hacer
											}
										  });
				
				return builder.create();
			default:
				return null;
		}
	}


}

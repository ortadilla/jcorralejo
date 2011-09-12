package es.jcorralejo.android.activities;

import android.app.Activity;
import android.content.ContentUris;
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
	
	protected long idLugar;
	
	protected boolean cargarImagenBD = true;
	
	protected abstract int getLayout();
	
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
			
			// Tomamosl los datos del Lugar
			if(cursor.moveToFirst()) {
				String nombre = cursor.getString(1);
				if(nombre!=null)
					nombreLugar.setText(nombre);
				String descr = cursor.getString(2);
				if(descr!=null)
					descripcionLugar.setText(descr);
				String imagen = cursor.getString(3);
				if(imagen!=null && cargarImagenBD)
					setImagen(Uri.parse(imagen));
			}
			
			cargarImagenBD = true;
		} catch (Exception e) {
			//Si se produce algún error al obtener los datos los lugar, avisamos al usuario
			//y cerramos la aplicación, volcando la traza del error
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


}

package activities;

import utils.Constantes;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;

public class LugarAcitivity extends Activity {

	private TextView nombreLugar;
	private TextView descripcionLugar;
	private ImageView imagenLugar;
	/**
	 * OnCreate
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugar);
		setTitle(R.string.lugar);
		nombreLugar = (TextView) findViewById(R.id.lugarNombre);
		descripcionLugar = (TextView) findViewById(R.id.lugarDescripcion);
		imagenLugar = (ImageView) findViewById(R.id.lugarImagen);
		
		
		Button botonEditar = (Button) findViewById(R.id.botonEditar);
		botonEditar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent2 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
					startActivityForResult(intent2, 5);
				}
			}
		);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 5){
			Uri selectedImage = data.getData();
			System.out.println(selectedImage);
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		try {
			Bundle extras = getIntent().getExtras();
			long idNoticia = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
		
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			uri = ContentUris.withAppendedId(uri, idNoticia);
			Cursor cursor = managedQuery(uri, columnas, null, null, null);
			// Queremos enterarnos si cambian los datos para recargar
			cursor.setNotificationUri(getContentResolver(), uri);
			
			// Para que la actividad se encarge de manejar el cursor seg�n sus ciclos de vida
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
				if(imagen!=null)
					imagenLugar.setImageURI(Uri.parse(imagen));
			}
		} catch (Exception e) {
			//Si se produce alg�n error al obtener los datos los lugar, avisamos al usuario
			//y cerramos la aplicaci�n, volcando la traza del error
			Toast.makeText(this, R.string.msg_error_salir, Toast.LENGTH_SHORT).show();
			
			finish();
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), PrincipalActivity.class);
			startActivity(intent);
			
			e.printStackTrace();
		}
	}

}

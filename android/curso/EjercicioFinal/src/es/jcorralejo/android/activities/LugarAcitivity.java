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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.utils.Constantes;

public class LugarAcitivity extends Activity {
	
	private static final float COEFICIENTE_REDUCCION_IMAGEN = (float) 0.8;

	private TextView nombreLugar;
	private TextView descripcionLugar;
	private ImageView imagenLugar;
	
	private long idNoticia;
	
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
			idNoticia = extras.getLong(Constantes.PARAMETRO_ID_LUGAR);
			final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION, Lugar.FOTO, Lugar.LATITUD, Lugar.LONGITUD};
		
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			uri = ContentUris.withAppendedId(uri, idNoticia);
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
				if(imagen!=null){
					//Si la imagen es mayor al tamaño de la pantalla, la ajustamos al 80% de esta
					imagenLugar.setImageURI(Uri.parse(imagen));
					imagenLugar.setMaxHeight((int) (getApplicationContext().getResources().getDisplayMetrics().heightPixels * COEFICIENTE_REDUCCION_IMAGEN));
					imagenLugar.setMaxWidth((int)(getApplicationContext().getResources().getDisplayMetrics().widthPixels * COEFICIENTE_REDUCCION_IMAGEN));
					imagenLugar.setAdjustViewBounds(true);
					imagenLugar.setScaleType(ScaleType.CENTER_INSIDE);
				}
			}
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
	
	/**
	 * "Inflamos" las opciones de menú de la pantalla de datos de un lugar 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_lugar, menu);
		return true;
	}

	/**
	 * Definimos las acciones correspondientes con cada opción de menú
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Al pulsar sobre "Editar" navegamos a la actividad EditarLugarActivity 
			case R.id.lugarEditar:
				//TODO
				return true;
				
			// Al pulsar sobre "Eliminar" pedimos confirmación al usuario
			case R.id.lugarEliminar:
				showDialog(Constantes.DIALOG_PEDIR_CONFIRMACION);
				return true;
				
			// Al pulsar sobre "Editar" navegamos a la actividad MapaLugaresActivity para mostrar el lugar 
			case R.id.lugarVerUbicacion:
				//TODO
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
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
												getContentResolver().delete(uri, Lugar._ID+" = "+idNoticia, null);
												//y volvemos a ListaLugaresActivity
												Intent intent = new Intent();
												intent.setClass(getApplicationContext(), ListaLugaresActivity.class);
												startActivity(intent);											}
									  	  });
				builder.setNegativeButton(R.string.no, 
										  new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												//TODO
											}
										  });
				
				return builder.create();
			default:
				return null;
		}
	}
}

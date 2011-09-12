package es.jcorralejo.android.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.utils.Constantes;

public class LugarAcitivity extends LugarAbstractActivity {
	
	@Override
	protected int getLayout() {
		return R.layout.lugar;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
				Intent i = new Intent();
				i.setClass(getApplicationContext(), EditarLugarActivity.class);
				i.putExtra(Constantes.PARAMETRO_ID_LUGAR, idLugar);
				startActivity(i);
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
												getContentResolver().delete(uri, Lugar._ID+" = "+idLugar, null);
												//y volvemos a ListaLugaresActivity
												Intent intent = new Intent();
												intent.setClass(getApplicationContext(), ListaLugaresActivity.class);
												startActivity(intent);											}
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

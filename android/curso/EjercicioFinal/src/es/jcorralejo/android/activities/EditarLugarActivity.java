package es.jcorralejo.android.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.utils.Constantes;


public class EditarLugarActivity extends LugarAbstractActivity{

	private Uri uriNuevaImagen;
	
	@Override
	protected int getLayout() {
		return R.layout.editar_lugar;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Al pulsar sobre la imagen vamos a seleccionar una de la galería del teléfono
		imagenLugar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					navegarEditarImagen();
				}
			}
		);
		// Registramos la imagen para definir su menú contextual
		registerForContextMenu(imagenLugar);
		
		// Botón editar
		Button botonEditar = (Button) findViewById(R.id.botonEditar);
		botonEditar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					//Modificamos el lugar con los nuevos datos
					Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
					ContentValues contentValues = new ContentValues();
					if(ignorarDatosBD)
						contentValues.put(Lugar.FOTO, uriNuevaImagen!=null ? uriNuevaImagen.toString() : null);
					contentValues.put(Lugar.DESCRIPCION, descripcionLugar.getText().toString());
					contentValues.put(Lugar.NOMBRE, nombreLugar.getText().toString());
					getContentResolver().update(uri, contentValues, Lugar._ID+" = "+idLugar, null);
					
					// Volvemos a los detalles del Lugar
					finish();
				}
			}
		);
		
		// Botón eliminar
		Button botonEliminar = (Button) findViewById(R.id.botonEliminar);
		botonEliminar.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						//Levantamos el popUp de confirmación
						showDialog(Constantes.DIALOG_PEDIR_CONFIRMACION);
					}
				}
		);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == Constantes.RESULT_FOTO){
			if(data!=null){
				// Procesamos la imagen de vuelta de la galería
				Uri selectedImage = data.getData();
				setImagen(selectedImage);
				uriNuevaImagen = selectedImage;
				ignorarDatosBD = true;
			}
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, Constantes.MENU_EDITAR, 0, R.string.editar);
		menu.add(0, Constantes.MENU_ELIMINAR, 0, R.string.eliminar);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    	// Navegamos a la galería para seleccionar otra imagen
	        case Constantes.MENU_EDITAR:
	        	navegarEditarImagen();
	            return true;
	        // Eliminamos la imagen actual del lugar
	        case Constantes.MENU_ELIMINAR:
	        	imagenLugar.setImageResource(R.drawable.no_imagen);
	        	ignorarDatosBD = true;
	        	uriNuevaImagen = null;
	        	return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
	/**
	 * Navega a la galería para seleccionar una nueva imagen para el lugar
	 */
	private void navegarEditarImagen(){
		Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(intent, Constantes.RESULT_FOTO);	
	}
	
	
}

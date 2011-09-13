package es.jcorralejo.android.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import es.jcorralejo.android.R;
import es.jcorralejo.android.utils.Constantes;


public class EditarLugarActivity extends LugarAbstractActivity{

	
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
					Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
					startActivityForResult(intent, Constantes.RESULT_FOTO);
				}
			}
		);
		
		// Botón editar
		Button botonEditar = (Button) findViewById(R.id.botonEditar);
		botonEditar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					//TODO: modificar el lugar con los nuevos datos
					
					// Volvemos a los detalles del Lugar
					Intent i = new Intent();
					i.setClass(getApplicationContext(), LugarAcitivity.class);
					i.putExtra(Constantes.PARAMETRO_ID_LUGAR, idLugar);
					startActivity(i);
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
				Uri selectedImage = data.getData();
				setImagen(selectedImage);
				cargarImagenBD = false;
			}
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getActivityAnterior() {
		return ListaLugaresActivity.class;
	}
	
}

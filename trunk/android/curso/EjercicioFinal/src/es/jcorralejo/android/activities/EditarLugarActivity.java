package es.jcorralejo.android.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
		
		imagenLugar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
					startActivityForResult(intent, Constantes.RESULT_FOTO);
				}
			}
		);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == Constantes.RESULT_FOTO){
			Uri selectedImage = data.getData();
			setImagen(selectedImage);
			cargarImagenBD = false;
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
}

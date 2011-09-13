package es.jcorralejo.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import es.jcorralejo.android.R;
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
					Intent i = new Intent();
					i.setClass(getApplicationContext(), EditarLugarActivity.class);
					i.putExtra(Constantes.PARAMETRO_ID_LUGAR, idLugar);
					startActivity(i);
				}
			}
		);
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
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class getActivityAnterior() {
		return ListaLugaresActivity.class;
	}
	
}

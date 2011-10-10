package es.jcorralejo.android.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;
import es.jcorralejo.android.utils.Constantes;

public class ListaLugaresActivity extends ListActivity{
	
	private SimpleCursorAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugares);
		setTitle(R.string.nombre_lugares);
		
		//Configuramos el adapter
		configurarAdapter();
	}
	
	public void configurarAdapter() {
		// Sólo necesitamos id, nombre y descripción de cada Lugar
		final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION};
		// Nos traemos la información de todos los Lugares
		Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
		Cursor cursor = managedQuery(uri, columnas, null, null, Lugar.NOMBRE); //Ordeamos por Nombre
		// Queremos enterarnos si cambian los datos para recargar el cursor
		cursor.setNotificationUri(getContentResolver(), uri);
		
		// Para que la actividad se encarge de manejar el cursor según sus ciclos de vida
		startManagingCursor(cursor);
		
		// Mapeamos las querys SQL a los campos de las vistas
		String[] camposDb = new String[] {Lugar.NOMBRE, Lugar.DESCRIPCION};
		int[] camposView = new int[] {R.id.lugarNombre, R.id.lugarDescripcion};
		
		// Creamos el adapter
		adapter = new SimpleCursorAdapter(this, R.layout.lugares_item, cursor, camposDb, camposView);
		setListAdapter(adapter);
	}
	
	
	/**
	 * "Inflamos" las opciones de menú de la pantalla de lista de lugares 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_lista, menu);
		return true;
	}

	/**
	 * Definimos las acciones correspondientes con cada opción de menú
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Al pulsar sobre "Agregar" navegamos a la actividad MapaLugaresActivity con un Toast que indica
			// que se debe pulsar un punto para guardar el nuevo Lugar 
			case R.id.listaAgregar:
				Toast.makeText(this, R.string.msg_info_agregar_lugar, Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MapaLugaresActivity.class);
				startActivity(intent);
				
				return true;
				
			// Al pulsar sobre "Eliminar" mostramos los checks para que el usuario marque los lugares
			// que quiere eliminar, además del botón "Eliminar" para confirmar la acción
			case R.id.listaEliminar:
				return accionMenuEliminar();
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Ejecutado al pulsar la opción de menú "Eliminar", muestra los checks para que el usuario marque los lugares
	 * que quiere eliminar, además del botón "Eliminar" para confirmar la acción
	 * @return
	 */
	private boolean accionMenuEliminar(){
		// Creamos el botón "Eliminar"
		Button botonEliminar = new Button(this);
		botonEliminar.setText(R.string.eliminar);
		botonEliminar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1));
		botonEliminar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					showDialog(Constantes.DIALOG_PEDIR_CONFIRMACION);
				}
			}
		);
		
		// Creamos el botón "Cancelar"
		Button botonCancelar = new Button(this);
		botonCancelar.setText(R.string.cancelar);
		botonCancelar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, 1));
		botonCancelar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					configurarListenerCancelarEliminacion();
				}
			}
		);
		
		// Agrupamos ambos botones en un LinearLayout 
		LinearLayout linearLayoutBotones = new LinearLayout(this);
		linearLayoutBotones.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 1);
		layoutParams.setMargins(0, 15, 0, 0);
		linearLayoutBotones.setLayoutParams(layoutParams);
		linearLayoutBotones.setGravity(Gravity.CENTER_HORIZONTAL);
		linearLayoutBotones.addView(botonEliminar,0);
		linearLayoutBotones.addView(botonCancelar,1);
		
		// Añadimos el LinearLayout al principio de la pantalla
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
		linearLayout.addView(linearLayoutBotones, 1);
		
		// Mostramos los checks 
		ListView lista = (ListView) findViewById(android.R.id.list);
		for(int i=0; i<lista.getChildCount(); i++){
			LinearLayout lugar = (LinearLayout) lista.getChildAt(i);
			LinearLayout check_nombre = (LinearLayout) lugar.getChildAt(0);
			CheckBox checkBox = (CheckBox)check_nombre.getChildAt(0);
			checkBox.setVisibility(View.VISIBLE);
		}
		
		return true;
	}
	
	/**
	 * Cancela la acción de eliminar lugares, es decir, carga de nuevo el adapter y elimina los botones "Eliminar" y "Cancelar"
	 */
	private void configurarListenerCancelarEliminacion(){
		// Cargamos de nuevo el adapter...
		configurarAdapter();
		// ..y eliminamos los botones "Eliminar" y "Cancelar"
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
		linearLayout.removeViewAt(1);
	}
	
	/**
	 * Elimina los lugares seleccionados
	 */
	private void configurarListenerEliminar(){
		ListView lista = (ListView) findViewById(android.R.id.list);
		String idsEliminar = "";
		for(int i=0; i<lista.getChildCount(); i++){
			LinearLayout lugar = (LinearLayout) lista.getChildAt(i);
			LinearLayout check_nombre = (LinearLayout) lugar.getChildAt(0);
			CheckBox checkBox = (CheckBox)check_nombre.getChildAt(0);
			// Si está marcado, lo añadimos para eliminar el registro posteriormente
			if(checkBox.isChecked())
				idsEliminar += adapter.getItemId(i)+",";
		}
		
		// Eliminamos todos los registro a la vez, siempre que se haya seleccionado alguno
		if(!"".equals(idsEliminar)){
			//Añadimos los paréntesos y quitamos la última coma ","
			idsEliminar = "("+idsEliminar;
			idsEliminar = idsEliminar.substring(0, idsEliminar.length()-1)+")";
			
			Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
			getContentResolver().delete(uri, Lugar._ID+" in "+idsEliminar, null);
		}
		
		configurarListenerCancelarEliminacion();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent();
		i.setClass(getApplicationContext(), LugarAcitivity.class);
		i.putExtra(Constantes.PARAMETRO_ID_LUGAR, id);
		startActivity(i);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			// Abrimos el popUp "Acerca de..." 
			case Constantes.DIALOG_PEDIR_CONFIRMACION:
				final AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage(R.string.msg_condirmacion_eliminar_varios);
				builder.setPositiveButton(R.string.si,
										  new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												configurarListenerEliminar();
											}
									  	  });
				builder.setNegativeButton(R.string.no, 
										  new DialogInterface.OnClickListener() {
											@Override
											public void onClick(DialogInterface dialog, int which) {
												configurarListenerCancelarEliminacion();
											}
										  });
				
				return builder.create();
			default:
				return null;
		}
	}


}

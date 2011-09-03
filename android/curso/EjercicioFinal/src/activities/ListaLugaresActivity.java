package activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;

public class ListaLugaresActivity extends ListActivity{
	
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
		Uri uri = Uri.parse("content://es.jcorralejo.lugares/lugar");
		Cursor cursor = managedQuery(uri, columnas, null, null, Lugar.NOMBRE); //Ordeamos por Nombre
		
		// Queremos enterarnos si cambian los datos para recargar el cursor
		cursor.setNotificationUri(getContentResolver(), uri);
		
		// Para que la actividad se encarge de manejar el cursor según sus ciclos de vida
		startManagingCursor(cursor);
		
		// Mapeamos las querys SQL a los campos de las vistas
		String[] camposDb = new String[] {Lugar.NOMBRE, Lugar.DESCRIPCION};
		int[] camposView = new int[] {R.id.lugarNombre, R.id.lugarDescripcion};
		
		// Creamos el adapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.lugares_item, cursor, camposDb, camposView);
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
				//TODO
				return true;
				
			// Al pulsar sobre "Eliminar" mostramos los checks para que el usuario marque los lugares
			// que quiere eliminar, además del botón "Eliminar" para confirmar la acción
			case R.id.listaEliminar:
				Button botonEliminar = new Button(this);
				botonEliminar.setText(R.string.eliminar);
				botonEliminar.setOnClickListener(
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							//TODO: Comprobar checks seleccionados, preguntar y eliminar
							System.out.println("Botón Eliminar pulsado");
						}
					}
				);
				
				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
				linearLayout.addView(botonEliminar, 0);
				
				
				
				ListView lista = (ListView) findViewById(android.R.id.list);
				
				for(int i=0; i<lista.getChildCount(); i++){
					LinearLayout lugar = (LinearLayout) lista.getChildAt(i);
					LinearLayout check_nombre = (LinearLayout) lugar.getChildAt(0);
					CheckBox checkBox = (CheckBox)check_nombre.getChildAt(0);
					checkBox.setVisibility(View.VISIBLE);
					
					System.out.println(item);
				}
				
				
				
				
				
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}


}

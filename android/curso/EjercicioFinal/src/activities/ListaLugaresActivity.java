package activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.graphics.Color;
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
import es.jcorralejo.android.R;
import es.jcorralejo.android.bd.LugaresDB.Lugar;
import es.jcorralejo.android.bd.LugaresProvider;

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
		// S�lo necesitamos id, nombre y descripci�n de cada Lugar
		final String[] columnas = new String[] {Lugar._ID, Lugar.NOMBRE, Lugar.DESCRIPCION};
		// Nos traemos la informaci�n de todos los Lugares
		Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
		Cursor cursor = managedQuery(uri, columnas, null, null, Lugar.NOMBRE); //Ordeamos por Nombre
		
		// Queremos enterarnos si cambian los datos para recargar el cursor
		cursor.setNotificationUri(getContentResolver(), uri);
		
		// Para que la actividad se encarge de manejar el cursor seg�n sus ciclos de vida
		startManagingCursor(cursor);
		
		// Mapeamos las querys SQL a los campos de las vistas
		String[] camposDb = new String[] {Lugar.NOMBRE, Lugar.DESCRIPCION};
		int[] camposView = new int[] {R.id.lugarNombre, R.id.lugarDescripcion};
		
		// Creamos el adapter
		adapter = new SimpleCursorAdapter(this, R.layout.lugares_item, cursor, camposDb, camposView);
		setListAdapter(adapter);
	}
	
	
	/**
	 * "Inflamos" las opciones de men� de la pantalla de lista de lugares 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_lista, menu);
		return true;
	}

	/**
	 * Definimos las acciones correspondientes con cada opci�n de men�
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
			// que quiere eliminar, adem�s del bot�n "Eliminar" para confirmar la acci�n
			case R.id.listaEliminar:
				return accionMenuEliminar();
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * Ejecutado al pulsar la opci�n de men� "Eliminar", muestra los checks para que el usuario marque los lugares
	 * que quiere eliminar, adem�s del bot�n "Eliminar" para confirmar la acci�n
	 * @return
	 */
	private boolean accionMenuEliminar(){
		// Creamos el bot�n "Eliminar"
		Button botonEliminar = new Button(this);
		botonEliminar.setText(R.string.eliminar);
		botonEliminar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					ListView lista = (ListView) findViewById(android.R.id.list);
					String idsEliminar = "";
					for(int i=0; i<lista.getChildCount(); i++){
						LinearLayout lugar = (LinearLayout) lista.getChildAt(i);
						LinearLayout check_nombre = (LinearLayout) lugar.getChildAt(0);
						CheckBox checkBox = (CheckBox)check_nombre.getChildAt(0);
						// Si est� marcado, lo a�adimos para eliminar el registro posteriormente
						if(checkBox.isChecked())
							idsEliminar += adapter.getItemId(i)+",";
					}
					
					// Eliminamos todos los registro a la vez, siempre que se haya seleccionado alguno
					if(!"".equals(idsEliminar)){
						//A�adimos los par�ntesos y quitamos la �ltima coma ","
						idsEliminar = "("+idsEliminar;
						idsEliminar = idsEliminar.substring(0, idsEliminar.length()-1)+")";
						
						Uri uri = Uri.parse(LugaresProvider.CONTENT_URI+"/lugar");
						getContentResolver().delete(uri, Lugar._ID+" in "+idsEliminar, null);
					}
					
					// Se eliminen elementos o no, debemos cargar de nuevo el adapter...
					configurarAdapter();
					// ..y eliminar los botones "Eliminar" y "Cancelar"
					LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
					linearLayout.removeViewAt(0);
				}
			}
		);
		
		// Creamos el bot�n "Cancelar"
		Button botonCancelar = new Button(this);
		botonCancelar.setText(R.string.cancelar);
		botonCancelar.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					// Cargamos de nuevo el adapter...
					configurarAdapter();
					// ..y eliminamos los botones "Eliminar" y "Cancelar"
					LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
					linearLayout.removeViewAt(0);
				}
			}
		);
		
		// Agrupamos ambos botones en un LinearLayout 
		LinearLayout linearLayoutBotones = new LinearLayout(this);
		linearLayoutBotones.setOrientation(LinearLayout.HORIZONTAL);
		linearLayoutBotones.setGravity(Gravity.CENTER_HORIZONTAL);
		linearLayoutBotones.addView(botonEliminar,0);
		linearLayoutBotones.addView(botonCancelar,1);
		
		// A�adimos el LinearLayout al principio de la pantalla
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayoutLugares);
		linearLayout.addView(linearLayoutBotones, 0);
		
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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		System.out.println(l+" "+v+" "+position+" "+id);
	}


}

package activities;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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

}

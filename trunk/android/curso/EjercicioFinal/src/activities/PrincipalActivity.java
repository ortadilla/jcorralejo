package activities;

import static es.jcorralejo.android.R.layout.principal;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import es.jcorralejo.android.R;

public class PrincipalActivity extends Activity {
	
	/**
	 * Constante para identificar el popUp "Acerca de..."
	 */
	private static final int DIALOG_ACERCA_DE = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(principal);
		
		Button botonLista = (Button) findViewById(R.id.botonLista);
		Button botonMapa = (Button) findViewById(R.id.botonMapa);
	}
	
	
	/**
	 * "Inflamos" las opciones de menú de la pantalla principal 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	/**
	 * Definimos las acciones correspondientes con cada opción de menú
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			// Al pulsar sobre "Lista" navegamos a la actividad ListaLugaresActivity
			case R.id.menuLista:
				//TODO
				return true;
			// Al pulsar sobre "Mapa" navegamos a la actividad MapaLugaresActivity
			case R.id.menuMapa:
				//TODO
				return true;
			// Al pulsar sobre "Configuración" navegamos a la actividad ConfiguracionActivity
			case R.id.menuConfiguracion:
				//TODO
				return true;
			// Al pulsar sobre "Acerca de" levantamos el popUp con la información sobre la aplicación
			case R.id.menuAcercaDe:
				showDialog(DIALOG_ACERCA_DE);
				return true;
			// Al pulsar sobre "Salir" salimos de la aplicación
			case R.id.menuQuit:
				finish();
				return true;
			//Acción por defecto
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			// Abrimos el popUp "Acerca de..." 
			case DIALOG_ACERCA_DE:
				LayoutInflater li = LayoutInflater.from(this);
				View view = li.inflate(R.layout.acercade, null);
				final AlertDialog.Builder builder = new AlertDialog.Builder(this).setIcon(R.drawable.icon)
																			     .setTitle(getString(R.string.app_name))
																			     .setPositiveButton("Ok", null)
																			     .setView(view);
				return builder.create();
			default:
				return null;
		}
	}

}

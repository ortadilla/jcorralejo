package es.jcorralejo.android.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import es.jcorralejo.android.CoacApplication;
import es.jcorralejo.android.R;
import es.jcorralejo.android.utils.Constantes;
import es.jcorralejo.android.utils.RssDownloadHelper;

public class MenuActivity extends Activity{
	
	private static final long FRECUENCIA_ACTUALIZACION = 60*60*1000*24; // recarga cada día: 
	private ActualizarPostAsyncTask tarea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.menu);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		cargarDatos(false);
	}
	
	@Override
	protected void onStop() {
		if (tarea != null && !tarea.getStatus().equals(AsyncTask.Status.FINISHED)) {
			tarea.cancel(true);
		}
		super.onStop();
	}
	
	private void cargarDatos(boolean ignorarTiempoEspera){
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		long ultima = prefs.getLong("ultima_actualizacion", 0);
		if (ignorarTiempoEspera || (System.currentTimeMillis() - ultima) > FRECUENCIA_ACTUALIZACION) {
			final ProgressDialog pd = ProgressDialog.show(this,"Cargando datos","Por favor, espere mientras actualizamos los datos desde el servidor...", true, false);
			tarea = new ActualizarPostAsyncTask(pd);
			tarea.execute();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuAcercaDe:
				showDialog(Constantes.DIALOG_ACERCA_DE);
				return true;
			case R.id.menuActualizar:
				cargarDatos(true);
				return true;
			case R.id.menuQuit:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
			case Constantes.DIALOG_ACERCA_DE:
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
	
	
	class ActualizarPostAsyncTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog pd;
		
		public ActualizarPostAsyncTask(ProgressDialog pd) {
			this.pd = pd;
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			CoacApplication app = (CoacApplication) getApplication();
			RssDownloadHelper.updateRssData(app.getRssUrl(), app.getAgrupaciones(), app.getCalendario(), app.getModalidades());
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion", System.currentTimeMillis());
			editor.commit();
			ocultarPD();
		}
		
		@Override
		protected void onCancelled() {
			ocultarPD();
			SharedPreferences prefs = getPreferences(MODE_PRIVATE);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion", 0);
			editor.commit();

			super.onCancelled();
		}
		
		private void ocultarPD(){
			if(pd.isShowing()){
				try{
					pd.dismiss();
					pd = null;
				}catch (Exception e) {
				}
			}

		}
	}

	

}

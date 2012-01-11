package es.jcorralejo.android.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import es.jcorralejo.android.Constantes;
import es.jcorralejo.android.R;

public class MenuActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.menu);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		cargarDatos();
	}
	
	private void cargarDatos(){
		//TODO
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
				cargarDatos();
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


}

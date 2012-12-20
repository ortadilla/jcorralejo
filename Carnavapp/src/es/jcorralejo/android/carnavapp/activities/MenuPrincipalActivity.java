package es.jcorralejo.android.carnavapp.activities;

import org.holoeverywhere.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.utils.AnunciosUtils;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class MenuPrincipalActivity extends SherlockActivity {
	
	private ActionBar actionBar;
	private CarnavappApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getSherlock().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        app = (CarnavappApplication) getApplication();
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        
        View fondo = findViewById(R.id.fondo);
        fondo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), CarnavappActivity3.class);
				startActivity(intent);	
				finish();
			}
		});
        
        AnunciosUtils.cargarAnuncios(this);
	}
	
	@Override
    public void onBackPressed() {
		
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setMessage(R.string.salir_pregunta)
	    	.setCancelable(false)
	    	.setPositiveButton(R.string.si,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			cerrarAplicacion();
		    		}
		    	})
	    	.setNegativeButton(R.string.no,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			dialog.cancel();
		    		}
		    	});

    	AlertDialog alert = builder.create();
    	alert.show();
	}
	
	private void cerrarAplicacion() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);;
		long ultima = prefs.getLong("ultima_actualizacion", 0);
		if ((System.currentTimeMillis() - ultima) > Constantes.FRECUENCIA_ACTUALIZACION){ 
			app.cargarDatos(null);
			Editor editor = prefs.edit();
			editor.putLong("ultima_actualizacion", System.currentTimeMillis());
			editor.commit();
		}
		
		AnunciosUtils.cargarAnuncios(this);
	}
	



}

package es.jcorralejo.android.carnavapp.activities;

import org.holoeverywhere.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

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
        
        boolean actualizar = false;
        Intent intent = getIntent();
        if(intent!=null && intent.getExtras()!=null && intent.getExtras().containsKey(Constantes.ACTUALIZAR))
        	actualizar = intent.getExtras().getBoolean(Constantes.ACTUALIZAR); 
        ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.cargando_datos), getResources().getString(R.string.esperar_carga), true, false, null);
        app.actualizarDatos(pd, actualizar);
        
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
        
        TextView anunciate = (TextView) findViewById(R.id.anunciate);
        anunciate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
		    	
		    	builder.setMessage(R.string.anunciate_text)
			    	.setCancelable(false)
			    	.setPositiveButton(R.string.si,
				    	new DialogInterface.OnClickListener() {
				    		public void onClick(DialogInterface dialog,int id) {
				    			Intent i = new Intent(android.content.Intent.ACTION_SEND);
								i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constantes.DIRECCION_EMAIL});
								i.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.anunciate_email_subject));
								i.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.anunciate_email_text));
								i.setType("plain/text");
								startActivity(i);
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
		});
        
        cargarFavoritas();
        AnunciosUtils.cargarAnuncios(this);
	}
	
	private void cargarFavoritas(){
        SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
        String favString = prefs.getString(Constantes.PREFERENCE_FAVORITAS, "");
        if(favString!=null && !favString.equals("")){
                String[] split = favString.split("\\|");
                for(String fav : split)
                        if(fav!=null && !fav.equals(""))
                                app.getFavoritas().add(Integer.parseInt(fav));
        }

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
	

}

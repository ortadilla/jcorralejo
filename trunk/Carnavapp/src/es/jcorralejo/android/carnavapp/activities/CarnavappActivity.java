package es.jcorralejo.android.carnavapp.activities;

import java.util.List;

import org.holoeverywhere.app.AlertDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.viewpagerindicator.TabPageIndicator;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public abstract class CarnavappActivity extends SherlockFragmentActivity implements OnNavigationListener{
	
	protected CarnavappApplication app;
	protected ViewPager pager;
	protected TabPageIndicator indicator;
	protected ActionBar actionBar;
	protected String[] opciones;
	protected List<Fragment> fragment;
	protected List<String> titulos;
	
	private boolean navegar;
	
	protected final int OPCION_CONCURSO = 0;
	protected final int OPCION_MODALIDADES = 1;
	protected final int OPCION_MAS_CARNAVAL = 2;
	protected final int OPCION_ENLACES = 3;
	protected final int OPCION_PUNTOS_INTERES = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getSherlock().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		app = (CarnavappApplication) getApplication();
		
		configurarFragment();
		configurarActionBar();
		configurarOpciones();
		configurarPageIndicator();
		actionBar.setSelectedNavigationItem(getOpcionSeleccionada());
		
		navegar = false;
	}
	
	private void configurarActionBar(){
		if(actionBar==null){
			actionBar = getSupportActionBar();
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
		}
	}
	
	private void configurarPageIndicator(){
		pager = (ViewPager)findViewById(R.id.pager);
		indicator = (TabPageIndicator)findViewById(R.id.indicator);
		
		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragment, titulos);
		pager.setAdapter(adapter);
		pager.setId(R.id.pager);
		indicator.setViewPager(pager);
	}
	
	private void configurarOpciones(){
		if(opciones==null || opciones.length==0){
			opciones = new String[] {getResources().getString(R.string.concurso),
									 getResources().getString(R.string.modalidades),
									 getResources().getString(R.string.mas_carnaval),
									 getResources().getString(R.string.enlaces),
									 getResources().getString(R.string.puntos_de_interes)};
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.sherlock_spinner_item, opciones);
			actionBar.setListNavigationCallbacks(arrayAdapter, this);
			arrayAdapter.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	 MenuInflater inflater = getSupportMenuInflater();
         inflater.inflate(R.menu.carnavapp, menu);
         return true;
    }
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.configurar) {
            configurar();
        } else if (item.getItemId() == R.id.actualizar) {
        	recargar();
        } else if (item.getItemId() == R.id.info) {
        	showDialog(Constantes.DIALOG_ACERCA_DE);
        } else if (item.getItemId() == R.id.salir) {
        	cerrarAplicacion();
        }
        
        return true;
    }
    
	private void cerrarAplicacion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setMessage(R.string.salir_pregunta)
	    	.setCancelable(false)
	    	.setPositiveButton(R.string.si,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			 System.runFinalizersOnExit(true);
		    			 System.exit(0);
		    			android.os.Process.killProcess(android.os.Process.myPid());
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

    
    private void configurar(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.seleccionar_anio);
		final CharSequence[] anios = new CharSequence[app.getInfoAnios().size()];
		for(int i=0; i<app.getInfoAnios().size(); i++){
			anios[i] = String.valueOf(app.getInfoAnios().get(i).getAnio());
		}
		builder.setItems(anios, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				String anio = (String) anios[item];
				
				SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);;
				Editor editor = prefs.edit();
				editor.putInt(Constantes.CTE_ANIO_ACTUAL_USUARIO, Integer.valueOf(anio));
				editor.commit();
			}
		});
		AlertDialog alert = builder.create();
		alert.setOwnerActivity(this);
		alert.show();
	}
    
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
    	final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
			case Constantes.DIALOG_ACERCA_DE:
				LayoutInflater li = LayoutInflater.from(this);
				View view = li.inflate(R.layout.acercade, null);
				builder
				       .setTitle(getString(R.string.app_name))
				       .setNegativeButton(R.string.contactar, 
									    	new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog, int which) {
													Intent i = new Intent(android.content.Intent.ACTION_SEND);
													i.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{Constantes.DIRECCION_EMAIL});
													i.setType("plain/text");
													startActivity(i);
										  	}})
				       .setNeutralButton(R.string.donar, 
						    				new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog, int which) {
													Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.URL_VERSION_DONACION));
													startActivity(i);
												}})
				       .setPositiveButton(R.string.volver, null)
				       .setView(view);
				return builder.create();

			default:
				return null;
		}
    }
    
    private void recargar(){
    	if(app!=null && app.isActualizando()){
    		Toast.makeText(getBaseContext(), getResources().getString(R.string.ya_actualizando), Toast.LENGTH_LONG).show();
    	}else{
    		final ProgressDialog pd = ProgressDialog.show(this, getResources().getString(R.string.cargando_datos), getResources().getString(R.string.esperar_carga), true, false, null);
			app.actualizarDatos(pd, true);
    	}
    }

	
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		
		if(navegar){
			Class clase = null;
			if(OPCION_CONCURSO == itemPosition){
				clase = ConcursoActivity.class;
			}else if(OPCION_MODALIDADES == itemPosition){
				clase = ModalidadesActivity.class;
			}else if(OPCION_MAS_CARNAVAL == itemPosition){
			}else if(OPCION_ENLACES == itemPosition){
			}else if(OPCION_PUNTOS_INTERES == itemPosition){
			}

			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), clase);
			startActivity(intent);	
		}else
			navegar = true;

		return true;
	}
	
	protected abstract void configurarFragment();
	protected abstract int getOpcionSeleccionada();
}

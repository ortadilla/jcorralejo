package es.jcorralejo.android.carnavapp.activities;

import java.util.List;
import java.util.Stack;

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
import android.support.v4.view.ViewPager.OnPageChangeListener;
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
import com.viewpagerindicator.TabPageIndicator.OnTabReselectedListener;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public abstract class CarnavappActivity extends SherlockFragmentActivity implements OnNavigationListener{
	
	protected CarnavappApplication app;
	protected ViewPager pagerConcurso;
	protected TabPageIndicator indicatorConcurso;
	protected ViewPager pagerModalidades;
	protected TabPageIndicator indicatorModalidades;
	protected ActionBar actionBar;
	protected String[] opciones;
	protected List<Fragment> fragment;
	protected List<String> titulos;
	static private Stack<Integer[]> pila = new Stack<Integer[]>();
	static private Stack<CarnavappActivity> pilaCarnavapp = new Stack<CarnavappActivity>();  
	
	private final int IDX_OPCION = 0;
	private final int IDX_SUBMENU = 1;
	
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
		
		pilaCarnavapp.push(this);
		
		navegar = false;
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return pila;
	}
	
	@SuppressWarnings("unchecked")
	private void recuperarUltimaPila(){
		//Si guardamos una pila, volvemos a donde nos indique
		Object ultimaPila = getLastCustomNonConfigurationInstance();
		if(ultimaPila!=null){
			pila = (Stack<Integer[]>) ultimaPila;
			Integer[] volverA = pila.peek();
			actionBar.setSelectedNavigationItem(volverA[IDX_OPCION]);
			pagerConcurso.setCurrentItem(volverA[IDX_SUBMENU]);
		}
	}
	
	private void configurarActionBar(){
		if(actionBar==null){
			actionBar = getSupportActionBar();
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
		}
	}
	
	private void configurarPageIndicator(){
		pagerConcurso = (ViewPager)findViewById(R.id.pagerConcurso);
		indicatorConcurso = (TabPageIndicator)findViewById(R.id.indicatorConcurso);
		
		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragment, titulos);
		pagerConcurso.setAdapter(adapter);
		pagerConcurso.setId(R.id.pagerConcurso);
		indicatorConcurso.setViewPager(pagerConcurso);
		indicatorConcurso.setOnTabReselectedListener(new OnTabReselectedListener() {
			@Override
			public void onTabReselected(int position) {
				Integer[] ultimo = pila.pop();
				ultimo[IDX_SUBMENU] = position; //Actualizamos la posición
				pila.push(ultimo);
			}
		});
		
		indicatorConcurso.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Integer[] ultimo = pila.pop();
				ultimo[IDX_SUBMENU] = arg0; //Actualizamos la posición
				pila.push(ultimo);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
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
//			actionBar.setSelectedNavigationItem(getOpcionSeleccionada());
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
		
		actualizarPila(itemPosition);
		
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
	
	private void actualizarPila(int itemPosition){
		Integer[] nuevaPosición = new Integer[2];
		nuevaPosición[IDX_OPCION] = itemPosition;
		nuevaPosición[IDX_SUBMENU] = 0; //Siempre empezamos en la primera pestaña
		
		if(!pila.isEmpty()){
			
			//Si llegamos al último no hacemos nada
			Integer[] ultimo = pila.peek();
			if(ultimo[IDX_OPCION].equals(itemPosition))
				return;
			
			//Si pulsan el anterior donde estuvimos quitamos de la pila el úlimo
			if(pila.size()>1){
				Integer[] penultimo = pila.get(pila.size()-2);
				if(penultimo[IDX_OPCION].equals(itemPosition))
					pila.pop();
				else
					pila.push(nuevaPosición);
			}else
				pila.push(nuevaPosición);
		}else
			pila.push(nuevaPosición);
	}
	
	@Override
	public void onBackPressed() {
		pilaCarnavapp.pop();
		super.onBackPressed();

	}
	
	protected abstract void configurarFragment();
	protected abstract int getOpcionSeleccionada();
}

package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;
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

public class CarnavappActivity3 extends SherlockFragmentActivity implements OnNavigationListener{

	private CarnavappApplication app;
	private ViewPager pager;
	private TabPageIndicator indicator;
	private ActionBar actionBar;
	private String[] opciones;
	private List<Fragment> fragmentConcurso;
	private List<String> titulosConcurso;
	private List<Fragment> fragmentModalidades;
	private List<String> titulosModalidades;
	private List<Fragment> fragmentMasCarnaval;
	private List<String> titulosMasCarnaval;
	private List<Fragment> fragmentEnlaces;
	private List<String> titulosEnlaces;
	private Stack<Integer[]> pila = new Stack<Integer[]>();
	
	private final int IDX_OPCION = 0;
	private final int IDX_SUBMENU = 1;
	
	private final int OPCION_CONCURSO = 0;
	private final int OPCION_MODALIDADES = 1;
	private final int OPCION_MAS_CARNAVAL = 2;
	private final int OPCION_ENLACES = 3;
	private final int OPCION_PUNTOS_INTERES = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getSherlock().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		app = (CarnavappApplication) getApplication();
		
		configurarPageIndicator();
		configurarActionBar();
		configurarOpciones();
		configurarFragment();
		recuperarUltimaPila(); //Debe ser lo último
	}
	
	@SuppressWarnings("unchecked")
	private void recuperarUltimaPila(){
		//Si guardamos una pila, volvemos a donde nos indique
		Object ultimaPila = getLastCustomNonConfigurationInstance();
		if(ultimaPila!=null){
			pila = (Stack<Integer[]>) ultimaPila;
			Integer[] volverA = pila.peek();
			actionBar.setSelectedNavigationItem(volverA[IDX_OPCION]);
			pager.setCurrentItem(volverA[IDX_SUBMENU]);

		}
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return pila;
	}
	
	private void configurarFragment(){
		fragmentConcurso = new ArrayList<Fragment>();
		fragmentConcurso.add(ConcursoFragment.newInstance("Hoy"));
		fragmentConcurso.add(ConcursoFragment.newInstance("Calendario"));
		fragmentConcurso.add(ConcursoFragment.newInstance("Clasificación"));
		titulosConcurso = new ArrayList<String>();
		titulosConcurso.add(getString(R.string.hoy));
		titulosConcurso.add(getString(R.string.calendario));
		titulosConcurso.add(getString(R.string.clasificacion));

		fragmentEnlaces = new ArrayList<Fragment>();
		fragmentEnlaces.add(ConcursoFragment.newInstance("Blogs"));
		fragmentEnlaces.add(ConcursoFragment.newInstance("Diarios"));
		fragmentEnlaces.add(ConcursoFragment.newInstance("Otros"));
		titulosEnlaces = new ArrayList<String>();
		titulosEnlaces.add(getString(R.string.blogs));
		titulosEnlaces.add(getString(R.string.diarios));
		titulosEnlaces.add(getString(R.string.otros));

		fragmentModalidades = new ArrayList<Fragment>();
		fragmentModalidades.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_COMPARSA)));
		fragmentModalidades.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CHIRIGOTA)));
		fragmentModalidades.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CORO)));
		fragmentModalidades.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CUARTETO)));
		titulosModalidades = new ArrayList<String>();
		titulosModalidades.add(getString(R.string.comparsas));
		titulosModalidades.add(getString(R.string.chirigotas));
		titulosModalidades.add(getString(R.string.coros));
		titulosModalidades.add(getString(R.string.cuartetos));

		fragmentMasCarnaval = new ArrayList<Fragment>();
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Juveniles"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Infantiles"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Romanceros"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Callejeras"));
		titulosMasCarnaval = new ArrayList<String>();
		titulosMasCarnaval.add(getString(R.string.juveniles));
		titulosMasCarnaval.add(getString(R.string.infantiles));
		titulosMasCarnaval.add(getString(R.string.romanceros));
		titulosMasCarnaval.add(getString(R.string.callejeras));
	}
	
	private void configurarPageIndicator(){
		pager = (ViewPager)findViewById(R.id.pager);
		indicator = (TabPageIndicator)findViewById(R.id.indicator);
		
		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), null, null);
		pager.setAdapter(adapter);
		pager.setId(1);
		indicator.setViewPager(pager);
		indicator.setOnTabReselectedListener(new OnTabReselectedListener() {
			@Override
			public void onTabReselected(int position) {
				Integer[] ultimo = pila.pop();
				ultimo[IDX_SUBMENU] = position; //Actualizamos la posición
				pila.push(ultimo);
			}
		});
		
		indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
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
	
	private void configurarActionBar(){
		actionBar = getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
	}

	private void configurarOpciones(){
		opciones = new String[] {getResources().getString(R.string.concurso),
								 getResources().getString(R.string.modalidades),
								 getResources().getString(R.string.mas_carnaval),
								 getResources().getString(R.string.enlaces),
								 getResources().getString(R.string.puntos_de_interes)};
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.sherlock_spinner_item, opciones);
		actionBar.setListNavigationCallbacks(arrayAdapter, this);
		arrayAdapter.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
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
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		//Guardamos en la pila hacia donde vamos
		actualizarPila(itemPosition);
		
		List<Fragment> fragments = null;
		List<String> titulos = null;
		boolean mostrarOpciones = true;
		if(OPCION_CONCURSO == itemPosition){
			fragments = fragmentConcurso;
			titulos = titulosConcurso;
		}else if(OPCION_MODALIDADES == itemPosition){
			fragments = fragmentModalidades;
			titulos = titulosModalidades;
		}else if(OPCION_MAS_CARNAVAL == itemPosition){
			fragments = fragmentMasCarnaval;
			titulos = titulosMasCarnaval;
		}else if(OPCION_ENLACES == itemPosition){
			fragments = fragmentEnlaces;
			titulos = titulosEnlaces;
		}else if(OPCION_PUNTOS_INTERES == itemPosition){
			mostrarOpciones = false;
		}

		if(mostrarOpciones){
			pager.setVisibility(View.VISIBLE);
			indicator.setVisibility(View.VISIBLE);
			
			PagerAdapter adapter = (PagerAdapter) pager.getAdapter();
			adapter.setFragments(fragments);
			adapter.setTitulos(titulos);
			indicator.notifyDataSetChanged();
			indicator.setCurrentItem(pila.peek()[IDX_SUBMENU]);
			adapter.notifyDataSetChanged();
		}else{
			pager.setVisibility(View.GONE);
			indicator.setVisibility(View.GONE);
		}
		
		return false;
	}
	
	
	
	@Override
    public void onBackPressed() {
		if(pila!=null && !pila.isEmpty() && pila.size()>1){
			//Quitamos el último
			pila.pop();
			Integer[] volverA = pila.peek();
			actionBar.setSelectedNavigationItem(volverA[IDX_OPCION]);
			pager.setCurrentItem(volverA[IDX_SUBMENU]);
		}else{
	    	super.onBackPressed();
		}
    }
	
	private void cerrarAplicacion() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setMessage(R.string.salir_pregunta)
	    	.setCancelable(false)
	    	.setPositiveButton(R.string.si,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			finish();
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


}

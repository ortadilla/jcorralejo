package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;
import java.util.Date;
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
import android.widget.FrameLayout;
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
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.entidades.Video;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class CarnavappActivity3 extends SherlockFragmentActivity implements OnNavigationListener{

	private CarnavappApplication app;
	protected ViewPager pagerActivo;
	protected TabPageIndicator indicatorActivo;
	
	protected ViewPager pagerConcurso;
	protected TabPageIndicator indicatorConcurso;
	protected ViewPager pagerModalidades;
	protected TabPageIndicator indicatorModalidades;
	protected ViewPager pagerMas;
	protected TabPageIndicator indicatorMas;
	protected ViewPager pagerEnlaces;
	protected TabPageIndicator indicatorEnlaces;
	protected AgrupacionFragment agrupacionFragment;
	protected FrameLayout frameAgrupacion;
	protected WebFragment webFragment;
	protected FrameLayout frameWeb;
	protected ActuacionesFragment actuacionFragment;
	protected FrameLayout frameActuacion;
	
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
	
	boolean finalizar = false;
	
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
		
		comprobarApp();
		
		if(!finalizar){
			configurarFragment();
			configurarPageIndicator();
			configurarActionBar();
			configurarOpciones();
			recuperarUltimaPila(); //Debe ser lo último
		}else
			finalizar = false;
	}
	

	private void comprobarApp(){
		//Si volvemos a la app y se ha perdido toda la info, volvemos al principio
		if(app==null || app.getInfoAnios()==null || app.getInfoAnios().isEmpty()){
			finalizar = true;
			
			Intent intent = new Intent();
			intent.setClass(getApplicationContext(), MenuPrincipalActivity.class);
			startActivity(intent);	
			finish();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void recuperarUltimaPila(){
		//Si guardamos una pila, volvemos a donde nos indique
		Object ultimaPila = getLastCustomNonConfigurationInstance();
		if(ultimaPila!=null){
			pila = (Stack<Integer[]>) ultimaPila;
			Integer[] volverA = pila.peek();
			actionBar.setSelectedNavigationItem(volverA[IDX_OPCION]);
			pagerActivo.setCurrentItem(volverA[IDX_SUBMENU]);

		}
	}
	
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return pila;
	}
	
	private void configurarFragment(){
		if(fragmentConcurso==null || fragmentConcurso.isEmpty()){
			fragmentConcurso = new ArrayList<Fragment>();
			titulosConcurso = new ArrayList<String>();
			Date hoy = new Date();
			if(app.hoyHayConcurso(hoy)){
				fragmentConcurso.add(ActuacionesFragment.newInstance(app.obtenerAgrupacionesDia(hoy), app.obtenerTituloActuacion(hoy)));
				titulosConcurso.add(getString(R.string.hoy));
			}
			fragmentConcurso.add(CalendarioFragment.newInstance());
			fragmentConcurso.add(ConcursoFragment.newInstance("Clasificación"));
			titulosConcurso.add(getString(R.string.calendario));
			titulosConcurso.add(getString(R.string.clasificacion));
		}

		if(fragmentEnlaces==null || fragmentEnlaces.isEmpty()){
			fragmentEnlaces = new ArrayList<Fragment>();
			fragmentEnlaces.add(ConcursoFragment.newInstance("Blogs"));
			fragmentEnlaces.add(ConcursoFragment.newInstance("Diarios"));
			fragmentEnlaces.add(ConcursoFragment.newInstance("Otros"));
			titulosEnlaces = new ArrayList<String>();
			titulosEnlaces.add(getString(R.string.blogs));
			titulosEnlaces.add(getString(R.string.diarios));
			titulosEnlaces.add(getString(R.string.otros));
		}

		if(fragmentModalidades==null || fragmentModalidades.isEmpty()){
			fragmentModalidades = new ArrayList<Fragment>();
			fragmentModalidades.add(AgrupacionesFragment.newInstance(app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_COMPARSA), this));
			fragmentModalidades.add(AgrupacionesFragment.newInstance(app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CHIRIGOTA), this));
			fragmentModalidades.add(AgrupacionesFragment.newInstance(app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CORO), this));
			fragmentModalidades.add(AgrupacionesFragment.newInstance(app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CUARTETO), this));
			titulosModalidades = new ArrayList<String>();
			titulosModalidades.add(getString(R.string.comparsas));
			titulosModalidades.add(getString(R.string.chirigotas));
			titulosModalidades.add(getString(R.string.coros));
			titulosModalidades.add(getString(R.string.cuartetos));
		}
		
		if(fragmentMasCarnaval==null || fragmentMasCarnaval.isEmpty()){
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
		
		if(frameAgrupacion==null){
			frameAgrupacion = (FrameLayout) findViewById(R.id.fragmentAgrupacion);
			frameAgrupacion.setVisibility(View.GONE);
		}
		
		if(frameWeb==null){
			frameWeb = (FrameLayout) findViewById(R.id.fragmentWeb);
			frameWeb.setVisibility(View.GONE);
		}
		
		if(frameActuacion==null){
			frameActuacion = (FrameLayout) findViewById(R.id.fragmentActuacion);
			frameActuacion.setVisibility(View.GONE);
		}
		
	}
	
	private class TabReselectedListener implements OnTabReselectedListener{
		@Override
		public void onTabReselected(int position) {
			Integer[] ultimo = pila.pop();
			ultimo[IDX_SUBMENU] = position; //Actualizamos la posición
			pila.push(ultimo);
		}
	}
	
	private class PageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) { }

		@Override
		public void onPageSelected(int arg0) {
			Integer[] ultimo = pila.pop();
			ultimo[IDX_SUBMENU] = arg0; //Actualizamos la posición
			pila.push(ultimo);
		}
		
	}
	
	private void configurarPageIndicator(){
		
		TabReselectedListener tabReselectedListener = new TabReselectedListener();
		PageChangeListener pageChangeListener = new PageChangeListener();
		
		pagerConcurso = (ViewPager)findViewById(R.id.pagerConcurso);
		pagerConcurso.setId(R.id.pagerConcurso);
		indicatorConcurso = (TabPageIndicator)findViewById(R.id.indicatorConcurso);
		FragmentPagerAdapter adapterConcurso = new PagerAdapter(pagerConcurso, super.getSupportFragmentManager(), fragmentConcurso, titulosConcurso);
		pagerConcurso.setAdapter(adapterConcurso);
		indicatorConcurso.setViewPager(pagerConcurso);
		indicatorConcurso.setOnTabReselectedListener(tabReselectedListener);
		indicatorConcurso.setOnPageChangeListener(pageChangeListener);

		pagerModalidades = (ViewPager)findViewById(R.id.pagerModalidades);
		pagerModalidades.setId(R.id.pagerModalidades);
		indicatorModalidades = (TabPageIndicator)findViewById(R.id.indicatorModalidades);
		FragmentPagerAdapter adapterModalidades = new PagerAdapter(pagerModalidades, super.getSupportFragmentManager(), fragmentModalidades, titulosModalidades);
		pagerModalidades.setAdapter(adapterModalidades);
		indicatorModalidades.setViewPager(pagerModalidades);
		indicatorModalidades.setOnTabReselectedListener(tabReselectedListener);
		indicatorModalidades.setOnPageChangeListener(pageChangeListener);
		pagerModalidades.setVisibility(View.GONE);
		indicatorModalidades.setVisibility(View.GONE);
		
		pagerMas = (ViewPager)findViewById(R.id.pagerMas);
		pagerMas.setId(R.id.pagerMas);
		indicatorMas = (TabPageIndicator)findViewById(R.id.indicatorMas);
		FragmentPagerAdapter adapterMas = new PagerAdapter(pagerMas, super.getSupportFragmentManager(), fragmentMasCarnaval, titulosMasCarnaval);
		pagerMas.setAdapter(adapterMas);
		indicatorMas.setViewPager(pagerMas);
		indicatorMas.setOnTabReselectedListener(tabReselectedListener);
		indicatorModalidades.setOnPageChangeListener(pageChangeListener);
		pagerMas.setVisibility(View.GONE);
		indicatorMas.setVisibility(View.GONE);
		
		pagerEnlaces = (ViewPager)findViewById(R.id.pagerEnlaces);
		pagerEnlaces.setId(R.id.pagerEnlaces);
		indicatorEnlaces = (TabPageIndicator)findViewById(R.id.indicatorEnlaces);
		FragmentPagerAdapter adapterEnlaces = new PagerAdapter(pagerEnlaces, super.getSupportFragmentManager(), fragmentEnlaces, titulosEnlaces);
		pagerEnlaces.setAdapter(adapterEnlaces);
		indicatorEnlaces.setViewPager(pagerEnlaces);
		indicatorEnlaces.setOnTabReselectedListener(tabReselectedListener);
		indicatorEnlaces.setOnPageChangeListener(pageChangeListener);
		pagerEnlaces.setVisibility(View.GONE);
		indicatorEnlaces.setVisibility(View.GONE);
		
		pagerActivo = pagerConcurso;
		indicatorActivo = indicatorConcurso;
	}
	
	private void configurarActionBar(){
		if(actionBar==null){
			actionBar = getSupportActionBar();
			actionBar.setDisplayShowTitleEnabled(false);
			actionBar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
		}
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

		ocultarWeb();
		ocultarAgrupacion();
		ocultarActuacion();
		pagerActivo.setVisibility(View.GONE);
		indicatorActivo.setVisibility(View.GONE);
		if(OPCION_CONCURSO == itemPosition){
			pagerActivo = pagerConcurso;
			indicatorActivo = indicatorConcurso;
		}else if(OPCION_MODALIDADES == itemPosition){
			pagerActivo = pagerModalidades;
			indicatorActivo = indicatorModalidades;
		}else if(OPCION_MAS_CARNAVAL == itemPosition){
			pagerActivo = pagerMas;
			indicatorActivo = indicatorMas;
		}else if(OPCION_ENLACES == itemPosition){
			pagerActivo = pagerEnlaces;
			indicatorActivo = indicatorEnlaces;
		}else if(OPCION_PUNTOS_INTERES == itemPosition){
			//TODO
		}
		
		pagerActivo.setVisibility(View.VISIBLE);
		indicatorActivo.setVisibility(View.VISIBLE);
		
		return true;
	}
	
	
	
	@Override
    public void onBackPressed() {
		
		if(webFragment!=null && frameWeb!=null && frameWeb.getVisibility()==View.VISIBLE){
			ocultarWeb();

			pagerActivo.setVisibility(View.VISIBLE);
			indicatorActivo.setVisibility(View.VISIBLE);
			if(agrupacionFragment!=null)
				frameAgrupacion.setVisibility(View.VISIBLE);
			
		}else if(actuacionFragment!=null && frameActuacion!=null && frameActuacion.getVisibility()==View.VISIBLE){
			ocultarActuacion();

			pagerActivo.setVisibility(View.VISIBLE);
			indicatorActivo.setVisibility(View.VISIBLE);
		}
		
		else if(agrupacionFragment!=null && frameAgrupacion!=null && frameAgrupacion.getVisibility()==View.VISIBLE){
			ocultarAgrupacion();

			pagerActivo.setVisibility(View.VISIBLE);
			indicatorActivo.setVisibility(View.VISIBLE);
		}

		else{

			if(pila!=null && !pila.isEmpty() && pila.size()>1){
				//Quitamos el último
				pila.pop();
				Integer[] volverA = pila.peek();
				actionBar.setSelectedNavigationItem(volverA[IDX_OPCION]);
				pagerActivo.setCurrentItem(volverA[IDX_SUBMENU]);
			}else{
				super.onBackPressed();
			}
		}
    }
	
	private void ocultarAgrupacion(){
		if(agrupacionFragment!=null && frameAgrupacion!=null && frameAgrupacion.getVisibility()==View.VISIBLE){
			android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
			trans.remove(agrupacionFragment).commit();
			agrupacionFragment = null;
			frameAgrupacion.setVisibility(View.GONE);
		}		
	}
	
	private void ocultarWeb(){
		if(webFragment!=null && frameWeb!=null && frameWeb.getVisibility()==View.VISIBLE){
			android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
			trans.remove(webFragment).commit();
			webFragment = null;
			frameWeb.setVisibility(View.GONE);
		}		
	}
	
	private void ocultarActuacion(){
		if(actuacionFragment!=null && frameActuacion!=null && frameActuacion.getVisibility()==View.VISIBLE){
			android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
			trans.remove(actuacionFragment).commit();
			actuacionFragment = null;
			frameActuacion.setVisibility(View.GONE);
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
    
    public void verAgrupacion(Agrupacion agrupacion){
    	if(Integer.MIN_VALUE != agrupacion.getId()){
    		indicatorActivo.setVisibility(View.GONE);
    		pagerActivo.setVisibility(View.GONE);

    		frameAgrupacion.setVisibility(View.VISIBLE);
    		agrupacionFragment = AgrupacionFragment.newInstance(agrupacion);
    		android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
    		trans.add(android.R.id.content, agrupacionFragment);
    		trans.commit();
    	}
    }
    
    public void verVideo(Video video){
    	String url = video.getUrl();
    	if(url!=null && !url.equals("")){
    		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(video.getUrl()));
    		startActivity(i);
    	}
    }
    
    public void verURL(String url){
    	if(url!=null && !url.equals("")){
    		if(indicatorActivo!=null)
    			indicatorActivo.setVisibility(View.GONE);
    		if(pagerActivo!=null)
    			pagerActivo.setVisibility(View.GONE);
//    		if(agrupacionFragment!=null)
//    			frameAgrupacion.setVisibility(View.GONE);
    		
    		frameWeb.setVisibility(View.VISIBLE);
    		webFragment = WebFragment.newInstance(url);
    		android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
    		trans.add(android.R.id.content, webFragment);
    		trans.commit();
    	}
    }
    
    public void verActuacionesDia(Date dia){
    	if(app.hoyHayConcurso(dia)){
    		indicatorActivo.setVisibility(View.GONE);
    		pagerActivo.setVisibility(View.GONE);

    		frameActuacion.setVisibility(View.VISIBLE);
    		actuacionFragment =	ActuacionesFragment.newInstance(app.obtenerAgrupacionesDia(dia), app.obtenerTituloActuacion(dia));
    		android.support.v4.app.FragmentTransaction trans = super.getSupportFragmentManager().beginTransaction();
    		trans.add(android.R.id.content, actuacionFragment);
    		trans.commit();
    	}
    }
    


}

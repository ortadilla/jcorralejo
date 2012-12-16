package es.jcorralejo.android.carnavapp;

import java.util.ArrayList;
import java.util.List;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

public class CarnavappActivity3 extends SherlockFragmentActivity implements OnNavigationListener{

	private ViewPager pager;
	private TabPageIndicator indicator;
	private ActionBar actionBar;
	private String[] opciones;
	private List<NamedFragment> fragmentConcurso;
	private List<NamedFragment> fragmentModalidades;
	private List<NamedFragment> fragmentMasCarnaval;
	private List<NamedFragment> fragmentEnlaces;
	
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
		
		configurarPageIndicator();
		configurarActionBar();
		configurarOpciones();
		configurarFragment();
		onNavigationItemSelected(1, 1);

//		List<NamedFragment> fragments = new Vector<NamedFragment>();
//		fragments.add(ConcursoFragment.newInstance("Preeliminares"));
//		fragments.add(ConcursoFragment.newInstance("Cuartos de Final"));
//		fragments.add(ConcursoFragment.newInstance("Semifinales"));
//		fragments.add(ConcursoFragment.newInstance("Final"));
//		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
//
//		ViewPager pager = (ViewPager)findViewById(R.id.pager);
//		pager.setAdapter(adapter);
//
//		TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
//		indicator.setViewPager(pager);
	}
	
	private void configurarFragment(){
		fragmentConcurso = new ArrayList<NamedFragment>();
		fragmentConcurso.add(ConcursoFragment.newInstance("Hoy"));
		fragmentConcurso.add(ConcursoFragment.newInstance("Calendario"));
		fragmentConcurso.add(ConcursoFragment.newInstance("Clasificación"));

		fragmentEnlaces = new ArrayList<NamedFragment>();
		fragmentEnlaces.add(ConcursoFragment.newInstance("Blogs"));
		fragmentEnlaces.add(ConcursoFragment.newInstance("Diarios"));
		fragmentEnlaces.add(ConcursoFragment.newInstance("Otros"));

		fragmentModalidades = new ArrayList<NamedFragment>();
		fragmentModalidades.add(ConcursoFragment.newInstance("Comparsas"));
		fragmentModalidades.add(ConcursoFragment.newInstance("Chirigotas"));
		fragmentModalidades.add(ConcursoFragment.newInstance("Coros"));
		fragmentModalidades.add(ConcursoFragment.newInstance("Cuartetos"));

		fragmentMasCarnaval = new ArrayList<NamedFragment>();
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Juveniles"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Infantiles"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Romanceros"));
		fragmentMasCarnaval.add(ConcursoFragment.newInstance("Callejeras"));
	}
	
	private void configurarPageIndicator(){
		pager = (ViewPager)findViewById(R.id.pager);
		indicator = (TabPageIndicator)findViewById(R.id.indicator);
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

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		Toast.makeText(getBaseContext(), "Current Action : " + opciones[itemPosition]  , Toast.LENGTH_SHORT).show();
		
		List<NamedFragment> fragments = null;
		if(OPCION_CONCURSO == itemPosition){
			fragments = fragmentConcurso;
		}else if(OPCION_MODALIDADES == itemPosition){
			fragments = fragmentModalidades;
		}else if(OPCION_MAS_CARNAVAL == itemPosition){
			fragments = fragmentMasCarnaval;
		}else if(OPCION_ENLACES == itemPosition){
			fragments = fragmentEnlaces;
		}else if(OPCION_PUNTOS_INTERES == itemPosition){
			//No hay fragment
		}

		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), fragments);
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);

		return false;
	}


}

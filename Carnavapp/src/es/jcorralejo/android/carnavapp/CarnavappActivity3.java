package es.jcorralejo.android.carnavapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.holoeverywhere.app.AlertDialog;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

public class CarnavappActivity3 extends SherlockFragmentActivity implements OnNavigationListener{

	private ViewPager pager;
	private TabPageIndicator indicator;
	private ActionBar actionBar;
	private String[] opciones;
	private List<NamedFragment> fragmentConcurso;
	private List<NamedFragment> fragmentModalidades;
	private List<NamedFragment> fragmentMasCarnaval;
	private List<NamedFragment> fragmentEnlaces;
	private Stack<Integer> pila = new Stack<Integer>();
	
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
		
		FragmentPagerAdapter adapter = new PagerAdapter(super.getSupportFragmentManager(), new ArrayList<NamedFragment>());
		pager.setAdapter(adapter);
		indicator.setViewPager(pager);
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
		
		//Guardamos en la pila hacia donde vamos
		if(!pila.isEmpty()){
			//Si pulsan el anterior donde estuvimos eleminamos de la pila en el que estamos
			if(pila.size()>1){
				Integer penultimo = pila.get(pila.size()-2);
				if(penultimo.equals(itemPosition))
					pila.pop();
				else
					pila.push(itemPosition);
			}else
				pila.push(itemPosition);
		}else
			pila.push(itemPosition);
		
		List<NamedFragment> fragments = null;
		boolean mostrarOpciones = true;
		if(OPCION_CONCURSO == itemPosition){
			fragments = fragmentConcurso;
		}else if(OPCION_MODALIDADES == itemPosition){
			fragments = fragmentModalidades;
		}else if(OPCION_MAS_CARNAVAL == itemPosition){
			fragments = fragmentMasCarnaval;
		}else if(OPCION_ENLACES == itemPosition){
			fragments = fragmentEnlaces;
		}else if(OPCION_PUNTOS_INTERES == itemPosition){
			mostrarOpciones = false;
		}

		if(mostrarOpciones){
			pager.setVisibility(View.VISIBLE);
			indicator.setVisibility(View.VISIBLE);
			
			PagerAdapter adapter = (PagerAdapter) pager.getAdapter();
			adapter.setFragments(fragments);
			indicator.notifyDataSetChanged();
			indicator.setCurrentItem(0);
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
			Integer volverA = pila.peek();
			onNavigationItemSelected(volverA, volverA);
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
        if (item.getItemId() == R.id.buscar) {
            Toast.makeText(this, "Buscando..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.actualizar) {
        	Toast.makeText(this, "Actualizando..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.info) {
        	Toast.makeText(this, "Info..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.salir) {
        	cerrarAplicacion();
        }
        
        return true;
    }


}

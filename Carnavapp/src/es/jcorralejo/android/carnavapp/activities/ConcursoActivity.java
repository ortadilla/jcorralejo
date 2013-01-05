package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;

import es.jcorralejo.android.carnavapp.R;

public class ConcursoActivity extends CarnavappActivity implements OnNavigationListener{

	protected void configurarFragment(){
		if(fragment==null || fragment.isEmpty()){
			fragment = new ArrayList<Fragment>();
			fragment.add(ConcursoFragment.newInstance("Hoy"));
			fragment.add(ConcursoFragment.newInstance("Calendario"));
			fragment.add(ConcursoFragment.newInstance("Clasificación"));
			titulos = new ArrayList<String>();
			titulos.add(getString(R.string.hoy));
			titulos.add(getString(R.string.calendario));
			titulos.add(getString(R.string.clasificacion));
		}
	}
	
	@Override
	protected int getOpcionSeleccionada() {
		return OPCION_CONCURSO;
	}
}

package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;

import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.ActionBar.OnNavigationListener;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class ModalidadesActivity extends CarnavappActivity implements OnNavigationListener{

	@Override
	protected int getOpcionSeleccionada() {
		return OPCION_MODALIDADES;
	}
	
	protected void configurarFragment(){
		if(fragment==null || fragment.isEmpty()){
			fragment = new ArrayList<Fragment>();
			fragment.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_COMPARSA), null));
			fragment.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CHIRIGOTA), null));
			fragment.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CORO), null));
			fragment.add(AgrupacionesFragment.newInstance(app.getInfoAnioActual().getConcurso().getModalidades().get(Constantes.MODALIDAD_CUARTETO), null));
			titulos = new ArrayList<String>();
			titulos.add(getString(R.string.comparsas));
			titulos.add(getString(R.string.chirigotas));
			titulos.add(getString(R.string.coros));
			titulos.add(getString(R.string.cuartetos));
		}
	}

}

package es.jcorralejo.android.carnavapp.activities;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.R.layout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ConcursoFragment extends NamedFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(container==null)
			return null;
		return (LinearLayout)inflater.inflate(R.layout.concurso, container, false);
	}
	
	public static ConcursoFragment newInstance(String titulo) {
		ConcursoFragment concursoFragment = new ConcursoFragment();
		concursoFragment.setTitulo(titulo);
	    return concursoFragment;
	}
	
	
	
}

package es.jcorralejo.android.carnavapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import es.jcorralejo.android.carnavapp.R;

public class ConcursoFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(container==null)
			return null;
		LinearLayout v = (LinearLayout)inflater.inflate(R.layout.concurso, container, false);
//		Button buton = (Button) v.findViewById(R.id.button1);
//		buton.setText(titulo);
		return v;
	}
	
	public static ConcursoFragment newInstance() {
		ConcursoFragment concursoFragment = new ConcursoFragment();
	    return concursoFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	
	
}

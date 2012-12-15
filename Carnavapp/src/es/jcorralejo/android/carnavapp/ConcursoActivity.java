package es.jcorralejo.android.carnavapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class ConcursoActivity extends SherlockFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(container==null)
			return null;
		return (LinearLayout)inflater.inflate(R.layout.concurso, container, false);

	}
	
	
}

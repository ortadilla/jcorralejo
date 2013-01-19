package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Enlace;

public class EnlacesFragment extends ListFragment {
	
	private ListView listaEnlces;
	private List<Enlace> enlaces = new ArrayList<Enlace>();
	private LayoutInflater miInflater;
	private CarnavappApplication app;
	
	public EnlacesFragment() {
	}
	
	public static EnlacesFragment newInstance(List<Enlace> enlaces) {
		EnlacesFragment enlacesFragment = new EnlacesFragment(enlaces);
	    return enlacesFragment;
	}
	
	public EnlacesFragment(List<Enlace> enlaces) {
		this.enlaces = enlaces;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (CarnavappApplication) getActivity().getApplication();
		miInflater = LayoutInflater.from(getActivity());
		setRetainInstance(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.actuaciones_list, container, false);
		
		// Registramos la lista de lugares para definir su menú contextual
	    listaEnlces = (ListView)view.findViewById(android.R.id.list);
		registerForContextMenu(listaEnlces);
		
		configurarAdapter();
	    
	    return view;
	}
	
	public void configurarAdapter() {
		if(listaEnlces!=null){
		listaEnlces.setAdapter(new ArrayAdapter<Enlace>(getActivity(), R.layout.elemento_agrupacion, enlaces) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
                if (null == convertView) {
                        row = miInflater.inflate(R.layout.elemento_agrupacion, null);
                } else {
                        row = convertView;
                }
 
                Enlace item = getItem(position);
                TextView nombre = (TextView) row.findViewById(R.id.elementoAgrupacion);
                nombre.setText(item.getDescripcion());
                return row;
			}
			
			@Override
			public int getCount() {
				return enlaces!=null ? enlaces.size() : 0;
			}
			
		});
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Enlace enlace = (Enlace) l.getItemAtPosition(position);
		((CarnavappActivity3)getActivity()).verURL(enlace.getUrl());
	}


	
}

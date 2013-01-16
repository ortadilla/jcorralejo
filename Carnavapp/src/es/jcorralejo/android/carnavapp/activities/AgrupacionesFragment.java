package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;

public class AgrupacionesFragment extends ListFragment {
	
	private ListView listaAgrupaciones;
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private LayoutInflater miInflater;
	private CarnavappApplication app;
	private CarnavappActivity3 carnavappActivity3;
	
	public AgrupacionesFragment() {
	}
	
	public static AgrupacionesFragment newInstance(List<Agrupacion> agrupaciones, CarnavappActivity3 carnavappActivity3) {
		AgrupacionesFragment agrupacionesFragment = new AgrupacionesFragment(agrupaciones, carnavappActivity3);
	    return agrupacionesFragment;
	}
	
	public AgrupacionesFragment(List<Agrupacion> agrupaciones, CarnavappActivity3 carnavappActivity3) {
		this.agrupaciones = agrupaciones;
		this.carnavappActivity3 = carnavappActivity3;
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
		View view = inflater.inflate(R.layout.agrupaciones_list, container, false);
		
		// Registramos la lista de lugares para definir su menú contextual
	    listaAgrupaciones = (ListView)view.findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);

		configurarAdapter();
	    
	    return view;
	}
	
	public void configurarAdapter() {
		if(listaAgrupaciones!=null){
		listaAgrupaciones.setAdapter(new ArrayAdapter<Agrupacion>(getActivity(), R.layout.agrupaciones_item, agrupaciones) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row = null == convertView ? miInflater.inflate(R.layout.agrupaciones_item, null) : convertView;
		 
				Agrupacion agrupacion = getItem(position);
				
				TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
				nombre.setText(agrupacion.getNombre());
				
				ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
				fav.setImageResource(R.drawable.ic_fav);
				fav.setVisibility(agrupacion.isCabezaSerie() || app.getFavoritas().contains(agrupacion.getId()) ? View.VISIBLE : View.GONE);
				
				TextView datosExtras = (TextView) row.findViewById(R.id.agrDatosExtra);
				if(agrupacion.getInfo()!=null && !agrupacion.getInfo().equals("")){
					datosExtras.setText(agrupacion.getInfo());
					datosExtras.setVisibility(View.VISIBLE);
				}else{
					datosExtras.setText(null);
					datosExtras.setVisibility(View.GONE);
				}
				
				TextView otrosAniosText = (TextView) row.findViewById(R.id.agrOtrosAnios);
				String otrosAnios = "";
				if(agrupacion.getOtrosAnios()!=null){
					for(Agrupacion agrAnterior : agrupacion.getOtrosAnios())
						otrosAnios += "\n"+agrAnterior.getAnio()+": "+agrAnterior.getNombre();
					otrosAnios = otrosAnios.substring(1);
				}
				if(otrosAnios.equals(""))
					otrosAnios = getString(R.string.sin_datos_otras_ediciones);
				otrosAniosText.setText(otrosAnios);
		 
				return row;
			}
			
			@Override
			public int getCount() {
				return agrupaciones!=null ? agrupaciones.size() : 0;
			}

			
		});
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Agrupacion agrupacion = (Agrupacion) l.getItemAtPosition(position);
		((CarnavappActivity3)getActivity()).verAgrupacion(agrupacion);
	}

	
}

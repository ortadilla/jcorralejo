package es.jcorralejo.android.carnavapp.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.entidades.Video;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class ActuacionesFragment extends ListFragment {
	
	private ListView listaAgrupaciones;
	private List<Agrupacion> agrupaciones = new ArrayList<Agrupacion>();
	private String titulo;
	private LayoutInflater miInflater;
	private CarnavappApplication app;
	
	public ActuacionesFragment() {
	}
	
	public static ActuacionesFragment newInstance(List<Agrupacion> agrupaciones, String titulo) {
		ActuacionesFragment agrupacionesFragment = new ActuacionesFragment(agrupaciones, titulo);
	    return agrupacionesFragment;
	}
	
	public ActuacionesFragment(List<Agrupacion> agrupaciones, String titulo) {
		this.agrupaciones = agrupaciones;
		this.titulo = titulo;
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
	    listaAgrupaciones = (ListView)view.findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
		
		TextView tituloActuacion = (TextView) view.findViewById(R.id.tituloActuacion);
		if(tituloActuacion!=null && !tituloActuacion.equals(""))
			tituloActuacion.setText(titulo);
		else 
			tituloActuacion.setVisibility(View.GONE);
		
		ImageView canalSur = (ImageView) view.findViewById(R.id.directoCanalSur);
		canalSur.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((CarnavappActivity3)getActivity()).verURL(Constantes.URL_CANAL_ANDALUCIA);
			}
		});

		ImageView ondaCadiz = (ImageView) view.findViewById(R.id.directoOndaCadiz);
		ondaCadiz.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Video video = new Video(null, Constantes.URL_ONDA_CADIZ);
				((CarnavappActivity3)getActivity()).verVideo(video);
			}
		});
		
		configurarAdapter();
	    
	    return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
//		configurarAdapter();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		configurarAdapter();
	}
	
	public void configurarAdapter() {
		if(listaAgrupaciones!=null){
		listaAgrupaciones.setAdapter(new ArrayAdapter<Agrupacion>(getActivity(), R.layout.actuaciones_item, agrupaciones) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
                if (null == convertView) {
                        row = miInflater.inflate(R.layout.actuaciones_item, null);
                } else {
                        row = convertView;
                }
 
                Agrupacion item = getItem(position);
                TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
                nombre.setText(item.getNombre());
                if(Constantes.TEXTO_DESCANSO.equals(item.getNombre()) && convertView!=null){
//                        nombre.setBackgroundColor(Color.GRAY);
//                        nombre.setTextColor(Color.BLACK);
                }
                
                ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
                fav.setImageResource(R.drawable.ic_fav);
                fav.setVisibility(item.isCabezaSerie() || app.getFavoritas().contains(item.getId()) ? View.VISIBLE : View.GONE);
                TextView modLoc = (TextView) row.findViewById(R.id.agrModalidadLocalidad);
                String textModLoc = "";
                if(item.getModalidad()!=null && !item.getModalidad().equals(""))
                	textModLoc += item.getModalidad();
                if(item.getLocalidad()!=null && !item.getLocalidad().equals(""))
                	textModLoc += " ("+item.getLocalidad()+")";
                if(!textModLoc.equals("")){
                	modLoc.setText(textModLoc);
                	modLoc.setVisibility(View.VISIBLE);
                }else{
                	modLoc.setText(null);
                	modLoc.setVisibility(View.GONE);
                }
                TextView infoExtra = (TextView) row.findViewById(R.id.agrDatosExtra);
                if(item.getInfo()!=null && !item.getInfo().equals("")){
                	infoExtra.setText(item.getInfo());
                	infoExtra.setVisibility(View.VISIBLE);
                }else{
                	infoExtra.setText(null);
                	infoExtra.setVisibility(View.GONE);
                }
 
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

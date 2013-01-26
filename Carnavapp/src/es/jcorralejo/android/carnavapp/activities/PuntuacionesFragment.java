package es.jcorralejo.android.carnavapp.activities;

import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class PuntuacionesFragment extends ListFragment {
	
	private ListView listaAgrupaciones;
	private LayoutInflater miInflater;
	private CarnavappApplication app;
	private TextView comparsas;
	private TextView chirigotas;
	private TextView coros;
	private TextView cuartetos;
	private TextView modalidadActiva;
	
	private List<Agrupacion> agrupaciones;
	private List<Agrupacion> agrupacionesComparsas;
	private List<Agrupacion> agrupacionesChirigotas;
	private List<Agrupacion> agrupacionesCoros;
	private List<Agrupacion> agrupacionesCuartetos;
	
	public PuntuacionesFragment() {
	}
	
	public static PuntuacionesFragment newInstance() {
		PuntuacionesFragment puntuacionesFragment = new PuntuacionesFragment();
	    return puntuacionesFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (CarnavappApplication) getActivity().getApplication();
		miInflater = LayoutInflater.from(getActivity());
		setRetainInstance(true);
		
		((CarnavappActivity3)getActivity()).comprobarApp();
		
		agrupacionesComparsas = app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_COMPARSA);
		agrupacionesChirigotas = app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CHIRIGOTA);
		agrupacionesCoros = app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CORO);
		agrupacionesCuartetos = app.obtenerAgrupacionesOrdenadasAlfabeticamente(Constantes.MODALIDAD_CUARTETO);
		
		agrupaciones = agrupacionesComparsas;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.puntuaciones_list, container, false);
		
		// Registramos la lista de lugares para definir su menú contextual
	    listaAgrupaciones = (ListView)view.findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
		
		comparsas = (TextView) view.findViewById(R.id.puntuacionComparsas);
		comparsas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modalidadActiva.setTypeface(null, Typeface.NORMAL);
				comparsas.setTypeface(null, Typeface.BOLD);
				modalidadActiva = comparsas;
				agrupaciones = agrupacionesComparsas;
				
				configurarAdapter();
			}
		});
		comparsas.setTypeface(null, Typeface.BOLD);
		modalidadActiva = comparsas;
		
		chirigotas = (TextView) view.findViewById(R.id.puntuacionChirigotas);
		chirigotas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modalidadActiva.setTypeface(null, Typeface.NORMAL);
				chirigotas.setTypeface(null, Typeface.BOLD);
				modalidadActiva = chirigotas;
				agrupaciones = agrupacionesChirigotas;
				
				configurarAdapter();
			}
		});
		coros = (TextView) view.findViewById(R.id.puntuacionCoros);
		coros.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modalidadActiva.setTypeface(null, Typeface.NORMAL);
				coros.setTypeface(null, Typeface.BOLD);
				modalidadActiva = coros;
				agrupaciones = agrupacionesCoros;
				
				configurarAdapter();
			}
		});
		cuartetos = (TextView) view.findViewById(R.id.puntuacionCuartetos);
		cuartetos.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				modalidadActiva.setTypeface(null, Typeface.NORMAL);
				cuartetos.setTypeface(null, Typeface.BOLD);
				modalidadActiva = cuartetos;
				agrupaciones = agrupacionesCuartetos;
				
				configurarAdapter();
			}
		});

		configurarAdapter();
	    
	    return view;
	}
	
	public void configurarAdapter() {
		if(listaAgrupaciones!=null){
		listaAgrupaciones.setAdapter(new ArrayAdapter<Agrupacion>(getActivity(), R.layout.puntuaciones_list, agrupaciones) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
				if (null == convertView) {
					row = miInflater.inflate(R.layout.puntuacion_item, null);
				} else {
					row = convertView;
				}
 
                Agrupacion item = getItem(position);
                TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
                nombre.setText(item.getNombre());

                TextView puntuacion = (TextView) row.findViewById(R.id.agrPuntuacion);
                puntuacion.setText(item.getPuntos()!=null ? item.getPuntos() : "-");
 
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

package es.jcorralejo.android.carnavapp.activities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
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
import es.jcorralejo.android.carnavapp.utils.Constantes;


public class AgrupacionesActivity extends ListActivity{

	private List<Agrupacion> agrupaciones;
	private LayoutInflater miInflater;
	private CarnavappApplication app;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrupaciones_list);
		
		miInflater = LayoutInflater.from(this);
		app = (CarnavappApplication) getApplication();
		
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			agrupaciones = (List<Agrupacion>) extras.get(Constantes.PARAMETRO_AGRUPACIONES);
			Collections.sort(agrupaciones, new Comparator<Agrupacion>() {
				@Override
				public int compare(Agrupacion obj1, Agrupacion obj2) {
		    		String nombre1 = obj1.getNombre();
					String nombre2= obj2.getNombre();
		    		return nombre1.compareTo(nombre2);
				}
			});
		}
		
		configurarAdapter();
		
		// Registramos la lista de lugares para definir su menú contextual
		ListView listaAgrupaciones = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
	}
	
	public void configurarAdapter() {
		setListAdapter(new ArrayAdapter<Agrupacion>(this, R.layout.agrupaciones_item, agrupaciones) {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row = null == convertView ? miInflater.inflate(R.layout.agrupaciones_item, null) : convertView;
		 
				Agrupacion agrupacion = getItem(position);
				
				TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
				nombre.setText(agrupacion.getNombre());
				
				ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
				fav.setImageResource(R.drawable.ic_fav);
				fav.setVisibility(agrupacion.isCabezaSerie() || app.getFavoritas().contains(agrupacion.getId()) ? View.VISIBLE : View.GONE);
				
				TextView datosExtras = (TextView) row.findViewById(R.id.agrDatosExtras);
				if(agrupacion.getInfo()!=null && !agrupacion.getInfo().equals("")){
					datosExtras.setText(agrupacion.getInfo());
					datosExtras.setVisibility(View.VISIBLE);
				}else{
					datosExtras.setText(null);
					datosExtras.setVisibility(View.GONE);
				}
				
				TextView coac2011 = (TextView) row.findViewById(R.id.agrOtrosAnios);
				String otrosAnios = "";
				if(agrupacion.getOtrosAnios()!=null){
					for(Agrupacion agrAnterior : agrupacion.getOtrosAnios())
						otrosAnios += "\n"+agrAnterior.getAnio()+": "+agrAnterior.getNombre();
				}
				if(otrosAnios.equals(""))
					otrosAnios = getString(R.string.sin_datos_otras_ediciones);
				coac2011.setText(otrosAnios);
		 
				return row;
			}
			
		});
	}
	

}

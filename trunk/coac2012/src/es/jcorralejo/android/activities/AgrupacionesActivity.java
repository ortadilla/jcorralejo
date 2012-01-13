package es.jcorralejo.android.activities;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.R;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.utils.Constantes;

public class AgrupacionesActivity extends ListActivity{

	private List<Agrupacion> agrupaciones;
	private LayoutInflater miInflater;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrupaciones_list);
		
		miInflater = LayoutInflater.from(this);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			agrupaciones = (List<Agrupacion>) extras.get(Constantes.PARAMETRO_AGRUPACIONES_MODALIDAD);
			Collections.sort(agrupaciones, new ComparatorArticulos());
		}
		
		configurarAdapter();
		
		// Registramos la lista de lugares para definir su menú contextual
		ListView listaAgrupaciones = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
	}
	
	private static final class ComparatorArticulos implements Comparator<Agrupacion>{
    	public int compare(Agrupacion obj1, Agrupacion obj2) {
    		String nombre1 = ((Agrupacion)obj1).getNombre();
			String nombre2= ((Agrupacion)obj2).getNombre();
    		return nombre1.compareTo(nombre2);
    	}
    }; 
	
	public void configurarAdapter() {
		setListAdapter(new ArrayAdapter<Agrupacion>(this, R.layout.agrupaciones_item, agrupaciones) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
				if (null == convertView) {
					row = miInflater.inflate(R.layout.agrupaciones_item, null);
				} else {
					row = convertView;
				}
		 
				Agrupacion item = getItem(position);
				TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
				nombre.setText(item.getNombre());
				ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
				fav.setImageResource(R.drawable.ic_fav);
				fav.setVisibility(item.isCabezaSerie() ? View.VISIBLE : View.GONE);
				TextView datosExtras = (TextView) row.findViewById(R.id.agrDatosExtras);
				if(item.getInfo()!=null && !item.getInfo().equals("")){
					datosExtras.setText(item.getInfo());
					datosExtras.setVisibility(View.VISIBLE);
				}else{
					datosExtras.setText(null);
					datosExtras.setVisibility(View.GONE);
				}
				TextView coac2011 = (TextView) row.findViewById(R.id.agrCOAC2011);
				coac2011.setText("COAC2011: "+(item.getCoac2011()!=null && !item.getCoac2011().equals("") ? item.getCoac2011() : "No participó"));
		 
				return row;
			}
			
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent();
		i.setClass(getApplicationContext(), AgrupacionActivity.class);
		i.putExtra(Constantes.PARAMETRO_AGRUPACION, (Agrupacion)l.getItemAtPosition(position));
		startActivity(i);
	}
	
	
}

package es.jcorralejo.android.activities;

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
		if(extras!=null)
			agrupaciones = (List<Agrupacion>) extras.get(Constantes.AGRUPACIONES_MODALIDAD);
		
		configurarAdapter();
		
		// Registramos la lista de lugares para definir su menú contextual
		ListView listaAgrupaciones = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
	}
	
	
	
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
		 
				TextView nombre = (TextView) row.findViewById(R.id.agrNombre);
				Agrupacion item = getItem(position);
				nombre.setText(item.getNombre());
				ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
				fav.setImageResource(R.drawable.ic_fav);
				TextView datosExtras = (TextView) row.findViewById(R.id.agrDatosExtras);
				datosExtras.setText(item.getNombre());
				TextView coac2011 = (TextView) row.findViewById(R.id.agrCOAC2011);
				coac2011.setText("COAC2011: "+item.getCoac2011());
		 
				return row;
			}
		});
	}
	
}

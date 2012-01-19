package es.jcorralejo.android.coac2012.activities;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import es.jcorralejo.android.coac2012.R;
import es.jcorralejo.android.coac2012.entidades.Enlace;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class EnlacesActivity extends ListActivity{

	private List<Enlace> enlaces;
	private LayoutInflater miInflater;
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enlaces_list);
		
		miInflater = LayoutInflater.from(this);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			enlaces = (List<Enlace>) extras.get(Constantes.PARAMETRO_ENLACES);
		}
		
		configurarAdapter();
		
		// Registramos la lista de lugares para definir su menú contextual
		ListView listaAgrupaciones = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(listaAgrupaciones);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		configurarAdapter();
	}
	
	public void configurarAdapter() {
		setListAdapter(new ArrayAdapter<Enlace>(this, R.layout.enlaces_item, enlaces) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
				if (null == convertView) {
					row = miInflater.inflate(R.layout.enlaces_item, null);
				} else {
					row = convertView;
				}
		 
				Enlace item = getItem(position);
				TextView nombre = (TextView) row.findViewById(R.id.enlaceDesc);
				nombre.setText(item.getDescripcion());
				
				return row;
			}
			
			@Override
			public int getCount() {
				return enlaces!=null ? enlaces.size() : 0;
			}
			
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Enlace enlace = (Enlace)l.getItemAtPosition(position);
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(enlace.getUrl()));
		startActivity(i);
	}
	
	
	
}

package es.jcorralejo.android.coac2012.activities;

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
import es.jcorralejo.android.coac2012.CoacApplication;
import es.jcorralejo.android.coac2012.entidades.Agrupacion;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class ActuacionActivity extends ListActivity{

	private List<Agrupacion> agrupaciones;
	private LayoutInflater miInflater;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actuaciones_list);
		
		miInflater = LayoutInflater.from(this);
		String textoDia = "";
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			agrupaciones = (List<Agrupacion>) extras.get(Constantes.PARAMETRO_AGRUPACIONES);
			textoDia = extras.getString(Constantes.PARAMETRO_TEXTO_DIA);
		}
		
		TextView dia = (TextView) findViewById(R.id.actDia);
		dia.setText(textoDia);
		
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
		setListAdapter(new ArrayAdapter<Agrupacion>(this, R.layout.actuaciones_item, agrupaciones) {
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
				ImageView fav = (ImageView) row.findViewById(R.id.agrFav);
				fav.setImageResource(R.drawable.ic_fav);
				CoacApplication app = (CoacApplication) getApplication();
				fav.setVisibility(item.isCabezaSerie() || app.getFavoritas().contains(item.getId()) ? View.VISIBLE : View.GONE);
				TextView modLoc = (TextView) row.findViewById(R.id.agrModLoc);
				if(item.getModalidad()!=null && !item.getModalidad().equals("")
				&& item.getLocalidad()!=null && !item.getLocalidad().equals("")){
					modLoc.setText(item.getModalidad()+" ("+item.getLocalidad()+")");
					modLoc.setVisibility(View.VISIBLE);
				}else{
					modLoc.setText(null);
					modLoc.setVisibility(View.GONE);
				}
				TextView infoExtra = (TextView) row.findViewById(R.id.agrDatosExtras);
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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Agrupacion agr = (Agrupacion)l.getItemAtPosition(position);
		if(agr.getId()>0){
			Intent i = new Intent();
			i.setClass(getApplicationContext(), AgrupacionActivity.class);
			i.putExtra(Constantes.PARAMETRO_AGRUPACION, agr);
			startActivity(i);
		}
	}
	
	
}

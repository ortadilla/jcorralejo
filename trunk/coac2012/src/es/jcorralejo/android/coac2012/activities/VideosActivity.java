package es.jcorralejo.android.coac2012.activities;

import java.util.Collections;
import java.util.Comparator;
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
import es.jcorralejo.android.R;
import es.jcorralejo.android.coac2012.entidades.Agrupacion;
import es.jcorralejo.android.coac2012.entidades.Video;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class VideosActivity extends ListActivity{

	private List<Video> videos;
	private LayoutInflater miInflater;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videos_list);
		
		miInflater = LayoutInflater.from(this);
		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			videos = (List<Video>) extras.get(Constantes.PARAMETRO_VIDEOS);
			Collections.sort(videos, new ComparatorVideos());
		}
		
		configurarAdapter();
		
		// Registramos la lista de lugares para definir su men� contextual
		ListView listaVideos = (ListView) findViewById(android.R.id.list);
		registerForContextMenu(listaVideos);
	}
	
	private static final class ComparatorVideos implements Comparator<Video>{
    	public int compare(Video obj1, Video obj2) {
    		String nombre1 = ((Video)obj1).getDescripcion();
			String nombre2= ((Video)obj2).getDescripcion();
    		return nombre1.compareTo(nombre2);
    	}
    }; 
	
	public void configurarAdapter() {
		setListAdapter(new ArrayAdapter<Video>(this, R.layout.videos_item, videos) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View row;
				if (null == convertView) {
					row = miInflater.inflate(R.layout.videos_item, null);
				} else {
					row = convertView;
				}
		 
				Video item = getItem(position);
				TextView descripcion = (TextView) row.findViewById(R.id.videoDesc);
				descripcion.setText(item.getDescripcion());
		 
				return row;
			}
			
		});
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent i = new Intent("android.intent.action.VIEW", Uri.parse(((Video)l.getItemAtPosition(position)).getUrl()));
		startActivity(i);
	}
	
	
}

package es.jcorralejo.android.carnavapp.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.entidades.Comentario;
import es.jcorralejo.android.carnavapp.entidades.Componente;
import es.jcorralejo.android.carnavapp.entidades.Entidad;
import es.jcorralejo.android.carnavapp.entidades.Video;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class AgrupacionFragment extends Fragment{
	
	CarnavappApplication app;
	private Agrupacion agrupacion;
	private LayoutInflater miInflater;
	
	public AgrupacionFragment() {
	}
	
	public static AgrupacionFragment newInstance(Agrupacion agrupacion) {
		AgrupacionFragment agrupacionFragment = new AgrupacionFragment(agrupacion);
	    return agrupacionFragment;
	}
	
	public AgrupacionFragment(Agrupacion agrupacion) {
		this.agrupacion = agrupacion;
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
		
		View view = inflater.inflate(R.layout.agrupacion, container, false);
		
		if(agrupacion!=null){
			TextView nombre = (TextView) view.findViewById(R.id.agrNombre);
			nombre.setText(agrupacion.getNombre());
			TextView director = (TextView) view.findViewById(R.id.agrDirector);
			director.setText(agrupacion.getDirector());
			TextView autor = (TextView) view.findViewById(R.id.agrAutor);
			autor.setText(agrupacion.getAutor());
			TextView modalidad = (TextView) view.findViewById(R.id.agrModalidad);
			modalidad.setText(agrupacion.getModalidad());
			TextView localidad = (TextView) view.findViewById(R.id.agrLocalidad);
			localidad.setText(agrupacion.getLocalidad());

			ImageView agrFav = (ImageView) view.findViewById(R.id.agrFav);
			agrFav.setVisibility(agrupacion.isCabezaSerie() || app.getFavoritas().contains(agrupacion.getId()) ? View.VISIBLE : View.GONE);
			
			ImageView imagen = (ImageView) view.findViewById(R.id.agrImagen);
			imagen.setAdjustViewBounds(true);
			imagen.setScaleType(ScaleType.CENTER_INSIDE);
			if(agrupacion.getUrl_foto()!=null && !agrupacion.getUrl_foto().equals("")){
				Bitmap imagenAgr = downloadFile(agrupacion.getUrl_foto());
				if(imagenAgr!=null)
					imagen.setImageBitmap(imagenAgr);
				else
					imagen.setImageResource(R.drawable.no_imagen_agrupacion);
			}else
				imagen.setImageResource(R.drawable.no_imagen_agrupacion);
			
			TextView componentes = (TextView) view.findViewById(R.id.agrComponentes);
			if(agrupacion.getComponentes()!=null && !agrupacion.getComponentes().isEmpty()){
				String comps = "";
				for(Componente comp : agrupacion.getComponentes()){
					if(comp.getNombre()!=null && !comp.getNombre().equals(""))
						comps += "\n"+comp.getNombre()+" ("+comp.getVoz()+")";
				}
				if(!comps.equals(""))
					comps = comps.substring(1);
				componentes.setText(comps);
				componentes.setVisibility(comps!=null && !comps.equals("") ? View.VISIBLE : View.GONE);
			}else{
				componentes.setVisibility(View.GONE);
				componentes.setText(null);
			}
			
			TextView otrosAniosText = (TextView) view.findViewById(R.id.agrOtrosAnios);
			String otrosAnios = "";
			if(agrupacion.getOtrosAnios()!=null){
				for(Agrupacion agrAnterior : agrupacion.getOtrosAnios())
					otrosAnios += "\n "+agrAnterior.getAnio()+" - "+agrAnterior.getNombre();
				otrosAnios = otrosAnios.substring(1);
			}
			if(otrosAnios.equals(""))
				otrosAnios = getString(R.string.sin_datos_otras_ediciones);
			otrosAniosText.setText(otrosAnios);
			
//			http://trivedihardik.wordpress.com/2011/09/19/scrollview-inside-scrollview-scrolling-problem/
			
			ListView comentarios = (ListView) view.findViewById(R.id.comentarios);
			if(agrupacion.getComentarios()!=null && !agrupacion.getComentarios().isEmpty()){
				Comentario[] comentariosArray = agrupacion.getComentarios().toArray(new Comentario[agrupacion.getComentarios().size()]);
				ElementoAgrupacionAdapter adapter = new ElementoAgrupacionAdapter(getActivity(), R.layout.elemento_agrupacion, comentariosArray);
				comentarios.setAdapter(adapter);
			}else{
				comentarios.setVisibility(View.GONE);
			}
			
			ListView videos = (ListView) view.findViewById(R.id.videos);
			if(agrupacion.getVideos()!=null && !agrupacion.getVideos().isEmpty()){
				Video[] videosArray = agrupacion.getVideos().toArray(new Video[agrupacion.getVideos().size()]);
				ElementoAgrupacionAdapter adapter = new ElementoAgrupacionAdapter(getActivity(), R.layout.elemento_agrupacion, videosArray);
				videos.setAdapter(adapter);
			}else{
				videos.setVisibility(View.GONE);
			}
		}
		
		return view;
	}
	
    private Bitmap downloadFile(String imageHttpAddress) {
    	Bitmap loadedImage = null;
    	try {
    		File root = Environment.getExternalStorageDirectory();
    		File folder = new File(root, Constantes.PATH_IMAGENES);
    		//Si no existe, creamos la carpeta
    		if(!folder.exists())
    		    folder.mkdirs();
    		
    		//Antes de nada comprobamos si existe la imagen en la tarjeta
    		String[] split = imageHttpAddress.split("/");
    		String nombre = split[split.length-1];
    		File file = new File(folder,nombre);
    		if (file.exists()) {
    			//Tomamos la imagen de la SD
    			loadedImage = BitmapFactory.decodeFile(file.getAbsolutePath());
    		}else{
    			URL imageUrl = new URL(imageHttpAddress);
    			HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
    			conn.connect();
    			InputStream is = conn.getInputStream();
				loadedImage = BitmapFactory.decodeStream(is);
    			conn.disconnect();

    			//Guardamos la imagen en la SD
    			OutputStream f = null;
    			try {
    			    f = new FileOutputStream(file);
    			    loadedImage.compress(Bitmap.CompressFormat.JPEG, 100, f);
    			    f.close();
    			} catch (Exception e) {
    			    e.printStackTrace();
    			}
    		}
        } catch (IOException e) {
            Toast.makeText(getActivity().getApplicationContext(), "Error cargando imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return loadedImage;
    }



    private class ElementoAgrupacionAdapter extends ArrayAdapter<Entidad>{
    	
    	Entidad[] elementos ;
    	Context context;
    	
		public ElementoAgrupacionAdapter(Context context, int textViewResourceId, Entidad[] elementos) {
			super(context, textViewResourceId, elementos);
			this.context = context;
			this.elementos = elementos;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null == convertView ? miInflater.inflate(R.layout.elemento_agrupacion, null) : convertView;
			
			String text = null;
			Object elemento = elementos[position];
			if(elemento!=null){
				if(elemento instanceof Comentario)
					text = ((Comentario)elemento).getOrigen();
					else if(elemento instanceof Video)
						text = ((Video)elemento).getDescripcion();
			}
			
			TextView textView = (TextView) view.findViewById(R.id.elementoAgrupacion);
			if(text!=null)
				textView.setText(text);
			else
				view.setVisibility(View.GONE);
			
			return view;
		}
    	
    }
}

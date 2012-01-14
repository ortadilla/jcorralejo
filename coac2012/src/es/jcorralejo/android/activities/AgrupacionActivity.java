package es.jcorralejo.android.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.CoacApplication;
import es.jcorralejo.android.R;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.entidades.Comentario;
import es.jcorralejo.android.entidades.Componente;
import es.jcorralejo.android.entidades.Video;
import es.jcorralejo.android.utils.Constantes;

public class AgrupacionActivity extends Activity{
	
	private Agrupacion agrupacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrupacion);
		
		agrupacion = (Agrupacion) getIntent().getSerializableExtra(Constantes.PARAMETRO_AGRUPACION);
		if(agrupacion!=null){
			TextView nombre = (TextView) findViewById(R.id.agrNombre);
			nombre.setText(agrupacion.getNombre());
			TextView director = (TextView) findViewById(R.id.agrDirector);
			director.setText(agrupacion.getDirector());
			TextView autor = (TextView) findViewById(R.id.agrAutor);
			autor.setText(agrupacion.getAutor());
			TextView modalidad = (TextView) findViewById(R.id.agrModalidad);
			modalidad.setText(agrupacion.getModalidad());
			TextView localidad = (TextView) findViewById(R.id.agrLocalidad);
			localidad.setText(agrupacion.getLocalidad());
			TextView coac2011 = (TextView) findViewById(R.id.agrCOAC2011);
			coac2011.setText(agrupacion.getCoac2011()!=null && !agrupacion.getCoac2011().equals("") ? agrupacion.getCoac2011() : "No participó");

			CoacApplication app = (CoacApplication) getApplication();
			LinearLayout agrFav = (LinearLayout) findViewById(R.id.agrFav);
			agrFav.setVisibility(agrupacion.isCabezaSerie() || app.getFavoritas().contains(agrupacion.getId()) ? View.VISIBLE : View.GONE);
			
			ImageView imagen = (ImageView) findViewById(R.id.agrImagen);
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
			
			TextView componentes = (TextView) findViewById(R.id.agrComponentes);
			if(agrupacion.getComponentes()!=null && !agrupacion.getComponentes().isEmpty()){
				componentes.setVisibility(View.VISIBLE);
				String comps = "";
				for(Componente comp : agrupacion.getComponentes())
					comps += "\n"+comp.getNombre()+" ("+comp.getVoz()+")";
				comps = comps.substring(1);
				componentes.setText(comps);
			}else{
				componentes.setVisibility(View.GONE);
				componentes.setText(null);
			}
		}
		
		ImageView btnFicha = (ImageView) findViewById(R.id.btnFicha);
		btnFicha.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					accionFicha();
				}
			}
		);
		ImageView btnVideos = (ImageView) findViewById(R.id.btnVideos);
		btnVideos.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						accionVideos();
					}
				}
		);
		ImageView btnComent = (ImageView) findViewById(R.id.btnComent);
		btnComent.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						accionComent();
					}
				}
		);
		ImageView btnFav = (ImageView) findViewById(R.id.btnFav);
		btnFav.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						accionFav();
					}
				}
		);
	}
	
    private Bitmap downloadFile(String imageHttpAddress) {
    	Bitmap loadedImage = null;
    	try {
    		File root = Environment.getExternalStorageDirectory();
    		File folder = new File(root,Constantes.PATH_IMAGENES);
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
            Toast.makeText(getApplicationContext(), "Error cargando imagen: "+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return loadedImage;
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.agrupacion, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.agrFichaCC:
				accionFicha();
				return true;
			case R.id.agrVideos:
				accionVideos();
				return true;
			case R.id.agrFavoritos:
				accionFav();
				return true;
			case R.id.agrComentarios:
				accionComent();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void accionFicha(){
		if(agrupacion.getUrl_cc()!=null && !agrupacion.getUrl_cc().equals("")){
			Intent i = new Intent("android.intent.action.VIEW", Uri.parse(agrupacion.getUrl_cc()));
			startActivity(i);
		}else
			Toast.makeText(getApplicationContext(), "Agrupación sin Ficha en www.carnavaldecadiz.com", Toast.LENGTH_LONG).show();
	}
	
	private void accionVideos(){
		if(agrupacion.getVideos()!=null && !agrupacion.getVideos().isEmpty()){
			Intent i = new Intent();
			i.setClass(getApplicationContext(), VideosActivity.class);
			i.putExtra(Constantes.PARAMETRO_VIDEOS, (ArrayList<Video>)agrupacion.getVideos());
			startActivity(i);
		}else{
			if(agrupacion.getUrl_videos()!=null && !agrupacion.getUrl_videos().equals("")){
				Toast.makeText(getApplicationContext(), "Vídeos de la Agrupación no disponibles. Buscando en Youtube...", Toast.LENGTH_LONG).show();
				Intent i = new Intent("android.intent.action.VIEW", Uri.parse(agrupacion.getUrl_videos()));
				startActivity(i);
			}else
				Toast.makeText(getApplicationContext(), "No se han encontrado vídeos de la Agrupación", Toast.LENGTH_LONG).show();
		}
	}
	
	private void accionFav(){
		CoacApplication app = (CoacApplication) getApplication();
		LinearLayout agrFav = (LinearLayout) findViewById(R.id.agrFav);
		if(app.getFavoritas().contains(agrupacion.getId())){
			if(!agrupacion.isCabezaSerie()){
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' ha dejado de ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
				app.getFavoritas().remove((Object)agrupacion.getId());
				agrFav.setVisibility(View.GONE);
			}else{
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' es cabeza de serie y no puede dejar de ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
			}
		}else{
			if(!agrupacion.isCabezaSerie()){
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' ha pasado a ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
				app.getFavoritas().add(agrupacion.getId());
				agrFav.setVisibility(View.VISIBLE);
			}else{
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' es cabeza de serie y ya es una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private void accionComent(){
		if(agrupacion.getComentarios()!=null && !agrupacion.getComentarios().isEmpty()){
			Intent i = new Intent();
			i.setClass(getApplicationContext(), ComentariosActivity.class);
			i.putExtra(Constantes.PARAMETRO_COMENTARIOS, (ArrayList<Comentario>)agrupacion.getComentarios());
			startActivity(i);
		}else{
			Toast.makeText(getApplicationContext(), "No se han encontrado comentarios de la Agrupación", Toast.LENGTH_LONG).show();
		}
	}

}

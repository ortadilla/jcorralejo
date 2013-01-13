package es.jcorralejo.android.carnavapp.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

import es.jcorralejo.android.carnavapp.R;
import es.jcorralejo.android.carnavapp.app.CarnavappApplication;
import es.jcorralejo.android.carnavapp.entidades.Agrupacion;
import es.jcorralejo.android.carnavapp.entidades.Componente;
import es.jcorralejo.android.carnavapp.utils.Constantes;

public class AgrupacionActivity extends SherlockActivity{
	
	CarnavappApplication app;
	private Agrupacion agrupacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.agrupacion);
		
		app = (CarnavappApplication) getApplication();
		ActionBar ab = getSupportActionBar();
		ab.setDisplayShowTitleEnabled(false); 
		ab.setDisplayShowHomeEnabled(false);
		
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

			ImageView agrFav = (ImageView) findViewById(R.id.agrFav);
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
		}
		
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
    
	private void accionFav(){
		ImageView agrFav = (ImageView) findViewById(R.id.agrFav);
		if(app.getFavoritas().contains(agrupacion.getId())){
			if(!agrupacion.isCabezaSerie()){
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' ha dejado de ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
				app.getFavoritas().remove((Object)agrupacion.getId());
				guardarFav();
				agrFav.setVisibility(android.view.View.GONE);
			}else{
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' es cabeza de serie y no puede dejar de ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
			}
		}else{
			if(!agrupacion.isCabezaSerie()){
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' ha pasado a ser una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
				app.getFavoritas().add(agrupacion.getId());
				guardarFav();
				agrFav.setVisibility(View.VISIBLE);
			}else{
				Toast.makeText(getApplicationContext(), "'"+agrupacion.getNombre()+"' es cabeza de serie y ya es una de las agrupaciones favoritas", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private void guardarFav(){
		String favoritas = "";
		for(Integer fav : app.getFavoritas())
			favoritas += fav+"|";
		
		SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString(Constantes.PREFERENCE_FAVORITAS, favoritas);
		editor.commit();
	}
	
}

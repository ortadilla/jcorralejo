package es.jcorralejo.android.activities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.entidades.Componente;
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
			TextView localidad = (TextView) findViewById(R.id.agrLocalidad);
			localidad.setText(agrupacion.getLocalidad());
			TextView coac2011 = (TextView) findViewById(R.id.agrCOAC2011);
			coac2011.setText(agrupacion.getCoac2011()!=null && !agrupacion.getCoac2011().equals("") ? agrupacion.getCoac2011() : "No participó");
			
			LinearLayout agrFav = (LinearLayout) findViewById(R.id.agrFav);
			agrFav.setVisibility(agrupacion.isCabezaSerie() ? View.VISIBLE : View.GONE);
			
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
	}
	
    private Bitmap downloadFile(String imageHttpAddress) {
    	Bitmap loadedImage = null;
        try {
        	URL imageUrl = new URL(imageHttpAddress);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();
            loadedImage = BitmapFactory.decodeStream(conn.getInputStream());
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
				if(agrupacion.getUrl_cc()!=null && !agrupacion.getUrl_cc().equals("")){
					Intent i = new Intent("android.intent.action.VIEW", Uri.parse(agrupacion.getUrl_cc()));
					startActivity(i);
				}else
					Toast.makeText(getApplicationContext(), "En estos momentos se puede acceder a la Ficha de carnavaldecadiz.com", Toast.LENGTH_LONG).show();
				return true;
			case R.id.agrVideos:
				if(agrupacion.getUrl_videos()!=null && !agrupacion.getUrl_videos().equals("")){
					Intent i = new Intent("android.intent.action.VIEW", Uri.parse(agrupacion.getUrl_videos()));
					startActivity(i);
				}else
					Toast.makeText(getApplicationContext(), "En estos momentos se puede acceder a los vídeos de la aplicación", Toast.LENGTH_LONG).show();
				return true;
			case R.id.agrFavoritos:
				Toast.makeText(getApplicationContext(), "Utilidad de disponible en esta versión", Toast.LENGTH_LONG).show();
				return true;
			case R.id.agrComentarios:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

}

package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import aplicacion.AutodefinidosApplication;
import entidades.Tablero;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;
import es.jcorralejo.android.autodefinidos.utilities.TablerosPredefinidos;

public class JuegoActivity extends Activity {
	
	private AutodefinidosApplication app;
	private int dificultad;
	private int tamanio;
	private Tablero tablero;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        app = (AutodefinidosApplication) getApplication();
        
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
        	dificultad = extras.getInt(Constantes.DIFICULTAD);
        	tamanio = extras.getInt(Constantes.TAMANIO);
        }
        
        crearTablero();
	}
	
	private void crearTablero(){
		//TODO
		tablero = TablerosPredefinidos.tableroPequenioUno;
		tablero.imprimir(false);
	}

}

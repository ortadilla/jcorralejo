package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;

public class MenuActivity extends Activity {
	
	private AutodefinidosApplication app;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        
        app = (AutodefinidosApplication) getApplication();

        TextView botonNuevoJuego = (TextView) findViewById(R.id.botonNuevoJuego);
        botonNuevoJuego.setTypeface(app.getFuenteApp());
        botonNuevoJuego.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			//Navegamos a la configuración de la partida
        			Intent intent = new Intent();
        			intent.setClass(getApplicationContext(), ConfigurarJuegoActivity.class);
        			startActivity(intent);
                }
        	}
        );

        
        TextView botonContinuarJuego = (TextView) findViewById(R.id.botonContinuarJuego);
        botonContinuarJuego.setTypeface(app.getFuenteApp());
        botonContinuarJuego.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			if(app.isFree()){
        				//TODO: Levantar popUp para indicar que en esta versión no se puede guardar partidas
        			}else{
        				//TODO: Cargar última partida
        			}
                }
        	}
        );
        
        TextView botonOpciones = (TextView) findViewById(R.id.botonOpciones);
        botonOpciones.setTypeface(app.getFuenteApp());
        botonOpciones.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			//TODO: Abrir actividad de las opciones: Guardar automáticamente,
        			//estadísticas (tiempo, partidas, palabras, etc.), "ayudas", 
                }
        	}
        );
        
        TextView botonSalir = (TextView) findViewById(R.id.botonSalir);
        botonSalir.setTypeface(app.getFuenteApp());
        botonSalir.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			//TODO: Guardar antes todo lo necesario
        			onBackPressed();
                }
        	}
        );
        
        
    }
    
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(R.string.salir_pregunta)
	    	.setCancelable(false)
	    	.setPositiveButton(R.string.si,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			cerrarAplicacion();
		    		}
		    	})
	    	.setNegativeButton(R.string.no,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			dialog.cancel();
		    		}
		    	});

    	AlertDialog alert = builder.create();
    	alert.show();
    }

    private void cerrarAplicacion() {
    	android.os.Process.killProcess(android.os.Process.myPid());
    }

}
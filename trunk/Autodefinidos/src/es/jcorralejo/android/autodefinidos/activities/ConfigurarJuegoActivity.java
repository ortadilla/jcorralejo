package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;

public class ConfigurarJuegoActivity extends Activity implements OnSeekBarChangeListener {
	
	private AutodefinidosApplication app;
	
	private TextView descDificultad;
	private TextView descTamanio;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.configurar_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        app = (AutodefinidosApplication) getApplication();
        
        TextView textoDificultad = (TextView) findViewById(R.id.textoDificultad);
        textoDificultad.setTypeface(app.getFuenteApp());

        descDificultad = (TextView) findViewById(R.id.descDificultad);
        descDificultad.setTypeface(app.getFuenteApp());
        
        SeekBar dificultad = (SeekBar) findViewById(R.id.dificultad);
        dificultad.setOnSeekBarChangeListener(this);
        dificultad.setClickable(true);
        dificultad.setOnTouchListener(new OnTouchListener() {
			@SuppressWarnings("deprecation")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(app.isFree()){
					showDialog(Constantes.DIALOG_VERSION_PAGO);
					return true;
				}
				return false;
			}
		});
        
        TextView textoTamanio = (TextView) findViewById(R.id.textoTamanio);
        textoTamanio.setTypeface(app.getFuenteApp());

        descTamanio = (TextView) findViewById(R.id.descTamanio);
        descTamanio.setTypeface(app.getFuenteApp());
        
        SeekBar tamanio = (SeekBar) findViewById(R.id.tamanio);
        tamanio.setOnSeekBarChangeListener(this);
        tamanio.setOnTouchListener(new OnTouchListener() {
			@SuppressWarnings("deprecation")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(app.isFree()){
					showDialog(Constantes.DIALOG_VERSION_PAGO);
					return true;
				}
				return false;
			}
		});


	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case Constantes.DIALOG_VERSION_PAGO:
			VersionCompleta alert = new VersionCompleta(this);
			TextView obtener = (TextView) alert.findViewById(R.id.obtenerPRO);
			obtener.setOnClickListener(
	        	new OnClickListener() {
	        		public void onClick(View v) {
	        			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.URL_VERSION_PAGO));
						startActivity(i);
	                }
	        	}
	        );
			TextView noObtener = (TextView) alert.findViewById(R.id.enOtroMomento);
			noObtener.setOnClickListener(
	        	new OnClickListener() {
	        		public void onClick(View v) {
	        			finish();
	                }
	        	}
	        );
			return alert;
		default:
			return null;
		}
	}

	
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
		switch (seekBar.getId())
		{
		 case R.id.dificultad:
			 descDificultad.setText(progress==0 ? R.string.facil : progress==1 ? R.string.normal : R.string.dificil);
			 break;
		 case R.id.tamanio:
			 descTamanio.setText(progress==0 ? R.string.paquenio : progress==1 ? R.string.mediano : R.string.grande);
			 break;
		}
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {}
}

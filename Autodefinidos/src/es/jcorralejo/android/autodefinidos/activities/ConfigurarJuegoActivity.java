package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;

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
        dificultad.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(app.isFree()){
					//TODO: Levantar popUp para indicar que en esta versi�n no se puede modificar la dificultad
				}
			}});
        
        TextView textoTamanio = (TextView) findViewById(R.id.textoTamanio);
        textoTamanio.setTypeface(app.getFuenteApp());

        descTamanio = (TextView) findViewById(R.id.descTamanio);
        descTamanio.setTypeface(app.getFuenteApp());
        
        SeekBar tamanio = (SeekBar) findViewById(R.id.tamanio);
        tamanio.setOnSeekBarChangeListener(this);
        tamanio.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(app.isFree()){
					//TODO: Levantar popUp para indicar que en esta versi�n no se puede modificar el tama�o
				}
			}});


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

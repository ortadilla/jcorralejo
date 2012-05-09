package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;

public class ConfigurarJuegoActivity extends Activity {
	
	private AutodefinidosApplication app;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.configurar_juego);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        app = (AutodefinidosApplication) getApplication();
        
        TextView textoDificultad = (TextView) findViewById(R.id.textoDificultad);
        textoDificultad.setTypeface(app.getFuenteApp());


	}
}

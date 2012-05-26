package es.jcorralejo.android.autodefinidos.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;

public class VersionCompleta extends AlertDialog {
	
	private Context context;
	
	protected VersionCompleta(Context context) {
		super(context);
		this.context = context;
	}

	private AutodefinidosApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//        setContentView(R.layout.version_completa);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        app = (AutodefinidosApplication) context.getApplicationContext();
        
        TextView textoObtenerPRO = (TextView) findViewById(R.id.textoVersionPago);
        textoObtenerPRO.setTypeface(app.getFuenteApp());

        TextView obtener = (TextView) findViewById(R.id.obtenerPRO);
        obtener.setTypeface(app.getFuenteApp());

        TextView noObtener = (TextView) findViewById(R.id.enOtroMomento);
        noObtener.setTypeface(app.getFuenteApp());
	}
}

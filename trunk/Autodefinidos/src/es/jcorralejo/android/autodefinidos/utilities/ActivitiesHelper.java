package es.jcorralejo.android.autodefinidos.utilities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import aplicacion.AutodefinidosApplication;
import es.jcorralejo.android.autodefinidos.R;

public class ActivitiesHelper {
	
	public static Dialog levantarPopupPago(AutodefinidosApplication app, final Context context){
		
		Dialog alert = new Dialog(context);
		alert.setContentView(R.layout.version_completa);
		alert.setTitle(R.string.app_name);
		TextView obtener = (TextView) alert.findViewById(R.id.obtenerPRO);
		obtener.setTypeface(app.getFuenteApp());
		obtener.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constantes.URL_VERSION_PAGO));
					context.startActivity(i);
                }
        	}
        );
		TextView noObtener = (TextView) alert.findViewById(R.id.enOtroMomento);
		noObtener.setTypeface(app.getFuenteApp());
		noObtener.setOnClickListener(
        	new OnClickListener() {
        		public void onClick(View v) {
        			((Activity)context).dismissDialog(Constantes.DIALOG_VERSION_PAGO);
                }
        	}
        );
		
		TextView textoObtenerPRO = (TextView) alert.findViewById(R.id.textoVersionPago);
		textoObtenerPRO.setTypeface(app.getFuenteApp());

		alert.show();
		return alert;
		
	}

}

package es.jcorralejo.android.coac2012.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import es.jcorralejo.android.coac2012.R;
import es.jcorralejo.android.coac2012.CoacApplication;
import es.jcorralejo.android.coac2012.entidades.Agrupacion;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class ModalidadesActivity extends Activity{
	private CoacApplication app;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modalidades);
		
		app = (CoacApplication)getApplication();
		
		TextView comparsas = (TextView) findViewById(R.id.comparsas);
		comparsas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarModalidad(Constantes.MODALIDAD_COMPARSA);
				}
			}
		);
		TextView chirigotas = (TextView) findViewById(R.id.chirigotas);
		chirigotas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarModalidad(Constantes.MODALIDAD_CHIRIGOTA);
				}
			}
		);
		TextView coros = (TextView) findViewById(R.id.coros);
		coros.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarModalidad(Constantes.MODALIDAD_CORO);
				}
			}
		);
		TextView cuartetos = (TextView) findViewById(R.id.cuartetos);
		cuartetos.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarModalidad(Constantes.MODALIDAD_CUARTETO);
				}
			}
		);
	}
	
	private void navegarModalidad(String modalidad){
		Intent i = new Intent();
//		Bundle b = new Bundle();
//		b.putSerializable(Constantes.PARAMETRO_AGRUPACIONES_MODALIDAD, (ArrayList<Agrupacion>)app.getModalidades().get(modalidad));
//		i.putExtras(b);
		i.putExtra(Constantes.PARAMETRO_AGRUPACIONES, (ArrayList<Agrupacion>)app.getModalidades().get(modalidad));
		i.setClass(getApplicationContext(), AgrupacionesActivity.class);
		startActivity(i);
	}
	
	

}
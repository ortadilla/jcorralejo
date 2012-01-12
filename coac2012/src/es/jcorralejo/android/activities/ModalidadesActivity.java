package es.jcorralejo.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import es.jcorralejo.android.CoacApplication;
import es.jcorralejo.android.R;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.utils.Constantes;

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
		Bundle b = new Bundle();
		b.putSerializable(Constantes.AGRUPACIONES_MODALIDAD, (ArrayList<Agrupacion>)app.getModalidades().get(modalidad));
		i.putExtras(b);
		i.setClass(getApplicationContext(), AgrupacionesActivity.class);
		startActivity(i);
	}
	
	

}

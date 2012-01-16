package es.jcorralejo.android.coac2012.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import es.jcorralejo.android.R;
import es.jcorralejo.android.coac2012.CoacApplication;
import es.jcorralejo.android.coac2012.entidades.Agrupacion;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class ConcursoActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concurso);
		
		TextView preeliminares = (TextView) findViewById(R.id.preeliminares);
		preeliminares.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarFase(Constantes.FASE_PREELIMINAR);
				}
			}
		);
		TextView semis = (TextView) findViewById(R.id.semis);
		semis.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarFase(Constantes.FASE_SEMIS);
				}
			}
		);
		TextView cuartos = (TextView) findViewById(R.id.cuartos);
		cuartos.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarFase(Constantes.FASE_CUARTOS);
				}
			}
		);
		TextView granFinal = (TextView) findViewById(R.id.granFinal);
		granFinal.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					navegarFase(Constantes.FASE_FINAL);
				}
			}
		);
	}
	
	private void navegarFase(String fase){
		Intent i = new Intent();
		i.putExtra(Constantes.PARAMETRO_FASE, fase);
		i.setClass(getApplicationContext(), DiasFaseActivity.class);
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.concurso, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.concursoClasif:
				Intent i = new Intent();
				CoacApplication app = (CoacApplication) getApplication();
				i.putExtra(Constantes.PARAMETRO_AGRUPACIONES, (ArrayList<Agrupacion>)app.getAgrupaciones());
				i.setClass(getApplicationContext(), OrdenActivity.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	
	

}

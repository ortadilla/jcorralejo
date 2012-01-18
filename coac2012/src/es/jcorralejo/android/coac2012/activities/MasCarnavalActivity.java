package es.jcorralejo.android.coac2012.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;

public class MasCarnavalActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mas_carnaval);
		
		TextView comparsas = (TextView) findViewById(R.id.infantilesJuveniles);
		comparsas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Apartado no disponible en esta versión", Toast.LENGTH_LONG).show();
				}
			}
		);
		TextView chirigotas = (TextView) findViewById(R.id.callejeras);
		chirigotas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Apartado no disponible en esta versión", Toast.LENGTH_LONG).show();
				}
			}
		);
		TextView coros = (TextView) findViewById(R.id.romanceros);
		coros.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Apartado no disponible en esta versión", Toast.LENGTH_LONG).show();
				}
			}
		);
		TextView cuartetos = (TextView) findViewById(R.id.enlaces);
		cuartetos.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(), EnlacesTipoActivity.class);
					startActivity(intent);
				}
			}
		);
	}
	
	

}

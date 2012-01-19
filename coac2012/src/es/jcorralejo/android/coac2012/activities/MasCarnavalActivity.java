package es.jcorralejo.android.coac2012.activities;

import java.util.HashSet;
import java.util.Set;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import es.jcorralejo.android.R;

public class MasCarnavalActivity extends Activity{
	
	private AdView adView1;
	private AdView adView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mas_carnaval);
		
		cargarAnuncios();
		
		TextView comparsas = (TextView) findViewById(R.id.infantilesJuveniles);
		comparsas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Próximamente disponible......", Toast.LENGTH_LONG).show();
				}
			}
		);
		TextView chirigotas = (TextView) findViewById(R.id.callejeras);
		chirigotas.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Próximamente disponible......", Toast.LENGTH_LONG).show();
				}
			}
		);
		TextView coros = (TextView) findViewById(R.id.romanceros);
		coros.setOnClickListener(
			new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(), "Próximamente disponible......", Toast.LENGTH_LONG).show();
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
	

	private void cargarAnuncios(){
		Set<String> key = new HashSet<String>();
		key.add("Carnaval"); 
		key.add("Cádiz"); 
		key.add("Comparsa"); 
		key.add("Chirigota"); 
		key.add("Coro"); 
		key.add("Cuarteto"); 
		key.add("Febrero"); 

		AdRequest r1 = new AdRequest();
		r1.setKeywords(key);
		adView1 = (AdView) findViewById(R.id.ad1);
	    adView1.loadAd(r1);

	    AdRequest r2 = new AdRequest();
	    r1.setKeywords(key);
	    adView2 = (AdView) findViewById(R.id.ad2);
	    adView2.loadAd(r2);
	}
	
	@Override
	public void onDestroy() {
		adView1.destroy();
		adView2.destroy();
		super.onDestroy();
	}

}

package es.jcorralejo.android.ejemplos.mapsview;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class EjemploMapsActivity extends MapActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}
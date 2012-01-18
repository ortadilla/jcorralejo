package es.jcorralejo.android.coac2012.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import es.jcorralejo.android.R;
import es.jcorralejo.android.coac2012.CoacApplication;
import es.jcorralejo.android.coac2012.entidades.Enlace;
import es.jcorralejo.android.coac2012.utils.Constantes;

public class EnlacesTipoActivity extends TabActivity{

	static private TabHost mTabHost;
	private Resources mResources;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fase);
		mTabHost = getTabHost();       
		mResources = getResources();
		
		CoacApplication app = (CoacApplication) getApplication();
		for(String tipo : app.getEnlaces().keySet()){
			addTab(tipo, tipo, app.getEnlaces().get(tipo));
		}
		
		mTabHost.setCurrentTab(0);   
	}	
	private void addTab(String tipo, String textoTipo, List<Enlace> enlaces) {
		Intent intent = new Intent(this, EnlacesActivity.class);
		intent.putExtra(Constantes.PARAMETRO_ENLACES, (ArrayList<Enlace>)enlaces);
		TabSpec spec = mTabHost.newTabSpec(tipo);
		spec.setIndicator(tipo);
		spec.setContent(intent);
		mTabHost.addTab(spec);
	}
}

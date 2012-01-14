package es.jcorralejo.android.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import es.jcorralejo.android.CoacApplication;
import es.jcorralejo.android.R;
import es.jcorralejo.android.entidades.Agrupacion;
import es.jcorralejo.android.utils.Constantes;

public class DiasFaseActivity extends TabActivity{

	static private TabHost mTabHost;
	private Resources mResources;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fase);
		mTabHost = getTabHost();       
		mResources = getResources();
		for(int i=0; i<7;i++){
		addTabUno();
		addTabDos();
		}
		mTabHost.setCurrentTab(0);   
	}	
	private void addTabUno() {
		Intent intent = new Intent(this, ActuacionActivity.class);
		CoacApplication app = (CoacApplication) getApplication();
		intent.putExtra(Constantes.PARAMETRO_AGRUPACIONES, (ArrayList<Agrupacion>)app.getCalendario().get("21/02/2012"));
		TabSpec spec = mTabHost.newTabSpec("21/01/2012");
		spec.setIndicator("21/01");
		spec.setContent(intent);
		mTabHost.addTab(spec);
	}
	private void addTabDos() {
		Intent intent = new Intent(this, ActuacionActivity.class);
		CoacApplication app = (CoacApplication) getApplication();
		intent.putExtra(Constantes.PARAMETRO_AGRUPACIONES, (ArrayList<Agrupacion>)app.getCalendario().get("22/02/2012"));
		TabSpec spec = mTabHost.newTabSpec("22/01/2012");
		spec.setIndicator("22/01");
		spec.setContent(intent);
		mTabHost.addTab(spec);
	}
}

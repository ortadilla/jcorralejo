package es.jcorralejo.android.activities;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import es.jcorralejo.android.R;

public class DiasFaseActivity extends TabActivity{

	static private TabHost mTabHost;
	private Resources mResources;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fase);
		mTabHost = getTabHost();       
		mResources = getResources();
//		for(int i=0; i<15; i++)
			addTabUno();
		mTabHost.setCurrentTab(0);   
	}	
	private void addTabUno() {
		Intent intent = new Intent(this, ActuacionActivity.class);
		TabSpec spec = mTabHost.newTabSpec("22/01/2012");
		spec.setIndicator("Tab1", mResources.getDrawable(android.R.drawable.ic_menu_agenda));
		spec.setContent(intent);
		mTabHost.addTab(spec);
	}
}

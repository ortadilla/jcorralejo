package es.jcorralejo.android.carnavapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class CarnavappActivity extends SherlockActivity implements TabListener{
	
	private ActionBar actionBar;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        
        Tab tabConcurso = actionBar.newTab();
        tabConcurso.setText("Concurso");
        tabConcurso.setTabListener(this);
        actionBar.addTab(tabConcurso);
        
        Tab tabModalidades = actionBar.newTab();
        tabModalidades.setText("Modalidades");
        tabModalidades.setTabListener(this);
        actionBar.addTab(tabModalidades);

        Tab tabMas = actionBar.newTab();
        tabMas.setText("Modalidades");
        tabMas.setTabListener(this);
        actionBar.addTab(tabMas);
        
        http://developer.android.com/design/patterns/swipe-views.html
    	http://www.buzzingandroid.com/2012/11/tab-swipe-between-fragments-using-actionbarsherlock/
    }
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	 MenuInflater inflater = getSupportMenuInflater();
         inflater.inflate(R.menu.carnavapp, menu);
         return true;
    }
    
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buscar) {
            Toast.makeText(this, "Buscando..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.actualizar) {
        	Toast.makeText(this, "Actualizando..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.info) {
        	Toast.makeText(this, "Info..", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.salir) {
        	Toast.makeText(this, "Saliendo..", Toast.LENGTH_SHORT).show();
        }
        
        return true;
    }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.i("EO", "onTabSelected");
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.i("EO", "onTabUnselected");
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.i("EO", "onTabReselected");
		
	}
}
package es.jcorralejo.android.carnavapp;

import org.holoeverywhere.app.AlertDialog;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class CarnavappActivity extends SherlockFragmentActivity{
	
	private ActionBar actionBar;
	String[] opciones;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	getSherlock().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        
        opciones = new String[] {getResources().getString(R.string.concurso),
				        		 getResources().getString(R.string.modalidades),
				        		 getResources().getString(R.string.mas_carnaval)};
        actionBar.setNavigationMode(com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_LIST);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.sherlock_spinner_item, opciones);
        ActionBar.OnNavigationListener navigationListener = new OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int itemPosition, long itemId) {
                Toast.makeText(getBaseContext(), "Current Action : " + opciones[itemPosition]  , Toast.LENGTH_SHORT).show();
                return false;
            }
        };
        actionBar.setListNavigationCallbacks(adapter, navigationListener);
        adapter.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        
        
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
        	onBackPressed();
        }
        
        return true;
    }
    
    @Override
    public void onBackPressed() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setMessage(R.string.salir_pregunta)
	    	.setCancelable(false)
	    	.setPositiveButton(R.string.si,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			cerrarAplicacion();
		    		}
		    	})
	    	.setNegativeButton(R.string.no,
		    	new DialogInterface.OnClickListener() {
		    		public void onClick(DialogInterface dialog,int id) {
		    			dialog.cancel();
		    		}
		    	});

    	AlertDialog alert = builder.create();
    	alert.show();
    }

    private void cerrarAplicacion() {
    	android.os.Process.killProcess(android.os.Process.myPid());
    }

}
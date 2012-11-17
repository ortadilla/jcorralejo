package es.jcorralejo.android.carnavapp;

import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

public class CarnavappActivity extends SherlockActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
}
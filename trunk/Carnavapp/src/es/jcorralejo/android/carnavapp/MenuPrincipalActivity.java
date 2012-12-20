package es.jcorralejo.android.carnavapp;

import org.holoeverywhere.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;

public class MenuPrincipalActivity extends SherlockActivity {
	
	private ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getSherlock().setUiOptions(ActivityInfo.UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);
        
        View fondo = findViewById(R.id.fondo);
        fondo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), CarnavappActivity3.class);
				startActivity(intent);	
				finish();
			}
		});
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

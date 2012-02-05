package es.jcorralejo.android.callejeras2012.activities;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;

import es.jcorralejo.android.callejeras2012.CallejerasApplication;
import es.jcorralejo.android.callejeras2012.R;
import es.jcorralejo.android.callejeras2012.entidades.Agrupacion;
import es.jcorralejo.android.callejeras2012.utils.Constantes;

public class LoginActivity extends Activity{

	CallejerasApplication app;
	private AdView adView1;
	private EditText pass;
	private EditText usuario; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		cargarAnuncios();
		app = (CallejerasApplication) getApplication();

		usuario = (EditText) findViewById(R.id.loginUsuario);
		pass = (EditText) findViewById(R.id.loginPass);

		Button aceptar = (Button)findViewById(R.id.loginAceptar);
		aceptar.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						if(datosCorrectos())
							Toast.makeText(getBaseContext(), "Login correcto", Toast.LENGTH_LONG).show();
						else
							Toast.makeText(getBaseContext(), "Debe indicar un usuario y una contraseña correctos", Toast.LENGTH_LONG).show();
					}
				}
		);

		Button cancelar = (Button)findViewById(R.id.loginCancelar);
		cancelar.setOnClickListener(
				new OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				}
		);
	}
	
	private boolean datosCorrectos(){
		boolean correcto = false;
		
		if(usuario.getText()!=null && usuario.getText().length()>0 
		&& pass.getText()!=null && pass.getText().length()>0){
			Agrupacion agr = app.getAgrupacionPorUsuarioPass(usuario.getText().toString(), pass.getText().toString());
			if(agr!=null){
				SharedPreferences prefs = getSharedPreferences(Constantes.PREFERENCES, MODE_PRIVATE);
				Editor editor = prefs.edit();
				editor.putInt(Constantes.PREFERENCE_AGRUPACION_LOGUEADA, agr.getId() );
				editor.commit();
				
				correcto = true;
				finish();
			}
		}
		
		return correcto;
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

	}

	@Override
	public void onDestroy() {
		adView1.destroy();
		super.onDestroy();
	}
}

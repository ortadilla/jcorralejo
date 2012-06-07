package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import aplicacion.AutodefinidosApplication;
import entidades.Tablero;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;
import es.jcorralejo.android.autodefinidos.utilities.TablerosHelper;

public class JuegoActivity extends Activity {

	private AutodefinidosApplication app;
	private Integer dificultad;
	private Integer tamanio;
	private Tablero tablero;
	private TableLayout tabla;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.juego);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		app = (AutodefinidosApplication) getApplication();
		tabla = (TableLayout) findViewById(R.id.tablero);

		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			dificultad = extras.getInt(Constantes.DIFICULTAD);
			tamanio = extras.getInt(Constantes.TAMANIO);
		}

		crearTablero();

	}

	private void crearTablero(){
		
		boolean error = true;
		
		if(tamanio!=null && dificultad!=null && tabla!=null){
			tablero = TablerosHelper.crearTablero(tamanio);
			if(tablero!=null){
				error = false;
				int counter = 0;
				for(int i=0; i<tablero.getAlto(); i++){
					TableRow fila = new TableRow(this);
					fila.setLayoutParams(new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
					fila.setWeightSum(tablero.getAncho());
					for(int j=0; j<tablero.getAncho(); j++){
						counter++;
						TextView t = new TextView(this);
						TableRow.LayoutParams layoutParam = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
						layoutParam.setMargins(1, 1, 1, 1);
						t.setLayoutParams(layoutParam);
						t.setText("C " + counter);
						t.setBackgroundResource(R.drawable.fondo_casilla);
						fila.addView(t);
					}
			        tabla.addView(fila);
				}
				tabla.setWeightSum(tablero.getAlto());
				
			}
		}
		
		if(error){
			Toast.makeText(this, R.string.error_nuevo_juego, Toast.LENGTH_LONG).show();
			finish();
		}
	}

}

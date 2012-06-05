package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
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
				
				for(int i=0; i<tablero.getTablero()[0].length; i++){
					TableRow fila = new TableRow(this);
					fila.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
					for(int j=0; j<tablero.getTablero()[1].length; j++){
						TextView celda = new TextView(this);
						celda.setText("celda");
//						celda.setBackgroundColor(tablero.getTablero()[i][j].isPregunta() ? Color.BLACK : Color.BLUE);
						celda.setBackgroundColor(Color.RED);
						celda.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));            
						fila.addView(celda, fila.getChildCount());      
					}
					tabla.addView(fila, tabla.getChildCount());
				}
				
			}
		}
		
		if(error){
			Toast.makeText(this, R.string.error_nuevo_juego, Toast.LENGTH_LONG).show();
			finish();
		}
	}

}

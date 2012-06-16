package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import aplicacion.AutodefinidosApplication;
import entidades.Casilla;
import entidades.Tablero;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;
import es.jcorralejo.android.autodefinidos.utilities.TablerosHelper;
import es.jcorralejo.android.autodefinidos.views.TextViewFlechas;

public class JuegoActivity extends Activity{

	ScaleGestureDetector scaleGestureDetector;
	GestureDetector gestureDetector;

	private AutodefinidosApplication app;
	private Integer dificultad;
	private Integer tamanio;
	private Tablero tablero;
	private LinearLayout tabla;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.juego);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		app = (AutodefinidosApplication) getApplication();
		tabla = (LinearLayout) findViewById(R.id.tablero);
		scaleGestureDetector = new ScaleGestureDetector(this, new MySimpleOnScaleGestureListener());
		gestureDetector = new GestureDetector(this, new MySimpleOnGestureListener());

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
					LinearLayout fila = new LinearLayout(this);
					fila.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
					fila.setWeightSum(tablero.getAncho());
					for(int j=0; j<tablero.getAncho(); j++){
						counter++;
						Casilla casilla = tablero.getCasilla(i,j);
						
						if(casilla.isPregunta()){
							if(casilla.getNumPalabras()==1){
								TextView t = new TextViewFlechas(this, false, false, false, false);
								TableRow.LayoutParams layoutParam = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
								layoutParam.setMargins(1, 1, 1, 1);
								t.setLayoutParams(layoutParam);
								t.setText("P 1");
								t.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
								fila.addView(t);
							}else if (casilla.getNumPalabras()==2){
								
								LinearLayout ll = new LinearLayout(this);
								ll.setWeightSum(2);
								ll.setOrientation(LinearLayout.VERTICAL);
								TableRow.LayoutParams layoutParam = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
								layoutParam.setMargins(1, 1, 1, 1);
								ll.setLayoutParams(layoutParam);
								
								TextView t = new TextViewFlechas(this, false, false, false, false);
								LinearLayout.LayoutParams layoutParam2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
								layoutParam2.setMargins(1, 1, 1, 1);
								t.setLayoutParams(layoutParam2);
								t.setText("P 1");
								t.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
								ll.addView(t);
								
								TextView t2 = new TextViewFlechas(this, false, false, false, false);
								LinearLayout.LayoutParams layoutParam3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
								layoutParam3.setMargins(1, 1, 1, 1);
								t2.setLayoutParams(layoutParam3);
								t2.setText("P 2");
								t2.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
								ll.addView(t2);
								
								fila.addView(ll);
							}
							
						}else{
							boolean flechaDerecha = j>0 && tablero.getCasilla(i,j-1).isPregunta() && tablero.getCasilla(i,j-1).isDerecha(); 
							boolean flechaDerechaAbajo = j>0 && tablero.getCasilla(i,j-1).isPregunta() && tablero.getCasilla(i,j-1).isDerechaAbajo(); 
							boolean flechaAbajo = i>0 && tablero.getCasilla(i-1,j).isPregunta() && tablero.getCasilla(i-1,j).isAbajo(); 
							boolean flechaAbajoDerecha = i>0 && tablero.getCasilla(i-1,j).isPregunta() && tablero.getCasilla(i-1,j).isAbajoDerecha(); 
							TextView t = new TextViewFlechas(this, flechaDerecha, flechaDerechaAbajo, flechaAbajo, flechaAbajoDerecha);
							LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
							layoutParam.setMargins(1, 1, 1, 1);
							t.setLayoutParams(layoutParam);
							t.setText("C " + counter);
							t.setBackgroundResource(R.drawable.fondo_casilla_libre);
							fila.addView(t);
						}
						
						
						
						
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
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		gestureDetector.onTouchEvent(event);
		scaleGestureDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	
	private void zoom(int scaleX, int scaleY, float x, float y) {
		tabla.setPivotX(x);
		tabla.setPivotY(y);
		tabla.setScaleX(scaleX);
		tabla.setScaleY(scaleY);
	}
	
	private void mover(float x, float y) {
		tabla.setPivotX(x);
		tabla.setPivotY(y);
	}

	public class MySimpleOnScaleGestureListener extends SimpleOnScaleGestureListener {
		
		float xIni, yIni, xFin, yFin;
		float spanIni, spanFin;

		public int zoom;
		private static final int DISTANCIA_MINIMA_DEDOS = 100;
		private static final int MAX_ZOOM = 3;
		private static final int MIN_ZOOM = 1;
		
		public MySimpleOnScaleGestureListener() {
			zoom = MIN_ZOOM;
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			return super.onScale(detector);
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			xIni = detector.getFocusX();
			yIni = detector.getFocusY();
			spanIni = detector.getCurrentSpan();
			
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			xFin = detector.getFocusX();
			yFin = detector.getFocusY();
			spanFin = detector.getCurrentSpan();

			//Zoom in
			if(spanFin>spanIni && spanFin-spanIni>DISTANCIA_MINIMA_DEDOS && zoom<MAX_ZOOM){
				zoom++;
				zoom(zoom, zoom, xIni, yIni);
			}
			//Zoom out
			else if (spanFin<spanIni && spanIni-spanFin>DISTANCIA_MINIMA_DEDOS && zoom>MIN_ZOOM){
				zoom--;
				zoom(zoom, zoom, xIni, yIni);
			}
		}
	}
	
	public class MySimpleOnGestureListener extends SimpleOnGestureListener{
		private static final int DISTANCIA_MINIMA_DEDOS = 100;
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			int deltaX = (int)(e1.getRawX()-e2.getRawX());
			int deltaY = (int)(e1.getRawY()-e2.getRawY());
			float x, y;
			
			int absDeltaX = Math.abs(deltaX);
			int absDeltaY = Math.abs(deltaY);
			if(absDeltaX>DISTANCIA_MINIMA_DEDOS || absDeltaY>DISTANCIA_MINIMA_DEDOS){
				if(absDeltaX > absDeltaY){
					if(velocityX>0){
						//Izquierda
						System.out.println("IZQUIERDA");
					}else{
						System.out.println("DERECHA");
						//Derecha
					}
				}else{
					if(velocityY>0){
						System.out.println("ARRIBA");
						//Arriba
					}else{
						System.out.println("ABAJO");
						//Abajo
					}
				}
			}
			
			
			return true;
		}
	}
	
//	
//	http://android-er.blogspot.com.es/2011/11/detect-pinch-zoom-using.html
//	http://myandroidnote.blogspot.com.es/2011/03/pinch-zoom-to-view-completely.html
//  http://code.google.com/p/android-touchexample/source/browse/trunk/src/com/example/android/touchexample/TouchExampleView.java
}


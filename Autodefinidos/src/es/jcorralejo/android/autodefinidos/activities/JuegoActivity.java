package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnTouchListener;
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

public class JuegoActivity extends Activity implements OnTouchListener{

	// Remember some things for zooming
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;
	PointF oldDistPoint = new PointF();
	public static String TAG = "ZOOM";
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	ScaleGestureDetector scaleGestureDetector;
	float xIni, yIni, xFin, yFin;
	float spanIni, spanFin;

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
//		tabla.setOnTouchListener(this);
		scaleGestureDetector = new ScaleGestureDetector(this, new simpleOnScaleGestureListener());

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
		if(scaleGestureDetector.onTouchEvent(event))
	         return true;

	    return super.onTouchEvent(event);
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			start.set(event.getX(), event.getY());
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			oldDistPoint = spacingPoint(event);
			if (oldDist > 10f) {
				midPoint(mid, event);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {

			} else if (mode == ZOOM) {
				PointF newDist = spacingPoint(event);
				float newD = spacing(event);
				int scale = (int)(newD / oldDist);
				Log.e(TAG, "distancia = " + newD);
				Log.e(TAG, "zoom = " + scale);
				if(scale>0){
					zoom(scale, scale, start);
				}
			}
			break;
		}
		return true;
	}
	
	/** 
	 * zooming is done from here 
	 */
	public void zoom(int scaleX, int scaleY, PointF pivot) {
		tabla.setPivotX(pivot.x);
		tabla.setPivotY(pivot.y);
		tabla.setScaleX(scaleX);
		tabla.setScaleY(scaleY);
	}

	/**
	 * space between the first two fingers
	 */
	private float spacing(MotionEvent event) {
		// ...
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private PointF spacingPoint(MotionEvent event) {
		PointF f = new PointF();
		f.x = event.getX(0) - event.getX(1);
		f.y = event.getY(0) - event.getY(1);
		return f;
	}

	/**
	 * the mid point of the first two fingers
	 */
	private void midPoint(PointF point, MotionEvent event) {
		// ...
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}
	
	public class simpleOnScaleGestureListener extends SimpleOnScaleGestureListener {
		
		public float zoom;
		private static final float MAX_ZOOM = 5.0f;
		private static final float MIN_ZOOM = 0.25f;

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			return super.onScale(detector);
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
//			System.out.println("INICIO");
//			System.out.println("Scale factor: "+String.valueOf(detector.getScaleFactor()));
//			System.out.println("Current Span: "+String.valueOf(detector.getCurrentSpan()));
//			System.out.println("X: "+String.valueOf(detector.getFocusX())+", Y: "+String.valueOf(detector.getFocusY()));
//			System.out.println();
			
			xIni = detector.getFocusX();
			yIni = detector.getFocusY();
			spanIni = detector.getCurrentSpan();
			
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
//			System.out.println("FIN");
//			System.out.println("Scale factor: "+String.valueOf(detector.getScaleFactor()));
//			System.out.println("Current Span: "+String.valueOf(detector.getCurrentSpan()));
//			System.out.println("X: "+String.valueOf(detector.getFocusX())+", Y: "+String.valueOf(detector.getFocusY()));
//			System.out.println();

			xFin = detector.getFocusX();
			yFin = detector.getFocusY();
			spanFin = detector.getCurrentSpan();

			//Zoom in
			if(spanFin>spanIni){
//				tabla.
			}
		}
		
		

	}
	
//	
//	http://android-er.blogspot.com.es/2011/11/detect-pinch-zoom-using.html
//	http://myandroidnote.blogspot.com.es/2011/03/pinch-zoom-to-view-completely.html
//  http://code.google.com/p/android-touchexample/source/browse/trunk/src/com/example/android/touchexample/TouchExampleView.java
}


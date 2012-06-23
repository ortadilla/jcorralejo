package es.jcorralejo.android.autodefinidos.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.Display;
import android.view.Gravity;
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
import entidades.Flecha;
import entidades.Tablero;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;
import es.jcorralejo.android.autodefinidos.utilities.TablerosHelper;
import es.jcorralejo.android.autodefinidos.views.TextViewFlechas;

public class JuegoActivity extends Activity{
	
	public int zoom;
	ScaleGestureDetector scaleGestureDetector;
	
	int prevX;  
    int prevY; 
    int topeMinX;
    int topeMinY;
    int topeMaxX;
    int topeMaxY;

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

		Bundle extras = getIntent().getExtras();
		if(extras!=null){
			dificultad = extras.getInt(Constantes.DIFICULTAD);
			tamanio = extras.getInt(Constantes.TAMANIO);
		}

		crearTablero();
		obtenerTopesPermitidos();
		
	}
	
	private void obtenerTopesPermitidos(){
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		
		topeMinX = 0 - width/20;   
		topeMinY = 0 - height/20;   
		topeMaxX = width + width/20;   
		topeMaxY = height + height/20;   
	}
	
	private void obtenerTablero(){
		if(tamanio!=null && dificultad!=null) {
			tablero = TablerosHelper.crearTablero(tamanio);
			TablerosHelper.rellenarTablero(tablero);
		}
	}
	
	

	private void crearTablero(){
		
		boolean error = true;
		obtenerTablero();
		if(tabla!=null && tablero!=null){
			error = false;
			int counter = 0;
			for(int i=0; i<tablero.getAlto(); i++){
				LinearLayout fila = new LinearLayout(this);
				fila.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1));
				fila.setWeightSum(tablero.getAncho());
				for(int j=0; j<tablero.getAncho(); j++){
					counter++;
					Casilla casilla = tablero.getCasilla(i,j);
					
					
					List<Flecha> flechas = new ArrayList<Flecha>();
					if(casilla.isPregunta()){
						if(casilla.getNumPalabras()==1){
							TextView t = new TextViewFlechas(this, flechas, false);
							TableRow.LayoutParams layoutParam = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
							layoutParam.setMargins(1, 1, 1, 1);
							t.setLayoutParams(layoutParam);
							t.setText(casilla.getPalabras().get(0).getDefinicion());
							t.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
							t.setGravity(Gravity.CENTER);
							fila.addView(t);
						}else if (casilla.getNumPalabras()==2){
							
							LinearLayout ll = new LinearLayout(this);
							ll.setWeightSum(2);
							ll.setOrientation(LinearLayout.VERTICAL);
							TableRow.LayoutParams layoutParam = new TableRow.LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
							layoutParam.setMargins(1, 1, 1, 1);
							ll.setLayoutParams(layoutParam);
							
							TextView t = new TextViewFlechas(this, flechas, true);
							LinearLayout.LayoutParams layoutParam2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
							layoutParam2.setMargins(1, 1, 1, 1);
							t.setLayoutParams(layoutParam2);
							t.setText(casilla.getPalabras().get(0).getDefinicion());
							t.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
							t.setGravity(Gravity.CENTER);
							ll.addView(t);
							
							TextView t2 = new TextViewFlechas(this, flechas, false);
							LinearLayout.LayoutParams layoutParam3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, 1);
							layoutParam3.setMargins(1, 1, 1, 1);
							t2.setLayoutParams(layoutParam3);
							t2.setText(casilla.getPalabras().get(1).getDefinicion());
							t2.setBackgroundResource(R.drawable.fondo_casilla_ocupada);
							t2.setGravity(Gravity.CENTER);
							ll.addView(t2);
							
							fila.addView(ll);
						}
						
					}else{
						if(i>0 && tablero.getCasilla(i-1,j).isPregunta() && tablero.getCasilla(i-1,j).isAbajo())
							flechas.add(new Flecha(Casilla.DIRECCION_ABAJO, Flecha.POSICION_CENTRO));
						if(i>0 && tablero.getCasilla(i-1,j).isPregunta() && tablero.getCasilla(i-1,j).isAbajoDerecha())
							flechas.add(new Flecha(Casilla.DIRECCION_ABAJO_DERECHA, Flecha.POSICION_CENTRO));
						if(j>0 && tablero.getCasilla(i,j-1).isPregunta() && tablero.getCasilla(i,j-1).isDerecha()){
							if(tablero.getCasilla(i,j-1).getDirecciones().size()==1)
								flechas.add(new Flecha(Casilla.DIRECCION_DERECHA, Flecha.POSICION_CENTRO));
							else
								flechas.add(new Flecha(Casilla.DIRECCION_DERECHA, 
											tablero.getCasilla(i,j-1).getDirecciones().get(0)==Casilla.DIRECCION_DERECHA 
										  ? Flecha.POSICION_ARRIBA : Flecha.POSICION_ABAJO));
						}
						if(j>0 && tablero.getCasilla(i,j-1).isPregunta() && tablero.getCasilla(i,j-1).isDerechaAbajo()){
							if(tablero.getCasilla(i,j-1).getDirecciones().size()==1)
								flechas.add(new Flecha(Casilla.DIRECCION_DERECHA_ABAJO, Flecha.POSICION_CENTRO));
							else
								flechas.add(new Flecha(Casilla.DIRECCION_DERECHA_ABAJO, 
											tablero.getCasilla(i,j-1).getDirecciones().get(0)==Casilla.DIRECCION_DERECHA_ABAJO 
										  ? Flecha.POSICION_ARRIBA : Flecha.POSICION_ABAJO));
						}
										
						TextView t = new TextViewFlechas(this, flechas, false);
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
		
		if(error){
			Toast.makeText(this, R.string.error_nuevo_juego, Toast.LENGTH_LONG).show();
			finish();
		}
	}
	
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		gestureDetector.onTouchEvent(event);
//		scaleGestureDetector.onTouchEvent(event);
//		return super.onTouchEvent(event);
//	}

	
	private void zoom(int scaleX, int scaleY, float x, float y) {
		tabla.setPivotX(x);
		tabla.setPivotY(y);
		tabla.setScaleX(scaleX);
		tabla.setScaleY(scaleY);
	}
	
	public class MySimpleOnScaleGestureListener extends SimpleOnScaleGestureListener {
		
		float xIni, yIni, xFin, yFin;
		float spanIni, spanFin;

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
	
	
	public boolean onTouchEvent(MotionEvent event)
	{
		scaleGestureDetector.onTouchEvent(event);
		
		int positionX = (int)event.getRawX();
		int positionY = (int)event.getRawY();

		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN: {
			if(zoom>1)
			{
				prevX = positionX;
				prevY = positionY;
			}
		}
		break;

		case MotionEvent.ACTION_MOVE: 
		{
			//Sólo se mueve si estamos con zoom aplicado
			if(zoom>1)
			{
				final int distY = positionY - prevY;  
				final int distX = positionX - prevX; 

				float nuevoX = tabla.getPivotX() - distX;
				float nuevoY = tabla.getPivotY() - distY;
				//No nos podemos salir de los límites
				nuevoX = nuevoX<topeMinX ? topeMinX : nuevoX > topeMaxX ? topeMaxX : nuevoX;    
				nuevoY = nuevoY<topeMinY ? topeMinY : nuevoY > topeMaxY ? topeMaxY : nuevoY;    
				tabla.setPivotX(nuevoX);
				tabla.setPivotY(nuevoY);

				prevX = positionX;
				prevY = positionY;
			} 
		}   
		break;
		case MotionEvent.ACTION_UP: 
			break; 
		}
		return true;
	}
}


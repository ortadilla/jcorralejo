package es.jcorralejo.android.autodefinidos.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.TextView;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;

public class TextViewFlechas extends TextView{
	
	boolean derecha; 
	boolean derechaAbajo; 
	boolean abajo; 
	boolean abajoDerecha;
	
	public TextViewFlechas(Context context, boolean derecha, boolean derechaAbajo, boolean abajo, boolean abajoDerecha) {
		super(context);
		
		this.derecha = derecha;
		this.derechaAbajo = derechaAbajo;
		this.abajo = abajo;
		this.abajoDerecha = abajoDerecha;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		pintarFlechas(canvas);
	}

	private void pintarFlechas(Canvas canvas){
		
		int numFlechasHorizontales = 0;
		int numFlechasVerticales = 0;
		if(derecha)
			numFlechasHorizontales++;
		if(derechaAbajo)
			numFlechasHorizontales++;
		if(abajo)
			numFlechasVerticales++;
		if(derechaAbajo)
			numFlechasVerticales++;
		
		int alto = getHeight();
		int ancho = getWidth();
		int anchoFlecha = (int) ((float)ancho * Constantes.PORCENTAJE_TAMANIO_FLECHA)/100;
		
		Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        Path path = new Path();

        
        if(numFlechasHorizontales>0){
        	int[][] puntosDerecha = {{0, alto/(numFlechasHorizontales+1) - anchoFlecha}, {anchoFlecha, alto/(numFlechasHorizontales+1)}, {0, alto/(numFlechasHorizontales+1) + anchoFlecha}};
        	int[][] puntosDerechaAbajo = {{0, numFlechasHorizontales*alto/(numFlechasHorizontales+1)}, {2*anchoFlecha, numFlechasHorizontales*alto/(numFlechasHorizontales+1)}, {anchoFlecha, numFlechasHorizontales*alto/(numFlechasHorizontales+1) + 2*anchoFlecha}};
        	if(numFlechasHorizontales==1){
        		if(derecha){
        			pintarFlecha(canvas, path, paint, puntosDerecha);
        		}else if(derechaAbajo){
        			pintarFlecha(canvas, path, paint, puntosDerechaAbajo);
        		}
        	}else if (numFlechasHorizontales==2){
//        		int[][] puntosDerecha = {{0, alto/3 - anchoFlecha}, {anchoFlecha, alto/3}, {0, alto/3 + anchoFlecha}};
        		pintarFlecha(canvas, path, paint, puntosDerecha);
//        		int[][] puntosDerechaAbajo = {{0, 2*alto/3}, {2*anchoFlecha, 2*alto/3}, {anchoFlecha, 2*alto/3 + 2*anchoFlecha}};
        		pintarFlecha(canvas, path, paint, puntosDerechaAbajo);
        	}
        }
        
        
        
        canvas.drawPath(path, paint);
	}
	
	private void pintarFlecha(Canvas canvas, Path path, Paint paint, int[][] puntos){
        path.moveTo(puntos[0][0], puntos[0][1]);
        path.lineTo(puntos[1][0], puntos[1][1]);
        path.lineTo(puntos[2][0], puntos[2][1]);
        path.close();
        
        canvas.drawPath(path, paint);		
	}



}

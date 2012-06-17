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
	boolean centrado;
	
	public TextViewFlechas(Context context, boolean derecha, boolean derechaAbajo, boolean abajo, boolean abajoDerecha, boolean centrado) {
		super(context);
		
		this.derecha = derecha;
		this.derechaAbajo = derechaAbajo;
		this.abajo = abajo;
		this.abajoDerecha = abajoDerecha;
		this.centrado = centrado;
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		pintarFlechas(canvas);
	}

//	private void pintarFlechas(Canvas canvas){
//		
//		int numFlechasHorizontales = 0;
//		int numFlechasVerticales = 0;
//		if(derecha)
//			numFlechasHorizontales++;
//		if(derechaAbajo)
//			numFlechasHorizontales++;
//		if(abajo)
//			numFlechasVerticales++;
//		if(abajoDerecha)
//			numFlechasVerticales++;
//		
//		int alto = getHeight();
//		int ancho = getWidth();
//		int anchoFlecha = (int) ((float)ancho * Constantes.PORCENTAJE_TAMANIO_FLECHA)/100;
//		
//		Paint paint = new Paint();
//        paint.setStrokeWidth(2);
//        paint.setColor(Color.BLACK);
//        Path path = new Path();
//
//        
//        if(numFlechasHorizontales>0){
//        	int[][] puntosDerecha = {{0, alto/(numFlechasHorizontales+1) - anchoFlecha}, {anchoFlecha, alto/(numFlechasHorizontales+1)}, {0, alto/(numFlechasHorizontales+1) + anchoFlecha}};
//        	int[][] puntosDerechaAbajo = {{0, numFlechasHorizontales*alto/(numFlechasHorizontales+1)}, {2*anchoFlecha, numFlechasHorizontales*alto/(numFlechasHorizontales+1)}, {anchoFlecha, numFlechasHorizontales*alto/(numFlechasHorizontales+1) + 2*anchoFlecha}};
//        	if(numFlechasHorizontales==1){
//        		if(derecha){
//        			pintarFlecha(canvas, path, paint, puntosDerecha);
//        		}else if(derechaAbajo){
//        			pintarFlecha(canvas, path, paint, puntosDerechaAbajo);
//        		}
//        	}else if (numFlechasHorizontales==2){
//        		pintarFlecha(canvas, path, paint, puntosDerecha);
//        		pintarFlecha(canvas, path, paint, puntosDerechaAbajo);
//        	}
//        }
//        
//        if(numFlechasVerticales>0){
//        	int[][] puntosAbajo = {{ancho/(numFlechasVerticales+1) - anchoFlecha, 0}, {ancho/(numFlechasVerticales+1), anchoFlecha}, {ancho/(numFlechasVerticales+1) + anchoFlecha, 0}};
//        	int[][] puntosAbajoDerecha = {{numFlechasVerticales*ancho/(numFlechasVerticales+1), 0}, {numFlechasVerticales*ancho/(numFlechasVerticales+1), 2*anchoFlecha}, {numFlechasVerticales*ancho/(numFlechasVerticales+1) + 2*anchoFlecha, anchoFlecha}};
//        	if(numFlechasVerticales==1){
//        		if(abajo){
//        			pintarFlecha(canvas, path, paint, puntosAbajo);
//        		}else if(abajoDerecha){
//        			pintarFlecha(canvas, path, paint, puntosAbajoDerecha);
//        		}
//        	}else if (numFlechasVerticales==2){
//        		pintarFlecha(canvas, path, paint, puntosAbajo);
//        		pintarFlecha(canvas, path, paint, puntosAbajoDerecha);
//        	}
//        }
//        
//        canvas.drawPath(path, paint);
//	}
	
	private void pintarFlechas(Canvas canvas){
		
		int numFlechas = 0;
		if(derecha)
			numFlechas++;
		if(derechaAbajo)
			numFlechas++;
		if(abajo)
			numFlechas++;
		if(abajoDerecha)
			numFlechas++;
		
		int alto = getHeight();
		int ancho = getWidth();
		int anchoFlecha = (int) ((float)ancho * Constantes.PORCENTAJE_TAMANIO_FLECHA)/100;
		
		Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        
        int coefCentrado = centrado ? 1 : 2;
        
        if(numFlechas>0){
        	int[][] puntosDerecha = {{0, alto/(coefCentrado+1) - anchoFlecha}, {anchoFlecha, alto/(coefCentrado+1)}, {0, alto/(coefCentrado+1) + anchoFlecha}};
        	int[][] puntosDerechaAbajo = {{0, coefCentrado*alto/(coefCentrado+1)}, {2*anchoFlecha, coefCentrado*alto/(coefCentrado+1)}, {anchoFlecha, coefCentrado*alto/(coefCentrado+1) + 2*anchoFlecha}};
        	int[][] puntosAbajo = {{ancho/(coefCentrado+1) - anchoFlecha, 0}, {ancho/(coefCentrado+1), anchoFlecha}, {ancho/(coefCentrado+1) + anchoFlecha, 0}};
        	int[][] puntosAbajoDerecha = {{coefCentrado*ancho/(coefCentrado+1), 0}, {coefCentrado*ancho/(coefCentrado+1), 2*anchoFlecha}, {coefCentrado*ancho/(coefCentrado+1) + 2*anchoFlecha, anchoFlecha}};

        	if(derecha){
        		pintarFlecha(canvas, path, paint, puntosDerecha);
        	}
        	if(derechaAbajo){
        		pintarFlecha(canvas, path, paint, puntosDerechaAbajo);
        	}
        	if(abajo){
    			pintarFlecha(canvas, path, paint, puntosAbajo);
    		}
        	if(abajoDerecha){
    			pintarFlecha(canvas, path, paint, puntosAbajoDerecha);
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

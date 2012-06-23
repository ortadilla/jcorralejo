package es.jcorralejo.android.autodefinidos.views;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.TextView;
import entidades.Flecha;
import es.jcorralejo.android.autodefinidos.utilities.Constantes;

public class TextViewFlechas extends TextView{

	private List<Flecha> flechas;
	private boolean dividir;
	
	public TextViewFlechas(Context context, List<Flecha> flechas, boolean dividir){
		super(context);
		this.flechas = flechas;
		this.dividir = dividir;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		pintarFlechas(canvas);
	}

	private void pintarFlechas(Canvas canvas){
		
		int alto = getHeight();
		int ancho = getWidth();
		int anchoFlecha = (int) ((float)ancho * Constantes.PORCENTAJE_TAMANIO_FLECHA)/100;
		
		Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        
        if(flechas!=null && flechas.size()>0){

        	int[][] puntos = new int[3][2];
        	for(Flecha flecha : flechas){
        		if(flecha.isDerecha()){
        			puntos[0][0] = 0; 
        			puntos[0][1] = flecha.isPosicionArriba() ? alto/4 - anchoFlecha : flecha.isPosicionCentro() ? alto/2 - anchoFlecha : flecha.isPosicionAbajo() ? 3*alto/4 - anchoFlecha :0;
        			
        			puntos[1][0] = anchoFlecha; 
        			puntos[1][1] = flecha.isPosicionArriba() ? alto/4 : flecha.isPosicionCentro() ? alto/2 : flecha.isPosicionAbajo() ? 3*alto/4 : 0;
        			
        			puntos[2][0] = 0; 
        			puntos[2][1] = flecha.isPosicionArriba() ? alto/4 + anchoFlecha : flecha.isPosicionCentro() ? alto/2 + anchoFlecha : flecha.isPosicionAbajo() ? 3*alto/4 + anchoFlecha : 0; 
        		}
        		if(flecha.isDerechaAbajo()){
        			puntos[0][0] = 0; 
        			puntos[0][1] = flecha.isPosicionArriba() ? alto/4 : flecha.isPosicionCentro() ? alto/2 : flecha.isPosicionAbajo() ? 3*alto/4 : 0;
        			
        			puntos[1][0] = 2*anchoFlecha; 
        			puntos[1][1] = flecha.isPosicionArriba() ? alto/4 : flecha.isPosicionCentro() ? alto/2 : flecha.isPosicionAbajo() ? 3*alto/4 : 0;
        			
        			puntos[2][0] = anchoFlecha; 
        			puntos[2][1] = flecha.isPosicionArriba() ? alto/4 + 2*anchoFlecha : flecha.isPosicionCentro() ? alto/2 + 2*anchoFlecha : flecha.isPosicionAbajo() ? 3*alto/4 + 2*anchoFlecha : 0; 
        		}
        		if(flecha.isAbajo()){
        			puntos[0][0] = ancho/2 - anchoFlecha; 
        			puntos[0][1] = 0;
        			
        			puntos[1][0] = ancho/2 + anchoFlecha; 
        			puntos[1][1] = 0;
        			
        			puntos[2][0] = ancho/2; 
        			puntos[2][1] = anchoFlecha; 
        		}
        		if(flecha.isAbajoDerecha()){
        			puntos[0][0] = ancho/2; 
        			puntos[0][1] = 0;
        			
        			puntos[1][0] = ancho/2; 
        			puntos[1][1] = 2*anchoFlecha;
        			
        			puntos[2][0] = ancho/2 + anchoFlecha; 
        			puntos[2][1] = anchoFlecha; 

        		}
        		
        		pintarFlecha(canvas, path, paint, puntos);
        	}
        }
        
        if(dividir)
        	dividir(canvas, paint, alto, ancho);
        
        canvas.drawPath(path, paint);
	}
	
	private void dividir(Canvas canvas, Paint paint, int alto, int ancho){
    	paint.setStrokeWidth(1);
    	paint.setColor(Color.WHITE);
    	canvas.drawLine(0, alto-1, ancho, alto-1, paint);
		
	}
	
	private void pintarFlecha(Canvas canvas, Path path, Paint paint, int[][] puntos){
        path.moveTo(puntos[0][0], puntos[0][1]);
        path.lineTo(puntos[1][0], puntos[1][1]);
        path.lineTo(puntos[2][0], puntos[2][1]);
        path.close();
        
        canvas.drawPath(path, paint);		
	}



}

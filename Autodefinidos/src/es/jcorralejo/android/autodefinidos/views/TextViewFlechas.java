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

		pintarFlecha(canvas);
	}

	private void pintarFlecha(Canvas canvas){
		
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
		
		int anchoFlecha = getWidth() / Constantes.PORCENTAJE_TAMANIO_FLECHA;
		
		Paint paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        
        Path path = new Path();
        path.moveTo(0, alto/3 - anchoFlecha);
        path.lineTo(anchoFlecha, alto/3);
        path.lineTo(0, alto/3 + anchoFlecha);
        path.close();
        
        canvas.drawPath(path, paint);
	}



}

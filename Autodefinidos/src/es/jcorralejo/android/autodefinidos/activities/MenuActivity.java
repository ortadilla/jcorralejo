package es.jcorralejo.android.autodefinidos.activities;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import es.jcorralejo.android.autodefinidos.R;

public class MenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
        
        Typeface font = Typeface.createFromAsset(getAssets(), "handsean.ttf");

        TextView botonNuevoJuego = (TextView) findViewById(R.id.botonNuevoJuego);
        botonNuevoJuego.setTypeface(font);
        
        TextView botonContinuarJuego = (TextView) findViewById(R.id.botonContinuarJuego);
        botonContinuarJuego.setTypeface(font);
        
        TextView botonSalir = (TextView) findViewById(R.id.botonSalir);
        botonSalir.setTypeface(font);
        
        
    }
}
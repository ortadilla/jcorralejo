package es.jcorralejo.android.autodefinidos.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import entidades.Palabra;
import es.jcorralejo.android.autodefinidos.R;
import es.jcorralejo.android.autodefinidos.bd.DBHelper;

public class AutodefinidosActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
      //Creamos y abrimos la base de datos
        DBHelper BD = new DBHelper(this);
        BD.open();
         
        //Insertamos una nueva alarma con valores _id=1, alarma=1, evento=1
        BD.insertPalabra(null, "Hola", "Saludo", "FACIL");
         
        //Modificamos la alarma anterior dejándola como _id=1, alarma=2, evento=3
        BD.updateAlarma(1, "A", "Vocal", "ASD");
         
       Palabra palabra = BD.getPalabra(1);
       System.out.println(palabra.getPalabra()+": "+palabra.getDefinicion());
       System.out.println();
       List<Palabra> palabras = BD.getPalabras();
       for(Palabra p : palabras){
    	   System.out.println(p.getPalabra()+": "+p.getDefinicion());
       }
    }
}
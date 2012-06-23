package es.jcorralejo.android.autodefinidos.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Casilla;
import entidades.PalabrasTablero;

public class PalabrasTableroHelper {

	private static final Map<String, List<PalabrasTablero>> PALABRAS_TABLEROS;
    static {
    	PALABRAS_TABLEROS = new HashMap<String, List<PalabrasTablero>>();
    	aniadirPalabrasTableroPequenioUno();
    }
    
    private static void aniadirPalabrasTableroPequenioUno(){
    	PalabrasTablero pt = new PalabrasTablero(1, Constantes.TAMANIO_PEQUENIO, PalabrasTablero.PLANTILLA_PEQUENIA_UNO);
    	pt.setCasilla(0, 0, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(8), PalabrasHelper.getPalabra(1)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA_ABAJO, Casilla.DIRECCION_ABAJO_DERECHA), true));
    	pt.setCasilla(0, 2, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(10), PalabrasHelper.getPalabra(9)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA_ABAJO, Casilla.DIRECCION_ABAJO), true));
    	pt.setCasilla(0, 4, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(12), PalabrasHelper.getPalabra(11)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA_ABAJO, Casilla.DIRECCION_ABAJO), true));
    	pt.setCasilla(2, 0, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(2), PalabrasHelper.getPalabra(3)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA, Casilla.DIRECCION_ABAJO_DERECHA), true));
    	pt.setCasilla(4, 0, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(4), PalabrasHelper.getPalabra(5)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA, Casilla.DIRECCION_ABAJO_DERECHA), true));
    	pt.setCasilla(6, 0, new Casilla(null, null, Arrays.asList(PalabrasHelper.getPalabra(6), PalabrasHelper.getPalabra(7)), 2, Arrays.asList(Casilla.DIRECCION_DERECHA, Casilla.DIRECCION_ABAJO_DERECHA), true));

    	aniadir(pt);
    }
    
    private static void aniadir(PalabrasTablero pt){
    	if(!PALABRAS_TABLEROS.containsKey(pt.getTamanio()+"-"+pt.getPlantilla()))
    		PALABRAS_TABLEROS.put(pt.getTamanio()+"-"+pt.getPlantilla(), new ArrayList<PalabrasTablero>());

    	PALABRAS_TABLEROS.get(pt.getTamanio()+"-"+pt.getPlantilla()).add(pt);
    	PALABRAS_TABLEROS.put(pt.getTamanio()+"-"+pt.getPlantilla(), PALABRAS_TABLEROS.get(pt.getTamanio()+"-"+pt.getPlantilla()));
    }
    
    
    public static List<PalabrasTablero> getPalabraTablero(String clave){
    	return PALABRAS_TABLEROS.get(clave);
    }
}

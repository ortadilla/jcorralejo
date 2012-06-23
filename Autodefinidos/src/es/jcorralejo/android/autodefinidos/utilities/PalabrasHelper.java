package es.jcorralejo.android.autodefinidos.utilities;

import java.util.HashMap;
import java.util.Map;

import entidades.Palabra;

public class PalabrasHelper {
	
	private static final Map<Integer, Palabra> PALABRAS;
    static {
    	PALABRAS = new HashMap<Integer, Palabra>();
    	PALABRAS.put(1, new Palabra(1, 		"RETAMA", 		"Mata leguminosa", 		Palabra.DIFICULTAD_DIFICIL));
    	PALABRAS.put(2, new Palabra(2, 		"CORAN", 		"Libro musulman", 		Palabra.DIFICULTAD_FACIL));
    	PALABRAS.put(3, new Palabra(3, 		"CASERO", 		"Hogareño", 			Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(4, new Palabra(4, 		"LIAIS", 		"Engañáis", 			Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(5, new Palabra(5, 		"CAERSE", 		"Precipitarse", 		Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(6, new Palabra(6, 		"DRAMA", 		"Pieza de teatro", 		Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(7, new Palabra(7, 		"FAENAR", 		"Pescar", 				Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(8, new Palabra(8, 		"RECALADA", 	"Acción de recalar", 	Palabra.DIFICULTAD_DIFICIL));
    	PALABRAS.put(9, new Palabra(9, 		"TOSIERE", 		"Carraspeare", 			Palabra.DIFICULTAD_DIFICIL));
    	PALABRAS.put(10, new Palabra(10, 	"MAREARAN", 	"Darán vueltas", 		Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(11, new Palabra(11, 	"MARISMA", 		"Terreno pantajoso", 	Palabra.DIFICULTAD_NORMAL));
    	PALABRAS.put(12, new Palabra(12, 	"MANOSEAR", 	"Sobar", 				Palabra.DIFICULTAD_NORMAL));
    }
	
	
	public static Palabra getPalabra(Integer id){
		return PALABRAS.get(id);
	}
	
	

}

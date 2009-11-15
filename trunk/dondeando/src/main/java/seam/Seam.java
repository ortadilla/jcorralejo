package seam;

/**
 * Fa�ade para la clase Seam de Seam, valga la redundacia, en previsi�n de que algunas funciones
 * desaparecer�n en Seam 2.0.0 (p.e., invalidateSession()). 
 * 
 */
public class Seam{
    
    /** Marca la sesi�n como invalidada al final del ciclo del request */
    public static void invalidateSession(){
        org.jboss.seam.Seam.invalidateSession();
    }

}

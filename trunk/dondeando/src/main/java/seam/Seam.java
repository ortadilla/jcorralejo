package seam;

/**
 * Façade para la clase Seam de Seam, valga la redundacia, en previsión de que algunas funciones
 * desaparecerán en Seam 2.0.0 (p.e., invalidateSession()). 
 * 
 */
public class Seam{
    
    /** Marca la sesión como invalidada al final del ciclo del request */
    public static void invalidateSession(){
        org.jboss.seam.Seam.invalidateSession();
    }

}

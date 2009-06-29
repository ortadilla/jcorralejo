package dondeando.excepciones;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;

public abstract class Excepcion extends Exception {

	   /**
	    * Construye una Excepcion sin mensaje de detalle.
	    */
	   public Excepcion() {
	   }

	   /**
	    * Construye una Excepcion con el mensaje de detalle
	    * especificada.
	    *
	    * @param mensaje El mensaje de detalle.
	    */
	   public Excepcion(String mensaje) {
		super(mensaje);
	   }

	   /**
	    * Construye una nueva excepción con el mensaje de detalle especificada y
	    * la causa.
	    *
	    * <p>Notar que el mensaje de detalle asociado con la <code>causa</code>
	    * <i>no</i>es incorporado automáticamente en el mensaje de detalle de 
	    * la excepción.
	    *
	    * @param  mensaje El mensaje de detalle (el cual es salvado para ser recuperado
	    * 			mas tarde por el método {@link Throwable#getMessage()} ).
	    * @param  causa La causa (la cual es salvada para su posterior recuperación
	    * 		    por el método {@link Throwable#getCause()} ).  
	    * 			(Un valor <tt>null</tt> es permitido, e indica que la causa es inexistente
	    * 			o desconocida).
	    */
	   public Excepcion(String mensaje, Throwable causa) {
	       super(mensaje, causa);
	   }

	   /**
	    * Construye una nueva excepción con la causa especificada, y un mensaje
	    * de detalle <tt>(causa==null ? null : causa.toString())</tt> (el cual
	    * típicamente contiene la clase y el mensaje de detalle de <tt>causa</tt>).
	    * 
	    * Este constructor es útil para excepciones que son algo más que wrappers
	    * (sorry) para otros throwables.
	    *
	    * @param  causa La causa (la cual es salvada para su posterior recuperación
	    * 		    por el método {@link Throwable#getCause()} ).  
	    * 			(Un valor <tt>null</tt> es permitido, e indica que la causa es inexistente
	    * 			o desconocida).
	    */
	   public Excepcion(Throwable causa) {
	       super(causa);
	   }
	
	/** 
	 * Devuelve la causa raíz de la excepción indicada, o ella misma si no tiene causa. 
	 * @param exc  Excepción de la que obtener la causa    
	 * @return Causa raíz (ella misma si no tiene) o null si el argumento es null
	 */
    public static Exception getCausa(Throwable exc) {
        Throwable causa = null;
        if(exc!=null)
            causa = exc instanceof ServletException          ? ((ServletException) exc).getRootCause() :
                    exc instanceof SQLException              ? ((SQLException) exc).getNextException() :
                    exc instanceof InvocationTargetException ? ((InvocationTargetException) exc).getTargetException() 
                                                             : exc.getCause();
        return causa!=null              ? getCausa(causa) : 
               exc instanceof Exception ? (Exception)exc
                                        : new Exception(exc);
    }
}

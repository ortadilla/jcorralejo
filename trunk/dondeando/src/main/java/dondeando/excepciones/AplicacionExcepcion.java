package dondeando.excepciones;

import java.util.ArrayList;
import java.util.List;

public class AplicacionExcepcion extends Exception {

	
	

	/**
	    * Construye una Excepcion sin mensaje de detalle.
	    */
	   public AplicacionExcepcion() {
	   }

	   /**
	    * Construye una Excepcion con el mensaje de detalle
	    * especificada.
	    *
	    * @param mensaje El mensaje de detalle.
	    */
	   public AplicacionExcepcion(String mensaje) {
		   super(mensaje);
		   iniciaMensajes(mensaje);
	   }

	protected void iniciaMensajes(String mensaje) {
		mensajes = new ArrayList<String>();
		   mensajes.add(mensaje);
	}

	   /**
	    * Construye una excepcion especifica para un conjunto de mensajes de detalle.
	    *
	    * @param mensaje El mensaje de detalle.
	    */
	   public AplicacionExcepcion(List<String> mensajes) {
		   
		   this.mensajes = mensajes;
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
	   public AplicacionExcepcion(String mensaje, Throwable causa) {
	       super(mensaje, causa);
	       iniciaMensajes(mensaje);
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
	   public AplicacionExcepcion(Throwable causa) {
	       super(causa);
	   }
	
	   
	   /**
		 * @return Retorna el valor asignado a mensajes.
		 */
		public List<String> getMensajes() {
			return mensajes;
		}

		/**
		 * @param mensajes El mensajes a asignar.
		 */
		public void setMensajes(List<String> mensajes) {
			this.mensajes = mensajes;
		}
	   
	   /**
		* 
		*/
		private static final long serialVersionUID = 1L;
		
		/**
		 * Lista de mensaje individuales para la aplicacion.
		 */
		private List<String> mensajes;

}

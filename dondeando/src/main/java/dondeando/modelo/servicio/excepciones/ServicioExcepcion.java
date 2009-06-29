/**
 * 
 */
package dondeando.modelo.servicio.excepciones;

import java.util.List;

import dondeando.excepciones.AplicacionExcepcion;


public class ServicioExcepcion extends AplicacionExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construye una excepcion especifica para un conjunto de mensajes de detalle.
	 *
	 * @param mensaje El mensaje de detalle.
	 */
	public ServicioExcepcion(List<String> mensajes) {
		super(mensajes);
	}
	
	public ServicioExcepcion(String mensaje) {
		super(mensaje);
	}
	
	/**
	 * Metodo que se lanza cuando la excepcion de servicio esta causada por otra excepcion
	 * @param e, la excepcion que causa la de servicio.
	 */
	public ServicioExcepcion(Exception e) {
		super(e);
		
	}
	
	/**
	 * Constructor para una excepcion de servicio cambiado por otro y con una lista de mensajes.
	 * @param mensajes, el mensaje que queremos añadir.
	 * @param e, la excepcion que lo causa.
	 */
	public ServicioExcepcion(String mensaje,
							 Exception e) {
		super(e);
		iniciaMensajes(mensaje);
	}
	
}

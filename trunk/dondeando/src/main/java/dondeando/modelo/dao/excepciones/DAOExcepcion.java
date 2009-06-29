/**
 * 
 */
package dondeando.modelo.dao.excepciones;

import java.util.List;

import dondeando.excepciones.AplicacionExcepcion;


public class DAOExcepcion extends AplicacionExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construye una excepcion especifica para un conjunto de mensajes de detalle.
	 *
	 * @param mensaje El mensaje de detalle.
	 */
	public DAOExcepcion(List<String> mensajes) {
		super(mensajes);
	}
	
	public DAOExcepcion(String mensaje) {
		super(mensaje);
	}
}

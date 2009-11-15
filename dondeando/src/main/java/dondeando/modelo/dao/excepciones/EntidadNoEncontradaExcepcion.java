/**
 * 
 */
package dondeando.modelo.dao.excepciones;

public class EntidadNoEncontradaExcepcion extends DAOExcepcion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construye una excepcion especifica con el nombre de la clase y el identificador buscado
	 *
	 * @param clase La entidad que se estaba buscando.
	 * @param identificador Identificador de la entidad buscada
	 */

	public EntidadNoEncontradaExcepcion(String clase, String identificador) {
		super( "No se ha encontrado la entidad de la clase: " + clase + 
				", cuyo identificador es: "+ identificador);
	}
}

package dondeando.excepciones;

/**
 * Esta excepci�n es lanzada cuando se intenta instanciar un objeto
 * mediante la factor�a general no es posible.
 * Puede ser por diferentes razones. Por ejemplo, no se ha indicado
 * de que clase se quiere obtener la instancia, no es una clase 
 * v�lida, etc.
 * 
 */
public class InstanciacionExcepcion extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construye una Excepcion sin mensaje de detalle.
	 */
	public InstanciacionExcepcion() {
		super();
	}

	/**
	 * Construye una nueva excepci�n con el mensaje de detalle especificada y la
	 * causa.
	 * 
	 * <p>
	 * Notar que el mensaje de detalle asociado con la <code>causa</code>
	 * <i>no</i>es incorporado autom�ticamente en el mensaje de detalle de la
	 * excepci�n.
	 * 
	 * @param mensaje
	 *            El mensaje de detalle (el cual es salvado para ser recuperado
	 *            mas tarde por el m�todo {@link Throwable#getMessage()} ).
	 * @param causa
	 *            La causa (la cual es salvada para su posterior recuperaci�n
	 *            por el m�todo {@link Throwable#getCause()} ). (Un valor
	 *            <tt>null</tt> es permitido, e indica que la causa es
	 *            inexistente o desconocida).
	 */
	public InstanciacionExcepcion(String mensaje, Throwable causa) {
		super(mensaje, causa);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye una Excepcion con el mensaje de detalle especificada.
	 * 
	 * @param mensaje
	 *            El mensaje de detalle.
	 */
	public InstanciacionExcepcion(String mensaje) {
		super(mensaje);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Construye una nueva excepci�n con la causa especificada, y un mensaje de
	 * detalle <tt>(causa==null ? null : causa.toString())</tt> (el cual
	 * t�picamente contiene la clase y el mensaje de detalle de <tt>causa</tt>).
	 * 
	 * Este constructor es �til para excepciones que son algo m�s que wrappers
	 * (sorry) para otros throwables.
	 * 
	 * @param causa
	 *            La causa (la cual es salvada para su posterior recuperaci�n
	 *            por el m�todo {@link Throwable#getCause()} ). (Un valor
	 *            <tt>null</tt> es permitido, e indica que la causa es
	 *            inexistente o desconocida).
	 */
	public InstanciacionExcepcion(Throwable causa) {
		super(causa);
		// TODO Auto-generated constructor stub
	}
	
}

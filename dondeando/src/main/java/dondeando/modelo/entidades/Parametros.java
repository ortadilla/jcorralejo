package dondeando.modelo.entidades;

/**
 * Interface que contiene todos los par�metros utilizados en la aplicaci�n
 */
public interface Parametros {
	
	/**
	 * Indica el directorio donde se almacenar�n las im�genes
	 */
	public static String PARAMETRO_DIRECTORIO_GUARDAR_IMAGENES = "C:\\dondeando\\img";

	/**
	 * Indica la URL donde encontrar las im�genes
	 */
	public static String PARAMETRO_URL_IMAGENES = "http://localhost:8080/dondeando/ficheros/";

	/**
	 * Indica el nombre de la im�gen del usuario gen�rico
	 */
	public static String PARAMETRO_NOMBRE_IMAGENEN_USUARIO_GENERICO = "usuarioGenerico.jpg";
	
	/**
	 * Indica el la base de la URL para buscar una direcci�n en GoogleMaps
	 */
	public static String PARAMETRO_BASE_URL_IR_MAPA = "http://maps.google.es/maps?f=q&hl=es&geocode=&q=";
	
}

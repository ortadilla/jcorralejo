package dondeando.modelo.entidades;

/**
 * Interface que contiene todos los parámetros utilizados en la aplicación
 */
public interface Parametros {
	
	/**
	 * Indica el directorio donde se almacenarán las imágenes
	 */
	public static String PARAMETRO_DIRECTORIO_GUARDAR_IMAGENES = "C:\\dondeando\\img";

	/**
	 * Indica la URL donde encontrar las imágenes
	 */
	public static String PARAMETRO_URL_IMAGENES = "http://localhost:8080/dondeando/ficheros/";

	/**
	 * Indica el nombre de la imágen del usuario genérico
	 */
	public static String PARAMETRO_NOMBRE_IMAGENEN_USUARIO_GENERICO = "usuarioGenerico.jpg";
	
}

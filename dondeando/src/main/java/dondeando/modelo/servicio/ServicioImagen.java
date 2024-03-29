package dondeando.modelo.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.Usuario;

public interface ServicioImagen {

	/**
	 * Copia el contenido de un fichero a un fichero temporal
     * @param inputStream, el inputStream del archivo
     * @return File , el archivo temporal esperado
	 * @throws IOException Si ocurre alg�n error al crear el fichero
	 */
	public File devuelveFicheroTemporal(InputStream inputStream) throws IOException;
	
	/**
     * Dado un archivo temporal devuelve su FileInputStream
     * @param temp Fichero temporal
     * @return FileInputStream FileInputStream del fichero temporal
     */
    public FileInputStream devuelveFileInputStream(File temp);
    
    /**
	 * Comprueba si la imagen es del tipo y del tama�o concreto
	 * @param uploadedFileType, el tipo de foto
	 * @param uploadedFileSize, el tama�o de la foto
     * @param input, el inputStream del fichero subido
	 * @return lista de errores
	 */
	public List<String> comprobarAgregarImagen(String uploadedFileType, Long uploadedFileSize, InputStream input);
	
	/**
	 * Guarda en BD la imagen indicada
	 * @param nombre	Nombre de la imagen
	 * @param inputStream	Fichero temporal de la imagen a guardar
	 * @param guardarEnBD	Indica si, adem�s de en el servidor, se desea guardar la imagen en BD
	 * @return	Imagen creada
	 */
	public Imagen guardarImagen(String nombre, InputStream inputStream, boolean guardarEnBD);

	/**
	 * Calcula la URL de la imagen del usuario pasado por par�metros
	 * @param usuario	Usuario para el que calcular la URL de su imagen
	 * @return	URL de la imagen del usuario
	 */
	public String calcularUrlImagenUsuario(Usuario usuario);
	
	/**
	 * Calcula la URL de la imagen para un usuario nuevo
	 * @return	URL de la imagen del usuario nuevo
	 */
	public String calcularUrlImagenUsuarioNuevo();
	
	/**
	 * Busca la imagen por defecto de los Usuarios
	 * @return Devuelve la imagen del usuario gen�rico
	 */
	public Imagen encontrarImagenUsuarioGenerico();
	
	/**
	 * Calcula la URL de la imagen indicada
	 * @param imagen Imagen para calcular su URL
	 * @return URL de la imagen
	 */
	public String calcularUrlImagen(Imagen imagen);
	
	/**
	 * Elimina la imagen indicada
	 * @param imagen Imagen a borrar
	 */
	public void hacerTransitoria(Imagen imagen);
	
	/**
	 * Hace efectivas en la BD las operaciones pendientes 
	 * @throws DAOExcepcion Si ocurre alg�n error SQL al realizar las operaciones.
	 */
	public void flush() throws DAOExcepcion;
}

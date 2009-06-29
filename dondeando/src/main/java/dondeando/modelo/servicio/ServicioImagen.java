package dondeando.modelo.servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.Usuario;

public interface ServicioImagen {

	/**
	 * Copia el contenido de un fichero a un fichero temporal
     * @param inputStream, el inputStream del archivo
     * @return File , el archivo temporal esperado
	 * @throws IOException Si ocurre algún error al crear el fichero
	 */
	public File devuelveFicheroTemporal(InputStream inputStream) throws IOException;
	
	/**
     * Dado un archivo temporal devuelve su FileInputStream
     * @param temp Fichero temporal
     * @return FileInputStream FileInputStream del fichero temporal
     */
    public FileInputStream devuelveFileInputStream(File temp);
    
    /**
	 * Comprueba si la imagen es del tipo y del tamaño concreto
	 * @param uploadedFileType, el tipo de foto
	 * @param uploadedFileSize, el tamaño de la foto
     * @param input, el inputStream del fichero subido
	 * @return lista de errores
	 */
	public List<String> comprobarAgregarImagen(String uploadedFileType, Long uploadedFileSize, InputStream input);
	
	/**
	 * Guarda en BD la imagen indicada
	 * @param nombre	Nombre de la imagen
	 * @param inputStream	Fichero temporal de la imagen a guardar
	 * @param guardarEnBD	Indica si, además de en el servidor, se desea guardar la imagen en BD
	 * @return	Imagen creada
	 */
	public Imagen guardarImagen(String nombre, InputStream inputStream, boolean guardarEnBD);

	/**
	 * Calcula la URL de la imagen del usuario pasado por parámetros
	 * @param usuario	Usuario para el que calcular la URL de su imagen
	 * @return	URL de la imagen del usuario
	 */
	public String calcularUrlImagenUsuario(Usuario usuario);
}

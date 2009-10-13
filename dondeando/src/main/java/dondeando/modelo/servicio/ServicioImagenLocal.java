package dondeando.modelo.servicio;

import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.ImagenLocal;
import dondeando.modelo.entidades.Local;

public interface ServicioImagenLocal {
	
	/**
	 * Elimina la imagenLocal indicada
	 * @param imagenLocal ImagenLocal a eliminar
	 */
	public void hacerTransitoria(ImagenLocal imagenLocal);
	
	/**
	 * Hace efectivas en la BD las operaciones pendientes 
	 * @throws DAOExcepcion Si ocurre algún error SQL al realizar las operaciones.
	 */
	public void flush() throws DAOExcepcion;
	
	/**
	 * Crea una imagenLocal con los datos pasados por parámetros
	 * @param imagen Imagen de la ImagenLocal
	 * @param local	Local de la ImagenLocal
	 * @param descripcion Descripcion de la ImagenLocal
	 * @return ImagenLocal creada
	 */
	public ImagenLocal crearImagenLocal(Imagen imagen, Local local, String descripcion);

}

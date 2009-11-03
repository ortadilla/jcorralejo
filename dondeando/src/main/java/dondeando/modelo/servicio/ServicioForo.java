package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Usuario;

public interface ServicioForo {
	
	/**
	 * Busca los foros con los par�metros indicados
	 * @param titulo T�tulo del foro a buscar
	 * @param activo Indica si los Foros estar�n o no activos
	 * @return Lista de foros encontrados
	 */
	public List<Foro> encontrarForosPorTituloYActivo(String titulo, Boolean activo);
	
	/**
	 * Rellena las propiedades no mapeadas de la lista de foros indicada
	 * @param listaForos Lista de foros a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Foro> listaForos);
	
	/**
	 * Desactiva el foro indicado
	 * @param foro	Foro a desactivar
	 */
	public void desactivarForo(Foro foro);
	
	/**
	 * Descarta el foro indicado de la sessi�n de Hibernate
	 * @param foro Foro a descartar
	 */
	public void descartarForo(Foro foro);
	
	/**
	 * Activa el foro indicado
	 * @param foro	Foro a activa
	 */
	public void activarForo(Foro foro);

	/**
	 * Crea un foro con los datos indicados
	 * @param titulo T�tulo del foro
	 * @param descripcion Descripci�n del foro
	 * @return Foro creado
	 */
	public Foro crearForo(String titulo, String descripcion);
	
	/**
	 * Edita el foro con los datos indicados 
	 * @param foro Foro a editar
	 * @param titulo Nuevo t�tulo para el foro
	 * @param descripcion Nueva descripci�n para el foro
	 */
	public void editarForo(Foro foro, String titulo, String descripcion);
	
	/**
	 * Busca un foro con el t�tulo indicado
	 * @param titulo	T�tulo por el que buscar alg�n Foro
	 * @return Foro encontrado
	 */
	public Foro encontrarForoPorTitulo(String titulo);
	
	/**
	 * A�ade el usuario como moderador del foro
	 * @param foro Foro al que a�adir el moderador
	 * @param moderador Nuevo moderador del foro
	 */
	public void aniadirModerador(Foro foro, Usuario moderador);

	/**
	 * Elimina el usuario como moderador del foro
	 * @param foro Foro del que eliminar el moderador
	 * @param moderador Moderador a eliminar
	 */
	public void eliminarModerador(Foro foro, Usuario moderador);
	
	/**
	 * Devuelve todos los Foros de la aplicaci�n
	 * @return Todos los Foros de la aplicaci�n
	 */
	public List<Foro> encontrarTodos();
	
	/**
	 * Busca el foro con el id indicado
	 * @param id Id por el que buscar
	 * @return
	 */
	public Foro encontrarForoPorId(Integer id);

}

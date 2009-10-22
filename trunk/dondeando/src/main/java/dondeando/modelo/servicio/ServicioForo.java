package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;

public interface ServicioForo {
	
	/**
	 * Busca los foros con los parámetros indicados
	 * @param titulo Título del foro a buscar
	 * @param activo Indica si los Foros estarán o no activos
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
	 * Descarta el foro indicado de la sessión de Hibernate
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
	 * @param titulo Título del foro
	 * @param descripcion Descripción del foro
	 * @return Foro creado
	 */
	public Foro crearForo(String titulo, String descripcion);
	
	/**
	 * Edita el foro con los datos indicados 
	 * @param foro Foro a editar
	 * @param titulo Nuevo título para el foro
	 * @param descripcion Nueva descripción para el foro
	 */
	public void editarForo(Foro foro, String titulo, String descripcion);
	
	/**
	 * Busca un foro con el título indicado
	 * @param titulo	Título por el que buscar algún Foro
	 * @return Foro encontrado
	 */
	public Foro encontrarForoPorTitulo(String titulo);

}

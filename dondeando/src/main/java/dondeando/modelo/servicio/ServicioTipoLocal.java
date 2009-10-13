package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoLocal;

public interface ServicioTipoLocal {

	/**
	 * Devuelve todos los tipos de locales de la aplicación
	 * @return	Todos los tipos de locales de la aplicación
	 */
	public List<TipoLocal> encontrarTodos();
	
	/**
	 * Devuelve los locales con los ids indicados
	 * @param ids Ids por los que buscar los locales
	 * @return Locales encontrados
	 */
	public List<TipoLocal> encontrarPorIds(List<Integer> ids);

}

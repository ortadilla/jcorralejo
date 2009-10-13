package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoLocal;

public interface ServicioTipoLocal {

	/**
	 * Devuelve todos los tipos de locales de la aplicaci�n
	 * @return	Todos los tipos de locales de la aplicaci�n
	 */
	public List<TipoLocal> encontrarTodos();
	
	/**
	 * Devuelve los locales con los ids indicados
	 * @param ids Ids por los que buscar los locales
	 * @return Locales encontrados
	 */
	public List<TipoLocal> encontrarPorIds(List<Integer> ids);

}

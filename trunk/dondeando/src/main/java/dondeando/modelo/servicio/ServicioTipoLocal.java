package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoLocal;

public interface ServicioTipoLocal {

	/**
	 * Devuelve todos los tipos de locales de la aplicaci�n
	 * @return	Todos los tipos de locales de la aplicaci�n
	 */
	public List<TipoLocal> encontrarTodos();

}

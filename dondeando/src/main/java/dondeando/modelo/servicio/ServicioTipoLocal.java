package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoLocal;

public interface ServicioTipoLocal {

	/**
	 * Devuelve todos los tipos de locales de la aplicación
	 * @return	Todos los tipos de locales de la aplicación
	 */
	public List<TipoLocal> encontrarTodos();

}

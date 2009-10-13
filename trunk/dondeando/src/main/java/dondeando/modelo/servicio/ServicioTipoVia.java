package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoVia;

public interface ServicioTipoVia {
	
	/**
	 * Devuelve todos los Tipos de Vías de la aplicación
	 * @return	Todos los Tipos de Vías de la aplicación
	 */
	public List<TipoVia> encontrarTodos();

}

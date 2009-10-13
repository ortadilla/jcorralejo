package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoVia;

public interface ServicioTipoVia {
	
	/**
	 * Devuelve todos los Tipos de V�as de la aplicaci�n
	 * @return	Todos los Tipos de V�as de la aplicaci�n
	 */
	public List<TipoVia> encontrarTodos();

}

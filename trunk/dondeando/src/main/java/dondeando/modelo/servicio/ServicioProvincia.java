package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Provincia;

public interface ServicioProvincia {

	/**
	 * Devuelve todas las Provincias de la aplicación
	 * @return	Todas las Provincias de la aplicación
	 */
	public List<Provincia> encontrarTodos();
}

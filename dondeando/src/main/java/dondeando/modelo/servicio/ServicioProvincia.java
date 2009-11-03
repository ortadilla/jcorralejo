package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Provincia;

public interface ServicioProvincia {

	/**
	 * Devuelve todas las Provincias de la aplicaci�n
	 * @return	Todas las Provincias de la aplicaci�n
	 */
	public List<Provincia> encontrarTodos();
	
	/**
	 * Busca la provincia con el id indicado
	 * @param id Id por el que buscar
	 * @return
	 */
	public Provincia encontrarProvinciaPorId(Integer id);

}

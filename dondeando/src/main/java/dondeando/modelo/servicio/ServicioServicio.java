package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Servicio;

public interface ServicioServicio {
	
	/**
	 * Devuelve todos los Servicios de los locales
	 * @return Servicios de la locales
	 */
	public List<Servicio> encontrarTodos();

}

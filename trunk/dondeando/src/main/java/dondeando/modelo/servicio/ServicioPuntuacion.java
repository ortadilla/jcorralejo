package dondeando.modelo.servicio;

import java.math.BigDecimal;
import java.util.List;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Puntuacion;
import dondeando.modelo.entidades.Usuario;

public interface ServicioPuntuacion {

	/**
	 * Rellena las propiedades no mapeadas de la lista de Puntuaciones
	 * @param listaForos Lista de Puntuaciones a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Puntuacion> listaPuntuaciones);
	
	/**
	 * Crea una puntuaci�n con los datos indicados
	 * @param local Local de la puntuaci�n
	 * @param comida Puntuaci�n para la comida
	 * @param ambiente Puntuaci�n para el ambiente
	 * @param servicio Puntuaci�n para el servicio
	 * @param calidadPrecio Puntuaci�n para la relaci�n calidad/precio
	 * @param loMejor Lo mejor del local
	 * @param loPeor Lo peor del local
	 * @param usuario Usuario que hace la puntuaci�n
	 * @return Puntuaci�n creada
	 */
	public Puntuacion crearPuntuacionLocal(Local local, 
										   BigDecimal comida, 
										   BigDecimal ambiente, 
										   BigDecimal servicio, 
										   BigDecimal calidadPrecio, 
										   String loMejor, 
										   String loPeor, 
										   Usuario usuario);
	
	/**
	 * Devuelve todas las votaciones del sistema
	 * @return Lista con las votaciones
	 */
	public List<Puntuacion> encontrarTodas();
}

package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Interes;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoInteres;
import dondeando.modelo.entidades.Usuario;

public interface ServicioInteres {

	
	/**
	 * Elimina de BD el interes indicado
	 * @param interes Interes a borrar
	 */
	public void eliminarOpinion(Interes interes);
	
	/**
	 * Crea un Interes con los datos indicados
	 * @param usuario usuario del interes
	 * @param tipoInteres Tipo para el interes
	 * @param foro Foro del interes
	 * @param local Local del interes
	 * @param provincia Provincia del interes
	 * @param avisarEmail indica si hay que avisar por email
	 */
	public void crearInteres(Usuario usuario, TipoInteres tipoInteres, Foro foro, Local local, Provincia provincia, boolean avisarEmail);

	/**
	 * Rellena las propiedades no mapeadas de los interes indicados
	 * @param interes Interes a los que rellenar sus propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Interes> interes);
}

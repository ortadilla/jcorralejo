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
	
	/**
	 * Busca el Interés para el usuario, tipo y objeto indicado
	 * @param usuario Usuario para el que buscar el interés
	 * @param tipoInteres Tipo de Interés por el que buscar
	 * @param idObjeto Id del Objeto por el que buscar
	 * @return Interés encontrado
	 */
	public Interes encontrarInteresPorUsuarioTipoYObjeto(Usuario usuario, TipoInteres tipoInteres, Integer idObjeto);
	
	/**
	 * Busca el Interés para el tipo y objeto indicado
	 * @param tipoInteres Tipo de Interés por el que buscar
	 * @param idObjeto Id del Objeto por el que buscar
	 * @return Interés encontrado
	 */
	public List<Interes> encontrarNotificacionesPorTipoYObjeto(TipoInteres tipoInteres, Integer idObjeto);
}

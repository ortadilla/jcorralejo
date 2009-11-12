package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Opinion;
import dondeando.modelo.entidades.Usuario;

public interface ServicioOpinion {
	
	/**
	 * Rellena las propiedades no mapeadas de la lista de Opiniones
	 * @param listaForos Lista de Opiniones a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Opinion> listaOpiniones);
	
	/**
	 * Elimina de BD la opini�n indicado
	 * @param opinion Opinion a eliminar
	 */
	public void eliminarOpinion(Opinion opinion);
	
	/**
	 * Crea una opini�n con los datos indicados
	 * @param local Local sobre el que se opina
	 * @param opinion Opini�n en s�	
	 * @param usuario Usuario que opina
	 * @return Opinion creada
	 */
	public Opinion crearOpinion(Local local, String opinion, Usuario usuario);

	/**
	 * Edita la opini�n con los datos indicados
	 * @param opinionEdicion Opini�n a editar
	 * @param mensaje Nuevo mensaje de la opini�n
	 */
	public void editarOpinion(Opinion opinionEdicion, String mensaje);
	
	/**
	 * Busca todas las opinones del sistema
	 * @return Lista con todas las opiniones
	 */
	public List<Opinion> encontrarTodas();
}

package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Opinion;

public interface ServicioOpinion {
	
	/**
	 * Rellena las propiedades no mapeadas de la lista de Opiniones
	 * @param listaForos Lista de Opiniones a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Opinion> listaOpiniones);
	
	/**
	 * Elimina de BD la opinión indicado
	 * @param opinion Opinion a eliminar
	 */
	public void eliminarOpinion(Opinion opinion);

}

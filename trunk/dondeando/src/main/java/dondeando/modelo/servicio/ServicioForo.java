package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;

public interface ServicioForo {
	
	/**
	 * Busca los foros con los par�metros indicados
	 * @param titulo T�tulo del foro a buscar
	 * @param activo Indica si los Foros estar�n o no activos
	 * @return Lista de foros encontrados
	 */
	public List<Foro> encontrarForosPorTituloYActivo(String titulo, Boolean activo);
	
	/**
	 * Rellena las propiedades no mapeadas de la lista de foros indicada
	 * @param listaForos Lista de foros a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<Foro> listaForos);

}

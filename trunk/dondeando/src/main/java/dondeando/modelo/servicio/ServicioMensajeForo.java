package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;

public interface ServicioMensajeForo {
	
	/**
	 * Busca los temas del foro indicado
	 * @param foro Foro para el que buscar los temas
	 * @return Lista de temas del Foro
	 */
	List<MensajeForo> encontrarTemasDeForo(Foro foro);
	
	/**
	 * Rellena las propiedades no mapeadas de la lista de MensajesForo
	 * @param listaForos Lista de Mensajes a la que rellenar las propiedades no mapeadas
	 */
	public void rellenarPropiedadesNoMapeadas(List<MensajeForo> listaMensajesForo);
	
	/**
	 * Elimina de BD el tema indicado, junto con todas sus respuestas
	 * @param tema Tema a eliminar
	 */
	public void eliminarTema(MensajeForo tema);
	

	/**
	 * Crea un mensaje con los datos indicados
	 * @param tema	Tema del mensaje
	 * @param asunto	Asunto del mensaje
	 * @param mensaje	Mensaje en sí
	 * @return Mensaje creado
	 */
	public MensajeForo crearMensajeForo(MensajeForo tema, String asunto, String mensaje);

	


}

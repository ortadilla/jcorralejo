package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.MensajeForo;
import dondeando.modelo.entidades.Usuario;

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
	 * Elimina de BD el mensaje indicado
	 * @param mensaje Mensaje a eliminar
	 */
	public void eliminarMensaje(MensajeForo mensaje);
	

	/**
	 * Crea un mensajeForo con los datos indicados
	 * @param foro Foro del mensaje
	 * @param tema	Tema del mensaje
	 * @param asunto	Asunto del mensaje
	 * @param mensaje	Mensaje en sí
	 * @param autor	Autor del mensaje
	 * @return	Mensaje creado
	 */
	public MensajeForo crearMensajeForo(Foro foro, MensajeForo tema, String asunto, String mensaje, Usuario autor);

	/**
	 * Actualiza el mensajeForo con los datos indicados
	 * @param mensajeForo mensajeForo a actualizar
	 * @param asunto	Asunto del mensaje
	 * @param mensaje	Mensaje en sí
	 * @param autor	Autor del mensaje
	 */
	public void editarMensajeForo(MensajeForo mensajeForo, String asunto, String mensaje, Usuario autor);


}

package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.Usuario;

public interface ServicioNotificacion {
	
	/**
	 * Env�as las notificaciones asociadas al tipo y el objeto
	 */
	public void enviarNotificacionesTipoObjeto(Integer idTipoInteres, Object objetoObjetivo, Object objetoMensaje);
	
	/**
	 * Indica si el Usuario activo tiene notificaciones pendientes
	 * @return TRUE si el Usuario activo tiene notificaciones pendientes
	 */
	public boolean hayNotificacionesPendientes();

	/**
	 * Marca la notificaci�n como leida
	 * @param notificacion Notificaci�n a marcar
	 */
	public void leer(Notificacion notificacion);
	
	/**
	 * Busca las notificaci�n del usuario con el estado indicado
	 * @param usuario usuario de las notificaciones
	 * @param leida Si las notificaciones ya fueron leidas o no
	 * @return Lista de notificaciones encontradas
	 */
	public List<Notificacion> encontrarNotificacionesPorUsuarioYEstado(Usuario usuario, boolean leida);
}

package dondeando.modelo.servicio;

public interface ServicioNotificacion {
	
	/**
	 * Envías las notificaciones asociadas al tipo y el objeto
	 */
	public void enviarNotificacionesTipoObjeto(Integer idTipoInteres, Object objetoObjetivo, Object objetoMensaje);
	
	/**
	 * Indica si el Usuario activo tiene notificaciones pendientes
	 * @return TRUE si el Usuario activo tiene notificaciones pendientes
	 */
	public boolean hayNotificacionesPendientes();

}

package dondeando.modelo.servicio;

public interface ServicioNotificacion {
	
	/**
	 * Env�as las notificaciones asociadas al tipo y el objeto
	 */
	public void enviarNotificacionesTipoObjeto(Integer idTipoInteres, Object objetoObjetivo, Object objetoMensaje);

}

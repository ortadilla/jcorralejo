package dondeando.modelo.servicio;

public interface ServicioPermisoUsuario {
	
	/**
	 * Comprueba si el Usuario activo el permiso indicado
	 * @param permiso	Permiso que comprobar
	 * @return	TRUE si el usuario activo tiene permiso
	 */
	public boolean hayPermiso(int permiso);

}

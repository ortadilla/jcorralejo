package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.TipoUsuario;

public interface ServicioTipoUsuario {
	
	/**
	 * Devuelve una lista con todos los tipos de usuarios que existen en la aplicaci�n
	 * a excepci�n del an�nimo, que es un tipo interno
	 * @return	Lista con los tipos de usuarios de la aplicaci�n
	 */
	public List<TipoUsuario> devolverTodosTipoUsuarioMenosAnonimo();

	/**
	 * Devuelve el tipoUsuario para los usuario registrados normales
	 * @return	TipoUsuario registrado
	 */
	public TipoUsuario devolverTipoUsuarioRegistrado();
}

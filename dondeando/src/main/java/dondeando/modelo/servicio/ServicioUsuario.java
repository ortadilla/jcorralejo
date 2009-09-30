package dondeando.modelo.servicio;

import java.util.List;

import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;

public interface ServicioUsuario {
	
    /**
     * Indica si el usuario existe, está activo y tiene la password indicada y lo guarda en
     * la sesión.
     * @param login     Login del usuario
     * @param passw  Contraseña sin encriptar
     * @return          True si el usuario existe, está activo y tiene la password indicada.
     */
	public boolean autenticar(String login, String passw);
	
    /** Elimina de la sesión el usuario logueado */
    public void logout();
    
    /**
     * Devuelve el usuario actualmente conectado al sistema.
     * @return El usuario que actualmente se encuentra conectado.
     */
    public Usuario devolverUsuarioActivo();
    
    /**
     * Comprueba si el usuario activo es anónimo
     * @return TRUE si el usuario activo es anónimo
     */
    public boolean isUsuarioActivoAnonimo();
    
    /**
	 * Busca el usuario con el login pasado por parámetros
	 * @param login Login del usuario que se busca
	 * @return	Usuario cuyo login coincide con el pasado por parámetros
	 */
	public Usuario encontrarUsuarioPorLogin(String login);
	
	/**
	 * Crea un usuario con los datos pasados por parámetros
	 * @param login	Login del usuario
	 * @param password Password del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param direccion Dirección del usuario
	 * @param email Email del usuario
	 * @param tipoUsuario Tipo de usuario
	 * @param imagen Imagen del avatar del usuario
	 * @return usuario creado
	 */
	public Usuario crearUsuario(String login, String password, String nombre, 
								String apellidos, String direccion, String email, 
								TipoUsuario tipoUsuario, Imagen imagen);
    
	/**
	 * Edita el usuario con los datos indicados
	 * @param usuario Usuario a editar
	 * @param login	Login del usuario
	 * @param password Password del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param direccion Dirección del usuario
	 * @param email Email del usuario
	 * @param tipoUsuario Tipo de usuario
	 * @param imagen Imagen del avatar del usuario
	 */
	public void editarUsuario(Usuario usuario, String login, String password, String nombre, 
							  String apellidos, String direccion, String email, 
							  TipoUsuario tipoUsuario,
							  Imagen imagen);
	
	/**
	 * Modifica el password del Usuario indicado
	 * @param usuario Usuario al que modificar el password 
	 * @param password Nuevo password para el usuario
	 */
	public void modificarPasswordUsuario(Usuario usuario, String password);
	
	/**
	 * Desactiva el usuario indicado
	 * @param usuario	Usuario a desactivar
	 */
	public void desactivarUsuario(Usuario usuario);
	
	/**
	 * Activa el usuario indicado
	 * @param usuario	Usuario a activa
	 */
	public void activarUsuario(Usuario usuario);
	
	/**
	 * Descarta el usuario indicado de la sessión de Hibernate
	 * @param usuario
	 */
	public void descartarUsuario(Usuario usuario);
	
	/**
	 * Devuelve todos los usuarios del sistema
	 * @return Lista de todos los usuarios del sistema
	 */
	public List<Usuario> encontrarTodosUsuarios();

	/**
	 * Busca los usuarios con el login y el campo activo indicado 
	 * @param usuario	Login por el que buscar usuarios. Puede ser null
	 * @param activo	Indica si los usuarios estarán activos o no. Puede ser null
	 * @return	Lista de usuarios encontrados
	 */
	public List<Usuario> encontrarUsuariosPorLoginYActivo(String usuario, Boolean activo);
}

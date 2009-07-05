package dondeando.modelo.servicio.implementacion;


import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.USUARIO_DAO;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Condicion;
import utilidades.busquedas.consultas.Criterio;
import dondeando.modelo.dao.UsuarioDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.UsuarioImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_USUARIO)
public class ServicioUsuarioImpl implements ServicioUsuario {

    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
	
    @In(value=USUARIO_DAO, create=true) 
    private UsuarioDAO usuarioDAO;
    
    private Log log = LogFactory.getLog(ServicioUsuarioImpl.class);
    
    private Integer usuarioLogueado;
    
    
	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioUsuario#autenticar(java.lang.String, java.lang.String)
	 */
	public boolean autenticar(String login, String passw) {
        if(login != null && passw != null){ 
            Usuario us = encontrarUsuarioPorLogin(login.toLowerCase());
            
            //Logueamos errores para depuración, pero no debe mostrarse a usuario.
            if(us==null)
                log.debug("No existe el usuario "+login);
            else if(!us.getPassword().equals(encriptar(passw)))
                log.debug("Password incorrecta"); 
            else if(!us.isActivo())
                log.debug("Usuario innactivo");
            else{
                autenticar(us);
                return true;
            }
        }
        return false;
	}

	/*
	 * (non-Javadoc)
	 * @see dondeando.modelo.servicio.ServicioUsuario#encontrarUsuarioPorLogin(java.lang.String)
	 */
	public Usuario encontrarUsuarioPorLogin(String login) {
		if (login == null) 
			throw new IllegalArgumentException("El argumento login no debe ser nulo");
		List<Usuario> res = usuarioDAO.encontrarPorCondicion(
				servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_LOGIN, Criterio.IGUAL, login));
		
		return res.size() > 0 ? res.get(0) : null;
	}
	
	/**
	 * Encripta el text indicado para que no sea público. Utiliamos el algoritmo SHA-1
	 * @param texto Texto a ecriptar
	 * @return
	 */
	private static String encriptar(String texto){
        if (texto==null)
            return null;
		try {
            return aHexadecimal(MessageDigest.getInstance("SHA-1").digest(texto.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			// No debería ocurrir
			throw new RuntimeException("El algoritmo predefinido no es correcto", e);
		}
	}
	
	/**
	 * Pasa a hexadecimal el texto pasado por parámetros
	 * @param bytes Texto a codificar
	 * @return	El texto codificado en hexadecimal
	 */
	protected static String aHexadecimal( byte[] bytes ){
	    StringBuffer sb = new StringBuffer();
	    for(byte bi:bytes){
	        String cadAux = Integer.toHexString((int)(0x00FF & bi)); 
	        if(cadAux.length() < 2) //Hay que añadir un 0 
	            sb.append("0"); 
	        sb.append(cadAux);
	    }
	    return sb.toString();
	}
	
    /**
     * Guarda en la sesión el usuario indicado como usuario logueado.
     * No se guarda directamente el obj Usuario porque da problemas el guardar un obj
     * caliente en sesión si luego lo comparten distintas conversaciones, especialmente
     * las temporales que se crean en los popups.
     * @param usuario
     */
    private void autenticar(Usuario usuario) {
        usuarioLogueado = usuario!=null ? usuario.getId() : null;
    }

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#logout()
     */
    public void logout(){
        usuarioLogueado = null;
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#devolverUsuarioActivo()
     */
    public Usuario devolverUsuarioActivo() {
    	Usuario usuarioActivo = null;
    	if(usuarioLogueado==null){
    		log.debug("No hay usuario en sesión. Cargando usuario anónimo...");
    		usuarioActivo = devolverUsuarioAnonimo();
    		if(usuarioActivo!=null)
    		usuarioLogueado = usuarioActivo.getId();
    	}else{
    		try{
    			usuarioActivo = usuarioDAO.encontrarPorId(usuarioLogueado);
    		}catch(DAOExcepcion de){
    			//no debería ocurrir
    		}
    	}
    	return usuarioActivo;
    }

    /**
     * Devuelve un usuario anónimo del sistema (no registrado)
     * @return El usuario anónimo
     */
    private Usuario devolverUsuarioAnonimo(){
    	return encontrarUsuarioPorLogin(Usuario.USUARIO_ANONIMO);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#crearUsuario(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, dondeando.modelo.entidades.TipoUsuario)
     */
    public Usuario crearUsuario(String login, String password, String nombre, 
    							String apellidos, String direccion, String email, 
    							TipoUsuario tipoUsuario, Imagen imagen){
    	Usuario usuario = new UsuarioImpl();
    	setearDatosUsuario(usuario, login, password, nombre, apellidos, direccion, email, tipoUsuario, imagen);
    	usuarioDAO.hacerPersistente(usuario);
    	
    	return usuario;
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#editarUsuario(dondeando.modelo.entidades.Usuario, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, dondeando.modelo.entidades.TipoUsuario)
     */
    public void editarUsuario(Usuario usuario, String login, String password, String nombre, 
    						  String apellidos, String direccion, String email,	
    						  TipoUsuario tipoUsuario, Imagen imagen) {
    	setearDatosUsuario(usuario, login, password, nombre, apellidos, direccion, email, tipoUsuario, imagen);
    	try {
			usuarioDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del usuario "+e);
		}
    }
    
    /**
     * Setea al usuario los datos indicados
     * @param usuario	usuario al que setear los datos
	 * @param login	Login del usuario
	 * @param password Password del usuario
	 * @param nombre Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param direccion Dirección del usuario
	 * @param email Email del usuario
	 * @param tipoUsuario Tipo de usuario
     */
    private void setearDatosUsuario(Usuario usuario, String login, String password, String nombre, 
									String apellidos, String direccion, String email, 
									TipoUsuario tipoUsuario, Imagen imagen){
    	usuario.setLogin(login);
    	if(password!=null)
    		usuario.setPassword(encriptar(password));
    	usuario.setNombre(nombre);
    	usuario.setApellidos(apellidos);
    	usuario.setDireccion(direccion);
    	usuario.setEmail(email);
    	usuario.setTipoUsuario(tipoUsuario);
    	usuario.setActivo(true); //Si ponemos confirmación por email, sería false
    	usuario.setKarma(new BigDecimal(5)); //Pendiente de ver su cálculo
    	usuario.setAvatar(imagen);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#isUsuarioActivoAnonimo()
     */
    public boolean isUsuarioActivoAnonimo(){
    	Usuario usuarioAnonimo = devolverUsuarioAnonimo();
		return usuarioLogueado==null || usuarioAnonimo==null || usuarioAnonimo.getId().equals(usuarioLogueado);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#modificarPasswordUsuario(dondeando.modelo.entidades.Usuario, java.lang.String)
     */
    public void modificarPasswordUsuario(Usuario usuario, String password){
    	usuario.setPassword(encriptar(password));
    	try {
			usuarioDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del usuario "+e);
		}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#desactivarUsuario(dondeando.modelo.entidades.Usuario)
     */
    public void desactivarUsuario(Usuario usuario) {
    	usuario.setActivo(false);
    	try {
			usuarioDAO.flush();
		} catch (DAOExcepcion e) {
			log.debug("Error al actualizar los datos del usuario "+e);
		}
    }

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#activarUsuario(dondeando.modelo.entidades.Usuario)
     */
    public void activarUsuario(Usuario usuario) {
    	usuario.setActivo(true);
    	try {
    		usuarioDAO.flush();
    	} catch (DAOExcepcion e) {
    		log.debug("Error al actualizar los datos del usuario "+e);
    	}
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#descartarUsuario(dondeando.modelo.entidades.Usuario)
     */
    public void descartarUsuario(Usuario usuario) {
    	usuarioDAO.descartar(usuario);
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#encontrarTodosUsuarios()
     */
    public List<Usuario> encontrarTodosUsuarios() {
    	return usuarioDAO.encontrarTodos();
    }
    
    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#encontrarUsuariosPorLoginYActivo(java.lang.String, boolean)
     */
    public List<Usuario> encontrarUsuariosPorLoginYActivo(String usuario, boolean activo) {
    	Condicion condicion = servicioCriterios.creaCondicion();
    	if(usuario!=null && !"".equals(usuario))
    		condicion.agregar(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_LOGIN, Criterio.IGUAL, usuario));
    	condicion.agregar(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_ACTIVO, Criterio.IGUAL, activo));
    	return usuarioDAO.encontrarPorCondicion(condicion.getCriterios());
    }
    
    public static void main(String[] args){
    	String clave =  encriptar("anonimo");
    	System.out.println(clave);
    }
}

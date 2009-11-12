package dondeando.modelo.servicio.implementacion;


import static dondeando.modelo.entidades.Usuario.USUARIO_ANONIMO;
import static org.jboss.seam.ScopeType.SESSION;
import static utilidades.varios.NombresBean.SERVICIO_CRITERIOS;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.USUARIO_DAO;
import static utilidades.varios.NombresBean.USUARIO_LOGUEADO;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.busquedas.consultas.Criterio;
import dondeando.modelo.dao.UsuarioDAO;
import dondeando.modelo.dao.excepciones.DAOExcepcion;
import dondeando.modelo.entidades.Imagen;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.entidades.implementacion.UsuarioImpl;
import dondeando.modelo.servicio.ServicioCriterios;
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioTipoInteres;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(SERVICIO_USUARIO)
public class ServicioUsuarioImpl implements ServicioUsuario {

    @In(value=SERVICIO_CRITERIOS, create=true)
    private ServicioCriterios servicioCriterios;
	
    @In(value=SERVICIO_NOTIFICACION, create=true)
    private ServicioNotificacion servicioNotificacion;
    
    @In(value=USUARIO_DAO, create=true) 
    private UsuarioDAO usuarioDAO;
    
    private Log log = LogFactory.getLog(ServicioUsuarioImpl.class);
    
    @In(value=USUARIO_LOGUEADO, required=false, scope=SESSION)
    @Out(value=USUARIO_LOGUEADO, required=false, scope=SESSION)
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
    	return encontrarUsuarioPorLogin(USUARIO_ANONIMO);
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
    	
    	//Enviamos las notificaciones
    	servicioNotificacion.enviarNotificacionesTipoObjeto(ServicioTipoInteres.TIPO_NUEVO_USUARIO, null, usuario);
    	
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
     * @see dondeando.modelo.servicio.ServicioUsuario#isUsuarioActivoAdmin()
     */
    public boolean isUsuarioActivoAdmin(){
    	Usuario usuarioAdmin = devolverUsuarioActivo();
    	return usuarioAdmin.getTipoUsuario().getId().equals(1);
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
     * @see dondeando.modelo.servicio.ServicioUsuario#encontrarUsuariosPorLoginTipoYActivo(java.lang.String, dondeando.modelo.entidades.TipoUsuario, java.lang.Boolean)
     */
    public List<Usuario> encontrarUsuariosPorLoginTipoYActivo(String usuario, TipoUsuario tipoUsuario, Boolean activo) {
    	List<Criterio> criterios = new ArrayList<Criterio>();
    	if(usuario!=null && !"".equals(usuario))
    		criterios.add(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_LOGIN, Criterio.LIKE, usuario));
    	if(activo!=null)
    		criterios.add(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_ACTIVO, Criterio.IGUAL, activo));
    	if(tipoUsuario!=null)
    		criterios.add(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_TIPO_USUARIO, Criterio.IGUAL, tipoUsuario));
    	//Eliminamos el usuario anónimo
    	criterios.add(servicioCriterios.construyeCriterio(Usuario.ATRIBUTO_LOGIN, Criterio.DISTINTO, USUARIO_ANONIMO));
    	return usuarioDAO.encontrarPorCondicion(criterios);
    }
    
    public static void main(String[] args){
    	String clave =  encriptar("anonimo");
    	System.out.println(clave);
    }

    /*
     * (non-Javadoc)
     * @see dondeando.modelo.servicio.ServicioUsuario#actualizarKarma(java.lang.Integer, java.math.BigDecimal, dondeando.modelo.entidades.Usuario)
     */
    public void actualizarKarma(Integer operacion, BigDecimal actualizacionKarma, Usuario usuario){
    	
    	if(!isUsuarioActivoAnonimo() || usuario!=null){
    		Usuario usuarioActivo = usuario == null ? devolverUsuarioActivo() : usuario;
    		BigDecimal karmaActual = usuarioActivo.getKarma();

    		if(usuarioActivo!=null && karmaActual!=null){
    			BigDecimal siete = new BigDecimal(7);
    			BigDecimal tres = new BigDecimal(3);
    			BigDecimal diez = new BigDecimal(10);
    			BigDecimal incremento = BigDecimal.ZERO;
    			if(OPERACION_AGREGAR_LOCAL.equals(operacion))
    				incremento = new BigDecimal(0.25);
    			else if(OPERACION_DETALLES_LOCAL.equals(operacion) && karmaActual.compareTo(siete)<0)
    				incremento = new BigDecimal(0.01);
    			else if(OPERACION_EDITAR_LOCAL.equals(operacion))
    				incremento = new BigDecimal(0.1);
    			else if(OPERACION_OPINIONAR_LOCAL.equals(operacion))
    				incremento = new BigDecimal(0.1);
    			else if(OPERACION_VALORAR_LOCAL.equals(operacion))
    				incremento = actualizacionKarma; //Se debe calcular fuera
    			else if(OPERACION_AGREGAR_TEMA_MENSAJE_FORO.equals(operacion) && karmaActual.compareTo(siete)<0)
    				incremento = new BigDecimal(0.01);
    			else if(OPERACION_RECIBIR_VOTO_POSITIVO_FORO.equals(operacion) && karmaActual.compareTo(siete)<0)
    				incremento = new BigDecimal(0.01);
    			else if(OPERACION_RECIBIR_VOTO_NEGATIVO_FORO.equals(operacion) && karmaActual.compareTo(tres)>0)
    				incremento = new BigDecimal(-0.01);
    			else if(OPERACION_RECIBIR_VOTO_POSITIVO_OPINION.equals(operacion) && karmaActual.compareTo(siete)<0)
    				incremento = new BigDecimal(0.05);
    			else if(OPERACION_RECIBIR_VOTO_NEGATIVO_OPINION.equals(operacion) && karmaActual.compareTo(tres)>0)
    				incremento = new BigDecimal(-0.05);
    			else if(OPERACION_MÁS_NOTIFICACIONES.equals(operacion))
    				incremento = new BigDecimal(0.1);
    			else if(OPERACION_MENOS_NOTIFICACIONES.equals(operacion))
    				incremento = new BigDecimal(-0.1);
    			else if(OPERACION_PARTICIPAR_ENCUESTA.equals(operacion))
    				incremento = new BigDecimal(0.1);

    			if(incremento.compareTo(diez)>0)
    				incremento = diez;
    			else if (incremento.compareTo(new BigDecimal(-10))<0)
    				incremento = BigDecimal.ZERO;
    			
    			usuarioActivo.setKarma(karmaActual.add(incremento));
    			try {
    				usuarioDAO.flush();
    			} catch (DAOExcepcion e) {
    				log.debug("No se pudo actualizar el karma del usuario", e);
    			}
    		}
    	}
    }
}

package dondeando.bean;

import static org.jboss.seam.ScopeType.CONVERSATION;
import static utilidades.jsf.ConstantesReglasNavegacion.CREAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.LOGIN;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.LOGIN_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.Seam;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import seam.Redirect;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(CONVERSATION)
@Name(LOGIN_BEAN)
public class LoginBean {

	private Log log = LogFactory.getLog(LoginBean.class);
	public static final String NOMBRE_COOKIE = "dondeando.login.user";

	private String username;
	private String password;

	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;

	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;

	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;

    @Create
    @Begin(join=true)
    public void inicializar(){     
        Cookie cookie = (Cookie)getExternalContext().getRequestCookieMap().get(NOMBRE_COOKIE);
        if (cookie!=null)
            username = cookie.getValue();
    }
	
	public void login(){
		if(servicioUsuario.autenticar(username, password)){
			log.info("Login correcto para : " + username);

			//Se guarda una cookie en el cliente para "recordar" el último login
			crearCookie();
			password = null;

			Redirect.redirigirInicio();
		}else{
			log.info("Login erróneo para: " + username);
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("LOGIN_INCORRECTO"));
		}
	}

	private void crearCookie() {
		HttpServletResponse response = (HttpServletResponse)getExternalContext().getResponse();
		Cookie cookie = new Cookie(NOMBRE_COOKIE, username);
		cookie.setMaxAge(31536000); //1 año, en segundos
		response.addCookie(cookie);
	}

//	public String loginUsuario(){
//		return LOGIN;
//	}
//	
//	/** Finaliza la sesión de usuario */
//	@End(beforeRedirect=true)
//	public String logout(){
//		log.info("Finalizando sesión del usuario");
//		servicioUsuario.logout();
//		Seam.invalidateSession();
//		return MENU_PRINCIPAL;
//	}
//	
//	/**
//	 * Comfigura el mapa el mapaArgumentos para navegar al registro de un nuevp usuario
//	 * @return Outcome del registro de usuarios
//	 */
//	public String registrarUsuario(){
//		if(mapaArgumentos==null);
//			mapaArgumentos = new MapaArgumentos();
//		mapaArgumentos.limpiaMapa();
//		
//		ProtocoloEdicion protocolo = new ProtocoloEdicion(null, MENU_PRINCIPAL);
//		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
//		
//		return CREAR_USUARIO;
//	}
	
	private ExternalContext getExternalContext(){
		return FacesContext.getCurrentInstance().getExternalContext();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}
	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}

}

package utilidades.componentes;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_NOTIFICACIONES;
import static utilidades.jsf.ConstantesReglasNavegacion.LOGIN;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.CABECERA_PAGINA_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.ScopeType;
import org.jboss.seam.Seam;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.varios.MapaArgumentos;
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloBusqueda;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioNotificacion;
import dondeando.modelo.servicio.ServicioUsuario;


@Name(CABECERA_PAGINA_BEAN)
@Scope(ScopeType.CONVERSATION)
public class CabeceraPaginasBean {
    
	private Log log = LogFactory.getLog(CabeceraPaginasBean.class);
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
    @In(value=SERVICIO_USUARIO, create=true)
    private ServicioUsuario servicioUsuario;

    @In(value=SERVICIO_NOTIFICACION, create=true)
    private ServicioNotificacion servicioNotificacion;
    
    @Create
    @Begin(join=true)
    public void inicializar(){     
    }
    
    public String getNombreUsuario() {
        String nombre = null; 
        Usuario usuario = servicioUsuario.devolverUsuarioActivo();
        if(usuario!=null)
            nombre = usuario.getNombreCompleto();
        if(nombre==null || nombre.trim().length()==0)
            nombre = usuario.getLogin();
        return nombre;
    }
    
	public String loginUsuario(){
		return LOGIN;
	}
	
	/** Finaliza la sesión de usuario */
	@End(beforeRedirect=true)
	public String logout(){
		log.info("Finalizando sesión del usuario");
		servicioUsuario.logout();
		Seam.invalidateSession();
		return MENU_PRINCIPAL;
	}
	
	/**
	 * Configura el mapaArgumentos para navegar al registro de un nuevo usuario
	 * @return Outcome del registro de usuarios
	 */
	public String registrarUsuario(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(null, 
														  MENU_PRINCIPAL,
														  null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		return EDITAR_USUARIO;
	}

	/**
	 * Configura el mapaArgumento para navegar a la edición del usuario activo
	 * @return Outcome del registro de usuarios
	 */
	public String detallesUsuario(){
		if(mapaArgumentos==null)
			mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();

		ProtocoloEdicion protocolo = new ProtocoloEdicion(servicioUsuario.devolverUsuarioActivo(), 
														  MENU_PRINCIPAL,
														  null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return DETALLES_USUARIO;
	}
	
	@End()
	public String irAlMenu(){
//		Conversation.instance().end();
		return MENU_PRINCIPAL;
	}

	/**
	 * Configura el mapaArgumento para buscar las notificaciones pendientes del usuario
	 * @return Regla de navegación
	 */
	public String accionNotificaciones(){
		ProtocoloBusqueda protocolo = new ProtocoloBusqueda(null, true, MENU_PRINCIPAL, false);
		protocolo.addParametro(Notificacion.ATRIBUTO_USUARIO, servicioUsuario.devolverUsuarioActivo());
		protocolo.addParametro(Notificacion.ATRIBUTO_LEIDA, false);
		mapaArgumentos = new MapaArgumentos(NombresBean.PROTOCOLO_BUSQUEDA, protocolo);
		return GESTION_NOTIFICACIONES;
	}

	/**
	 * Indica si el usuario tiene notificaciones pendientes
	 * @return TRUE si el usuario tiene notificaciones pendientes
	 */
	public boolean isHayNotificacionesPendientes(){
		return servicioNotificacion.hayNotificacionesPendientes();
	}

	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}
	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}
    
}

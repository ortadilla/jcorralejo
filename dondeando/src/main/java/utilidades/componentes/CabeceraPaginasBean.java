package utilidades.componentes;

import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_CREAR_USUARIO;
import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_DETALLES_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.CREAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.LOGIN;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.CABECERA_PAGINA_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
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

import utilidades.jsf.ConstantesArgumentosNavegacion;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.ProtocoloEdicion;

import dondeando.modelo.entidades.Usuario;
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
    
//    @Create
//    @Begin(join=true)
//    public void inicializar(){     
//    }
    
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
		if(mapaArgumentos==null)
			mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		
		ProtocoloEdicion protocolo = new ProtocoloEdicion(null, MENU_PRINCIPAL, OPERACION_CREAR_USUARIO);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		
		return CREAR_USUARIO;
	}

	/**
	 * Configura el mapaArgumento para navegar a la edición del usuario activo
	 * @return Outcome del registro de usuarios
	 */
	public String editarUsuario(){
		if(mapaArgumentos==null)
			mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();

		ProtocoloEdicion protocolo = new ProtocoloEdicion(servicioUsuario.devolverUsuarioActivo(), 
														  MENU_PRINCIPAL,
														  OPERACION_DETALLES_USUARIO);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return CREAR_USUARIO;
	}
	
	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}
	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}
    
}

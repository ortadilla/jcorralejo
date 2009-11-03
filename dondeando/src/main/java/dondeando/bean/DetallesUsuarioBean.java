package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_COFIGURACION_NOTIFICACIONES_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.jsf.ConstantesReglasNavegacion.MODIFICAR_PASSWORD;
import static utilidades.varios.NombresBean.CABECERA_PAGINA_BEAN;
import static utilidades.varios.NombresBean.DETALLES_USUARIO_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_IMAGEN;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.math.BigDecimal;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.componentes.CabeceraPaginasBean;
import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.ProtocoloEdicion;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioImagen;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(DETALLES_USUARIO_BEAN)
public class DetallesUsuarioBean {

	private String login;
	private String password;
	private String password2;
	private String nombre;
	private String apellidos;
	private String direccion; //TODO: Pendiente de GoogleMaps
	private String email;
	private BigDecimal karma;
	private TipoUsuario tipoUsuario;
	
	/** Indica si se puede modificar los datos del usuario */
	private boolean mantenerUsuario;

	private String urlImagenUsuario;

	private Usuario usuarioEdicion;
	
	//Utilidades
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
    @In(value=CABECERA_PAGINA_BEAN, create=true)
    private CabeceraPaginasBean cabeceraPaginasBean;
    
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	private ProtocoloEdicion protocoloEdicion;
	
	//Servicios
	@In(value=SERVICIO_IMAGEN, create=true)
	private ServicioImagen servicioImagen;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
	}

	public void cargarArgumentosDeEntrada(){
		
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloEdicion())
			protocoloEdicion = mapaArgumentos.getProtocoloEdicion();
		
		if(protocoloEdicion!=null){
			
			//Comprobamos si estamos editando un usuario ya existente
			if(protocoloEdicion.getObjeto()!=null){
				usuarioEdicion = (Usuario)protocoloEdicion.getObjeto();
				login = usuarioEdicion.getLogin();
				password = null;
				password2 = null;
				nombre = usuarioEdicion.getNombre();
				apellidos = usuarioEdicion.getApellidos();
				direccion = usuarioEdicion.getDireccion();
				email = usuarioEdicion.getEmail();
				tipoUsuario = usuarioEdicion.getTipoUsuario();
				karma = usuarioEdicion.getKarma();
				
				urlImagenUsuario = servicioImagen.calcularUrlImagenUsuario(usuarioEdicion);
			}else
				urlImagenUsuario = servicioImagen.calcularUrlImagenUsuarioNuevo();
				
		}

		Usuario usuarioActivo = servicioUsuario.devolverUsuarioActivo();
		mantenerUsuario = usuarioActivo.equals(usuarioEdicion); //O es administrador
	}
	
	public String volver(){
		return protocoloEdicion!=null ? protocoloEdicion.getOutcomeVuelta()
									  : MENU_PRINCIPAL;
	}
	
	public String modificar(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(usuarioEdicion,DETALLES_USUARIO, null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return EDITAR_USUARIO;
	}
	
	public String modificarContr(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(usuarioEdicion,DETALLES_USUARIO, null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return MODIFICAR_PASSWORD;
	}
	
	public String eliminar(){
		String outcome = MENU_PRINCIPAL;
		if(usuarioEdicion!=null){
			servicioUsuario.desactivarUsuario(usuarioEdicion);
			cabeceraPaginasBean.logout();
			servicioUsuario.descartarUsuario(usuarioEdicion);
			utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_ELIMINADO"));
		}
		return outcome;
	}
	
	public String configurarNotificaciones(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		ProtocoloEdicion protocolo = new ProtocoloEdicion(usuarioEdicion,DETALLES_USUARIO, null);
		mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);

		return GESTION_COFIGURACION_NOTIFICACIONES_USUARIO;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public MapaArgumentos getMapaArgumentos() {
		return mapaArgumentos;
	}

	public void setMapaArgumentos(MapaArgumentos mapaArgumentos) {
		this.mapaArgumentos = mapaArgumentos;
	}

	public String getUrlImagenUsuario() {
		return urlImagenUsuario;
	}

	public void setUrlImagenUsuario(String urlImagenUsuario) {
		this.urlImagenUsuario = urlImagenUsuario;
	}

	public boolean isMantenerUsuario() {
		return mantenerUsuario;
	}

	public void setMantenerUsuario(boolean mantenerUsuario) {
		this.mantenerUsuario = mantenerUsuario;
	}

	public BigDecimal getKarma() {
		return karma;
	}

	public void setKarma(BigDecimal karma) {
		this.karma = karma;
	}

}

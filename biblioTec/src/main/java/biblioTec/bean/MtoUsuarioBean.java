package biblioTec.bean;

import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_ANIADIR_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_DETALLES_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.ACCION_EDITAR_USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.LANZAR_BUSQUEDA;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.USUARIO;
import static biblioTec.utilidades.ConstantesArgumentosNavegacion.VOLVER_A;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_PRESTAMOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.GESTIONAR_USUARIOS;
import static biblioTec.utilidades.ConstantesReglasNavegacion.MTO_USUARIO;
import static biblioTec.utilidades.NombresBean.MAPA_ARGUMENTOS;
import static biblioTec.utilidades.NombresBean.MTO_USUARIO_BEAN;
import static biblioTec.utilidades.NombresBean.SERVICIO_PERFIL;
import static biblioTec.utilidades.NombresBean.SERVICIO_USUARIO;
import static biblioTec.utilidades.NombresBean.UTIL_JSF_CONTEXT;
import static org.jboss.seam.ScopeType.CONVERSATION;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import biblioTec.modelo.entidades.Perfil;
import biblioTec.modelo.entidades.Usuario;
import biblioTec.modelo.entidades.implementacion.UsuarioImpl;
import biblioTec.modelo.servicios.ServicioPerfil;
import biblioTec.modelo.servicios.ServicioUsuario;
import biblioTec.utilidades.ConstantesArgumentosNavegacion;
import biblioTec.utilidades.MapaArgumentos;
import biblioTec.utilidades.MensajesCore;
import biblioTec.utilidades.SelectItemBuilder;
import biblioTec.utilidades.UtilJsfContext;



@Scope(CONVERSATION)
@Name(MTO_USUARIO_BEAN)
public class MtoUsuarioBean {
	
	private String titulo;
	private String login;
	private String nombre;
	private String descPerfiles;
	public List<Perfil> perfiles;
	private boolean detalles;
	private Usuario usuarioEdicion;
	private SelectItem[] selectPerfiles;
	
    @In(value=MAPA_ARGUMENTOS, required=false)
    @Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
    
	@In(value=SERVICIO_PERFIL, create=true)
	private ServicioPerfil servicioPerfil;
	
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;

	private MensajesCore mensajesCore = MensajesCore.instancia();
	
    @Create
	@Begin(join=true)
	public void inicializar(){
		selectPerfiles = SelectItemBuilder.creaSelectItems(servicioPerfil.encontrarTodos(), 
														   null, 
														   Perfil.ATRIBUTO_DESCRIPCION,
														   true);
	}
    
	public void cargarArgumentosDeEntrada(){
		if(mapaArgumentos!=null){
			Usuario usuario = null;
			if(mapaArgumentos.contiene(ConstantesArgumentosNavegacion.USUARIO)){
				usuario = (Usuario) mapaArgumentos.getArgumento(ConstantesArgumentosNavegacion.USUARIO); 
				login = usuario.getLogin();
				nombre = usuario.getNombre();
				descPerfiles = ((UsuarioImpl)usuario).getDescPerfiles();
				perfiles = new ArrayList<Perfil>(usuario.getPerfiles());
			}
			
			if(mapaArgumentos.contiene(ACCION)){
				String accion = (String)mapaArgumentos.getArgumento(ACCION);
				if(ACCION_ANIADIR_USUARIO.equals(accion)){
					detalles = false;
				}
				else if (ACCION_EDITAR_USUARIO.equals(accion)){
					detalles = false;
					usuarioEdicion = usuario; 
				}
				else if (ACCION_DETALLES_USUARIO.equals(accion)){
					detalles = true;
					usuarioEdicion = usuario;
				}
			}
		}
	}
	
	public String aceptar(){

		String outcome = "";
		
		if(nombre==null || "".equals(nombre)
		|| login==null || "".equals(login)){
			utilJsfContext.insertaMensajeAdvertencia(mensajesCore.obtenerTexto("NOMBRE_LOGIN_OBLIGATORIOS"));
		}else{
			if(usuarioEdicion!=null){
				servicioUsuario.actualizarDatosUsuario(usuarioEdicion, login, nombre, perfiles!=null ? new HashSet<Perfil>(perfiles) : new HashSet<Perfil>());
			}else{
				servicioUsuario.crearUsuario(login, nombre, perfiles!=null ? new HashSet<Perfil>(perfiles) : new HashSet<Perfil>());
				limpiarFormulario();
			}
			outcome = GESTIONAR_USUARIOS;
		}
		return outcome;
	}
	
	public String cancelar(){
		limpiarFormulario();
		return GESTIONAR_USUARIOS;
	}
	
	public String volver(){
		limpiarFormulario();
		return GESTIONAR_USUARIOS;
	}
	
	private void limpiarFormulario(){
		titulo = null;
		login = null;
		nombre = null;
		descPerfiles = null;
	}
	
	public String verPrestamos(){
		if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
		mapaArgumentos.limpiaMapa();
		mapaArgumentos.setArgumento(LANZAR_BUSQUEDA, true);
		mapaArgumentos.setArgumento(USUARIO, usuarioEdicion);
		mapaArgumentos.setArgumento(VOLVER_A, MTO_USUARIO);
		return GESTIONAR_PRESTAMOS;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDetalles() {
		return detalles;
	}

	public void setDetalles(boolean detalles) {
		this.detalles = detalles;
	}

	public String getDescPerfiles() {
		return descPerfiles;
	}

	public void setDescPerfiles(String descPerfiles) {
		this.descPerfiles = descPerfiles;
	}

	public Usuario getUsuarioEdicion() {
		return usuarioEdicion;
	}

	public void setUsuarioEdicion(Usuario usuarioEdicion) {
		this.usuarioEdicion = usuarioEdicion;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public SelectItem[] getSelectPerfiles() {
		return selectPerfiles;
	}

	public void setSelectPerfiles(SelectItem[] selectPerfiles) {
		this.selectPerfiles = selectPerfiles;
	}

}

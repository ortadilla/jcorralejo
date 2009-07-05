package dondeando.bean;

import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_DETALLES_USUARIO;
import static utilidades.jsf.ConstantesArgumentosNavegacion.OPERACION_EDITAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.CREAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.jsf.ConstantesReglasNavegacion.MENU_PRINCIPAL;
import static utilidades.varios.NombresBean.GESTION_USUARIOS_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.List;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesArgumentosNavegacion;
import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloEdicion;

import binding.GestionUsuariosBinding;

import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_USUARIOS_BEAN)
public class GestionUsuariosBean {
	
	//Atributos
	private List<Usuario> listaUsuarios;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private String criterioUsuario;
	private boolean criterioActivo = true;

	//Servicios
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;

	//Utilidades
	@In(value=NombresBean.GESTION_USUARIOS_BINDING, create=true)
	private GestionUsuariosBinding binding;
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		desplegado = false;
		listaUsuarios = servicioUsuario.encontrarTodosUsuarios();
	}
	
	/**
	 * Navega a los detalles del usuario seleccionado
	 * @return Regla de navegación a la pantalla del usuario
	 */
	public String detalles(){
		return navegarAUsuario(false);
	}
	
	/**
	 * Navega para editar el usuario seleccionado
	 * @return Regla de navegación a la pantalla del usuario
	 */
	public String modificar(){
		return navegarAUsuario(true);
	}
	
	/**
	 * Prepara los parámetros para navegar a la pantalla del usuario seleccionado
	 * @param edicion Indica si se navega para editar el usuario, o para ver los detalles
	 * @return	Regla de navegación a la pantalla del usuario
	 */
	private String navegarAUsuario(boolean edicion){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			if(mapaArgumentos==null)
				mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
	
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			ProtocoloEdicion protocolo = new ProtocoloEdicion(listaUsuarios.get(seleccion),
															  GESTION_USUARIOS,
															  edicion ? OPERACION_EDITAR_USUARIO : OPERACION_DETALLES_USUARIO);
			mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
	
			outcome = CREAR_USUARIO;
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		return outcome;
	}
	
	public void eliminar(){
		
	}
	
	/**
	 * Busca los usuarios que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaUsuarios = servicioUsuario.encontrarUsuariosPorLoginYActivo(criterioUsuario, criterioActivo);
		desplegado = false;
		binding.getBusqueda().setDisclosed(desplegado);
	}
	
	public void limpiar(){
		criterioActivo = true;
		criterioUsuario = null;
		binding.getBusqueda().getChildren();
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}

	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}

	public boolean isDesplegado() {
		return desplegado;
	}

	public void setDesplegado(boolean desplegado) {
		this.desplegado = desplegado;
	}

	public String getCriterioUsuario() {
		return criterioUsuario;
	}

	public void setCriterioUsuario(String criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}

	public boolean isCriterioActivo() {
		return criterioActivo;
	}

	public void setCriterioActivo(boolean criterioActivo) {
		this.criterioActivo = criterioActivo;
	}
	
}

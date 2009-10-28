package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_USUARIO;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_USUARIOS;
import static utilidades.varios.NombresBean.GESTION_USUARIOS_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.PROTOCOLO_RESULTADO;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_USUARIO;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.myfaces.trinidad.component.core.layout.CoreShowDetailHeader;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.UtilJsfContext;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.NombresBean;
import utilidades.varios.ProtocoloBusqueda;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.binding.GestionUsuariosBinding;
import dondeando.modelo.entidades.TipoUsuario;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioTipoUsuario;
import dondeando.modelo.servicio.ServicioUsuario;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_USUARIOS_BEAN)
public class GestionUsuariosBean {
	
	//Constantes
	private static final String ACCION_DETALLES_USUARIO = "_detallesUsuario_";
	private static final String ACCION_MODIFICAR_USUARIO = "_modificarUsuario_";
	private static final String ACCION_ELIMINAR_USUARIO = "_eliminarUsuario_";
	private static final String ACCION_RECUPERAR_USUARIO = "_recuperarUsuario_";
	
	//Atributos
	private List<Usuario> listaUsuarios;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private boolean buscando;
	private String criterioUsuario;
	private TipoUsuario criterioTipoUsuario;
	private Boolean criterioActivo = Boolean.TRUE;
	
	private ProtocoloBusqueda protocoloBusqueda;
	private SelectItem[] selectSiNo;
	private SelectItem[] selectTipoUsuario;

	//Servicios
	@In(value=SERVICIO_USUARIO, create=true)
	private ServicioUsuario servicioUsuario;
	
	@In(value=SERVICIO_TIPO_USUARIO, create=true)
	private ServicioTipoUsuario servicioTipoUsuario;

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
		selectSiNo = SelectItemBuilder.creaSelectItemsSiNo();
		selectTipoUsuario = SelectItemBuilder.creaSelectItems(servicioTipoUsuario.devolverTodosTipoUsuarioMenosAnonimo(), 
				  											  null, 
				  											  TipoUsuario.ATRIBUTO_DESCRIPCION,
				  											  true);
		desplegado = false;
//		buscar();
	}
	
	public void cargarArgumentosDeEntrada(){
		//Cargar los datos y lanzar la búsqueda
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloBusqueda())
			protocoloBusqueda = mapaArgumentos.getProtocoloBusqueda();
		
		if(protocoloBusqueda!=null){
			buscando = true;
			if(protocoloBusqueda.isLanzaConsulta())
				buscar();
		}
	}
	
	/**
	 * Realiza la operación concreta sobre el usuario seleccionado, que puede ser eliminar,
	 * ver detalles y modificar
	 * @param edicion Indica si se navega para editar el usuario, o para ver los detalles
	 * @return	Regla de navegación necesaria para realizar la acción
	 */
	private String realizarOperacion(String operacion){
		String outcome = "";
		boolean operacionRealizada = false;
		if(estadoDeSeleccionTabla.size()==1){
			
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(ACCION_MODIFICAR_USUARIO.equals(operacion) || ACCION_DETALLES_USUARIO.equals(operacion)){
				
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(usuario,GESTION_USUARIOS ,null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		
				outcome = ACCION_MODIFICAR_USUARIO.equals(operacion) ? EDITAR_USUARIO : DETALLES_USUARIO;
				operacionRealizada = true;
			
			}else if(ACCION_ELIMINAR_USUARIO.equals(operacion)){
				
				if(usuario!=null && usuario.isActivo()){
					servicioUsuario.desactivarUsuario(usuario);
					servicioUsuario.descartarUsuario(usuario);
					utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_ELIMINADO"));
					operacionRealizada = true;
				}else
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_USUARIO_ELIMINADO"));

			}else if(ACCION_RECUPERAR_USUARIO.equals(operacion)){

				if(usuario!=null && !usuario.isActivo()){
					servicioUsuario.activarUsuario(usuario);
					utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("USUARIO_RECUPERADO"));
					operacionRealizada = true;
				}else
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_RECUPERAR_USUARIO_ACTIVO"));
			}
			
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		if(operacionRealizada)
			estadoDeSeleccionTabla.clear();
		
		return outcome;
	}
	
	/**
	 * Navega a los detalles del usuario seleccionado
	 * @return Regla de navegación a la pantalla del usuario
	 */
	public String detalles(){
		return realizarOperacion(ACCION_DETALLES_USUARIO);
	}
	
	/**
	 * Navega para editar el usuario seleccionado
	 * @return Regla de navegación a la pantalla del usuario
	 */
	public String modificar(){
		return realizarOperacion(ACCION_MODIFICAR_USUARIO);
	}
	
	/**
	 * Elimina el usuario seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_USUARIO);
	}
	
	/**
	 * Recupera el usuario seleccionado
	 */
	public void recuperar(){
		realizarOperacion(ACCION_RECUPERAR_USUARIO);
	}
	
	/**
	 * Busca los usuarios que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaUsuarios = servicioUsuario.encontrarUsuariosPorLoginTipoYActivo(criterioUsuario, criterioTipoUsuario, criterioActivo);
		desplegado = false;
		if(binding.getBusqueda()==null) binding.setBusqueda(new CoreShowDetailHeader());
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaUsuarios!=null && listaUsuarios.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Limpia los criterios de búsqueda
	 */
	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioActivo = null;
		criterioTipoUsuario = null;
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaUsuarios != null ? listaUsuarios.size() : "0");
    }
	
	/**
	 * Cancela la selección de usuarios
	 * @return Regla de navegación
	 */
	public String cancelar(){
		String outcome = protocoloBusqueda!=null ? protocoloBusqueda.getOutcomeVuelta() : "";
		protocoloBusqueda = null; //para que al volver no se cargue nada
		estadoDeSeleccionTabla.clear();
		buscando = false;
		return outcome;
	}
	
	/**
	 * Devuelve el usuario seleccionado
	 * @return Regla de navegación
	 */
	public String aceptar(){
		String outcome = "";
		if(estadoDeSeleccionTabla.size()==1){
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Usuario usuario = listaUsuarios.get(seleccion);
			if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
			mapaArgumentos.limpiaMapa();
			mapaArgumentos.setArgumento(PROTOCOLO_RESULTADO, usuario);
			
			outcome = protocoloBusqueda!=null ? protocoloBusqueda.getOutcomeVuelta() : "";
			protocoloBusqueda = null; //para que al volver no se cargue nada
			estadoDeSeleccionTabla.clear();
			buscando = false;
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
			
		return outcome;
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

	public SelectItem[] getSelectSiNo() {
		return selectSiNo;
	}

	public void setSelectSiNo(SelectItem[] selectSiNo) {
		this.selectSiNo = selectSiNo;
	}

	public Boolean getCriterioActivo() {
		return criterioActivo;
	}

	public void setCriterioActivo(Boolean criterioActivo) {
		this.criterioActivo = criterioActivo;
	}

	public TipoUsuario getCriterioTipoUsuario() {
		return criterioTipoUsuario;
	}

	public void setCriterioTipoUsuario(TipoUsuario criterioTipoUsuario) {
		this.criterioTipoUsuario = criterioTipoUsuario;
	}

	public SelectItem[] getSelectTipoUsuario() {
		return selectTipoUsuario;
	}

	public void setSelectTipoUsuario(SelectItem[] selectTipoUsuario) {
		this.selectTipoUsuario = selectTipoUsuario;
	}

	public boolean isBuscando() {
		return buscando;
	}

	public void setBuscando(boolean buscando) {
		this.buscando = buscando;
	}
	
}

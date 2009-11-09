package dondeando.bean;

import static utilidades.varios.NombresBean.GESTION_NOTIFICACIONES_BEAN;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.SERVICIO_NOTIFICACION;
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
import utilidades.varios.SelectItemBuilder;
import dondeando.binding.GestionNotificacionesBinding;
import dondeando.modelo.entidades.Notificacion;
import dondeando.modelo.entidades.Usuario;
import dondeando.modelo.servicio.ServicioNotificacion;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_NOTIFICACIONES_BEAN)
public class GestionNotificacionesBean {

	//Constantes
	private static final String ACCION_MARCAR_LEIDA = "_marcarComoLeida_";
	
	//Atributos
	private List<Notificacion> listaNotificaciones;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	private boolean desplegado;
	private Usuario criterioUsuario;
	private Boolean criterioLeidas = Boolean.TRUE;
	
	private ProtocoloBusqueda protocoloBusqueda;
	private SelectItem[] selectSiNo;
	private boolean primeraBusqueda = true;

	//Servicios
	@In(value=SERVICIO_NOTIFICACION, create=true)
	private ServicioNotificacion servicioNotificacion;
	
	//Utilidades
	@In(value=NombresBean.GESTION_NOTIFICACIONES_BINDING, create=true)
	private GestionNotificacionesBinding binding;
	
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
		desplegado = true;
	}
	
	public void cargarArgumentosDeEntrada(){
		//Cargar los datos y lanzar la búsqueda
		if(mapaArgumentos!=null && mapaArgumentos.contieneProtocoloBusqueda())
			protocoloBusqueda = mapaArgumentos.getProtocoloBusqueda();
		
		if(protocoloBusqueda!=null){
			if(protocoloBusqueda.getParamsBusqueda().containsKey(Notificacion.ATRIBUTO_USUARIO))
				criterioUsuario = (Usuario)protocoloBusqueda.getParamsBusqueda().get(Notificacion.ATRIBUTO_USUARIO);
			if(protocoloBusqueda.getParamsBusqueda().containsKey(Notificacion.ATRIBUTO_LEIDA))
				criterioLeidas = (Boolean)protocoloBusqueda.getParamsBusqueda().get(Notificacion.ATRIBUTO_LEIDA);

			if(protocoloBusqueda.isLanzaConsulta() && primeraBusqueda){
				primeraBusqueda = false;
				buscar();
			}
		}
	}
	
	/**
	 * Realiza la operación concreta sobre la notificación seleccionada.
	 * @param operacion Operación a realizar
	 * @return	Regla de navegación necesaria para realizar la acción
	 */
	private String realizarOperacion(String operacion){
		String outcome = "";
		boolean operacionRealizada = false;
		if(estadoDeSeleccionTabla.size()==1){
			
			Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
			Notificacion notificacion = listaNotificaciones.get(seleccion);
			if(ACCION_MARCAR_LEIDA.equals(operacion)){
				
				if(notificacion!=null && !notificacion.isLeida()){
					servicioNotificacion.leer(notificacion);
					operacionRealizada = true;
				}else
					utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_MARCAR_NOTIFICACION_MARCADA"));
			}
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		if(operacionRealizada)
			estadoDeSeleccionTabla.clear();
		
		return outcome;
	}
	
	/**
	 * Marca como leida la notificación
	 * @return Regla de navegación a la pantalla del usuario
	 */
	public void marcar(){
		realizarOperacion(ACCION_MARCAR_LEIDA);
	}
	
	/**
	 * Busca las notificaciones que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaNotificaciones = servicioNotificacion.encontrarNotificacionesPorUsuarioYEstado(criterioUsuario, criterioLeidas);
		if(binding.getBusqueda()==null) binding.setBusqueda(new CoreShowDetailHeader());
		desplegado = false;
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaNotificaciones!=null && listaNotificaciones.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Limpia los criterios de búsqueda
	 */
	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioUsuario= null;
		criterioLeidas = null;
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaNotificaciones != null ? listaNotificaciones.size() : "0");
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

	public SelectItem[] getSelectSiNo() {
		return selectSiNo;
	}

	public void setSelectSiNo(SelectItem[] selectSiNo) {
		this.selectSiNo = selectSiNo;
	}

	public Usuario getCriterioUsuario() {
		return criterioUsuario;
	}

	public void setCriterioUsuario(Usuario criterioUsuario) {
		this.criterioUsuario = criterioUsuario;
	}

	public Boolean getCriterioLeidas() {
		return criterioLeidas;
	}

	public void setCriterioLeidas(Boolean criterioLeidas) {
		this.criterioLeidas = criterioLeidas;
	}

	public GestionNotificacionesBinding getBinding() {
		return binding;
	}

	public void setBinding(GestionNotificacionesBinding binding) {
		this.binding = binding;
	}

	public List<Notificacion> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(List<Notificacion> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}
}

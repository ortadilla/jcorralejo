package dondeando.bean;

import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.DETALLES_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_FORO;
import static utilidades.jsf.ConstantesReglasNavegacion.EDITAR_LOCAL;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_FOROS;
import static utilidades.jsf.ConstantesReglasNavegacion.GESTION_LOCALES;
import static utilidades.varios.NombresBean.GESTION_FOROS_BEAN;
import static utilidades.varios.NombresBean.GESTION_FOROS_BINDING;
import static utilidades.varios.NombresBean.GESTION_LOCALES_BINDING;
import static utilidades.varios.NombresBean.MAPA_ARGUMENTOS;
import static utilidades.varios.NombresBean.MENSAJES_CORE;
import static utilidades.varios.NombresBean.PROTOCOLO_EDICION;
import static utilidades.varios.NombresBean.SERVICIO_FORO;
import static utilidades.varios.NombresBean.SERVICIO_LOCAL;
import static utilidades.varios.NombresBean.SERVICIO_PERMISO_USUARIO;
import static utilidades.varios.NombresBean.SERVICIO_PROVINCIA;
import static utilidades.varios.NombresBean.SERVICIO_TIPO_LOCAL;
import static utilidades.varios.NombresBean.UTIL_JSF_CONTEXT;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.RowKeySetImpl;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;

import utilidades.jsf.ConstantesReglasNavegacion;
import utilidades.jsf.UtilJsfContext;
import utilidades.varios.EntidadConCodigo;
import utilidades.varios.HerramientasList;
import utilidades.varios.MapaArgumentos;
import utilidades.varios.MensajesCore;
import utilidades.varios.NombresBean;
import utilidades.varios.Permisos;
import utilidades.varios.ProtocoloEdicion;
import utilidades.varios.SelectItemBuilder;
import dondeando.binding.GestionForosBinding;
import dondeando.binding.GestionLocalesBinding;
import dondeando.modelo.entidades.Foro;
import dondeando.modelo.entidades.Local;
import dondeando.modelo.entidades.Provincia;
import dondeando.modelo.entidades.TipoLocal;
import dondeando.modelo.servicio.ServicioForo;
import dondeando.modelo.servicio.ServicioLocal;
import dondeando.modelo.servicio.ServicioPermisoUsuario;
import dondeando.modelo.servicio.ServicioProvincia;
import dondeando.modelo.servicio.ServicioTipoLocal;

@Scope(ScopeType.CONVERSATION)
@Name(GESTION_FOROS_BEAN)
public class GestionForosBean {
	
	//Constantes
	private static final String ACCION_AGREGAR_FORO = "_agregarForo_";
	private static final String ACCION_DETALLES_FORO = "_detallesForo_";
	private static final String ACCION_MODIFICAR_FORO = "_modificarForo_";
	private static final String ACCION_ELIMINAR_FORO = "_eliminarForo_";
	private static final String ACCION_RECUPERAR_FORO = "_recuperarForo_";
	
	//Atributos
	private boolean desplegado;
	private String criterioTitulo;
	private Boolean criterioActivo = Boolean.TRUE;
	private SelectItem[] selectSiNo;
	
	private List<Foro> listaForos;
	private RowKeySet estadoDeSeleccionTabla = new RowKeySetImpl();
	
	//Utilidades
	@In(value=GESTION_FOROS_BINDING, create=true)
	private GestionForosBinding binding;
	
	@In(value=MENSAJES_CORE, create=true)
	private MensajesCore mensajesCore;
	
	@In(value=UTIL_JSF_CONTEXT, create=true)
	private UtilJsfContext utilJsfContext;
	
	@In(value=MAPA_ARGUMENTOS, required=false)
	@Out(value=MAPA_ARGUMENTOS, required=false)
	private MapaArgumentos mapaArgumentos;
	
	//Servicios
	@In(value=SERVICIO_TIPO_LOCAL, create=true)
	private ServicioTipoLocal servicioTipoLocal;
	
	@In(value=SERVICIO_PROVINCIA, create=true)
	private ServicioProvincia servicioProvincia;
	
	@In(value=SERVICIO_LOCAL, create=true)
	private ServicioLocal servicioLocal;

	@In(value=SERVICIO_FORO, create=true)
	private ServicioForo servicioForo;
	
	@In(value=SERVICIO_PERMISO_USUARIO, create=true)
	private ServicioPermisoUsuario servicioPermisoUsuario;

	
	
	
	@Create
	@Begin(join=true)
	public void inicializar(){
		desplegado = true;
		selectSiNo = SelectItemBuilder.creaSelectItemsSiNo();
	}
	
	/**
	 * Busca los Foros que coincidan con los criterios indicados en pantalla
	 */
	public void buscar(){
		listaForos = servicioForo.encontrarForosPorTituloYActivo(criterioTitulo, criterioActivo);
		servicioForo.rellenarPropiedadesNoMapeadas(listaForos);
		desplegado = false;
		binding.getBusqueda().setDisclosed(desplegado);
		if(listaForos!=null && listaForos.size()==1)
			estadoDeSeleccionTabla.add(0);
	}
	
	/**
	 * Limpia los criterios de búsqueda
	 */
	public void limpiar(){
		binding.getBusqueda().getChildren().clear();
		criterioTitulo = null;
		criterioActivo = null;
	}
	
	/**
	 * Navega a la pantalla de edición de foros para agregar uno nuevo
	 * @return Regla de navegación de la pantalla de edición de foros 
	 */
	public String agregar(){
		return realizarOperacion(ACCION_AGREGAR_FORO);
	}
	
	/**
	 * Navega a la pantalla de detalles del foro
	 * @return Regla de navegación de la pantalla de detalles del foro
	 */
	public String detalles(){
		return realizarOperacion(ACCION_DETALLES_FORO);
	}

	/**
	 * Navega a la pantalla de edición de foros para modificar el seleccionado
	 * @return Regla de navegación de la pantalla de edición de foros
	 */
	public String modificar(){
		return realizarOperacion(ACCION_MODIFICAR_FORO);
	}
	
	/**
	 * Elimina el foro seleccionado
	 */
	public void eliminar(){
		realizarOperacion(ACCION_ELIMINAR_FORO);
	}
	
	/**
	 * Recupera el foro seleccionado
	 */
	public void recuperar(){
		realizarOperacion(ACCION_RECUPERAR_FORO);
	}
	
	/**
	 * Devuelve un mensaje con el número de elementos de la tabla de resultados
	 * @return mensaje con el número de elementos de la tabla de resultados
	 */
	public String getNumeroElementosTabla(){
		return mensajesCore.obtenerTexto("ELEMENTOS_ENCONTRADOS", listaForos != null ? listaForos.size() : "0");
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
		if(estadoDeSeleccionTabla.size()==1 || ACCION_AGREGAR_FORO.equals(operacion)){
			
			if(ACCION_AGREGAR_FORO.equals(operacion)){
				if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
				mapaArgumentos.limpiaMapa();
				ProtocoloEdicion protocolo = new ProtocoloEdicion(null, GESTION_FOROS, null);
				mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
		
				outcome = EDITAR_FORO;
			}
			else{
				Integer seleccion = (Integer)estadoDeSeleccionTabla.iterator().next();
				Foro foro = listaForos.get(seleccion);
				if(ACCION_MODIFICAR_FORO.equals(operacion) || ACCION_DETALLES_FORO.equals(operacion)){
					
					if(mapaArgumentos==null) mapaArgumentos = new MapaArgumentos();
					mapaArgumentos.limpiaMapa();
					ProtocoloEdicion protocolo = new ProtocoloEdicion(foro, GESTION_FOROS, null);
					mapaArgumentos.setArgumento(PROTOCOLO_EDICION, protocolo);
			
					outcome = ACCION_MODIFICAR_FORO.equals(operacion) ? EDITAR_FORO : DETALLES_FORO;
					operacionRealizada = true;
				
				}else if(ACCION_ELIMINAR_FORO.equals(operacion)){
					
					if(foro!=null && foro.isActivo()){
						servicioForo.desactivarLocal(foro);
						servicioForo.descartarLocal(foro);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("FORO_ELIMINADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_ELIMINAR_FORO_ELIMINADO"));
	
				}else if(ACCION_RECUPERAR_FORO.equals(operacion)){
	
					if(foro!=null && !foro.isActivo()){
						servicioForo.activarLocal(foro);
						utilJsfContext.insertaMensajeInformacion(mensajesCore.obtenerTexto("FORO_RECUPERADO"));
						operacionRealizada = true;
					}else
						utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("ERROR_RECUPERAR_FORO_ACTIVO"));
				}
			}
			
		}else
			utilJsfContext.insertaMensaje(mensajesCore.obtenerTexto("SELECCIONAR_UNO"));
		
		if(operacionRealizada)
			estadoDeSeleccionTabla.clear();
		
		return outcome;
	}



	public boolean isDesplegado() {
		return desplegado;
	}
	public void setDesplegado(boolean desplegado) {
		this.desplegado = desplegado;
	}
	public RowKeySet getEstadoDeSeleccionTabla() {
		return estadoDeSeleccionTabla;
	}
	public void setEstadoDeSeleccionTabla(RowKeySet estadoDeSeleccionTabla) {
		this.estadoDeSeleccionTabla = estadoDeSeleccionTabla;
	}
	public Boolean getCriterioActivo() {
		return criterioActivo;
	}
	public void setCriterioActivo(Boolean criterioActivo) {
		this.criterioActivo = criterioActivo;
	}
	public SelectItem[] getSelectSiNo() {
		return selectSiNo;
	}
	public void setSelectSiNo(SelectItem[] selectSiNo) {
		this.selectSiNo = selectSiNo;
	}

	public String getCriterioTitulo() {
		return criterioTitulo;
	}

	public void setCriterioTitulo(String criterioTitulo) {
		this.criterioTitulo = criterioTitulo;
	}

	public GestionForosBinding getBinding() {
		return binding;
	}

	public void setBinding(GestionForosBinding binding) {
		this.binding = binding;
	}

}
